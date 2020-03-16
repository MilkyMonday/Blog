package com.ccz.dao;

import com.ccz.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findTypeByName(String name);
}
