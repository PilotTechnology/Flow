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
    <c:set value="${ctx}/portal/distributor!selectPage.action" scope="page" var="url"/>
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
              <li class="current"><a href="${ctx}/portal/distributor!selectPage.action" title="">分销商管理</a></li>
              <c:if test='${fatherDistributorCode ne "0" && fatherDistributorCode ne null}'>
              	<li class="current"><a href="${ctx}/portal/distributor!selectPage.action?fatherDistributorCode=${fatherDistributorCode}" title="">二级分销商管理</a></li>
              </c:if>
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
                  			<label class="col-md-1 control-label">账户名:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="distrbutorCode" name="distrbutorCode" value="${distributor.distrbutorCode}"></div>
                  			<label class="col-md-1 control-label">手机号:</label>
                  			<div class="col-md-2"><input class="form-control" type="text" id="phone" name="phone" value="${distributor.user.phone}"></div>
                  			<label class="col-md-1 control-label">状态:</label>
                  			<div class="col-md-2">
                  				<select name="state" id="state" class="form-control">
                  					<option value="-1" <c:if test='${distributor.state eq -1}'> selected</c:if>>全部</option>
                              		<option value="1" <c:if test='${distributor.state eq 1}'> selected</c:if>>正常</option>
                              		<option value="0" <c:if test='${distributor.state eq 0}'> selected</c:if>>禁用</option>
	                            </select>
                  			</div>
                  			
                  			<c:if test='${fatherDistributorCode ne "0" && fatherDistributorCode ne null}'>
                  				<input type="hidden" id="fatherDistributorCode" name="fatherDistributorCode" value="${fatherDistributorCode}"/>
                  			</c:if>
                  			<c:if test='${fatherDistributorCode eq "0" || fatherDistributorCode eq null}'>
                  				<input type="hidden" id="fatherDistributorCode" name="fatherDistributorCode" value="0"/>
                  			</c:if>
                  			
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
                  <h4><i class="icon-reorder"></i>分销商管理</h4>
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
                        <th>账户</th>
                        <th>公司</th>
                        <th>联系人</th>
                        <th>手机号</th>
                        <th>邮箱</th>
                        <th>余额(元)</th>
                        <th>冻结(元)</th>
                        <th>已用(元)</th>
                        <th>状态</th>
                        <th>创建时间</th>
                        <th data-hide="phone,tablet">操作</th>
                      </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="distributor" items="${page.rows}" varStatus="vs">
                      	<tr>
                        <td>${page.firstResult + vs.count}</td>
                        <td>${distributor.distrbutorCode}</td>
                        <td>${distributor.company}</td>
                        <td>${distributor.user.linkman}</td>
                        <td>${distributor.user.phone}</td>
                        <td>${distributor.user.email}</td>
                        <td>${distributor.balance}</td>
                        <td>${distributor.freezing}</td>
                        <td>${distributor.total}</td>
                        <td><c:if test='${distributor.state eq 0}'>禁用</c:if><c:if test='${distributor.state eq 1}'>正常</c:if></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${distributor.user.createDate}"/></td>
                        <td>
                        	<button class="btn btn-sm btn-info" onclick="toSearch('${distributor.distrbutorCode}');"><i class="icon-edit"  ></i>编辑</button>
                        	<c:if test='${fatherDistributorCode eq "0"}'>
                        		<a class="btn btn-sm btn-success" href="${ctx}/portal/distributor!selectPage.action?fatherDistributorCode=${distributor.distrbutorCode}"><i class="icon-cog"></i>查看二级</a>
                        	</c:if>
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
            <label for="distrbutorCode_add">分销商编码</label>
            <input type="text" name="distrbutorCode_add" class="form-control required" id="distrbutorCode_add" placeholder="分销商编码">
          </div>
          <div class="form-group">
            <label for="password_add">密码</label>
            <input type="text" name="password_add" class="form-control required" id="password_add" placeholder="密码">
          </div>
          <div class="form-group">
            <label for="confirm_password_add">确认密码</label>
            <input type="text" name="confirm_password_add" class="form-control required" id="confirm_password_add" placeholder="确认密码">
          </div>
          <div class="form-group">
            <label for="nickname_add">昵称</label>
            <input type="text" name="nickname_add" class="form-control required" id="nickname_add" placeholder="昵称">
          </div>
          <div class="form-group">
            <label for="company_add">公司名称</label>
            <input type="text" name="company_add" class="form-control required" id="company_add" placeholder="公司名称">
          </div>
          <div class="form-group">
            <label for="linkman_add">联系人</label>
            <input type="text" name="linkman_add" class="form-control required" id="linkman_add" placeholder="联系人">
          </div>
          <div class="form-group">
            <label for="phone_add">电话</label>
            <input type="text" name="phone_add" class="form-control required" id="phone_add" placeholder="电话">
          </div>
          <div class="form-group">
            <label for="email_add">邮箱</label>
            <input type="text" name="email_add" class="form-control required" id="email_add" placeholder="邮箱">
          </div>
          <div class="form-group">
            <label for="channelType_add">渠道类型</label>
            <select name="channelType_add" id="channelType_add" class="form-control">
                <option value="0">分销</option>
                <option value="1">营销</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="coopModel_add">合作模式</label>
            <select name="coopModel_add" id="coopModel_add" class="form-control">
                <option value="0">批发</option>
                <option value="1">分成</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="smsContent_add">短信模板</label>
            <input type="text" name="smsContent_add" class="form-control required" id="smsContent_add" placeholder="短信模板">
          </div>
          <div class="form-group">
            <label for="level_add">服务级别</label>
            <select name="level_add" id="level_add" class="form-control">
                <option value="0">低</option>
                <option value="1">中</option>
                <option value="2">高</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="callbackUrl_add">回调接口</label>
            <input type="text" name="callbackUrl_add" class="form-control required" id="callbackUrl_add" placeholder="回调接口">
          </div>
          <div class="form-group">
            <label for="confiningIp_add">ip白名单</label>
            <input type="text" name="confiningIp_add" class="form-control required" id="confiningIp_add" placeholder="ip白名单">
          </div>
          <div class="form-group">
            <label for="state_add">状态</label>
            <select name="state_add" id="state_add" class="form-control">
                <option value="1">正常</option>
                <option value="0">禁用</option>
	        </select>
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
            <label for="distrbutorCode_edit">分销商编码</label>
            <input type="text" readonly="readonly" name="distrbutorCode_edit" class="form-control required" id="distrbutorCode_edit" placeholder="分销商编码">
          </div>
          <div class="form-group">
            <label for="password_edit">密码</label>
            <input type="text" name="password_edit" class="form-control required" id="password_edit" placeholder="密码">
          </div>
          <div class="form-group">
            <label for="confirm_password_edit">确认密码</label>
            <input type="text" name="confirm_password_edit" class="form-control required" id="confirm_password_edit" placeholder="确认密码">
          </div>
          <div class="form-group">
            <label for="nickname_edit">昵称</label>
            <input type="text" name="nickname_edit" class="form-control required" id="nickname_edit" placeholder="昵称">
          </div>
          <div class="form-group">
            <label for="company_add">公司名称</label>
            <input type="text" name="company_edit" class="form-control required" id="company_edit" placeholder="公司名称">
          </div>
          <div class="form-group">
            <label for="linkman_add">联系人</label>
            <input type="text" name="linkman_edit" class="form-control required" id="linkman_edit" placeholder="联系人">
          </div>
          <div class="form-group">
            <label for="phone_add">电话</label>
            <input type="text" name="phone_edit" class="form-control required" id="phone_edit" placeholder="电话">
          </div>
          <div class="form-group">
            <label for="email_edit">邮箱</label>
            <input type="text" name="email_edit" class="form-control required" id="email_edit" placeholder="邮箱">
          </div>
          <div class="form-group">
            <label for="channelType_edit">渠道类型</label>
            <select name="channelType_edit" id="channelType_edit" class="form-control">
                <option value="0">分销</option>
                <option value="1">营销</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="coopModel_edit">合作模式</label>
            <select name="coopModel_edit" id="coopModel_edit" class="form-control">
                <option value="0">批发</option>
                <option value="1">分成</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="smsContent_edit">短信模板</label>
            <input type="text" name="smsContent_edit" class="form-control required" id="smsContent_edit" placeholder="短信模板">
          </div>
          <div class="form-group">
            <label for="level_edit">服务级别</label>
            <select name="level_edit" id="level_edit" class="form-control">
                <option value="0">低</option>
                <option value="1">中</option>
                <option value="2">高</option>
	        </select>
          </div>
          <div class="form-group">
            <label for="callbackUrl_edit">回调接口</label>
            <input type="text" name="callbackUrl_edit" class="form-control required" id="callbackUrl_edit" placeholder="回调接口">
          </div>
          <div class="form-group">
            <label for="confiningIp_edit">ip白名单</label>
            <input type="text" name="confiningIp_edit" class="form-control required" id="confiningIp_edit" placeholder="ip白名单">
          </div>
          <div class="form-group">
            <label for="state_edit">状态</label>
            <select name="state_edit" id="state_edit" class="form-control">
                <option value="1">正常</option>
                <option value="0">禁用</option>
	        </select>
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
  <script type="text/javascript" src="${ctx}/view/distributor/distributor.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.core.min.js"></script>
  <script type="text/javascript" src="${ctx}/js/ztree/js/jquery.ztree.excheck.min.js"></script>
</html>