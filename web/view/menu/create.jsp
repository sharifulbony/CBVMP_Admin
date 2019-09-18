<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="menuModelManager" scope="request" class="cbvmp.admin.data.manager.MenuModelManager"/>
<jsp:useBean id="userCategoryModelManager" scope="request" class="cbvmp.admin.data.manager.UserCategoryModelManager"/>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Application Menu</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Create new Menu</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <form  data-parsley-validate class="form-horizontal form-label-left" novalidate="" action="${pageContext.request.contextPath}/menu/create" method="POST">
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menuName">Menu Name<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input data-parsley-required="true" id="menuName" class="form-control col-md-7 col-xs-12" name="menuName" placeholder=""  type="text">
                                </div>
                            </div>
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menuUrl">URL<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input data-parsley-required="true" id="menuUrl" class="form-control col-md-7 col-xs-12" name="menuUrl" placeholder="/controller/action" type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Parent</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    
                                    <select class="form-control" name="parentId">
                                        <option value="">Choose Parent</option>
                                        <c:forEach items="<%=menuModelManager.listAllParent()%>" var="userModel" varStatus="counter">
                                            <option value="${userModel.getId()}">${userModel.getAlias()}</option>
                                        </c:forEach>
                                        
                                        
                                        
                                    </select>
                                </div>
                            </div>
                            <div class="form-group bad">
                                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Select User Category</label>
                                    <div class="col-md-6 col-sm-6 col-xs-12">

                                        <select name="userCategory" class="form-control">
                                            <c:forEach items="<%=userCategoryModelManager.listAllForMenu()%>" var="userCategory" varStatus="counter">
                                                <option value="${userCategory.getCategoryId()}">${userCategory.getCategoryName()}</option>
                                            </c:forEach> 
                                        </select>

                                    </div>
                                </div>


                            <div class="ln_solid"></div>
                            <div class="form-group">
                                <div class="col-md-6 col-md-offset-3">
                                    <a href="${pageContext.request.contextPath}/menu/view" class="btn btn-primary">Cancel</a>
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
