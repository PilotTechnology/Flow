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
    <link href="${ctx}/assets/css/plugins/fileinput.min.css" rel="stylesheet" type="text/css" />
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
              <li class="current"><a href="${ctx}/portal/order!batchInit.action" title="">批量充值</a></li>
            </ul>
          </div>
          
          <br/>
          <div class="widget-content">
          	<form id="searchForm" action="${ctx}/portal/order!batchInsert.action" method="post" onsubmit="return iframeCallback(this, pageAjaxDone)" class="form-horizontal row-border" enctype=”multipart/form-data”>
          		<div class="form-group">
        			<label class="col-md-2 control-label">充值方式：</label>
        			<div class="col-md-4">
                		<select id="orderType" name="orderType" class="form-control">
                			<option value="1" >手机号</option>
                			<option value="2" >Excel文件</option>
                		</select>
          			</div>
            	</div>
          		<div class="form-group" id="phoneDiv">
        			<label class="col-md-2 control-label">手机号码：</label>
        			<div class="col-md-10">
                		<textarea class="auto form-control" id="phone" name="phone" cols="5" rows="3" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 87px;" placeholder="请输入手机号码，多个号码用逗号隔开"></textarea>
          			</div>
            	</div>
            	<div class="form-group" style="display: none" id="fileDiv">
        			<label class="col-md-2 control-label">excel文件：</label>
        			<div class="col-md-10">
                        <input type="file" name="excelFile" id="excelFile">
                    </div>
            	</div>
            	<div class="form-group">
        			<label class="col-md-2 control-label">折扣：</label>
        			<div class="col-md-10">
                		<input type="text" id="discount" name="discount" class="form-control" placeholder="5">
          			</div>
            	</div>
            	<div class="form-group">
        			<label class="col-md-2 control-label">流量包：</label>
        			<div class="col-md-4">
                		<select name="productCode" id="productCode" class="form-control">
                			<c:forEach var="product" items="${productList}">
                				<option value="${product.productCode}">${product.prodcutName}</option>
                			</c:forEach>
                		</select>
          			</div>
            	</div>
            	<div class="form-group">
        			<label class="col-md-2 control-label">使用账户：</label>
        			<div class="col-md-4">
                		<select name="distributorCode" id="distributorCode" class="form-control">
                			<c:forEach var="distributor" items="${distributorList}">
                				<option value="${distributor.distrbutorCode}">${distributor.company}</option>
                			</c:forEach>
                		</select>
          			</div>
            	</div>
            	<div class="form-group">
            		<label class="col-md-3 control-label"></label>
            		<button type="button" class="btn btn-sm btn-info" id="btn_submit">提交</button>
            	</div>
          	</form>
          </div>	
         </div>
      </div>
    </div>
  </body>
  <script type="text/javascript" src="${ctx}/view/order/order.js"></script>
  <script type="text/javascript" src="${ctx}/plugins/fileinput/fileinput.js"></script>
  <script type="text/javascript" src="${ctx}/plugins/fileinput/fileinput_local_zh.js"></script>
  <script type="text/javascript">
  $(document).ready(function() {
	$("#excelFile").fileinput();
  });
  </script>
</html>