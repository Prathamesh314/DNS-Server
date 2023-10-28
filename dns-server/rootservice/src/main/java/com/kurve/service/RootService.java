package com.kurve.service;
import com.kurve.dto.RootServiceRequest;
import com.kurve.dto.RootServiceResponse;
import com.kurve.models.RootServer;
import com.kurve.repository.RootRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RootService {

    private  final RootRepository rootRepository;

    public void createRoot(RootServiceRequest rootServer)
    {
        RootServer rootServer1 = RootServer.builder()
                .name(rootServer.getName())
                .build();

        rootRepository.save(rootServer1);
    }

    public RootServiceResponse getAllNames(String name){
        int n = name.lastIndexOf(".");
        String tld = name.substring(n);
//        int n = arr.length;
        return RootServiceResponse.builder()
                .TopLevelDomain(tld)
                .build();
    }


}
