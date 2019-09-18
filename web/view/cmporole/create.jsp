<%-- 
    Document   : create
    Created on : Jun 30, 2016, 11:14:05 AM
    Author     : tanbir
--%>

<%@page import="cbvmp.admin.data.BaseClass"%>
<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cmpoModelManager" scope="request" class="cbvmp.admin.data.manager.CmpoModelManager"/>


<%
//    List<CmpoRoleModel> list = cmpoRoleModelManager.listAll();
//    out.println(list.size());
//    CmpoRoleModel en = (CmpoRoleModel)list.get(0);
//    out.println(en.getName()); 
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
   //BaseClass base= (BaseClass) request.getSession().getAttribute("base");
%>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>CMPO Role Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Create User Role</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="col-md-6 col-md-offset-3">
                        <h4 style="color: red"> ${message}</h4>
                        <c:remove var="message" scope="session" />                         
                    </div>
                    <div style="display: block;" class="x_content">
                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/cmpo/role/create" method="POST">
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="cmpoRoleType">Role Name <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input  id="cmpoRoleType" class="form-control col-md-7 col-xs-12"  name="cmpoRoleType" placeholder="" data-parsley-required="true" type="text">
                                </div>
                            </div>
                            <!--<div class="form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Operator</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">

                                    <select name="cmpoId" class="form-control">
                                        <//c:forEach items="</%=cmpoModelManager.listAll(userSession.getUserCmpoId())%>" var="cmpoModel" varStatus="counter">
                                            <option value="$//{cmpoModel.getCmpoSeq()}" $/{userModel.getCmpoNo() == cmpoModel.getId() ? 'selected' : ''}>$/{cmpoModel.getCmpoName()}</option>
                                        <//c:forEach> 
                                    </select>

                                </div>
                            </div>
                            <!--
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Is Active</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div id="active" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input name="active" value="True" type="radio"> Yes
                                        </label>
                                        <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input name="active" value="False" type="radio"> No
                                        </label>
                                    </div>
                                </div>
                            </div>
                            -->

                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">                                    
                                    <a href="${pageContext.request.contextPath}/cmpo/role" class="btn btn-warning">Cancel</a>
                                    <button type="submit" class="btn btn-success">Create New Role</button>
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

<!-- validator  -->

<!-- Custom Theme Scripts -->

<script type="text/javascript">
    // initialize the validator function
    validator.message.date = 'not a real date';

    // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
    $('form')
            .on('blur', 'input[required], input.optional, select.required', validator.checkField)
            .on('change', 'select.required', validator.checkField)
            .on('keypress', 'input[required][pattern]', validator.keypress);

    $('.multi.required').on('keyup blur', 'input', function () {
        validator.checkField.apply($(this).siblings().last()[0]);
    });

    $('form').submit(function (e) {
        e.preventDefault();
        var submit = true;

        // evaluate the form using generic validaing
        if (!validator.checkAll($(this))) {
            submit = false;
        }

        if (submit)
            this.submit();

        return false;
    });
</script>
