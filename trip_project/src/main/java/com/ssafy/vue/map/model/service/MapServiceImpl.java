package com.ssafy.vue.map.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.vue.map.dto.AttractionDto;
import com.ssafy.vue.map.dto.MyLocationDto;
import com.ssafy.vue.map.dto.SidoGugunCodeDto;
import com.ssafy.vue.map.dto.TripDto;
import com.ssafy.vue.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {
	
	private MapMapper mapMapper;

	public MapServiceImpl(MapMapper mapMapper) {
		super();
		this.mapMapper = mapMapper;
	}

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return mapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(int sidogugun) throws Exception {
		return mapMapper.getGugunInSido(sidogugun);
	}

	@Override
	public AttractionDto getAttraction(int contentid) throws Exception {
		return mapMapper.getAttraction(contentid);
	}

	@Override
	public List<AttractionDto> listAttractions(Map<String, Integer> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sido", map.get("sido"));
		param.put("gugun", map.get("gugun"));
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		return mapMapper.listAttractions(param);
	}

	@Override
	public void writeMyLocation(List<MyLocationDto> mylocationlist) throws Exception {
		mapMapper.writeMyLocation(mylocationlist);
	}

	@Override
	public List<TripDto> mylocationList(String userId) throws Exception {
		return mapMapper.mylocationList(userId);
	}

	@Override
	public List<MyLocationDto> courseList(int courseId) throws Exception {
		return mapMapper.courseList(courseId);
	}

	@Override
	public void deleteCourse(int courseId) throws Exception {
		mapMapper.deleteCourse(courseId);		
	}

}
