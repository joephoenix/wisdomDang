package com.mgs.dao;

import java.util.List;
import com.mgs.entity.MemberInfo;

public interface MemberInfoDAO {

	MemberInfo getMemberDetailByid(String id);

	List<MemberInfo> querMemberDetailBymid(String mid);

	List<MemberInfo> queryMembersByage(Integer age);

	List<MemberInfo> queryMembersBysex(Integer sex);

	List<MemberInfo> queryMembersByedu(String education);

	int generateMemberInformation(MemberInfo memberInfo);

	int refreshMemberInformation(MemberInfo memberInfo);

	int removeChooseMemberinfo(String id);

	int reviseChooseMemberinfo(String id);

}
