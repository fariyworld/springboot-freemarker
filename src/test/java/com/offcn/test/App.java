package com.offcn.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class App {

	private static final String url = "http://10.0.0.229:8983/solr/hellocore/";

	@Test
	public void serach() {

		HttpSolrClient httpSolrClient = new HttpSolrClient(url);

		// 定义查询内容 （列名:关键字）
		SolrQuery query = new SolrQuery("product_name:幸福"); // 定义查询内容
		query.setStart(0);// 起始游标位置
		query.setRows(10);// 返回的结果集数量
		try {
			
			QueryResponse response = httpSolrClient.query(query);
			
			SolrDocumentList results = response.getResults();
			System.out.println(results.getNumFound());// 查询总条数
			for (SolrDocument doc : results) {
				System.out.println(doc);
			}
		} catch (SolrServerException | IOException e) {
			System.out.println(e.getMessage());
		}finally{
			
			try {
				httpSolrClient.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
