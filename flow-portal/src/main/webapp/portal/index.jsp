<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"
    />
    <title>流量平台</title>
    <%@include file="../view/common/index.jsp" %>
  </head>
  
  <body>
    <%@ include file="../view/common/header.jsp" %>
    <div id="container">
      <div id="sidebar" class="sidebar-fixed">
        <div id="sidebar-content">
          <!-- <form class="sidebar-search">
            <div class="input-box">
              <button type="submit" class="submit">
                <i class="icon-search">
                </i>
              </button>
              <span>
                <input type="text" placeholder="搜索...">
              </span>
            </div>
          </form>
          <div class="sidebar-search-results">
            <i class="icon-remove close">
            </i>
            <div class="title">
              Documents
            </div>
            <ul class="notifications">
              <li>
                <a href="javascript:void(0);">
                  <div class="col-left">
                    <span class="label label-info">
                      <i class="icon-file-text">
                      </i>
                    </span>
                  </div>
                  <div class="col-right with-margin">
                    <span class="message">
                      <strong>
                        John Doe
                      </strong>
                      received $1.527,32
                    </span>
                    <span class="time">
                      finances.xls
                    </span>
                  </div>
                </a>
              </li>
              <li>
                <a href="javascript:void(0);">
                  <div class="col-left">
                    <span class="label label-success">
                      <i class="icon-file-text">
                      </i>
                    </span>
                  </div>
                  <div class="col-right with-margin">
                    <span class="message">
                      My name is
                      <strong>
                        John Doe
                      </strong>
                      ...
                    </span>
                    <span class="time">
                      briefing.docx
                    </span>
                  </div>
                </a>
              </li>
            </ul>
            <div class="title">
              Persons
            </div>
            <ul class="notifications">
              <li>
                <a href="javascript:void(0);">
                  <div class="col-left">
                    <span class="label label-danger">
                      <i class="icon-female">
                      </i>
                    </span>
                  </div>
                  <div class="col-right with-margin">
                    <span class="message">
                      Jane
                      <strong>
                        Doe
                      </strong>
                    </span>
                    <span class="time">
                      21 years old
                    </span>
                  </div>
                </a>
              </li>
            </ul>
          </div> -->
          
          <%@include file="../view/common/nav.jsp"%>
          
          <!-- <div class="sidebar-title">
            <span>
              提示
            </span>
          </div>
          <ul class="notifications demo-slide-in">
            <li style="display: none;">
              <div class="col-left">
                <span class="label label-danger">
                  <i class="icon-warning-sign">
                  </i>
                </span>
              </div>
              <div class="col-right with-margin">
                <span class="message">
                  服务器
                  <strong>
                    #512
                  </strong>
                  crashed.
                </span>
                <span class="time">
                  几分钟之前
                </span>
              </div>
            </li>
            <li style="display: none;">
              <div class="col-left">
                <span class="label label-info">
                  <i class="icon-envelope">
                  </i>
                </span>
              </div>
              <div class="col-right with-margin">
                <span class="message">
                  <strong>
                    John
                  </strong>
                  发了条消息给你
                </span>
                <span class="time">
                  几分钟之前
                </span>
              </div>
            </li>
            <li>
              <div class="col-left">
                <span class="label label-success">
                  <i class="icon-plus">
                  </i>
                </span>
              </div>
              <div class="col-right with-margin">
                <span class="message">
                  <strong>
                    Emma
                  </strong>
                  账号已经创建
                </span>
                <span class="time">
                  4小时以前
                </span>
              </div>
            </li>
          </ul> -->
          <div class="sidebar-widget align-center">
            <div class="btn-group" data-toggle="buttons" id="theme-switcher">
              <label class="btn active">
                <input type="radio" name="theme-switcher" data-theme="bright">
                <i class="icon-sun">
                </i>
                白天
              </label>
              <label class="btn">
                <input type="radio" name="theme-switcher" data-theme="dark">
                <i class="icon-moon">
                </i>
                夜晚
              </label>
            </div>
          </div>
        </div>
        <div id="divider" class="resizeable">
        </div>
      </div>
      <div id="content">
        <div class="container">
          <!-- <div class="crumbs">
            <ul id="breadcrumbs" class="breadcrumb">
              <li>
                <i class="icon-home">
                </i>
                <a href="index.html">
                  控制台
                </a>
              </li>
              <li class="current">
                <a href="pages_calendar.html" title="">
                  日历
                </a>
              </li>
            </ul>
            <ul class="crumb-buttons">
              <li>
                <a href="charts.html" title="">
                  <i class="icon-signal">
                  </i>
                  <span>
                    统计
                  </span>
                </a>
              </li>
              <li class="dropdown">
                <a href="#" title="" data-toggle="dropdown">
                  <i class="icon-tasks">
                  </i>
                  <span>
                    用户
                    <strong>
                      (+3)
                    </strong>
                  </span>
                  <i class="icon-angle-down left-padding">
                  </i>
                </a>
                <ul class="dropdown-menu pull-right">
                  <li>
                    <a href="form_components.html" title="">
                      <i class="icon-plus">
                      </i>
                      添加新用户
                    </a>
                  </li>
                  <li>
                    <a href="http://envato.stammtec.de/themeforest/melon/tables_dynamic.html"
                    title="">
                      <i class="icon-reorder">
                      </i>
                      查看
                    </a>
                  </li>
                </ul>
              </li>
              <li class="range">
                <a href="#">
                  <i class="icon-calendar">
                  </i>
                  <span>
                  </span>
                  <i class="icon-angle-down">
                  </i>
                </a>
              </li>
            </ul>
          </div> -->
          <div class="page-header">
            <div class="page-title">
              <h3>
                	我的信息
              </h3>
              <span>
                
              </span>
            </div>
            <ul class="page-stats">
              <li>
                <div class="summary">
                  <span>
                    今日订单
                  </span>
                  <h3>
                    ${orderCount }
                  </h3>
                </div>
                <div id="sparkline-bar" class="graph sparkline hidden-xs">
                  20,15,8,50,20,40,20,30,20,15,30,20,25,20
                </div>
              </li>
              <li>
                <div class="summary">
                  <span>
                    我的余额
                  </span>
                  <h3>
                  ￥${balance}
                  </h3>
                </div>
                <div id="sparkline-bar2" class="graph sparkline hidden-xs">
                  20,15,8,50,20,40,20,30,20,15,30,20,25,20
                </div>
              </li>
            </ul>
          </div>
          
          <div class="row row-bg">
            
          </div>
          
          <!-- <div class="row row-bg">
            <div class="col-sm-6 col-md-3">
              <div class="statbox widget box box-shadow">
                <div class="widget-content">
                  <div class="visual cyan">
                    <div class="statbox-sparkline">
                      30,20,15,30,22,25,26,30,27
                    </div>
                  </div>
                  <div class="title">
                    客户
                  </div>
                  <div class="value">
                    4 501
                  </div>
                  <a class="more" href="javascript:void(0);">
                    查看更多
                    <i class="pull-right icon-angle-right">
                    </i>
                  </a>
                </div>
              </div>
            </div>
            <div class="col-sm-6 col-md-3">
              <div class="statbox widget box box-shadow">
                <div class="widget-content">
                  <div class="visual green">
                    <div class="statbox-sparkline">
                      20,30,30,29,22,15,20,30,32
                    </div>
                  </div>
                  <div class="title">
                    反馈
                  </div>
                  <div class="value">
                    714
                  </div>
                  <a class="more" href="javascript:void(0);">
                   查看更多
                    <i class="pull-right icon-angle-right">
                    </i>
                  </a>
                </div>
              </div>
            </div>
            <div class="col-sm-6 col-md-3 hidden-xs">
              <div class="statbox widget box box-shadow">
                <div class="widget-content">
                  <div class="visual yellow">
                    <i class="icon-dollar">
                    </i>
                  </div>
                  <div class="title">
                    总共利润
                  </div>
                  <div class="value">
                    $42,512.61
                  </div>
                  <a class="more" href="javascript:void(0);">
                    查看更多
                    <i class="pull-right icon-angle-right">
                    </i>
                  </a>
                </div>
              </div>
            </div>
            <div class="col-sm-6 col-md-3 hidden-xs">
              <div class="statbox widget box box-shadow">
                <div class="widget-content">
                  <div class="visual red">
                    <i class="icon-user">
                    </i>
                  </div>
                  <div class="title">
                    访问量
                  </div>
                  <div class="value">
                    2 521 719
                  </div>
                  <a class="more" href="javascript:void(0);">
                   查看更多
                    <i class="pull-right icon-angle-right">
                    </i>
                  </a>
                </div>
              </div>
            </div>
          </div> -->
          
          <!-- 饼状图 start-->
          <div class="row">
          	 <div class="col-md-6">
          	 	<div class="widget box">
          	 		<div class="widget-header">
                  		<h4><i class="icon-reorder"></i>订单流量大小统计</h4>
                  		<div class="toolbar no-padding">
		                	<div class="btn-group">
		                      <span class="btn btn-xs widget-collapse">
		                        <i class="icon-angle-down"></i>
		                      </span>
		                    </div>
		                </div>
                	</div>
                	
                	<div class="widget-content">
	                  <div id="chart_pie" class="chart">
	                  </div>
	                </div>
          	 	</div>
          	 </div>
          	 <div class="col-md-6">
          	 	<div class="widget box">
          	 		<div class="widget-header">
                  		<h4><i class="icon-reorder"></i>订单按省份统计</h4>
                  		<div class="toolbar no-padding">
		                	<div class="btn-group">
		                      <span class="btn btn-xs widget-collapse">
		                        <i class="icon-angle-down"></i>
		                      </span>
		                    </div>
		                </div>
                	</div>
                	
                	<div class="widget-content">
	                  <div id="chart_day" class="chart">
	                  </div>
	                </div>
          	 	</div>
          	 </div>
          </div>
          <!-- 饼状图 end-->
          
          <div class="row">
            <div class="col-md-12">
              <div class="widget box">
                <div class="widget-header">
                  <h4>
                    <i class="icon-reorder">
                    </i>
                    订单按天统计走势图
                  </h4>
                  <div class="toolbar no-padding">
                    <div class="btn-group">
                      <span class="btn btn-xs widget-collapse">
                        <i class="icon-angle-down">
                        </i>
                      </span>
                    </div>
                  </div>
                </div>
                <div class="widget-content">
                  <div id="chart_filled_blue" class="chart">
                  </div>
                </div>
                <div class="divider">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>

</html>