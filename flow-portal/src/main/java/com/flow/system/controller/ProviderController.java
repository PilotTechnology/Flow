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
import com.flow.system.model.Provider;
import com.flow.system.service.ProviderService;

/**
 * 
 * @Description:供应商管理
 * 
 */
@Controller
@RequestMapping("/portal")
public class ProviderController extends BaseController {

	@Autowired
	private ProviderService providerService;
	
	/**
	 * 查询供应商分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "provider!selectPage.action")
	public String selectPage(HttpServletRequest request, Provider provider, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<Provider> page = providerService.listPage(map);
		model.addAttribute("page",page);
		model.addAttribute("provider", provider);
		return "/view/provider/providerList.jsp";
	}
	
	/**
	 * 新增供应商
	 * @param request
	 * @param Provider
	 * @return
	 */
	@RequestMapping(value = "provider!addProvider.action")
	@ResponseBody
	public Object addProvider(HttpServletRequest request, Provider provider){
		try {
			
			if(providerService.checkExists(provider)){
				return new BaseResponse(Constant.JSON_FAIL, "供应商已存在");
			}
			providerService.save(provider);
		} catch (Exception e) {
			PubLog.error("新增供应商失败 : >> "+provider, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	/**
	 * 修改供应商
	 * @param request
	 * @param Provider
	 * @return
	 */
	@RequestMapping(value = "provider!editProvider.action")
	@ResponseBody
	public Object editProvider(HttpServletRequest request, Provider provider){
		try {
			providerService.update(provider);
		} catch (Exception e) {
			PubLog.error("修改角色失败 : >> "+provider, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "provider!toEdit.action")
	public Provider toEdit(HttpServletRequest request, Provider provider){
		return providerService.getProviderByCode(provider.getProviderCode());
	}
	
//	/**
//	 * 删除供应商
//	 * @param request
//	 * @param Provider
//	 * @return
//	 */
//	@RequestMapping(value = "provider!delProvider.action")
//	@ResponseBody
//	public Object delProvider(HttpServletRequest request,Provider provider) {
//		providerService.delete(provider);
//		return Constant.successMsg;
//
//	}
}
