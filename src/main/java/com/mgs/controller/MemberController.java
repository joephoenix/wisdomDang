package com.mgs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.mgs.entity.MemberInfo;
import com.mgs.entity.Plog;
import com.mgs.entity.Pmember;
import com.mgs.entity.Porganize;
import com.mgs.entity.Power;
import com.mgs.entity.RelationMO;
import com.mgs.service.PlogService;
import com.mgs.service.PmemberService;
import com.mgs.service.PorganizeService;
import com.mgs.service.RelationService;
import com.mgs.view.MemberView;
import com.mgs.view.Rqbody;

@Controller
public class MemberController {

	@Autowired
	private PmemberService pmemberService;

	@Autowired
	private PlogService plogService;

	@Autowired
	private PorganizeService porganizeService;

	@Autowired
	private RelationService relationService;

	@RequestMapping(value = "/appLogin", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject appLoginAction(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		// read the parameters
		String username = body.getUsername();
		String longtude = body.getLongtude();
		String latitude = body.getLatitude();
		String password = body.getPassword();
		// verify
		if (null == username || null == longtude || null == latitude) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String membid = jsonObject.getString("result");
			Plog loginLog = new Plog();
			loginLog.setLongitude("120.203081");
			loginLog.setLatitude("30.600326");
			loginLog.setMid(membid);
			loginLog.setIsAttend(0);
			plogService.addLogForMember(loginLog);
			jsonObject.put("message", "Login");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			if (jsonObject.get("message") == "ReLogin") {
				String membid = jsonObject.getString("result");
				String passwd = jsonObject.getString("passwd");
				if (!passwd.equals(password)) {
					jsonObject.put("message", "ParametersError");
					jsonObject.put("status", "error");
					return jsonObject;
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLongitude("120.203081");
					loginLog.setLatitude("30.600326");
					loginLog.setMid(membid);
					loginLog.setIsAttend(0);
					plogService.addLogForMember(loginLog);
					jsonObject.put("message", "Attend");
					jsonObject.put("status", "success");
					return jsonObject;
				}
			} else {
				return jsonObject;
			}
		}

	}

	@RequestMapping(value = "/normalLogin", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject loginAction(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		String password = body.getPassword();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String membid = jsonObject.getString("result");
			Plog loginLog = new Plog();
			loginLog.setLongitude("120.203081");
			loginLog.setLatitude("30.600326");
			loginLog.setMid(membid);
			loginLog.setIsAttend(0);
			plogService.addLogForMember(loginLog);
			jsonObject.put("message", "Login");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			if (jsonObject.get("message") == "ReLogin") {
				String membid = jsonObject.getString("result");
				String passwd = jsonObject.getString("passwd");
				if (!passwd.equals(password)) {
					jsonObject.put("message", "ParametersError");
					jsonObject.put("status", "error");
					return jsonObject;
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLongitude("120.203081");
					loginLog.setLatitude("30.600326");
					loginLog.setMid(membid);
					loginLog.setIsAttend(0);
					plogService.addLogForMember(loginLog);
					jsonObject.put("message", "Attend");
					jsonObject.put("status", "success");
					return jsonObject;
				}
			} else {
				return jsonObject;
			}
		}
	}

	@RequestMapping(value = "/memberList", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showMemberList(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String membid = jsonObject.getString("result");
			List<RelationMO> lstRMO1 = relationService.findReltionsByMember(membid);
			if (null == lstRMO1 || 0 == lstRMO1.size()) {
				jsonObject.put("message", "SomeErrorAppeared");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				String orgId = lstRMO1.get(0).getOid();
				List<Porganize> subOrgs = new ArrayList<Porganize>();
				porganizeService.ergodicSubOrganizes(orgId, subOrgs);
				if (null == subOrgs || 0 == subOrgs.size()) {
					jsonObject.put("message", "SomeErrorAppeared");
					jsonObject.put("status", "error");
					return jsonObject;
				} else {
					List<MemberView> mvlst = new ArrayList<MemberView>();
					for (Porganize org : subOrgs) {
						List<RelationMO> lstRMO2 = relationService.findRelationByOrganize(org.getId());
						if (null == lstRMO2 || 0 == lstRMO2.size()) {

						} else {
							for (RelationMO rmo : lstRMO2) {
								String mid = rmo.getMid();
								Plog lg = plogService.getLastLog4ChooseMember(mid);
								MemberInfo mi = pmemberService.getMemberDetailBymid(mid);
								MemberView mv = new MemberView();
								mv.setLatitude(lg.getLatitude());
								mv.setLongtude(lg.getLongitude());
								mv.setMid(rmo.getId());
								mv.setRecordtm(lg.getRecordtm());
								mv.setRname(mi.getName());
								mvlst.add(mv);
							}
						}

					}
					jsonObject.put("result", mvlst);
					jsonObject.put("message", "getMemberViewList");
					jsonObject.put("status", "success");
					return jsonObject;
				}
			}
		} else {
			return jsonObject;
		}

	}

	@RequestMapping(value = "/memberLogs", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject ShowMemberLogs(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		String memid = body.getMemid();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			List<Plog> mLoglist = plogService.queryLogsByMember(memid);
			jsonObject.put("logs", mLoglist);
			jsonObject.put("message", "getMemberLogs");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			return jsonObject;
		}
	}

	@RequestMapping(value = "/memberInfo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject getMemberInfo(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String membid = jsonObject.getString("result");
			Pmember rpm = pmemberService.getMemberInformation(membid);
			jsonObject.put("memInfo", rpm);
			jsonObject.put("message", "get memeber information");
			jsonObject.put("status", "success");
			return jsonObject;
		} else {
			return jsonObject;
		}
	}

	@RequestMapping(value = "/showMenu", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showMenus(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String membid = jsonObject.getString("result");
			List<Power> rplist = relationService.lstMenuByMemberId(membid);
			jsonObject.put("menus", rplist);
			jsonObject.put("message", "getMenus");
			jsonObject.put("status", "success");
			return jsonObject;

		} else {
			return jsonObject;
		}
	}

	@RequestMapping(value = "/showMemberDetail", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showMemberDetail(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String membid = jsonObject.getString("result");
			MemberInfo mi = pmemberService.getMemberDetailBymid(membid);
			jsonObject.put("memberDetail", mi);
			jsonObject.put("message", "getInformation");
			jsonObject.put("status", "success");
			return jsonObject;

		} else {
			return jsonObject;
		}
	}
}
