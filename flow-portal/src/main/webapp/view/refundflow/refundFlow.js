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
		}
	});
})