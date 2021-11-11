package com.example.fullstackspringangularamigos.repo;

import com.example.fullstackspringangularamigos.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server,Long > {
    Server findByIpAddress(String ipAddress);
//    Server findByName(String name);
}
