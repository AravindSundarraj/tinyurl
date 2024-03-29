package com.project.tinyurl.service.impl;

import com.project.tinyurl.dao.ClientDaoI;
import com.project.tinyurl.dao.TinyUrlDaoI;
import com.project.tinyurl.domain.Clients;
import com.project.tinyurl.domain.TinyUrl;
import com.project.tinyurl.domain.TinyUrlResponse;
import com.project.tinyurl.exception.ClientExistException;
import com.project.tinyurl.exception.TinyUrlException;
import com.project.tinyurl.service.ConversionI;
import com.project.tinyurl.service.TinyUrlService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
//import io.micrometer.core.instrument.distribution.Histogram;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.distribution.DistributionStatisticConfig;
import io.micrometer.core.instrument.distribution.Histogram;
import io.prometheus.client.CollectorRegistry;
//import io.prometheus.client.Histogram;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class TinyUrlServiceImpl implements TinyUrlService {

    private static Logger log = LoggerFactory.getLogger("TinyUrlServiceImpl");
    @Autowired
    ClientDaoI clientDaoI;
    @Autowired
    TinyUrlDaoI tinyUrlDaoI;
    @Autowired
    ConversionI conversionI;
    @Value("${default.url}")
    private String defaultUrl;


    private Counter exceptionCounter;

    @Autowired
    MeterRegistry tinyUrlMetric;

    private Map<String,Counter> counters = new HashMap<>();

   // private PrometheusHistogram prometheusHistogram;




/*    public TinyUrlServiceImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        initCounters();
*//*        Gauge.builder("beer.ordersInQueue", orders, Collection::size)
                .description("Number of unserved orders")
                .register(meterRegistry);*//*
    }*/

    @PostConstruct
    private void initCounters() {
        System.out.println("create -- index");
        exceptionCounter = Counter.builder("total.tinyurl.exception.count")
                // 2- create a counter using the fluent API
                .tag("type", "tinyurlException")
                .description("The number of tinyurl-count exception occurred")
                .register(tinyUrlMetric);


    }


    public void increment(String clientName){

        Counter counter = counters.get(clientName);

        if(counter == null) {

            counter = Counter.builder("tinyurl.clients.count").tags("clientName", clientName).register(tinyUrlMetric);

            counters.put(clientName, counter);

        }

        counter.increment();

    }

    @Transactional
    @Override
    public TinyUrlResponse addUrl(String url, String client) {
        //timerCounter.
        String turl = null;
        try {
            turl = conversionI.exec(url, client);
            turl = defaultUrl.concat(turl);
            Clients cls = clientDaoI.getClient(client);
            if (Objects.nonNull(cls)) {
                log.info("client = {} exist update the client  count = {} ", client, cls.getTotalUrls() + 1);
                clientDaoI.updateClient(client, cls.getTotalUrls() + 1);
            } else {
                addNewClient(client);

            }
            log.info("Generated tiny-url = {} for client={} , original-url={}", turl, client, url);
            tinyUrlDaoI.addUrl(TinyUrl.createTinyObject(turl, url, client));
            increment(client);
        }
        catch(Exception ex){
            exceptionCounter.increment();
            log.error("Tinyurl service exception {}" , ex);
            throw ex;
        }
        return TinyUrlResponse.getTinyUrl(turl, url, client);
    }

    private void addNewClient(String client) {
        try {
            clientDaoI.addClient(Clients.createClient(client, 1));
        } catch (Exception ex) {
            log.error("client = {} already exist update the url count", client, ex);
            throw new ClientExistException("Client URL Exist **********");
        }
    }

    @Override
    public long getClient(String client) {
        Clients clt = clientDaoI.getClient(client);
        return Objects.nonNull(clt) ? clt.getClientId() : 0;
    }

    @Override
    public String retrieveUrl(String tinyurl) {
        String tUrl = tinyUrlDaoI.getClientUrl(tinyurl);
        if (Objects.isNull(tUrl)) {
            throw new TinyUrlException(tinyurl.concat("tiny-url does not exist "));
        }
        return tUrl;

    }

}
