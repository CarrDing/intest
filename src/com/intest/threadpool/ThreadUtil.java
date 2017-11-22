package com.intest.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {

	public void read(Integer num, Runnable runnable) {
		ExecutorService executorService = Executors.newFixedThreadPool(num);  
		for(int i = 0; i < num; i++) {
			executorService.execute(runnable);  
		}
		//executorService.shutdown();  
	}
}
