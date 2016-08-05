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
import com.flow.system.model.CostFlow;
import com.flow.system.model.RefundFlow;
import com.flow.system.service.CostFlowService;
import com.flow.system.service.DistributorService;
import com.flow.system.service.RefundFlowService;

/**
 * 
 * @Description:退款单管理
 * 
 */
@Controller
@RequestMapping("/portal")
public class RefundFlowController extends BaseController {
	@Autowired
	private RefundFlowService refundFlowService;
	@Autowired
	private CostFlowService costFlowService;
	@Autowired
	private DistributorService distributorService;
	
	/**
	 * 退款单分页列表
	 * @param request
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "refundflow!selectPage.action")
	public String selectPage(HttpServletRequest request, RefundFlow refundFlow, Model model) throws Exception {
		//转换request参数为map
		Map<String,Object> map = getParameterMap(request);
		PageUtil<RefundFlow> page = refundFlowService.listPage(map);
		model.addAttribute("page",page);
		model.addAttribute("refundFlow",refundFlow);
		model.addAttribute("beginTime",map.get("beginTime"));
		model.addAttribute("endTime",map.get("endTime"));
		return "/view/refundflow/refundFlowList.jsp";
	}
	
	/**
	 * 新增退款单
	 * @param request
	 * @param RefundFlow
	 * @return
	 */
	@RequestMapping(value = "refundFlow!addRefundFlow.action")
	@ResponseBody
	public Object addRefundFlow(HttpServletRequest request, RefundFlow refundFlow){
		try {
			if(refundFlowService.checkExists(refundFlow)){
				return new BaseResponse(Constant.JSON_FAIL, "退款单已存在");
			}
			refundFlowService.save(refundFlow);
			distributorService.addBalance(refundFlow.getDistributorName(), refundFlow.getPurchased());
			CostFlow costFlow = new CostFlow();
			costFlow.setOrderCode(refundFlow.getOrderCode());
			costFlow.setDistributorCode(refundFlow.getDistributorName());
			costFlow.setCost(refundFlow.getPurchased());
			costFlow.setCurrentBalance(distributorService.getBalance(refundFlow.getDistributorName()));
			costFlow.setType(1);
			costFlowService.save(costFlow);
		} catch (Exception e) {
			PubLog.error("新增供应商失败 : >> "+refundFlow, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	/**
	 * 修改退款单
	 * @param request
	 * @param RefundFlow
	 * @return
	 */
	@RequestMapping(value = "refundFlow!editRefundFlow.action")
	@ResponseBody
	public Object editRefundFlowService(HttpServletRequest request, RefundFlow refundFlow){
		try {
			refundFlowService.update(refundFlow);
		} catch (Exception e) {
			PubLog.error("修改角色失败 : >> "+refundFlow, e);
			return new BaseResponse(Constant.JSON_FAIL, e.getMessage());
		}
		return Constant.successMsg;
	}
	
	@RequestMapping(value = "refundFlow!toSearch.action")
	public RefundFlow toSearch(HttpServletRequest request, RefundFlow refundFlow){
		return refundFlowService.getRefundFlowByPrimaryKey(refundFlow.getId());
	}
}
