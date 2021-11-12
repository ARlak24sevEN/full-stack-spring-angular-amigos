package com.example.fullstackspringangularamigos;

import com.example.fullstackspringangularamigos.enumerarion.Status;
import com.example.fullstackspringangularamigos.model.Server;
import com.example.fullstackspringangularamigos.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FullStackSpringAngularAmigosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackSpringAngularAmigosApplication.class, args);
	}

	@Bean
	CommandLineRunner run (ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null,"192.168.1.160","Ubuntu linux","16 GB",
					"Personal PC","http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.58","Fedora linux","16 GB",
					"Dell Tower","http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null,"192.168.1.21","MS 2008","32 GB",
					"Web Server","http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null,"192.168.1.14","Red Hat Enterprise  linux","64 GB",
					"Mail Server","http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
		};
	}

}
