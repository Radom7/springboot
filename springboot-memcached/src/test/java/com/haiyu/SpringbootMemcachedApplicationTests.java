package com.haiyu;

import com.whalin.MemCached.MemCachedClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMemcachedApplicationTests {

	@Autowired
	MemCachedClient memCachedClient;

	@Test
	public void contextLoads() {
		boolean i = memCachedClient.set("id", "123456", 1000);
		System.out.println(String.valueOf(i));
		System.out.println(memCachedClient.get("id"));
		memCachedClient.replace("id","123");
		memCachedClient.replace("ok","123");
		System.out.println(memCachedClient.get("id"));
		memCachedClient.delete("id");
		System.out.println(memCachedClient.get("id"));
		System.out.println(memCachedClient.get("ok"));
	}

}

