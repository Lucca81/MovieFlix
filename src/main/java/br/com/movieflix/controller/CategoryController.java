package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.controller.response.MovieResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Category", description = "Recurso responsavel pelo gerenciamento da controller")
public interface CategoryController {



    @Operation(summary = "Buscar category", description = "Método responsável por retornar todos os categories cadastrados.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os category cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema (implementation = MovieResponse.class))))
    @GetMapping
    ResponseEntity<List<CategoryResponse>> getAllCategories();

    @Operation(summary = "Salvar category", description = "Método responsavel por salvar um novo category",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "descrição do retorno",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request);

    @Operation(summary = "Buscar categories por id", description = "Método responsável por buscar categories por id.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "category encontrado com sucesso.",
            content = @Content(schema = @Schema (implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "category não encontrado", content = @Content())
    ResponseEntity<CategoryResponse> getCategoryById(@PathVariable long id);

    @Operation(summary = "Deletar categories por id", description = "Método responsável por deletar categories por id.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "category encontrado com sucesso.",content = @Content())
    @ApiResponse(responseCode = "404", description = "category não encontrado", content = @Content())
    ResponseEntity<Void> deleteCategoryById(@PathVariable Long id);

}
