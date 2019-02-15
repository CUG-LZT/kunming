$.extend($.fn.validatebox.defaults.rules, {    
    equals: {    
        validator: function(value,param){    
            return value == $(param[0]).val();    
        },    
        message: '两次密码不一致！'   
    }    
}); 
function changePsd() {
	var userPsd = $('#userPsd').val();
	var reuserPsd = $('#renew_userPsd').val();
	if (userPsd == reuserPsd) {
		$('#changePsd').form('submit', {
			url : "updatePsd.json",
			success : function(data) {
				//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
				var msg = $.parseJSON(data);
				if (msg.code == 200) {
					$.messager.alert('提示', msg.msg);
					//关闭窗口 刷新数据
					$('#odd_userPsd').textbox('setValue', '');
					$('#userPsd').val("");
					$('#renew_userPsd').val("");
				} else {
					$.messager.alert('提示', msg.msg);
				}
			},
			onLoadError : function() {
				$.messager.alert('提示', "修改失败！");
			}
		});
	} else {
		$.messager.alert('警告', "两次密码不一致！");
	}
}
