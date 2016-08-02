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
              <li><i class="icon-home"></i><a href="index.html">首页</a></li>
              <li class="current"><a href="pages_calendar.html" title="">分销平台订单</a></li>
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
                  				<select name="select" id="state" class="form-control">
                              		<option value="-1">全部</option>
                              		<option value="0">处理中</option>
                              		<option value="1">充值成功</option>
                              		<option value="2">充值失败</option>
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
                  			<label class="col-md-1 control-label">省份:</label>
                  			<div class="col-md-2">
                  				<select name="provinceCode" id="provinceCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div>
                  		</div>
	                    <div class="form-group">
                  			<label class="col-md-1 control-label">用户号:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" name="regular"></div>
                  			<label class="col-md-1 control-label">手机号:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="phone" name="phone" value="${order.phone}"></div>
                  			<label class="col-md-1 control-label">平台订单:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="orderCode" name="orderCode" value="${order.orderCode}"></div>
                  			<label class="col-md-1 control-label">下游订单:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="distributorOrderCode" name="distributorOrderCode" value="${order.distributorOrderCode}"></div>
                  		</div>
                  		<div class="form-group">
                  			<label class="col-md-1 control-label">创建时间:</label>
                  			<div class="col-md-5">
                  				<div class="row">
                  					<div class="col-md-5"><input type="text" name="regular" class="form-control datepicker"></div>
                  					<label class="col-md-2 control-label">至</label>
                  					<div class="col-md-5"><input type="text" name="regular" class="form-control datepicker"></div>
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
             	  
                  <table class="display nowrap table-hover table-striped table-bordered dataTable" width="100%">
                    <thead >
                      <tr>
                        <th class="checkbox-column">
                          <input type="checkbox" class="uniform">
                        </th>
                        <th>序号</th>
                        <th>用户</th>
                        <th>供应商</th>
                        <th>平台订单号</th>
                        <th>下游订单号</th>
                        <th>运营商</th>
                        <th>省份</th>
                        <th>手机号码</th>
                       <!--  <th data-hide="phone">大小</th>
                        <th data-hide="phone">价格</th> -->
                        <th>折扣</th>
                        <th>订单状态</th>
                        <th>创建时间</th>
                       <!--  <th data-hide="phone">回调时间</th>
                        <th data-hide="phone">上游订单号</th>
                        <th data-hide="phone">回调情况</th> -->
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="order" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td class="checkbox-column"><input type="checkbox" class="uniform"></td>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${order.distributorCode}</td>
                        <td>${order.providerName}</td>
                        <td>${order.orderCode}</td>
                        <td>${order.distributorOrderCode}</td>
                        <td>${order.operatorName}</td>
                        <td>${order.provinceName}</td>
                        <td>${order.phone}</td>
                        <td>${order.size}</td>
                        <%-- <td>${order.price}</td>
                        <td>${order.discount}</td> --%>
                        <td>${order.stateMsg}</td>
                        <td><fmt:formatDate value="${order.createDate}"/></td>
                   <%-- <td>${order.callBackDate}</td>
                        <td>${order.providerOrderCode}</td>
                        <td>${order.callBackCodeMess}</td> --%>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toEdit('${role.roleCode}');"><i class="icon-edit"  ></i>编辑</button>
                        	<button class="btn btn-sm btn-success" onclick="bindMenu('${role.roleCode}');"><i class="icon-cog"></i>授权</button>
                        	<button class="btn btn-sm btn-danger" onclick="removeRole('${role.roleCode}','${role.id}');"><i class="icon-remove"></i>删除</button>
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
	  initProvince('${order.provinceCode}');
  </script>
</html>