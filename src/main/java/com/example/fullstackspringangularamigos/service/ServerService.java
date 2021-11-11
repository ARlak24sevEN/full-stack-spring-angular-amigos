package com.example.fullstackspringangularamigos.service;

import com.example.fullstackspringangularamigos.model.Server;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {
    Server create(Server server);
    Server ping (String ipAddress) throws IOException;
    Collection<Server>list(int limit);
    Server get(Long id);
    Server update(Server server);
    boolean delete(Long id);
}
