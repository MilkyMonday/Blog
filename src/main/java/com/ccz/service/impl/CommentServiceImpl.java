package com.ccz.service.impl;

import com.ccz.dao.CommentRepository;
import com.ccz.po.Blog;
import com.ccz.po.Comment;
import com.ccz.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentsByBlogEquals(Blog blog) {
        return commentRepository.findCommentsByBlogEquals(blog);
    }

    @Override
    public Page<Comment> findAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }


    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Comment comment) {
        Comment one = commentRepository.getOne(comment.getId());
        one.setContent(comment.getContent());
        one.setNickname(comment.getNickname());
        one.setEmail(comment.getEmail());
        return commentRepository.save(one);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
