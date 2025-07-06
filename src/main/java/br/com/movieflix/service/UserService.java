package br.com.movieflix.service;

import br.com.movieflix.entity.UserModel;
import br.com.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public UserModel saveUser(UserModel userModel){
        String password = userModel.getPassword();
        userModel.setPassword(passwordEncoder.encode(password));
        return userRepository.save(userModel);
    }

}

