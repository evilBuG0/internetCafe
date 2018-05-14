<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>


<div class="navbar navbar-default" id="navbar">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                    <img width="30" style="margin-top:-10px;margin-bottom:-10px;" src="${ctxRoot}/static/ace/avatars/dianxin.png"/>
                <small style="font-family: SimHei ;">
                       XXX学校实验室机房反馈系统
                </small>
            </a>
        </div>
       
        
        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="green">
                </li>
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${ctxRoot}/static/ace/avatars/user.jpg" alt="Jason's Photo"/>
                            <span class="user-info">
                                <small>欢迎您：</small>
                                <shiro:principal></shiro:principal>
                            </span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="${ctxRoot}/user/modify_user_password.do">
                                <i class="icon-user"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${ctxRoot}/logout.do" onclick="javascript:return confirm('确定退出系统？')">
                                <i class="icon-off"></i>
                                退出登陆
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- /.ace-nav -->
        </div>
        <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
</div>