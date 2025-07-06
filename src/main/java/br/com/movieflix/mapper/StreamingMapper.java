package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.StreamingRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.controller.response.StreamingResponse;
import br.com.movieflix.entity.StreamingModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    //TODO: Sobrescreve StreamingModel, que recebe StreamingRequest e retorna StreamingModel
    public static StreamingModel toStreaming(StreamingRequest streamingRequest){
        return StreamingModel
                .builder()
                .name(streamingRequest.name())
                .build();

    }

    //TODO: Sobrescreve StreamingResponse passando StreamingModel e retorna StreamingResponse
    public static StreamingResponse toStrimingResponse(StreamingModel streamingModel){
        return StreamingResponse
                .builder()
                .name(streamingModel.getName())
                .id(streamingModel.getId())
                .build();
    }



}
