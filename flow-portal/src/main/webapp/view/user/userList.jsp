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
    <c:set value="${ctx}/portal/system/user!selectPage.action" scope="page" var="url"/>
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
              <li class="current"><a href="${ctx}/portal/system/user!selectPage.action" title="">用户管理</a></li>
            </ul>
          </div>
          
          <div class="row">
          	<div class="col-md-12">
          	  <div class="widget box">
          	  	<!--  表格导航栏 -->
          	  	<div class="widget-header">
                  <h4><i class="icon-reorder"></i>用户管理</h4>
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
                        <th>用户编码</th>
                        <th data-hide="phone">角色昵称</th>
                        <th data-hide="phone">电子邮箱</th>
                        <th data-hide="phone">所属角色</th>
                        <th data-hide="phone">所属部门</th>
                        <th data-hide="phone">当前状态</th>
                        <th data-hide="phone">创建时间</th>
                        <th data-hide="phone,tablet">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="user" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td class="checkbox-column"><input type="checkbox" class="uniform"></td>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${user.userCode}</td>
                        <td>${user.nickname}</td>
                        <td>${user.email}</td>
                        <td>${user.roleName}</td>
                        <td>${user.linkman}</td>
                        <td>${user.isEnable eq 0 ? '禁用' : '激活'}</td>
                        <td><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd"/></td>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toEdit('${user.userCode}');"><i class="icon-edit"  ></i>编辑</button>
                        	<button class="btn btn-sm btn-danger" onclick="removeUser('${user.userCode}');"><i class="icon-remove"></i>删除</button>
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
            <label for="userCode_add">用户编码</label>
            <input type="text" name="userCode_add" class="form-control required" id="userCode_add" placeholder="用户编码" maxlength="20">
          </div>
          <div class="form-group">
            <label for="nickname_add">用户昵称</label>
            <input type="text" name="nickname_add" class="form-control required" id="nickname_add" placeholder="用户昵称" maxlength="32">
          </div>
          <div class="form-group">
            <label for="linkman_add">所属部门</label>
            <input type="text" name="linkman_add" class="form-control required" id="linkman_add" placeholder="所属部门" maxlength="32">
          </div>
          <div class="form-group">
            <label for="roleCode_add">选择角色</label>
            <select name="roleCode_add" id="roleCode_add" class="form-control">
          		<option value="-1">全部</option>
            </select>
          </div>
          <div class="form-group">
            <label for="phone_add">用户电话</label>
            <input type="text" name="phone_add" class="form-control required" id="phone_add" placeholder="用户电话" maxlength="16">
          </div>
          <div class="form-group">
            <label for="email_add">电子邮箱</label>
            <input type="text" name="email_add" class="form-control required" id="email_add" placeholder="电子邮箱" maxlength="32">
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
            <label for="userCode_edit">用户编码</label>
            <input type="text" readonly="readonly" name="userCode_edit" class="form-control required" id="userCode_edit" placeholder="用户编码" maxlength="20">
          </div>
          <div class="form-group">
            <label for="nickname_edit">用户昵称</label>
            <input type="text" name="nickname_edit" class="form-control required" id="nickname_edit" placeholder="用户昵称" maxlength="32">
          </div>
          <div class="form-group">
            <label for="linkman_edit">所属部门</label>
            <input type="text" name="linkman_edit" class="form-control required" id="linkman_edit" placeholder="所属部门" maxlength="32">
          </div>
          <div class="form-group">
            <label for="roleCode_edit">选择角色</label>
            <select name="roleCode_edit" id="roleCode_edit" class="form-control">
          		<option value="-1">全部</option>
            </select>
          </div>
          <div class="form-group">
            <label for="phone_edit">用户电话</label>
            <input type="text" name="phone_edit" class="form-control required" id="phone_edit" placeholder="用户电话" maxlength="16">
          </div>
          <div class="form-group">
            <label for="email_edit">电子邮箱</label>
            <input type="text" name="email_edit" class="form-control required" id="email_edit" placeholder="电子邮箱" maxlength="32">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <button type="button" id="btn_update" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 修改</button>
        </div>
      </div>
    </div>
    </div>
    
  </body>
  <script type="text/javascript" src="${ctx}/view/user/user.js"></script>
</html>