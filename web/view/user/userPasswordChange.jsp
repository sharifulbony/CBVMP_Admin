<%-- 
    Document   : userPasswordChange
    Created on : Jul 20, 2016, 11:04:24 AM
    Author     : rahat
--%>

<%@page import="cbvmp.admin.data.model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userModelManager" scope="request" class="cbvmp.admin.data.manager.UserModelManager"/>
<jsp:useBean id="userRoleMapModelManager" scope="request" class="cbvmp.admin.data.manager.UserRoleMapModelManager"/>

<%

    UserModel userModel = (UserModel) request.getAttribute("userModel");

%>

<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>CMPO User</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">

            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Change Password </small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/cmpo/user/change" method="post">

                            <input type="hidden" name="userId" value="${userModel.getId()}">

                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="userName">User ID <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input class="form-control col-md-7 col-xs-12" name="userName" placeholder="User ID" data-parsley-required="true" disabled readonly type="text" value="${userModel.getUserId()}">
                                </div>
                            </div>




                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">New Password <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="password" class="form-control col-md-7 col-xs-12" name="password" placeholder="" autocomplete="off" data-parsley-required="true" type="password">
                                </div>
                            </div>
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="retypePassword">Retype Password <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="retypePassword" class="form-control col-md-7 col-xs-12"  name="retypePassword" placeholder="" autocomplete="off" data-parsley-required="true" type="password">
                                </div>
                            </div>
                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <h4 style="color: red"> ${message}</h4>
                                    <c:remove var="message" scope="session" /> 
                                    <a href="${pageContext.request.contextPath}/cmpo/user" class="btn btn-primary">Cancel</a>
                                    <button type="submit" class="btn btn-success">Change</button>
                                </div>
                            </div>
                        </form>


                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /page content -->
<jsp:include page="../layout/footer.jsp" /> <br/>










