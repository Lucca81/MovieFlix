package br.com.movieflix.service;

import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.StreamingModel;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class StreamingService {

    private final StreamingRepository streamingRepository;

    //Listar todos
    public List<StreamingModel> findAll(){
        return streamingRepository.findAll();
    }

    //save
    public StreamingModel saveStreaming(StreamingModel streamingModel){
        return streamingRepository.save(streamingModel);

    }

    //busarPorId
    public Optional<StreamingModel> findById(Long id){
        return streamingRepository.findById(id);

    }


    //deleteStreaming
    public void deleteByid(Long id){
        streamingRepository.deleteById(id);

    }
}
