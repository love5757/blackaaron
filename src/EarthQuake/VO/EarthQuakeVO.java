/**
 * BotSturdy
 * EarthQuakeVO.java
 * 3574
 * 2016. 9. 21.
 */
package EarthQuake.VO;

import java.util.Date;

/**
 * @author 3574
 *
 */
public class EarthQuakeVO {
	
	private int num;							// 번호(index)
	private Date earthQuakeTime;				// 지진 발생시간
	private double earthQuakeSacle;				// 지진 규모
	private double latitude;					// 위도
	private double longitude;					// 경도
	private String earthQuakeArea;				// 지진 발생지역
	private String earthQuakeFullInfo;			// 최근 지진 전체 정보
	
	// 마지막 지진 index 값
	private int lastCount;					// 가장 최근번호

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getEarthQuakeTime() {
		return earthQuakeTime;
	}

	public void setEarthQuakeTime(Date earthQuakeTime) {
		this.earthQuakeTime = earthQuakeTime;
	}

	public double getEarthQuakeSacle() {
		return earthQuakeSacle;
	}

	public void setEarthQuakeSacle(double earthQuakeSacle) {
		this.earthQuakeSacle = earthQuakeSacle;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getEarthQuakeArea() {
		return earthQuakeArea;
	}

	public void setEarthQuakeArea(String earthQuakeArea) {
		this.earthQuakeArea = earthQuakeArea;
	}

	public String getEarthQuakeFullInfo() {
		return earthQuakeFullInfo;
	}

	public void setEarthQuakeFullInfo(String earthQuakeFullInfo) {
		this.earthQuakeFullInfo = earthQuakeFullInfo;
	}

	public int getLastCount() {
		return lastCount;
	}

	public void setLastCount(int lastCount) {
		this.lastCount = lastCount;
	}

	@Override
	public String toString() {
		return "EarthQuakeVO [num=" + num + ", earthQuakeTime=" + earthQuakeTime + ", earthQuakeSacle="
				+ earthQuakeSacle + ", latitude=" + latitude + ", longitude=" + longitude + ", earthQuakeArea="
				+ earthQuakeArea + ", earthQuakeFullInfo=" + earthQuakeFullInfo + ", lastCount=" + lastCount + "]";
	}
	
	
}
