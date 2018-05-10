package com.mgs.dao;

import java.util.List;

import com.mgs.entity.MessageRecord;

public interface MessageRecordDAO {

	MessageRecord getDetail4mr(String id);

	List<MessageRecord> queryMessage4member(String mid);

	List<MessageRecord> queryMessageForward(String rsid);

	List<MessageRecord> queryMessage4memberReaded(String mid);

	int generateMessageRecord(MessageRecord mr);

	int adjustMessage2Unread(String id);

	int adjustMessage2Read(String id);

	int adjustMessage2Trash(String id);
	
	int moveChooseMessage(String id);

}
