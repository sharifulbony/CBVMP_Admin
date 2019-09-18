<%-- 
    Document   : edit
    Created on : Jul 18, 2016, 3:58:51 PM
    Author     : Administrator
--%>

<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="java.util.HashMap"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.HtmlExporter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/report_sidebar.jsp" /> <br/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cmpoModelManager" scope="request" class="cbvmp.admin.data.manager.CmpoModelManager"/>

<jsp:useBean id="documentTypeModelManager" scope="request" class="cbvmp.admin.data.manager.DocumentTypeModelManager"/>
<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
    StringBuffer sbufferJsp = new StringBuffer();
    HashMap<String, JasperPrint> jpMap = (HashMap) request.getSession().getAttribute("jpMap");
    JasperPrint currentJP = null;
    if (jpMap != null) {
        currentJP = jpMap.get("MSISDN");

    } else if (true) {
        Integer temp;
    }
    session.setAttribute("currentJp", currentJP);

    String docTypeNo = (String) request.getSession().getAttribute("docTypeNo");
    String cmpo = (String) request.getSession().getAttribute("cmpo");
    Integer docType = (Integer) request.getSession().getAttribute("docType");

    userSession.setDocType(docType);
    if (cmpo == null) {

        userSession.setCmpoNo(null);
    } else {

        userSession.setCmpoNo(Integer.parseInt(cmpo));
    }

    if (docTypeNo == null) {
        docTypeNo = "";
    }
    request.getSession().removeAttribute("docTypeNo");
    request.getSession().removeAttribute("docType");
    request.getSession().removeAttribute("cmpo");

%>
<!-- page content -->
<!-- page content -->
<div class="right_col" role="main">
    <div class="container-fluid">
        <div class="page-title">
            <div class="title_left">
                <h3> MSISDN Against Document ID  </h3>
            </div>
        </div>
        <!--<div class="clearfix"></div>-->

        <div class="row">

            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Report</small></h2>
                        <div class="clearfix"></div>
                    </div>

                    <div style="display: block;" class="x_content">

                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/report/msisdn" method="post">


                            <div class="row">
                                <div class="col-md-6 col-sm-4 col-xs-4 form-group has-feedback">
                                    <label class="control-label col-md-12 col-sm-12 col-xs-6" style="text-align: left">Document Type</label>

                                    <select name="docType" class="form-control">
                                        <c:forEach items="<%=documentTypeModelManager.listAll()%>" var="docTypeModel" varStatus="counter">

                                            <option value="${docTypeModel.docTypeNo}" ${docTypeModel.getDocTypeNo()== userSession.getDocType()  ? 'selected' : ''} >${docTypeModel.docType}</option>
                                        </c:forEach>
                                    </select>

                                </div>




                                <div class="col-md-6 col-sm-4 col-xs-4 form-group has-feedback">
                                    <label class="control-label col-md-12 col-sm-12 col-xs-6" style="text-align: left">Select CMPO</label>

                                    <select name="cmpo" class="form-control">
                                        <c:if test="${userSession.getUserModel().getIsAdmin()==1}">
                                            <option value="">All operator</option>
                                        </c:if> 
                                        <c:forEach items="<%=cmpoModelManager.listAll(userSession.getUserCmpoId())%>" var="cmpoModel" varStatus="counter">
                                             <c:if test="${cmpoModel.getId()!=7}">
                                            <option value="${cmpoModel.getId()}" ${cmpoModel.getId()== userSession.getCmpoNo()  ? 'selected' : ''} >${cmpoModel.getCmpoName()}</option>
                                             </c:if>
                                        </c:forEach>


                                    </select>

                                </div>
                                <div class="col-md-6 col-sm-4 col-xs-4 form-group has-feedback">
                                    <label class="control-label col-md-12 col-sm-12 col-xs-6" style="text-align: left">Document No <span class="required">*</span>
                                    </label>

                                    <input  class="form-control col-md-7 col-xs-12"  name="docTypeNo" placeholder="Enter  Document Number" required="required" type="text"  value="<%=docTypeNo%>" >


                                </div>

                            </div>
                            <div class="ln_solid"></div>

                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">   
                                    <div class="title_left">  
                                        <h4 style="color:red">${message}</h4>
                                        <c:remove var="message" scope="session" />
                                    </div>
                                    <button  class="btn btn-primary" type="submit" >Show</button>
                                    <a href="${pageContext.request.contextPath}/report">
                                        <button class="btn btn-danger" type="button">Cancel</button>
                                    </a>
                                    <%

                                        if (currentJP != null && currentJP.getPages().size() > 0) {
                                            Integer last = (Integer) request.getSession().getAttribute("lastPageIndex");
                                            Integer currentPageIndex = (Integer) request.getSession().getAttribute("currentPageIndex");

                                            HtmlExporter exporterJsp = new HtmlExporter();
                                            exporterJsp.setParameter(JRExporterParameter.JASPER_PRINT, currentJP);
                                            exporterJsp.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbufferJsp);
                                            exporterJsp.setParameter(JRExporterParameter.PAGE_INDEX, currentPageIndex);//                               
                                            exporterJsp.exportReport();

                                    %>


                                    <button type = "button" class = "btn btn-primary dropdown-toggle" data-toggle = "dropdown">
                                        Export
                                        <span class = "caret"></span>
                                    </button>

                                    <ul class = "dropdown-menu" role = "menu">
                                        <li><a href = "${pageContext.request.contextPath}/report/pdf">PDF</a></li>
                                        <li class = "divider"></li>
                                        <li><a href = "${pageContext.request.contextPath}/report/csv">CSV</a></li>
                                        <li class = "divider"></li>
                                        <li><a href = "${pageContext.request.contextPath}/report/docx">DOCX</a></li>
                                        <li class = "divider"></li>
                                        <li  name="excel" value="Msidsdn"> <a href = "${pageContext.request.contextPath}/report/excel">Excel</a></li>

                                    </ul>

                                </div>


                                <%=sbufferJsp.toString()%>
                                <div class="row">
                                    <div class="col-md-4 col-sm-4 col-xs-4 pull-right" > Navigate to page No:

                                        <select  onchange="window.location.href = this.value">
                                            <c:forEach var="i" begin="0" end="<%=last%>">
                                                <option  value="?page=${i}" ${currentPageIndex == i ? 'selected' : ''}> ${i+1} </option>
                                            </c:forEach>
                                        </select>

                                    </div>
                                </div>


                                <%}
                                    if (jpMap != null) {
                                        for (String keys : jpMap.keySet()) {
                                            if (!keys.equals("MSISDN")) {
                                                jpMap.put(keys, null);
                                            }
                                        }

                                    }

                                %>
                            </div> 


                    </div>

                    </form>




                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" /> <br/>  







