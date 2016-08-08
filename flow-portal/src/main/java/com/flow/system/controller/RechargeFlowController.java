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
import com.flow.system.bean.UserInfo;
import com.flow.system.model.RechargeFlow;
import com.flow.system.service.DistributorService;
import com.flow.system.service.RechargeFlowService;

@Controller
@RequestMapping("/portal")
public class RechargeFlowController extends BaseController {
	@Autowired
	private RechargeFlowService rechargeFlowService;
	@Autowired
	private DistributorService distributorService;
	
	/**
	 * 充值分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "rechargeflow!selectPage.action")
	public String selectPage(HttpServletRequest request, RechargeFlow rechargeFlow, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<RechargeFlow> page = rechargeFlowService.listPage(map);
		model.addAttribute("page",page);
		model.addAttribute("rechargeFlow",rechargeFlow);
		return "/view/rechargeflow/rechargeFlowList.jsp";
	}
	
	/**
	 * 新增充值记录
	 * @param request
	 * @param RechargeFlow
	 * @return
	 */
	@RequestMapping(value = "rechargeFlow!addRechargeFlow.action")
	@ResponseBody
	public Object addRechargeFlow(HttpServletRequest request, RechargeFlow rechargeFlow){
		try {
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			rechargeFlow.setBalanceAfterRecharge(rechargeFlow.getBalanceBeforeRecharge()+rechargeFlow.getRecharge());
			rechargeFlow.setUserCode(userInfo.getUserCode());
			rechargeFlowService.save(rechargeFlow);
			distributorService.addBalance(rechargeFlow.getDistributorCode(), rechargeFlow.getRecharge());
		} catch (Exception e) {
			PubLog.error("新增供应商失败 : >> "+rechargeFlow, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
}
