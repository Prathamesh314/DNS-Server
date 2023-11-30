package com.kurve.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TerminalCommandsGetter implements CommandLineRunner {
    private String ipAddress;

    private String website;

    @Override
    public void run(String... args) throws Exception {
        try {
            String command = "nslookup "+this.website;

            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            List<String> arr = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                arr.add(line);
            }
            if(arr.size()==1){
                throw new IOException(arr.get(0));
            }
            else{
                String line1 = arr.get(arr.size()-1);
                ipAddress = line1.trim();
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

    public void setweb(String website)
    {
        this.website = website;
    }

}
