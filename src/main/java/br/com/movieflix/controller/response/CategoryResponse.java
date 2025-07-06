package br.com.movieflix.controller.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryResponse( Long id, String name) {

}
