package com.example.fullstackspringangularamigos.resource;

import com.example.fullstackspringangularamigos.enumerarion.Status;
import com.example.fullstackspringangularamigos.model.Response;
import com.example.fullstackspringangularamigos.model.Server;
import com.example.fullstackspringangularamigos.service.imprementation.ServerServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private  final ServerServiceImp serverServiceImp;

    @GetMapping("/list")
    public ResponseEntity<Response>getServers(){
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("servers",serverServiceImp.list(30)))
                .message("Servers retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response>pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverServiceImp.ping(ipAddress);
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("server",server))
                .message(server.getStatus() == Status.SERVER_UP ?"Ping Success":"Ping Fail")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @PostMapping("/ping/{ipAddress}")
    public ResponseEntity<Response>saveServer(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("servers",serverServiceImp.create(server)))
                .message("Server created")
                .status(CREATED)
                .statusCode(CREATED.value())
                .build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response>getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("servers",serverServiceImp.get(id)))
                .message("Server retrieved")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response>deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Response.builder()
                .timeStamp(now())
                .data(Map.of("delete",serverServiceImp.delete(id)))
                .message("Server deleted")
                .status(OK)
                .statusCode(OK.value())
                .build());
    }

    @GetMapping(path="/image/{fileName}",produces=IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"Downloads/images/"+fileName));
    }
}
