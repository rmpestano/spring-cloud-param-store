package com.example.demo;

import com.example.demo.model.UserConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("config")
class ConfigController {

    private final UserConfig config;

    @GetMapping
    public Mono<ResponseEntity<UserConfig>> userConfig() {
        return Mono.just(config).map(ResponseEntity::ok);
    }

}
