package com.mgs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mgs.entity.Pmember;

public interface PmemberDAO {

	/**
	 * find a member information by primary key
	 * 
	 * @param id
	 * @return
	 */
	Pmember getMemInfoByPrimaryKey(@Param("id") String id);

	/**
	 * get a list of all members
	 * 
	 * @return
	 */
	List<Pmember> queryEntireMembers();

	List<Pmember> queryAbandonMembers();

	/**
	 * get a list of members by the condation of uname, it's support Fuzzy Lookup
	 * 
	 * @param un
	 * @return
	 */
	List<Pmember> queryMembersByUsername(String un);

	/**
	 * get a list of members by the condation of rname, it's support Fuzzy Lookup
	 * 
	 * @param rn
	 * @return
	 */
	List<Pmember> queryMembersByRealname(@Param("rn") String rn);

	/**
	 * add a new member or registered
	 * 
	 * @param pm
	 * @return
	 */
	int addNewMember(Pmember pm);

	/**
	 * modify the uname of a member
	 * 
	 * @param id
	 * @param uname
	 * @return
	 */
	int modifyMemberUsername(@Param("id") String id, @Param("uname") String uname);

	/**
	 * modify the password of a member
	 * 
	 * @param id
	 * @param pword
	 * @return
	 */
	int modifyMemberPassword(@Param("id") String id, @Param("pword") String pword);

	int moveMemberById(@Param("id") String id);

	int recoverMemberById(@Param("id") String id);

}
