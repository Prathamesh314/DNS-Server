package com.kurve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NameServerResponse {

    private String name;
    private String ip;
    private String success;
    private String message;
}
