

$("#orderType").change(function(){
	if($("#orderType").val() == 2){//文件导入
		$("#phoneDiv").hide();
		$("#fileDiv").show();
	}else{
		$("#phoneDiv").show();
		$("#fileDiv").hide();
	}
})

//ADD
$("#btn_submit").click(function(){
	var Order={"_method":"post"};
	var phone=$("#phone").val();
	var discount = $("#discount").val();
    var productCode = $("#productCode").val();
    var distributorCode = $("#distributorCode").val();
    
    Order.phone = phone;
    Order.discount = discount;
    Order.productCode = productCode;
    Order.distributorCode = distributorCode;
	$.ajax({
		type : "POST",
		url : '/portal/order!batchInsert.action',
		data: Order,
		async : false,
		success : function(data) {
			if(data.code == "success"){
				alert("批量充值成功！");
				location.href="/portal/order!selectPage.action";
			}else{
				alert(data.msg);
				return false;
			}
		}
	});
	
})