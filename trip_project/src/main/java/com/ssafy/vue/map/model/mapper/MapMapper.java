package com.ssafy.vue.map.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.vue.map.dto.AttractionDto;
import com.ssafy.vue.map.dto.MyLocationDto;
import com.ssafy.vue.map.dto.SidoGugunCodeDto;

public interface MapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(int sidogugun) throws SQLException;
	
	AttractionDto getAttraction(int contentid) throws SQLException;
	List<AttractionDto> listAttractions(Map<String, Object> param) throws SQLException;
	void writeMyLocation(MyLocationDto mylocationDto) throws SQLException;
}
