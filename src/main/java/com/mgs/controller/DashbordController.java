package com.mgs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.mgs.entity.ResourceFile;
import com.mgs.service.PmemberService;
import com.mgs.service.ResourceFileService;
import com.mgs.view.Rqbody;

@Controller
public class DashbordController {

	@Autowired
	private ResourceFileService resourceFileService;

	@Autowired
	private PmemberService pmemberService;

	@RequestMapping(value = "/userNotification", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showNotices(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String memid = jsonObject.getString("result");
			List<ResourceFile> lstRF = resourceFileService.findNotice4Member(memid);
			jsonObject.put("result", lstRF);
			jsonObject.put("message", "NoticeShow");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			return jsonObject;
		}
	}
}
