$(function () {


    /**
     * 表单验证
     */
    $("#pnumber").blur(function () {
        $("#error_text").text("");
       var pnum = $("#pnumber").val();
        if(pnum=="") {
            $("#error_text").text("手机号不能为空");
        }else{
            if (!pnum.match(/^(((13[0-9]{1})|159|153)+\d{8})$/)){
                $("#error_text").text("手机号格式不正确");
            }
        }
    });

    $("#pwd").blur(function () {
        $("#error_text").text("");
        var pwd = $("#pwd").val();
        if(pwd==""){
            $("#error_text").text("密码不能为空");
        }
    });

    $("#repwd").blur(function () {
        $("#error_text").text("");
        var repwd = $("#repwd").val();
        if(repwd==""){
            $("#error_text").text("重复密码不能为空");
        }else{
            var pwd = $("#pwd").val();
            if(pwd!=repwd){
                $("#error_text").text("两次密码不一致");
            }
        }
    })


    //提交
    $("#btn_register").click(function () {
        /*alert($("form").serialize());*/
        $.ajax({
            type:"post",
            url:"",
            data:$("form").serialize(),
            success: function (msg) {
                
            }
        });
    });
});