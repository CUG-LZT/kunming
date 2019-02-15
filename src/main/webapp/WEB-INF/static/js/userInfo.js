$(function () { 
	var address = $('#address').html().replace(/(^\s*)|(\s*$)/g, "");
	$('#dialog_userAddress').textbox('setValue', address);//combobox
	var role = toRole($('#role').html().replace(/(^\s*)|(\s*$)/g, ""));
	$('#dialog_userRole').textbox('setValue', role);//combobox
	var date = dateFormatter($('#date').html().replace(/(^\s*)|(\s*$)/g, ""));
	$('#dialog_userCreateTime').textbox('setValue', date);
	
});
//格式化日期栏
function dateFormatter(value) {
	var date = new Date(value);
	var year = date.getFullYear().toString();
	var month = (date.getMonth() + 1);
	var day = date.getDate().toString();
	var hour = date.getHours().toString();
	var minutes = date.getMinutes().toString();
	var seconds = date.getSeconds().toString();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (minutes < 10) {
		minutes = "0" + minutes;
	}
	if (seconds < 10) {
		seconds = "0" + seconds;
	}
	return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":"
			+ seconds;
}
//格式化角色栏
function toRole(value) {
	var role = "";
	if (value == 3) {
		role = "超级管理员";
	} else if (value == 2) {
		role = "管理员";
	} else {
		role = "普通用户";
	}
	return role;
}
//格式化地址栏
function toAddress(value) {
	var city = "其他,北京市,天津市,河北省,山西省,内蒙古自治区,辽宁省,吉林省,黑龙江省,上海市,江苏省,浙江省,安徽省,福建省,江西省,山东省,"
			+ "河南省,湖北省,湖南省,广东省,海南省,广西壮族自治区,甘肃省,陕西省,新疆维吾尔自治区,青海省,宁夏回族自治区,重庆市,四川省,"
			+ "贵州省,云南省,西藏自治区,台湾省,澳门特别行政区,香港特别行政区,海外";
	var citys = city.split(',');
	return citys[value];
}

//编辑用户
function editUser() {
	$('#userInfoForm').form('submit', {
		url : "updateUser.json",
		success : function(data) {
			//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
			var msg = $.parseJSON(data);
			if (msg.code == 200) {
				$.messager.alert('提示', msg.msg);
			} else {
				$.messager.alert('提示', msg.msg);
			}
		},
		onLoadError : function() {
			$.messager.alert('提示', "编辑失败！");
		}

	});
}
