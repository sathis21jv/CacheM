package com.cachem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cachem.entity.CacheEntity;

@Repository
public interface CacheEntityRepository extends JpaRepository<CacheEntity, String>{

}
