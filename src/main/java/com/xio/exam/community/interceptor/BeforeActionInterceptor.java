package com.xio.exam.community.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.xio.exam.community.service.MemberService;
import com.xio.exam.community.vo.Rq;

@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
	
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}
