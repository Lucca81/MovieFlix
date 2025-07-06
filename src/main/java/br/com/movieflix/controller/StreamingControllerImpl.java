package br.com.movieflix.controller;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.StreamingModel;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor

public class StreamingControllerImpl implements StreamingController {

    private final StreamingService streamingService;

    //getAllStreaming
    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAll(){
        List<StreamingResponse> streamings = streamingService.findAll()
        .stream()
                .map(StreamingMapper::toStrimingResponse)
                .toList();

        return ResponseEntity.ok(streamings);

    }

    //saveStreaming
    @PostMapping()
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest streamingRequest){
        StreamingModel newStreaming = StreamingMapper.toStreaming(streamingRequest);
        StreamingModel savedStreaming = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStrimingResponse(savedStreaming));
    }

    //findById
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id){
        streamingService.findById(id);
        return streamingService.findById(id)
                .map(streamingModel -> ResponseEntity.ok(StreamingMapper.toStrimingResponse(streamingModel)))
                .orElse(ResponseEntity.notFound().build());

    }
    //deleById
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(Long id){
        streamingService.deleteByid(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }







}
