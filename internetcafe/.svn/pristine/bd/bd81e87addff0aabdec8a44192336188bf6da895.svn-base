
$(function(){

    $("input[name='plan']").click(function(){
        if($(this).attr("checked")){
            var id = $(this).val();
            $("input[name='plan']").each(function(){
                if($(this).attr("checked")){
                    if($(this).val()!=id){
                        $(this).attr("checked",false);
                    }
                }

            });
        }
    });
});


function findPlans(obj){
    $("#condition").submit();
}

function alertAddPlan(){
    $("#add_plan h4").html("方案新增");
    $("#add_plan").show();
}

function changePlanType(obj){
    if($(obj).find("option:selected").val()==1){
        $("#updateType").attr("disabled",false);
        $("#planAddress").attr("disabled",false);
        $("#inputType").attr("disabled",true);
        $("input[type='file']").attr("disabled",true);
    }else{
        $("#updateType").attr("disabled",true);
        $("#planAddress").attr("disabled",true);
        $("#inputType").attr("disabled",false);
        if($("#inputType option:selected").val()==1){
            $("#filePath").attr("disabled",false);
        }else{
            $("#enfilePath").attr("disabled",false);
        }
    }
}

function changeInputType(obj){
    if($(obj).find("option:selected").val()==1){
        $("#filePath").attr("disabled",false);
        $("#enfilePath").attr("disabled",true);
    }else{
        $("#filePath").attr("disabled",true);
        $("#enfilePath").attr("disabled",false);
    }
}

function planClose(){
    $("#planName").attr("readOnly",false);
    $("#add input").val("");
    $("input[name='area']").attr("checked",false);
    $("#updateType").attr("disabled",false);
    $("#planAddress").attr("disabled",false);
    $("#planType option:eq(0)").attr("selected",true);
    $("#updateType option:eq(0)").attr("selected",true);
    $("input[type='file']").attr("disabled",true);
    $("#add_plan").hide();
}

function commitPlan(obj){
    if($("#operateType").val()=="update"){
        if(confirm("确定修改该方案吗？")){
            if(checkForm()){
                $("#add").submit();
            }
        }
    }else{
        if($("#planName").val()==""){
            alert("请输入方案名称");
        }else{
            $.post($(obj).attr("url"),"planName="+$("#planName").val(),function(data){
                if(data['data'].type=="success"){
                    if(checkForm()){
                        $("#add").submit();
                    }
                }else{
                    alert(data['data'].message);
                }
            },"json");
        }
    }
}

function checkForm(){
    var planType = $("#planType option:selected").val();
    if(planType==1){
        return true;
    }else{
        var inputType = $("#inputType option:selected").val();
        if(inputType==1){
            if($("#filePath").val()!=""){
                if($("#filePath").val().indexOf(".csv")>0){
                    return true;
                }else{
                    alert("请添加格式为.csv的文件");
                    return false;
                }
            }else{
                alert("请选择要导入的文件！");
                return false;
            }
        }else{
            if($("#enfilePath").val()!=""){
                if($("#enfilePath").val().indexOf(".csv")>0){
                    return true;
                }else{
                    alert("请添加格式为.csv的文件");
                    return false;
                }
            }else{
                alert("请选择要导入的文件！");
                return false;
            }
        }
    }
}

function showCity(obj){
    var address = $("#planAddress").val();
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

function startPlan(obj){
    var url = $(obj).attr("url");
    if($("input[name='plan']:checked").length==0){
        alert("请选择一个方案");
    }else{
        var tdObjs = $("input[name='plan']:checked").parent("label").parent("td").parent("tr").find("td");
        if($.trim(tdObjs.eq(4).text())=="手动更新"&& $.trim(tdObjs.eq(6).text())!="执行中"){
            if(confirm("确定手动启动该方案？")){
                var param = "planId="+$("input[name='plan']:checked").val();
                $.post(url,param,function(data){
                    if(data['data'].type=="success"){
                        alert(data['data'].message);
                        window.location.reload();
                    }else{
                        alert(data['data'].message);
                    }
                },"json");
            }
        }else{
            alert("请选择更新方式为手动更新，且不在执行中的方案");
        }
    }
}

function delPlan(obj){
    if($("input[name='plan']:checked").length==0){
        alert("请选择一个方案");
    }else{
        var url = $(obj).attr("url");
        var tdObjs = $("input[name='plan']:checked").parent("label").parent("td").parent("tr").find("td");
        if($.trim(tdObjs.eq(6).text())!="执行中"){
            if(confirm("确定删除该方案？")){
                var param = "planId="+$("input[name='plan']:checked").val();
                $.post(url,param,function(data){
                    if(data['data'].type=="success"){
                        alert(data['data'].message);
                        window.location.reload();
                    }else{
                        alert(data['data'].message);
                    }
                },"json");
            }
        }else{
            alert("不能删除执行中的方案！");
        }
    }
}

function checkPlan(obj){
    if($("input[name='plan']:checked").length==0){
        alert("请选择一个方案");
    }else{
        var url = $(obj).attr("url");
        var tdObjs = $("input[name='plan']:checked").parent("label").parent("td").parent("tr").find("td");
        if($.trim(tdObjs.eq(6).text())!="执行中"){
            var param = "planId="+$("input[name='plan']:checked").val();
            $.post(url,param,function(data){
                if(data['type']=="data"){
                    $("#add_plan h4").html("方案修改");
                    $("#id").val(data['data']['id']);
                    $("#planName").val(data['data']['planName']);
                    $("#planName").attr("readOnly",true);
                    var planType = data['data']['planType'];
                    var updateType = data['data']['updateType'];
                    var inputType = data['data']['inputType'];
                    $("#planType option").each(function(){
                        if($(this).val()==planType){
                            $(this).attr("selected",true);
                        }
                    });
                    if(planType=="1"){
                        $("#updateType").attr("disabled",false);
                        $("#planAddress").attr("disabled",false);
                        $("#inputType").attr("disabled",true);
                        $("input[name='file']").attr("disabled",true);
                        $("#updateType option").each(function(){
                            if($(this).val()==updateType){
                                $(this).attr("selected",true);
                            }
                        });
                        $("#planAddress").val(data['data']['planAddress']);
                    }else{
                        $("#updateType").attr("disabled",true);
                        $("#planAddress").attr("disabled",true);
                        $("#inputType").attr("disabled",false);
                        $("#inputType option").each(function(){
                            if(inputType==$(this).val()){
                                $(this).attr("selected",true);
                            }
                        });
                        if($("#inputType option:selected").val()==1){
                            $("#filePath").attr("disabled",false);
                        }else{
                            $("#enfilePath").attr("disabled",false);
                        }
                    }
                    $("#operateType").val("update");
                    $("#add_plan").show();
                }else{
                    alert(alert(data['data'].message));
                }
            },"json");
        }else{
            alert("不能修改执行中的方案！");
        }
    }
}

function page(page){
    window.location.href = $("#condition").attr("action")+"?page="+page+"&"+$("#condition").serialize();
}