/**
 * BotSturdy
 * EarthQuakeVO.java
 * 3574
 * 2016. 9. 21.
 */
package EarthQuake.VO;

import java.util.Date;

/**
 * @author Minyoung Lee(blackaaron)
 *
 */
public class SafeLocationVO {
	private String FACIL_POW;
	private String FACIL_UNIT;
	private int ROW_NO;
	private String NEW_FACIL_RD_ADDR;
	private String FACIL_RD_ADDR;
	private String FACIL_NM;
	private String facilGbnCode;
	private String FACIL_ASIGN_YMD;
	private int FACIL_GBN_CODE;
	private int FACIL_SE_CODE;
	private String FACIL_CODE;
	private String FACIL_ADDR;
	public String getFACIL_POW() {
		return FACIL_POW;
	}
	public void setFACIL_POW(String fACIL_POW) {
		FACIL_POW = fACIL_POW;
	}
	public String getFACIL_UNIT() {
		return FACIL_UNIT;
	}
	public void setFACIL_UNIT(String fACIL_UNIT) {
		FACIL_UNIT = fACIL_UNIT;
	}
	public int getROW_NO() {
		return ROW_NO;
	}
	public void setROW_NO(int rOW_NO) {
		ROW_NO = rOW_NO;
	}
	public String getNEW_FACIL_RD_ADDR() {
		return NEW_FACIL_RD_ADDR;
	}
	public void setNEW_FACIL_RD_ADDR(String nEW_FACIL_RD_ADDR) {
		NEW_FACIL_RD_ADDR = nEW_FACIL_RD_ADDR;
	}
	public String getFACIL_RD_ADDR() {
		return FACIL_RD_ADDR;
	}
	public void setFACIL_RD_ADDR(String fACIL_RD_ADDR) {
		FACIL_RD_ADDR = fACIL_RD_ADDR;
	}
	public String getFACIL_NM() {
		return FACIL_NM;
	}
	public void setFACIL_NM(String fACIL_NM) {
		FACIL_NM = fACIL_NM;
	}
	public String getFacilGbnCode() {
		return facilGbnCode;
	}
	public void setFacilGbnCode(String facilGbnCode) {
		this.facilGbnCode = facilGbnCode;
	}
	public String getFACIL_ASIGN_YMD() {
		return FACIL_ASIGN_YMD;
	}
	public void setFACIL_ASIGN_YMD(String fACIL_ASIGN_YMD) {
		FACIL_ASIGN_YMD = fACIL_ASIGN_YMD;
	}
	public int getFACIL_GBN_CODE() {
		return FACIL_GBN_CODE;
	}
	public void setFACIL_GBN_CODE(int fACIL_GBN_CODE) {
		FACIL_GBN_CODE = fACIL_GBN_CODE;
	}
	public int getFACIL_SE_CODE() {
		return FACIL_SE_CODE;
	}
	public void setFACIL_SE_CODE(int fACIL_SE_CODE) {
		FACIL_SE_CODE = fACIL_SE_CODE;
	}
	public String getFACIL_CODE() {
		return FACIL_CODE;
	}
	public void setFACIL_CODE(String fACIL_CODE) {
		FACIL_CODE = fACIL_CODE;
	}
	public String getFACIL_ADDR() {
		return FACIL_ADDR;
	}
	public void setFACIL_ADDR(String fACIL_ADDR) {
		FACIL_ADDR = fACIL_ADDR;
	}
	@Override
	public String toString() {
		return "SafeLocationVO [FACIL_POW=" + FACIL_POW + ", FACIL_UNIT=" + FACIL_UNIT + ", ROW_NO=" + ROW_NO
				+ ", NEW_FACIL_RD_ADDR=" + NEW_FACIL_RD_ADDR + ", FACIL_RD_ADDR=" + FACIL_RD_ADDR + ", FACIL_NM="
				+ FACIL_NM + ", facilGbnCode=" + facilGbnCode + ", FACIL_ASIGN_YMD=" + FACIL_ASIGN_YMD
				+ ", FACIL_GBN_CODE=" + FACIL_GBN_CODE + ", FACIL_SE_CODE=" + FACIL_SE_CODE + ", FACIL_CODE="
				+ FACIL_CODE + ", FACIL_ADDR=" + FACIL_ADDR + "]";
	}

	
}
