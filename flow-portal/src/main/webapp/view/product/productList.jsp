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
              <li><i class="icon-home"></i><a href="${ctx}/portal/system/login!selectPage.action">首页</a></li>
              <li class="current"><a href="${ctx}/portal/product!selectPage.action" title="">流量包管理</a></li>
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
                        <td><c:if test='${product.priority eq 0}'>低</c:if><c:if test='${product.priority eq 1}'>中</c:if><c:if test='${product.priority eq 2}'>高</c:if></td>
                        <td>${product.size}</td>
                        <td>${product.price}</td>
                        <td>${product.discount}</td>
                        <td>${product.purchased}</td>
                        <td><c:if test='${product.state eq 0}'>禁用</c:if><c:if test='${product.state eq 1}'>正常</c:if></td>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toSearch('${product.id}');"><i class="icon-edit"  ></i>编辑</button>
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
            <label for="productCode_add">流量包编码</label>
            <input type="text" name="productCode_add" class="form-control required" id="productCode_add" placeholder="流量包编码">
          </div>
          <div class="form-group">
            <label for="productName_add">流量包名称</label>
            <input type="text" name="productName_add" class="form-control required" id="productName_add" placeholder="流量包名称">
          </div>
          <div class="form-group">
            <label for="operatorCode_add">运营商</label>
          	<select name="operatorCode_add" id="operatorCode_add" class="form-control">
	        </select>
          </div>
          <div class="form-group">
            <label for="providerCode_add">供应商</label>
          	<select name="providerCode_add" id="providerCode_add" class="form-control">
	        </select>
          </div>
          <div class="form-group">
            <label for="proProductCode_add">供应商流量包编码</label>
            <input type="text" name="proProductCode_add" class="form-control required" id="proProductCode_add" placeholder="供应商流量包编码">
          </div>
          <div class="form-group">
            <label for="provinceCode_add">接口区域</label>
          	<select name="provinceCode_add" id="provinceCode_add" class="form-control">
	        </select>
          </div>
          <div class="form-group">
            <label for="enableArea_add">使用范围</label>
          	<select name="enableArea_add" id="enableArea_add" class="form-control">
                <option value="0">全国</option>
                <option value="1">省市</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="enableType_add">生效类型</label>
          	<select name="enableType_add" id="enableType_add" class="form-control">
            	<option value="0">立即生效</option>
            	<option value="1">次月生效</option>
            	<option value="2">24小时候生效</option>
            	<option value="3">当天生效</option>
            	<option value="4">当月生效</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="priority_add">优先级</label>
          	<select name="priority_add" id="priority_add" class="form-control">
            	<option value="0">低</option>
            	<option value="1">中</option>
            	<option value="2">高</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="size_add">流量包大小</label>
            <input type="text" name="size_add" class="form-control required" id="size_add" placeholder="流量包大小">
          </div>
          <div class="form-group">
            <label for="price_add">运营商价格</label>
            <input type="text" name="price_add" class="form-control required" id="price_add" placeholder="运营商价格">
          </div>
          <div class="form-group">
            <label for="discount_add">折扣</label>
            <input type="text" name="discount_add" class="form-control required" id="discount_add" placeholder="折扣">
          </div>
          <div class="form-group">
            <label for="state_add">状态</label>
          	<select name="state_add" id="state_add" class="form-control">
            	<option value="1">激活</option>
            	<option value="0">禁用</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="description_add">描述</label>
            <input type="text" name="description_add" class="form-control required" id="description_add" placeholder="描述">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <button type="button" id="btn_submit" class="btn btn-primary"><i class="icon-save"></i> 保存</button>
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
            <label for="productCode_edit">流量包编码</label>
            <input type="text" name="productCode_edit" class="form-control required" id="productCode_edit" readonly="readonly" placeholder="流量包编码">
          </div>
          <div class="form-group">
            <label for="productName_edit">流量包名称</label>
            <input type="text" name="productName_edit" class="form-control required" id="productName_edit" placeholder="流量包名称">
          </div>
          <div class="form-group">
            <label for="operatorCode_edit">运营商</label>
          	<select name="operatorCode_edit" id="operatorCode_edit" class="form-control">
	        </select>
          </div>
          <div class="form-group">
            <label for="providerCode_edit">供应商</label>
          	<select name="providerCode_edit" id="providerCode_edit" class="form-control">
	        </select>
          </div>
          <div class="form-group">
            <label for="proProductCode_edit">供应商流量包名称</label>
            <input type="text" name="proProductCode_edit" class="form-control required" id="proProductCode_edit" placeholder="供应商流量包名称">
          </div>
          <div class="form-group">
            <label for="provinceCode_edit">接口区域</label>
          	<select name="provinceCode_edit" id="provinceCode_edit" class="form-control">
	        </select>
          </div>
          <div class="form-group">
            <label for="enableArea_edit">使用范围</label>
          	<select name="enableArea_edit" id="enableArea_edit" class="form-control">
                <option value="0">全国</option>
                <option value="1">省市</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="enableType_edit">生效类型</label>
          	<select name="enableType_edit" id="enableType_edit" class="form-control">
            	<option value="0">立即生效</option>
            	<option value="1">次月生效</option>
            	<option value="2">24小时候生效</option>
            	<option value="3">当天生效</option>
            	<option value="4">当月生效</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="priority_edit">优先级</label>
          	<select name="priority_edit" id="priority_edit" class="form-control">
            	<option value="0">低</option>
            	<option value="1">中</option>
            	<option value="2">高</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="size_edit">流量包大小</label>
            <input type="text" name="size_edit" class="form-control required" id="size_edit" placeholder="流量包大小">
          </div>
          <div class="form-group">
            <label for="price_edit">运营商价格</label>
            <input type="text" name="price_edit" class="form-control required" id="price_edit" placeholder="运营商价格">
          </div>
          <div class="form-group">
            <label for="discount_edit">折扣</label>
            <input type="text" name="discount_edit" class="form-control required" id="discount_edit" placeholder="折扣">
          </div>
          <div class="form-group">
            <label for="state_edit">状态</label>
          	<select name="state_edit" id="state_edit" class="form-control">
            	<option value="1">激活</option>
            	<option value="0">禁用</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="description_edit">描述</label>
            <input type="text" name="description_edit" class="form-control required" id="description_edit" placeholder="描述">
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal"><i class="icon-undo"></i> 关闭</button>
          <input type="hidden" id="id_edit" name="id_edit" />
          <button type="button" id="btn_update" class="btn btn-primary"><i class="icon-save"></i> 修改</button>
        </div>
      </div>
    </div>
    </div>
  </body>
  <script type="text/javascript" src="${ctx}/view/common/public.js"></script>
  <script type="text/javascript" src="${ctx}/view/product/product.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
  <script type="text/javascript">
	  initProvider('${product.providerCode}');
	  initOperator('${product.operatorCode}');
	  initProvince('${product.provinceCode}');
  </script>
</html>