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
//    private final TerminalCommandsGetter getter;

    public NameServerResponse getResponse(String name) throws Exception {
        Random random = new Random();
        int a = random.nextInt(128);
        int b = random.nextInt(255);
        int c = random.nextInt(255);
        int d = random.nextInt(255);
        String ip = Integer.toString(a) + "." + Integer.toString(b) + "." + Integer.toString(c) + "." + Integer.toString(d);
        TLDResponse response = webClient.build().get().uri("http://TOP-LEVEL-DOMAIN/tld/"+name).retrieve().bodyToMono(TLDResponse.class)
                .block();
        boolean result = response.getValid();
        if(result) {
            Runner runner = new Runner(name);
            runner.run();
//            getter.setweb(name);
//            getter.run("Run");
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
                String command = "ping "+this.name;

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
                    for(String s:arr){
                        System.out.println(s);
                    }
                    String line1 = arr.get(1);
                    int ind1 = 0;
                    int ind2 = 0;
                    for(int i=0;i<line1.length();i++) {
                        if (line1.charAt(i) == '[') {
                            ind1 = i;
                        }
                        if(line1.charAt(i) == ']'){
                            ind2 = i;
                            break;
                        }
                    }
                    ipAddress = line1.substring(ind1+1, ind2);
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