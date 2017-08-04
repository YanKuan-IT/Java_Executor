package yk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorTest {
	
	private static Integer pages = 1; // 网页数
	private static boolean exeFlag = true; // 执行标识
	
	public static void main(String[] args) {
		long one = System.currentTimeMillis();
		//创建executorService 连接池默认连接10个
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		while(exeFlag){
			if(pages<=1000){
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						System.out.println("第"+pages+"个");
						pages++;
					}
				});
				int activeCount = ((ThreadPoolExecutor)executorService).getActiveCount();
				System.out.println("活动的线程个数为："+activeCount);
			}else {
				//活动线程个数为零时
				if( ((ThreadPoolExecutor)executorService).getActiveCount() == 0 ){ 
					//结束所有线程
					executorService.shutdown();
					exeFlag = false;
					System.out.println("任务结束");
				}
			}
			try {
				//暂停 0.01s
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long two = System.currentTimeMillis();
		System.out.println("用时"+(two-one)+"ms");
	}
}
