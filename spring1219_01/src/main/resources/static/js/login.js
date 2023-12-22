/**
 * 
 */

$(function(){
	$(".btn_login").click(function(){
		if($("#id").val().length<1){
			alert("아이디를 입력하세요.")
			$("#id").focus();
			return false;
		}
		loginFrm.submit();
	});
	
	//화살표함수
	/* $(".btn_login").click(()=>{
		
	}); */
});//jquery