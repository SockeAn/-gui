package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.util.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.ItemCatService;

@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService catService;
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/itemcat/list",produces =
	 * MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8") public String
	 * getItemCatList(String callback) { CatResult catList =
	 * catService.getItemCatList(); ///把pojo转换成字符串 String json =
	 * JsonUtils.objectToJson(catList); //拼装返回值 String result = callback + "(" +json
	 * +");"; return result; }
	 */
	
	@ResponseBody
	@RequestMapping("/itemcat/list")
	public Object getItemCatList(String callback) {
		CatResult catList = catService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catList);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
	
}
