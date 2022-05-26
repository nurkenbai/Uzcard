package com.company.controller;

import com.company.dto.request.ClientRequestDTO;
import com.company.dto.request.TransactionsRequestDTO;
import com.company.service.TransactionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "Transactions")
@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @ApiOperation(value = "Create", notes = "Method create Transactions")
    @PostMapping("/create")
    private ResponseEntity<?> create(@RequestBody TransactionsRequestDTO requestDTO) {
        return ResponseEntity.ok(transactionsService.create(requestDTO));
    }
}
