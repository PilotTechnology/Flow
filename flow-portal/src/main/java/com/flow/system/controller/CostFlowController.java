package com.flow.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flow.portal.controller.BaseController;
import com.flow.pub.common.Constant;
import com.flow.pub.util.PageUtil;
import com.flow.system.bean.UserInfo;
import com.flow.system.model.CostFlow;
import com.flow.system.model.Distributor;
import com.flow.system.service.CostFlowService;
import com.flow.system.service.DistributorService;

/**
 * 
 * @Description:资金流水列表
 * 
 */
@Controller
@RequestMapping("/portal")
public class CostFlowController extends BaseController {
	@Autowired
	private CostFlowService costFlowService;
	
	@Autowired
	private DistributorService distributorService;
	
	/**
	 * 资金流水分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "costflow!selectPage.action")
	public String selectPage(HttpServletRequest request, CostFlow costFlow, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		
		UserInfo loginUserInfo = (UserInfo) request.getSession().getAttribute("userInfo");
		Distributor distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
		if (loginUserInfo.getRoleCode().equals(Constant.DISTRIBUTOR_ROLE_CODE)) {
			distributor = distributorService.getDistributorByUserCode(loginUserInfo.getUserCode());
			map.put("distributorCodeScope", distributor.getDistrbutorCode());
		} else if (loginUserInfo.getRoleCode().equals(Constant.SON_DISTRIBUTOR_ROLE_CODE)) {
			map.put("distributorCode", distributor.getDistrbutorCode());
		}
		
		PageUtil<CostFlow> page = costFlowService.listPage(map);
		model.addAttribute("page",page);
		model.addAttribute("costFlow", costFlow);
		return "/view/costflow/costFlowList.jsp";
	}
}
