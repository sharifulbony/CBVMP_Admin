<%-- 
    Document   : header
    Created on : Jun 27, 2016, 12:27:02 PM
    Author     : tanbir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>CBVMP | Admin Panel </title>
        <!-- Custom Theme Style -->
        <link href="${pageContext.request.contextPath}/build/css/custom.css" rel="stylesheet">
        <!-- Bootstrap -->
        <link href="${pageContext.request.contextPath}/build/vendor/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="${pageContext.request.contextPath}/build/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/build/vendor/parsley/parsley.js"></script>
        <script src="${pageContext.request.contextPath}/build/vendor/parsley/parsley.min.js"></script>

        <script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery.min.js"></script>
        <!-- Data Tables -->
        <link href="${pageContext.request.contextPath}/build/vendor/DataTables/datatables.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/build/vendor/DataTables/datatables.min.css" rel="stylesheet">
        
        
        
    </head>
    
    

    <body class="nav-md">
        <div class="container body">
            <div class="main_container">
                <div class="col-md-3 left_col">
                    <div class="left_col scroll-view">
                        <div class="navbar nav_title" style="border: 0;">
                            <a href="${pageContext.request.contextPath}/welcome" class="site_title"><i class="fa fa-television"></i> <span>CBVMP</span></a>
                        </div>
                        <div class="profile_info">
                            <span>Welcome,</span>
                             <h2 >${sessionScope.userSession.user}</h2>
                        </div>
                        <div class="clearfix"></div>    
                        <br />
                        