package com.example.demoshiro.service.impl;

import com.example.demoshiro.dao.GoodDao;
import com.example.demoshiro.domain.good.Good;
import com.example.demoshiro.service.GoodService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodDao goodDao;


    @Override
    public List<Good> findAll(Good good) {

        Specification<Good> specification = new Specification<Good>() {
            @Override
            public Predicate toPredicate(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(good.getName())){
                    predicates.add(cb.equal(root.get("name").as(String.class) ,good.getName() ));
                }
                if (StringUtils.isNotBlank(good.getId())){
                    predicates.add(cb.equal(root.get("id").as(String.class), good.getId()));
                }
                if (StringUtils.isNotBlank(good.getTypeId())){
                    predicates.add(cb.equal(root.get("typeId").as(String.class), good.getTypeId()));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };

        return goodDao.findAll(specification);
    }

    @Override
    public Page<Good> findAllPage(Integer page, Integer limit, Good good) {

        Specification<Good> specification = new Specification<Good>() {
            @Override
            public Predicate toPredicate(Root<Good> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotBlank(good.getName())){
                    predicates.add(cb.like(root.get("name").as(String.class),"%" + good.getName() + "%"));
                }
                if (StringUtils.isNotBlank(good.getId())){
                    predicates.add(cb.equal(root.get("id").as(String.class),good.getId()));
                }
                if (StringUtils.isNotBlank(good.getTypeId())){
                    predicates.add(cb.equal(root.get("typeId").as(String.class),good.getTypeId()));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };

        Pageable pageable = new PageRequest(page - 1 ,limit, Sort.Direction.DESC, "createTime");

        return goodDao.findAll(specification, pageable);
    }
}
