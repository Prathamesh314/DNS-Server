package com.kurve.controller;


import com.kurve.dto.TLDResponse;
import com.kurve.service.TLDService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tld")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TLDController {

    private final TLDService tldService;

    @GetMapping("/{domainname}")
    @ResponseStatus(HttpStatus.OK)
    public TLDResponse checkValidity(@PathVariable String domainname){
        return tldService.getResponse(domainname);
    }

}