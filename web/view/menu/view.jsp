<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="aclModelManager" scope="request" class="cbvmp.admin.data.manager.AccessControlListManager"/>


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
                        <h2> <small>Menu Tree</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        
                        <div id="tree">

                        </div>
                        <a href="${pageContext.request.contextPath}/menu/create" class="btn btn-sm btn-success">Create</a>


                    </div>
                </div>

            </div>
        </div>
    </div>
</div>




<!-- /page content -->

<jsp:include page="../layout/footer.jsp" /> <br/>
<script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery-ui.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery.cookie.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/build/vendor/dynatree/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
<script src="${pageContext.request.contextPath}/build/vendor/dynatree/jquery.dynatree.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $("#tree").dynatree({
            selectMode: 3,
            initAjax: {
                url: "${pageContext.request.contextPath}/rest/access/view/1/1" 
            },
            onPostInit: function (isReloading, isError) {
                //logMsg("onPostInit(%o, %o)", isReloading, isError);
                // Re-fire onActivate, so the text is update
                this.reactivate();
            },
            onActivate: function (node) {
                //$("#echoActive").text(node.data.title);
            },
            onDeactivate: function (node) {
                //$("#echoActive").text("-");
            },
            onDblClick: function (node, event) {
                //logMsg("onDblClick(%o, %o)", node, event);
                node.toggleExpand();
            },
            onSelect: function (node, event) {
                console.log(node);

            }
        });
    });



</script>