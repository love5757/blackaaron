/**
 * BotSturdy
 * EarthQuakeEnum.java
 * 3574
 * 2016. 9. 21.
 */
package EarthQuake.VO;

/**
 * @author 3574
 *
 */
public enum EarthQuakeEnum {
	URL ("URL","http://necis.kma.go.kr/necis-dbf/user/earthquake/annual_earthquake.do"),
	LASTEARTHQUAKE_ELEMENTS ("LASTEARTHQUAKE_ELEMENTS","div.yearly_earthquake tbody tr"),
	GOOGLE_MAP ("GOOGLE_MAP","http://maps.google.com/maps?q="),
	//2.9미만
	LEVEL_1 ("LEVEL_1","극소수의 사람을 제외하고는 전혀 느낄 수 없는 수준"),
	//3~4 세기
	LEVEL_2 ("LEVEL_2","소수의 사람들, 특히 건물의 윗 층에 있는 소수의 사람들만 느낄 수 있는 수준으로 섬세하게 매달린 물체 진동"),
	//4~5 세기
	LEVEL_3 ("LEVEL_3","거의 모든 사람들이 느끼는 수준\n밤에 잠을 깨며 그릇, 창문, 문 등이 소란하며 벽이 갈라지는 소리를 냄. 대형 트럭이 벽을 받는 느낌을 주고 정지하고 있는 자동차의 움직임이 뚜렷함"),
	//5~6 세기
	LEVEL_4 ("LEVEL_4","모든 사람들이 밖으로 뛰어 나오는 수준 \n잘 설계된 건물에 피해가 없을 수 있으나 보통 건축물에는 약간의 피해가 있으며, 부실한 건축물에는 큰 피해가 발생하고 운전자가 느낄 수 있음."),
	//6~7 세기
	LEVEL_5 ("LEVEL_5","창벽이 무너지고 굴뚝, 기둥, 벽들이 무너짐. 무거운 가구가 뒤집어 지며 모래와 진흙이 솟아남. 우물 수면이 변하고 운전자가 방해를 받음."),
	//7 세기 이상
	LEVEL_6 ("LEVEL_6","교량이 부서지고 땅에 넓은 균열이 발생되며, 지하 파이프가 완전히 파괴됨. 연약한 땅이 푹 꺼지고 지층이 어긋나며, 기차선로가 심하게 휘어짐.\n지표면에 파동이 보이고 시야와 수평면이 뒤틀리고 물체가 하늘로 던져짐."),
	
	LASTCOUNT_ELEMENTS ("LASTCOUNT_ELEMENTS","div.totalCount b");
 
    private String code;
    private String desc;
 
    EarthQuakeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return this.code;
    }
    public String getDesc() {
        return this.desc;
    }
}