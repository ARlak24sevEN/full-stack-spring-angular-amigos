package com.example.fullstackspringangularamigos.service.imprementation;

import com.example.fullstackspringangularamigos.enumerarion.Status;
import com.example.fullstackspringangularamigos.model.Server;
import com.example.fullstackspringangularamigos.repo.ServerRepo;
import com.example.fullstackspringangularamigos.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
@RequiredArgsConstructor
@Service
@Transactional
@Log4j2
public class ServerServiceImp implements ServerService {
    private final ServerRepo serverRepo;
//   use  @RequiredArgsConstructor con not add constructor parameter
//    public ServerServiceImp(ServerRepo serverRepo) {
//        this.serverRepo = serverRepo;
//    }

    @Override
    public Server create(Server server) {
        log.info("Saving new server:{}",server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    private String setServerImageUrl() {
        return null;
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server ip:{}",ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000)? Status.SERVER_UP:Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all server");
        return serverRepo.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id : {}",id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Update server:{}",server.getName());
        return serverRepo.save(server);
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete server by id : {}",id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
