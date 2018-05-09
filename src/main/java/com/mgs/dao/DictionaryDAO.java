package com.mgs.dao;

import java.util.List;

import com.mgs.entity.Dictionary;

public interface DictionaryDAO {

	Dictionary getDictionaryInfoByid(String id);

	List<Dictionary> queryDictListBykey(String key);

	List<Dictionary> queryEntireDictionarys();

	List<Dictionary> queryAbandonDictionarys();

	int generateNewDictionary(Dictionary dict);

	int adjustDictionarValue(String key, String id);

	int removeChooseDictionary(String id);

	int reviseChooseDictionary(String id);

}
