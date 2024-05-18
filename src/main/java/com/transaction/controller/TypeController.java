package com.transaction.controller;


import com.transaction.models.Type;
import com.transaction.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/type")
public class TypeController {
    
    @Autowired
    TypeService typeService;

    @GetMapping
    public List<Type> getAllTypes(){
        return typeService.getAllTypes();
    }

    @GetMapping("/{typeId}")
    public Optional<Type> getTypeById(@PathVariable Integer typeId){
        return typeService.getTypeById(typeId);
    }

    @PostMapping
    public void createNewType(@RequestParam String type_name,
                                @RequestParam String description){
        Type type = new Type();
        type.setType_name(type_name);
        type.setDescription(description);
        typeService.createNewType(type);
    }

    @PutMapping("/{typeId}")
    public ResponseEntity<Type> updatetype(@PathVariable Integer typeId,
                                               @RequestParam String type_name,
                                               @RequestParam String description){
        try {
            Type updatedtype = typeService.updateType(typeId, type_name, description);
            return ResponseEntity.ok(updatedtype);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity<Void> deletetype(@PathVariable Integer typeId){
        try {
            typeService.deleteType(typeId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
