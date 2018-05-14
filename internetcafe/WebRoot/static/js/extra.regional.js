/**
 * Created by wanghongbo on 2015/1/19.
 */

$.extend($.fn, {
    valid: function (options) {
        var message = null;
        $("input", $(options)).each(function (i) {
            $(this).css("background-color", "#ffffff");
            var check_message = $(this).check($(this));
            if (check_message) {
                $(this).css("background-color", "#FFF8DC");
                if (!message) {
                    $(this).focus();
                    message = check_message;
                }
            }
        });
        $("select", $(options)).each(function (i) {
            $(this).css("background-color", "#ffffff");
            var check_message = $(this).check($(this));
            if (check_message) {
                $(this).css("background-color", "#FFF8DC");
                if (!message) {
                    $(this).focus();
                    message = check_message;
                }
            }
        });
        $("textarea", $(options)).each(function (i) {
            $(this).css("background-color", "#ffffff");
            var check_message = $(this).check($(this));
            if (check_message) {
                $(this).css("background-color", "#FFF8DC");
                if (!message) {
                    $(this).focus();
                    message = check_message;
                }
            }
        });
        if (!message) {
            return true;
        }
        else {
            alert(message);
            return false;
        }
    },
    check: function (obj) {
        var value = $(obj).val();
        if ($(obj).hasClass("required")) {
            if (value == "") {
                return "红色*号必填项不可以为空！";
            }
        }

        if($(obj).attr("maxlength")) {
            var maxlength=$(obj).attr("maxlength");
            if (/^\d+$/.test(maxlength)) {
                maxlength = parseInt(maxlength);
                if(!(value.length <= maxlength)){
                    return "长度最多是" +maxlength+ "位的字符串！";
                }
            }
        }

        if($(obj).attr("minlength")) {
            var minlength=$(obj).attr("minlength");
            if (/^\d+$/.test(minlength)) {
                minlength = parseInt(minlength);
                if(!(value.length >= minlength)){
                    return "长度最少是" + minlength + "位的字符串！";
                }
            }
        }

        if($(obj).attr("confirmPassword")) {
            var passWord = $(obj).attr("confirmPassword");
            if (value != document.getElementById(passWord).value) {
                return "两次输入的密码不一致！请重新输入！";
            }
        }

        if (value == "") {
            return;
        }

        if ($(obj).hasClass("digits")) {
            if (!/^\d+$/.test(value)) {
                return "只能输入整数！";
            }
        }

        if ($(obj).hasClass("password")) {
            if (!/^(?![a-zA-z]+$)(?!\d+$)(?![!@#$%^&*]+$)(?![a-zA-z\d]+$)(?![a-zA-z!@#$%^&*]+$)(?![\d!@#$%^&*]+$)[a-zA-Z\d!@#$%^&*]+$/.test(value)) {
                return "密码需包含数字、字母和特殊字符！不能有中文！";
            }
        }

        if ($(obj).hasClass("mail")) {
            if (!/^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/.test(value)) {
                return "请填写正确的邮箱格式！";
            }
        }

        if ($(obj).hasClass("mobile")) {
            if (!/^[1][0-9][0-9]{9}$/.test(value)) {
                return "请填写正确的手机号码格式！";
            }
        }

        if($(obj).hasClass("price")){
            if (!/^[0-9]+(.[0-9]{1,2})?$/.test(value)) {
                return "请输入合法的数字(保留两位小数)！";
            }
        }

        if ($(obj).hasClass("number")) {
            if (!/^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value)) {
                return "请输入合法的数字！";
            }
        }

        if ($(obj).attr("max")) {
            var max=$(obj).attr("max");
            if (/^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value) && /^\d+$/.test(max)) {
                max = parseInt(max);
                if(!(parseFloat(value) <= max)){
                    return "不能大于" + max +"！";
                }
            }
        }

        if ($(obj).attr("min")) {
            var min=$(obj).attr("min");
            if (/^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value) && /^\d+$/.test(min)) {
                min = parseInt(min);
                if(!(parseFloat(value) >=min)){
                    return "不能小于" + min +"！";
                }
            }
        }
    }
});