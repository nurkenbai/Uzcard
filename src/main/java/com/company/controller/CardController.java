package com.company.controller;

import com.company.dto.request.CardRequestDTO;
import com.company.enums.StatusEnum;
import com.company.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "Card")
@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @ApiOperation(value = "Create ", notes = "Method Create Card")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardRequestDTO requestDTO){
        return ResponseEntity.ok(cardService.create(requestDTO));
    }
    @ApiOperation(value = "Get by id", notes = "Method get By id")
    @GetMapping("/getBy/{id}")
    private ResponseEntity<?> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(cardService.getById(id));
    }

    @ApiOperation(value = "Get All", notes = "Method get All")
    @GetMapping("/getAll")
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(cardService.getAll());
    }

    @ApiOperation(value = "Cheng Status", notes = "Method Cheng Status by id Active")
    @PutMapping("/chengStatus/{id}/Active")
    private ResponseEntity<?> chengStatusActive(@PathVariable("id") String id) {
        return ResponseEntity.ok(cardService.chengStatus(StatusEnum.ACTIVE, id));
    }

    @ApiOperation(value = "Cheng Status", notes = "Method Cheng Status by id block")
    @PutMapping("/chengStatus/{id}/block")
    private ResponseEntity<?> chengStatusBlock(@PathVariable("id") String id) {
        return ResponseEntity.ok(cardService.chengStatus(StatusEnum.BLOCK, id));
    }

    @ApiOperation(value = "Cheng Status", notes = "Method Cheng Status by id Not active")
    @PutMapping("/chengStatus/{id}/notactive")
    private ResponseEntity<?> chengStatusNotActive(@PathVariable("id") String id) {
        return ResponseEntity.ok(cardService.chengStatus(StatusEnum.NOT_ACTIVE, id));
    }
}
