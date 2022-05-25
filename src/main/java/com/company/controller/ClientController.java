package com.company.controller;

import com.company.dto.request.ClientRequestDTO;
import com.company.enums.StatusEnum;
import com.company.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "Client")
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @ApiOperation(value = "Create", notes = "Method create Client")
    @PostMapping("/create")
    private ResponseEntity<?> create(@RequestBody ClientRequestDTO requestDTO) {
        return ResponseEntity.ok(clientService.create(requestDTO));
    }

    @ApiOperation(value = "Get by id", notes = "Method get By id")
    @GetMapping("/getBy/{id}")
    private ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @ApiOperation(value = "Get All", notes = "Method get All")
    @GetMapping("/getBy/{id}")
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @ApiOperation(value = "Cheng Status", notes = "Method Cheng Status by id Active")
    @PutMapping("/chengStatus/{id}/block")
    private ResponseEntity<?> chengStatusActive(@PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.chengStatus(StatusEnum.ACTIVE, id));
    }

    @ApiOperation(value = "Cheng Status", notes = "Method Cheng Status by id block")
    @PutMapping("/chengStatus/{id}/block")
    private ResponseEntity<?> chengStatusBlock(@PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.chengStatus(StatusEnum.BLOCK, id));
    }

    @ApiOperation(value = "Cheng Status", notes = "Method Cheng Status by id Not active")
    @PutMapping("/chengStatus/{id}/block")
    private ResponseEntity<?> chengStatusNotActive(@PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.chengStatus(StatusEnum.NOT_ACTIVE, id));
    }
}
