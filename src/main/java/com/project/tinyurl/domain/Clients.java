package com.project.tinyurl.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class Clients {
    private String clientName;
    private int totalUrls;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private long clientId;

    private Clients(String clientName,int totalUrls,String createdBy){
        this.clientName = clientName;
        this.totalUrls = totalUrls;
        this.createdBy = createdBy;
    }

    public static Clients createClient(String clientName,int totalUrls){
        return new Clients(clientName,totalUrls,"Admin");
    }

}
