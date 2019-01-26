package com.taotao.rest.service;

import com.taotao.common.util.TaotaoResult;

public interface RedisService {
	//同步内容
	TaotaoResult syncContent(long contentCid);

}
