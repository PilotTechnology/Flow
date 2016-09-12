package com.flow.api.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.pub.common.CodeConstants;
import com.flow.system.mapper.DistributorMapper;
import com.flow.system.mapper.OrderMapper;
import com.flow.system.mapper.ProductMapper;
import com.flow.system.mapper.RefundFlowMapper;
import com.flow.system.model.Order;
import com.flow.system.model.Product;
import com.flow.system.model.RefundFlow;
@Service
public class OrderUtil {
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	RefundFlowMapper refundFlowMapper;
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	DistributorMapper distributorMapper;
	
	public void updateOrder(int state, String orderCode, String providerOrderCode, String code, String msg) {
		Order order = orderMapper.selectByOrderCode(orderCode);
		order.setState(state);
		order.setOrderCode(orderCode);
		order.setProviderOrderCode(providerOrderCode);
		order.setCallbackCode(code);
		order.setCallbackCodeMess(msg);
		if(!CodeConstants.SUCCESS.equals(code)){
			order.setErrorTime(order.getErrorTime()+1);
		}
		
		orderMapper.updateByOrderCodeSelective(order);
	}
	
	public void refund(String orderCode){
		Order order = orderMapper.selectByOrderCode(orderCode);
		Product product = productMapper.selectByProductCode(order.getProductCode());
		
		RefundFlow refund = new RefundFlow();
		refund.setDistributorName(order.getDistributorName());
		refund.setProviderName(order.getProviderCode());
		refund.setPhone(order.getPhone());
		refund.setOrderCode(orderCode);
		refund.setProdcutName(product.getProdcutName());
		refund.setPurchased(order.getPurchased().doubleValue());
		refund.setProviderIsRefund(CodeConstants.PEOVIDER_IS_REFUNDED);
		refund.setCreateDate(new Date());
		
		refundFlowMapper.insert(refund);
		
		distributorMapper.addBalance(order.getDistributorCode(), order.getPurchased().doubleValue());
	}
}
