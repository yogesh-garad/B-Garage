package com.bgarage.order.controller;

import com.bgarage.order.model.Supplier;
import com.bgarage.order.model.SupplierOrderConfiguration;
import com.bgarage.order.service.SupplierService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value="/bgarage/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public Collection<Supplier> getParts() {
        return supplierService.getSuppliers();
    }

    @PostMapping
    public ResponseEntity<Supplier> createPart(@RequestBody Supplier supplier) {
        Supplier savedPartModel = supplierService.registerSupplier(supplier);
        return new ResponseEntity<>(savedPartModel, HttpStatus.CREATED);
    }

    @PostMapping(path = "/{supplierId}")
    public ResponseEntity<SupplierOrderConfiguration> createOrderConfiguration(@PathVariable String supplierId, @RequestBody SupplierOrderConfiguration supplier) {
        SupplierOrderConfiguration supplierOrderConfiguration = supplierService.saveSupplierOrderConfiguration(supplier);
        return new ResponseEntity<>(supplierOrderConfiguration, HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{supplierId}")
    public ResponseEntity<SupplierOrderConfiguration> createPart(@PathVariable String supplierId) {
        SupplierOrderConfiguration supplierOrderConfiguration = supplierService.deleteSupplierOrderConfiguration(supplierId);
        return new ResponseEntity<>(supplierOrderConfiguration, HttpStatus.OK);
    }

}
