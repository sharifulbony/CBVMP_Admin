<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="java.util.List"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cmpoModelManager" scope="request" class="cbvmp.admin.data.manager.CmpoModelManager"/>

<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

%>
<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Bulk Deregistration Module</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Create new bulk deregistration request</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <form  data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/bulk/dereg/create" method="POST" enctype="multipart/form-data">
                            <div class="item form-group ">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menuName">Upload MSISDN List<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input data-parsley-required="true"  data-parsley-max-file-size="2048" data-parsley-allowed-Extension="csv" id="msisdnList" class=" col-md-7 col-xs-12" name="msisdnList"   type="file">
                                </div>
                            </div>
                            
                            <div class="form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Select Operator</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">

                                    <select name="cmpoId" class="form-control">
                                        
                                        <c:forEach items="<%=cmpoModelManager.listAll(userSession.getUserCmpoId())%>" var="cmpoModel" varStatus="counter">
                                            <c:if test="${cmpoModel.getId()!=7}">
                                            <option value="${cmpoModel.id}">${cmpoModel.cmpoName}</option>
                                            </c:if>
                                        </c:forEach> 
                                    </select>

                                </div>
                            </div>

                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <h4 style="color: red"> ${message}</h4>
                                    <c:remove var="message" scope="session" /> 
                                    <a href="${pageContext.request.contextPath}/bulk/dereg/view" class="btn btn-primary">Cancel</a>
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

<script type="text/javascript">
    window.Parsley.addValidator('maxFileSize', {
        validateString: function (_value, maxSize, parsleyInstance) {
            if (!window.FormData) {
                alert('You are making all developpers in the world cringe. Upgrade your browser!');
                return true;
            }
            var files = parsleyInstance.$element[0].files;
            return files.length != 1 || files[0].size <= maxSize * 1024;
        },
        requirementType: 'integer',
        messages: {
//            en: 'Maximum File size %s Kb',
            en: 'Maximum File size 2 Mb',
            
        }
    });
    
    window.Parsley.addValidator('allowedExtension', {
        validateString: function (_value, extension, parsleyInstance) {
            if (!window.FormData) {
                alert('You are making all developpers in the world cringe. Upgrade your browser!');
                return true;
            }
            var files = parsleyInstance.$element[0].files;
            var fileExtension = files[0].name.split('.').pop();
            console.log(fileExtension);
            return fileExtension === extension; 
        },
        requirementType: 'string',
        messages: {
            en: 'File Extension should be %s'
            
        }
    });


</script>

