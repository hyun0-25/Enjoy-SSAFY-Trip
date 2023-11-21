package com.ssafy.vue.map.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.vue.map.dto.AttractionDto;
import com.ssafy.vue.map.dto.MyLocationDto;
import com.ssafy.vue.map.dto.SidoGugunCodeDto;

public interface MapService {

	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(int sidogugun) throws Exception;
	
	AttractionDto getAttraction(int contentid) throws Exception;
	List<AttractionDto> listAttractions(Map<String, Integer> map) throws Exception;
	void writeMyLocation(MyLocationDto mylocationDto) throws Exception;
	
}
