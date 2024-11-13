package com.bgarage.inventory.controller;

import com.bgarage.inventory.model.PartModel;
import com.bgarage.inventory.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping(value = "/bgarage/parts")
public class PartsController {

    @Autowired
    PartService partService;

    @GetMapping
    public Collection<PartModel> getParts() {
        return partService.getParts();
    }

    @PostMapping
    public ResponseEntity<PartModel> createPart(@RequestBody PartModel partModel) {
        PartModel savedPartModel = partService.createPart(partModel);
        return new ResponseEntity<>(savedPartModel, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PartModel> getPart(@PathVariable String id) {
        Optional<PartModel> part = partService.getPart(id);
        if (part.isPresent()) {
            return new ResponseEntity<>(part.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Object> updatePart(@PathVariable String id, @RequestBody PartModel partModel) {
        try {
            PartModel updatedPartModel = partService.updatePart(id, partModel);
            return new ResponseEntity<>(updatedPartModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Object> deletePart(@PathVariable String id) {
        try {
            partService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
