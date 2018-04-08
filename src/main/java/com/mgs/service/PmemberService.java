package com.mgs.service;

import java.util.List;

import com.mgs.entity.Pmember;

public interface PmemberService {

	public List<Pmember> getMembersByCondition(String condition);

	public Pmember getMemberInformation(String id);
}
