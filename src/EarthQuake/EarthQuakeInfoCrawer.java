/**
 * BotSturdy
 * EarthQuakeController.java
 * 3574
 * 2016. 9. 21.
 */
package EarthQuake;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import EarthQuake.VO.EarthQuakeEnum;
import EarthQuake.VO.EarthQuakeVO;
/**
 * @author 3574
 *
 */
public class EarthQuakeInfoCrawer {

	static final Logger log = LoggerFactory.getLogger(EarthQuakeInfoCrawer.class);
	private static EarthQuakeVO earthQuakeVO = new EarthQuakeVO();

	public static EarthQuakeVO getInfoEarthQuake() throws IOException{
		Document doc = Jsoup.connect(EarthQuakeEnum.URL.getDesc()).get();
		Elements lastEarthQuakeInfo = doc.select(EarthQuakeEnum.LASTEARTHQUAKE_ELEMENTS.getDesc());
		Elements lastCount = doc.select(EarthQuakeEnum.LASTCOUNT_ELEMENTS.getDesc());

		// 가장 최근 번호 저장
		earthQuakeVO.setLastCount( Integer.parseInt(lastCount.text()));
		
		String str = lastEarthQuakeInfo.first().text();
		String[] values = str.split(" ");
		
		Date to = null;
		try {
			String from = values[1]+" "+values[2];
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			to = transFormat.parse(from);
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("CRAWLING DATA FORMATE ERROR");
		}
		
		// 최근에 일어난 지진정보 earthQuakeVO 객체에 넣기
		earthQuakeVO.setNum(Integer.parseInt(values[0]));
		earthQuakeVO.setEarthQuakeTime(to);
		earthQuakeVO.setEarthQuakeSacle(Double.parseDouble(values[3]));
		earthQuakeVO.setLatitude(Double.parseDouble(values[4]));
		earthQuakeVO.setLongitude(Double.parseDouble(values[5]));
		earthQuakeVO.setEarthQuakeArea(values[6]+" "+values[7]+" "+values[8]);
		// 전체 정보 한줄로 저장
		earthQuakeVO.setEarthQuakeFullInfo(str);
		return earthQuakeVO;
	}
}
