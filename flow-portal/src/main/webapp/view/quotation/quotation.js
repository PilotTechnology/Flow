//新增报价单
var table ;
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//修改报价单
var b ;
function to_Edit(id){
	b = 0;
	$.post("/portal/quotation!get.action",{"id":id},function(data){
	    $('#myModal_edit').modal();
	    
	    $("#distributorName_edit").html(data.quotation.distributionName);
	    $("#editId").val(data.quotation.id);
	    $("#serviceCode").val(data.quotation.serviceCode);
	    $("#distributorDesc_edit").html("用户号:" + data.quotation.distributorCode + ", 登录名: " + data.quotation.distributorCode);
	    $("#display_province_edit").val(data.quotation.isDisplayProvince);
	    $("#state_edit").val(data.quotation.state);
	    b = data.products.length;
	    $("#product_edit").children("tbody").children("tr").remove();
	    for(var i=0;i<b;i++){
	    	$("#product_edit").append('<tr><td data-class="expand"> '+ i + '</td>'
				 +'<td data-class="expand"> '+ data.products[i].productCode + '</td>'
		 		 +'<td data-class="expand"> '+ data.products[i].productName + '</td>'
		 		 +'<td data-class="expand"> '+ data.products[i].price       + ' </td>'
		 		 +'<td data-class="expand"> <input style="width:35px;height:20px;" name="proDiscount_e" type="text" value="'+ data.products[i].discount + '"/>%</td>'
		 		 +'<td data-class="expand"> <a href="#" onclick="javascript:$(this).parent().parent().remove();">删除</a></td></tr>');
	    }
    },"json");
}

//add-modal关闭时清空页面元素
$('#myModal_add').on('hidden.bs.modal', function () {
	$("#distributorName_add").val("");
	$("#distributorDesc_add").val("");
})

//选择cp用户弹框
$("#cp_sel").click(function () {
	$('#distributor_sel').modal();
	$('#distributor_sel').on('shown.bs.modal', function () {
		
		if ($('#cp_table').hasClass('dataTable')) {
				table.fnDestroy();	//清除原有的dom
		}
		
		table = $('#cp_table').DataTable({
		        ajax: {
		            url: "/portal/system/user!userList.action"
		        },
		        serverSide: true,
		        columns: [
		            {
		            	"data": "userCode",
		            	"render":function (data,type,val,meta){
		            				return '<input type="radio" name="sel" value="' + data + '"/>';
		            			 }
		            },
		            {"data": "userCode"},
		            {"data": "userCode"},
		            {"data": "nickname"},
		            {"data": "phone"},
		        ],
		        "bFilter": false,
		        "bAutoWidth":true,
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
	})

});

// 单击选中用户
$("#btn_sel").click(function(){
	var userCode = $("input[name='sel']:checked").val();
	$("#distributorName_add").val(userCode);
	$("#distributorDesc_add").val("用户名：" + userCode + " 登录名：" + userCode);
});


//选择流量包弹框
function productAdd(divId){
	$('#product_sel').modal();
	if(divId == 'product_edit_add'){
		$("#product_btn").hide();
		$("#product_edit_btn").show();
	}else{
		$("#product_btn").show();
		$("#product_edit_btn").hide();
	}
	$('#product_sel').on('shown.bs.modal', function () {
		
		if ($('#product_table').hasClass('dataTable')) {
			$('#product_table').DataTable.fnDestroy();	//清除原有的dom
		}
		
		$('#product_table').DataTable({
		        ajax: {
		            url: "/portal/product!productList.action"
		        },
		        serverSide: true,
		        columns: [
		            {
		            	"data": null,
		            	"render":function (data,type,val,meta){
		            				return '<input type="checkbox" name="product_sel2" />';
		            			 }
		            },
		            {"data": "id"},
		            {"data": "productCode"},
		            {"data": "prodcutName"},
		            {"data": "mobileOperator.operatorName"},
		            {"data": "province.provinceName"},
		            {"data": "enableArea"},
		            {"data": "size"},
		            {"data": "price"},
		            {"data": "discount"},
		            {"data": "state"},
		        ],
		        "bFilter": false,
		        "bAutoWidth":true,
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
	})

}
var a = 0 ;
$("#product_btn").click(function(){
	 $("input[name='product_sel2']").each(function(){
		 if(this.checked == true){
			 a = a + 1;
			 $("#product_selected").append('<tr><td data-class="expand"> '+ a + '</td>'
					 +'<td data-class="expand"> '+ $(this).parent().next().next().text() + '</td>'
			 		 +'<td data-class="expand"> '+ $(this).parent().next().next().next().text() + '</td>'
			 		 +'<td data-class="expand"> '+ $(this).parent().next().next().next().next().next().next().next().next().text() + ' </td>'
			 		 +'<td data-class="expand"> <input style="width:35px;height:20px;" name="proDiscount" type="text" value="'+ $(this).parent().next().next().next().next().next().next().next().next().next().text() + '"/>%</td>'
			 		 +'<td data-class="expand"> <a href="#" onclick="javascript:$(this).parent().parent().remove();">删除</a></td></tr>');
		 }
	 })
});
$("#product_edit_btn").click(function(){
	 $("input[name='product_sel2']").each(function(){
		 if(this.checked == true){
			 b = b + 1;
			 $("#product_edit").append('<tr><td data-class="expand"> '+ b + '</td>'
					 +'<td data-class="expand"> '+ $(this).parent().next().next().text() + '</td>'
			 		 +'<td data-class="expand"> '+ $(this).parent().next().next().next().text() + '</td>'
			 		 +'<td data-class="expand"> '+ $(this).parent().next().next().next().next().next().next().next().next().text() + ' </td>'
			 		 +'<td data-class="expand"> <input style="width:35px;height:20px;" name="proDiscount_e" type="text" value="'+ $(this).parent().next().next().next().next().next().next().next().next().next().text() + '"/>%</td>'
			 		 +'<td data-class="expand"> <a href="#" onclick="javascript:$(this).parent().parent().remove();">删除</a></td></tr>');
		 }
	 })
});
$("#product_remove").click(function(){
	$("#product_selected").children("tbody").children("tr").remove();
});
$("#product_edit_remove").click(function(){
	$("#product_edit").children("tbody").children("tr").remove();
});
//保存报价单
$("#btn_submit").click(function(){
	var map ={"_method":"post"};
	var areaAdd = $("#area_add").val();
	var distributorName = $("#distributorName_add").val();
	var products = "";
	$("input[name='proDiscount']").each(function(){
		products = products + $(this).parent().prev().prev().prev().text() + "_" + $(this).val() + ",";
	})
	if(products != ""){
		products = products.substring(0,products.length-1);
	}
	
	var display_province = $("#display_province").val();
	var state_add = $("#state_add").val();
	
	map.areaAdd = areaAdd;
	map.distributorCode = distributorName;
	map.products = products;
	map.display_province = display_province;
	map.state_add = state_add;
	
	$.ajax({
		type : "POST",
		url : '/portal/quotation!addQuotation.action',
		data: map,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("保存成功！");
				location.href="/portal/quotation!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
});

//修改报价单
$("#btn_edit").click(function(){
	var map ={"_method":"post"};
	var areaEdit = $("#area_edit").val();
	var products = "";
	$("input[name='proDiscount_e']").each(function(){
		products = products + $(this).parent().prev().prev().prev().text() + "_" + $(this).val() + ",";
	})
	if(products != ""){
		products = products.substring(0,products.length-1);
	}
	var display_province_edit = $("#display_province_edit").val();
	var state_edit = $("#state_edit").val();
	var id = $("#editId").val();
	var serviceCode = $("#serviceCode").val();
	map.areaEdit = areaEdit;
	map.products = products;
	map.display_province = display_province_edit;
	map.state_edit = state_edit;
	map.serviceCode = serviceCode;
	map.id = id; 
	$.ajax({
		type : "POST",
		url : '/portal/quotation!editQuotation.action',
		data: map,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("修改成功！");
				location.href="/portal/quotation!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
});

