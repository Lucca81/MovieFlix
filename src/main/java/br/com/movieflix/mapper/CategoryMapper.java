package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.CategoryModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static CategoryModel toCategory(CategoryRequest categoryRequest){
        return CategoryModel
                .builder()
                .name(categoryRequest.name())
                .build();
    }
    public static CategoryResponse toCatecoryResponse(CategoryModel category){
        return CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .build();

    }


}
