<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${request.pagecontext.contextpath}" var="ctx"/>
<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>流量平台</title>
    <c:set value="${ctx}/portal/quotation!selectPage.action" scope="page" var="url"/>
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
              <li class="current"><a href="${ctx}/portal/quotation!selectPage.action" title="">报价单管理</a></li>
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
                  			<div class="col-md-2"><input class="form-control" type="text" id="distributionName" name="distributionName" value="${quotation.distributionName}"></div>
                  			<label class="col-md-1 control-label">状态:</label>
                  			<div class="col-md-2">
                  				<select name="state" id="state" class="form-control">
                  					<option value="-1">全部</option>
                              		<option value="1">正常</option>
                              		<option value="0">禁用</option>
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
                  <h4><i class="icon-reorder"></i>报价单管理</h4>
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
                        <th class="checkbox-column">
                          <input type="checkbox" class="uniform">
                        </th>
                        <th data-class="expand">序号</th>
                        <th>报价单ID</th>
						<th>分销商名称</th>
						<th>是否显示省包</th>
						<th>状态</th>
						<th>创建时间</th>
						<th>操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="quotation" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td class="checkbox-column"><input type="checkbox" class="uniform"></td>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${quotation.serviceCode}</td>
                        <td>${quotation.distributionName}</td>
                        <td>${quotation.isDisplayProvince}</td>
                        <td>${quotation.state}</td>
                        <td>${quotation.createDate}</td>
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
            <label for="roleCode_add">角色编码</label>
            <input type="text" name="roleCode_add" class="form-control required" id="roleCode_add" placeholder="角色编码">
          </div>
          <div class="form-group">
            <label for="roleName_add">角色名称</label>
            <input type="text" name="roleName_add" class="form-control required" id="roleName_add" placeholder="角色名称">
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
            <label for="roleCode_edit" class="control-label">角色编码</label>
            <input type="text" name="roleCode_edit" class="form-control required" id="roleCode_edit" placeholder="角色编码">
          </div>
          <div class="form-group">
            <label for="roleName_edit" class="control-label">角色名称</label>
            <input type="text" name="roleName_edit" class="form-control required" id="roleName_edit" placeholder="角色名称">
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
    
    <!-- 授权 弹框 -->
    <div class="modal fade" id="myModal_grant" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title">角色授权：</h4>
        </div>
        <div class="modal-body">
 
          <div class="form-group">
            <label class="control-label col-md-2">角色编码:</label>
            <label class="control-label col-md-4" id="roleCode_grant"></label>
            <label class="control-label col-md-2">角色名称:</label>
            <label class="control-label col-md-4" id="roleName_grant"></label>
          </div>
          <div class="form-group" style="height: 200px;">
            <label  class="col-md-2 control-label">绑定权限</label>
            <div class="col-md-10" ><div class="well">
              <div class="portlet">
                <div class="portlet-body">
               	 	<div id="menuTree" class="ztree"></div>
                </div>
           	  </div>
            </div></div>
          </div>      
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 取消</button>
          <input type="hidden" id="roleCode_hidden" name="roleCode_hidden" />
          <button type="button" id="btn_grant" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 保存</button>
        </div>
      </div>
    </div>
    </div>
    <!-- 弹框结束 -->
  
  </body>
  <%@include file="../common/common.jsp" %>
  <script type="text/javascript" src="${ctx}/view/role/role.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
</html>