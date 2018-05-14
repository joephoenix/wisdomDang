package com.mgs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mgs.service.PmemberService;

@WebFilter(filterName = "Servlet3Filter", urlPatterns = "/*")
@Component
public class Servlet3Filter implements Filter {

	@Autowired
	private PmemberService pmemberService;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		JSONObject jsonObject = new JSONObject();
		String username = request.getParameterMap().get("username").toString();
		jsonObject = pmemberService.judgeLoginState(username);
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsonObject.toJSONString());
		out.close();
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
