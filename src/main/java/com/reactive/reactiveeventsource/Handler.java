package com.reactive.reactiveeventsource;

import com.reactive.reactiveeventsource.dao.Dao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@Slf4j
public class Handler {

    private final Dao dao;

    public Handler(Dao dao)
    {
        this.dao = dao;
    }
    private final List<MediaType> acceptedMediaTypes = List.of(MediaType.APPLICATION_JSON, MediaType.APPLICATION_STREAM_JSON, MediaType.TEXT_EVENT_STREAM, MediaType.APPLICATION_STREAM_JSON);

    public Mono<ServerResponse> getAllId(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(serverRequest.headers().accept().stream().filter(acceptedMediaTypes::contains)
                        .findFirst().orElse(MediaType.APPLICATION_STREAM_JSON))
                .body(dao.getAllUniverseID(), Integer.class);
    }
}
