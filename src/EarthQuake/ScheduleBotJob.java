/**
 * BotSturdy
 * BotJob.java
 * 3574
 * 2016. 9. 8.
 */
package EarthQuake;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import EarthQuake.Telegram.ChannelHandlers;
import EarthQuake.VO.EarthQuakeVO;


/**
 * @author 3574
 *
 */
public class ScheduleBotJob implements Job{

	//로그선언
	static final Logger log = LoggerFactory.getLogger(ScheduleBotJob.class);
	EarthQuakeVO earthQuakeVO = new EarthQuakeVO();
	EarthQuakeInfoCrawer earthQuakeController = new EarthQuakeInfoCrawer();
	String jobSays;
	float myFloatValue;
	ArrayList state;

	private EarthQuakeVO currentEarthQuakep = ScheduleController.currentEarthQuakep;
	
	public ScheduleBotJob() {

	}
	//스케줄러에 인해 주기적으로 실행되는 함수.
	public void execute(JobExecutionContext context)throws JobExecutionException{
		JobKey key = context.getJobDetail().getKey();
		
		state.add(new Date());
		
//		log.error("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue + ", state size:" + state.size() + "," +state.get(state.size()-1));
		
		ChannelHandlers channelHandlers = new ChannelHandlers();
			try {
				earthQuakeVO = earthQuakeController.getInfoEarthQuake();
				log.debug("***********홈페이지 지진정보"+earthQuakeVO.toString()+"***********");
				
				if(currentEarthQuakep.getLastCount() == 0){
					currentEarthQuakep = earthQuakeVO;
					log.debug("***********최근 지진정보"+currentEarthQuakep.toString()+"***********");
				}
				
				if(currentEarthQuakep.getLastCount() < earthQuakeVO.getLastCount()){
					channelHandlers.schedulerMessageSend(earthQuakeVO,"@jijin2");
					currentEarthQuakep.setLastCount(earthQuakeVO.getLastCount());
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("***********information Connection Error***********");
				log.error("***********홈페이지 지진정보"+earthQuakeVO.toString()+"***********");
			}
		
	}
	
	public void setJobSays(String jobSays) {
		this.jobSays = jobSays;
	}
	public void setMyFloatValue(float myFloatValue) {
		this.myFloatValue = myFloatValue;
	}
	public void setState(ArrayList state) {
		this.state = state;
	}
}


