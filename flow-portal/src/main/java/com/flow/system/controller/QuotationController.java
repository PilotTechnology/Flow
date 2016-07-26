package com.flow.system.controller;

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
import com.flow.system.model.Quotation;
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
	
	/**
	 * 报价单分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "quotation!selectPage.action")
	public String selectPage(HttpServletRequest request, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<Quotation> page = quotationService.listPage(map);
		model.addAttribute("page",page);
		return "/view/quotation/quotationList.jsp";
	}
	
	/**
	 * 新增报价单
	 * @param request
	 * @param Provider
	 * @return
	 */
	@RequestMapping(value = "quotation!addQuotation.action")
	@ResponseBody
	public Object addProvider(HttpServletRequest request, Quotation quotation){
		try {
			
			if(quotationService.checkExists(quotation)){
				return new BaseResponse(Constant.JSON_FAIL, "供应商已存在");
			}
			quotationService.save(quotation);
		} catch (Exception e) {
			PubLog.error("新增报价单失败 : >> "+quotation, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	/**
	 * 修改报价单
	 * @param request
	 * @param Quotation
	 * @return
	 */
	@RequestMapping(value = "quotation!editQuotation.action")
	@ResponseBody
	public Object editProvider(HttpServletRequest request, Quotation quotation){
		try {
			if(quotationService.checkExists(quotation)){
				return new BaseResponse(Constant.JSON_FAIL, "报价单已存在");
			}
			quotationService.update(quotation);
		} catch (Exception e) {
			PubLog.error("修改报价单失败 : >> "+quotation, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "quotation!toEdit.action")
	public Quotation toEdit(HttpServletRequest request, Quotation quotation){
		return quotationService.getQuotationByCode(quotation.getServiceCode());
	}
}
