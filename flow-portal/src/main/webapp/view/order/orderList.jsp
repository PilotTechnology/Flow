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
    <c:set value="${ctx}/portal/order!selectPage.action" scope="page" var="url"/>
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
              <li class="current"><a href="${ctx}/portal/order!selectPage.action" title="">分销商订单</a></li>
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
                  			<label class="col-md-1 control-label">订单状态:</label>
                  			<div class="col-md-2">
                  				<select name="state" id="state" class="form-control">
                              		<option value="-1" <c:if test='${order.state eq -1}'>selected</c:if>>全部</option>
                              		<option value="0" <c:if test='${order.state eq 0}'>selected</c:if>>处理中</option>
                              		<option value="1" <c:if test='${order.state eq 1}'>selected</c:if>>充值成功</option>
                              		<option value="2" <c:if test='${order.state eq 2}'>selected</c:if>>充值失败</option>
	                            </select>
                  			</div>
                  			<label class="col-md-1 control-label">供应商:</label>
                  			<div class="col-md-2">
                  				<select name="providerCode" id="providerCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div>
                  			<label class="col-md-1 control-label">运营商:</label>
                  			<div class="col-md-2">
                  				<select name="operatorCode" id="operatorCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div>
                  			<!-- <label class="col-md-1 control-label">省份:</label>
                  			<div class="col-md-2">
                  				<select name="provinceCode" id="provinceCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div -->
                  		</div>
	                    <div class="form-group">
                  			<label class="col-md-1 control-label">分销商:</label>
                  			<div class="col-md-2">
                  				<select name="distributorCode" id="distributorCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div>
                  			<label class="col-md-1 control-label">手机号:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="phone" name="phone" value="${order.phone}"></div>
                  			<label class="col-md-1 control-label">平台订单:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="orderCode" name="orderCode" value="${order.orderCode}"></div>
                  			<label class="col-md-1 control-label">下游订单:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="distributorOrderCode" name="distributorOrderCode" value="${order.distributorOrderCode}"></div>
                  		</div>
                  		<div class="form-group">
                  			<input type="hidden" id="searchType" name="searchType">
                  			<div class="col-md-1">
                  				<button class="btn btn-sm btn-warning" type="submit" id="search" onclick="$('#searchType').val(-1);">搜索</button>
                  			</div>
                  			<div class="col-md-1">
                  				<button class="btn btn-sm btn-warning" type="submit" id="search" onclick="$('#searchType').val(0);">12小时卡单</button>
                  			</div>
                  			<div class="col-md-1">
                  				<button class="btn btn-sm btn-warning" type="submit" id="search" onclick="$('#searchType').val(1);">24小时卡单</button>
                  			</div>
                  			<div class="col-md-1">
                  				<button class="btn btn-sm btn-warning" type="submit" id="search" onclick="$('#searchType').val(2);">36小时卡单</button>
                  			</div>
                  			<div class="col-md-1">
                  				<button class="btn btn-sm btn-warning" type="submit" id="search" onclick="$('#searchType').val(3);">48小时卡单</button>
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
                  <h4><i class="icon-reorder"></i>平台订单列表</h4>
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
                  <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th>序号</th>
                        <th>手机号</th>
                        <th>运营商</th>
                        <th>省份</th>
                        <th>订单号</th>
                        <th>分销商</th>
                        <th>分销商订单号</th>
                        <th>供应商</th>
                        <th>供应商订单号</th>
                        <th>大小</th>
                        <th>折扣</th>
                        <th>价格</th>
                        <th>订单状态</th>
                        <th>错误码</th>
                        <th>供应商响应码</th>
                        <th>供应商响应码描述</th>
                        <th>创建时间</th>
                        <th>回调时间</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="order" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${order.phone}</td>
                        <td>${order.operatorName}</td>
                        <td>${order.phoneProvince}-${order.phoneCity}</td>
                        <td>${order.orderCode}</td>
                        <td>${order.distributorName}</td>
                        <td>${order.distributorOrderCode}</td>
                        <td>${order.providerName}</td>
                        <td>${order.providerOrderCode}</td>
                        <td>${order.size}</td>
                        <td>${order.discount}</td>
                        <td>${order.purchased}</td>
                        <td>
                        	<c:if test='${order.state eq 0}'>处理中</c:if>
                        	<c:if test='${order.state eq 1}'>充值成功</c:if>
                        	<c:if test='${order.state eq 2}'>充值失败</c:if>
                        </td>
                        <td>${order.platformErrorCode}</td>
                        <td>${order.callbackCode}</td>
                        <td>${order.callbackCodeMess}</td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.createDate}"/></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.callbackDate}"/></td>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toSearch('${role.roleCode}');"><i class="icon-edit"  ></i>详细信息</button>
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
  </body>
  <script type="text/javascript" src="${ctx}/view/common/public.js"></script>
  <script type="text/javascript">
	  initProvider('${order.providerCode}');
	  initOperator('${order.operatorCode}');
	  initDistributor('${order.distributorCode}');
	  $(document).ready(function() {
			$('#orderList').dataTable( {
				"scrollX": true
			} );
		} );
  </script>
</html>