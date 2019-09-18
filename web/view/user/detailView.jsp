<%-- 
    Document   : view
    Created on : Jun 26, 2016, 3:31:08 PM
    Author     : tanbir
--%>
<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userModelManager" scope="request" class="cbvmp.admin.data.manager.UserModelManager"/>



<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

%>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>User Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>User List</small></h2>
                        <!--<div class="pull-right">

                            <a class="btn btn-success" href=href="${pageContext.request.contextPath}/cmpo/user/edit/${userModel.getId()}"><i class="fa fa-plus" aria-hidden="true"></i>Edit</a>

                        </div> -->
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        
                        <div class="col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1 col-xs-12" >
                            <table class="table table-striped table table-bordered">
                                <thead>
                                </thead>

                                <tbody>
                                    <tr>
                                        <td class="text-left"><label >Name 
                                            </label>                                           
                                        </td>
                                        <td class="text-left">  ${userModel.getName()} </td>
                                    </tr>
                                    <tr>
                                        <td class="text-left"><label >User ID 
                                            </label>                                           
                                        </td>
                                        <td class="text-left">  ${userModel.getUserId()} </td>
                                    </tr>
                                    <tr>
                                        <td class="text-left"><label >Last Login Time 
                                            </label>                                           
                                        </td>
                                        <td class="text-left"> ${userModel.getLastLoginTime()} </td>
                                    </tr>
                                    <tr>
                                        <td class="text-left"><label >User Company 
                                            </label>                                           
                                        </td>
                                        <td class="text-left"> ${userModel.getCmpoNo().getCmpoName()} </td>
                                    </tr>
                                    <!--<tr>
                                        <td class="text-left"><label >Lock Option 
                                            </label>                                           
                                        </td>
                                        <td class="text-left">  <c:choose>
                                                <c:when test="${userModel.isAdmin() == false}">
                                                    Can be locked
                                                </c:when>
                                                <c:otherwise>
                                                    Can not be locked
                                                </c:otherwise>
                                            </c:choose> </td>
                                    </tr> -->
                                    <tr>
                                        <td class="text-left"><label >User Role 
                                            </label>                                           
                                        </td>
                                        <td class="text-left">

                                            <c:forEach items="${allowedRole}" var="roles" varStatus="counter">
                                                <ul>
                                                  <!--  <i class="fa fa-chevron-circle-right" ></i> ${roles} -->
                                                  <li> ${roles}</li>
                                                </ul>


                                            </c:forEach>

                                        </td>
                                    </tr>

                                </tbody>
                            </table>

                        </div>

                    </div>

                </div>
                
                <div class="row col-md-8 col-md-offset-4 col-sm-7 col-sm-offset-3 col-xs-2">


                    <c:if test='${not empty aclMap.get("/cmpo/user/edit/") && aclMap.get("/cmpo/user/edit/").isAllowed() }'>
                        <a class="btn btn-default btn-md" href="${pageContext.request.contextPath}/cmpo/user/edit/${userModel.getId()}"><i class="fa fa-edit"></i>Edit</a>
                    </c:if>
                    <c:if test='${not empty aclMap.get("/cmpo/user/change/") && aclMap.get("/cmpo/user/change/").isAllowed() }'>
                        <a class="btn btn-default btn-md" href="${pageContext.request.contextPath}/cmpo/user/change/${userModel.getId()}"><i class="fa fa-exchange"></i>Change Password</a>
                    </c:if>
                    <c:choose>
                        <c:when test="${userModel.isActive()}"> 
                            <c:if test='${not empty aclMap.get("/cmpo/user/lock/") && aclMap.get("/cmpo/user/lock/").isAllowed() && (userModel.getId() != userSession.userModel.getId()) &&(userModel.isAdmin() == false)  }'>
                                <a class="btn btn-default btn-md"  data-toggle="tooltip" data-placement="right" title="Lock User Temporarily" href="${pageContext.request.contextPath}/cmpo/user/lock/${userModel.getId()}"><i class="fa fa-unlock" style="color:green"  ></i> Lock</a>
                                </c:if>    
                            </c:when>
                            <c:otherwise>
                                <c:if test='${not empty aclMap.get("/cmpo/user/unlock/") && aclMap.get("/cmpo/user/unlock/").isAllowed() && (userModel.getId() != userSession.userModel.getId()) &&(userModel.isAdmin() == false)  }'>
                                <a class="btn btn-default btn-md" data-toggle="tooltip" data-placement="right" title="Unlock This user" href="${pageContext.request.contextPath}/cmpo/user/unlock/${userModel.getId()}"><i class="fa fa-lock"  style="color:red"> </i>Unlock </a>
                                </c:if>
                            </c:otherwise>

                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- /page content -->
<jsp:include page="../layout/footer.jsp" /> <br/>



