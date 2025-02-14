package com.example.demo.service;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.time.Duration;
import reactor.core.publisher.Flux;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.http.MediaType;

@Component
public class TimeHandler {
	public Mono<ServerResponse> getTime(ServerRequest serverRequest) {
		return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date())), String.class);
	}
	public Mono<ServerResponse> getDate(ServerRequest serverRequest) {
		return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Today is " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())), String.class);
	}


	public Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(  // 1
                Flux.interval(Duration.ofSeconds(1)).   // 2
                        map(l -> new SimpleDateFormat("HH:mm:ss").format(new Date())), 
                String.class);
    }

}
