package com.mgs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
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
		// get the member state
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				String cpwd = mem.getPword();
				if (!cpwd.equals(password)) {
					jsonObject.put("message", "ParametersError");
					jsonObject.put("status", "error");
					return jsonObject;
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLatitude(latitude);
					loginLog.setLongitude(longtude);
					loginLog.setMid(mem.getId());
					plogService.addLogForMember(loginLog);
					jsonObject.put("message", "attend");
					jsonObject.put("status", "success");
					return jsonObject;
				}
			} else {
				Plog loginLog = new Plog();
				loginLog.setLongitude(longtude);
				loginLog.setLatitude(latitude);
				loginLog.setMid(memID);
				plogService.addLogForMember(loginLog);
				jsonObject.put("message", "Login");
				jsonObject.put("status", "success");
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
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				String cpwd = mem.getPword();
				if (!cpwd.equals(password)) {
					jsonObject.put("message", "ParametersError");
					jsonObject.put("status", "error");
					return jsonObject;
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLongitude("120.203081");
					loginLog.setLatitude("30.600326");
					loginLog.setMid(mem.getId());
					plogService.addLogForMember(loginLog);
					jsonObject.put("message", "Attend");
					jsonObject.put("status", "success");
					return jsonObject;
				}
			} else {
				Plog loginLog = new Plog();
				loginLog.setLongitude("120.203081");
				loginLog.setLatitude("30.600326");
				loginLog.setMid(memID);
				plogService.addLogForMember(loginLog);
				jsonObject.put("message", "Login");
				jsonObject.put("status", "success");
				return jsonObject;
			}
		}

	}

	@RequestMapping(value = "/memberList", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showMemberList(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		Pmember pm = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (memst.get("code").equals("OK")) {
			pm = (Pmember) memst.get("member");
		} else {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		}
		String memID = pmemberService.isLoginState(pm);
		if ("NotLogin" == memID) {
			jsonObject.put("message", memID);
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			List<RelationMO> lstRMO1 = relationService.findReltionsByMember(memID);
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
								Pmember subMember = pmemberService.getMemberInformation(rmo.getMid());
								Plog lg = plogService.getLastLog4ChooseMember(rmo.getMid());
								MemberView mv = new MemberView();
								mv.setLatitude(lg.getLatitude());
								mv.setLongtude(lg.getLongitude());
								mv.setMid(rmo.getId());
								mv.setRecordtm(lg.getRecordtm());
								mv.setRname(subMember.getRname());
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
		}

	}

	@RequestMapping(value = "/memberLogs", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject ShowMemberLogs(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		String memid = body.getMemid();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				jsonObject.put("message", "PermissionBanished");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				List<Plog> mLoglist = plogService.queryLogsByMember(memid);
				jsonObject.put("logs", mLoglist);
				jsonObject.put("message", "getMemberLogs");
				jsonObject.put("status", "success");
				return jsonObject;
			}
		}
	}

	@RequestMapping(value = "/memberInfo", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject getMemberInfo(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				jsonObject.put("message", "PermissionBanished");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				Pmember rpm = pmemberService.getMemberInformation(memID);
				jsonObject.put("memInfo", rpm);
				jsonObject.put("message", "get memeber information");
				jsonObject.put("status", "success");
				return jsonObject;
			}
		}
	}

	@RequestMapping(value = "/showMenu", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showMenus(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		if (null == username) {
			jsonObject.put("message", "ParametersError");
			jsonObject.put("status", "error");
			return jsonObject;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			jsonObject.put("message", memst.get("code"));
			jsonObject.put("status", "error");
			return jsonObject;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				jsonObject.put("message", "PermissionBanished");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				List<Power> rplist = relationService.lstMenuByMemberId(memID);
				jsonObject.put("menus", rplist);
				jsonObject.put("message", "getMenus");
				jsonObject.put("status", "success");
				return jsonObject;
			}
		}
	}
}
