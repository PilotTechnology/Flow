<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set value="${request.pagecontext.contextpath}" var="ctx" />
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/assets/css/plugins/datatables_bootstrap.css" rel="stylesheet" type="text/css" />
<div class="container">

<!-- 内容区导航栏 -->
<div class="crumbs">
	<ul id="breadcrumbs" class="breadcrumb">
		<li><i class="icon-home"></i><a href="index.html">首页</a></li>
		<li class="current"><a href="pages_calendar.html" title="">分销平台订单</a></li>
	</ul>
</div>

<br />
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
		<table class="display nowrap dataTable" id="testTable">
  		  <thead>
			<tr>
				<th class="checkbox-column"><input type="checkbox" class="uniform"></th>
				<th>序号</th>
				<th>角色编码</th>
				<th>角色名称</th>
				<th>创建者</th>
				<th>操作</th>
			</tr>
		  </thead>
		  <tbody></tbody>
		</table>
	  </div>
	  <!-- 表格内容  end-->
	</div>
  </div>
</div>
</div>

<script type="text/javascript" src="${ctx}/assets/js/libs/jquery-1.12.3.js"></script>
<script type="text/javascript" src="${ctx}/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/js/dataTables.bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	
	$.post("/portal/test!list.action",
		function(data){
			$("#testTable").dataTable({
		      data : data,
		      columns: [
		        {data: "id"},
		        {data: "roleCode"},
		        {data: "roleName"},
		        {data: "createUser"},
		      ] 	
		    });
    },"json");
	
 });
</script>