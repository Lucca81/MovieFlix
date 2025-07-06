package br.com.movieflix.service;


import br.com.movieflix.entity.CategoryModel;
import br.com.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<CategoryModel> findAll(){
    return categoryRepository.findAll();
}

    public CategoryModel saveCategory(CategoryModel category){
        return categoryRepository.save(category);
    }

    public Optional<CategoryModel> findById(Long id){
        return categoryRepository.findById(id);

    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);

    }
}
