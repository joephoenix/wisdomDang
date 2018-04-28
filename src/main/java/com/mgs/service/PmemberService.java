package com.mgs.service;

import java.util.List;
import java.util.Map;

import com.mgs.entity.Pmember;

public interface PmemberService {

	/**
	 * 按照条件查询用户列表， 使用模糊查询则需要添加% %
	 * 
	 * @param condition
	 * @return
	 */
	public List<Pmember> queryMembersByCondition(String condition);

	public Pmember getMemberInformation(String id);

	public String addNewMember(Pmember npm);

	public Map<String, Object> checkUserState(String username);

	public String isLoginState(Pmember pmb);
}
