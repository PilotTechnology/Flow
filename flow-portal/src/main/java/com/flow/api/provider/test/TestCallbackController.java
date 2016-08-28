package com.flow.api.provider.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flow.api.util.OrderUtil;
import com.flow.pub.common.CodeConstants;

@RestController
@RequestMapping(value = "/api/callback")
public class TestCallbackController {
	
	@RequestMapping(value = "/test.do", method = RequestMethod.GET)
	@ResponseBody
	public TestCallbackResponse callback(TestCallbackRequest req){
		OrderUtil orderUtil = new OrderUtil();
		if (req.getCode().equals("0000")) {
			orderUtil.updateOrder(CodeConstants.ORDER_STATE_SUCC, req.getOrder_id(), req.getP_order_id(), req.getCode(), req.getMsg());
		} else {
			orderUtil.updateOrder(CodeConstants.ORDER_STATE_ERR, req.getOrder_id(), req.getP_order_id(), req.getCode(), req.getMsg());
			orderUtil.refund(req.getOrder_id());
		}
		
		return TestCallbackResponse.SUCCESS;
	}
}