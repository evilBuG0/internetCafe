/**
 * Created by wanghongbo on 2015/1/19.
 */

function loadUrl(id, url, pars) {
    $panel = $("#" + id);
    if(url) {
        url += ((url.indexOf("?")== -1)?"?":"&") + new Date().getTime();
    }
    $panel.load(url, pars, function () {
    });
}
function search(form, callback, confirmMsg) {
    if (null != callback) {
        if (!callback) {
            return false;
        }
    }
    var $form = $(form);
    var url = $form.attr("action");
    if(url) {
        url += ((url.indexOf("?")== -1)?"?":"&") + new Date().getTime();
        $form.attr("action", url);
    }
    var rel = $form.attr("rel");
    if(!rel) {
        return true;
    }
    else{
        $panel = $("#" + rel);
        $panel.load($form.attr("action"), $form.serializeArray(), function () {});
        return false;
    }
}
function validate(form, callback, confirmMsg) {
    var $form = $(form);
    var rel = $form.attr("rel");
    if (!$form.valid($form)) {
        return false;
    }
    if(!rel){
        var _submitFn = function(){
            $.ajax({
                type: form.method || 'POST',
                url:$form.attr("action"),
                data:$form.serializeArray(),
                dataType:"json",
                cache: false,
                success: function(json){
                    if(json["type"]=="alert"){
                        if(json['data'].type=="success"){
                            alert(json['data'].message);
                            window.location.reload();
                        }
                        else{
                            alert(json['data'].message);
                            return;
                        }
                    }
                    else if(json["type"]=="confirm"){
                        if(confirm(json["data"]["message"])) {
                            $.ajax({
                                type: 'POST',
                                url: json["data"]["url"],
                                data: {},
                                dataType: "json",
                                cache: false,
                                success: function (json) {
                                    var $searchForm=$("form", "#mainId");
                                    if($searchForm.size()==0){
                                        window.location.reload(true)
                                    }
                                    if($searchForm.size()>=1){
                                        $searchForm.submit();
                                    }
                                },
                                error: function () {
                                }
                            });
                        }
                        return;
                    }

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
        _submitFn();
        return false;
    }
    var tagName = $("#" + rel).get(0).tagName;
    if (tagName == "FORM") {
        var _submitFn = function(){
            $.ajax({
                type: form.method || 'POST',
                url:$form.attr("action"),
                data:$form.serializeArray(),
                dataType:"json",
                cache: false,
                success: function(json){
                    if(json["type"]=="alert"){
                        alert(json["data"]["message"])
                        return;
                    }
                    else if(json["type"]=="dialog") {
                        if(confirm(json["data"])){

                        }
                    }
                    win.close();
                    $("#" + rel).submit();
                },
                error: function(){}
            });
        }
        _submitFn();
        return false;
    }
    else if (tagName == "DIV") {
        var _submitFn = function(){
            $.ajax({
                type: form.method || 'POST',
                url:$form.attr("action"),
                data:$form.serializeArray(),
                dataType:"json",
                cache: false,
                success: function(json){
                    if(json["type"]=="alert"){
                        alert(json["data"]["message"])
                        return;
                    }
                    win.close();
                    $("#" + rel).submit();
                },
                error: function(){}
            });
        }
        _submitFn();
        return false;
    }
}

function iframeValidate(form, callback, confirmMsg) {
    var $form = $(form), $iframe = $("#callbackframe");
    if (!$form.valid($form)) {
        return false;
    }
    var url = $form.attr("action");
    if(url) {
        url += ((url.indexOf("?")== -1)?"?":"&") + new Date().getTime();
        $form.attr("action", url);
    }
//    alert($form.has("#pladress"));
    if($form.find("input[name='file']").val()!=""){
        if ($iframe.size() == 0) {
            $iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
        }
        if(!form.ajax) {
            $form.append('<input type="hidden" name="ajax" value="1" />');
        }
        form.target = "callbackframe";

        var rel = $form.attr("rel");
        $iframe.attr("rel", rel);

        _iframeResponse($iframe[0], callback);
    }else{
        alert("请选择一个正确的文件！");
        return false;
    }
    return true;
}

function materielValidate(form, callback, confirmMsg) {
    var $form = $(form), $iframe = $("#callbackframe");
    if (!$form.valid($form)) {
        return false;
    }
    var url = $form.attr("action");
    if(url) {
        url += ((url.indexOf("?")== -1)?"?":"&") + new Date().getTime();
        $form.attr("action", url);
    }
//    alert($form.has("#pladress"));

    if ($iframe.size() == 0) {
        $iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
    }
    if(!form.ajax) {
        $form.append('<input type="hidden" name="ajax" value="1" />');
    }
    form.target = "callbackframe";

    var rel = $form.attr("rel");
    $iframe.attr("rel", rel);

    _iframeResponse($iframe[0], callback);

    return true;
}

//created By qianjunjie 用户群方案特例
function planValidate(form, callback, confirmMsg) {
    var $form = $(form), $iframe = $("#callbackframe");
    if($("#planName").val()==""){
        alert("请输入方案名称！");
        return false;
    }
    if($("#planType option:selected").val()=="1"){
        if($("#planAddress").val()==""){
            alert("请选择方案地区/市局！");
            return false;
        }else{
            if ($iframe.size() == 0) {
                $iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
            }
            if(!form.ajax) {
                $form.append('<input type="hidden" name="ajax" value="1" />');
            }
            form.target = "callbackframe";

            var rel = $form.attr("rel");
            $iframe.attr("rel", rel);

            _iframeResponse($iframe[0], callback);
            return true;
        }
    }else{
        if($("input[name='filePath']").val()==""){
            alert("请选择一个正确的导入文件！");
            return false;
        }else{
            if ($iframe.size() == 0) {
                $iframe = $("<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>").appendTo("body");
            }
            if(!form.ajax) {
                $form.append('<input type="hidden" name="ajax" value="1" />');
            }
            form.target = "callbackframe";

            var rel = $form.attr("rel");
            $iframe.attr("rel", rel);

            _iframeResponse($iframe[0], callback);
            return true;
        }
    }
}

function _iframeResponse(iframe, callback){
    var $iframe = $(iframe), $document = $(document);
    $document.trigger("ajaxStart");

    $iframe.bind("load", function(event){
        $iframe.unbind("load");
        $document.trigger("ajaxStop");
        if (iframe.src == "javascript:'%3Chtml%3E%3C/html%3E';" || // For Safari
            iframe.src == "javascript:'<html></html>';") { // For FF, IE
            return;
        }
        var doc = iframe.contentDocument || iframe.document;
        // fixing Opera 9.26,10.00
        if (doc.readyState && doc.readyState != 'complete') return;
        // fixing Opera 9.64
        if (doc.body && doc.body.innerHTML == "false") return;
        var response;
        if (doc.XMLDocument) {
            // response is a xml document Internet Explorer property
            response = doc.XMLDocument;
        } else if (doc.body){
            try{
                response = $iframe.contents().find("body").text();
                response = jQuery.parseJSON(response);
            } catch (e){ // response is html document or plain text
                response = doc.body.innerHTML;
            }
        } else {
            // response is a xml document
            response = doc;
        }
        var rel = $iframe.attr("rel");
        if(callback){
            callback(response);
        }else{
            var json = response;
            if(json["type"]=="alert"){
                if(json['data'].type=="success"){
                    alert(json['data'].message);
                    window.location.reload();
                }else{
                    alert(json['data'].message);
                    return;
                }
            }
            win.close();
            var $searchForm=$("form", "#mainId");
            if($searchForm.size()==0){
                window.location.reload(true)
            }
            if($searchForm.size()>=1){
                $searchForm.submit();
            }
        }
        thirdWin.close();

    });
}
