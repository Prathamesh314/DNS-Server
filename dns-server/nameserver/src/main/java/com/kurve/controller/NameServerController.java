package com.kurve.controller;

import com.kurve.dto.NameServerResponse;
import com.kurve.exception.ApiResponse;
import com.kurve.redisconfig.RateLimitConfig;
import com.kurve.service.NameServerService;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nameserver")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NameServerController {

    private final NameServerService service;
    @Autowired
    RateLimitConfig rateLimitConfig;


    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getResponse(@PathVariable String name){
        Bucket bucket = rateLimitConfig.resolveBucket(name);
        if(bucket.tryConsume(1)){
            NameServerResponse response = service.getResponse(name);
            return new ResponseEntity<NameServerResponse>(response, HttpStatus.OK);
        }
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Too many Api Requests !!, This might be DoS attack so we are blocking your requests")
                .response(429)
                .status(false)
                .build();
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);

    }

}