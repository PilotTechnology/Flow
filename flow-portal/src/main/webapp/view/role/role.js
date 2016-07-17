//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//ADD
$("#btn_submit").click(function(){
	var Role={"_method":"post"};
	var roleCode=$("#roleCode_add").val();
    var roleName=$("#roleName_add").val();
	Role.roleCode = roleCode;
    Role.roleName = roleName;
	$.ajax({
		type : "POST",
		url : '/portal/system/role!addRole.action',
		data: Role,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("添加成功！");
				location.href="/portal/system/role!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
	
})
//TO UPDATE
function toEdit(roleCode){
	$.post("/portal/system/role!toEdit.action",{"roleCode":roleCode},function(role){
	    $('#myModal_edit').modal();
	    $("#roleCode_edit").val(role.sysRole.roleCode);
	    $("#roleName_edit").val(role.sysRole.roleName);
	    $("#id_edit").val(role.sysRole.id);
    },"json");
}
//UPDATE
$("#btn_update").click(function(){
	var Role={"_method":"post"};
	var roleCode=$("#roleCode_edit").val();
    var roleName=$("#roleName_edit").val();
    var id=$("#id_edit").val();
    Role.id = id;
	Role.roleCode = roleCode;
    Role.roleName = roleName;
	$.ajax({
		type : "POST",
		url : '/portal/system/role!editRole.action',
		data: Role,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("修改成功！");
				location.href="/portal/system/role!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
	
})

//TO GRANT
function bindMenu(roleCode) {
	$.post("/portal/system/role!toGrant.action",{"roleCode":roleCode},function(role){
	    $('#myModal_grant').modal();
	    $("#roleCode_grant").text(role.roleCode);
	    $("#roleName_grant").text(role.roleName);
	    $("#id_grant").val(role.id);
	    
	    var data = role.menuList;
	    var tree = $.fn.zTree.init($("#menuTree"), setting, data);
	    tree.expandAll(true);
	    
    },"json");
}

//REMOVE
function removeRole(roleCode,id){
	if(confirm("您确定要删除该角色吗？")){
		$.post("/portal/system/role!delRole.action",{"roleCode":roleCode,"id":id},function(data){
	    	if(data.result == "success"){
	    		alert("删除成功！");
				location.href="/portal/system/role!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
	    },"json");
	}
}

var setting = {
		view: { dblClickExpand: false , showLine: true , selectedMulti: false},
		data: { key:{name:"menuName"} , simpleData: { enable:true , idKey: "id" , pIdKey: "pId", rootPId: ""}	},
		check:{ chkStyle: "checkbox"}
	};
