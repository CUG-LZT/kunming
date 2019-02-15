;(function($){
	$.insdep_example={
		route:function(v){

			if(v==undefined){
				var url = location.href;
				var index = url.split("#");
			}else{
				var index = v.split("#");
			}

			var page=$('.example_layout').layout('panel',"center");

			var href = "examples/";

			var path;


			if(index[0]!=url){

				path = index.pop();
				var structure = path.split("-");

				switch (structure.length) {
				case 1:
					href = href + path + ".html";
					break;
				case 2:
					switch (structure[0]) {
					case "element":
					case "combination":
					case "extend":
					case "plug":
					case "plugin":
						href = href + structure[0] + "/" + structure[1] + "/index.html";
						break;
					case "electron":
						href = href + structure[0] + "/" + structure[1] + "/index.html";
						break;
					case "theme":
						href = href + structure[0] + "/" + structure[1] + ".html";
						break;
					case "components":
						href = href + structure[1] + "/index.html";
						break;
					default:
						href = href + path + ".html"
					}
					break;
				case 3:
					switch (structure[0]) {
					case "theme":
					case "element":
					case "combination":
					case "extend":
					case "plug":
					case "plugin":
						href = href + structure[0] + "/" + structure[1] + "/" + structure[2] + ".html";
						break;
					case "electron":
						href = href + structure[0] + "/" + structure[1] + "/" + structure[2] + ".html";
						break;
					case "components":
						href = href + structure[1] + "/" + structure[2] + ".html";
						break;
					default:
						href = href + path + ".html";
					}
					//page=$('.page_content').layout('panel',"center");
					break;
				default:
					href = href + path + ".html";
				}
				//end switch
				
			}else{
				path="started";
				href = href + path + ".html";

			}
			//console.log(href);
			if(path){
				
				page.panel({
					href: href,
					loadingMessage: "",
					cache:false,
					onBeforeLoad: function() {
						NProgress.start();
					},
					onLoad: function() {
						NProgress.done();
					},
					onLoadError: function() {
						NProgress.done();
						console.log('The page "'+href+'" was not found');
						$.insdep.route("#404");
						
					}
				});
			}

			return true;
		}
		//end route
	}
	//end $.insdep
})(jQuery);
