package demo.democache;

import java.util.HashMap;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class TestService {

	public static HashMap<String,String> dataHashMap = new HashMap<String, String>();
	
	@Cacheable("numberList")
	public HashMap<String,String> findList() {
		
		dataHashMap.put("1", "one");
		dataHashMap.put("2", "two");
		dataHashMap.put("3", "three");
		dataHashMap.put("4", "four");
		
		HashMap<String,String> resultHashMap = new HashMap<String, String>();
		
		resultHashMap.putAll(dataHashMap);
		
		try  {
			System.out.println("sleep......");
			Thread.sleep(2000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultHashMap;
	}
	
	
	public void addData() {
		dataHashMap.put("5", "five");
		dataHashMap.put("6", "six");
	}
	
}
