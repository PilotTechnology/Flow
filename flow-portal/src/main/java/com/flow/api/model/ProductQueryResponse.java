package com.flow.api.model;

import java.util.List;

import com.flow.pub.common.CodeConstants;

public class ProductQueryResponse {
	public static final ProductQueryResponse SUCCESS = new ProductQueryResponse(CodeConstants.SUCCESS,"成功！");
	public static final ProductQueryResponse SYS_ERR = new ProductQueryResponse(CodeConstants.SUCCESS,"系统错误！");
	
	private String code;
	private String msg;
	private List<ProductForApi> productList;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ProductForApi> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductForApi> productList) {
		this.productList = productList;
	}

	public ProductQueryResponse(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
}
