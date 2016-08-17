<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Datatable-serverSide 服务器分页例子</title>
</head>
<body>
<%@include file="../common/common.jsp" %>
<div class="container">
    <table id="example" class="table table-striped table-bordered">
        <thead>
       <tr>
			<th>序号</th>
			<th>角色编码</th>
			<th>角色名称</th>
			<th>创建者</th>
		</tr>
        </thead>
        <tbody></tbody>
        <!-- tbody是必须的 -->
    </table>
</div>

</body>
</html>
<!--定义操作列按钮模板-->

<script>
    var table;
    $(function () {

        //预编译模板
        table = $('#example').DataTable({
            ajax: {
                url: "/portal/test!list.action"
            },
            serverSide: true,
            columns: [
				{data: "id"},
				{data: "roleCode"},
				{data: "roleName"},
				{data: "createUser"}
            ],
            "language": {
                "lengthMenu": "_MENU_ 条记录每页",
                "zeroRecords": "没有找到记录",
                "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty": "无记录",
                "infoFiltered": "(从 _MAX_ 条记录过滤)",
                "paginate": {
                    "previous": "上一页",
                    "next": "下一页"
                }
            },
            "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
                    "t" +
                    "<'row'<'col-xs-6'i><'col-xs-6'p>>"	
        });
    });

</script>