/**
 * BotSturdy
 * CronTest.java
 * 3574
 * 2016. 9. 8.
 */
package EarthQuake;

import java.util.ArrayList;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author 3574
 *
 */
public class ScheduleController {
	public ScheduleController() {
		try {
			ArrayList state = new ArrayList<String>();
			//주기적으로 실행시클 job 클래스 등록
			//job 클래스는 생성자에 인자가 들어가면 안됨, inner class로 생성하면 안됨.
			//job 클래스에 데이터 전송
			JobDetail job = JobBuilder.newJob(ScheduleBotJob.class)
									  .withIdentity("testJob")
									  .usingJobData("jobSays", "최근 지진 발생 정보")
									  .usingJobData("myFloatValue", "product by MinYoung Lee").build();
			job.getJobDataMap().put("state", state);


			// 10초마다 계속 돌기 java Timer와 비슷
			/*
			 * Trigger trigger = TriggerBuilder.newTrigger()
			 * .withSchedule(SimpleScheduleBuilder.simpleSchedule()
			 * .withIntervalInSeconds(10) .repeatForever()) .build();
			 */

			// CronTrigger 매 10초마다(10,20,30 ...) 작업 실행

			CronTrigger cronTrigger = TriggerBuilder.newTrigger()
													.withIdentity("crontrigger", "crontriggergroup1")
//													.withSchedule(CronScheduleBuilder.cronSchedule("1 * * * * ?"))
													.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
													.build();

			// schedule the job
			SchedulerFactory schFactory = new StdSchedulerFactory();
			Scheduler sch = schFactory.getScheduler();

			//스케줄러 시작
			sch.start();
			sch.scheduleJob(job, cronTrigger);

			// 스케줄러 정지 sch.shutdown();

		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		
		new ScheduleController();
	}

}
