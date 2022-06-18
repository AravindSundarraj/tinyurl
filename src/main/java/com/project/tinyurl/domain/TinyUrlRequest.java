package com.project.tinyurl.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TinyUrlRequest {

    String clientName;
    String url;
}
