/**
 * Created by wanghongbo on 2015/1/19.
 */

var win = {

    init : function(param){
        if(param){
            this.rel = param.rel || "mainId";
            $("#_winTitle").html(param.title || '操作');
        }
        return win;
    },
    show : function(url, pars) {
        var $panel = $("#_winId");
        var $content = $("#_winContentId");
        var reg = /\{(\w+)\}/g;
        var map = url.match(reg);
        for(key in map){
            var mat = map[key];
            var val = mat.replace("{","").replace("}","");
            var lst = $("input:checked[name="+val+"]", "#" + this.rel);
            if(lst.size()==1){
                url = url.replace(map[key],lst.val());
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        if(url) {
            url += ((url.indexOf("?")== -1)?"?":"&") + new Date().getTime();
        }
        $content.load(url, pars, function(){
            $panel.show();
        });
    },
    multi : function(url, pars) {
        var $panel = $("#_winId");
        var $content = $("#_winContentId");
        var reg = /(\w+)=\{(\w+)\}/g;
        var map = url.match(reg);
        for(key in map){
            var tKeyValue = map[key];
            url = url.replace(tKeyValue,"");
            var tValue = tKeyValue.match(/\{(\w+)\}/g);
            var iValue = tValue[0].replace("{","").replace("}","");
            var lst = $("input:checked[name="+iValue+"]","#" + this.rel);
            if(lst.size()>=1){
                $(lst).each(function(index){
                    if(/\w+$/g.test(url)){
                        url += "&";
                    }
                    url += tKeyValue.replace(tValue[0],$(this).val());
                });
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        if(url) {
            url += ((url.indexOf("?")== -1)?"?":"&") + new Date().getTime();
        }
        $content.load(url, pars, function(){
            $panel.show();
        });
    },
    href : function(url, pars) {
        var reg = /\{(\w+)\}/g;
        var map = url.match(reg);
        for(key in map){
            var mat = map[key];
            var val = mat.replace("{","").replace("}","");
            var lst = $("input:checked[name="+val+"]", "#" + this.rel);
            if(lst.size()==1){
                url = url.replace(map[key],lst.val());
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        if(url) {
            url += ((url.indexOf("?")== -1)?"?":"&") + new Date().getTime();
        }
        location.href = url;
    },
    export:function(url, pars) {
        var $panel = $("#_winId");
        var $content = $("#_winContentId")
        var reg = /\{(\w+)\}/g;
        var map = url.match(reg);
        for(key in map){
            var mat = map[key];
            var val = mat.replace("{","").replace("}","");
            var lst = $("input:checked[name="+val+"]", "#" + this.rel);
            if(lst.size()==1){
                url = url.replace(map[key],lst.val());
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        $("#callbackframe").attr('src',url);
    },
    confirm : function(url, pars, callback) {
        var reg = /(\w+)=\{(\w+)\}/g;
        var map = url.match(reg);
        for(key in map){
            var tKeyValue = map[key];
            url = url.replace(tKeyValue,"");
            var tValue = tKeyValue.match(/\{(\w+)\}/g);
            var iValue = tValue[0].replace("{","").replace("}","");
            var lst = $("input:checked[name="+iValue+"]","#" + this.rel);
            if(lst.size()>=1){
                $(lst).each(function(index){
                    if(/\w+$/g.test(url)){
                        url += "&";
                    }
                    url += tKeyValue.replace(tValue[0],$(this).val());
                });
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        if(confirm(pars||"确定要删除吗？")){
            $.ajax({
                type: 'GET',
                url:url,
                dataType:"json",
                cache: false,
                success: callback||function(json){
                    if(json["type"]=="alert"){
                        alert(json["data"]["message"])
                        return;
                    }
                    var $searchForm=$("form", "#mainId");
                    if($searchForm.size()==0){
                        window.location.reload(true);
                    }
                    if($searchForm.size()>=1){
                        $searchForm.submit();
                    }
                },
                error: function(){}
            });
        }
    },
    hangupordown : function(url, pars, callback,type) {
        var reg = /(\w+)=\{(\w+)\}/g;
        var map = url.match(reg);
        for(key in map){
            var tKeyValue = map[key];
            url = url.replace(tKeyValue,"");
            var tValue = tKeyValue.match(/\{(\w+)\}/g);
            var iValue = tValue[0].replace("{","").replace("}","");
            var lst = $("input:checked[name="+iValue+"]","#" + this.rel);
            if(lst.size()>=1){
                $(lst).each(function(index){
                    if(/\w+$/g.test(url)){
                        url += "&";
                    }
                    url += tKeyValue.replace(tValue[0],$(this).val());
                });
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        if(confirm(pars||type)){
            $.ajax({
                type: 'GET',
                url:url,
                dataType:"json",
                cache: false,
                success: callback||function(json){
                    if(json["type"]=="alert"){
                    	alert(json["data"]["message"])
                        return;
                    }
                    var $searchForm=$("form", "#mainId");
                    if($searchForm.size()==0){
                        window.location.reload(true);
                    }
                    if($searchForm.size()>=1){
                        $searchForm.submit();
                    }
                },
                error: function(){}
            });
        }
    },
    close : function(){
        $panel = $("#_winId");
        $panel.hide();
    }
}

function alertResult(data){
    if(data['data'].type=="success"){
        alert(data['data'].message);
        var $searchForm=$("form", "#mainId");
        if($searchForm.size()==0){
            window.location.reload(true)
        }
        if($searchForm.size()>=1){
            $searchForm.submit();
        }
    }else{
        alert(data['data'].message);
    }
}

var thirdWin = {

    init : function(param){
        if(param){
            this.rel = param.rel || "mainId";
            $("#_thirdTitle").html(param.title || '操作');
        }
        return thirdWin;
    },
    show : function(url, pars) {
        var $panel = $("#_thirdId");
        var $content = $("#_thirdContentId")
        var reg = /\((\w+)\)/g;
        var map = url.match(reg);
        for(key in map){
            var mat = map[key];
            var val = mat.replace("(","").replace(")","");
            var select = $("#"+val);
            if(select.size()==1){
                url = url.replace(map[key],select.find("option:selected").val());
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        $content.load(url, pars, function(){
            $panel.show();
        });
    },
    confirm : function(url, pars) {
        var reg = /(\w+)=\{(\w+)\}/g;
        var map = url.match(reg);
        for(key in map){
            var tKeyValue = map[key];
            url = url.replace(tKeyValue,"");
            var tValue = tKeyValue.match(/\{(\w+)\}/g);
            var iValue = tValue[0].replace("{","").replace("}","");
            var lst = $("input:checked[name="+iValue+"]","#" + this.rel);
            if(lst.size()>=1){
                $(lst).each(function(index){
                    if(/\w+$/g.test(url)){
                        url += "&";
                    }
                    url += tKeyValue.replace(tValue[0],$(this).val());
                });
            }
            else{
                alert("请选择一条记录！")
                return;
            }
        }
        if(confirm(pars)){
            $.ajax({
                type: 'GET',
                url:url,
                dataType:"json",
                cache: false,
                success: function(){
                    var $searchForm=$("form", "#mainId");
                    if($searchForm.size()==0){
                        window.location.reload(true)
                    }
                    if($searchForm.size()>=1){
                        $searchForm.submit();
                    }
                },
                error: function(){}
            });
        }
    },
    close : function(){
        $panel = $("#_thirdId");
        $panel.hide();
    }
}

