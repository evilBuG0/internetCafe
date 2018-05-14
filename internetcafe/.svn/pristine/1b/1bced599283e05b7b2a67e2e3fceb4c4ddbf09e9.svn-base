/**
* @author wanghongbo@163.com
*/

function checkAll(obj, divId) {
    divId = divId || "mainId";
    if ($(obj).prop("checked")) {
        $(":checkbox", "#" + divId).prop("checked", true);
    } else {
        $(":checkbox", "#" + divId).prop("checked", false);
    }
}

/*$(document).ready(function(){
    $('.date-picker', "#mainId").datepicker({autoclose:true}).next().on(ace.click_event, function(){
        $(this).prev().focus();
    });
});*/

function showCity(obj){
    var address = $(obj).val();
    if(address!=null){
        var areas = address.split(",");
        for(var i = 0 ; i < areas.length ; i++){
            $("input[name='area']").each(function(){
                if(areas[i]==$(this).val()){
                    $(this).attr("checked",true);
                }
            });
        }
    }
    $("#address").show();
}

function addressClose(){
    $("#address").hide();
    $("input[name='area']").attr("checked",false);
}

function addressOk(){
    var areas = "";
    $("input[name='area']:checked").each(function(){
        areas+=$(this).val()+",";
    });
    if(areas!=null){
        $("#planAddress").val(areas.substring(0,areas.length-1));
    }
    $("#address").hide();
}

function changePlanType(obj){
    if($(obj).find("option:selected").val()==1){
        $("#filePath").attr("disabled",true);
        $("#planAddress").attr("disabled",false);
    }else{
        $("#filePath").attr("disabled",false);
        $("#planAddress").attr("disabled",true);
    }
}

function selectRoundMode(obj,divId){
    divId = divId || "mainId";
    if($(obj).attr("name")=="round_mode"){
        $("input[name='round_mode']","#"+divId).attr("disabled",true);
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
    }
}

function initEnv() {
    var ajaxbg = $("#background,#progressBar");
    ajaxbg.hide();
    /*var _timeout=null;*/
    $(document).ajaxStart(function(){
        ajaxbg.show();
        /*_timeout=setTimeout(function(){ajaxbg.show()}, 500*1);*/
    }).ajaxStop(function(){
        /*if(_timeout) {
            clearTimeout(_timeout);
            _timeout=null;
        }*/
        ajaxbg.hide();
    });
}

$(document).ready(function(){
    initEnv();
});

function report(sid,param){
    var $form = $("#"+sid);
    var query=$form.serialize();
    if(param){
        for(var item in param){
            query += "&" + item + "=" + param[item];
        }
    }
    else{
        query += "&export=1";
    }
    var $iframe = $("#callbackframe");
    if ($iframe.size() == 0) {
        $iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
    }
    var action = $form.attr("action");
    $iframe.attr("src",action+(action.indexOf('?') == -1 ? "?" : "&") + query);
}
