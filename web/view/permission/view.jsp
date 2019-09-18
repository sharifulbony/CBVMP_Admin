<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="java.util.List"%>
<%@page import="cbvmp.admin.data.model.CmpoRoleModel"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="aclModelManager" scope="request" class="cbvmp.admin.data.manager.AccessControlListManager"/>

<%
    Integer id = (Integer) request.getAttribute("id");
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");
%>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>User Role Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-9 col-sm-12 col-xs-12 col-md-offset-1">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Edit User Privilege</small></h2>
                        <div class="pull-right">

                            <a href="#" id="security" class="btn btn-default"><i class="fa fa-wrench"></i> Update User Privilege</a>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <div id="roleId" value="${id}"></div>
                        <div id="userCategory" value="${userSession.getUserCategoryId()}"></div>
                        <div id="tree">

                        </div>


                    </div>

                </div>

            </div>
        </div>
    </div>
</div>




<!-- /page content -->

<div class="modal fade" id="request-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <i class="fa fa-spinner fa-spin"></i>&nbsp;Please wait....
            </div>

        </div>
    </div>
</div>


<jsp:include page="../layout/footer.jsp" /> <br/>
<script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery-ui.custom.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery.cookie.js" type="text/javascript"></script>

<link href="${pageContext.request.contextPath}/build/vendor/dynatree/skin/ui.dynatree.css" rel="stylesheet" type="text/css" id="skinSheet">
<script src="${pageContext.request.contextPath}/build/vendor/dynatree/jquery.dynatree.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        $("#security").click(function () {
            var accessControlList = [];
            var roleId = $("#roleId").attr("value");
//            var tree = $("#tree").dynatree("getTree");
            var tree = $("#tree").dynatree("getTree").toDict();
            console.log(tree);
            for (var i = 0; i < tree.length; i++) {
                var accessControlObject = {
                    "id": tree[i].key,
                    "is_selected": tree[i].select,
                    "role_id": roleId
                };
                accessControlList.push(accessControlObject);
                var currentParent = accessControlList.length - 1;
                var isChildSelected = true;

                if ((typeof tree[i].children !== 'undefined') && (tree[i].children.length > 0)) {
                    isChildSelected = false;
                    for (var j = 0; j < tree[i].children.length; j++) {
                        var child = {
                            "id": tree[i].children[j].key,
                            "is_selected": tree[i].children[j].select,
                            "role_id": roleId
                        };
                        accessControlList.push(child);
                        
                        if(child.is_selected === true)
                        {
                            isChildSelected =true;
                        }
                    }
                }
                
                var nowParent = accessControlList[currentParent];
                if((typeof tree[i].children !== 'undefined') && (tree[i].children.length > 0)){
                    nowParent.is_selected = isChildSelected;
                }
                console.log("Single Parent:"+nowParent.is_selected);
                accessControlList[currentParent] = nowParent;

            }
            console.log(JSON.stringify(accessControlList));



            $.ajax({

                url: '${pageContext.request.contextPath}/rest/access/save',
                type: 'POST',
                contentType: "application/json",
                data: JSON.stringify(accessControlList),
                beforeSend: function (data) {
                    $("#request-modal").modal({
                        show: true,
                        backdrop: "static",
                        keyboard: false

                    });
                    console.log("Saving Data...");

                },
                success: function (data) {
                    $('#request-modal').modal('hide');
                    console.log("After save " + data);

                }

            });

//            console.log(JSON.stringify(tree.serializeArray()));
//            formData = formData.concat(tree.serializeArray());
//            console.log("POSTing this:\n" + jQuery.param(formData));


        });

        $("#tree").dynatree({
            checkbox: true,
            selectMode: 3,
            initAjax: {

                url: "${pageContext.request.contextPath}/rest/access/view/" + $("#roleId").attr("value")+"/"+$("#userCategory").attr("value")

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