//TO-ADD
$("#addButton").click(function () {
   $('#myModal_add').modal();
});

//ADD
$("#btn_submit").click(function(){
	var Product={"_method":"post"};
    var productCode=$("#productCode_add").val();
    var productName=$("#productName_add").val();
    var operatorCode=$("#operatorCode_add").val();
    var providerCode=$("#providerCode_add").val();
    var proProductCode=$("#proProductCode_add").val();
    var provinceCode=$("#provinceCode_add").val();
    var enableArea=$("#enableArea_add").val();
    var enableType=$("#enableType_add").val();
    var priority=$("#priority_add").val();
    var size=$("#size_add").val();
    var price=$("#price_add").val();
    var discount=$("#discount_add").val();
    var state=$("#state_add").val();
    var description=$("#description_add").val();
    
    if (productCode.length == 0) {
    	alert("流量包编码不能为空！");
    	return;
    }
    if (productName.length == 0) {
    	alert("流量包名称不能为空！");
    	return;
    }
    if (proProductCode.length == 0) {
    	alert("供应商流量包编码不能为空！");
    	return;
    }
    if (size.length == 0) {
    	alert("流量包大小不能为空！");
    	return;
    }
    if (price.length == 0) {
    	alert("流量包价格不能为空！");
    	return;
    }
    if (discount.length == 0) {
    	alert("流量包折扣不能为空！");
    	return;
    }
    
    Product.productCode =productCode;
    Product.prodcutName =productName;
    Product.operatorCode =operatorCode;
    Product.providerCode =providerCode;
    Product.proProductCode =proProductCode;
    Product.provinceCode =provinceCode;
    Product.enableArea =enableArea;
    Product.enableType =enableType;
    Product.enableType =enableType;
    Product.priority=priority;
    Product.size =size;
    Product.price =price;
    Product.discount =discount;
    Product.purchased =price*discount/100;
    Product.state =state;
    Product.description =description;
    
	$.ajax({
		type : "POST",
		url : '/portal/product!addProduct.action',
		data: Product,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("添加成功！");
				location.href="/portal/product!selectPage.action";
				$("#btn_submit").attr("data-dismiss", "modal");
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
})

//TO SEARCH
function toSearch(id){
	$.post("/portal/product!toSearch.action",{"id":id},function(json){
	    $('#myModal_edit').modal();
	    $("#id_edit").val(json.product.id);
	    $("#productCode_edit").val(json.product.productCode);
	    $("#productName_edit").val(json.product.prodcutName);
	    $("#operatorCode_edit").val(json.product.operatorCode);
	    $("#providerCode_edit").val(json.product.providerCode);
	    $("#proProductCode_edit").val(json.product.proProductCode);
	    $("#provinceCode_edit").val(json.product.provinceCode);
	    $("#enableArea_edit").val(json.product.enableArea);
	    $("#enableType_edit").val(json.product.enableType);
	    $("#priority_edit").val(json.product.priority);
	    $("#size_edit").val(json.product.size);
	    $("#price_edit").val(json.product.price);
	    $("#discount_edit").val(json.product.discount);
	    $("#state_edit").val(json.product.state);
	    $("#description_edit").val(json.product.description);
    },"json");
}

//UPDATE
$("#btn_update").click(function(){
	var Product={"_method":"post"};
	var id=$("#id_edit").val();
    var productCode=$("#productCode_edit").val();
    var productName=$("#productName_edit").val();
    var operatorCode=$("#operatorCode_edit").val();
    var providerCode=$("#providerCode_edit").val();
    var proProductCode=$("#proProductCode_edit").val();
    var provinceCode=$("#provinceCode_edit").val();
    var enableArea=$("#enableArea_edit").val();
    var enableType=$("#enableType_edit").val();
    var priority=$("#priority_edit").val();
    var size=$("#size_edit").val();
    var price=$("#price_edit").val();
    var discount=$("#discount_edit").val();
    var state=$("#state_edit").val();
    var description=$("#description_edit").val();
    
    if (productCode.length == 0) {
    	alert("流量包编码不能为空！");
    	return;
    }
    if (productName.length == 0) {
    	alert("流量包名称不能为空！");
    	return;
    }
    if (proProductCode.length == 0) {
    	alert("供应商流量包编码不能为空！");
    	return;
    }
    if (size.length == 0) {
    	alert("流量包大小不能为空！");
    	return;
    }
    if (price.length == 0) {
    	alert("流量包价格不能为空！");
    	return;
    }
    if (discount.length == 0) {
    	alert("流量包折扣不能为空！");
    	return;
    }
    
    Product.id =id;
    Product.productCode =productCode;
    Product.prodcutName =productName;
    Product.operatorCode =operatorCode;
    Product.providerCode =providerCode;
    Product.proProductCode =proProductCode;
    Product.provinceCode =provinceCode;
    Product.enableArea =enableArea;
    Product.enableType =enableType;
    Product.enableType =enableType;
    Product.priority=priority;
    Product.size =size;
    Product.price =price;
    Product.discount =discount;
    Product.purchased =price*discount/100;
    Product.state =state;
    Product.description =description;
    
	$.ajax({
		type : "POST",
		url : '/portal/product!editProduct.action',
		data: Product,
		async : false,
		success : function(data) {
			if(data.result == "success"){
				alert("修改成功！");
				location.href="/portal/product!selectPage.action";
				$("#btn_update").attr("data-dismiss", "modal");
			}else{
				alert(data.errMsg);
				return false;
			}
		}
	});
})