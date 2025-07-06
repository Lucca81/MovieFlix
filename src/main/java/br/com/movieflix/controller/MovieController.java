package br.com.movieflix.controller;

import br.com.movieflix.controller.request.MovieRequest;
import br.com.movieflix.controller.response.MovieResponse;
import br.com.movieflix.entity.MovieModel;
import br.com.movieflix.mapper.MovieMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Movie", description = "Recurso responsavel pelo gerenciamento da controller")
public interface MovieController {


    @Operation(summary = "Salvar movie", description = "Método responsavel por salvar um novo movie",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "descrição do retorno",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))

    ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest movieRequest);

    @Operation(summary = "Buscar movies", description = "Método responsável por retornar todos os movies cadastrados.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados.",
            content = @Content(array = @ArraySchema(schema = @Schema (implementation = MovieResponse.class))))
    @GetMapping
    ResponseEntity<List<MovieResponse>> findAll();

    @Operation(summary = "Buscar movies por id", description = "Método responsável por buscar movies por id.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme encontrado com sucesso.",
            content = @Content(schema = @Schema (implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<MovieResponse> findById(@Valid @PathVariable Long id);

    @Operation(summary = "Alterar movie", description = "Método responsável por alterar dedos do movie.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filme alterado com sucesso.",
            content = @Content(schema = @Schema (implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request);

    @Operation(summary = "Buscar movies por categoria", description = "Método responsável por buscar movies por categoria.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Filmes encontrado com sucesso.",
            content = @Content(array = @ArraySchema(schema = @Schema (implementation = MovieResponse.class))))
    ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category);

    @Operation(summary = "Deletar movies por id", description = "Método responsável por deletar movies por id.",
            security =  @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Filme encontrado com sucesso.",content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não encontrado", content = @Content())
    ResponseEntity<Void> delete(@PathVariable Long id);
}
