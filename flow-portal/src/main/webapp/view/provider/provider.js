//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//ADD
$("#btn_submit").click(function(){
	var Provider={"_method":"post"};
	var providerCode=$("#providerCode_add").val();
    var providerName=$("#providerName_add").val();
    var description=$("#description_add").val();
    Provider.providerCode = providerCode;
    Provider.name = providerName;
    Provider.description = description;
	$.ajax({
		type : "POST",
		url : '/portal/provider!addProvider.action',
		data: Provider,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("添加成功！");
				location.href="/portal/provider!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
})
//TO UPDATE
function toEdit(providerCode){
	$.post("/portal/provider!toEdit.action",{"providerCode":providerCode},function(json){
	    $('#myModal_edit').modal();
	    $("#providerCode_edit").val(json.provider.providerCode);
	    $("#providerName_edit").val(json.provider.name);
	    $("#description_edit").val(json.provider.description);
	    $("#id_edit").val(json.provider.id);
    },"json");
}
//UPDATE
$("#btn_update").click(function(){
	var Provider={"_method":"post"};
	var id=$("#id_edit").val();
	var providerCode=$("#providerCode_edit").val();
    var providerName=$("#providerName_edit").val();
    var description=$("#description_edit").val();
    Provider.id = id;
    Provider.providerCode = providerCode;
    Provider.name = providerName;
    Provider.description = description;
	$.ajax({
		type : "POST",
		url : '/portal/provider!editProvider.action',
		data: Provider,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("修改成功！");
				location.href="/portal/provider!selectPage.action";
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
	
})