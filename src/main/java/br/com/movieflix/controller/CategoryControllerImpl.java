package br.com.movieflix.controller;


import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.CategoryModel;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryControllerImpl  implements  CategoryController {

    private final CategoryService categoryService;



    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List <CategoryResponse>list = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCatecoryResponse)
                .toList();

        return ResponseEntity.ok(list);


    }

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request) {
        CategoryModel newCategory = CategoryMapper.toCategory(request);
        CategoryModel savadCategory = categoryService.saveCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCatecoryResponse(savadCategory));


    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCatecoryResponse(category)))
                .orElse(ResponseEntity.notFound().build());


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
