<%-- 
    Document   : create
    Created on : Jun 30, 2016, 11:14:05 AM
    Author     : tanbir
--%>

<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="cbvmp.admin.data.model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="userModelManager" scope="request" class="cbvmp.admin.data.manager.UserModelManager"/>
<jsp:useBean id="userRoleMapModelManager" scope="request" class="cbvmp.admin.data.manager.UserRoleMapModelManager"/>
<jsp:useBean id="cmpoModelManager" scope="request" class="cbvmp.admin.data.manager.CmpoModelManager"/>
<jsp:useBean id="cmpoRoleModelManager" scope="request" class="cbvmp.admin.data.manager.CmpoRoleModelManager"/>
<jsp:useBean id="userCategoryModelManager" scope="request" class="cbvmp.admin.data.manager.UserCategoryModelManager"/>

<%

    UserModel userModel = (UserModel) request.getAttribute("userModel");
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

%>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>User</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Edit User</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="col-md-6 col-md-offset-3">
                        <h4 style="color: red"> ${message}</h4>
                        <c:remove var="message" scope="session" />                         
                    </div>
                    <div style="display: block;" class="x_content">
                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/cmpo/user/edit" method="POST">
                            <input type="hidden" name="userId" value="${userModel.getId()}">

                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="userName">User Name <span class="required"></span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input value="${userModel.getName()}" id="userName" class="form-control col-md-7 col-xs-12"  name="userName" placeholder="" data-parsley-required="true" type="text">
                                </div>
                            </div>

                            <div class="form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select User Type</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">

                                    <select name="userCategory" id="selectCategory" class="form-control">
                                        <c:forEach items="<%=userCategoryModelManager.listAll(userSession.getUserCategoryId())%>" var="userCategory" varStatus="counter">
                                            <option value="${userCategory.getCategoryId()}" ${userCategory.getCategoryId() == userModel.getUserCatId() ? 'selected' : ''}>${userCategory.getCategoryName()}</option>
                                        </c:forEach> 
                                    </select>

                                </div>
                            </div>


                            <div class="form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Company</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">

                                    <select name="cmpoId" id="selectOperator" class="form-control">
                                        <c:forEach items="<%=cmpoModelManager.listAll(userSession.getUserCmpoId())%>" var="cmpoModel" varStatus="counter">
                                            <option value="${cmpoModel.getCmpoSeq()}" ${userModel.getCmpoNo().getId() == cmpoModel.getCmpoSeq() ? 'selected' : ''}>${cmpoModel.getCmpoName()}</option>
                                        </c:forEach> 
                                    </select>
                                </div>
                            </div>


                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="retypePassword"></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div class="checkbox" id="isAdmin">
                                        <label>
                                            <input name="isAdmin"  ${userModel.isAdmin() ? 'checked' : ''}  type="checkbox"> System Admin (account can not be locked)
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="clearfix"></div>
                            <div class="row">
                                <div class="item form-group bad">

                                    <div class="control-label col-md-4 col-md-offset-3 col-sm-3 col-xs-12" style="text-align: left">                                 

                                        <table class="table table-bordered" >
                                            <tr>
                                                <th>User Role </th>
                                                <th>Assign </th>
                                            </tr>

                                            <c:forEach items="<%=userRoleMapModelManager.getRoleListByUserId(userModel.getId(), userSession.getUserCmpoId())%>" var="cmpoRoleModel" varStatus="counter">
                                                <tr>
                                                    <td>${cmpoRoleModel.getName()}</td>
                                                    <td>
                                                        <input name="v${cmpoRoleModel.getId()}" id="v${cmpoRoleModel.getId()}" ${cmpoRoleModel.isAllowed() ? 'checked' : ''} type="checkbox"> 
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                        </table>

                                    </div>

                                </div>
                            </div>

                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <a href="${pageContext.request.contextPath}/cmpo/user" class="btn btn-primary">Cancel</a>
                                    <button type="submit" class="btn btn-success">Submit</button>
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

<!-- validator -->

<!-- Custom Theme Scripts -->

<script type="text/javascript">
//     initialize the validator function
    //validator.message.date = 'not a real date';

    // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
//    $('form')
//            .on('blur', 'input[required], input.optional, select.required', validator.checkField)
//            .on('change', 'select.required', validator.checkField)
//            .on('keypress', 'input[required][pattern]', validator.keypress);
//
//    $('.multi.required').on('keyup blur', 'input', function () {
//        validator.checkField.apply($(this).siblings().last()[0]);
//    });
//
//    $('form').submit(function (e) {
//        e.preventDefault();
//        var submit = true;
//
//        // evaluate the form using generic validaing
//        if (!validator.checkAll($(this))) {
//            submit = false;
//        }
//
//        if (submit)
//            this.submit();
//
//        return false;
//    });
    console.log($("#selectOperator"));

    //var selectedOption = $("#selectOperator option:selected").val();

    var selectedType = $("#selectCategory option:selected").val();

    var isAdminLabel = $("#isAdmin label")[0].outerHTML;
    console.log($("#isAdmin label"));
//    $("#isAdmin label").remove();
    if ($("#selectCategory option").length > 1) {
        $("#isAdmin").show();
        $("#isAdmin input").removeAttr("disabled");
//        $("#isAdmin").append(isAdminLabel);
        $("#selectCategory")[0].selectedIndex = 1;
        $("#selectOperator")[0].selectedIndex = 0;
        //        $("#selectCategory option[value=2]").attr({"disabled": true});
        $("#selectOperator").find('option').each(function (currentValue, currentObject) {
            //            console.log($(currentObject) );
            if ($(currentObject)[0].value != 7) {
                $(currentObject).hide();
            } else {
                $(currentObject).show();
            }

        });
    } else {
        $("#isAdmin").hide();
        $("#isAdmin input").attr("disabled", "disabled");
//        $("#isAdmin label").remove();ffasfsadfsdf
        $("#selectCategory")[0].selectedIndex = 0;
        $("#selectOperator")[0].selectedIndex = 0;
        //        $("#selectCategory option[value=2]").attr({"disabled": true});
        //    $("#selectOperator").find('option').each(function(currentValue, currentObject){
        ////            console.log($(currentObject) );
        //        if($(currentObject)[0].value != 7){
        //            $(currentObject).hide();
        //        }
        //        else{
        //            $(currentObject).show();
        //        }
        //
        //    });

    }
//    console.log($("#selectCategory option[value=2]"));
    $("#selectCategory").on("change", function () {
        var selectedType = $("#selectCategory option:selected").val();
        if (selectedType == 1) {
            $("#isAdmin").show();
            $("#isAdmin input").removeAttr("disabled");
//            $("#isAdmin").append(isAdminLabel);
            $("#selectOperator")[0].selectedIndex = 0;
            //        $("#selectCategory option[value=2]").attr({"disabled": true});
            $("#selectOperator").find('option').each(function (currentValue, currentObject) {
                console.log($(currentObject));
                if ($(currentObject)[0].value != 7) {
                    $(currentObject).hide();
                } else {
                    $(currentObject).show();
                }

            });
        } else {
            $("#isAdmin").hide();
            $("#isAdmin input").attr("disabled", "disabled");
//            $("#isAdmin label").remove();
            $("#selectOperator")[0].selectedIndex = 6;
            //        $("#selectCategory option[value=1]").attr({"disabled": true});
            $("#selectOperator").find('option').each(function (currentValue, currentObject) {
                console.log($(currentObject));
                if ($(currentObject)[0].value == 7) {
                    $(currentObject).hide();
                } else {
                    $(currentObject).show();
                }

            });
        }

        //    $("#selectOperator").on("change", function () {
//        var selectedOption = $("#selectOperator option:selected").val();
//        console.log(selectedOption);
//        console.log("selectedOption");
//        if (selectedOption == 7) {
//            $("#isAdmin").show();
//            $("#selectCategory option[value=2]").attr({"disabled": true});
//            $("#selectCategory option[value=1]").attr({"disabled": false, "selected": "selected"});
//            $("#selectCategory option[value=2]").removeAttr("selected");
//        } else {
//            $("#isAdmin").hide();
//            $("#selectCategory option[value=1]").attr({"disabled": true});
//            $("#selectCategory option[value=2]").attr({"disabled": false, "selected": "selected"});
//            $("#selectCategory option[value=1]").removeAttr("selected");
//
//        }
//    });
    });
</script>
