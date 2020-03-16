package com.ccz.service;

import com.ccz.po.Blog;
import com.ccz.po.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentsByBlogEquals(Blog blog);

    Page<Comment> findAllComments(Pageable pageable);

    Comment saveComment(Comment comment);

    Comment updateComment(Comment comment);

    void deleteComment(Long id);
}
