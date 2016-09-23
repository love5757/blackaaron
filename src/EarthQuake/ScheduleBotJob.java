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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import EarthQuake.Telegram.ChannelHandlers;
import EarthQuake.VO.EarthQuakeVO;


/**
 * @author 3574
 *
 */
public class ScheduleBotJob implements Job{
	
	
	
	EarthQuakeVO earthQuakeVO = new EarthQuakeVO();
	EarthQuakeInfoCrawer earthQuakeController = new EarthQuakeInfoCrawer();
	//TelegramMain telegram = new TelegramMain();
	String jobSays;
	float myFloatValue;
	ArrayList state;

	public ScheduleBotJob() {

	}
	//스케줄러에 인해 주기적으로 실행되는 함수.
	public void execute(JobExecutionContext context)throws JobExecutionException{
		JobKey key = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getMergedJobDataMap();

		//데이터는 아래 setter에서 저장된다. 혹은 아래와 같이 얻어도 된다.
		/*
	       String jobSays = dataMap.getString("jobSays");
	       float myFloatValue = dataMap.getFloat("myFloatValue");
	       ArrayList state = (ArrayList) dataMap.get("myStateData"); 
		 */
		state.add(new Date());
		System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue + ", state size:" + state.size() + "," +state.get(state.size()-1));
		
		
		HashMap<Message, EarthQuakeVO> earthQuakeMap = ChannelHandlers.earthQuakeMap;
		ChannelHandlers channelHandlers = new ChannelHandlers();
		for (Map.Entry<Message, EarthQuakeVO> entry : earthQuakeMap.entrySet() ){
			SendMessage sendMessage = new SendMessage();
			sendMessage.setChatId(entry.getKey().getChatId().toString());
			System.out.println("schedulerBotController = "+entry.getKey().getChatId().toString());
			try {
				earthQuakeVO = earthQuakeController.getInfoEarthQuake();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(earthQuakeVO.getLastCount()>earthQuakeMap.get(earthQuakeVO).getLastCount()){
				channelHandlers.schedulerMessageSend(entry.getKey(), earthQuakeVO.getEarthQuakeFullInfo());
			}
			
		}
//		try {
//			earthQuakeVO = earthQuakeController.getInfoEarthQuake();
//			if(earthQuakeVO.getLastCount()>메모리에 있는 값보다 크면){
//					메시지 보내고 / 메모리 갱신 다시 저장
//			}
//			System.out.println("나와랏!" + earthQuakeVO.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	//아래와 같이 setter 형식으로 값을 받을 수도 있고 혹은, datamap을 통해 얻을 수 있음
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


