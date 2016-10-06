/**
 * BotSturdy
 * EarthQuakeVO.java
 * 3574
 * 2016. 9. 21.
 */
package EarthQuake.VO;

/**
 * @author Minyoung Lee(blackaaron)
 *
 */
public class SafeLocationSelectBoxVO {
	
	
	private String orgNm;	   
	private int upperOrgCd;
	private int orgCd;
	private int totCnt;
	private int num;
	private int orgAcctoStepSeCd;
	private String useAt;
	private double arcd;
	private String fllOrgNm;
	public String getOrgNm() {
		return orgNm;
	}
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	public int getUpperOrgCd() {
		return upperOrgCd;
	}
	public void setUpperOrgCd(int upperOrgCd) {
		this.upperOrgCd = upperOrgCd;
	}
	public int getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(int orgCd) {
		this.orgCd = orgCd;
	}
	public int getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getOrgAcctoStepSeCd() {
		return orgAcctoStepSeCd;
	}
	public void setOrgAcctoStepSeCd(int orgAcctoStepSeCd) {
		this.orgAcctoStepSeCd = orgAcctoStepSeCd;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public double getArcd() {
		return arcd;
	}
	public void setArcd(double arcd) {
		this.arcd = arcd;
	}
	public String getFllOrgNm() {
		return fllOrgNm;
	}
	public void setFllOrgNm(String fllOrgNm) {
		this.fllOrgNm = fllOrgNm;
	}
	@Override
	public String toString() {
		return "SafeLocationSelectBoxVO [orgNm=" + orgNm + ", upperOrgCd=" + upperOrgCd + ", orgCd=" + orgCd
				+ ", totCnt=" + totCnt + ", num=" + num + ", orgAcctoStepSeCd=" + orgAcctoStepSeCd + ", useAt=" + useAt
				+ ", arcd=" + arcd + ", fllOrgNm=" + fllOrgNm + "]";
	}
	
	
}
