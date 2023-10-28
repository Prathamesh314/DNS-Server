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
public class RootController {

    private final RootService rootService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createroot(@RequestBody RootServiceRequest rootServer)
    {
        rootService.createRoot(rootServer);
        return "Domain name registered successfully";
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<RootServiceResponse> getTopLevelDomain()
    {
        return rootService.getAllNames();
    }

}
