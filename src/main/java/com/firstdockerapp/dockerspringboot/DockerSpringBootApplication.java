package com.firstdockerapp.dockerspringboot;

import java.io.File;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DockerSpringBootApplication {

	public static void main(String[] args) {
		System.setProperty("ID", UUID.randomUUID().toString());
		File dataDirectory = new File("/data");
        if(!dataDirectory.exists() || !dataDirectory.isDirectory()) {
			System.out.println("'/data' directory does not exist");
			try{
				if(!dataDirectory.mkdir()) {
					System.out.println("Unable to create 'data' directory");
				}
			}
			catch(Exception e) {
				System.out.println("Unable to create 'data' directory");
				e.printStackTrace();
			}
			System.out.println("Data directory created");
        }
		SpringApplication.run(DockerSpringBootApplication.class, args);
	}
}
