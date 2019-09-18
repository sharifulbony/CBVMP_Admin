<%-- 
    Document   : view
    Created on : Apr 26, 2017, 12:12:50 PM
    Author     : rahat
--%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>


<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>



<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="blackListManager" scope="request" class="cbvmp.admin.data.manager.BlackListModelManager"/>

<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Black Listed Document Panel</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Document List</small></h2>
                        <div class="pull-right">
                            <c:if test='${not empty aclMap.get("/blackList/create/") && aclMap.get("/blackList/create/").isAllowed() }'>
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/blackList/create"><i class="fa fa-plus" aria-hidden="true"></i>Add New Document</a>
                            </c:if>
                                
                                <!--<a class="btn btn-success" href="${pageContext.request.contextPath}/blackList/create"><i class="fa fa-plus" aria-hidden="true"></i>Add New Document</a>-->
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
                                    <th>Type</th>
                                    <th>Document ID</th>
                                    <!--<th>CMPO</th>-->
                                    <th>Black listed By</th>
                                    <th><br></th>
                                </tr>
                            </thead>

                            <c:forEach items="<%=blackListManager.listAll()%>" var="blackModel" varStatus="counter">
                                <tr>
                                    <!--
                                    <td><i class="fa fa-check-circle-o fa-2x red"></i></td>
                                    -->
                                    <td>
                                        ${counter.index+1}

                                    </td>
                                    <td class="text-left">
                                        ${blackModel.getDocTypeNo().getDocType()}
                                        <!--<p class="small text-muted">Last login at ${userModel.getLastLoginTime()}</p>-->
                                    </td>
                                    <td class="text-left">${blackModel.getDocumenId()}</td>
                                 
                                    <td class="text-left">${blackModel.getCreatedBy().getName()}</td>
                                    <td class="text-center">
                                        <c:if test='${not empty aclMap.get("/blackList/delete/") && aclMap.get("/blackList/delete/").isAllowed()}'>
                                            <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/blackList/delete/${blackModel.getBlackNo()}"><i class="fa fa-eraser"></i>Remove</a>
                                        </c:if>
                                            <!--<a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/blackList/delete/${blackModel.getBlackNo()}"><i class="fa fa-eraser"></i>Remove</a>-->
                                       
                                        
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
        "lengthMenu": [[10, 50, 100], [10, 50 , 100]]});
</script>


