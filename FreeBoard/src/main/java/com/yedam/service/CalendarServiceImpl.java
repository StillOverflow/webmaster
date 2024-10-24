package com.yedam.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.CalendarMapper;

public class CalendarServiceImpl implements CalendarService {

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	CalendarMapper mapper = sqlSession.getMapper(CalendarMapper.class);
	
	@Override
	public List<Map<String, String>> getAllEvents() {
		return mapper.events();
	}

	@Override
	public boolean addEvent(Map<String, String> map) {
		return mapper.addEvent(map.get("title"), map.get("startDate"), map.get("endDate")) == 1;
	}

	@Override
	public boolean removeEvent(Map<String, String> map) {
		return mapper.deleteEvent(map.get("title"), map.get("startDate"), map.get("endDate")) == 1;
	}

}
