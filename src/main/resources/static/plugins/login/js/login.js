



// 管理员登录
function adminLogin(){
	
	var account = $('input[name="account"]');
	
	var accountValue = $.trim(account.val());
	
	if( accountValue.length == 0 ){
	
		$(account).parents(".form-group").find(".error-notic").html('请输入账号').fadeIn(100).delay(3000).fadeOut(100);

        $(account).focus();
        
        return;
        
	}
	
	var password = $('input[name="password"]');
	
	var passwordValue = $.trim(password.val());
	
	if( passwordValue.length == 0 ){
	
		$(password).parents(".form-group").find(".error-notic").html('请输入密码').fadeIn(100).delay(3000).fadeOut(100);;

        $(password).focus();
        
         return;
	}
	
	var data = { 'username' : accountValue, 'password' : passwordValue};
	
	var option = { type : 'POST', async : true, cache : false, url : '/login/authc', data : data, success : loginResult, beforeSend : loginBefore};
	
	$.ajax(option);

}

function loginBefore(){

	$('.login-loading').show();
	
}

function loginResult(data){

	if( data.code == 200 ){
	
		window.location.href = '/';
		
	}else if( data.code == 500 ){
	
		var account = $('input[name="account"]');
		
		$(account).parents(".form-group").find(".error-notic").html(data.msg).fadeIn(100).delay(3000).fadeOut(100);

        $(account).focus();
        
        $('.login-loading').hide();
        
        return;
        
	}else if( data.code == 500 ){
		
		var password = $('input[name="password"]');
		
		$(password).parents(".form-group").find(".error-notic").html(data.msg).fadeIn(100).delay(3000).fadeOut(100);

        $(password).focus();	
        
         $('.login-loading').hide();
        
         return;
        
	}
}

$('.login-btn').unbind('click').click(adminLogin);