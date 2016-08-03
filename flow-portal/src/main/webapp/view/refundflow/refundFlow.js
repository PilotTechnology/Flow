//TO SEARCH
function toSearch(providerCode){
	$.post("/portal/provider!toEdit.action",{"providerCode":providerCode},function(json){
	    $('#myModal_edit').modal();
	    $("#providerCode_edit").val(json.provider.providerCode);
	    $("#providerName_edit").val(json.provider.name);
	    $("#description_edit").val(json.provider.description);
	    $("#id_edit").val(json.provider.id);
    },"json");
}