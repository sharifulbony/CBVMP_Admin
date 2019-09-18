<%-- 
    Document   : sidebar
    Created on : Jun 27, 2016, 12:27:12 PM
    Author     : tanbir
--%>
<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="cbvmp.admin.data.model.AccessControlListModel"%>
<%@page import="java.util.HashMap"%>

<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

    HashMap<String, AccessControlListModel> aclMap = (HashMap) request.getSession().getAttribute("aclMap");
    if (aclMap == null) {
        aclMap = new HashMap<String, AccessControlListModel>();
    }

%>



<!-- sidebar menu -->
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
    <div class="menu_section">
        <ul class="nav side-menu">
            <c:if test='${not empty aclMap.get("/report") && aclMap.get("/report").isAllowed() }'>
                <li><a  href="${pageContext.request.contextPath}/report"><i class="fa fa-home"></i>Dashboard</a>    

                </li>
            </c:if>
            <c:if test='${not empty aclMap.get("/report/thres") && aclMap.get("/report/thres").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report/thres"><i class="fa fa-server"></i> Threshold Exceed Report</a>
                </li>
            </c:if>

            <!--added on 11-7-17-->

            <c:if test='${not empty aclMap.get("/report/msisdn/search") && aclMap.get("/report/msisdn/search").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report/msisdn/search"><i class="fa fa-server"></i> Msisdn Search Report</a>               </li>
            </c:if>


            <!--<li><a><i class="fa fa-server"></i> Change Password</a>
            </li>-->
            <c:if test='${not empty aclMap.get("/report/msisdn") && aclMap.get("/report/msisdn").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report/msisdn"><i class="fa fa-server"></i>MSISDN Against NID Report</a>
                </li>
            </c:if>
            <c:if test='${not empty aclMap.get("/report/svdate") && aclMap.get("/report/svdate").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report/svdate"><i class="fa fa-server"></i>Sim Verification Report(DateWise)</a>
                </li>
            </c:if>
            <c:if test='${not empty aclMap.get("/report/svop") && aclMap.get("/report/svop").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report/svop"><i class="fa fa-server"></i>Sim Verification Report(CMPO wise)</a>
                </li>
            </c:if>
            <c:if test='${not empty aclMap.get("/report/sv") && aclMap.get("/report/sv").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report/sv"><i class="fa fa-server"></i>Sim Verification Report</a>
                </li>
            </c:if>
            <c:if test='${not empty aclMap.get("/report/audit") && aclMap.get("/report/audit").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report/audit"><i class="fa fa-server"></i>Audit Trail</a>
                </li>
            </c:if>

            <c:if test='${not empty aclMap.get("/log/monitor/") && aclMap.get("/log/monitor/").isAllowed() && userSession.userModel.getIsAdmin()==1}'>
                <li><a href="http://172.29.1.91:5601" target="_blank"><i class="fa fa-server"></i>System Log Monitor</a>
                </li>
            </c:if>


        </ul>
    </div>

</div>
<!-- /sidebar menu -->

<!-- /menu footer buttons -->

<!-- /menu footer buttons -->
</div>
</div>
<!-- top navigation -->
<div class="top_nav">
    <div class="nav_menu">
        <nav>
            <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="">
                    <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <i class="fa fa-user"></i> <${sessionScope.userSession.user}
                        <span class=" fa fa-angle-down"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-usermenu pull-right">
                        <li><a href="${pageContext.request.contextPath}/change/password"><i class="fa fa-key pull-right"></i> Change Password</a></li>

                        <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </ul>
                </li>


            </ul>
        </nav>

    </div>
</div>
<!-- /top navigation -->


