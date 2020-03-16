package com.ccz.service;

import com.ccz.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listTypes(Pageable pageable);

    List<Type> listTypes();

    Type updateType(Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);
}
