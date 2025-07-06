package br.com.movieflix.service;



import br.com.movieflix.config.JWTUserData;
import br.com.movieflix.entity.CategoryModel;
import br.com.movieflix.entity.MovieModel;
import br.com.movieflix.entity.StreamingModel;
import br.com.movieflix.repository.MovieRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service

public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieService(MovieRepository movieRepository, CategoryService categoryService, StreamingService streamingService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public MovieModel save(MovieModel movieModel){
        movieModel.setCategories(this.findCategories(movieModel.getCategories()));
        movieModel.setStreamings(this.findStreamings(movieModel.getStreamings()));
        return movieRepository.save(movieModel);
    }

    public List<MovieModel> findAll(){
        return movieRepository.findAll();
    }

    public List<MovieModel> findByCategory(Long categoryId){
        return movieRepository.findMovieByCategories(List.of(CategoryModel.builder().id(categoryId).build()));
    }

    public Optional<MovieModel> findById(Long id){
        return movieRepository.findById(id);
    }

    public Optional<MovieModel> update(Long id, MovieModel updatedMovie){
        Optional<MovieModel> optMovie = movieRepository.findById(id);
        if(optMovie.isPresent()){
            List<CategoryModel> categories = this.findCategories(updatedMovie.getCategories());
            List<StreamingModel> streamings = this.findStreamings(updatedMovie.getStreamings());

            MovieModel movie = optMovie.get();
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setRating(updatedMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            movieRepository.save(movie);
            return Optional.of(movie);
        }
        return Optional.empty();
    }

    public  void delete(long movieId){
        movieRepository.deleteById(movieId);
    }

    private List<CategoryModel> findCategories(List<CategoryModel> categories){
    List<CategoryModel> categoriesFound = new ArrayList<>();
    categories.forEach(category -> categoryService.findById(category.getId()).ifPresent(categoriesFound::add));
    return categoriesFound;

    }

    private List<StreamingModel> findStreamings(List<StreamingModel> streamings){
        List<StreamingModel> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add));
        return streamingsFound;

    }
}


