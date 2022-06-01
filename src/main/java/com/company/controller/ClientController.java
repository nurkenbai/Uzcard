package com.company.controller;

import com.company.dto.request.ClientChangePhoneRequestDTO;
import com.company.dto.request.ClientRequestDTO;
import com.company.dto.request.ClientStatusDTO;
import com.company.dto.response.ClientResponseDTO;
import com.company.enums.StatusEnum;
import com.company.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@Api(tags = "Client")
@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
public class ClientController {

    public final ClientService clientService;

    @PreAuthorize("hasRole('BANK')")
    @ApiOperation(value = "Create", notes = "Method used for create client")
    @PostMapping("")
    public ResponseEntity<ClientResponseDTO> create(@RequestBody @Valid ClientRequestDTO requestDTO) {
        log.info("Create: {}", requestDTO);
        return ResponseEntity.ok(clientService.create(requestDTO));
    }

    @ApiOperation(value = "Get", notes = "Method used for get client")
    @PreAuthorize("hasAnyRole('BANK','PROFILE')")
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable("clientId") String clientId) {
        log.info("Get by id: {}", clientId);
        return ResponseEntity.ok(clientService.getById(clientId));
    }

    @ApiOperation(value = "Get All", notes = "Method get All")
    @GetMapping("")
    public ResponseEntity<List<ClientResponseDTO>> getAll() {
        log.info("Get All: {}", CardController.class);
        return ResponseEntity.ok(clientService.getAll());
    }

    @ApiOperation(value = "List", notes = "Method used for get list")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<PageImpl<ClientResponseDTO>> pagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        log.info("pagination: {}", CardController.class);
        return ResponseEntity.ok(clientService.pagination(page, size));
    }

    @ApiOperation(value = "Update Phone", notes = "Method used for update phone")
    @PreAuthorize("hasRole('BANK')")
    @PutMapping("/phone/{clientId}")
    public ResponseEntity<Boolean> changePhone(@RequestBody @Valid ClientChangePhoneRequestDTO requestDTO,
                                               @PathVariable("clientId") String clientId) {
        log.info("changePhone: {},{}", CardController.class, requestDTO);
        return ResponseEntity.ok(clientService.changePhone(requestDTO, clientId));
    }

    @ApiOperation(value = "Update", notes = "Method used for update")
    @PreAuthorize("hasRole('BANK')")
    @PutMapping("/update/{сlientId}")
    public ResponseEntity<Boolean> update(@RequestBody ClientRequestDTO requestDTO, @PathVariable("сlientId") String сlientId) {
        log.info("Update: {},{}", CardController.class, requestDTO);
        return ResponseEntity.ok(clientService.update(requestDTO, сlientId));
    }

    @ApiOperation(value = "Update Status", notes = "Method used for update status")
    @PreAuthorize("hasRole('BANK')")
    @PutMapping("/status/{clientId}")
    public ResponseEntity<Boolean> updateStatus(@RequestBody @Valid ClientStatusDTO dto,
                                                @PathVariable("clientId") String clientId) {
        log.info("Status: {}", dto.getStatus());
        return ResponseEntity.ok(clientService.changeStatus(dto.getStatus(), clientId));
    }

    @ApiOperation(value = "List By Profile", notes = "Method used for list by profile")
    @PreAuthorize("hasAnyRole('ADMIN','PROFILE')")
    @GetMapping("/list/{profileName}")
    public ResponseEntity<PageImpl<ClientResponseDTO>> paginationListByProfileName(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                                   @RequestParam(value = "size", defaultValue = "5") int size,
                                                                                   @PathVariable("profileName") String profileName,
                                                                                   Principal principal) {
        log.info("/adm/list/{profileName}");
        if (principal.getName().equals("profile")) {
            return ResponseEntity.ok(clientService.paginationListByProfileName(page, size, principal.getName()));
        }
        return ResponseEntity.ok(clientService.paginationListByProfileName(page, size, profileName));
    }


}
