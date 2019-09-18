<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="cbvmp.admin.util.security.BulkMsisdnRequest"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% BulkMsisdnRequest msisdn = (BulkMsisdnRequest) request.getSession().getAttribute("msisdnList");%>
<jsp:useBean id="deregList" scope="request" class="cbvmp.admin.data.manager.BulkDeregModelManager"/>

<%

    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

%>
<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Bulk Deregistration Module</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->
        <% if (msisdn != null) {%>
        <div class="row">
            <div class="col-md-12">
                <h4>Total Deregistration Request : <%=msisdn.getTotalCount()%>  </h4> 
                <h4>Total Valid Entry : <%=msisdn.getTotalValidNumber()%> </h4> 
                <h4>Total Invalid Entry : <%=msisdn.getTotalInValidNumber()%> </h4> 
                <table class="table table-striped" >
                    <thead class = "text-center ">
                    <th> Invalid Entry List </th>
                    </thead>
                    <tbody>
                        <c:forEach items="<%=msisdn.getInvalidMsisdnList()%>" var = "invalid" varStatus="counter">
                            <tr>
                                <!--   <td class="text-center ">
                                       $//{counter.index+1}
                                   </td>
                                -->
                                <td class="text-center">
                                    ${invalid} 
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table>


            </div>
        </div>
        <% }%>
        <c:remove var="msisdnList" scope="session" /> 


        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>View deregistration request list</small></h2>
                        <div class="pull-right">
                            <c:if test='${not empty aclMap.get("/bulk/dereg/create/") && aclMap.get("/bulk/dereg/create/").isAllowed() }'>
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/bulk/dereg/create"><i class="fa fa-cloud-upload" aria-hidden="true"></i>Upload MSISDN List</a>
                            </c:if>
                        </div>
                        <div class="clearfix"></div>
                    </div>

                    <div style="display: block;" class="x_content">

                        <table class="table table-striped" id="bulk-request-list">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Requested MSISDN</th>
                                    <th>Valid MSISDN</th>
                                    <th>Total Executed</th>
                                    <th>CMPO</th>
                                    <th>Batch Id</th>
                                    <th>Created By</th>
                                    <th>Created On</th>
                                    <th>File</th>                                    
                                    <th><br></th>
                                </tr>
                            </thead>

                            <c:forEach items="<%=deregList.listAll(userSession.getUserCmpoId())%>" var = "batch" varStatus="counter">
                                <tr>
                                    <td>${counter.index+1}</td>
                                    <td class="text-left">${batch.getTotalMsisdn()}</td>
                                    <td class="text-left">${batch.getValidMsisdn()}</td>
                                    <td class="text-left">${batch.getTotalExecuted()}</td>
                                    <td class="text-left">${batch.getCmpoNo().getCmpoName()}</td>
                                    <td class="text-left">${batch.getBatchId()}</td>
                                    <td class="text-left">${batch.getCreatedById().getName()}</td>
                                    <td class="text-left">${batch.getCreatedAt()}</td>
                                    <td class="text-left">
                                        <c:if test='${not empty aclMap.get("/bulk/dereg/download/") && aclMap.get("/bulk/dereg/download/").isAllowed() }'>
                                            <!--<a href="${pageContext.request.contextPath}/bulk/dereg/download/?filename=/var/log/bulk/${batch.getFileName()}">${batch.getFileName()} <i class="fa fa-cloud-download"> </i></a>--> 
                                        <a href="${pageContext.request.contextPath}/bulk/dereg/download/?filename=${batch.getFileName()}">${batch.getFileName()} <i class="fa fa-cloud-download"> </i></a> 

                                        </c:if>
                                    </td>
                                    <td class="text-left">
                                        <c:if test='${not empty aclMap.get("/bulk/dereg/detail/") && aclMap.get("/bulk/dereg/detail/").isAllowed() }'>
                                            <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/bulk/dereg/detail/${batch.getId()}"><i class="fa fa-edit"></i>Details</a>
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
    $("#bulk-request-list").DataTable({
        "lengthMenu": [[10, 20, 25], [10, 20, 25]]});
</script>

