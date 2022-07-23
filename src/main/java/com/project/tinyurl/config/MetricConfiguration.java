package com.project.tinyurl.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfiguration {

/*    @Configuration(proxyBeanMethods = false)
    public class MyMeterRegistryConfiguration {

        @Bean
        public MeterRegistryCustomizer<PrometheusMeterRegistry> prometheusMeterRegistry() {
            return (registry) -> registry.config().namingConvention(this::name);
        }*/

    @Bean(name="tinyUrlMetric")
    public MeterRegistry meterRegistry(){

      return  new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
    }
}
