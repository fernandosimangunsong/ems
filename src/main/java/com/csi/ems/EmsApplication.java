package com.csi.ems;

import com.csi.ems.model.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class EmsApplication{
	public static void main(String[] args) {
		SpringApplication.run(EmsApplication.class, args);
	}


}
