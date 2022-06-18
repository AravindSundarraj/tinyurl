package com.project.tinyurl.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TinyUrlResponse {
    private String tinyurl;
    private String url;
    private String clientName;

    private TinyUrlResponse(String tinyurl, String url,String clientName){
        this.tinyurl = tinyurl;
        this.url = url;
        this.clientName = clientName;
    }

    public  static TinyUrlResponse getTinyUrl(String tinyurl, String clientId,String clientName){
        return new TinyUrlResponse(tinyurl , clientId,clientName);
    }
}
