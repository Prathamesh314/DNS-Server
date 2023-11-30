package com.kurve.service;

import com.kurve.config.TerminalCommandsGetter;
import com.kurve.dto.NameServerResponse;
import com.kurve.dto.TLDResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NameServerService {

    private final WebClient.Builder webClient;

    public NameServerResponse getResponse(String name) throws Exception {
        TLDResponse response = webClient.build().get().uri("http://TOP-LEVEL-DOMAIN/tld/"+name).retrieve().bodyToMono(TLDResponse.class)
                .block();
        boolean result = response.getValid();
        if(result) {
            Runner runner = new Runner(name);
            runner.run();
            String ipp = runner.getIp();
            return NameServerResponse.builder()
                    .name(name)
                    .ip(ipp)
                    .success("Success")
                    .message("Generated Ip Address successfully for domain name: "+name)
                    .build();
        }
        return NameServerResponse.builder()
                .name(name)
                .ip("")
                .success("Failure")
                .message("Invalid domain name: "+name)
                .build();
    }

    class Runner implements CommandLineRunner{
        private String name;
        private String ipAddress;

        public Runner(String name)
        {
            this.name = name;
        }
        @Override
        public void run(String... args) throws Exception {
            try {
                String command = "nslookup "+this.name;

                ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
                processBuilder.redirectErrorStream(true);

                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                List<String> arr = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    arr.add(line);
                }
                if(arr.size()==1){
                    throw new IOException(arr.get(0));
                }
                else{
                    int index = -1;
                    int i = 0;
                    for(String s:arr)
                    {
                        if(s.startsWith("Addresses")){
                            index=i;
                            break;
                        }
                        i++;
                    }
                    while(!arr.get(index).startsWith("Aliases") && index < arr.size()-1){
//                    System.out.println(arr.get(index));
                        index++;
                    }
                    index--;
//                    System.out.println(arr.get(index).trim());
                    ipAddress = arr.get(index).trim();
                }
                int exitCode = process.waitFor();
                System.out.println("Command exited with code: " + exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        public String getIp(){
            return this.ipAddress;
        }

    }

}