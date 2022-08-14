package com.project.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.export.prometheus.PrometheusMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {PrometheusMetricsExportAutoConfiguration.class})
@SpringBootApplication
public class TinyurlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyurlApplication.class, args);
	}
	


}
