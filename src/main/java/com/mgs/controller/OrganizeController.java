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
import com.mgs.entity.Porganize;
import com.mgs.entity.RelationMO;
import com.mgs.service.PmemberService;
import com.mgs.service.PorganizeService;
import com.mgs.service.RelationMOService;
import com.mgs.view.OrgView;
import com.mgs.view.Rqbody;

@Controller
public class OrganizeController {

	@Autowired
	private PmemberService pmemberService;

	@Autowired
	private PorganizeService porganizeService;

	@Autowired
	private RelationMOService relationMOService;

	@RequestMapping(value = "/showSubOrgs", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showSubOrgs(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		String fOrgid = body.getfOrgid();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			List<Porganize> subOrgs = new ArrayList<Porganize>();
			porganizeService.ergodicSubOrganizes(fOrgid, subOrgs);
			if (null == subOrgs || 0 == subOrgs.size()) {
				jsonObject.put("message", "SomeErrorAppeared");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				List<OrgView> ovList = new ArrayList<OrgView>();
				for (Porganize org : subOrgs) {
					OrgView rov = new OrgView();
					rov.setOrgId(org.getId());
					rov.setOrgName(org.getOname());
					rov.setOrgOrder(org.getOorder());
					ovList.add(rov);
				}
				jsonObject.put("result", ovList);
				jsonObject.put("message", "showSubOrgs");
				jsonObject.put("status", "success");
				return jsonObject;
			}
		} else {
			return jsonObject;
		}
	}

	@RequestMapping(value = "/showOrgList", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public JSONObject showOrgList(@RequestBody Rqbody body) {
		JSONObject jsonObject = new JSONObject();
		String username = body.getUsername();
		jsonObject = pmemberService.judgeLoginState(username);
		if (jsonObject.get("status") == "success") {
			String membid = jsonObject.getString("result");
			List<RelationMO> lstRMO1 = relationMOService.queryReltionsByMember(membid);
			if (null == lstRMO1 || 0 == lstRMO1.size()) {
				jsonObject.put("message", "SomeErrorAppeared");
				jsonObject.put("status", "error");
				return jsonObject;
			} else {
				List<OrgView> ovList = new ArrayList<OrgView>();
				String orgId = lstRMO1.get(0).getOid();
				Porganize fOrg = porganizeService.getOrganizeInfo(orgId);
				OrgView fov = new OrgView();
				fov.setOrgId(fOrg.getId());
				fov.setOrgName(fOrg.getOname());
				fov.setOrgOrder(fOrg.getOorder());
				ovList.add(fov);
				List<Porganize> subOrgs = new ArrayList<Porganize>();
				porganizeService.ergodicSubOrganizes(orgId, subOrgs);
				if (null == subOrgs || 0 == subOrgs.size()) {
					jsonObject.put("message", "SomeErrorAppeared");
					jsonObject.put("status", "error");
					return jsonObject;
				} else {
					for (Porganize org : subOrgs) {
						OrgView rov = new OrgView();
						rov.setOrgId(org.getId());
						rov.setOrgName(org.getOname());
						rov.setOrgOrder(org.getOorder());
						ovList.add(rov);
					}
					jsonObject.put("result", ovList);
					jsonObject.put("message", "getEntireOrgs");
					jsonObject.put("status", "success");
					return jsonObject;
				}
			}

		} else {
			return jsonObject;
		}
	}
}
