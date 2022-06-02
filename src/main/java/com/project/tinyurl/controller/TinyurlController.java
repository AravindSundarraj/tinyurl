package com.project.tinyurl.controller;

import com.project.tinyurl.domain.TinyUrl;
import com.project.tinyurl.domain.TinyUrlRequest;
import com.project.tinyurl.domain.TinyUrlResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
public class TinyurlController {

    @PostMapping(value = "/data/shorten")
    public ResponseEntity<TinyUrlResponse> addShorten(@RequestBody TinyUrlRequest tinyUrlRequest){
        /*

         */
        return null;

    }

    @GetMapping(value = "/{shortUrl}")
    public void getShorturl(@PathVariable String shortUrl){

    }
}
