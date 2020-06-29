package com.example.herokudemo.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    private String userId;
    private String oldEmail;
    private String newEmail;
    private int mobile;
    private String email;
    private String hkId;
    private String brand;
    private boolean optOut;
    private String offerId;
    private String type;
}
