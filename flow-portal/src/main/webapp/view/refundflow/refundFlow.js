//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//TO SEARCH
function toSearch(){
	$.post("/portal/order!toSearch.action",{"orderCode":$("#orderCode_add").val()},function(json){
//	    $('#myModal_edit').modal();
	    $("#phone_add").val(json.provider.providerCode);
	    $("#prodcutName_add").val(json.provider.name);
	    $("#purchased_add").val(json.provider.description);
	    $("#id_edit").val(json.provider.id);
    },"json");
}