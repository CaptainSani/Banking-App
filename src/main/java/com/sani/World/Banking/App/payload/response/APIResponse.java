package com.sani.World.Banking.App.payload.response;

import com.sani.World.Banking.App.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class APIResponse <T>{

    private String data;

    private String message;

    private String responseTime;

    public APIResponse(String data, String message) {
        this.data = data;
        this.message = message;
        this.responseTime = DateUtils.dateToString(LocalDateTime.now());

    }

    public APIResponse(String message) {
        this.message = message;
        this.responseTime= DateUtils.dateToString(LocalDateTime.now());
    }

    public APIResponse(String loginSuccessful, JwtAuthResponse build) {
    }
}
