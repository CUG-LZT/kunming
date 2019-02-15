
//格式化操作栏
function opera(value, row, index) {
	var result = '';
	if(row.resType != "button"){
		result += '<a href="#" style="color:rgb(34,139,34)" onclick="openAddResDialog(' +
			+ row.resId
			+',\''
			+row.resType
			+'\',\''
			+row.resParentIds
			+'\''
			+')">添加子节点</a>&nbsp;|&nbsp;';
	}
	result += '<a href="#" style="color:rgb(0,125,220)" onclick="openEditResDialog('
			+ row.resId
			+ ',\''
			+ row.resType
			+ ' \',\''
			+ row.resName
			+ '\',\''
			+ row.resUrl
			+ '\',\''
			+ row.resPermission
			+ '\','
			+ row.resParentId
			+ ',\''
			+ row.resParentIds
			+ ' \',\''
			+ row.resIcon
			+ '\''
			+ ')">编辑</a>&nbsp;|&nbsp;'
			+ '<a href="#" style="color:red" onclick="deleteOneRes('
			+ row.resId + ',\'  ' + row.resName + ' \')">删除</a>';

	return result;
}

//新增资源
function addChild(){
	//弹出对话框--设置父编号以及父编号列表--填写数据--获取数据（id由后台生成-->先根据父id获取已有的id然后加一；注意是个位数范围从0-9）
	$('#add_res_dlg_form').form('submit', {
		url : "createResource.json",
		success : function(data) {
			//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
			var msg = $.parseJSON(data);
			if (msg.code == 200) {
				$.messager.alert('提示', msg.msg);
				//关闭窗口 刷新数据
				$('#add_dlg').dialog('close');
				$('#ResourceTable').treegrid('reload');
			} else {
				$.messager.alert('提示', msg.msg);
			}
		},
		onLoadError : function() {
			$.messager.alert('提示', "添加失败！");
		}
	});
}

//打开添加的窗口
function openAddResDialog(resId , resType ,  resParentIds) {
	if(resId == 1){
		$('#add_dialog_resType').textbox('setValue', 'menu');
	}else if(resType == "menu"){
		$('#add_dialog_resType').textbox('setValue', 'page');
	}else if(resType == "page"){
		$('#add_dialog_resType').textbox('setValue', 'button');
	}
	$('#add_dialog_resParentId').textbox('setValue', resId);
	$('#add_dialog_resParentIds').textbox('setValue', resParentIds+resId+'/');
	$('#add_dialog_resAvailable').textbox('setValue', 'TRUE');
	$('#add_dialog_resName').textbox('setValue', '');
	$('#add_dialog_resUrl').textbox('setValue', '');
	$('#add_dialog_resPermission').textbox('setValue', '');
	$('#add_dialog_resIcon').textbox('setValue', '');
	$('#add_dlg').dialog('open');
}

//打开编辑的窗口
function openEditResDialog(resId, resType, resName, resUrl, resPermission,resParentId, resParentIds,resIcon,) {
	$('#edit_dialog_resId').textbox('textbox').attr('disabled', true); //设置输入框为禁用
	$('#edit_dialog_resParentId').textbox('textbox').attr('disabled', true); //设置输入框为禁用
	$('#edit_dialog_resParentIds').textbox('textbox').attr('disabled', true); //设置输入框为禁用
	$('#edit_dialog_resAvailable').textbox('textbox').attr('disabled', true); //设置输入框为禁用


	//选择框只用将testbox换为combobox
	$('#edit_dialog_resId').textbox('setValue', resId);
	$('#edit_dialog_resType').textbox('setValue', resType);
	$('#edit_dialog_resName').textbox('setValue', resName);
	$('#edit_dialog_resUrl').textbox('setValue', resUrl);
	$('#edit_dialog_resPermission').textbox('setValue', resPermission);
	$('#edit_dialog_resParentId').textbox('setValue', resParentId);
	$('#edit_dialog_resParentIds').textbox('setValue', resParentIds);
	$('#edit_dialog_resIcon').textbox('setValue', resIcon);
	$('#edit_dialog_resAvailable').textbox('setValue', 'TRUE');

	$('#edit_dlg').dialog('open');
}


//编辑用户
function updateResource() {
	$('#edit_res_dlg_form').form('submit', {
		url : "updateResource.json",
		success : function(data) {
			//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
			var msg = $.parseJSON(data);
			if (msg.code == 200) {
				$.messager.alert('提示', msg.msg);
				//关闭窗口 刷新数据
				$('#edit_dlg').dialog('close');
				$('#ResourceTable').treegrid('reload');
			} else {
				$.messager.alert('提示', msg.msg);
			}
		},
		onLoadError : function() {
			$.messager.alert('提示', "编辑失败！");
		}
	});
}

//删除单个
function deleteOneRes(resId, resName) {
	$.messager.confirm('删除', '您确认想要删除' + resName + '资源吗？', function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : "deleteOneRes.json",
				data : {
					"resId" : resId
				},
				dataType : "json",
				traditional : true,//防止深度序列化
				success : function(data) {
					//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
					var msg = data;
					if (msg.code == 200) {
						$.messager.alert('提示', msg.msg);
						//刷新数据
						$('#ResourceTable').treegrid('reload');

					} else {
						$.messager.alert('提示', msg.msg);
					}
				},
				error : function(err) {
					$.messager.alert('提示', "删除" + resName + "失败！");
				}
			});
		}
	});
}


function fixWidth(percent)
{
	return document.body.clientWidth * percent ; //这里你可以自己做调整
}