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
    <c:set value="${ctx}/portal/quotation!selectPage.action" scope="page" var="url"/>
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
                  			<label class="col-md-2 control-label">分销商名称:</label>
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
                        <td>${quotation.isDisplayProvince eq 1 ? "显示" : "不显示"}</td>
                        <td>${quotation.state eq 1 ? "激活" : "禁用"}</td>
                        <td><fmt:formatDate value="${quotation.createDate}"/></td>
                        <td>
                          <div class="btn-group">
                        	<button data-toggle="dropdown" class="btn btn-sm dropdown-toggle">
                       			<i class="icon-cog"></i>操作<span class="caret"></span>
                      		</button>
                      		<ul class="dropdown-menu">
                        		<li><a href="javascript:void(0)" onclick="to_Edit('${quotation.serviceCode}')"><i class="icon-edit"></i>修改报价单</a></li>
                        	<!-- 	<li><a href="#"><i class="icon-search"></i>查看报价单</a></li>
                        		<li><a href="#"><i class="icon-search"></i>查看子渠道报价单</a></li> -->
		                    </ul>
		                  </div>
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
            <label for="area_add">接口区域：	</label>
            <select name="area_add" id="area_add" class="form-control"><option>批量通用</option><option>专用</option></select>
          </div>
          <div class="form-group">
            <label for="distributorName_add">cp名称：</label>
            <button type="button" id="cp_sel" class="btn-xs btn-info"><i class="icon-plus-sign"></i> 选择</button>
            <input type="text" name="distributorName_add" class="form-control required" id="distributorName_add" placeholder="cp名称">
          </div>
          <div class="form-group">
            <label for="distributorDesc_add">cp详情：</label>
            <input type="text" name="distributorDesc_add" class="form-control required" id="distributorDesc_add" placeholder="用户号：    登录名： ">
          </div>
          <div class="form-group">
            <label for="distributorDesc_add">业务操作：</label> 
            <button type="button" id="product_add" onclick="productAdd('product_add');" class="btn-xs btn-info"	><i class="icon-plus-sign"></i> 添加</button>
            <button type="button" id="product_remove" class="btn-xs btn-warning"><i class="icon-remove-sign"></i> 清除</button>
            <div class="widget-content">
            	 <table id="product_selected" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th data-class="expand">序号</th>
                        <th>业务编码</th>
						<th>业务名称</th>
						<th>业务价格</th>
						<th>折扣</th>
						<th>操作</th>
                      </tr>
                    </thead>
                  </table>
            </div>
          </div>
          
          <div class="form-group">
            <label for="display_province">是否显示省包：	</label>
            <select name="display_province" id="display_province" class="form-control"><option value="1">显示</option><option value="0">不显示</option></select>
          </div>
          <div class="form-group">
            <label for="state_add">状态：	</label>
            <select name="state_add" id="state_add" class="form-control"><option value="1">激活</option><option value="0">禁用</option></select>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <button type="button" id="btn_submit" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 保存</button>
        </div>
      </div>
    </div>
    </div>
    <!-- 编辑 弹框 -->
	<div class="modal fade" id="myModal_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title" id="myModalLabel_add">修改</h4>
        </div>
        <div class="modal-body">
 
          <div class="form-group">
            <label for="area_edit">接口区域：	</label>
            <select name="area_edit" id="area_edit" class="form-control"><option>批量通用</option><option>专用</option></select>
          </div>
          <div class="form-group">
            <label for="distributorName_edit">cp名称：</label>
            <label for="distributorName_edit" id="distributorName_edit"></label>
          </div>
          <div class="form-group">
            <label for="distributorDesc_add">cp详情：</label>
            <label for="distributorDesc_edit" id="distributorDesc_edit">用户号：    登录名：</label>
          </div>
          <div class="form-group">
            <label for="distributorDesc_add">业务操作：</label> 
            <button type="button" id="product_edit_add" onclick="productAdd('product_edit_add');" class="btn-xs btn-info"	><i class="icon-plus-sign"></i> 添加</button>
            <button type="button" id="product_edit_remove" class="btn-xs btn-warning"><i class="icon-remove-sign"></i> 清除</button>
            <div class="widget-content">
            	 <table id="product_edit" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th data-class="expand">序号</th>
                        <th>业务编码</th>
						<th>业务名称</th>
						<th>业务价格</th>
						<th>折扣</th>
						<th>操作</th>
                      </tr>
                    </thead>
                  </table>
            </div>
          </div>
          
          <div class="form-group">
            <label for="display_province_edit">是否显示省包：	</label>
            <select name="display_province_edit" id="display_province_edit" class="form-control"><option value="1">显示</option><option value="0">不显示</option></select>
          </div>
          <div class="form-group">
            <label for="state_edit">状态：	</label>
            <select name="state_edit" id="state_edit" class="form-control"><option value="1">激活</option><option value="0">禁用</option></select>
          </div>
        </div>
        <div class="modal-footer">
          <input type="hidden" id="editId" /><input type="hidden" id="serviceCode" />
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <button type="button" id="btn_edit" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 修改</button>
        </div>
      </div>
    </div>
    </div>
    <!-- 弹框结束 -->
    <!-- 业务选择弹框 -->
    <div class="modal fade" id="product_sel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:880px;">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title" id="myModalLabel_add">选择业务包：</h4>
        </div>
        <div class="modal-body">
        	<div class="widget-content">
            	 <table id="product_table" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th class="checkbox-column"><input type="checkbox" name="product_sel2" class="uniform"></th>
                        <th>业务ID</th>
						<th>业务编码</th>
						<th>业务名称</th>
						<th>运营商</th>
						<th>接口区域</th>
						<th>使用范围</th>
						<th>大小</th>
						<th>价格</th>
						<th>折扣</th>
						<th>状态</th>
                      </tr>
                    </thead>
                    <tbody></tbody>
                  </table>
            </div> 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 取消</button>
          <button type="button" id="product_btn" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 选择</button>
          <button type="button" id="product_edit_btn" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 选择</button>
        </div>
      </div>
    </div>
    </div>
    
    <!-- cp用户 弹框 -->
    <div class="modal fade" id="distributor_sel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" style="width:800px">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title">添加cp用户：</h4>
        </div>
        <div class="modal-body">
			<div class="widget-content">
            	 <table id="cp_table" class="table table-striped table-bordered table-hover table-checkable table-responsive datatable">
                    <thead>
                      <tr>
                        <th class="radio-column">
                        </th>
                        <th>用户号</th>
						<th>用户名</th>
						<th>昵称</th>
						<th>手机号码</th>
                      </tr>
                    </thead>
                    <tbody></tbody>
                  </table>
            </div> 
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 取消</button>
          <button type="button" id="btn_sel" class="btn btn-primary" data-dismiss="modal"><i class="icon-save"></i> 选择</button>
        </div>
      </div>
    </div>
    </div>
  
  </body>
  <script type="text/javascript" src="${ctx}/view/quotation/quotation.js"></script>
</html>