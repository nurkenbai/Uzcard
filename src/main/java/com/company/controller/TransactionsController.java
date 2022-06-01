package com.company.controller;

import com.company.dto.request.CardFilterRequestDTO;
import com.company.dto.request.TransactionsFilterRequestDTO;
import com.company.dto.request.TransactionsRequestDTO;
import com.company.dto.response.TransactionsResponseDTO;
import com.company.service.TransactionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Transactions")
@Slf4j
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @ApiOperation(value = "Create", notes = "Method used for create transaction")
    @PreAuthorize("hasRole('BANK')")
    @PostMapping("")
    public ResponseEntity<TransactionsResponseDTO> create(@RequestBody @Valid TransactionsRequestDTO requestDTO) {
        log.info("Create: {},{}", TransactionsController.class, requestDTO);
        return ResponseEntity.ok(transactionsService.create(requestDTO));
    }

    @ApiOperation(value = "List By Card", notes = "Method used for get pagination list by card id")
    @GetMapping("/cardId/{cardId}")
    @PreAuthorize("hasRole('BANK')")
    public ResponseEntity<PageImpl<TransactionsResponseDTO>> getByCardId(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "5") int size,
                                                @PathVariable("cardId") String cardId) {
        log.info("Get By Card Id: {},{}", TransactionsController.class, cardId);
        return ResponseEntity.ok(transactionsService.getByCardIdAndPagination(page, size, cardId));
    }

    @ApiOperation(value = "List By Client", notes = "Method used for get pagination list by client id")
    @GetMapping("/clientId/{clientId}")
    @PreAuthorize("hasRole('BANK')")
    public ResponseEntity<PageImpl<TransactionsResponseDTO>> getByClientId(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "size", defaultValue = "5") int size,
                                           @PathVariable("clientId") String cardId) {
        log.info("Get By Client Id: {},{}", TransactionsController.class, cardId);
        return ResponseEntity.ok(transactionsService.getByClientId(page, size, cardId));
    }

    @ApiOperation(value = "List By Profile Name From Transaction",
            notes = "Method used for get pagination list by profile name from transaction")
    @GetMapping("/profileName/{profileName}")
    @PreAuthorize("hasRole('BANK')")
    public ResponseEntity<PageImpl<TransactionsResponseDTO>> getByProfileName(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "5") int size,
                                              @PathVariable("profileName") String profileName) {
        log.info("Get By Profile Name Id: {},{}", TransactionsController.class, profileName);
        return ResponseEntity.ok(transactionsService.getByProfileName(page, size, profileName));
    }

    @ApiOperation(value = "List By Phone", notes = "Method used for get pagination list by phone number")
    @GetMapping("/phone/{phone}")
    @PreAuthorize("hasRole('BANK')")
    public ResponseEntity<PageImpl<TransactionsResponseDTO>> getByPhone(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size,
                                        @PathVariable("phone") String cardId) {
        log.info("Get By Phone Id: {},{}", TransactionsController.class, cardId);
        return ResponseEntity.ok(transactionsService.getByPhone(page, size, cardId));
    }

    @ApiOperation(value = "List By Filter", notes = "Method used for get list by filter")
    @PreAuthorize("hasRole('BANK')")
    @PostMapping("/filter")
    public ResponseEntity<List<TransactionsResponseDTO>> filter(@RequestBody TransactionsFilterRequestDTO requestDTO) {
        log.info("Filter: {},{}", TransactionsController.class, requestDTO);
        return ResponseEntity.ok(transactionsService.filter(requestDTO));
    }


}
