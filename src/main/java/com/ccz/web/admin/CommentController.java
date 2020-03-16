package com.ccz.web.admin;

import com.ccz.po.Comment;
import com.ccz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public String index() {
        return "redirect:/admin/comments/list";
    }

    @GetMapping("/comments/list")
    public String listComment(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable, Model model) {
        model.addAttribute("page", commentService.findAllComments(pageable));
        return "admin/commentList";
    }

    @GetMapping("/comments/update/{id}")
    public String updateComment(@Valid Comment comment) {
        commentService.updateComment(comment);
        return "redirect:/admin/comments/list";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/admin/comments/list";
    }
}
