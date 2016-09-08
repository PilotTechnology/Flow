<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set value="${request.pagecontext.contextpath}" var="ctx"/>

<link rel="stylesheet" type="text/css" href="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css"
     href="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.css"/>
<link href="../../assets/css/main.css" rel="stylesheet" type="text/css" />

<!-- 页面图标样式 -->
<link rel="stylesheet" href="../../assets/css/fontawesome/font-awesome.min.css">
<script src="http://cdn.gbtags.com/datatables/1.10.5/js/jquery.js"></script>
<script src="http://cdn.gbtags.com/datatables/1.10.5/js/jquery.dataTables.min.js"></script>
<script src="http://cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../assets/js/libs/lodash.compat.min.js"></script>
<!--[if lt IE 9]>
  <script src="assets/js/libs/html5shiv.js">
  </script>
<![endif]-->
<script type="text/javascript" src="../../plugins/touchpunch/jquery.ui.touch-punch.min.js"></script>
<script type="text/javascript" src="../../plugins/event.swipe/jquery.event.move.js"></script>
<script type="text/javascript" src="../../plugins/event.swipe/jquery.event.swipe.js"></script>
<script type="text/javascript" src="../../assets/js/libs/breakpoints.js"></script>
<script type="text/javascript" src="../../plugins/respond/respond.min.js"></script>
<script type="text/javascript" src="../../plugins/cookie/jquery.cookie.min.js"></script>
<script type="text/javascript" src="../../plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="../../plugins/slimscroll/jquery.slimscroll.horizontal.min.js"></script>
<!--[if lt IE 9]>
  <script type="text/javascript" src="../../plugins/flot/excanvas.min.js">
  </script>
<![endif]-->

<!-- 
<script type="text/javascript" src="../../plugins/sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="../../plugins/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="../../plugins/flot/jquery.flot.tooltip.min.js"></script>
<script type="text/javascript" src="../../plugins/flot/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="../../plugins/flot/jquery.flot.time.min.js"></script>
<script type="text/javascript" src="../../plugins/flot/jquery.flot.growraf.min.js"></script>
<script type="text/javascript" src="../../plugins/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<script type="text/javascript" src="../../plugins/blockui/jquery.blockUI.min.js"></script>
<script type="text/javascript" src="../../plugins/fullcalendar/fullcalendar.min.js"></script>
<script type="text/javascript" src="../../plugins/noty/jquery.noty.js"></script>
<script type="text/javascript" src="../../plugins/noty/layouts/top.js"></script>
<script type="text/javascript" src="../../plugins/noty/themes/default.js"></script>
<script type="text/javascript" src="../../plugins/uniform/jquery.uniform.min.js"></script>
<script type="text/javascript" src="../../plugins/select2/select2.min.js"></script>
 -->
<script type="text/javascript" src="../../assets/js/app.js"></script>
<script type="text/javascript" src="../../assets/js/plugins.js"></script>
<script type="text/javascript" src="../../assets/js/plugins.form-components.js"></script>
<script>
  $(document).ready(function() {
    App.init();
    Plugins.init();
    FormComponents.init()
  });
</script>
<!-- <script type="text/javascript" src="../../assets/js/custom.js"></script>
<script type="text/javascript" src="../../assets/js/demo/pages_calendar.js"></script>
<script type="text/javascript" src="../../assets/js/demo/charts/chart_simple.js"></script> -->