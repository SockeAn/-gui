package com.taotao.rest.dao;

public interface JedisClient {
	
	//获取
	String get(String key);
	
	//插入
	String set(String key,String value);
	
	//获取hash
	String hget(String hkey,String key);
	
	//插入hash
	Long hset(String hkey,String key,String value);
	
	//返回长整型
	long incr(String key);
	
	//设置有效时间
	long expire(String key,int second);
	
	//
	long ttl(String key);
	
	//删除key
	long del(String key);
	
	long hdel(String hkey,String key);

}
