package org.example.webphim.service;

import lombok.RequiredArgsConstructor;
import org.example.webphim.entity.Movie;
import org.example.webphim.model.enums.MovieType;
import org.example.webphim.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<Movie> getHotMovies(Boolean status, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("view").descending());
        return movieRepository.findByStatus(status, pageRequest);
    }

    public List<Movie> getMoviesByType(MovieType movieType, Boolean status, Sort sort) {
        return movieRepository.findByTypeAndStatus(movieType, status, sort);
    }

    public Page<Movie> getMoviesByType(MovieType movieType, Boolean status, Pageable pageable) {
        return movieRepository.findByTypeAndStatus(movieType, status, pageable);
    }

    public Page<Movie> getMoviesByType(MovieType movieType, Boolean status, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("publishedAt").descending());
        return movieRepository.findByTypeAndStatus(movieType, status, pageRequest);
    }

    public Movie getMovie(Integer id, String slug, Boolean status) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, status).orElse(null);
    }

    public List<Movie> getRelatedMovies(Integer id, MovieType movieType, Boolean status, Integer size) {
        return movieRepository.findByTypeAndStatusAndRatingGreaterThanEqualAndIdNotOrderByRatingDescViewDescPublishedAtDesc(movieType, status, 5.0, id)
                .stream().limit(size).toList();
    }
}
