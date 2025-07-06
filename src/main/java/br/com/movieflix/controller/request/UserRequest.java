package br.com.movieflix.controller.request;

public record UserRequest(String password, String email, String name) {
}
