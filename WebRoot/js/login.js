$(function () {

    /**
     * 登录验证
     */
    $("#userName_id").blur(function () {

        $("#login_form_error").text("");
        var pnum = $("#userName_id").val();
        if(pnum=="") {
            $("#login_form_error").text("手机号不能为空");
        }else{
            if (!pnum.match(/^(((13[0-9]{1})|159|153)+\d{8})$/)){
                $("#login_form_error").text("手机号格式不正确");
            }
        }
    });

    $("#password_id").blur(function () {
        $("#login_form_error").text("");
        var pwd = $("#password_id").val();
        if(pwd==""){
            $("#login_form_error").text("密码不能为空");
        }
    });

});