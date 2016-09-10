package com.flow.system.controller;

import java.util.Date;
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
import com.flow.pub.common.KeyGenerate;
import com.flow.pub.common.PubLog;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.mapper.QuotationMapper;
import com.flow.system.model.Distributor;
import com.flow.system.model.ProductForDistributor;
import com.flow.system.model.Quotation;
import com.flow.system.service.DistributorService;
import com.flow.system.service.ProductForDistributorService;
import com.flow.system.service.QuotationService;

/**
 * 
 * @Description:报价单管理
 * 
 */
@Controller
@RequestMapping("/portal")
public class QuotationController extends BaseController {
	@Autowired
	private QuotationService quotationService;
	
	@Autowired
	private ProductForDistributorService productForDistributorService;
	
	@Autowired
	private DistributorService distributorService;
	
	@Autowired
	private QuotationMapper quotationMapper;

	/**
	 * 报价单分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "quotation!selectPage.action")
	public String selectPage(HttpServletRequest request, Quotation quotation, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<Quotation> page = null;
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		if(user!=null && Constant.ADMIN_ROLE_CODE.equals(user.getRoleCode())){//管理员可以查看所有的报价单
			page = quotationService.listPage(map);
		}else if(Constant.DISTRIBUTOR_ROLE_CODE.equals(user.getRoleCode())){//经销商可以查看二级分销商的报价单
			map.put("fatherCode",user.getUserCode());
			page = quotationService.sonListPage(map);
		}
		model.addAttribute("page",page);
		model.addAttribute("quotation",quotation);
		return "/view/quotation/quotationList.jsp";
	}
	
	/**
	 * 我的报价单，流量包列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "quotation!productForDistributor.action")
	public String productForDistributor(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<ProductForDistributor> page = null;
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		if(Constant.DISTRIBUTOR_ROLE_CODE.equals(user.getRoleCode()) || Constant.SON_DISTRIBUTOR_ROLE_CODE.equals(user.getRoleCode())){//经销商可以查看二级分销商的报价单
			Distributor distributor = distributorService.getDistributorByUserCode(user.getUserCode());
			Quotation quotation = quotationMapper.getQuotationByDistributorCode(distributor.getDistrbutorCode());
			if (quotation != null) {
				map.put("quotationCode", quotation.getServiceCode());
				page = productForDistributorService.listPage(map);
				model.addAttribute("page",page);
			}
			return "/view/quotation/productsForDistributor.jsp";
		} else {
			return "/portal/index.jsp";
		}
		
		
	}
	
	/**
	 * 新增报价单
	 * @param request
	 * @param Provider
	 * @return
	 */
	@RequestMapping(value = "quotation!addQuotation.action")
	@SuppressWarnings("unchecked")
	@ResponseBody
	public Object addProvider(HttpServletRequest request){
		//接收请求参数
		Quotation quotation = new Quotation();
		Map<String,String> map = getParameterMap(request);
		quotation.setCreateDate(new Date());
		quotation.setState(1);//默认激活
		quotation.setDistributorCode(map.get("distributorCode"));
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		if(user!=null && Constant.ADMIN_ROLE_CODE.equals(user.getRoleCode())){//管理员创建的报价单
			quotation.setFatherCode("");
		}else if(Constant.DISTRIBUTOR_ROLE_CODE.equals(user.getRoleCode())){//经销商创建的报价单
			quotation.setFatherCode(user.getDistributorCode());
		}
		
		quotation.setIsDisplayProvince(Integer.parseInt(map.get("display_province")));
		quotation.setServiceCode(KeyGenerate.getServiceCode(quotation.getDistributorCode()));
		String products = map.get("products"); //eg:proCode1_disCount1,proCode2_disCount2,
		try {
//			if(quotationService.checkExists(quotation)){
//				return new BaseResponse(Constant.JSON_FAIL, "供应商已存在");
//			}
			quotationService.save(quotation,products);
		} catch (Exception e) {
			PubLog.error("新增报价单失败 : >> "+quotation, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "quotation!get.action")
	@ResponseBody
	public Map<String,Object> getQuotation(HttpServletRequest request){
		String id = request.getParameter("id");
		Quotation quotation = quotationService.getQuotationByCode(id);
		
		List<Map<String,Object>> proList = quotationService.findProductsByServiceCode(id);
		Map<String,Object> result = new HashMap<>();
		result.put("quotation", quotation);
		result.put("products", proList);
		return result;
	}
	
	/**
	 * 修改报价单
	 * @param request
	 * @param Quotation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "quotation!editQuotation.action")
	@ResponseBody
	public Object editProvider(HttpServletRequest request){
		Map<String,String> map = getParameterMap(request);
		try {
			Quotation quotation = new Quotation();
			quotation.setServiceCode(map.get("serviceCode"));
			quotation.setId(Integer.parseInt(map.get("id")));
			quotation.setIsDisplayProvince(Integer.parseInt(map.get("display_province")));
			quotation.setState(Integer.parseInt(map.get("state_edit")));
			String products = map.get("products");
			if(quotationService.checkExists(quotation)){
				return new BaseResponse(Constant.JSON_FAIL, "报价单已存在");
			}
			quotationService.update(products,quotation);
		} catch (Exception e) {
			PubLog.error("修改报价单失败 : >> "+map, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "quotation!toEdit.action")
	public Quotation toEdit(HttpServletRequest request, Quotation quotation){
		return quotationService.getQuotationByCode(quotation.getServiceCode());
	}
}
