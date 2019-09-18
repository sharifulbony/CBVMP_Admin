<%-- 
    Document   : SimVerConsentUnconsentSearch
    Created on : Aug 1, 2016, 5:46:55 PM
    Author     : Administrator
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
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
<link href="${pageContext.request.contextPath}/build/vendor/datepicker/bootstrap-datepicker3.standalone.min.css" rel="stylesheet">
<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
    StringBuffer sbufferJsp = new StringBuffer();
    HashMap<String, JasperPrint> jpMap = (HashMap) request.getSession().getAttribute("jpMap");
    JasperPrint currentJP = null;
    if (jpMap != null) {
        currentJP = jpMap.get("AUDIT");
    }
    SimpleDateFormat dformat = new SimpleDateFormat("yyyy/MM/dd");
    Date now = new Date();
    String curDate = dformat.format(now);
    session.setAttribute("currentJp", currentJP);
    String cmpo = (String) request.getSession().getAttribute("cmpo");
    String dFrom = (String) request.getSession().getAttribute("dFrom");
    String dTo = (String) request.getSession().getAttribute("dTo");
    String groupByTimeParam = (String) request.getSession().getAttribute("groupByTimeParam");

    if (cmpo == null) {

        userSession.setCmpoNo(null);
    } else {

        userSession.setCmpoNo(Integer.parseInt(cmpo));
    }
    if (dFrom != null) {
        userSession.setDateFrom(dFrom);

    } else {
        userSession.setDateFrom(curDate);

    }
    if (dTo != null) {
        userSession.setDateTo(dTo);

    } else {
        userSession.setDateTo(curDate);
    }
    if (groupByTimeParam != null) {
        userSession.setGroupByTimeParam(groupByTimeParam);

    } else {
        userSession.setGroupByTimeParam("");
    }

    request.getSession().removeAttribute("dFrom");
    request.getSession().removeAttribute("dTo");
    request.getSession().removeAttribute("groupByTimeParam");
    request.getSession().removeAttribute("cmpo");

%>

<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Audit Trail  </h3>
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
                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/report/audit" method="post">
                            <div class="row">
                                <!--  cmpo dropdown --->
                                <div class="col-md-6 col-sm-12 col-xs-3 form-group has-feedback">
                                    <label class="control-label col-md-12 col-sm-12 col-xs-6" style="text-align: left">Select Operator</label>
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
                                <!--  groupby dropdown --->
                                <div class="col-md-6 col-sm-12 col-xs-3 form-group has-feedback">
                                    <label class="control-label col-md-12 col-sm-12 col-xs-6" style="text-align: left">Report Type</label>
                                    <select name="groupByTime"  class="form-control">


                                        <option  value="dd-mm-yyyy" ${userSession.getGroupByTimeParam()=="dd-mm-yyyy"  ? 'selected' : ''}> Daily</option>
                                        <option value="IW &quot of &quot yyyy" ${userSession.getGroupByTimeParam()=="IW \" of \" yyyy" ? 'selected' : ''}>Weekly</option>
                                        <option value="MONTH yyyy" ${userSession.getGroupByTimeParam()=="MONTH yyyy"  ? 'selected' : ''}>Monthly</option>
                                        <option value="q &quot of &quot yyyy" ${userSession.getGroupByTimeParam()=="q \" of \" yyyy"  ? 'selected' : ''}>Quarterly</option>
                                        <option value="yyyy" ${userSession.getGroupByTimeParam()=="yyyy"  ? 'selected' : ''}>Yearly</option>




                                    </select>

                                </div>
                                <!--  date --->
                                <div class="col-md-6 col-sm-6 form-group has-feedback ">
                                    <label class="control-label col-md-12 col-sm-12 col-xs-12" style="text-align: left"> Date Range</label>
                                    <div class="input-group input-daterange   has-feedback " data-provide="datepicker">
                                        <input type="text" class="form-control" name="dateFrom" value="${userSession.getDateFrom()}">
                                        <span class="input-group-addon">to</span>
                                        <input type="text" class="form-control" name="dateTo" value="${userSession.getDateTo()}">
                                    </div>
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
                                            if (!keys.equals("AUDIT")) {
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
<script src="${pageContext.request.contextPath}/build/vendor/datepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/build/vendor/datepicker/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/build/vendor/datepicker/bootstrap-datepicker.min.js"></script>

<script>
                                           $(document).ready(function () {
                                               //        console.log($('.input-daterange input'));
                                               $.fn.datepicker.defaults.format = 'yyyy/mm/dd';
                                               $.fn.datepicker.defaults.endDate = '0d';
                                               $.fn.datepicker.defaults.autoclose = false;
                                               $.fn.datepicker.defaults.clearBtn = false;
                                               $('.input-daterange input').each(function () {
                                                   var defaultDate = $(this)[0].defaultValue.split('/');
                                                   if (defaultDate == '') {
                                                       var today = Date.UTC(2016, 8, 10);
                                                       console.log(today);
                                                       var toSetDate = new Date(Date.UTC(today.getFullYear(), today.getMonth(), today.getDate()));
                                                   } else {
                                                       var toSetDate = Date.UTC(parseInt(defaultDate[0]), parseInt(defaultDate[1]) - 1, parseInt(defaultDate[2]));

                                                   }
                                                   console.log(today);
                                                   console.log(today.getMonth());
                                                   console.log(today.today.getDate());
                                                   console.log(today.today.getFullYear());
                                                   $(this).datepicker('update', '2011/03/05');
                                               });
                                           });
</script>



