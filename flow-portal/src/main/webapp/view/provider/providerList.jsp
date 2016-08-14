<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${request.pagecontext.contextpath}" var="ctx"/>
<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>流量平台</title>
    <%@include file="../common/common.jsp" %>
    <c:set value="${ctx}/portal/provider!selectPage.action" scope="page" var="url"/>
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
              <li class="current"><a href="${ctx}/portal/provider!selectPage.action" title="">供应商管理</a></li>
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
                  				<label class="col-md-1 control-label">供应商编码或名称:</label>
                  				<div class="col-md-2"><input class="form-control" type="text" id="name" name="name" value="${provider.name}"></div>
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
                  <h4><i class="icon-reorder"></i>供应商管理</h4>
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
             	  	<div class="2">
             	  		<button class="btn btn-sm btn-warning" id="addButton">+ 新增</button>
             	  	</div>
             	  </div>
             	  
                  <table class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th data-class="expand">序号</th>
                        <th>供应商编码</th>
						<th>供应商名称</th>
						<th>描述</th>
						<th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="provider" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${provider.providerCode}</td>
                        <td>${provider.name}</td>
                        <td>${provider.description}</td>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toEdit('${provider.providerCode}');"><i class="icon-edit"  ></i>编辑</button>
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
            <label for="providerCode_add">供应商编码</label>
            <input type="text" name="providerCode_add" class="form-control required" id="providerCode_add" placeholder="供应商编码">
          </div>
          <div class="form-group">
            <label for="providerName_add">供应商名称</label>
            <input type="text" name="providerName_add" class="form-control required" id="providerName_add" placeholder="供应商名称">
          </div>
          <div class="form-group">
            <label for="providerName_add">描述</label>
            <input type="text" name="description_add" class="form-control required" id="description_add" placeholder="描述">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 保存</button>
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
            <label for="providerCode_edit" class="control-label">供应商编码</label>
            <input type="text" readonly="readonly" name="providerCode_edit" class="form-control required" id="providerCode_edit" placeholder="供应商编码">
          </div>
          <div class="form-group">
            <label for="providerName_edit" class="control-label">供应商名称</label>
            <input type="text" name="providerName_edit" class="form-control required" id="providerName_edit" placeholder="供应商名称">
          </div>
          <div class="form-group">
            <label for="description_edit" class="control-label">描述</label>
            <input type="text" name="description_edit" class="form-control required" id="description_edit" placeholder="描述">
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
    <!-- 弹框结束 -->
  
  </body>
  <script type="text/javascript" src="${ctx}/view/provider/provider.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
</html>