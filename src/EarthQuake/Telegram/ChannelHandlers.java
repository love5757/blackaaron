package EarthQuake.Telegram;


import java.io.IOException;
import java.io.InvalidObjectException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

import EarthQuake.EarthQuakeInfoCrawer;
import EarthQuake.VO.EarthQuakeEnum;
import EarthQuake.VO.EarthQuakeVO;
import EarthQuake.GPS.Geocoding;


/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Handler for updates to channel updates bot
 * This is a use case that will send a message to a channel if it is added as an admin to it.
 * @date 24 of June of 2015
 */
public class ChannelHandlers extends TelegramLongPollingBot {
	public static HashMap<Message, EarthQuakeVO> earthQuakeMap = new HashMap<Message, EarthQuakeVO>();
    private static final String LOGTAG = "CHANNELHANDLERS";


    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                try {
                	handleIncomingMessage(message);
//                	if(RECRUIT_INFO.equals(message)){
//                		sendRecuitSearchHelpMessage(message);
//                	}else{
//                		handleIncomingMessage(message);                		
//                	}
                } catch (InvalidObjectException e) {
                    BotLogger.severe(LOGTAG, e);
                }
            }
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    @Override
    public String getBotToken() {
        return BotConfig.CHANNEL_TOKEN;
    }


    @Override
    public String getBotUsername() {
        return BotConfig.CHANNEL_USER;
    }
    
    // 스케쥴러 메시지 전달
    public void schedulerMessageSend(Message message, String text){
    	SendMessage sendMessage = new SendMessage();
    	sendMessage.setChatId(message.getChatId().toString());
    	
    	String resultMessage = text;
    	sendMessage.setText(resultMessage);
    	try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
        }
    }

    // 질문에 답변하기
    private void handleIncomingMessage(Message message) throws InvalidObjectException {
    	String messageText = message.getText();
        if(messageText.startsWith("/")){
        	messageText = messageText.substring(1, messageText.length());
        }
    	if(messageText.equals("닥쳐") || messageText.equals("아놔") || messageText.equals("죽는다") || messageText.equals("")  ){
    		funnyConversation(message, "똑바로 말해! 시비걸지 말고.. 바쁘다");
    	}else if(messageText.equals("뭐") || messageText.equals(" ")){
    		funnyConversation(message, "뭐! 이눔이!");
    	}else if(messageText.equals("야") || messageText.equals("모야")){
    		funnyConversation(message, "내가 니 봇인줄 아냐? ㅋㅋ ");
    	}else if(messageText.equals("싫은데") || messageText.equals("싫어")){
    		funnyConversation(message, "나가!");
    	}else if(messageText.equals("알려줘")){
    		EarthQuakeVO earthQuakeVO = new EarthQuakeVO();
    		EarthQuakeInfoCrawer earthQuakeInfoCrawer = new EarthQuakeInfoCrawer();
    		try {
    			earthQuakeVO = earthQuakeInfoCrawer.getInfoEarthQuake();
    			SendMessage sendMessage = messageForm(message, earthQuakeVO, "");
    			sendMessage(sendMessage);
    			earthQuakeMap.put(message, earthQuakeVO);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (TelegramApiException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else{
    		funnyConversation(message, "장난 그만치고...");
    	}
    }
    
    // SendMessage 공통 폼
    public SendMessage messageForm(Message message, EarthQuakeVO earthQuakeVO, String text){
    	SendMessage sendMessage = new SendMessage();
    	sendMessage.setChatId(message.getChatId().toString());
    	Geocoding geocoding = null;
		try {
			geocoding = new Geocoding(earthQuakeVO.getLatitude(), earthQuakeVO.getLongitude());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	if(text.isEmpty()){
    		String mapLink = EarthQuakeEnum.GOOGLE_MAP.getDesc()+geocoding.getAddress().replace(" ", "+");
    		Date from = new Date();
    		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		String earthQuakeTime = transFormat.format(earthQuakeVO.getEarthQuakeTime());
    		String earthQuakInfoText =
    				"=========================================\n"
    				+"지진 발생시간 = "+earthQuakeTime+"\n"
    				+"지진 규모       = "+earthQuakeVO.getEarthQuakeSacle()+"\n"
    				+"지진 위도       = "+earthQuakeVO.getLatitude()+"\n"
    				+"지진 경도       = "+earthQuakeVO.getLongitude()+"\n"
    				+"지진 발생지역   = "+geocoding.getAddress()+"\n"
    				+"========================================\n"
    				+"구글 지도링크\n"
    				+mapLink+"\n"
    				+"========================================\n"
    				+"규모에 따른 피해사항\n"
    				+earthQuakeStep(earthQuakeVO.getEarthQuakeSacle());

    		sendMessage.setText(earthQuakInfoText);
    	}else{
    		String infoText ="'/알려줘' 라 입력하시면 최근에 일어난 지진정보를 알 수 있습니다.";
    		sendMessage.setText(text+"\n"+infoText);
    	}
    	return sendMessage;
    }
    
    // 규모에 따른 피해사항 텍스트
    public String earthQuakeStep(double scale){
    	String stepSituation ="";
    	if(scale<3){
    		stepSituation = EarthQuakeEnum.LEVEL_1.getDesc();
    	}else if(3<=scale&&scale<4){
    		stepSituation = EarthQuakeEnum.LEVEL_2.getDesc();
    	}else if(4<=scale&&scale<5){
    		stepSituation = EarthQuakeEnum.LEVEL_3.getDesc();
    	}else if(5<=scale&&scale<6){
    		stepSituation = EarthQuakeEnum.LEVEL_4.getDesc();
    	}else if(6<=scale&&scale<7){
    		stepSituation = EarthQuakeEnum.LEVEL_5.getDesc();
    	}else{
    		stepSituation = EarthQuakeEnum.LEVEL_6.getDesc();
    	}
    	return stepSituation;
    }
    
    // 봇과 장난치는 메시지
    public void funnyConversation(Message message, String text){
    	SendMessage sendMessage = new SendMessage();
    	sendMessage.setChatId(message.getChatId().toString());
		String infoText ="'/알려줘' 라 입력하시면 최근에 일어난 지진정보를 알 수 있습니다.";
		sendMessage.setText(text+"\n"+infoText);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
