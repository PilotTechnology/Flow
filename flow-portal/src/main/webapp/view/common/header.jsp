<%@ page language="java" pageEncoding="UTF-8"%>
<header class="header navbar navbar-fixed-top" role="banner">
      <div class="container">
        <ul class="nav navbar-nav">
          <li class="nav-toggle">
            <a href="javascript:void(0);" title="">
              <i class="icon-reorder">
              </i>
            </a>
          </li>
        </ul>
        <a class="navbar-brand" href="index.html">
          <img src="${ctx}/assets/img/logo.png" alt="logo" /><strong>Me</strong>流量平台管理
        </a>
        <a href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation">
          <i class="icon-reorder"></i>
        </a>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown user">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="icon-male">
              </i>
              <span class="username">${userInfo.nickname}</span>
              <i class="icon-caret-down small">
              </i>
            </a>
            <ul class="dropdown-menu">
              <li>
                <a href="pages_user_profile.html">
                  <i class="icon-user">
                  </i>
                  我的资料
                </a>
              </li>
              <li class="divider">
              </li>
              <li>
                <a href="${ctx}/portal/system/login!logout.action">
                  <i class="icon-key">
                  </i>
                  退出
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </header>