<%-- 
    Document   : sidebar
    Created on : Jun 27, 2016, 12:27:12 PM
    Author     : tanbir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="cbvmp.admin.data.model.AccessControlListModel"%>
<%@page import="java.util.HashMap"%>
<%
    HashMap<String, AccessControlListModel> aclMap = (HashMap) request.getSession().getAttribute("aclMap");
    if (aclMap == null) {
        aclMap = new HashMap<String, AccessControlListModel>();
    }

%>



<!-- sidebar menu -->
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
    <div class="menu_section">
        <ul class="nav side-menu">

            <c:if test='${not empty aclMap.get("/cmpo/user/") && aclMap.get("/cmpo/user/").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/cmpo/user"><i class="fa fa-users"></i> User Settings</a>

                </c:if>


                <c:if test='${not empty aclMap.get("/cmpo/role/") && aclMap.get("/cmpo/role/").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/cmpo/role"><i class="fa fa-user-plus "></i> User Role</a>
                </li>
            </c:if>


            <!--<li><a><i class="fa fa-server"></i> Change Password</a>
            </li>-->
            <c:if test='${not empty aclMap.get("/policy/view/") && aclMap.get("/policy/view/").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/policy/view"><i class="fa fa-wrench"></i> Policy</a>
                </li>
            </c:if>

            <c:if test='${not empty aclMap.get("/pass/view/") && aclMap.get("/pass/view/").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/pass/view"><i class="fa fa-magic"></i> Password</a>
                </li>
            </c:if>
            <c:if test='${not empty aclMap.get("/bulk/dereg/view") && aclMap.get("/bulk/dereg/view").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/bulk/dereg/view"><i class="fa fa-cogs"></i> Bulk Deregistration</a>
                </li>
            </c:if>
             <c:if test='${not empty aclMap.get("/blackList/view/") && aclMap.get("/blackList/view/").isAllowed() }'>
            <li><a href="${pageContext.request.contextPath}/blackList/view"><i class="fa fa-user-times" ></i> Black List Settings</a>
            </li>  
             </c:if>

            <c:if test='${not empty aclMap.get("/report/") && aclMap.get("/report/").isAllowed() }'>
                <li><a href="${pageContext.request.contextPath}/report"><i class="fa fa-bar-chart"></i> Report</a>
                    <!--
                    <ul class="nav child_menu">
                        <li><a href="general_elements.html">Yearly NID Registration</a></li>
                        <li><a href="media_gallery.html">Monthly Report</a></li>
                        <li><a href="typography.html">Daily Report</a></li>
                    </ul>
                    -->
                </li>
            </c:if>


            <!--  <li><a><i class="fa fa-sign-out"></i> Logout</a>
  
              </li>-->

        </ul>
    </div>

</div>
<!-- /sidebar menu -->

<!--
<!-- /menu footer buttons -->
<!--
<div class="sidebar-footer hidden-small">
    <a data-toggle="tooltip" data-placement="top" title="Settings">
        <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
    </a>
    
    <a data-toggle="tooltip" data-placement="top" title="FullScreen">
        <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
    </a>
    <a data-toggle="tooltip" data-placement="top" title="Lock">
        <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
    </a>
    
    <a data-toggle="tooltip" data-placement="top" title="Logout">
        <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
    </a>
</div>
-->
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
                        <!--
                        <li> 
                            <a href="javascript:;">
                                <span class="badge bg-red pull-right">50%</span>
                                <span>Settings</span>
                            </a>
                        </li>
                        
                        <li><a href="javascript:;">Help</a></li>
                        -->
                        <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                    </ul>
                </li>


            </ul>
        </nav>

    </div>
</div>
<!-- /top navigation -->
