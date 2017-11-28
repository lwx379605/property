package com.lmc.property.tasks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 李敏成
 *
 */
@Lazy(false)
@Component
public class BaseTask {
	
	 @Scheduled(cron="0 0 0/1 * * ?")//B任务每1小时执行一次进入测试
     public void work() {
         // task execution logic
		 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
         System.out.println(sdf.format(new Date())+"*********B任务每1小时执行一次进入测试");  
     }

}
