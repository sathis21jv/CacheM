package com.cachem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cachem.entity.CacheEntity;
import com.cachem.repository.CacheEntityRepository;

@Service
public class CacheManagerService {

	@Autowired
	CacheEntityRepository cacheEntityRepository;
	
	@Value("${cachem.maxEntries}")
	private int maxEntries;
	
	@Transactional
	public void createCacheEntity(CacheEntity ce) throws Exception {
		List<CacheEntity> ces = cacheEntityRepository.findAll();
		if(ces.size() >= maxEntries) {
			throw new Exception("Cache Max Entry Reached");
		}
		ce.setCreatetime(LocalDateTime.now());
		if(ce.getDatavaljson() != "") {
			cacheEntityRepository.save(ce);
		}
		else {
			ce.setDatavaljson(null);
			cacheEntityRepository.save(ce);
		}
	}
	
	
	public CacheEntity getCacheEntity(String name) {
		Optional<CacheEntity> ceOpt = cacheEntityRepository.findById(name);
		if(ceOpt.isPresent()) {
			return ceOpt.get();
		} else {
			return new CacheEntity();
		}
	}
	
	@Transactional
	public void deleteCacheEntity(String name) {
		Optional<CacheEntity> ceOpt = cacheEntityRepository.findById(name);
		if(ceOpt.isPresent()) {
			cacheEntityRepository.delete(ceOpt.get());
		}
	}
}
