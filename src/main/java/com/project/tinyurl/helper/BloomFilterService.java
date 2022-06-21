package com.project.tinyurl.helper;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class BloomFilterService {

    private static Logger log = LoggerFactory.getLogger("BloomFilterService");

   private static BloomFilter<Long> filter = BloomFilter.create(
            Funnels.longFunnel(),
            500,
            0.01);



   public void addValue(Long o){
       log.info("Add to Filter = {} " , o);
       filter.put(o);
   }

   public Long contains(Long o, int suffix){
       if(!filter.mightContain(o)){
           addValue(o);
           return o;
       }
       suffix = suffix + 1;
      Long ll = Long.parseLong(String.valueOf(o).concat(String.valueOf(suffix)));
       return contains(ll , suffix);
    }
}
