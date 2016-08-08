//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//TO SEARCH
function toSearch(){
	$.post("/portal/distributor!toSearchWithInfo.action",{"distributorInfo":$("#distributorInfo_add").val()},function(json){
	    if (json.distributor == null) {
	    	$("#distributorCode_add").val("");
		    $("#nickname_add").val("");
		    $("#linkman_add").val("");
		    $("#company_add").val("");
		    $("#balanceBeforeRecharge_add").val("");
	    } else {
	    	$("#distributorCode_add").val(json.distributor.distrbutorCode);
		    $("#nickname_add").val(json.distributor.user.nickname);
		    $("#linkman_add").val(json.distributor.user.linkman);
		    $("#company_add").val(json.distributor.company);
		    $("#balanceBeforeRecharge_add").val(json.distributor.balance);
	    }
    },"json");
}

//ADD
$("#btn_submit").click(function(){
	var RechargeFlow={"_method":"post"};
	if($("#distributorCode_add").val().length == 0 || $("#nickname_add").val().length == 0){
		alert("分销商信息不能为空！");
		return;
	}
	
	RechargeFlow.distributorCode = $("#distributorCode_add").val();
	RechargeFlow.balanceBeforeRecharge = $("#balanceBeforeRecharge_add").val();
	RechargeFlow.recharge = $("#recharge_add").val();
	RechargeFlow.type = $("#type_add").val();
	
	$.ajax({
		type : "POST",
		url : '/portal/rechargeFlow!addRechargeFlow.action',
		data: RechargeFlow,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("添加成功！");
				location.href="/portal/rechargeflow!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
			$("#btn_submit").attr("data-dismiss", "modal");
		}
	});
})