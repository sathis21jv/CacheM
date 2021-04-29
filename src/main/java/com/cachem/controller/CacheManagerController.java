package com.cachem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cachem.entity.CacheEntity;
import com.cachem.service.CacheManagerService;

@RestController
@RequestMapping("/cachem")
public class CacheManagerController {
	
	@Autowired
	CacheManagerService cacheManagerService;
	
	@GetMapping(path="getCache/{name}")
	public ResponseEntity<String> getCachedData(@PathVariable("name") String name) {
		CacheEntity ce = cacheManagerService.getCacheEntity(name);
		if(ce.getDataval() != null)
			return new ResponseEntity<String>(ce.getDataval(), new HttpHeaders(), HttpStatus.OK);
		else if(ce.getDatavalbin() != null)
			return new ResponseEntity<String>(ce.getDatavalbin()+"", new HttpHeaders(), HttpStatus.OK);
		else if(ce.getDatavaljson() != null)
			return new ResponseEntity<String>(ce.getDatavaljson()+"", new HttpHeaders(), HttpStatus.OK);
		else
			return new ResponseEntity<String>("", new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping(path="createCache")
	public void createCacheData(@RequestBody CacheEntity ce) throws Exception {
		cacheManagerService.createCacheEntity(ce);
	}
	
	@DeleteMapping(path="deleteCache/{name}")
	public void deleteCachedData(@PathVariable("name") String name) {
		cacheManagerService.deleteCacheEntity(name);
	}
	
}
