package com.bgarage.inventory.controller;

import com.bgarage.inventory.model.Part;
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
    public Collection<Part> getParts() {
        return partService.getParts();
    }

    @PostMapping
    public ResponseEntity<Part> createPart(@RequestBody Part part) {
        Part savedPart = partService.createPart(part);
        return new ResponseEntity<>(savedPart, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Part> getPart(@PathVariable String id) {
        Optional<Part> part = partService.getPart(id);
        if (part.isPresent()) {
            return new ResponseEntity<>(part.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Object> updatePart(@PathVariable String id, @RequestBody Part part) {
        try {
            Part updatedPart = partService.updatePart(id, part);
            return new ResponseEntity<>(updatedPart, HttpStatus.OK);
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
