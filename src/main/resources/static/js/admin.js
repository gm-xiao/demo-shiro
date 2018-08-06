layui.use([ 'layer', 'form', 'table', 'laydate' ],function() {
	var layer = layui.layer, form = layui.form, table = layui.table, laydate = layui.laydate, $= layui.jquery;
	
	$('.sign-out').unbind('click').click(adminLoginOut);
	
	function loginOutResult(data){
		if( data.code == 0 ){
			window.location.href = ctx + '/';
		}
	}
	
	function adminLoginOut(){
		layer.confirm('是否确认退出系统?', {icon: 3, title:'提示'}, function(index){
			layer.close(index);
			var url = ctx + '/loginOut';
			var option = { type : 'POST', async : true, cache : false, url : url, data : null, success : loginOutResult};
			$.ajax(option);
		});			
	}
	
	// 初始化菜单
	var initMenu = function(){
		
		var active = $('body').attr('nav');
		
		console.log(active);
		
		var menu = $('.sidebar-nav').find('li');
		
		menu.each(function(){
			
			if( $(this).attr('active') == active ){
				
				$(this).children('a').addClass('active');
				
				$(this).siblings().children('a').removeClass('active');
				
				if( null != $(this).children('ul') ){
					
					$(this).children('ul').show();
					
					var subMenu = $(this).children('ul').find('li');
					
					var subActive = $('body').attr('sub-active');
					
					console.log(subActive);
					
					subMenu.each(function(){
						
						if( $(this).attr('active') == subActive ){
							
							$(this).children('a').addClass('sub-active');
							
							$(this).siblings().children('a').removeClass('sub-active');
							
						}
						
					});
				}
			}
		});
	}();
	
});

