
//格式化操作栏
function opera(value, row, index) {
	return '<a href="#" style="color:rgb(0,125,220)" onclick="openEditRoleDialog('
			+ row.roleId
			+ ',\''
			+ row.roleName
			+ '\',\''
			+ row.roleDescription
			+ '\','
			+ row.roleLevel
			+ ',\''
			+ row.roleResourceIds
			+ '\''
			+ ')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
			+ '<a href="#" style="color:red" onclick="deleteOneRole('
			+ row.roleId + ',\'  ' + row.roleDescription + ' \')">删除</a>';
}
//打开添加的窗口
//todo
function openAddRoleDialog() {
	$('#add_dialog_roleName').textbox('setValue', '');
	$('#add_dialog_roleDescription').textbox('setValue', '');
	$('#add_dialog_roleResourceIds').textbox('setValue', '');


	$('#roleResourceIdsTree').tree({
		url:'roleResourceIdsTreeData.json'
	});
	$('#add_dlg').dialog('open');
}
//打开编辑的窗口
function openEditRoleDialog(roleId, roleName, roleDescription, roleLevel, roleResourceIds) {
	//$('#edit_dialog_roleName').textbox('textbox').attr('disabled', true); //设置输入框为禁用
	$('#edit_dialog_roleLevel').textbox('textbox').attr('disabled', true); //设置输入框为禁用
	$('#edit_dialog_roleId').textbox('textbox').attr('disabled', true); //设置输入框为禁用

	$('#edit_dialog_roleId').textbox('setValue', roleId);
	$('#edit_dialog_roleName').textbox('setValue', roleName);
	$('#edit_dialog_roleDescription').textbox('setValue', roleDescription);
	$('#edit_dialog_roleLevel').textbox('setValue', roleLevel);
	$('#edit_dialog_roleResourceIds').textbox('setValue', roleResourceIds);

	$('#edit_roleResourceIdsTree').tree({
		url:'roleResourceIdsTreeData.json'
	});
	$('#dlg').dialog('open');
}

//删除单个角色  
function deleteOneRole(roleId, roleDescription) {
	$.messager.confirm('删除', '您确认想要删除' + roleDescription + '角色吗？', function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : "deleteOneRole.json",
				data : {
					"roleId" : roleId
				},
				dataType : "json",
				traditional : true,//防止深度序列化
				success : function(data) {
					//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
					var msg = data;
					if (msg.code == 200) {
						$.messager.alert('提示', msg.msg);
						//刷新数据
						$('#dg').datagrid('reload');
					} else {
						$.messager.alert('提示', msg.msg);
					}
				},
				error : function(err) {
					$.messager.alert('提示', "删除" + userName + "失败！");
				}
			});
		}
	});
}
//批量删除角色 
function deleteListRole() {

	var ids = [];
	var rows = $('#dg').datagrid('getSelections');
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].roleId);
	}
	var idss = ids.join(',');
	$.messager.confirm('删除', '您确认想要删除"' + idss + '"这' + rows.length + '条用户吗？',
			function(r) {
				if (r) {
					$.ajax({
						type : "POST",
						url : "deleteListRole.json",
						data : {
							"ids" : ids
						},
						dataType : "json",
						traditional : true,//防止深度序列化
						success : function(data) {
							//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
							var msg = data;
							if (msg.code == 200) {
								$.messager.alert('提示', msg.msg);
								//刷新数据
								$('#dg').datagrid('reload');
							} else {
								$.messager.alert('提示', msg.msg);
							}
						},
						error : function(err) {
							$.messager.alert('提示', "删除失败！");
						}
					});
				}
			});
}

//新增用户
function addRole() {
		$('#add_dlg_f').form('submit', {
			url : "insertRole.json",
			success : function(data) {
				//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
				var msg = $.parseJSON(data);
				if (msg.code == 200) {
					$.messager.alert('提示', msg.msg);
					//关闭窗口 刷新数据
					$('#add_dlg').dialog('close');
					$('#dg').datagrid('reload');
				} else {
					$.messager.alert('提示', msg.msg);
				}
			},
			onLoadError : function() {
				$.messager.alert('提示', "添加失败！");
			}
		});

}
//编辑用户
//todo
function editRole() {

	$('#edit_dlg_f').form('submit', {
		url : "updateRole.json",
		success : function(data) {
			//ajax成功后返回的数据是json对象，但是easyui的form表单返回时是json字符串，需要转化成json对象
			var msg = $.parseJSON(data);
			if (msg.code == 200) {
				$.messager.alert('提示', msg.msg);
				//关闭窗口 刷新数据
				$('#dlg').dialog('close');
				$('#dg').datagrid('reload');
			} else {
				$.messager.alert('提示', msg.msg);
			}
		},
		onLoadError : function() {
			$.messager.alert('提示', "编辑失败！");
		}
	});
}

//edit 添加权限进text
function editResourcesToText(){
	$('#edit_dialog_roleResourceIds').textbox('setValue', getChecked('edit_roleResourceIdsTree'));
}

//add 添加权限进text
function addResourcesToText(){
	$('#add_dialog_roleResourceIds').textbox('setValue', getChecked('roleResourceIdsTree'));
}

//获取选中的权限id并进行缩进
function getChecked(test){
	var nodes = $('#'+test+'').tree('getChecked');//roleResourceIdsTree

	var resultlist = new Array();
	var resultlistNum = 0;

	var menulist = new Array();
	var menulistNum = 0;

	var pagelist = new Array();
	var pagelistNum = 0;

	var buttonlist = new Array();
	var buttonlistNum = 0;

	//添加所有的两位（菜单可视）
	for(var i=0; i<nodes.length; i++){
		if(nodes[i].id > 9){
			if(nodes[i].id <= 99 ){
				menulist[menulistNum] = nodes[i].id;
				menulistNum++;
			}else{
				var menuHahNum = 0;
				var pageHahNum = 0;
				var menutemp =0;
				var pagetemp = 0;

				if(nodes[i].id < 1000 && nodes[i].id > 99){
					menutemp = Math.floor(nodes[i].id/10);
					pagelist[pagelistNum]=nodes[i].id;
					pagelistNum++;
				}else{
					menutemp = Math.floor(nodes[i].id/100);
					pagetemp = Math.floor(nodes[i].id/10);
					/*因为按照顺序当一个页面全选的时候，不仅会选中所有的按钮也会选中page，并且page的顺序是在所有的button前，
					  所以当时button的时候，只需要判断其前三位是否在page中已经出现，若已经出现，那么么说明已经全选，是没有再
					  加button的必要。如果不在page中，那说明不是全选，就需要把button加入*/
					for(var j = 0; j < pagelistNum; j++){
						if(pagetemp == pagelist[j]){
							pageHahNum++;
						}
					}
					if(pageHahNum == 0){
						buttonlist[buttonlistNum] = nodes[i].id;
						buttonlistNum++;
					}
				}
				//记录menutemp在menulist中的个数
				for (var j = 0; j < menulistNum; j++) {
					if(menutemp == menulist[j]){
						menuHahNum++;
					}
				}
				//如果个数是0，那么说明其中没有相同的，则进入menu
				if(menuHahNum == 0 && menutemp > 0){
					menulist[menulistNum] = menutemp;
					menulistNum++;
				}
			}
		}
	}
	for (var i = 0; i < menulistNum; i++) {
		resultlist[resultlistNum] = menulist[i];
		resultlistNum++;
	}
	for (var i = 0; i < pagelistNum; i++) {
		resultlist[resultlistNum] = pagelist[i];
		resultlistNum++;
	}
	for (var i = 0; i < buttonlistNum; i++) {
		resultlist[resultlistNum] = buttonlist[i];
		resultlistNum++;
	}
	return resultlist;
}

