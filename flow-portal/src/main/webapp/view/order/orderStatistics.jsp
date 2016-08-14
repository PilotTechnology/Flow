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
    <c:set value="${ctx}/portal/order!statistics.action" scope="page" var="url"/>
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
              <li class="current"><a href="${ctx}/portal/order!statistics.action" title="">分销商订单统计</a></li>
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
                  			<label class="col-md-1 control-label">分销商:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="distributorCode" name="distributorCode" value="${distributorCode}"></div>
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
                        <th data-class="expand">序号</th>
                        <th>分销商编码</th>
						<th>充值成功订单数</th>
						<th>充值失败订单数</th>
						<th>消费金额(元)</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="orderStatistics" items="${list}" varStatus="vs">
                      	<tr>
                        <td>${vs.count}</td>
                        <td>${orderStatistics.distributorCode}</td>
                        <td>${orderStatistics.successNum}</td>
                        <td>${orderStatistics.failNum}</td>
                        <td>${orderStatistics.moneyOfSuccess}</td>
                      	</tr>
                      </c:forEach>
                    </tbody>
                  </table>            
                </div>
                <!-- 表格内容  end-->
          	  </div>
          	</div>
          </div>
          </div>
      </div>
    </div>
  </body>
  <script type="text/javascript" src="${ctx}/plugins/laydate/laydate.js"></script>
</html>