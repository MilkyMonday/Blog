package com.ccz.dao;

import com.ccz.po.Blog;
import com.ccz.po.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentsByBlogEquals(Blog blog);
}
