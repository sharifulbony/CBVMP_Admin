<%-- 
    Document   : edit
    Created on : Jun 30, 2016, 2:49:37 PM
    Author     : tanbir
--%>

<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="aclModelManager" scope="request" class="cbvmp.admin.data.manager.AccessControlListManager"/>

<%
    Integer id = (Integer)request.getAttribute("id");
%>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>CMPO Role Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Edit Role Security</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <form class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/role/permission" method="POST">
                            <input type="hidden" value="${id}" name="id">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <table class="table">
                                    <tr>
                                        <th>Menu Name</th>
                                        <th><br></th>
                                    </tr>
                                    <c:forEach items="<%=aclModelManager.listAll(id)%>" var="aclModel" varStatus="counter">
                                        <tr>
                                            <td>${aclModel.getAlias()}</td>
                                            <td>
                                                <div class="checkbox">
                                                    <label class="">
                                                        <input name="v${aclModel.getId()}" id="${aclModel.getId()}" ${aclModel.isAllowed() ? 'checked' : ''} type="checkbox"> Allow access
                                                    </label>
                                                </div>

                                            </td>

                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td><br></td>
                                        <td>
                                            <div class="form-group">
                                                <div class="col-md-6 col-md-offset-3">
                                                    <a href="${pageContext.request.contextPath}/permission/view" class="btn btn-primary">Cancel</a>
                                                    <button type="submit" class="btn btn-success btn-xs">Submit</button>
                                                </div>
                                            </div>    


                                        </td>

                                    </tr>

                                </table>


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


