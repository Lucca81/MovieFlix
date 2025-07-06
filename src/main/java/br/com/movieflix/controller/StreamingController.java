package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.controller.response.StreamingResponse;
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

@Tag(name = "Streaming", description = "Recurso responsavel pelo gerenciamento da controller")
public interface StreamingController {

    @Operation(summary = "Buscar streamings", description = "Método responsável por retornar todos os streamings cadastrados.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os streamings cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @GetMapping
    ResponseEntity<List<StreamingResponse>> getAll();

    @Operation(summary = "Salvar streaming", description = "Método responsavel por salvar um novo streaming",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "descrição do retorno",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest streamingRequest);

    @Operation(summary = "Buscar streamings por id", description = "Método responsável por buscar streamings por id.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "streaming encontrado com sucesso.",
            content = @Content(schema = @Schema (implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Streaming não encontrado", content = @Content())
    ResponseEntity<StreamingResponse> getById(@PathVariable Long id);

    @Operation(summary = "Deletar streamings por id", description = "Método responsável por deletar streamings por id.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "streaming encontrado com sucesso.",content = @Content())
    @ApiResponse(responseCode = "404", description = "streaming não encontrado", content = @Content())
    ResponseEntity<Void> deleteById(Long id);
}
