package com.project.tinyurl.service;

import com.project.tinyurl.domain.TinyUrlResponse;

public interface TinyUrlService {

    TinyUrlResponse addUrl(String url, String client);
    long getClient(String url);

    String retrieveUrl(String tinyurl);
}
