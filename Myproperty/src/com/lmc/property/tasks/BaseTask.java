package com.lmc.property.tasks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Lazy(false)
@Component
public class BaseTask {
	
	 @Scheduled(cron="0/5 * *  * * ? ")//ÿ5��ִ��һ�� 
     public void work() {
         // task execution logic
		 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
         System.out.println(sdf.format(new Date())+"*********B����ÿ5��ִ��һ�ν������");  
     }

}
