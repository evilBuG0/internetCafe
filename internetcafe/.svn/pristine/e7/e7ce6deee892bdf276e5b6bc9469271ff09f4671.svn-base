$(function(){
    $("#push_mode").click(function(){
        if($(this).attr("checked")){
            $("#pushDomainIds").attr("disabled",false);
        }else{
            $("#pushDomainIds").attr("disabled",true);
        }
    });

    $("input[name='channel']").click(function(){
        if($(this).attr("checked")){
            var id = $(this).val();
            $("input[name='channel']").each(function(){
                if($(this).attr("checked")){
                    if($(this).val()!=id){
                        $(this).attr("checked",false);
                    }
                }

            });
        }
    });
});

function showAddDiv(){
    $("#alert_win h4").text("频道新增");
    $("#operateType").val("");
    $("#alert_win").show();
}

function showUpdateDiv(obj){

    if($("input[name='channel']:checked").length==0){
        alert("请选择一个频道");
    }else{
        var url = $(obj).attr("url");
        $.post(url,"channelId="+$("input[name='channel']:checked").val(),function(data){
            if(data!=null){
                $("#operateType").val("update");
                $("#alert_win h4").text("频道修改");
                $("#id").val(data['id']);
                $("#channelName").val(data['channelName']);
                $("#planIds").val(data['planIds']);
                $("#operator option").each(function(){
                    if($(this).val()==data['operator']['id']){
                        $(this).attr("selected",true);
                    }
                });
                $("#workParentId option").each(function(){
                    if($(this).val()==data['workParentId']){
                        $(this).attr("selected",true);
                    }
                });
                $("#workId option").each(function(){
                    if($(this).val()==data['workId']){
                        $(this).attr("selected",true);
                    }
                });
                $("#businessName").val(data['businessName']);
                $("#functionId option").each(function(){
                    if($(this).val()==data['functionId']){
                        $(this).attr("selected",true);
                    }
                });
                $("#contentId option").each(function(){
                    if($(this).val()==data['contentId']){
                        $(this).attr("selected",true);
                    }
                });
                $("#thirdParty option").each(function(){
                    if($(this).val()==data['thirdParty']){
                        $(this).attr("selected",true);
                    }
                });
                $("#userForced option").each(function(){
                    if($(this).val()==data['userForced']){
                        $(this).attr("selected",true);
                    }
                });
                $("#domainForced option").each(function(){
                    if($(this).val()==data['domainForced']){
                        $(this).attr("selected",true);
                    }
                });
                $("#tamperProof option").each(function(){
                    if($(this).val()==data['tamperProof']){
                        $(this).attr("selected",true);
                    }
                });
                $("#startDate").val(data['startDate']);
                $("#endDate").val(data['endDate']);
                $("#startTime").val(data['startTime']);
                $("#endTime").val(data['endTime']);
                $("#roleIds").val(data['roleIds']);
                $("#timeInterval").val(data['timeInterval']);
                $("input[name='round_mode']").each(function(i){
                    if(data['roundMode'].charAt(i)=="1"){
                        $(this).attr("checked",true);
                    }
                });
                if(data['pushMode']=="2"){
                    $("#pushDomainIds").val(data['pushDomainIds']);
                }else{
                    $("#push_mode").attr("checked",false);
                    $("#pushDomainIds").attr("disabled",true);
                }
                $("#delayMode option").each(function(){
                    if($(this).val()==data['delayMode']){
                        $(this).attr("selected",true);
                    }
                });
                $("#delayTime").val(data['delayTime']);
                $("#cpmPrice").val(data['cpmPrice']);
                $("#cpcPrice").val(data['cpcPrice']);
                $("#cpsPrice").val(data['cpsPrice']);
                $("#cpaPrice").val(data['cpaPrice']);
                $("#price").val(data['price']);
                $("#throwUrl").val(data['throwUrl']);
                $("#describe").val(data['describe']);
                $("#alert_win").show();
            }else{
                alert("未找到该频道");
            }
        });
    }
}

function channelInfoClose(){
    $("#channel_info input[name='round_mode']").attr("disabled",false);
    $("#channel_info input[name='round_mode']").attr("checked",false);
    $("#push_mode").attr("disabled",false);
    $("#push_mode").attr("checked",true);
    $("#pushDomainIds").attr("disabled",false);
    $("#channel_info input[type='text']").val("");
    $("#alert_win").hide();
}

function channelInfoOk(){
    if(checkForm()){
        var s = "";
        if($("#operateType").val()=="update"){
            s = "确定修改该频道信息？";
        }else{
            s = "确定添加该频道？";
        }
        if(confirm(s)){
            var param = $("#channel_info").serialize();
            $.post($("#channel_info").attr("action"),param,function(data){
                if(data['data'].type=="success"){
                    alert(data['data'].message);
                    window.location.reload();
                }else{
                    $("#channel_info input[name='round_mode']").attr("disabled",false);
                    $("#push_mode").attr("disabled",false);
                    alert(data['data'].message);
                }
            },"json");
        }else{
            $("#channel_info input[name='round_mode']").attr("disabled",false);
            $("#push_mode").attr("disabled",false);
        }
    }
}

function checkForm(){
    var regInt = /^[0-9]+$/;
    var regPrice = /^[0-9]+(.[0-9]{1,2})?$/;
    if($.trim($("#channelName").val())==""){
        alert("请填写频道名称！");
        return false;
    }
    if($("#planIds").val()==""){
        alert("请选择方案！");
        return false;
    }
    if($("#startDate").val()==""){
        alert("请选择推送开始日期！");
        return false;
    }
    if($("#endDate").val()==""){
        alert("请选择推送结束日期！");
        return false;
    }
    if($("#startTime").val()==""){
        alert("请选择推送开始时间！");
        return false;
    }
    if($("#endTime").val()==""){
        alert("请选择推送结束时间！");
        return false;
    }
    if($("#roleIds").val()==""){
        alert("请选择审核角色！");
        return false;
    }
    if($.trim($("#timeInterval").val())==""){
        $("#timeInterval").val("60");
    }else{
        if(!regInt.test($.trim($("#timeInterval").val()))){
            alert("请在时间间隔一栏输入整数");
            return false;
        }
    }

    if($("#delayTime").val()==""){
        if($("#delayMode option:selected").val()=="1"){
            $("#delayTime").val("3");
        }else{
            $("#delayTime").val("3");
        }
    }
    if($.trim($("#cpmPrice").val())!=""){
        if(!regPrice.test($.trim($("#cpmPrice").val()))){
            alert("请在CPM价格一栏输入数字，最多保留小数点后两位");
            return false;
        }
    }
    if($.trim($("#cpcPrice").val())!=""){
        if(!regPrice.test($.trim($("#cpcPrice").val()))){
            alert("请在CPC价格一栏输入数字，最多保留小数点后两位");
            return false;
        }
    }
    if($.trim($("#cpaPrice").val())!=""){
        if(!regPrice.test($.trim($("#cpaPrice").val()))){
            alert("请在CPA价格一栏输入数字，最多保留小数点后两位");
            return false;
        }
    }
    if($.trim($("#cpsPrice").val())!=""){
        if(!regPrice.test($.trim($("#cpsPrice").val()))){
            alert("请在CPS价格一栏输入数字，最多保留小数点后两位");
            return false;
        }
    }
    if($.trim($("#price").val())==""){
        alert("请输入价格");
        return false;
    }else{
        if(!regPrice.test($.trim($("#price").val()))){
            alert("请在价格一栏输入数字，最多保留小数点后两位");
            return false;
        }
    }
    if($.trim($("#throwUrl").val())==""){
        alert("请填写推送链接！");
        return false;
    }

    $("#channel_info input[name='round_mode']").attr("disabled",true);
    var flag = true;
    var roundMode = "";
    $("#channel_info input[name='round_mode']").each(function(){
        if($(this).attr("checked")){
            flag = false;
            roundMode += "1";
        }else{
            roundMode += "0";
        }
    });
    if(flag){
        roundMode = "1111111";
    }
    $("#roundMode").val(roundMode);
    if($("#push_mode").attr("checked")){
        if($("#pushDomainIds").val()==""){
            alert("请选择要推送的域名！");
            return false;
        }else{
            $("#push_mode").attr("disabled",false);
            $("#pushMode").val($("#push_mode").val());
        }
    }else{
        $("#push_mode").attr("disabled",false);
        $("#pushMode").val("1");
    }
    return true;
}

function delChannel(obj){
    if($("input[name='channel']:checked").length==0){
        alert("请选择一个频道");
    }else{
        if(confirm("确定删除该频道吗？")){
            var url = $(obj).attr("url");
            $.post(url,"channelId="+$("input[name='channel']:checked").val(),function(data){
                if(data['data'].type=="success"){
                    alert(data['data'].message);
                    window.location.reload();
                }else{
                    alert(data['data'].message);
                }
            });
        }
    }
}

function showSolidifyDiv(obj){
    var url = $(obj).attr("url");
    if($("input[name='channel']:checked").length==0){
        alert("请选择一个频道");
    }else{
        $("#channelId").val($("input[name='channel']:checked").val());
        $.post(url,"channelId="+$("input[name='channel']:checked").val(),function(data){
            for(var key in data){
                if(key=="id"){
                    $("#solidId").val(data[key]);
                }else if(key=="channel"){
                    $("#channelId").val(data[key]['id']);
                }else{
                    $("#channel_solidify input[name='"+key+"']").each(function(){
                        if($(this).val()==data[key]){
                            $(this).attr("checked",true);
                        }
                    });
                }
            }
            $("#solidify").show();
        },"json");
    }
}

function solidifyClose(){
    $("#solidify input[type='radio']").each(function(i){
        if(i%2==0){
            $(this).attr("checked",true);
        }
    });
    $("#solidId").val("");
    $("#solidify").hide();
}

function solidifyOk(){
    if(confirm("确定更新该频道的固化信息吗？")){
        $.post($("#channel_solidify").attr("action"),$("#channel_solidify").serialize(),function(data){
            if(data['data'].type=="success"){
                alert(data['data'].message);
                solidifyClose();
            }else{
                alert(data['data'].message);
            }
        },"json");
    }
}

function page(page){
    window.location.href = $("#condition").attr("action")+"?page="+page+"&"+$("#condition").serialize();
}