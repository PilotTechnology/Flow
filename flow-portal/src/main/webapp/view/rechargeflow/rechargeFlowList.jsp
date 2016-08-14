<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${request.pagecontext.contextpath}" var="ctx"/>
<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>流量平台</title>
    <%@include file="../common/common.jsp" %>
    <c:set value="${ctx}/portal/rechargeflow!selectPage.action" scope="page" var="url"/>
    <link rel="stylesheet" href="${ctx}/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
  </head>
  
  <body>
    <%@ include file="../common/header.jsp" %>
    <div id="container">
	    <!-- 左侧菜单 -->	    
        <div id="sidebar" class="sidebar-fixed">
       	  <div id="sidebar-content">
       	 	<%@include file="../common/nav.jsp"%>
       	  </div>
        </div>
       
				       
      	<div id="content">
          <div class="container">
		  
		  <!-- 内容区导航栏 -->          
          <div class="crumbs">
            <ul id="breadcrumbs" class="breadcrumb">
              <li><i class="icon-home"></i><a href="${ctx}/portal/system/login!selectPage.action">首页</a></li>
              <li class="current"><a href="${ctx}/portal/rechargeflow!selectPage.action" title="">账户充值</a></li>
            </ul>
          </div>
          
          <br/>
          
          <div class="row">
          	<div class="col-md-12">
          		<div class="widget box">
          			<div class="widget-header"><h4><i class="icon-search"></i>条件搜索</h4></div>
          			<div class="widget-content">
                  		<form id="searchForm" action="" method="post" class="form-horizontal row-border">
	                    <div class="form-group">
                  			<label class="col-md-1 control-label">分销商名称:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="distributorName" name="distributorName" value="${rechargeFlow.distributorName}"></div>
                  			<label class="col-md-1 control-label">类型:</label>
                  			<div class="col-md-2">
                  				<select name="type" id="type" class="form-control">
                  					<option value="-1">全部</option>
                              		<option value="1">正常加款</option>
                              		<option value="2">授信加款</option>
                              		<option value="3">赔付加款</option>
                              		<option value="4">运营补款</option>
                              		<option value="5">测试加款</option>
                              		<option value="6">返点加款</option>
                              		<option value="7">赠送加款</option>
                              		<option value="8">提现加款</option>
	                            </select>
                  			</div>
                  			<div class="col-md-2">
                  				<button class="btn btn-sm btn-warning" type="submit" id="search">搜索</button>
                  			</div>
                  		</div>
                  </form>
                </div>
          		</div>
          	</div>
          </div>
          
          <div class="row">
          	<div class="col-md-12">
          	  <div class="widget box">
          	  	<!--  表格导航栏 -->
          	  	<div class="widget-header">
                  <h4><i class="icon-reorder"></i>账户充值</h4>
                  <div class="toolbar no-padding">
                    <div class="btn-group">
                      <span class="btn btn-xs widget-collapse">
                        <i class="icon-angle-down"></i>
                      </span>
                    </div>
                  </div>
             	</div>
             	<!-- 表格内容  start-->
             	<div class="widget-content">
             	  
             	  <div class="row">
             	  	<div class="col-md-12">
             	  		<button class="btn btn-sm btn-warning" id="addButton">充值</button>
             	  	</div>
             	  </div>
             	  
                  <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th data-class="expand">序号</th>
                        <th>分销商名称</th>
						<th>充值前余额(元)</th>
						<th>充值金额(元)</th>
						<th>充值后余额(元)</th>
						<th>类型</th>
						<th>操作人</th>
						<th>创建时间</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="rechargeFlow" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${rechargeFlow.distributorName}</td>
                        <td>${rechargeFlow.balanceBeforeRecharge}</td>
                        <td>${rechargeFlow.recharge}</td>
                        <td>${rechargeFlow.balanceAfterRecharge}</td>
                        <td>
                      		<c:if test='${rechargeFlow.type eq 1}'>正常加款</c:if>
              	        	<c:if test='${rechargeFlow.type eq 2}'>授信加款</c:if>
               	        	<c:if test='${rechargeFlow.type eq 3}'>赔付加款</c:if>
                        	<c:if test='${rechargeFlow.type eq 4}'>运营补款</c:if>
                        	<c:if test='${rechargeFlow.type eq 5}'>测试加款</c:if>
                        	<c:if test='${rechargeFlow.type eq 6}'>返点加款</c:if>
                        	<c:if test='${rechargeFlow.type eq 7}'>赠送加款</c:if>
                        	<c:if test='${rechargeFlow.type eq 8}'>提现加款</c:if>
                        </td>
                        <td>${rechargeFlow.userName}</td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${rechargeFlow.createDate}"/></td>
                      	</tr>
                      </c:forEach>
                    </tbody>
                  </table>
                
				  <%@ include file="../common/page.jsp"%>                  
                </div>
                <!-- 表格内容  end-->
          	  </div>
          	</div>
          </div>
          </div>
      	
      </div>
    </div>
	
	<!-- add 弹框 -->
	<div class="modal fade" id="myModal_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title" id="myModalLabel_add">新增</h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="phone_add">分销商信息</label>
            <button type="button" onclick="toSearch();" id="btn_search_order" class="btn btn-primary">查询</button>
            <input type="text" name="distributorInfo_add" class="form-control required" id="distributorInfo_add" placeholder="输入分销商编码、名称、公司，查询账户">
          </div>
          <div class="form-group">
            <label for="distributorCode_add">账户名</label>
            <input type="text" name="distributorCode_add" readonly="readonly" class="form-control required" id="distributorCode_add" placeholder="账户名" style="color:#dd5145" >
          </div>
          <div class="form-group">
            <label for="nickname_add">账户昵称</label>
            <input type="text" name="nickname_add" readonly="readonly" class="form-control required" id="nickname_add" placeholder="账户昵称" style="color:#dd5145">
          </div>
          <div class="form-group">
            <label for="company_add">公司</label>
            <input type="text" name="company_add" readonly="readonly" class="form-control required" id="company_add" placeholder="公司" style="color:#dd5145">
          </div>
          <div class="form-group">
            <label for="linkman_add">联系人</label>
            <input type="text" name="linkman_add" readonly="readonly" class="form-control required" id="linkman_add" placeholder="联系人" style="color:#dd5145">
          </div>
          <div class="form-group">
            <label for="balanceBeforeRecharge_add">余额</label>
            <input type="text" name="balanceBeforeRecharge_add" readonly="readonly" class="form-control required" id="balanceBeforeRecharge_add" placeholder="余额">
          </div>
          <div class="form-group">
            <label for="recharge_add">充值金额</label>
            <input type="text" name="recharge_add" class="form-control required" id="recharge_add" placeholder="充值金额">
          </div>
          <div class="form-group">
            <label for="type_add">充值类型</label>
            <select name="type_add" id="type_add" class="form-control">
                <option value="1">正常加款</option>
                <option value="2">授信加款</option>
                <option value="3">赔付加款</option>
                <option value="4">运营补款</option>
                <option value="5">测试加款</option>
                <option value="6">返点加款</option>
                <option value="7">赠送加款</option>
                <option value="8">提现加款</option>
	        </select>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <button type="button" id="btn_submit" class="btn btn-primary"><i class="icon-save"></i> 保存</button>
        </div>
      </div>
    </div>
    </div>
  </body>
  <script type="text/javascript" src="${ctx}/view/rechargeflow/rechargeflow.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
</html>