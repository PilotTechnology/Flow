<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${request.pagecontext.contextpath}" var="ctx"/>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>流量平台</title>
    <%@include file="../common/common.jsp" %>
    <c:set value="${ctx}/portal/refundflow!selectPage.action" scope="page" var="url"/>
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
              <li class="current"><a href="${ctx}/portal/refundflow!selectPage.action" title="">退款单管理</a></li>
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
                  			<label class="col-md-1 control-label">订单号:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="orderCode" name="orderCode" value="${refundFlow.orderCode}"></div>
                  			<label class="col-md-1 control-label">手机号:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="phone" name="phone" value="${refundFlow.phone}"></div>
                  			<label class="col-md-1 control-label">上游是否已退款:</label>
                  			<div class="col-md-2">
                  				<select name="providerIsRefund" id="providerIsRefund" class="form-control">
                              		<option value="-1" <c:if test='${refundFlow.providerIsRefund eq -1}'> selected</c:if>>全部</option>
                              		<option value="0" <c:if test='${refundFlow.providerIsRefund eq 0}'> selected</c:if>>未退</option>
                              		<option value="1" <c:if test='${refundFlow.providerIsRefund eq 1}'> selected</c:if>>已退</option>
	                            </select>
                  			</div>
                  		</div>
                  		<div class="form-group">
                  			<label class="col-md-1 control-label">创建时间:</label>
                  			<div class="col-md-5">
                  				<div class="row">
                  					<div class="col-md-5"><input type="text" id="beginTime" name="beginTime" value="${beginTime}" class="form-control" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"></div>
                  					<label class="col-md-2 control-label">至</label>
                  					<div class="col-md-5"><input type="text" id="endTime" name="endTime" value="${endTime}" class="form-control" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})"></div>
                  				</div>
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
                  <h4><i class="icon-reorder"></i>退款单管理</h4>
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
             	  		<button class="btn btn-sm btn-warning" id="addButton">+ 新增</button>
             	  	</div>
             	  </div>
             	  
                  <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th>退款单ID</th>
						<th>订单号</th>
						<th>手机号码</th>
						<th>流量包大小(M)</th>
						<th>流量包价格(元)</th>
						<th>分销商名称</th>
						<th>供应商名称</th>
						<th>供应商是否已退款</th>
						<th>创建时间</th>
						<th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="refundflow" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td>${refundflow.id}</td>
                        <td>${refundflow.orderCode}</td>
                        <td>${refundflow.phone}</td>
                        <td>${refundflow.prodcutName}</td>
                        <td>${refundflow.purchased}</td>
                        <td>${refundflow.distributorName}</td>
                        <td>${refundflow.providerName}</td>
                        <td><c:if test='${refundflow.providerIsRefund eq 0}'>未退</c:if><c:if test='${refundflow.providerIsRefund eq 1}'>已退</c:if></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${refundflow.createDate}"/></td>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toEdit('${refundFlow.id}');"><i class="icon-edit"  ></i>编辑</button>
                        </td>
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
            <label for="orderCode_add">订单号</label>
            <button type="button" onclick="toSearch();" id="btn_search_order" class="btn btn-primary">查询</button>
            <input type="text" name="orderCode_add" class="form-control required" id="orderCode_add" placeholder="订单号">
          </div>
          <div class="form-group">
            <label for="phone_add">手机号</label>
            <input type="text" readonly="readonly" name="phone_add" class="form-control required" id="phone_add" placeholder="手机号">
          </div>
          <div class="form-group">
            <label for="prodcutName_add">流量包大小(M)</label>
            <input type="text" readonly="readonly" name="prodcutName_add" class="form-control required" id="prodcutName_add" placeholder="流量包名称">
          </div>
          <div class="form-group">
            <label for="purchased_add">流量包价格(元)</label>
            <input type="text" readonly="readonly" name="purchased_add" class="form-control required" id="purchased_add" placeholder="流量包价格">
          </div>
          <div class="form-group">
            <label for="distributorName_add">分销商名称</label>
            <input type="text" readonly="readonly" name="distributorName_add" class="form-control required" id="distributorName_add" placeholder="分销商名称">
          </div>
          <div class="form-group">
            <label for="providerName_add">供应商名称</label>
            <input type="text" readonly="readonly" name="providerName_add" class="form-control required" id="providerName_add" placeholder="供应商名称">
          </div>
          <div class="form-group">
            <label for="providerIsRefund_add">供应商是否已退款</label>
          	<select name="providerIsRefund_add" id="providerIsRefund_add" class="form-control">
            	<option value="0">未退</option>
            	<option value="1">已退</option>
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
    <!-- edit 弹框 -->
    <div class="modal fade" id="myModal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title" id="myModalLabel_add">编辑</h4>
        </div>
        <div class="modal-body">
 		  <div class="form-group">
            <label for="orderCode_edit">订单号</label>
            <input type="text" readonly="readonly" name="orderCode_edit" class="form-control required" id="orderCode_edit" placeholder="订单号">
          </div>
          <div class="form-group">
            <label for="phone_edit">手机号</label>
            <input type="text" readonly="readonly" name="phone_edit" class="form-control required" id="phone_edit" placeholder="手机号">
          </div>
          <div class="form-group">
            <label for="prodcutName_edit">流量包大小(M)</label>
            <input type="text" readonly="readonly" name="prodcutName_edit" class="form-control required" id="prodcutName_edit" placeholder="流量包名称">
          </div>
          <div class="form-group">
            <label for="purchased_edit">流量包价格(元)</label>
            <input type="text" readonly="readonly" name="purchased_edit" class="form-control required" id="purchased_edit" placeholder="流量包价格">
          </div>
          <div class="form-group">
            <label for="distributorName_edit">分销商名称</label>
            <input type="text" readonly="readonly" name="distributorName_edit" class="form-control required" id="distributorName_edit" placeholder="分销商名称">
          </div>
          <div class="form-group">
            <label for="providerName_edit">供应商名称</label>
            <input type="text" readonly="readonly" name="providerName_edit" class="form-control required" id="providerName_edit" placeholder="供应商名称">
          </div>
          <div class="form-group">
            <label for="providerIsRefund_edit">供应商是否已退款</label>
          	<select name="providerIsRefund_edit" id="providerIsRefund_edit" class="form-control">
            	<option value="0">未退</option>
            	<option value="1">已退</option>
	        </select>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <input type="hidden" id="id_edit" name="id_edit" />
          <button type="button" id="btn_update" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 修改</button>
        </div>
      </div>
    </div>
    </div>
  </body>
  <script type="text/javascript" src="${ctx}/view/refundflow/refundFlow.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
  <script type="text/javascript" src="${ctx}/plugins/laydate/laydate.js"></script>
</html>