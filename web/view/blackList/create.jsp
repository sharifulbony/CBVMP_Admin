<%-- 
    Document   : create
    Created on : Apr 27, 2017, 12:40:19 PM
    Author     : rahat
--%>



<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="java.util.List"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="documentTypeModelManager" scope="request" class="cbvmp.admin.data.manager.DocumentTypeModelManager"/>
<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

%>


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
                        <h2> <small>Add Document to Blacklist</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/blackList/create" method="POST">
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Document Type</label>

                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="docType" class="form-control">
                                        <c:forEach items="<%=documentTypeModelManager.listAll()%>" var="docTypeModel" varStatus="counter">
                                            <option value="${docTypeModel.docTypeNo}" ${docTypeModel.getDocTypeNo()== userSession.getDocType()  ? 'selected' : ''} >${docTypeModel.docType}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Document ID</label>

                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input  class="form-control col-md-7 col-xs-12"  name="docId" placeholder="Enter ID here"required="required" type="text"  >
                                </div>
                            </div>


                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <h4 style="color: red"> ${message}</h4>
                                    <c:remove var="message" scope="session" />                         
                                </div>
                                <div class="col-md-6 col-md-offset-3">
                                    <a href="${pageContext.request.contextPath}/blackList/view" class="btn btn-primary">Cancel</a>
                                    <button type="submit" class="btn btn-success">Add</button>
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


