package org.example.webphim.service;

import lombok.RequiredArgsConstructor;
import org.example.webphim.entity.Blog;
import org.example.webphim.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    // Lấy danh sách tất cả blog
    public Page<Blog> getAllBlogs(Boolean status, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("publishedAt").descending());
        return blogRepository.findByStatus(status, pageRequest);
    }

    // Lấy danh sách blog theo id
    public Blog getBlog(Integer id, String slug, Boolean status) {
        return blogRepository.findByIdAndSlugAndStatus(id, slug, status).orElse(null);
    }
}
