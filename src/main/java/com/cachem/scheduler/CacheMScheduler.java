package com.cachem.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cachem.entity.CacheEntity;
import com.cachem.repository.CacheEntityRepository;

@Component
public class CacheMScheduler {
	
	@Autowired
	CacheEntityRepository cacheEntityRepository;
	
	@Value("${cachem.globalTimeout}")
	private long globalTimeout;
	
	@Value("${cachem.ttl}")
	private long ttl;
	
	@Scheduled(fixedDelay = 60000, initialDelay = 3000)
	public void checkTimeout() {
		LocalDateTime tmpCurrentTime = LocalDateTime.now();
		List<CacheEntity> ces = cacheEntityRepository.findAll();
		ces.stream().forEach(ce -> {
			LocalDateTime tmpCreateTime = ce.getCreatetime();
			long minutesVal = ChronoUnit.MINUTES.between(tmpCreateTime, tmpCurrentTime);
			long milliSecsVal = ChronoUnit.MILLIS.between(tmpCreateTime, tmpCurrentTime);
			if((ttl > 0 && milliSecsVal > ttl) || (globalTimeout > 0 && minutesVal > globalTimeout)) {
				cacheEntityRepository.deleteById(ce.getId());
			}
		});
	}
}
