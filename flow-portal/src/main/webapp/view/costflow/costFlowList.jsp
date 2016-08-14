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
              <li><i class="icon-home"></i><a href="${ctx}/portal/system/login!selectPage.action">首页</a></li>
              <li class="current"><a href="${ctx}/portal/costflow!selectPage.action" title="">资金流水</a></li>
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
                  			<label class="col-md-1 control-label">类型:</label>
                  			<div class="col-md-2">
                  				<select name="type" id="type" class="form-control">
                  					<option value="-1" <c:if test='${costFlow.type eq -1}'> selected</c:if>>全部</option>
                              		<option value="0" <c:if test='${costFlow.type eq 0}'> selected</c:if>>扣款</option>
                              		<option value="1"<c:if test='${costFlow.type eq 1}'> selected</c:if>>退款</option>
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
                        <td>${page.firstResult + vs.count}</td>
                        <td>${costflow.orderCode}</td>
                        <td>${costflow.distributorCode}</td>
                        <td>${costflow.cost}</td>
                        <td>${costflow.currentBalance}</td>
                        <td><c:if test='${costflow.type eq 0}'>扣款</c:if><c:if test='${costflow.type eq 1}'>退款</c:if></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${costflow.creatDate}"/></td>
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
  <script type="text/javascript" src="${ctx}/view/role/role.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
</html>