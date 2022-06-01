package com.company.controller;

import com.company.dto.request.CardAssignRequestDTO;
import com.company.dto.request.CardFilterRequestDTO;
import com.company.dto.request.CardRequestDTO;
import com.company.dto.request.CardStatusDTO;
import com.company.dto.response.CardResponseDTO;
import com.company.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "Card")
@RestController
@RequestMapping("/api/v1/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Create", notes = "Method used for create card")
    @PreAuthorize("hasRole('BANK')")
    @PostMapping("")
    public ResponseEntity<CardResponseDTO> create(@RequestBody @Valid CardRequestDTO requestDTO) {
        log.info("Create: {}", requestDTO);
        return ResponseEntity.ok(cardService.create(requestDTO));
    }

    @ApiOperation(value = "List By Filter", notes = "Method used for get list by filter")
    @PreAuthorize("hasRole('BANK')")
    @PostMapping("/filter")
    public ResponseEntity<?> filter(@RequestBody CardFilterRequestDTO requestDTO) {
        log.info("Filter: {}", requestDTO);
        return ResponseEntity.ok(cardService.filter(requestDTO));
    }

    @ApiOperation(value = "Get by id", notes = "Method get By id")
    @PreAuthorize("hasRole('BANK')")
    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> getById(@PathVariable("id") String id) {
        log.info("get BY id: {}", id);
        return ResponseEntity.ok(cardService.getById(id));
    }

    @ApiOperation(value = "Get", notes = "Method used for get card")
    @PreAuthorize("hasAnyRole('BANK','PROFILE','ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<CardResponseDTO>> getAll() {
        log.info("getAll: {}", CardController.class);
        return ResponseEntity.ok(cardService.getAll());
    }

    @ApiOperation(value = "Get by Card number", notes = "Method get By Card number")
    @GetMapping("/card-number/{cardNumber}")
    public ResponseEntity<CardResponseDTO> getByCardNumber(@PathVariable("cardNumber") String cardNumber) {
        log.info("Get By Card Number: {}", cardNumber);
        return ResponseEntity.ok(cardService.getByCardNumber(cardNumber));
    }

    @ApiOperation(value = "List By Client", notes = "Method used for get list by client")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CardResponseDTO>> getByClientId(@PathVariable("clientId") String clientId) {
        log.info("Get By Client Id: {}", clientId);
        return ResponseEntity.ok(cardService.getByClientId(clientId));
    }

    @ApiOperation(value = "List By Phone", notes = "Method used for get list by phone")
    @PreAuthorize("hasAnyRole('BANK','PROFILE','ADMIN')")
    @GetMapping("/list/{phone}")
    public ResponseEntity<List<CardResponseDTO>> getByPhoneId(@PathVariable("phone") String phone) {
        log.info("Get By Phone Id: {}", phone);
        return ResponseEntity.ok(cardService.getByPhoneId(phone));
    }

    @ApiOperation(value = "Balance", notes = "Method used for get balance from card")
    @PreAuthorize("hasAnyRole('BANK','PROFILE','ADMIN')")
    @GetMapping("/balance/{number}")
    public ResponseEntity<Long> getBalance(@PathVariable("number") String number) {
        log.info("Get balance By number: {}", number);
        return ResponseEntity.ok(cardService.getBalance(number));
    }


    @ApiOperation(value = "Assign Phone", notes = "Method used for assign phone")
    @PreAuthorize("hasRole('BANK')")
    @PutMapping("/phone/{Cardid}")
    public ResponseEntity<Boolean> assignPhone(@PathVariable("id") String id, @RequestBody @Valid CardAssignRequestDTO requestDTO) {
        log.info("Assign Phone: {}", id);
        return ResponseEntity.ok(cardService.assignPhone(requestDTO.getPhone(), id));
    }


    @ApiOperation(value = "Update Status", notes = "Method used for update status")
    @PreAuthorize("hasAnyRole('BANK', 'PROFILE')")
    @PutMapping("/status/{id}")
    public ResponseEntity<Boolean> updateStatus(@RequestBody @Valid CardStatusDTO dto,
                                          @PathVariable("id") String id) {
        log.info("Status: {}",dto.getStatus());
        return ResponseEntity.ok(cardService.chengStatus(dto.getStatus(),id));
    }

}
