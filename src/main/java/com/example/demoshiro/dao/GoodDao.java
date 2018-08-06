package com.example.demoshiro.dao;

import com.example.demoshiro.domain.good.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodDao extends JpaRepository<Good,String> {

    List<Good> findAll(Specification<Good> spec);

    Page<Good> findAll(Specification<Good> spec, Pageable pageable);

}
