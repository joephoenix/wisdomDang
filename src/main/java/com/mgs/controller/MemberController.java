package com.mgs.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgs.entity.Plog;
import com.mgs.entity.Pmember;
import com.mgs.entity.RelationMO;
import com.mgs.service.PlogService;
import com.mgs.service.PmemberService;
import com.mgs.service.RelationMOService;
import com.mgs.view.MemberView;

@Controller
public class MemberController {

	@Autowired
	private PmemberService pmemberService;

	@Autowired
	private PlogService plogService;
	
	@Autowired
	private RelationMOService relationMOService;

	@RequestMapping("/login,method=RequestMethod.POST")
	@ResponseBody
	public String appLoginAction(String username, String password, String longtude, String latitude) {
		if (null == username || null == longtude || null == latitude) {
			return "ParamentsError";
		}
		List<Pmember> lstMember = pmemberService.queryMembersByCondition(username);
		if (null == lstMember || 0 < lstMember.size()) {
			return "MemberNotExist";
		} else {
			Pmember mem = lstMember.get(0);
			Plog lastLog = plogService.getLastLog4ChooseMember(mem.getId());
			Date now = new Date();
			int seconds = (int) (now.getTime() - lastLog.getRecordtm().getTime() / 1000 / 60);
			if (seconds > 30) {
				Plog loginLog = new Plog();
				loginLog.setLatitude(latitude);
				loginLog.setLongitude(longtude);
				loginLog.setMid(mem.getId());
				plogService.addLogForMember(loginLog);
				return "successful";
			} else {
				String cpwd = mem.getPword();
				if (!cpwd.equals(password)) {
					return "MemberNotExist";
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLatitude(latitude);
					loginLog.setLongitude(longtude);
					loginLog.setMid(mem.getId());
					plogService.addLogForMember(loginLog);
					return "successful";
				}
			}
		}
	}

	@RequestMapping("/login,method=RequestMethod.POST")
	@ResponseBody
	public String loginAction(String username, String password) {
		if (null == username) {
			return "ParamentsError";
		}
		List<Pmember> lstMember = pmemberService.queryMembersByCondition(username);
		if (null == lstMember || 0 < lstMember.size()) {
			return "MemberNotExist";
		} else {
			Pmember mem = lstMember.get(0);
			Plog lastLog = plogService.getLastLog4ChooseMember(mem.getId());
			Date now = new Date();
			int seconds = (int) (now.getTime() - lastLog.getRecordtm().getTime() / 1000 / 60);
			if (seconds > 30) {
				Plog loginLog = new Plog();
				loginLog.setLongitude("120.203081");
				loginLog.setLatitude("30.600326");
				loginLog.setMid(mem.getId());
				plogService.addLogForMember(loginLog);
				return "successful";
			} else {
				String cpwd = mem.getPword();
				if (!cpwd.equals(password)) {
					return "MemberNotExist";
				} else {
					// record login log
					Plog loginLog = new Plog();
					loginLog.setLongitude("120.203081");
					loginLog.setLatitude("30.600326");
					loginLog.setMid(mem.getId());
					plogService.addLogForMember(loginLog);
					return "successful";
				}
			}
		}
	}

	@RequestMapping("/memberList, method=RequestMethod.POST")
	@ResponseBody
	public Map<String, Object> viewMemberList(String username) {
		Map<String,Object> mrlt = new HashMap<String ,Object>();
		if (null == username) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		}
		List<Pmember> lstMember = pmemberService.queryMembersByCondition(username);
		if (null == lstMember || 0 < lstMember.size()) {
			mrlt.put("code", "ParametersError");
			return mrlt;
		} else {
			Pmember mem = lstMember.get(0);
			String memID = mem.getId();
			Plog lastLog = plogService.getLastLog4ChooseMember(memID);
			Date now = new Date();
			int seconds = (int) (now.getTime() - lastLog.getRecordtm().getTime() / 1000 / 60);
			if (seconds > 30) {
				mrlt.put("code", "ParametersError");
				return mrlt;
			} else {
				List<MemberView> mvlst = new ArrayList<MemberView>();
				List<RelationMO> lstRMO1 =  relationMOService.queryReltionsByMember(memID);
				String orgId = lstRMO1.get(0).getOid();
				List<RelationMO> lstRMO2 = relationMOService.queryReltionsByOrganize(orgId);
				for(RelationMO rmo : lstRMO2) {
					Pmember pm = pmemberService.getMemberInformation(rmo.getMid());
					Plog lg = plogService.getLastLog4ChooseMember(rmo.getMid());
					MemberView mv = new MemberView();
					mv.setLatitude(lg.getLatitude());
					mv.setLongtude(lg.getLongitude());
					mv.setMid(rmo.getId());
					mv.setRecordtm(lg.getRecordtm());
					mv.setRname(pm.getRname());
					mvlst.add(mv);
				}
				mrlt.put("result", mvlst);
				mrlt.put("code", "successfully");
				return mrlt;
			}
		}
	}

	@RequestMapping("/aixes")
	@ResponseBody
	public List<Plog> viewAixes() {
		return plogService.queryEntireLogsOfDaily();
	}

}
