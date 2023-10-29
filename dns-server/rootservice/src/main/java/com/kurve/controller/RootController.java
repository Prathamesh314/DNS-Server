package com.kurve.controller;

import com.kurve.dto.RootServiceRequest;
import com.kurve.dto.RootServiceResponse;
import com.kurve.service.RootService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/root")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RootController {

    private final RootService rootService;

    @PostMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createroot(@PathVariable String name)
    {
        rootService.createRoot(name);
        return "Domain name registered successfully";
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public RootServiceResponse getTopLevelDomain(@PathVariable String name)
    {
        return rootService.getAllNames(name);
    }

}
