package com.company.controller;

import com.company.dto.request.TransactionsRequestDTO;
import com.company.service.TransactionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Transactions")
@RestController
@RequestMapping("/v1/transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @ApiOperation(value = "Create", notes = "Method create Transactions")
    @PreAuthorize("hasRole('BANK')")
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody TransactionsRequestDTO requestDTO) {
        return ResponseEntity.ok(transactionsService.create(requestDTO));
    }

    @ApiOperation(value = "get", notes = "Method create Transactions")
    @GetMapping("/cardId/{cardId}")
    @PreAuthorize("hasRole('BANK')")
    public ResponseEntity<?> getByCardId(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "5") int size,
                                          @PathVariable("cardId") String cardId) {
        return ResponseEntity.ok(transactionsService.getByCardIdAndPagination(page, size, cardId));
    }
}
