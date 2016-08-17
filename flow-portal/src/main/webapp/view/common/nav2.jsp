<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set value="${request.pagecontext.contextpath}" var="ctx"/>
<ul id="nav">
	<li class="current">
	  <a href="${ctx}/"> 
	    <i class="icon-dashboard"> </i> 后台首页
	  </a>
	</li>
	<li>
	  <a href="javascript:void(0);"> 
	    <i class="icon-desktop"></i> 系统管理 <span class="label label-info pull-right">2</span>
	  </a>
	  <ul class="sub-menu">
		<li><a href="${ctx}/portal/system/role!selectPage.action"> <i class="icon-angle-right">
			</i> 角色管理
		</a></li>
		<li><a href="ui_buttons.html"> <i class="icon-angle-right">
			</i> 系统用户
		</a></li>
		<li><a href="javascript:void(0);" onclick="linkTo('${ctx}/portal/test!init.action')"> <i class="icon-angle-right">
			</i> 测试菜单
		</a></li>
	  </ul>
	</li>
	<li>
	  <a href="javascript:void(0);"> 
	    <i class="icon-time"></i> 流量管理 
	  </a>
	  <ul class="sub-menu">
		<li><a href="${ctx}/portal/order!selectPage.action"> <i class="icon-angle-right">
			</i> 分销平台订单
		</a></li>
		<li><a href="ui_general.html"> <i class="icon-angle-right">
			</i> 分销平台订单统计
		</a></li>
		<li><a href="${ctx}/portal/provider!selectPage.action"> <i class="icon-angle-right">
			</i> 供应商管理
		</a></li>
		<li><a href="${ctx}/portal/product!selectPage.action"> <i class="icon-angle-right">
			</i> 流量包管理
		</a></li>
		<li><a href="${ctx}/portal/costflow!selectPage.action"> <i class="icon-angle-right">
			</i> 资金流水
		</a></li>	
		<li><a href="${ctx}/portal/refundflow!selectPage.action"> <i class="icon-angle-right">
			</i> 退款单管理
		</a></li>
	  </ul>
	</li>
	<li>
	  <a href="javascript:void(0);"> 
	    <i class="icon-volume-down"></i> 通知管理 
	  </a>
	  <ul class="sub-menu">
		<li><a href="ui_general.html"> <i class="icon-angle-right">
			</i> 通知列表
		</a></li>
	  </ul>
	</li>
	<li>
	  <a href="javascript:void(0);"> 
	    <i class="icon-money"></i> 报价单管理 
	  </a>
	  <ul class="sub-menu">
		<li><a href="${ctx}/portal/distributor!selectPage.action"> <i class="icon-angle-right">
			</i> 分销商管理
		</a></li>
			<li><a href="${ctx}/portal/quotation!selectPage.action"> <i class="icon-angle-right">
			</i> 报价单列表
		</a></li>
			<li><a href="${ctx}/portal/rechargeflow!selectPage.action"> <i class="icon-angle-right">
			</i> 账户充值
		</a></li>
			<li><a href="ui_general.html"> <i class="icon-angle-right">
			</i> 批量充值
		</a></li>
	  </ul>
	</li>
	<li>
	  <a href="javascript:void(0);"> 
	    <i class="icon-mobile-phone"></i> 号段管理 
	  </a>
	  <ul class="sub-menu">
			<li><a href="${ctx}/portal/mobile!selectPage.action"> <i class="icon-angle-right">
			</i> 号段管理
		</a></li>
	  </ul>
	</li>
	
	<!-- <li>
	  <a href="javascript:void(0);"> 
	    <i class="icon-desktop"></i> 通知管理 <span class="label label-info pull-right">2</span>
	  </a>
	  <ul class="sub-menu">
		<li><a href="ui_general.html"> <i class="icon-angle-right">
			</i> 通知列表
		</a></li>
	  </ul>
	</li>
	<li><a href="javascript:void(0);"> <i class="icon-edit"> </i>
			表单元素
	</a>
		<ul class="sub-menu">
			<li><a href="form_components.html"> <i
					class="icon-angle-right"> </i> 表单组件
			</a></li>
			<li><a href="form_layouts.html"> <i class="icon-angle-right">
				</i> 表单布局
			</a></li>
			<li><a href="form_validation.html"> <i
					class="icon-angle-right"> </i> 表单验证
			</a></li>
			<li><a href="form_wizard.html"> <i class="icon-angle-right">
				</i> 表单提示
			</a></li>
		</ul></li>
	<li><a href="javascript:void(0);"> <i class="icon-table">
		</i> 表格
	</a>
		<ul class="sub-menu">
			<li><a href="tables_static.html"> <i
					class="icon-angle-right"> </i> 静态表格
			</a></li>
			<li><a href="tables_dynamic.html"> <i
					class="icon-angle-right"> </i> 动态表格
			</a></li>
			<li><a href="tables_responsive.html"> <i
					class="icon-angle-right"> </i> 响应式表格
			</a></li>
		</ul></li>
	<li><a href="charts.html"> <i class="icon-bar-chart"> </i> 图表
			&amp; 统计
	</a></li>
	<li><a href="javascript:void(0);"> <i
			class="icon-folder-open-alt"> </i> 页面
	</a>
		<ul class="sub-menu">
			<li><a href="login.html"> <i class="icon-angle-right"> </i>
					登录页面
			</a></li>
			<li><a href="pages_user_profile.html"> <i
					class="icon-angle-right"> </i> 个人资料页面
			</a></li>
			<li><a href="pages_calendar.html"> <i
					class="icon-angle-right"> </i> 日历页面
			</a></li>
			<li><a href="pages_invoice.html"> <i
					class="icon-angle-right"> </i> 购物清单页面
			</a></li>
			<li><a href="404.html"> <i class="icon-angle-right"> </i>
					404错误页面
			</a></li>
		</ul></li>
	<li><a href="javascript:void(0);"> <i class="icon-list-ol">
		</i> 4级菜单
	</a>
		<ul class="sub-menu">
			<li class="open-default"><a href="javascript:void(0);"> <i
					class="icon-cogs"> </i> Item 1 <span class="arrow"> </span>
			</a>
				<ul class="sub-menu">
					<li class="open-default"><a href="javascript:void(0);"> <i
							class="icon-user"> </i> Sample Link 1 <span class="arrow">
						</span>
					</a>
						<ul class="sub-menu">
							<li class="current"><a href="javascript:void(0);"> <i
									class="icon-remove"> </i> Sample Link 1
							</a></li>
							<li><a href="javascript:void(0);"> <i
									class="icon-pencil"> </i> Sample Link 1
							</a></li>
							<li><a href="javascript:void(0);"> <i class="icon-edit">
								</i> Sample Link 1
							</a></li>
						</ul></li>
					<li><a href="javascript:void(0);"> <i class="icon-user">
						</i> Sample Link 1
					</a></li>
					<li><a href="javascript:void(0);"> <i
							class="icon-external-link"> </i> Sample Link 2
					</a></li>
					<li><a href="javascript:void(0);"> <i class="icon-bell">
						</i> Sample Link 3
					</a></li>
				</ul></li>
			<li><a href="javascript:void(0);"> <i class="icon-globe">
				</i> Item 2 <span class="arrow"> </span>
			</a>
				<ul class="sub-menu">
					<li><a href="javascript:void(0);"> <i class="icon-user">
						</i> Sample Link 1
					</a></li>
					<li><a href="javascript:void(0);"> <i
							class="icon-external-link"> </i> Sample Link 1
					</a></li>
					<li><a href="javascript:void(0);"> <i class="icon-bell">
						</i> Sample Link 1
					</a></li>
				</ul></li>
			<li><a href="javascript:void(0);"> <i
					class="icon-folder-open"> </i> Item 3
			</a></li>
		</ul></li> -->
</ul>
<script>
	function linkTo(url){
		$("#content").load(url);
	}
</script>