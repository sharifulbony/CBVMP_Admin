<%-- 
    Document   : edit
    Created on : Jun 29, 2016, 4:15:50 PM
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.PolicyTypesModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="policyTypesModelManager" scope="request" class="cbvmp.admin.data.manager.PolicyTypesModelManager"/>





<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Policy Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">

            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Create Policy </small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <form data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/policy/create" method="post">


                            <div class="form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Policy</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">

                                    <select name="policyTypeId" class="form-control">
                                        <c:forEach items="<%=policyTypesModelManager.listAll()%>" var="policyTypesModel" varStatus="counter">
                                            <option value="${policyTypesModel.policyTypeNO }">${policyTypesModel.policyType }</option>
                                        </c:forEach>
                                    </select>

                                </div>
                            </div>
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Policy Value <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input data-parsley-type="number" class="form-control col-md-7 col-xs-12"  name="policyValue" placeholder="Enter Policy value" required="required" type="text"  >
                                </div>
                                <!--
                                <div class="alert">please put something here</div>
                                -->

                            </div>
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12 daterangepicker" for="name">Start Date <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12   xdisplay_inputx  has-feedback">
                                    <input  id="single_cal3"  aria-describedby="inputSuccess2Status3"class="form-control has-feedback-left col-md-7 col-xs-12 " data-validate-length-range="6" data-validate-words="2" name="startDate"  required="required" type="text"    >
                                    <span  class="fa fa-calendar form-control-feedback left" ></span>
                                </div>


                                <!--
                                <div class="alert">please put something here</div>
                                -->

                            </div>



                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">End Date <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12   xdisplay_inputx  has-feedback">
                                    <input  id="single_cal4"  aria-describedby="inputSuccess2Status3" class="form-control has-feedback-left col-md-7 col-xs-12 " data-validate-length-range="6" data-validate-words="2" name="endDate"  required="required" type="text" >
                                    <span  class="fa fa-calendar form-control-feedback left" ></span>
                                </div>


                                <!--
                                <div class="alert">please put something here</div>
                                -->

                            </div>



                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <div class="title_left">
                                        <h4 style="color:red">${message}</h4>
                                        <c:remove var="message" scope="session" />
                                    </div>
                                    <a href="${pageContext.request.contextPath}/policy/view" class="btn btn-primary">Cancel</a>

                                    <button type="submit" class="btn btn-success">Save</button>
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



<script src="${pageContext.request.contextPath}/build/vendor/datepicker/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/build/vendor/datepicker/daterangepicker.js"></script>
<script>
    $(document).ready(function () {



        $('#single_cal3').daterangepicker({
            singleDatePicker: true,
            timePicker: true,
            timePicker24Hour: true,
            showDropdowns: true,
            calender_style: "picker_3"
//          "startDate": "07/05/2016 10:10",
//          "endDate": "07/11/2016"



        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
            //start.format('DD/MM/YYYY HH:mm');
        });

        $('#single_cal4').daterangepicker({
            singleDatePicker: true,
            timePicker: true,
            timePicker24Hour: true,
            showDropdowns: true,
            calender_style: "picker_3"
        }, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    });
</script>
