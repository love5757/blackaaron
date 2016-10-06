/**
 * BotSturdy
 * EarthQuakeController.java
 * 3574
 * 2016. 9. 21.
 */
package EarthQuake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import EarthQuake.VO.EarthQuakeEnum;
import EarthQuake.VO.SafeLocationSelectBoxVO;
import EarthQuake.VO.SafeLocationVO;

/**
 * @author 3574
 *
 */
public class SafeLocationSearch {
	static final Logger log = LoggerFactory.getLogger(SafeLocationSearch.class);
	static int areaCodeOne =0;
	static int areaCodeTwo =0;
	static int areaCodeThree =0;
	

	// URLConnection 공통 폼
	public static HttpURLConnection connectionForm(String url, int upperCode, int[] addrCd){
		HttpURLConnection con = null ;
		try {
			URL obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept", EarthQuakeEnum.SAFE_ACCEPT.getDesc());
			con.setRequestProperty("Content-type", EarthQuakeEnum.SAFE_CONTENT_TYPE.getDesc());
			con.setRequestProperty("Accept-Encoding", EarthQuakeEnum.SAFE_ACCEPT_ENCODING.getDesc());
			
			JSONObject selectList = new JSONObject();
			JSONObject params = new JSONObject();
    		// Url 별 파라미터 세팅
			if(url.equals(EarthQuakeEnum.SAFE_URL.getDesc())){
				params.put("pageIndex", "1");
				params.put("searchGb", "pageSearch");
				params.put("q_area_cd_1", addrCd[0]);
				params.put("q_area_cd_2", addrCd[1]);
				params.put("q_area_cd_3", "");
				params.put("q_equp_type", "S");
				params.put("gubunCode", "CO7NN00006");
				selectList.put("selectList", params);
			}else if(url.equals(EarthQuakeEnum.SAFE_SELLECTBOX_URL.getDesc())){
			    params.put("upperOrgCd", upperCode);
			    selectList.put("reqInfo", params);
			}
    		// For POST only - START
    		con.setDoOutput(true);
    		OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
    		os.write(selectList.toString());
    		os.flush();
    		os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	// 최종 가까운 위치의 대피소 파악하기
	public static List<SafeLocationVO> searchSafeLocaion(int upperCode, int[] addrCd){
		List<SafeLocationVO> safeLocList = null ;
    	try {
    		HttpURLConnection con = connectionForm(EarthQuakeEnum.SAFE_URL.getDesc(), upperCode, addrCd);
    		int responseCode = con.getResponseCode();
    		if (responseCode == HttpURLConnection.HTTP_OK) { //success
    			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    			new InputStreamReader(con.getInputStream());
    			String inputLine;
    			StringBuffer response = new StringBuffer();
    			while ((inputLine = in.readLine()) != null) {
    				response.append(inputLine);
    			}
    			// JSON 형태 분리작업
    		    String responseString = response.toString();
    	        JSONObject jObj = new JSONObject(responseString);
    	        JSONArray jObj2 = jObj.getJSONArray("List");
    	        // Google Gson() 사용
    	        safeLocList = new Gson().fromJson(jObj2.toString(), new TypeToken<List<SafeLocationVO>>(){}.getType());
    			in.close();
    		} else {
    			log.error("*************searchSafeLocaion Error*************");
    		}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return safeLocList;
	}
	
	// 1,2,3차 depth의 코드 값 구분의 selectbox
	public static List<SafeLocationSelectBoxVO> searchSelectBoxCode(int upperCode, int[] addrCd){
		List<SafeLocationSelectBoxVO> selectBoxList = null ;
    	try {
    		HttpURLConnection con = connectionForm(EarthQuakeEnum.SAFE_SELLECTBOX_URL.getDesc(), upperCode, addrCd);
    		int responseCode = con.getResponseCode();
    		if (responseCode == HttpURLConnection.HTTP_OK) { //success
    			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    			new InputStreamReader(con.getInputStream());
    			String inputLine;
    			StringBuffer response = new StringBuffer();
    			while ((inputLine = in.readLine()) != null) {
    				response.append(inputLine);
    			}
    			// JSON 형태 분리작업
    		    String responseString = response.toString();
    	        JSONObject jObj = new JSONObject(responseString);
    	        JSONArray jObj2 = jObj.getJSONArray("List");
    	        // Google Gson() 사용
    	        selectBoxList = new Gson().fromJson(jObj2.toString(), new TypeToken<List<SafeLocationSelectBoxVO>>(){}.getType());
    	        in.close();
    			
    		} 
		} catch (Exception e) {
			log.error("*************searchSelectBoxCode Error*************");
		}
		return selectBoxList;
	}
	
	public List<SafeLocationVO> locationMessageMatch(String userLocation){
		int[] addrCd = {0,0,0};
		String[] fullAddr = userLocation.split(" ");
		String addr1 = fullAddr[1];
		String addr2 = fullAddr[1]+" "+fullAddr[2];
		String addr3 = fullAddr[1]+" "+fullAddr[2]+" "+fullAddr[3];
		int upperCode=0;
		List<SafeLocationVO> safeLocList = null;
		try {
			areaCodeOne = getUpperCodeMtd(upperCode, addr1).getOrgCd();
			addrCd[0]=areaCodeOne;
			areaCodeTwo = getUpperCodeMtd(areaCodeOne, addr2).getOrgCd();
			addrCd[1]=areaCodeTwo;
			// 문제점 발견
			// 1. 서울 특별시 동작구 신길7동 인데 실제 데이터에는 신길제7동임.. 3번째 값이 맞지 않아서 null
			// 2. 지도에서 위도 경도로 했을 때 두번 째 값인 "~~구"가 값이 없는 경우가 있음
			// 예를 들어 서울특별시 대방동 대림아파트
//		areaCodeThree = getUpperCodeMtd(areaCodeTwo, addr3).getOrgCd();
//		addrCd[2]=areaCodeThree;
//		System.out.println(addrCd[2]);
			safeLocList = searchSafeLocaion(0, addrCd);
			
		} catch (Exception e) {
			log.error("************"+userLocation+"**************");
		}
		//pushpush baby
		return safeLocList;
	}

	/**
	 * @param searchSelectBoxCode
	 * @param upperCode
	 * @param addr3
	 * @return
	 */
	public SafeLocationSelectBoxVO getUpperCodeMtd(int searchUpperCode, String addr) {
		SafeLocationSelectBoxVO result = new SafeLocationSelectBoxVO();
		List<SafeLocationSelectBoxVO> selectBoxList =  searchSelectBoxCode(searchUpperCode, null);
		for(SafeLocationSelectBoxVO temp : selectBoxList){
			if(temp.getFllOrgNm().equals(addr)){
				result  = temp;
			}
		}
		return result;
	}
    
}
