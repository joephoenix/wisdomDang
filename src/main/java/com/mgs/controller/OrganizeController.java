package com.mgs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mgs.entity.Pmember;
import com.mgs.entity.Porganize;
import com.mgs.entity.RelationMO;
import com.mgs.service.PmemberService;
import com.mgs.service.PorganizeService;
import com.mgs.service.RelationMOService;
import com.mgs.view.OrgView;

@Controller
public class OrganizeController {

	@Autowired
	private PmemberService pmemberService;

	@Autowired
	private PorganizeService porganizeService;

	@Autowired
	private RelationMOService relationMOService;

	@RequestMapping("/viewOrgList,method=RequestMethod.POST")
	@ResponseBody
	public Map<String, Object> viewOrganizeList(String username) {
		Map<String, Object> mrlt = new HashMap<String, Object>();
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
				List<RelationMO> lstRMO1 = relationMOService.queryReltionsByMember(memID);
				if (null == lstRMO1 || 0 == lstRMO1.size()) {
					mrlt.put("code", "SomeErrorAppeared");
					return mrlt;
				} else {
					String orgId = lstRMO1.get(0).getOid();
					List<Porganize> subOrgs = porganizeService.ergodicSubOrganizes(orgId);
					if (null == subOrgs || 0 == subOrgs.size()) {
						mrlt.put("code", "SomeErrorAppeared");
						return mrlt;
					} else {
						List<OrgView> ovList = new ArrayList<OrgView>();
						for (Porganize org : subOrgs) {
							OrgView rov = new OrgView();
							rov.setOrgId(org.getId());
							rov.setOrgName(org.getOname());
							rov.setOrgOrder(org.getOorder());
							ovList.add(rov);
						}
						mrlt.put("result", ovList);
						mrlt.put("code", "successfully");
						return mrlt;
					}
				}
			}
		}
	}
}
