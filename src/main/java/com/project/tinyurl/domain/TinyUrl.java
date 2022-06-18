package com.project.tinyurl.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
public class TinyUrl {
    private String turl;
    private String url;
    private String client;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private TinyUrl(String turl,String url,String client,String createdBy){
        this.client = client;
        this.turl = turl;
        this.url = url;
        this.createdBy = createdBy;
    }

    public static TinyUrl createTinyObject(String turl,String url,String clientName){

       return new TinyUrl(turl,url,clientName,"ADMIN");
    }


}
