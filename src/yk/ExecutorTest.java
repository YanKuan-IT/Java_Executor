package yk;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecutorTest {
	
	private static Integer pages = 1; // ��ҳ��
	private static boolean exeFlag = true; // ִ�б�ʶ
	
	public static void main(String[] args) {
		long one = System.currentTimeMillis();
		//����executorService ���ӳ�Ĭ������10��
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		while(exeFlag){
			if(pages<=1000){
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						System.out.println("��"+pages+"��");
						pages++;
					}
				});
				int activeCount = ((ThreadPoolExecutor)executorService).getActiveCount();
				System.out.println("����̸߳���Ϊ��"+activeCount);
			}else {
				//��̸߳���Ϊ��ʱ
				if( ((ThreadPoolExecutor)executorService).getActiveCount() == 0 ){ 
					//���������߳�
					executorService.shutdown();
					exeFlag = false;
					System.out.println("�������");
				}
			}
			try {
				//��ͣ 0.01s
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long two = System.currentTimeMillis();
		System.out.println("��ʱ"+(two-one)+"ms");
	}
}
