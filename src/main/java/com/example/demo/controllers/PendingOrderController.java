package com.example.demo.controllers;

import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.services.PendingOrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/pending-orders")
public class PendingOrderController {

    private final PendingOrderService pendingOrderService;

    public PendingOrderController(PendingOrderService pendingOrderService) {
        this.pendingOrderService = pendingOrderService;
    }

    @PostMapping
    public ResponseEntity addPendingOrder(@RequestBody PendingOrderRequestModel pendingOrder, Principal principal) {
        return ResponseEntity.status(CREATED).body(pendingOrderService.addPendingOrder(pendingOrder, principal.getName()));
    }

    @DeleteMapping("/customer/{uuid}")
    public ResponseEntity deletePendingOrder(@PathVariable String uuid, Principal principal){
        System.out.println(principal.getName());
        pendingOrderService.deletePendingOrder(uuid, principal.getName());
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/customer/{uuid}")
    public ResponseEntity<PendingOrderResponseModel> getPendingOrder(@PathVariable String uuid, Principal principal){
        return ResponseEntity.ok(pendingOrderService.getPendingOrderByUuid(uuid, principal.getName()));
    }

}
