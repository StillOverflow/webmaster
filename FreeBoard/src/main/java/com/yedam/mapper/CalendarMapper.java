package com.yedam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CalendarMapper {
	public List<Map<String, String>> events();
	public int addEvent(@Param("title") String title, @Param("startDate") String startDate, @Param("endDate") String endDate);
	public int deleteEvent(@Param("title") String title, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
