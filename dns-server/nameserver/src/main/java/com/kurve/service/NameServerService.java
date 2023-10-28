package com.kurve.service;

import com.kurve.dto.NameServerResponse;
import com.kurve.repository.NameServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class NameServerService {

    private final NameServerRepository repository;

    public NameServerResponse getResponse(String name){
        Random random = new Random();
        int a = random.nextInt(255);
        int b = random.nextInt(255);
        int c = random.nextInt(255);
        int d = random.nextInt(255);
        String ip = Integer.toString(a) + "." + Integer.toString(b) + "." + Integer.toString(c) + "." + Integer.toString(d);
        return NameServerResponse.builder()
                .name(name)
                .ip(ip)
                .build();
    }

}