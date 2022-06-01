package com.project.tinyurl.controller;

import com.project.tinyurl.domain.TinyUrl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/")
public class TinyurlController {

    @PostMapping(value = "/data/shorten")
    public void addShorten(@RequestBody TinyUrl tinyUrl){
        /*

         */

    }

    @GetMapping(value = "/{shortUrl}")
    public void getShorturl(@PathVariable String shortUrl){

    }
}
