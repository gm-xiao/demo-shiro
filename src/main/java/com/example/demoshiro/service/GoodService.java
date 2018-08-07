package com.example.demoshiro.service;

import com.example.demoshiro.domain.good.Good;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodService {

    Good save(Good good) throws Exception;

    Good update(Good good) throws Exception;

    void delete(String id);

    List<Good> findAll(Good good);

    Page<Good> findAllPage(Integer page, Integer limit, Good good);

}
