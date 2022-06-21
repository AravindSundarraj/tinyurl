package com.project.tinyurl.service.impl;

import com.project.tinyurl.helper.BloomFilterService;
import com.project.tinyurl.service.ConversionI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.zip.CRC32;

@Service
public class Hashing implements ConversionI {

    @Autowired
    BloomFilterService bloomFilterService;

    private static Logger log = LoggerFactory.getLogger("Hashing");

    @Override
    public String exec(String url, String client) {

        log.info("trigger hashing for client={} , url={} ", client, url);
        CRC32 crc = new CRC32();
        crc.update(url.getBytes());
        String s = String.valueOf(crc.getValue());
        log.info("Hashed for client={} , url={} , hash={} ", client, url, crc.getValue());
        bloomFilterService.contains(crc.getValue(), 0);
        return String.valueOf(crc.getValue());

    }
}
