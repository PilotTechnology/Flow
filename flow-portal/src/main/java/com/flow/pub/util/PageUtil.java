package com.flow.pub.util;

import java.util.List;

public class PageUtil<T> {

	private Integer page = 1; 	   // 当前页码
	private Integer prePage ;
	private Integer nextPage;
	private Integer pageSize = 10; // 每页记录数
	private Long records = 0L;     // 总记录数
	private Integer totalPage = 0; // 总页数
	private Integer firstResult;   // first pagenum 显示的第一条记录是多少[开始条数]
	private List<T> rows;		   // 数据List
	public Integer getPage() {
		return page;
	}
	
	public Integer getPrePage() {
		if(page <= 1){
			prePage = 1;
		}else{
			prePage = page - 1;
		}
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}
	
	public Integer getNextPage() {
		if(page >= totalPage){
			nextPage = totalPage;
		}else{
			nextPage = page + 1;
		}
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public void setPage(Integer page) {
		if (page < 1) {
			page = 1;
		}
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1) {
			pageSize = 1;
		}
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		totalPage = records.intValue() / pageSize;
		if (records % pageSize > 0) {
			totalPage++;
		}
		return totalPage;
	}

	public void setTotal(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public Integer getFirstResult() {
		firstResult = (page - 1) * pageSize; 
		return firstResult;
	}
	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "PageUtil [page=" + page + ", pageSize=" + pageSize + ", records=" + records + ", totalPage=" + totalPage
				+ ", firstResult=" + firstResult + ", rows=" + rows + "]";
	}

}
