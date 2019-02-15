$(function() {
   
    tabClose();
    tabCloseEven();
})
   
   $(function(){    
       $('.accordion .panel-body li a').bind('click', function(){    
   
   
           var tabTitle = $(this).text();
           var url = $(this).attr("data");
           var icon = $(this)[0].dataset.options.split(",")[1].split(":")[1].split("\'")[1];
           addTab(tabTitle, url, icon);
       });    
   });  
   
   
   // 添加当前一项
   function addTab(tabTitle, url, icon) {
       //如果不在当前页面中
       if (!$('#tabs').tabs('exists', tabTitle)) {
           $('#tabs').tabs('add', {
               title : tabTitle,
               content : createFrame(url),
               closable : true,//关闭按钮
               icon : icon
           });
       } else {
           
           $('#tabs').tabs('select', tabTitle);
           $('#mm-tabupdate').click();//刷新
       }
       tabClose();
   }
   
/*   function addmainTab() {
           $('#tabs').tabs('add', {
               title : "主页",
               content : createFrame("tab1.html"),
               closable : false,//关闭按钮
               index: 0
           });
   }*/
   
   // 添加项中的创建一个项函数
   function createFrame(url) {
       var s = '<iframe scrolling="auto"  src="' + url + '"  style="width:100%;height:100%;border:none;"></iframe>';		
       return s;
   }
   
   /* 双击关闭TAB选项卡，为选项卡绑定右键 */
   function tabClose() {
       /* 双击关闭TAB选项卡 */
       $(".tabs-inner").dblclick(function() {
           var subtitle = $(this).children(".tabs-closable").text();
           $('#tabs').tabs('close', subtitle);
       })
       /* 为选项卡绑定右键 */
       $(".tabs-inner").bind('contextmenu', function(e) {
           $('#mm').menu('show', {
               left : e.pageX,
               top : e.pageY
           });
   
           var subtitle = $(this).children(".tabs-closable").text();
   
           $('#mm').data("currtab", subtitle);
           $('#tabs').tabs('select', subtitle);
           return false;
       });
   }
   
   // 绑定右键菜单事件
   function tabCloseEven() {
       // 刷新
       $('#mm-tabupdate').click(function() {
           var currTab = $('#tabs').tabs('getSelected');
           var url = $(currTab.panel('options').content).attr('src');
           if(url == "undefined" || url ==null){
        	   url="welcome.page";
        	   }
           $('#tabs').tabs('update', {
               tab : currTab,
               options : {
                   content : createFrame(url)
               }
           })
       })
       // 关闭当前
       $('#mm-tabclose').click(function() {
           var currtab_title = $('#mm').data("currtab");
           $('#tabs').tabs('close', currtab_title);
       })
   
       // 全部关闭
       $('#mm-tabcloseall').click(function() {
           $('.tabs-inner span').each(function(index, element) {
               var t = $(element).text();
               if(t=="主页"){
                       
               }else{
                   $('#tabs').tabs('close', t);
               }
           });
       });
   
       // 关闭除当前之外的TAB
       $('#mm-tabcloseother').click(function() {
           $('#mm-tabcloseright').click();
           $('#mm-tabcloseleft').click();
       });
       // 关闭当前右侧的TAB
       $('#mm-tabcloseright').click(function() {
           var nextall = $('.tabs-selected').nextAll();
           if (nextall.length == 0) {
        	   $.messager.alert('提示','右侧没有关闭项');
               return false;
           }
           nextall.each(function(i, n) {
               var t = $('a:eq(0) span', $(n)).text();
               $('#tabs').tabs('close', t);
           });
           return false;
       });
       // 关闭当前左侧的TAB
       $('#mm-tabcloseleft').click(function() {
           var prevall = $('.tabs-selected').prevAll();
           
           if (prevall.length < 2) {
        	   $.messager.alert('提示','左侧没有可关闭项');
               return false;
           }
   
           prevall.each(function(index, element) {
               if(prevall.length-1 == index){
                   $('#tabs').tabs('select', 1);
               }else{
                   var t = $('a:eq(0) span', $(element)).text();
                   $('#tabs').tabs('close', t);
               }	 
           });
   
           return false;
       });
       // 退出
       $("#mm-exit").click(function() {
           $('#mm').menu('hide');
       })
   }