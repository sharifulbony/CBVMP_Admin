<%-- 
    Document   : simVerOperSearch
    Created on : Aug 3, 2016, 6:41:43 PM
    Author     : Administrator
--%>

<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="cbvmp.admin.data.manager.CmpoModelManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cbvmp.admin.data.model.CmpoModel"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.export.JRHtmlExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.export.HtmlExporter"%>
<%@page import="java.util.List"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/report_sidebar.jsp" /> <br/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cmpoModelManager" scope="request" class="cbvmp.admin.data.manager.CmpoModelManager"/>
<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
    StringBuffer sbufferJsp = new StringBuffer();
    HashMap<String, JasperPrint> jpMap = (HashMap) request.getSession().getAttribute("jpMap");
    JasperPrint currentJP = null;
    if (jpMap != null) {
        currentJP = jpMap.get("SIMVEROP");
    }
    session.setAttribute("currentJp", currentJP);
    String cmpo = (String) request.getSession(false).getAttribute("cmpoParam");
    if (cmpo == null) {

        userSession.setCmpoNo(null);
    } else {

        userSession.setCmpoNo(Integer.parseInt(cmpo));
    }
    request.getSession(false).removeAttribute("cmpoParam");
%>

<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Sim Verification Consent Unconsent Report(Operator Wise)  </h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">

            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Report </small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">

                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/report/svop" method="post">
                            <div class="row">
                                <!--  cmpo dropdown --->
                                <div class="col-md-2 col-sm-4 col-xs-2">
                                </div>
                                <div class="col-md-6 col-sm-6 col-xs-8 form-group has-feedback">
                                    <label class="control-label col-md-12 col-sm-12 col-xs-12" style="text-align: left">Select Operator</label>
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
                            </div>
                            <div class="ln_solid"></div>

                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">   
                                    <div class="title_left">  
                                        <h4 style="color:red">${message}</h4>
                                        <c:remove var="message" scope="session" />
                                    </div>
                                    <button  class="btn btn-primary" type="submit">Show</button>
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
                                        <li><a href = "${pageContext.request.contextPath}/report/excel">Excel</a></li>

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
                                            if (!keys.equals("SIMVEROP")) {
                                                jpMap.put(keys, null);
                                            }
                                        }
                                    }
                                %>

                            </div>

                        </form>
                    </div>
                </div>



            </div>
        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" /> <br/>

