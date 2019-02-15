
function register() {
	var userPsd = $('#userPsd').val();
	var reuserPsd = $('#reuserPsd').val();
	if($('#userAddress').combobox('getValue') == null || $('#userAddress').combobox('getValue') == ""){
		$('#userAddress').combobox('setValue', 0);
	}
	if(userPsd == reuserPsd){
		$('#userInfoForm').form('submit', {
			url : "sys/user/insertUser.json",
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
				$.messager.alert('提示', "添加失败！");
			}

		});
	}else{
		$.messager.alert('提示', "密码不一致！");
	}
}
