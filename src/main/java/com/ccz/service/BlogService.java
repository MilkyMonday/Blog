package com.ccz.service;

import com.ccz.po.Blog;
import com.ccz.po.Type;
import com.ccz.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog saveBlog(Blog blog);

    Blog getBlog(Long id);

    Page<Blog> listBlogs(Pageable pageable);

    List<Blog> listBlogsByFlag();

    List<Blog> listBlogsByType(Type type);

    Page<Blog> listBlogsByType(Type type, Pageable pageable);

    Page<Blog> listBlogsByUser(User user, Pageable pageable);

    Blog updateBlog(Blog blog);

    void deleteBlog(Long id);

    void deleteBlog(List<Blog> list);


}
