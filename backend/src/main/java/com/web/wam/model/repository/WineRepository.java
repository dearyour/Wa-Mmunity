package com.web.wam.model.repository;

import com.web.wam.model.entity.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Integer> {

}
