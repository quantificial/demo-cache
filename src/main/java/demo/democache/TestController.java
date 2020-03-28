package demo.democache;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Autowired
	TestService testService;
	
	@Autowired
	CacheManager cacheManager;
	
	@GetMapping("/findData")
	public HashMap<String, String> findData() {
		
		return testService.findList();
		
	}
	
	@GetMapping("/addData")
	public String addData() {
		this.testService.addData();
		
		return "added";
	}
	
	/**
	 * clear the cache "numberList" and all entries
	 * @return
	 */
	@GetMapping("/clearCache")
	@CacheEvict(value="numberList", allEntries=true)
	public String clearCache() {
		//cacheManager.getCache("numberList").clear();
		return "clear";
	}
	
	
	/**
	 * clear all the cache
	 */
	public void evictAllCaches() {
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	/**
	 * clear the cache every "fixedRate" period
	 */
	@Scheduled(fixedRate = 6000)
	public void evictAllcachesAtIntervals() {
		System.out.println("schedule clear cache");
	    evictAllCaches();
	}

}
