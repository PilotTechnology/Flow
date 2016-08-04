//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//TO SEARCH
function toSearch(){
	$.post("/portal/order!toSearch.action",{"orderCode":$("#orderCode_add").val()},function(json){
	    $("#phone_add").val(json.orderBean.phone);
	    $("#prodcutName_add").val(json.orderBean.size);
	    $("#purchased_add").val(json.orderBean.price);
	    $("#distributorName_add").val(json.orderBean.distributorCode);
	    $("#providerName_add").val(json.orderBean.providerName);
    },"json");
}

//ADD
$("#btn_submit").click(function(){
	var RefundFlow={"_method":"post"};
	if($("#orderCode_add").val().length == 0){
		alert("订单号不能为空！");
		return;
	}
	if($("#phone_add").val().length == 0 || $("#purchased_add").val().length == 0){
		alert("没有此订单");
		return;
	}
	
	RefundFlow.orderCode = $("#orderCode_add").val();
	RefundFlow.prodcutName = $("#prodcutName_add").val();
	RefundFlow.purchased = $("#purchased_add").val();
	RefundFlow.distributorName = $("#distributorName_add").val();
	RefundFlow.providerName = $("#providerName_add").val();
	RefundFlow.phone = $("#phone_add").val();
	RefundFlow.providerIsRefund = $("#providerIsRefund_add").val();
	
	$.ajax({
		type : "POST",
		url : '/portal/refundFlow!addRefundFlow.action',
		data: RefundFlow,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("添加成功！");
				location.href="/portal/refundflow!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
			$("#btn_submit").attr("data-dismiss", "modal");
		}
	});
})

//TO UPDATE
function toEdit(id){
	$.post("/portal/refundFlow!toSearch.action",{"id":id},function(json){
	    $('#myModal_edit').modal();
	    $("#orderCode_edit").val(json.refundFlow.orderCode);
	    $("#phone_edit").val(json.refundFlow.phone);
	    $("#prodcutName_edit").val(json.refundFlow.prodcutName);
	    $("#purchased_edit").val(json.refundFlow.purchased);
	    $("#distributorName_edit").val(json.refundFlow.distributorName);
	    $("#providerName_edit").val(json.refundFlow.providerName);
	    $("#providerIsRefund_edit").val(json.refundFlow.providerIsRefund);
	    $("#id_edit").val(json.refundFlow.id);
    },"json");
}
//UPDATE
$("#btn_update").click(function(){
	var RefundFlow={"_method":"post"};
	var id=$("#id_edit").val();
	var orderCode=$("#orderCode_edit").val();
    var phone=$("#phone_edit").val();
    var prodcutName=$("#prodcutName_edit").val();
    var purchased=$("#purchased_edit").val();
    var distributorName=$("#distributorName_edit").val();
    var providerName=$("#providerName_edit").val();
    var providerIsRefund=$("#providerIsRefund_edit").val();
    RefundFlow.id = id;
    RefundFlow.orderCode = orderCode;
    RefundFlow.phone = phone;
    RefundFlow.prodcutName = prodcutName;
    RefundFlow.purchased = purchased;
    RefundFlow.distributorName = distributorName;
    RefundFlow.providerName = providerName;
    RefundFlow.providerIsRefund = providerIsRefund;
	$.ajax({
		type : "POST",
		url : '/portal/refundFlow!editRefundFlow.action',
		data: RefundFlow,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("修改成功！");
				location.href="/portal/refundflow!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
	
})