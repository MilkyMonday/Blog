package com.ccz.web;

import com.ccz.po.Blog;
import com.ccz.po.Comment;
import com.ccz.service.BlogService;
import com.ccz.service.CommentService;
import com.ccz.service.TypeService;
import com.ccz.service.UserService;
import com.ccz.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(Model model) {
        List<Blog> blogs = blogService.listBlogsByFlag();
        Collections.reverse(blogs);
        model.addAttribute("blogs", blogs);
        return "index";
    }

    @GetMapping("/posts")
    public String listBlogs(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlogs(pageable));
        return "posts";
    }

    @GetMapping("/post/{id}")
    public String getBlog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getBlog(id);
        List<Comment> comments = commentService.findCommentsByBlogEquals(blog);
        blog.setViews(blog.getViews() + 1);
        blogService.saveBlog(blog);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        model.addAttribute("comments",comments);
        model.addAttribute("blog", blog);
        return "post";
    }

    @PostMapping("/post/saveComment")
    public ModelAndView saveComment(@Valid Comment comment) {
        comment.setCreateTime(new Date());
        commentService.saveComment(comment);
        return new ModelAndView("redirect:/post/"+comment.getBlog().getId());
    }

    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/about")
    public String toAbout() {
        return "about";
    }

    @GetMapping("/post/user/{id}")
    public String toUserBlogs(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model) {
        Page<Blog> page = blogService.listBlogsByUser(userService.getUser(id), pageable);
        model.addAttribute("page", page);
        return "posts";
    }

    @GetMapping("/post/type/{id}")
    public String toTypeBlogs(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model) {
        Page<Blog> page = blogService.listBlogsByType(typeService.getType(id), pageable);
        model.addAttribute("page", page);
        return "posts";
    }
}
