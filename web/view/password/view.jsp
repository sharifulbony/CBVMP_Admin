<%-- 
   Document   : view
   Created on : Jul 12, 2016, 3:54:38 PM
   Author     : Administrator
--%>

<%@page import="cbvmp.admin.data.manager.PasswordConfigModelManager"%>
<%-- 
    Document   : view
    Created on : Jun 29, 2016, 5:39:02 PM
    Author     : Administrator
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="cbvmp.admin.data.model.PasswordConfigModel"%>

<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>

<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="passwordConfigModelManager" scope="request" class="cbvmp.admin.data.manager.PasswordConfigModelManager"/>

<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Password Policy Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Password List</small></h2>
                        <div class="pull-right">
                            <c:if test='${not empty aclMap.get("/pass/create/") && aclMap.get("/pass/create/").isAllowed() }'>
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/pass/create"><i class="fa fa-plus"></i>Add New Password Policy</a>
                            </c:if>
                        </div>

                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <table class="table table-striped" id="paginate">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Password Strength</th>
                                    <th>Start</th>
                                    <th>End</th>
                                    <th><br></th>
                                </tr>
                            </thead>
                            <c:forEach items="<%=passwordConfigModelManager.listAll()%>" var="passConfigModel" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.index+1}

                                    </td>
                                    <td>${passConfigModel.passStrength }</td>
                                    <td>${passConfigModel.passStart}</td>
                                    <td>${passConfigModel.passEnd}</td>
                                    <td>
                                        <c:if test='${not empty aclMap.get("/pass/edit/") && aclMap.get("/pass/edit/").isAllowed() }'>

                                            <a class="btn btn-default btn-xs"  href="${pageContext.request.contextPath}/pass/edit/${passConfigModel.getPassNO()}" ><i class="fa fa-edit"></i>Edit</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                        <div class="ln_solid"></div>
                        <!--                        <div class="form-group">
                                                    <div class="col-md-6 col-md-offset-4">
                        
                        
                                                        
                                                    </div>
                                                </div>-->



                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<jsp:include page="../layout/footer.jsp" /> <br/>
<script src="${pageContext.request.contextPath}/build/vendor/DataTables/datatables.min.js"></script>
<script type="text/javascript">
    $("#paginate").DataTable({
        "lengthMenu": [[5, 10, 20], [5, 10, 20]]});
</script>

