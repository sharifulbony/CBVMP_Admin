<%@page import="org.json.JSONObject"%>
<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="java.util.List"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="detailList" scope="request" class="cbvmp.admin.data.manager.BulkEntryDetailsModelManager"/>
<%
    Integer requestId = (Integer) request.getAttribute("requestId");

    //JSONObject jsonObj = (JSONObject) request.getAttribute("jsonObj");
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
                        <h2> <small>Valid Request Detail</small></h2>

                        <div class="clearfix"></div>

                    </div>
                    <div class="pull-right">
                        <label for="filter">Search</label>
                        <input type="search" placeholder="In this page..." name="filter" value="" id="filter" />
                    </div>

                    <div style="display: block;" class="x_content">
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">

                                <div class="x_panel">                                    
                                    <div style="display: block;" class="x_content">

                                        <table class="table table-striped" id="bulk-request-detail">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>MSISDN</th>
                                                    <th>Status</th>
                                                    <th>Ref. MSISDN</th>
                                                    <th>Is Executed</th>
                                                    <th>Execution Time</th>
                                                </tr>
                                            </thead>

                                            <tbody>


                                            </tbody>
                                        </table>

                                    </div>
                                </div>

                            </div>
                        </div>


                    </div>
                </div>

            </div>
        </div>
    </div>
</div>




<!-- /page content -->

<jsp:include page="../layout/footer.jsp" /> <br/>
<script src="${pageContext.request.contextPath}/build/vendor/DataTables/datatables.min.js"></script>
<script type="text/javascript">

    function filter(selector, query) {
        query = $.trim(query); //trim white space
        query = query.replace(/ /gi, '|'); //add OR for regex query

        $(selector).each(function () {
            ($(this).text().search(new RegExp(query, "i")) < 0) ? $(this).hide().removeClass('visible') : $(this).show().addClass('visible');
        });
    }


    $(document).ready(function () {

//        var url = '${pageContext.request.contextPath}/bulk/dereg/detail/${requestId}';
    //console.log(${jsonObj});
    var table = $("#bulk-request-detail").DataTable({
    "lengthMenu": [[30, 100, 300], [30, 100, 300]],
//            "sPaginationType": "full_numbers",
//            "iDisplayLength": 100,
            "bProcessing": true,
            "bServerSide": true,
            "bSearching": false,
//            "sFilter" : true,
//            "sFilterInput": 'aaData',
            "bFilter": false,
//            "bJQueryUI": true,
            "sAjaxSource": "${pageContext.request.contextPath}/bulk/dereg/show/${requestId}"

//           
                });
//                    $("#bulk-request-detail tfoot th").each(function (i) {
//                        var select = $('<select><option value=""></option></select>')
//                                .appendTo($(this).empty())
//                                .on('change', function () {
//                                    table.column(i)
//                                            .search($(this).val())
//                                            .draw();
//                                });
//
//                        table.column(i).data().unique().sort().each(function (d, j) {
//                            select.append('<option value="' + d + '">' + d + '</option>')
//                        });
//                    });

//                       var indexOfMyCol = 2; //you want it on the third column
//                $("#bulk-request-detail thead th").each(function (i) {
//                if (i === indexOfMyCol){
//                this.innerHTML = fnCreateSelect(oTable.fnGetColumnData(i));
//                $('select', this).change(function () {
//                oTable.fnFilter($(this).val(), i);
//                });
//                }
//                });
//                 var oData = table._fnFeatureHtmlFilter ( this);
//                 console.log(oData);
                            
                                $('tbody tr').addClass('visible');
                                $('#filter').keyup(function (event) {
                        //if esc is pressed or nothing is entered
                        if (event.keyCode == 27 || $(this).val() == '') {
                        //if esc is pressed we want to clear the value of search box
                        $(this).val('');
                                //we want each row to be visible because if nothing
                                //is entered then all rows are matched.
                                $('tbody tr').removeClass('visible').show().addClass('visible');
                        }

                        //if there is text, lets filter
                        else {
                        filter('tbody tr', $(this).val());
                        }
                        });
                        });

</script>




