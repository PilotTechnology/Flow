//get providerList

function initProvider(defValue){
	$.ajax({
		type : "POST",
		url  : '/pub/provider!list.action',
		async: false,
		success:function(data){
			$(data).each(function(v , provider){
				if(provider.providerCode == defValue){
					$("#providerCode").append('<option selected="selected" value=' + provider.providerCode + '>' + provider.name + '</option>')
				}else{
					$("#providerCode").append('<option value=' + provider.providerCode + '>' + provider.name + '</option>')
				}
			});
		}
	}) 
}

function initOperator(defValue){
	$.ajax({
		type : "POST",
		url  : '/pub/operator!list.action',
		async: false,
		success:function(data){
			$(data).each(function(v , operator){
				if(operator.operatorCode == defValue){
					$("#operatorCode").append('<option selected="selected" value=' + operator.operatorCode + '>' + operator.operatorName + '</option>')
				}else{
					$("#operatorCode").append('<option value=' + operator.operatorCode + '>' + operator.operatorName + '</option>')
				}
			});
		}
	}) 
}

function initProvince(defValue){
	$.ajax({
		type : "POST",
		url  : '/pub/province!list.action',
		async: false,
		success:function(data){
			$(data).each(function(v , pro){
				if(pro.id == defValue){
					$("#provinceCode").append('<option selected="selected" value=' + pro.id + '>' + pro.provinceName + '</option>')
				}else{
					$("#provinceCode").append('<option value=' + pro.id + '>' + pro.provinceName + '</option>')
				}
			});
		}
	}) 
}