<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<head>
    <title>Authority</title>
   <%-- <link rel="stylesheet" href="${ctxRoot}/static/framework/zTree/css/demo.css" type="text/css">--%>
    <link rel="stylesheet" href="${ctxRoot}/static/framework/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script src="${ctxRoot}/static/framework/zTree/js/jquery-1.4.4.min.js"></script>
    <script src="${ctxRoot}/static/framework/zTree/js/jquery.ztree.core-3.5.js"></script>
    <script src="${ctxRoot}/static/framework/zTree/js/jquery.ztree.excheck-3.5.js"></script>
    <SCRIPT type="text/javascript">
        <!--
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };

        var zNodes = ${menuTree};

        function setCheck() {
            var zTree = $.fn.zTree.getZTreeObj("menuTree");
            zTree.setting.check.chkboxType =  { "Y" : "p", "N" : "s" };
        }

        $(document).ready(function(){
            $.fn.zTree.init($("#menuTree"), setting, zNodes);
            setCheck();
            $("#py").bind("change", setCheck);
            $("#sy").bind("change", setCheck);
            $("#pn").bind("change", setCheck);
            $("#sn").bind("change", setCheck);
            var ajaxbg = $("#background, #progressBar");
            ajaxbg.hide();
        });
        //-->

        function saveRoleMenu() {
            var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getCheckedNodes(true);
            if ($("#isClick").val() == "Y") {
                alert("系统正在处理，请勿多次点击！");
                return false;
            }
            if (nodes.length == 0) {
                alert("请选择权限！");
                return false;
            } else {
                $("#isClick").val("Y");
                var menuIds = '';
                for (var i = 0 ; i < nodes.length; i++ ) {
                    menuIds += nodes[i]['id'] + ",";
                }
                $.ajax({
                    type: "POST",
                    url: "save_roleMenu.do",
                    data: {"menuIds":  menuIds.slice(0, -1), "roleId": $("#roleId").val() },
                    dataType: "json",
                    cache: false,
                    success: function(data) {
                        alert("授权成功！");
                        $("#isClick").val(null);
                        win.close();
                    },
                    error: function(){
                        $("#isClick").val(null);
                    }
                });
            }
        }

    </SCRIPT>
</head>
<form action="${ctx}/save_roleMenu.do" method="post" onsubmit="return validate(this)">
    <input type="hidden" id="roleId" value="${roleId}">
    <input type="hidden" id="isClick">
    <div class="content_wrap">
        <div class="zTreeDemoBackground left">
            <ul id="menuTree" class="ztree"></ul>
        </div>
    </div>
    <div class="ui_t_foot">
        <button class="btn" type="button" onclick="win.close();">取消</button>
        <button class="btn btn-primary" type="button" onclick="saveRoleMenu();">确定</button>
    </div>
</form>
