package com.mgs;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.mgs.service.PmemberService;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private PmemberService pmemberService;

	/**
	 * Handler执行完成之后调用这个方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc)
			throws Exception {

	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * Handler执行之前调用这个方法
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		// 获取请求的URL
		String url = request.getRequestURI();

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toJSONString());
		out.close();

		return false;

	}

}
