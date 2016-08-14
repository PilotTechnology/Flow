//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//ADD
$("#btn_submit").click(function(){
    var distrbutorCode=$("#distrbutorCode_add").val();
    var password=$("#password_add").val();
    var confirmPassword=$("#confirm_password_add").val();
    var nickname=$("#nickname_add").val();
    var company=$("#company_add").val();
    var linkman=$("#linkman_add").val();
    var phone=$("#phone_add").val();
    var email=$("#email_add").val();
    var channelType=$("#channelType_add").val();
    var coopModel=$("#coopModel_add").val();
    var smsContent=$("#smsContent_add").val();
    var level=$("#level_add").val();
    var callbackUrl=$("#callbackUrl_add").val();
    var confiningIp=$("#confiningIp_add").val();
    var state=$("#state_add").val();
    
    if (distrbutorCode.length == 0) {
    	alert("分销商编码不能为空！");
    	return;
    }
    if (password.length == 0) {
    	alert("密码不能为空！");
    	return;
    }
    if (confirmPassword.length == 0) {
    	alert("确认密码不能为空！");
    	return;
    }
    if (password != confirmPassword) {
    	alert("密码不一致！");
    	return;
    }
    if (nickname.length == 0) {
    	alert("昵称不能为空！");
    	return;
    }
    if (company.length == 0) {
    	alert("公司不能为空！");
    	return;
    }
    if (linkman.length == 0) {
    	alert("联系人不能为空！");
    	return;
    }
    if (phone.length == 0) {
    	alert("电话号码不能为空！");
    	return;
    }
    if (email.length == 0) {
    	alert("邮箱不能为空！");
    	return;
    }
    if (callbackUrl.length == 0) {
    	alert("回调接口不能为空！");
    	return;
    }
    if (confiningIp.length == 0) {
    	alert("ip白名单不能为空！");
    	return;
    }
    $.post(
    	"/portal/distributor!addDistributor.action",
    	{"distrbutorCode":distrbutorCode,
   		 "userCode":distrbutorCode,
   		 "user.userCode":distrbutorCode,
    	 "user.password":password,
         "user.nickname":nickname,
   	     "user.linkman":linkman,
         "user.phone":phone,
    	 "user.email":email,
    	 "company":company,
    	 "channelType":channelType,
         "coopModel":coopModel,
   	     "smsContent":smsContent,
   	     "level":level,
   	     "callbackUrl":callbackUrl,
   	     "confiningIp":confiningIp,
   	     "state":state,
    	},
    	function(json){
    		if(json.result == "success"){
    			alert("添加成功！");
    			location.href="/portal/distributor!selectPage.action";
   				$("#btn_submit").attr("data-dismiss", "modal");
   			}else{
   				alert(json.errMsg);
   				return false;
   			}
   		},"json"
   	);
})

//TO SEARCH
function toSearch(distributorCode){
	$.post("/portal/distributor!toSearch.action",{"distrbutorCode":distributorCode},function(json){
	    $('#myModal_edit').modal();
	    $("#id_edit").val(json.distributor.id);
	    $("#distrbutorCode_edit").val(json.distributor.distrbutorCode);
	    $("#nickname_edit").val(json.distributor.user.nickname);
	    $("#company_edit").val(json.distributor.company);
	    $("#linkman_edit").val(json.distributor.user.linkman);
	    $("#phone_edit").val(json.distributor.user.phone);
	    $("#email_edit").val(json.distributor.user.email);
	    $("#channelType_edit").val(json.distributor.channelType);
	    $("#coopModel_edit").val(json.distributor.coopModel);
	    $("#smsContent_edit").val(json.distributor.smsContent);
	    $("#level_edit").val(json.distributor.level);
	    $("#callbackUrl_edit").val(json.distributor.callbackUrl);
	    $("#confiningIp_edit").val(json.distributor.confiningIp);
	    $("#state_edit").val(json.distributor.state);
    },"json");
}

//UPDATE
$("#btn_update").click(function(){
	var id=$("#id_edit").val();
	var distrbutorCode=$("#distrbutorCode_edit").val();
    var password=$("#password_edit").val();
    var confirmPassword=$("#confirm_password_edit").val();
    var nickname=$("#nickname_edit").val();
    var company=$("#company_edit").val();
    var linkman=$("#linkman_edit").val();
    var phone=$("#phone_edit").val();
    var email=$("#email_edit").val();
    var channelType=$("#channelType_edit").val();
    var coopModel=$("#coopModel_edit").val();
    var smsContent=$("#smsContent_edit").val();
    var level=$("#level_edit").val();
    var callbackUrl=$("#callbackUrl_edit").val();
    var confiningIp=$("#confiningIp_edit").val();
    var state=$("#state_edit").val();
    
    if (distrbutorCode.length == 0) {
    	alert("分销商编码不能为空！");
    	return;
    }
    if (nickname.length == 0) {
    	alert("昵称不能为空！");
    	return;
    }
    if (company.length == 0) {
    	alert("公司不能为空！");
    	return;
    }
    if (linkman.length == 0) {
    	alert("联系人不能为空！");
    	return;
    }
    if (phone.length == 0) {
    	alert("电话号码不能为空！");
    	return;
    }
    if (email.length == 0) {
    	alert("邮箱不能为空！");
    	return;
    }
    if (callbackUrl.length == 0) {
    	alert("回调接口不能为空！");
    	return;
    }
    if (confiningIp.length == 0) {
    	alert("ip白名单不能为空！");
    	return;
    }
    
    $.post(
        "/portal/distributor!editDistributor.action",
       	{"id":id,
         "distrbutorCode":distrbutorCode,
         "userCode":distrbutorCode,
   		 "user.userCode":distrbutorCode,
         "user.password":password,
         "user.nickname":nickname,
   	     "user.linkman":linkman,
         "user.phone":phone,
      	 "user.email":email,
       	 "company":company,
       	 "channelType":channelType,
         "coopModel":coopModel,
   	     "smsContent":smsContent,
   	     "level":level,
   	     "callbackUrl":callbackUrl,
         "confiningIp":confiningIp,
   	     "state":state,
   	    },
       	function(json){
     		if(json.result == "success"){
      			alert("修改成功！");
       			location.href="/portal/distributor!selectPage.action";
       			$("#btn_submit").attr("data-dismiss", "modal");
       		}else{
       			alert(json.errMsg);
       			return false;
   			}
   		},"json"
   	);
})