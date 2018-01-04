package com.offcn.bean;

import java.util.List;

public class SearchResult {
	
	
	private List<Product> productList;// 结果集列表
	private long recordCount;// 总记录数
	private long pageCount;// 总页数

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public long getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

}
