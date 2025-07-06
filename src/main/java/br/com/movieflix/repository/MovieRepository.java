package br.com.movieflix.repository;

import br.com.movieflix.entity.CategoryModel;
import br.com.movieflix.entity.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, Long> {

    List<MovieModel> findMovieByCategories (List<CategoryModel> categories);

    List<MovieModel> findTop5ByOrderByRatingDesc();

}
