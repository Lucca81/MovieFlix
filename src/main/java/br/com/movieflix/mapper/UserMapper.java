package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.UserResponse;
import br.com.movieflix.entity.UserModel;
import br.com.movieflix.repository.UserRepository;
import lombok.Builder;
import lombok.experimental.UtilityClass;

@Builder
@UtilityClass
public class UserMapper {

    public static UserModel toUser(UserRequest userRequest){
        return UserModel
                .builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public static UserResponse toUserResponse(UserModel userModel){
        return UserResponse
                .builder()
                .id(userModel.getId())
                .name(userModel.getName())
                .email(userModel.getEmail())
                .build();
    }
}
