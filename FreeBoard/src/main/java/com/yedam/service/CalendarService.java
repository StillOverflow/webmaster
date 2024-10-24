package com.yedam.service;

import java.util.List;
import java.util.Map;

public interface CalendarService {
	
	public List<Map<String, String>> getAllEvents();
	public boolean addEvent(Map<String, String> map);
	public boolean removeEvent(Map<String, String> map);
	
}
