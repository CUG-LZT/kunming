// strPrintName 打印任务名
// printDatagrid 要打印的datagrid
function CreateFormPage(strPrintName, printDatagrid) {
	var tableString = '<table cellspacing="0" class="pb">';
	var frozenColumns = printDatagrid.datagrid("options").frozenColumns; // 得到frozenColumns对象
	var columns = printDatagrid.datagrid("options").columns; // 得到columns对象
	var nameList = '';
	// 载入title
	if (typeof columns != 'undefined' && columns != '') {
		$(columns)
				.each(
						function(index) {
							tableString += '\n<tr>';
							if (typeof frozenColumns != 'undefined'
									&& typeof frozenColumns[index] != 'undefined') {
								for (var i = 0; i < frozenColumns[index].length; ++i) {
									if (!frozenColumns[index][i].hidden) {
										tableString += '\n<th width="'
												+ frozenColumns[index][i].width
												+ '"';
										if (typeof frozenColumns[index][i].rowspan != 'undefined'
												&& frozenColumns[index][i].rowspan > 1) {
											tableString += ' rowspan="'
													+ frozenColumns[index][i].rowspan
													+ '"';
										}
										if (typeof frozenColumns[index][i].colspan != 'undefined'
												&& frozenColumns[index][i].colspan > 1) {
											tableString += ' colspan="'
													+ frozenColumns[index][i].colspan
													+ '"';
										}
										if (typeof frozenColumns[index][i].field != 'undefined'
												&& frozenColumns[index][i].field != '') {
											nameList += ',{"f":"'
													+ frozenColumns[index][i].field
													+ '", "a":"'
													+ frozenColumns[index][i].align
													+ '"}';
										}
										tableString += '>'
												+ frozenColumns[0][i].title
												+ '</th>';
									}
								}
							}
							for (var i = 0; i < columns[index].length; ++i) {
								if (!columns[index][i].hidden) {
									tableString += '\n<th width="'
											+ columns[index][i].width + '"';
									if (typeof columns[index][i].rowspan != 'undefined'
											&& columns[index][i].rowspan > 1) {
										tableString += ' rowspan="'
												+ columns[index][i].rowspan
												+ '"';
									}
									if (typeof columns[index][i].colspan != 'undefined'
											&& columns[index][i].colspan > 1) {
										tableString += ' colspan="'
												+ columns[index][i].colspan
												+ '"';
									}
									if (typeof columns[index][i].field != 'undefined'
											&& columns[index][i].field != '') {
										nameList += ',{"f":"'
												+ columns[index][i].field
												+ '", "a":"'
												+ columns[index][i].align
												+ '"}';
									}
									tableString += '>'
											+ columns[index][i].title + '</th>';
								}
							}
							tableString += '\n</tr>';
						});
	}
	// 载入内容
	var rows = printDatagrid.datagrid("getRows"); // 这段代码是获取当前页的所有行
	var nl = eval('([' + nameList.substring(1) + '])');
	for (var i = 0; i < rows.length; ++i) {
		tableString += '\n<tr>';
		$(nl).each(function(j) {
			var e = nl[j].f.lastIndexOf('_0');

			tableString += '\n<td';
			//調整格式
			if (nl[j].a != 'undefined' && nl[j].a != '') {
				tableString += ' style="text-align:' + nl[j].a + ';"';
			}
			tableString += '>';
			
			
			if (e + 2 == nl[j].f.length) {
				tableString += rows[i][nl[j].f.substring(0, e)];
			}else{              //======================自己添加的判斷===========================
				if(nl[j].f == 'userCreateTime'){
					tableString += dateFormatter(rows[i][nl[j].f]);
				}else if(nl[j].f == 'userRole'){
					tableString += toRole(rows[i][nl[j].f]);
				}else if(nl[j].f == 'userAddress'){
					tableString += toAddress(rows[i][nl[j].f]);
				}else if(nl[j].f == 'userAddress'){
					tableString += 'opera';
				}else{
					tableString += rows[i][nl[j].f];
				}               //======================自己添加的判斷===========================
			}
			tableString += '</td>';
		});
		tableString += '\n</tr>';
	}
	
	
	sessionStorage.setItem("sent", tableString);
	tableString += '\n</table>';
	window.open(
			"http://127.0.0.1:8080/pro_kunming/print.page",
					strPrintName,
					"location:No;status:No;help:No;dialogWidth:800px;dialogHeight:600px;scroll:auto;");
}



function dateFormatter (value) {
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
    return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
}

function toRole (value) { 
	var role = "";
	if(value == 3){
		role="超级管理员";
	}else if(value == 2){
		role="管理员";
	}else{
		role="普通用户";
	}
	return role;
}
function toAddress (value) { 
	var city = "其他,北京市,天津市,河北省,山西省,内蒙古自治区,辽宁省,吉林省,黑龙江省,上海市,江苏省,浙江省,安徽省,福建省,江西省,山东省,"
		+ "河南省,湖北省,湖南省,广东省,海南省,广西壮族自治区,甘肃省,陕西省,新疆维吾尔自治区,青海省,宁夏回族自治区,重庆市,四川省,"
		+ "贵州省,云南省,西藏自治区,台湾省,澳门特别行政区,香港特别行政区,海外";
	var citys = city.split(',');
	return citys[value];
}