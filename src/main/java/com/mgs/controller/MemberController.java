package com.mgs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value = "/appLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> appLoginAction(@RequestBody Rqbody body) {
		// read the parameters
		String username = body.getUsername();
		String longtude = body.getLongtude();
		String latitude = body.getLatitude();
		String password = body.getPassword();
		// create return parameter
		Map<String, Object> mrlt = new HashMap<String, Object>();
		// verify
		if (null == username || null == longtude || null == latitude) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		// get the member state
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				String cpwd = mem.getPword();
				if (!cpwd.equals(password)) {
					mrlt.put("code", "ParametersError");
					return mrlt;
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLatitude(latitude);
					loginLog.setLongitude(longtude);
					loginLog.setMid(mem.getId());
					plogService.addLogForMember(loginLog);
					mrlt.put("code", "AttendSuccessful");
					return mrlt;
				}
			} else {
				Plog loginLog = new Plog();
				loginLog.setLongitude(longtude);
				loginLog.setLatitude(latitude);
				loginLog.setMid(memID);
				plogService.addLogForMember(loginLog);
				mrlt.put("code", "LoginSuccessful");
				return mrlt;
			}
		}
	}

	@RequestMapping(value = "/normalLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginAction(@RequestBody Rqbody body) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
		String username = body.getUsername();
		String password = body.getPassword();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				String cpwd = mem.getPword();
				if (!cpwd.equals(password)) {
					mrlt.put("code", "ParametersError");
					return mrlt;
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLongitude("120.203081");
					loginLog.setLatitude("30.600326");
					loginLog.setMid(mem.getId());
					plogService.addLogForMember(loginLog);
					mrlt.put("code", "AttendSuccessful");
					return mrlt;
				}
			} else {
				Plog loginLog = new Plog();
				loginLog.setLongitude("120.203081");
				loginLog.setLatitude("30.600326");
				loginLog.setMid(memID);
				plogService.addLogForMember(loginLog);
				mrlt.put("code", "LoginSuccessful");
				return mrlt;
			}
		}

	}

	@RequestMapping(value = "/memberList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showMemberList(@RequestBody Rqbody body) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
		String username = body.getUsername();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		Pmember pm = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (memst.get("code").equals("OK")) {
			pm = (Pmember) memst.get("member");
		} else {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		}
		String memID = pmemberService.isLoginState(pm);
		if ("NotLogin" == memID) {
			mrlt.put("code", memID);
			return mrlt;
		} else {
			List<RelationMO> lstRMO1 = relationService.findReltionsByMember(memID);
			if (null == lstRMO1 || 0 == lstRMO1.size()) {
				mrlt.put("code", "SomeErrorAppeared");
				return mrlt;
			} else {
				String orgId = lstRMO1.get(0).getOid();
				List<Porganize> subOrgs = new ArrayList<Porganize>();
				porganizeService.ergodicSubOrganizes(orgId, subOrgs);
				if (null == subOrgs || 0 == subOrgs.size()) {
					mrlt.put("code", "SomeErrorAppeared");
					return mrlt;
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
					mrlt.put("result", mvlst);
					mrlt.put("code", "successfully");
					return mrlt;
				}
			}
		}

	}

	@RequestMapping(value = "/memberLogs", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> ShowMemberLogs(@RequestBody Rqbody body) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
		String username = body.getUsername();
		String memid = body.getMemid();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				mrlt.put("code", "PermissionBanished");
				return mrlt;
			} else {
				List<Plog> mLoglist = plogService.queryLogsByMember(memid);
				mrlt.put("code", "OK");
				mrlt.put("logs", mLoglist);
				return mrlt;
			}
		}
	}

	@RequestMapping(value = "/showMenu", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> showMenus(@RequestBody Rqbody body) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
		String username = body.getUsername();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		Pmember mem = new Pmember();
		Map<String, Object> memst = pmemberService.checkUserState(username);
		if (!memst.get("code").equals("OK")) {
			mrlt.put("code", memst.get("code"));
			return mrlt;
		} else {
			mem = (Pmember) memst.get("member");
			String memID = pmemberService.isLoginState(mem);
			if (null == memID || "NotLogin" == memID) {
				mrlt.put("code", "PermissionBanished");
				return mrlt;
			} else {
				List<Power> rplist = relationService.lstMenuByMemberId(memID);
				mrlt.put("code", "getMenuSuccess");
				mrlt.put("menu", rplist);
				return mrlt;
			}
		}
	}
}
