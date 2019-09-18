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
                        <div class="pull-right">
                            <c:if test='${not empty aclMap.get("/cmpo/user/create/") && aclMap.get("/cmpo/user/create/").isAllowed() }'>
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/cmpo/user/create"><i class="fa fa-plus" aria-hidden="true"></i>Create New User</a>
                            </c:if>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <table class="table table-striped" id="paginate">
                            <thead>
                                <tr>
                                    <!--
                                    <th><br></th>
                                    -->
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>User ID</th>
                                    <th>Company</th>
                                    <th>Created By</th>
                                    <th><br></th>
                                </tr>
                            </thead>

                            <c:forEach items="<%=userModelManager.listAll(userSession.getUserCmpoId())%>" var="userModel" varStatus="counter">
                                <tr>
                                    <!--
                                    <td><i class="fa fa-check-circle-o fa-2x red"></i></td>
                                    -->
                                    <td>
                                        ${counter.index+1}

                                    </td>
                                    <td class="text-left">
                                        ${userModel.getName()}
                                        <p class="small text-muted">Last login at ${userModel.getLastLoginTime()}</p>
                                    </td>
                                    <td class="text-left">${userModel.getUserId()}</td>
                                    <td class="text-left">${userModel.getCmpoNo().getCmpoName()}</td>
                                    <td class="text-left">${userModel.getCreatedById().getName()}</td>
                                    <td class="text-center">
                                        <c:if test='${not empty aclMap.get("/cmpo/user/edit/") && aclMap.get("/cmpo/user/edit/").isAllowed()}'>
                                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/cmpo/user/edit/${userModel.getId()}"><i class="fa fa-edit"></i>Edit</a>
                                        </c:if>
                                        <c:if test='${not empty aclMap.get("/cmpo/user/change/") && aclMap.get("/cmpo/user/change/").isAllowed() }'>
                                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/cmpo/user/change/${userModel.getId()}"><i class="fa fa-exchange"></i>Change Password</a>
                                        </c:if>
                                        <c:choose>
                                            <c:when test="${userModel.isActive()}"> 
                                                <c:if test='${not empty aclMap.get("/cmpo/user/lock/") && aclMap.get("/cmpo/user/lock/").isAllowed() && (userModel.getId() != userSession.userModel.getId()) &&(userModel.isAdmin() == false)  }'>
                                                    <a class="btn btn-default btn-xs"  data-toggle="tooltip" data-placement="right" title="Lock User Temporarily" href="${pageContext.request.contextPath}/cmpo/user/lock/${userModel.getId()}"><i class="fa fa-unlock" style="color:green"  > </i>Lock</a>
                                                    </c:if>    
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test='${not empty aclMap.get("/cmpo/user/unlock/") && aclMap.get("/cmpo/user/unlock/").isAllowed() && (userModel.getId() != userSession.userModel.getId()) &&(userModel.isAdmin() == false) }'>
                                                    <a class="btn btn-default btn-xs" data-toggle="tooltip" data-placement="right" title="Unlock This user" href="${pageContext.request.contextPath}/cmpo/user/unlock/${userModel.getId()}"><i class="fa fa-lock"  style="color:red"> </i>Unlock</a>
                                                    </c:if>
                                                </c:otherwise>

                                        </c:choose>
                                        <c:if test='${not empty aclMap.get("/cmpo/user/view/") && aclMap.get("/cmpo/user/view/").isAllowed() }'>
                                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/cmpo/user/view/${userModel.getId()}"><i class="fa fa-edit"></i>View</a>
                                        </c:if>
                                            <c:if test='${(not empty aclMap.get("/cmpo/user/logout/") && aclMap.get("/cmpo/user/logout/").isAllowed()) && (userModel.getId() != userSession.userModel.getId()) &&(userModel.isAdmin() == false)  }'>
                                            <%--<c:if test='${ (userModel.getId() != userSession.userModel.getId()) &&(userModel.isAdmin() == false)  }'>--%>
                                                    <a class="btn btn-default btn-xs"  data-placement="right" title="Browser Session Clear" href="${pageContext.request.contextPath}/cmpo/user/logout/${userModel.getId()}"><i class="fa fa-sign-out"> </i>Clear Session</a>
                                                    </c:if>  
                                    </td>
                                </tr>
                            </c:forEach>  
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /page content -->
<jsp:include page="../layout/footer.jsp" /> <br/>

<script src="${pageContext.request.contextPath}/build/vendor/DataTables/datatables.min.js"></script>
<script type="text/javascript">
    $("#paginate").DataTable({
        "lengthMenu": [[5, 10, 20], [5, 10, 20]]});
</script>

