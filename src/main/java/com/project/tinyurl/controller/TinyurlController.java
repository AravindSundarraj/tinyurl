package com.project.tinyurl.controller;

import com.project.tinyurl.domain.TinyUrl;
import com.project.tinyurl.domain.TinyUrlRequest;
import com.project.tinyurl.domain.TinyUrlResponse;
import com.project.tinyurl.service.TinyUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
@RequestMapping(value = "v1/api")
public class TinyurlController {

    @Autowired
    TinyUrlService tinyUrlService;

    private static Logger log = LoggerFactory.getLogger("TinyurlController");
    @PostMapping(value = "/data/shorten")
    public ResponseEntity<TinyUrlResponse> addShorten(@RequestBody TinyUrlRequest tinyUrlRequest){
        log.info("Add-TinyUrl = {} , ClientName = {} ", tinyUrlRequest.getUrl(),tinyUrlRequest.getClientName());
        TinyUrlResponse tinyUrlResponse =  tinyUrlService.addUrl(tinyUrlRequest.getUrl(),tinyUrlRequest.getClientName());
        /*

         */
        log.info(" TinyUrl = {} , ClientName = {} Added Successfully", tinyUrlRequest.getUrl(),tinyUrlRequest.getClientName());
        return new ResponseEntity<>(tinyUrlResponse , HttpStatus.CREATED);

    }

    @GetMapping(value = "/{shortUrl}")
    public void getShorturl(@PathVariable String shortUrl){

    }

    @GetMapping(value = "/{testDirect}")
    public void testRedirect(){
        log.info("Test Status 302 --- ");

    }

/*    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "http://localhost:8085/celebrity/test");
        httpServletResponse.setStatus(302);
    }*/

   /* @RequestMapping(value = "/redirect", method=RequestMethod.POST)
    public RedirectView redirectUrl( HttpServletRequest request, HttpServletResponse response)  {
      //  log.debug("Received shortened url to redirect: " + id);
      //  String redirectUrlString = urlConverterService.getLongURLFromID(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8085/celebrity/test");
        return redirectView;
    }*/

    @GetMapping(value = "/redirect")
    public void handleRedirect(HttpServletResponse response) throws URISyntaxException, IOException {

/*        response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
        response.setHeader("Location", "http://localhost:8085/celebrity/test");

        URI uri = new URI("http://localhost:8085/celebrity/test");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);*/
      //  response.se
        response.setStatus(307);
        response.sendRedirect("https://stackoverflow.com/questions/68050824/rest-post-api-retrieve-result-from-tinyurl-method");
        //return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
    }
}
