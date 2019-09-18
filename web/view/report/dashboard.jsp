<%-- 
    Document   : edit
    Created on : Jul 18, 2016, 3:58:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.ReportModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/report_sidebar.jsp" /> <br/>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="reportModelManager" scope="request" class="cbvmp.admin.data.manager.ReportModelManager"/>



<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Report Dashboard</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">

                        <div class="ln_solid"></div>


                    </div>
                </div>

            </div>
        </div>
        <div id="buttonBlock" style="display: none;">

        </div>
    </div>
</div>
<jsp:include page="../layout/footer.jsp" /> <br/>