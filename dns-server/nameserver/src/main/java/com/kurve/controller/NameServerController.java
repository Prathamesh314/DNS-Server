package com.kurve.controller;

import com.kurve.dto.NameServerResponse;
import com.kurve.service.NameServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nameserver")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NameServerController {

    private final NameServerService service;

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public NameServerResponse getResponse(@PathVariable String name){
        return service.getResponse(name);
    }

}