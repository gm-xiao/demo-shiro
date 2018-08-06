package com.example.demoshiro.service;

import com.example.demoshiro.domain.good.Good;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodService {

    List<Good> findAll(Good good);

    Page<Good> findAllPage(Integer page, Integer limit, Good good);

}
