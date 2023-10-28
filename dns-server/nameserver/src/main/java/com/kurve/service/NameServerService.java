package com.kurve.service;

import com.kurve.dto.NameServerResponse;
import com.kurve.dto.TLDResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class NameServerService {

    private final WebClient.Builder webClient;

    public NameServerResponse getResponse(String name){
        Random random = new Random();
        int a = random.nextInt(128);
        int b = random.nextInt(255);
        int c = random.nextInt(255);
        int d = random.nextInt(255);
        String ip = Integer.toString(a) + "." + Integer.toString(b) + "." + Integer.toString(c) + "." + Integer.toString(d);
        TLDResponse response = webClient.build().get().uri("http://TOP-LEVEL-DOMAIN/tld/"+name).retrieve().bodyToMono(TLDResponse.class)
                .block();
        boolean result = response.getValid();
        if(result){
            return NameServerResponse.builder()
                    .name(name)
                    .ip(ip)
                    .build();
        }
        return null;
    }

}