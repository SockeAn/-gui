package com.taotao.rest.jedis;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	
	@Test
	public void addDocument()  throws Exception{
		//创建连接
		SolrServer server  = new HttpSolrServer("http://192.168.12.130:8080/solr");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "test001");
		document.addField("item_title", "测试商品2");
		document.addField("item_price", 12345);
		//把文档对象写入索引库
		server.add(document);
		//提交
		server.commit();
	}
	
	@Test
	public void deleteDocument() throws Exception{
		//创建连接
		SolrServer server  = new HttpSolrServer("http://192.168.12.130:8080/solr");
		server.deleteById("test001");
//		server.de
		server.commit();
	}
	
	@Test
	public void getDocument() throws Exception {
		//创建连接
		SolrServer server  = new HttpSolrServer("http://192.168.12.130:8080/solr");
		//创建一个查询对象
		SolrQuery query = new SolrQuery();
		//设置条件
		query.setQuery("*:*");
		//设置查询起始位置
		query.setStart(20);
		//设置每页展示条目
		query.setRows(50);
		//执行查询
		QueryResponse response = server.query(query);
		//从中取出查询结果
		SolrDocumentList documentList = response.getResults();
		//查询记录数
		System.out.println(documentList.getNumFound());
		//遍历documentList 取出结果
		for (SolrDocument solrDocument : documentList) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
		}
	}
}
