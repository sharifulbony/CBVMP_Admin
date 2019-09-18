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
<jsp:useBean id="cmpoRoleModelManager" scope="request" class="cbvmp.admin.data.manager.CmpoRoleModelManager"/>


<%
//    List<CmpoRoleModel> list = cmpoRoleModelManager.listAll();
//    out.println(list.size());
//    CmpoRoleModel en = (CmpoRoleModel)list.get(0);
//    out.println(en.getName()); 
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

%>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                 <h3>User Role Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Role List</small></h2>
                        <div class="pull-right">
                            <c:if test='${not empty aclMap.get("/cmpo/role/create/") && aclMap.get("/cmpo/role/create/").isAllowed() }'>
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/cmpo/role/create"> <i class="fa fa-plus"></i>Create New Role</a>
                            </c:if>
                        </div>

                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <table class="table table-striped" id="paginate">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Created By</th>
                                    <th><br></th>
                                </tr>
                            </thead>
                            <c:forEach items="<%=cmpoRoleModelManager.listAll(userSession.getUserCmpoId())%>" var="cmpoRoleModel" varStatus="counter">
                                <tr>
                                    <td class="text-left">${counter.index+1}</td>
                                    <td class="text-left">${cmpoRoleModel.name}</td>
                                    <td class="text-left">${cmpoRoleModel.getCreatedById().getName()}</td>
                                    <td class="text-center">
                                        <c:if test='${not empty aclMap.get("/cmpo/role/edit/") && aclMap.get("/cmpo/role/edit/").isAllowed() }'>
                                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/cmpo/role/edit/${cmpoRoleModel.id}"><i class="fa fa-edit"></i>Edit</a>
                                        </c:if>
                                        <c:if test='${not empty aclMap.get("/role/permission/") && aclMap.get("/role/permission/").isAllowed() }'>
                                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/role/permission/${cmpoRoleModel.id}"><i class="fa fa-key"></i>Security</a>
                                        </c:if>                                        
<!--<a class="btn btn-danger btn-xs" href="${pageContext.request.contextPath}/cmpo/role/delete/${cmpoRoleModel.id}"><i class="fa fa-trash"></i></a>-->
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
        "lengthMenu": [[5, 10, 20], [5, 10, 20]]
    });
</script>

