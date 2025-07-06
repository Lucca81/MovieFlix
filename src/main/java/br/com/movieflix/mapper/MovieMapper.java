package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.CategoryModel;
import br.com.movieflix.entity.MovieModel;
import br.com.movieflix.entity.StreamingModel;
import lombok.Builder;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static MovieModel toMovie(MovieRequest request){
        List<CategoryModel> categories = request.categories().stream()
                .map(categoryId -> CategoryModel.builder().id(categoryId).build())
                .toList();

        List<StreamingModel> streamings = request.streamings().stream()
                .map(streamingId -> StreamingModel.builder().id(streamingId).build())
                .toList();


        return MovieModel
                .builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();

    }

    public static MovieResponse toMovieResponse(MovieModel movie){
        List<CategoryResponse> categories = movie.getCategories().stream()
                .map(categoryModel -> CategoryMapper.toCatecoryResponse(categoryModel))
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings().stream()
                .map(streamingModel -> StreamingMapper.toStrimingResponse(streamingModel))
                .toList();


        return MovieResponse
                .builder()
                .title(movie.getTitle())
                .id(movie.getId())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

}
