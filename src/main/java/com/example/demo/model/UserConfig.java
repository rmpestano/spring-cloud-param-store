package com.example.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.List;

@ConstructorBinding
@ConfigurationProperties(prefix = "user.config")
public record UserConfig (List<User> users) {

    public record User (String name, String pass, List<Role> roles) {}

    public record Role (String name){}
}
