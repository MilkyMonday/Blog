package com.ccz.web.admin;

import com.ccz.po.Blog;
import com.ccz.po.User;
import com.ccz.service.BlogService;
import com.ccz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String index() {
        return "redirect:/admin/blogs/list";
    }

    @GetMapping("/blogs/list")
    public String listBlogs(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", blogService.listBlogs(pageable));
        return "admin/blogList";
    }

    @PostMapping("/saveBlog")
    public String saveBlog(@Valid Blog blog, HttpSession session) {
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setViews(0);
        blog.setCreateTime(new Date());
        blog.setUser((User) session.getAttribute("USER_SESSION"));
        blog.setUpdateTime(new Date());
        blogService.saveBlog(blog);
        return "redirect:/posts";
    }

    @GetMapping("/blogs/update/{id}")
    public String toUpdateBlog(@PathVariable Long id, Model model) {
        model.addAttribute("types", typeService.listTypes());
        model.addAttribute("blog", blogService.getBlog(id));
        return "admin/blogUpdate";
    }

    @PostMapping("/blogs/updateBlogs")
    public String updateBlog(@Valid Blog blog) {
        blogService.updateBlog(blog);
        return "redirect:/admin/blogs/list";
    }

    @GetMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs/list";
    }

    @GetMapping("/edit")
    public String toEdit(Model model) {
        model.addAttribute("types", typeService.listTypes());
        return "admin/blogInsert";
    }
}
