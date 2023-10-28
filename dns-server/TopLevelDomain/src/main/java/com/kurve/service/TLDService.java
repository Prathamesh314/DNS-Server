package com.kurve.service;

import com.kurve.dto.RootServiceResponse;
import com.kurve.dto.TLDResponse;
import com.kurve.repository.TLDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TLDService {

    private final TLDRepository tldRepository;


    private final WebClient.Builder webClient;
    public List<String> getDomains()
    {
        List<java.lang.String> tlds = new ArrayList<>();
        tlds.add(".com");
        tlds.add(".in");
        tlds.add(".org");
        tlds.add(".net");
        tlds.add(".gov");
        tlds.add(".edu");
        tlds.add(".mil");
        tlds.add(".int");
        tlds.add(".aero");
        tlds.add(".coop");
        tlds.add(".museum");
        tlds.add(".name");
        tlds.add(".pro");
        tlds.add(".biz");
        tlds.add(".info");
        tlds.add(".mobi");
        tlds.add(".tel");
        tlds.add(".asia");
        tlds.add(".cat");
        tlds.add(".jobs");
        tlds.add(".travel");
        tlds.add(".post");
        tlds.add(".xxx");
        tlds.add(".eu");
        tlds.add(".us");
        tlds.add(".uk");
        tlds.add(".ca");
        tlds.add(".au");
        tlds.add(".jp");
        tlds.add(".cn");
        tlds.add(".de");
        tlds.add(".fr");
        tlds.add(".it");
        tlds.add(".ru");
        tlds.add(".br");
        tlds.add(".sa");
        tlds.add(".za");
        tlds.add(".ae");
        tlds.add(".io");
        return tlds;
    }

    public Boolean checkValidity(String name){
        RootServiceResponse response = webClient.build().get().uri("http://ROOT-LEVEL/root/"+name).retrieve()
                .bodyToMono(RootServiceResponse.class).block();
        List<String> domains = getDomains();
        for(String k: domains){
            if(k.equalsIgnoreCase(response.getTopLevelDomain())){
                return true;
            }
        }
        return false;
    }

    public TLDResponse getResponse(String name){
        Boolean val = checkValidity(name);
        return TLDResponse.builder()
                .name(name)
                .valid(val)
                .build();
    }
}
