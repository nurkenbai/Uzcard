package com.company.controller;

import com.company.repository.custom.KillerDataBaseCoustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ciller")
public class KillerDataBaseController {
    @Autowired
    private KillerDataBaseCoustomRepository killerDataBaseCoustomRepository;

    @GetMapping("/{tableName}")
    @PreAuthorize("hasRole('ADMINGENERAL')")
    private ResponseEntity<?> drop(@PathVariable("tableName") Object  tableName) {
        return ResponseEntity.ok(killerDataBaseCoustomRepository.drop(tableName));
    }

}
