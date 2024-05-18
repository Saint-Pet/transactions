package com.transaction.service;


import com.transaction.models.Type;
import com.transaction.models.Type;
import com.transaction.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository typeRepository;
    
    public List<Type> getAllTypes(){
        return typeRepository.findAll();
    }
    
    public Optional<Type> getTypeById(Integer id) {
        return typeRepository.findById(id);
    }
    
    public void createNewType(Type type) {
        typeRepository.save(type);
    }
    
    public Type updateType(Integer typeId, String type_name, String description) {
        Optional<Type> type = typeRepository.findById(typeId);
        if (type.isPresent()){
            Type updatedType = type.get();
            updatedType.setType_name(type_name);
            updatedType.setDescription(description);
            return updatedType;
        } else {
            throw new IllegalArgumentException("Invalid Type_id");}
    }

    public void deleteType(Integer typeId) {
        typeRepository.deleteById(typeId);
    }
}
