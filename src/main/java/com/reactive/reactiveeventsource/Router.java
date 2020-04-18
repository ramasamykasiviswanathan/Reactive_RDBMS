package com.reactive.reactiveeventsource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;

@Configuration
@Slf4j
public class Router {

    private Handler handler;

    public Router(Handler handler)
    {
        this.handler = handler;
    }


    @Bean
    public RouterFunction<ServerResponse> routes(){
        return RouterFunctions.route()
                .GET("/getAllID", handler::getAllId)
                .build();
    }
}



/**
 * let sse = new EventSource('//localhost:8080/getAllID',{withxCredentials:false});
 * let isObtained = false;
 * sse.onopen= e=>{
 *     if(isObtained){
 *         sse.close();
 *     }
 *     isObtained = true;
 * };
 *  sse.onmessage= e=>console.log('message ',e.data);
 *
 **/