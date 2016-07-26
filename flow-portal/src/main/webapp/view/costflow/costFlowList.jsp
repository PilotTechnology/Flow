<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${request.pagecontext.contextpath}" var="ctx"/>
<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>流量平台</title>
    <c:set value="${ctx}/portal/costflow!selectPage.action" scope="page" var="url"/>
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
              <li><i class="icon-home"></i><a href="index.html">首页</a></li>
              <li class="current"><a href="pages_calendar.html" title="">资金流水</a></li>
            </ul>
          </div>
          
          <div class="row">
          	<div class="col-md-12">
          	  <div class="widget box">
          	  	<!--  表格导航栏 -->
          	  	<div class="widget-header">
                  <h4><i class="icon-reorder"></i>资金流水</h4>
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
                        <th class="checkbox-column">
                          <input type="checkbox" class="uniform">
                        </th>
                        <th data-class="expand">序号</th>
                        <th>订单ID</th>
                        <th>分销商ID</th>
                        <th>资金流水(元)</th>
                        <th>当前余额</th>
                        <th>类型</th>
                        <th>创建时间</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="costflow" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td class="checkbox-column"><input type="checkbox" class="uniform"></td>
                        <td>${costflow.firstResult + vs.count}</td>
                        <td>${costflow.orderCode}</td>
                        <td>${costflow.distributorCode}</td>
                        <td>${costflow.cost}</td>
                        <td>${costflow.currentBalance}</td>
                        <td>${costflow.type}</td>
                        <td>${costflow.createDate}</td>
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
  <%@include file="../common/common.jsp" %>
  <script type="text/javascript" src="${ctx}/view/role/role.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
</html>