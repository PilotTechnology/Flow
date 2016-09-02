package com.flow.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flow.portal.controller.BaseController;
import com.flow.pub.common.BaseResponse;
import com.flow.pub.common.Constant;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.model.Product;
import com.flow.system.service.ProductService;

/**
 * 
 * @Description:流量包管理
 * 
 */
@Controller
@RequestMapping("/portal")
public class ProductController extends BaseController {
	@Autowired
	private ProductService productService;
	
	/**
	 * 流量包分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "product!selectPage.action")
	public String selectPage(HttpServletRequest request, Product product, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<Product> page = productService.listPage(map);
		model.addAttribute("page",page);
		model.addAttribute("product", product);
		return "/view/product/productList.jsp";
	}
	
	/**
	 * 流量包列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product!productList")
	@ResponseBody
	public Map<String,Object> productList(HttpServletRequest request, Product product, Model model) throws Exception {
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		List<Product> list = null;
		if(Constant.ADMIN_ROLE_CODE.equals(user.getRoleCode())){
			list = productService.findAllProduct();
		}else if(Constant.DISTRIBUTOR_ROLE_CODE.equals(user.getRoleCode())){
			list = productService.findProductByUserCode(user.getUserCode());
		}
		Map<String,Object> info = new HashMap<>();
		info.put("data", list);
	    info.put("recordsTotal", String.valueOf(list.size()));
	    info.put("recordsFiltered", String.valueOf(list.size()));
	    info.put("draw", "1");
		return info;
	}
	
	/**
	 * 新增流量包
	 * @param request
	 * @param Product
	 * @return
	 */
	@RequestMapping(value = "product!addProduct.action")
	@ResponseBody
	public Object addProduct(HttpServletRequest request, Product product){
		try {
			
			if(productService.checkExists(product)){
				return new BaseResponse(Constant.JSON_FAIL, "流量包已存在");
			}
			productService.save(product);
		} catch (Exception e) {
			PubLog.error("新增流量包失败 : >> "+product, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "product!toSearch.action")
	public Product toSearch(HttpServletRequest request, Product product){
		return productService.getProductByPrimaryKey(product.getId());
	}
	
	/**
	 * 修改流量包
	 * @param request
	 * @param Product
	 * @return
	 */
	@RequestMapping(value = "product!editProduct.action")
	@ResponseBody
	public Object editProvider(HttpServletRequest request, Product product){
		try {
			productService.update(product);
		} catch (Exception e) {
			PubLog.error("修改流量包失败 : >> "+product, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
}
