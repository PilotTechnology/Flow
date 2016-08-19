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
				$("#providerCode_add").append('<option value=' + provider.providerCode + '>' + provider.name + '</option>')
				$("#providerCode_edit").append('<option value=' + provider.providerCode + '>' + provider.name + '</option>')
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
				if(operator.id == defValue){
					$("#operatorCode").append('<option selected="selected" value=' + operator.id + '>' + operator.operatorName + '</option>')
				}else{
					$("#operatorCode").append('<option value=' + operator.id + '>' + operator.operatorName + '</option>')
				}
				$("#operatorCode_add").append('<option value=' + operator.id + '>' + operator.operatorName + '</option>')
				$("#operatorCode_edit").append('<option value=' + operator.id + '>' + operator.operatorName + '</option>')
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
				$("#provinceCode_add").append('<option value=' + pro.id + '>' + pro.provinceName + '</option>')
				$("#provinceCode_edit").append('<option value=' + pro.id + '>' + pro.provinceName + '</option>')
			});
		}
	}) 
}

function initDistributor(defValue){
	$.ajax({
		type : "POST",
		url  : '/pub/distributor!list.action',
		async: false,
		success:function(data){
			$(data).each(function(v , distributor){
				if(distributor.distrbutorCode == defValue){
					$("#distributorCode").append('<option selected="selected" value=' + distributor.distrbutorCode + '>' + distributor.company + '</option>')
				}else{
					$("#distributorCode").append('<option value=' + distributor.distrbutorCode + '>' + distributor.company + '</option>')
				}
			});
		}
	}) 
}