<%-- 
    Document   : view
    Created on : Jun 29, 2016, 5:39:02 PM
    Author     : Administrator
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="cbvmp.admin.data.model.PolicyModel"%>
<%@page import="cbvmp.admin.data.manager.PolicyModelManager"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.PolicyTypesModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="policyModelManager" scope="request" class="cbvmp.admin.data.manager.PolicyModelManager"/>

<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Policy Type Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Policy List</small></h2>
                        <div class="pull-right">
                            <c:if test='${not empty aclMap.get("/policy/create/") && aclMap.get("/policy/create/").isAllowed() }'>
                                <a class="btn btn-success" href="${pageContext.request.contextPath}/policy/create"><i class="fa fa-plus" aria-hidden="true"></i>Add New Policy</a>
                            </c:if>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <table class="table table-striped" id="paginate">
                            <thead>
                                <tr>
                                    <!-- <td><th>Policy No</th></td>  -->

                                    <th>#</th>
                                    <th>Policy Type</th>
                                    <th>Value</th>
                                    <th>Start</th>
                                    <th>End</th>
                                    <th><br></th>

                                </tr>

                            </thead>

                            <c:forEach items="<%=policyModelManager.viewPolicy()%>" var="policyModel" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.index+1}

                                    </td>
                                    
                                    <td>${policyModel.policyType }</td>
                                    <td>${policyModel.policyVal}</td>
                                    <td>${policyModel.policyStart}</td>
                                    <td>${policyModel.policyEnd }</td>



                                    <td>
                                        <c:if test='${not empty aclMap.get("/policy/edit/") && aclMap.get("/policy/edit/").isAllowed() }'>
                                            <a class="btn btn-default btn-xs"  href="${pageContext.request.contextPath}/policy/edit/${policyModel.getPolicyNo()}" ><i class="fa fa-edit"></i>Edit</a>
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
