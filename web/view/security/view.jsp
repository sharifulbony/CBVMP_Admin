<%-- 
    Document   : view
    Created on : Jun 26, 2016, 3:31:08 PM
    Author     : tanbir
--%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>CMPO User Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Active User List</small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">
                        <table class="table table-condensed">
                            <tr>
                                <th>Name</th>
                                <th>Designation</th>
                                <th>Email</th>
                            </tr>
                            <tr>
                                <td>Test User</td>
                                <td>Test deg</td>
                                <td>Test email</td>
                                <td>
                                    <a class="btn btn-primary btn-xs">View</a>
                                    <a class="btn btn-default btn-xs" >Edit</a>
                                    <a class="btn btn-danger btn-xs">Delete</a>
                                </td>
                            </tr>
                            
                            
                        </table>
                        

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- /page content -->
<jsp:include page="../layout/footer.jsp" /> <br/>
