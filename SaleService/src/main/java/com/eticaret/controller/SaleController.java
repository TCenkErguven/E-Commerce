package com.eticaret.controller;

import com.eticaret.dto.request.TransactionRequestDto;
import com.eticaret.repository.entity.Sale;
import com.eticaret.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;

    @PostMapping("/transaction")
    public ResponseEntity<Boolean> transaction(@RequestBody TransactionRequestDto dto){
        return ResponseEntity.ok(service.transaction(dto));
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Sale>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
