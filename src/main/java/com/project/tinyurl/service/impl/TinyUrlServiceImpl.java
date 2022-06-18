package com.project.tinyurl.service.impl;

import com.project.tinyurl.dao.ClientDaoI;
import com.project.tinyurl.dao.TinyUrlDaoI;
import com.project.tinyurl.domain.Clients;
import com.project.tinyurl.domain.TinyUrl;
import com.project.tinyurl.domain.TinyUrlResponse;
import com.project.tinyurl.service.ConversionI;
import com.project.tinyurl.service.TinyUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {

    private static Logger log = LoggerFactory.getLogger("TinyUrlServiceImpl");
    @Autowired
    ClientDaoI clientDaoI;
    @Autowired
    TinyUrlDaoI tinyUrlDaoI;
    @Autowired
    ConversionI conversionI;

    @Transactional
    @Override
    public TinyUrlResponse addUrl(String url, String client) {
       String turl = conversionI.exec(url,client);
       turl = "http://localhost:8082/v1/api/".concat(turl);

        log.info("Generated tiny-url = {} for client={} , original-url={}",turl , client,url);
/*        long clientId = getClient(client);
        if(Objects.isNull(clientId))*/
        addNewClient(client);
        tinyUrlDaoI.addUrl(TinyUrl.createTinyObject(turl,url,client));

        return TinyUrlResponse.getTinyUrl(turl,url,client);
    }

    private void addNewClient(String client){
        try{
            clientDaoI.addClient(Clients.createClient(client , 1));
        }
        catch(Exception ex){
            log.error("client = {} already exist update the url count",client , ex);
        }
    }

    @Override
    public long getClient(String client) {
        Clients clt = clientDaoI.getClient(client);
        return Objects.nonNull(clt)? clt.getClientId() : 0;
    }

/*    public String getClientUrl(String tinyUrl){
       // clientDaoI.getClient()
    }*/
}
