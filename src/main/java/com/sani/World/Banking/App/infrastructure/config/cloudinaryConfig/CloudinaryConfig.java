package com.sani.World.Banking.App.infrastructure.config.cloudinaryConfig;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    private final String CLOUD_NAME ="dwawllewi";

    private final String API_KEY ="138859868899835";

    private final String API_SECRET="6cpaq4zwfnE1f8J2CA7Rpirh7y0";

    @Bean
    public Cloudinary cloudinary(){

        Map<String, String> config = new HashMap<>();

        config.put("cloud_name", CLOUD_NAME);

        config.put("api_key", API_KEY);

        config.put("api_secret", API_SECRET);

        return new Cloudinary(config);
    }
}
