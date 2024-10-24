package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.CalendarService;
import com.yedam.service.CalendarServiceImpl;

public class AddEventControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		CalendarService svc = new CalendarServiceImpl();
		
		String title = req.getParameter("title");
		String startDate = req.getParameter("start");
		String endDate = req.getParameter("end");
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("title", title);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		System.out.println(map);
		
		if(svc.addEvent(map)) {
			resp.getWriter().print("{\"retCode\": \"OK\"}");
		} else {
			resp.getWriter().print("{\"retCode\": \"FAIL\"}");
		}
	
	}

}
