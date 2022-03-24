package com.web.wam.model.repository.wine;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.wam.model.entity.Wine;

public interface WineRepository extends JpaRepository<Wine, Integer> {

}
