package com.ccz.service.impl;

import com.ccz.dao.TypeRepository;
import com.ccz.po.Type;
import com.ccz.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;


    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transactional
    @Override
    public Page<Type> listTypes(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Type> listTypes() {
        return typeRepository.findAll();
    }

    @Transactional
    @Override
    public Type updateType(Type type) {
        Type one = typeRepository.getOne(type.getId());
        one.setName(type.getName());
        return typeRepository.save(one);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findTypeByName(name);
    }
}
