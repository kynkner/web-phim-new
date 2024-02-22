package org.example.webphim.controller;

import lombok.RequiredArgsConstructor;
import org.example.webphim.entity.Blog;
import org.example.webphim.entity.Movie;
import org.example.webphim.entity.Review;
import org.example.webphim.model.enums.MovieType;
import org.example.webphim.service.BlogService;
import org.example.webphim.service.MovieService;
import org.example.webphim.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final BlogService blogService;
    private final ReviewService reviewService;

    @GetMapping("/")
    public String getHome(Model model) {
        Page<Movie> pageDataBannerMovie = movieService.getHotMovies(true, 1, 2);
        Page<Movie> pageDataHotMovie = movieService.getHotMovies(true, 1, 8);
        Page<Movie> pageDataSingleMovie = movieService.getMoviesByType(MovieType.PHIM_LE, true, 1, 20);
        Page<Movie> pageDataSeriesMovie = movieService.getMoviesByType(MovieType.PHIM_BO, true, 1, 20);
        Page<Movie> pageDataCinemaMovie = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, 1, 20);

        model.addAttribute("hotMovieList", pageDataHotMovie.getContent());
        model.addAttribute("singleMovieList", pageDataSingleMovie.getContent());
        model.addAttribute("seriesMovieList", pageDataSeriesMovie.getContent());
        model.addAttribute("cinemaMovieList", pageDataCinemaMovie.getContent());
        return "index";
    }

    @GetMapping("/phimle")
    public String getSingleMovies(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "20") Integer size) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_LE, true, page, size);

        model.addAttribute("singleMovieList", pageData.getContent());
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "phimle";
    }

    @GetMapping("/phimbo")
    public String getSeriesMovies(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "20") Integer size) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_BO, true, page, size);

        model.addAttribute("seriesMovieList", pageData.getContent());
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "phimbo";
    }

    @GetMapping("/phimchieurap")
    public String getCinemaMovies(Model model,
                                  @RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "20") Integer size) {
        Page<Movie> pageData = movieService.getMoviesByType(MovieType.PHIM_CHIEU_RAP, true, page, size);

        model.addAttribute("cinemaMovieList", pageData.getContent());
        model.addAttribute("pageData", pageData);
        model.addAttribute("currentPage", page);
        return "phimchieurap";
    }

    @GetMapping("/phim/{id}/{slug}")
    public String getMovieById(@PathVariable Integer id, @PathVariable String slug, Model model) {
        Movie movie = movieService.getMovie(id, slug, true);
        List<Movie> relatedMovieList = movieService.getRelatedMovies(id, movie.getType(), true, 6);
        List<Review> reviewList = reviewService.getReviewsByMovie(id);

        model.addAttribute("movie", movie);
        model.addAttribute("relatedMovieList", relatedMovieList);
        model.addAttribute("reviewList", reviewList);
        return "phim";
    }

//    @GetMapping("/danh-sach-bai-viet")
//    public String getBlog(Model model,
//                          @RequestParam(required = false, defaultValue = "1") Integer page,
//                          @RequestParam(required = false, defaultValue = "20") Integer size) {
//        Page<Blog> pageData = blogService.getAllBlogs(true, page, size);
//        model.addAttribute("blogList", pageData.getContent());
//        model.addAttribute("pageData", pageData);
//        model.addAttribute("currentPage", page);
//        return "danh-sach-bai-viet";
//    }
//
//    @GetMapping("/danh-sach-bai-viet/{id}/{slug}")
//    public String getBlogDetails(@PathVariable Integer id, @PathVariable String slug, Model model) {
//        Blog blog = blogService.getBlog(id, slug, true);
//        model.addAttribute("blog", blog);
//        return "web/chi-tiet-bai-viet";
//    }

    @GetMapping("/dang-nhap")
    public String getLogin() {
        return "dang-nhap";
    }

    @GetMapping("/dang-ky")
    public String getSignUp() {
        return "dang-ky";
    }
}
