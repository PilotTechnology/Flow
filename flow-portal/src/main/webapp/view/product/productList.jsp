<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${request.pagecontext.contextpath}" var="ctx"/>
<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>流量平台</title>
    <c:set value="${ctx}/portal/product!selectPage.action" scope="page" var="url"/>
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
              <li class="current"><a href="pages_calendar.html" title="">流量包管理</a></li>
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
                  			<label class="col-md-1 control-label">流量包名称:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="prodcutName" name="prodcutName" value="${product.prodcutName}"></div>
                  			<label class="col-md-1 control-label">运营商:</label>
                  			<div class="col-md-2">
                  				<select name="operatorCode" id="operatorCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div>
                  			<label class="col-md-1 control-label">供应商:</label>
                  			<div class="col-md-2">
                  				<select name="providerCode" id="providerCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div>
                  		</div>
                  		<div class="form-group">
                  			<label class="col-md-1 control-label">所属区域:</label>
                  			<div class="col-md-2">
                  				<select name="provinceCode" id="provinceCode" class="form-control">
                              		<option value="-1">全部</option>
	                            </select>
                  			</div>
                  			<label class="col-md-1 control-label">使用范围:</label>
                  			<div class="col-md-2">
                  				<select name="enableArea" id="enableArea" class="form-control">
                  					<option value="-1" <c:if test='${product.enableArea eq -1}'> selected</c:if>>全部</option>
                              		<option value="0" <c:if test='${product.enableArea eq 0}'> selected</c:if>>全国</option>
                              		<option value="1" <c:if test='${product.enableArea eq 1}'> selected</c:if>>省市</option>
	                            </select>
                  			</div>
                  			<label class="col-md-1 control-label">状态:</label>
                  			<div class="col-md-2">
                  				<select name="state" id="state" class="form-control">
                  					<option value="-1" <c:if test='${product.state eq -1}'> selected</c:if>>全部</option>
                              		<option value="1" <c:if test='${product.state eq 1}'> selected</c:if>>正常</option>
                              		<option value="0" <c:if test='${product.state eq 0}'> selected</c:if>>禁用</option>
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
                  <h4><i class="icon-reorder"></i>流量包管理</h4>
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
                        <th data-class="expand">序号</th>
                        <th>流量包编码</th>
						<th>流量包名称</th>
						<th>运营商</th>
						<th>供应商</th>
						<th>接口区域</th>
						<th>使用范围</th>
						<th>优先级</th>
						<th>大小(M)</th>
						<th>运营商价格(元)</th>
						<th>采购折扣</th>
						<th>采购价格</th>
						<th>状态</th>
						<th></th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="product" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${product.productCode}</td>
                        <td>${product.prodcutName}</td>
                        <td>${product.mobileOperator.operatorName}</td>
                        <td>${product.provider.name}</td>
                        <td>${product.province.provinceName}</td>
                        <td><c:if test='${product.enableArea eq 0}'>全国</c:if><c:if test='${product.enableArea eq 1}'>省市</c:if></td>
                        <td>${product.priority}</td>
                        <td>${product.size}</td>
                        <td>${product.price}</td>
                        <td>${product.discount}</td>
                        <td>${product.purchased}</td>
                        <td><c:if test='${product.state eq 0}'>禁用</c:if><c:if test='${product.state eq 1}'>正常</c:if></td>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toEdit('${role.roleCode}');"><i class="icon-edit"  ></i>编辑</button>
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
  <script type="text/javascript" src="${ctx}/view/common/public.js"></script>
  <script type="text/javascript" src="${ctx}/view/role/role.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
  <script type="text/javascript">
	  initProvider('${product.providerCode}');
	  initOperator('${product.operatorCode}');
	  initProvince('${product.provinceCode}');
  </script>
</html>