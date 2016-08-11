//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
   initRole($("#roleCode_add") , null)
});

function initRole(sel,defValue){
	$.ajax({
		type : "POST",
		url  : '/portal/system/role!findAll.action',
		async: false,
		success:function(data){
			$(data).each(function(v , role){
				if(role.roleCode == defValue){
					sel.append('<option selected="selected" value=' + role.roleCode + '>' +role.roleName + '</option>')
				}else{
					sel.append('<option value=' + role.roleCode + '>' + role.roleName + '</option>')
				}
			});
		}
	}) 
}

//ADD
$("#btn_submit").click(function(){
	var UserInfo={"_method":"post"};
	var roleCode=$("#roleCode_add").val();
	var userCode=$("#userCode_add").val();
	var nickname=$("#nickname_add").val();
	var linkman=$("#linkman_add").val();
	var phone=$("#phone_add").val();
	var email=$("#email_add").val();
    
	UserInfo.roleCode = roleCode;
	UserInfo.userCode = userCode;
	UserInfo.nickname = nickname;
	UserInfo.linkman = linkman;
	UserInfo.phone = phone;
	UserInfo.email = email;
	$.ajax({
		type : "POST",
		url : '/portal/system/user!addUser.action',
		data: UserInfo,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("添加成功！");
				location.href="/portal/system/user!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
	
})
//TO UPDATE
function toEdit(userCode){
	$.post("/portal/system/user!toEdit.action",{"userCode":userCode},function(user){
	    $('#myModal_edit').modal();
	    $("#userCode_edit").val(user.userCode);
	    $("#nickname_edit").val(user.nickname);
	    $("#linkman_edit").val(user.linkman);
	    $("#phone_edit").val(user.phone);
	    $("#email_edit").val(user.email);
	    $("#userCode_edit").val(user.userCode);
	    initRole($("#roleCode_edit"),user.roleCode)
    },"json");
}
//UPDATE
$("#btn_update").click(function(){
	var UserInfo={"_method":"post"};
	var roleCode=$("#roleCode_edit").val();
	var userCode=$("#userCode_edit").val();
	var nickname=$("#nickname_edit").val();
	var linkman=$("#linkman_edit").val();
	var phone=$("#phone_edit").val();
	var email=$("#email_edit").val();
    
	UserInfo.roleCode = roleCode;
	UserInfo.userCode = userCode;
	UserInfo.nickname = nickname;
	UserInfo.linkman = linkman;
	UserInfo.phone = phone;
	UserInfo.email = email;
	$.ajax({
		type : "POST",
		url : '/portal/system/user!editUser.action',
		data: UserInfo,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("修改成功！");
				location.href="/portal/system/user!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
	
})

//REMOVE
function removeUser(userCode){
	if(confirm("您确定要删除该用户吗？")){
		$.post("/portal/system/user!delUser.action",{"userCode":userCode},function(data){
	    	if(data.result == "success"){
	    		alert("删除成功！");
				location.href="/portal/system/user!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
	    },"json");
	}
}