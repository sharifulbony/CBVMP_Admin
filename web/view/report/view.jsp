<%@page import="cbvmp.admin.data.model.UserSessionManager"%>
<%@page import="java.util.List"%>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/report_sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="totalSimCount" scope="request" class="cbvmp.admin.data.manager.TotalSimCountModelManager"/>
<jsp:useBean id="totalSimCountAgainstDoc" scope="request" class="cbvmp.admin.data.manager.TotalSimAgainstDocModelManager"/>
<jsp:useBean id="totalLoginInfo" scope="request" class="cbvmp.admin.data.manager.TotalLoginInfoModelManager"/>
<jsp:useBean id="totalBulkDereg" scope="request" class="cbvmp.admin.data.manager.TotalBulkDeregModelManager"/>
<jsp:useBean id="otpInfo" scope="request" class="cbvmp.admin.data.manager.DashOtpInfoModelManager"/>
<jsp:useBean id="ecVerify" scope="request" class="cbvmp.admin.data.manager.DashECVerificationModelManager"/>
<%
    UserSessionManager userSession = (UserSessionManager) request.getSession().getAttribute("userSession");

%>

<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>Report Module</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->
        <div class="x_panel">
            <div style="text-align: center" class="x_title"><h3>TOTAL SIM REGISTERED</h3></div>
            <div class="row">
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <!-- <td><th>Policy No</th></td>  -->

                                <th>CMPO </th>
                                <th>SIM COUNT</th>

                            </tr>

                        </thead>

                        <c:forEach items="<%=totalSimCount.geTotalSim(userSession.getUserCmpoId())%>" var="simCount" varStatus="counter">
                            <tr>
                                <td>${simCount.getCmpo() }</td>
                                <td>${simCount.getSimCount()}</td>
                            </tr>
                        </c:forEach>


                    </table>

                </div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <canvas height="182" width="365" style="width: 365px; height: 182px;" id="pieChart"></canvas>

                </div>

            </div>

            <div class="ln_solid"></div>
            <div style="text-align: center" class="x_title"><h3>TOTAL SIM COUNT AGAINST DOCUMENT  TYPE</h3></div>
            <div class="row">
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <!-- <td><th>Policy No</th></td>  -->

                                <th>DOCUMENT </th>
                                <th>SIM COUNT</th>

                            </tr>

                        </thead>

                        <c:forEach items="<%=totalSimCountAgainstDoc.geTotalSim(userSession.getUserCmpoId())%>" var="simCount" varStatus="counter">
                            <tr>
                                <td>${simCount.getDocumentType() }</td>
                                <td>${simCount.getSimCount()}</td>


                            </tr>
                        </c:forEach>


                    </table>

                </div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <canvas height="182" width="365" style="width: 365px; height: 182px;" id="pieChart2"></canvas>

                </div>

            </div>
            <div class="ln_solid"></div>
            <div style="text-align: center" class="x_title"><h3>LOGIN INFO</h3></div>
            <div class="row">
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <!-- <td><th>Policy No</th></td>  -->

                                <th>CMPO </th>
                                <th>TOTAL LOGIN</th>

                            </tr>

                        </thead>

                        <c:forEach items="<%=totalLoginInfo.geTotalLoginInfo(userSession.getUserCmpoId())%>" var="simCount" varStatus="counter">
                            <tr>
                                <td>${simCount.getCategory() }</td>
                                <td>${simCount.getTotalCount()}</td>


                            </tr>
                        </c:forEach>


                    </table>

                </div>

                <div class="col-md-6 col-sm-12 col-xs-12">
                    <canvas height="182" width="365" style="width: 365px; height: 182px;" id="pieChart3"></canvas>

                </div>
            </div>
            <div class="ln_solid"></div>
            <div class="row">
                <div style="text-align: center" class="x_title"><h3>BULK DEREGISTRATION</h3></div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <!-- <td><th>Policy No</th></td>  -->

                                <th>CMPO </th>
                                <th>TOTAL VALID MSISDN</th>
                                <th>TOTAL EXECUTED</th>

                            </tr>

                        </thead>

                        <c:forEach items="<%=totalBulkDereg.geTotalBulkDereg(userSession.getUserCmpoId())%>" var="bulkCount" varStatus="counter">
                            <tr>
                                <td>${bulkCount.getCmpo() }</td>
                                <td>${bulkCount.getTotalValidMsisdn() }</td>
                                <td>${bulkCount.getTotalCountExcuted()}</td>


                            </tr>
                        </c:forEach>


                    </table>

                </div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <div id="progress-bar-bootstrap">


                    </div>

                    <!--<canvas height="182" width="365" style="width: 365px; height: 182px;" id="barchart1"></canvas>-->

                </div>

            </div>
            <div class="row">
                <div id="progress-bar-bootstrap">


                </div>

            </div>


            <div class="ln_solid"></div>
            <div class="row">
                <div style="text-align: center" class="x_title"><h3>OTP INFO</h3></div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <!-- <td><th>Policy No</th></td>  -->

                                <th>CMPO </th>
                                <th>CONSENT</th>
                                <th>UNCONSENT</th>

                            </tr>

                        </thead>

                        <c:forEach items="<%=otpInfo.getOtpInfo(userSession.getUserCmpoId())%>" var="otpCount" varStatus="counter">
                            <tr>
                                <td>${otpCount.getCmpo()}</td>
                                <td>${otpCount.getConsentQty()}</td>
                                <td>${otpCount.getUnconsentQty()}</td>


                            </tr>
                        </c:forEach>


                    </table>

                </div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <canvas id="bar" width="500" height="400"></canvas>              
                    <!-- <canvas height="182" width="365" style="width: 365px; height: 182px;" id="bar-chart"></canvas>-->

                </div>
            </div>

            <div class="ln_solid"></div>
            <div class="row">
                <div style="text-align: center" class="x_title"><h3>EC VERIFICATION INFO</h3></div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <!-- <td><th>Policy No</th></td>  -->

                                <th>CMPO</th>
                                <th>TOTAL NID</th>
                                <th>THUMB NOT FOUND</th>
                                <th>NID VALID</th>
                                <th>NID INVALID</th>

                            </tr>

                        </thead>

                        <c:forEach items="<%=ecVerify.getEcVerified(userSession.getUserCmpoId())%>" var="ecCount" varStatus="counter">
                            <tr>
                                <td>${ecCount.getCmpo()}</td>
                                <td>${ecCount.getTotalNid()}</td>
                                <td>${ecCount.getThumbNotFound()}</td>
                                <td>${ecCount.getNidOk()}</td>
                                <td>${ecCount.getNidNotOK()}</td>
                            </tr>
                        </c:forEach>


                    </table>

                </div>
                <div class="col-md-6 col-sm-12 col-xs-12">
                    <canvas id="bar-ec" width="500" height="500"></canvas>              
                    <!-- <canvas height="182" width="365" style="width: 365px; height: 182px;" id="bar-chart"></canvas>-->

                </div>
            </div>
        </div>

    </div>

</div>
</div>




<!-- /page content -->



<jsp:include page="../layout/footer.jsp" /> <br/>
<script src="${pageContext.request.contextPath}/build/vendor/Chart.js/dist/Chart.min.js"></script>
<script type="text/javascript">
    $.ajax({
        url: '${pageContext.request.contextPath}/rest/dashboard/total/sim/reg?cmpo=${userSession.getUserCmpoId()}',
                type: 'GET',
                beforeSend: function (data) {
                    console.log("Saving Data...");
                },
                success: function (response) {

                    console.log();
                    var chartData = response.sim_count;
                    var labelData = response.cmpo;
                    var colorData = response.color_code;
                    // Pie chart
                    var ctx = $("#pieChart");
                    var data = {
                        datasets: [{
                                data: chartData,
                                backgroundColor: colorData,
                                label: 'My dataset' // for legend
                            }],
                        labels: labelData
                    };
                    var pieChart = new Chart(ctx, {
                        data: data,
                        type: 'pie',
                        otpions: {
                            legend: false
                        }
                    });
                    $('#request-modal').modal('hide');
                }

            });
            $.ajax({
                url: '${pageContext.request.contextPath}/rest/dashboard/total/sim/doc?cmpo=${userSession.getUserCmpoId()}',
                        type: 'GET',
                        beforeSend: function (data) {

                            console.log("Saving Data...");
                        },
                        success: function (response) {

                            console.log();
                            var chartData = response.sim_count;
                            var labelData = response.doc_type;
                            var colorData = response.color_code;
                            // Pie chart
                            var ctx = $("#pieChart2");
                            var data = {
                                datasets: [{
                                        data: chartData,
                                        backgroundColor: colorData,
                                        label: 'My dataset' // for legend
                                    }],
                                labels: labelData
                            };
                            var pieChart = new Chart(ctx, {
                                data: data,
                                type: 'pie',
                                otpions: {
                                    legend: false
                                }
                            });
                        }

                    });
                    $.ajax({
                        url: '${pageContext.request.contextPath}/rest/dashboard/total/login?cmpo=${userSession.getUserCmpoId()}',
                                type: 'GET',
                                beforeSend: function (data) {

                                    console.log("Saving Data...");
                                },
                                success: function (response) {

                                    console.log();
                                    var chartData = response.total_count;
                                    var labelData = response.category;
                                    var colorData = response.color_code;
                                    // Pie chart
                                    var ctx = $("#pieChart3");
                                    var data = {
                                        datasets: [{
                                                data: chartData,
                                                backgroundColor: colorData,
                                                label: 'My dataset' // for legend
                                            }],
                                        labels: labelData
                                    };
                                    var pieChart = new Chart(ctx, {
                                        data: data,
                                        type: 'pie',
                                        otpions: {
                                            legend: false
                                        }
                                    });
                                }

                            });
                            $.ajax({
                                url: '${pageContext.request.contextPath}/rest/dashboard/total/bulkdereg?cmpo=${userSession.getUserCmpoId()}',
                                        type: 'GET',
                                        beforeSend: function (data) {
                                            console.log("Saving Data...");
                                        },
                                        success: function (response) {

                                            console.log();
                                            var chartTotalValidMsisdn = response.total_valid_msisdn;
                                            var labelData = response.cmpo;
                                            var chartTotalExecuted = response.total_executed;

                                            // Pie chart
                                            var ctx = $("#progress-bar-bootstrap");
                                            for (var i = 0; i < labelData.length; i++) {
                                                var totalValid = response.total_valid_msisdn[i];
                                                var totalExecuted = response.total_executed[i];
                                                var colorCode = response.color_code[i];
                                                var percentage = 0;
                                                if (totalValid !== 0) {
                                                    percentage = Math.ceil(totalExecuted / totalValid * 100);
                                                }

//                                var percentage = Math.ceil((56/1136)*100);


                                                console.log("totalValid:" + totalValid);
                                                console.log("totalExecuted:" + totalExecuted);
                                                console.log("percentage:" + percentage);

                                                var html = labelData[i] + "<div class='progress' style='background:#F0AD4E'>\n\
                                            <div class ='progress-bar' role ='progressbar progress-bar-striped' aria-valuenow='40' aria-valuemin='0' aria-valuemax = '100' style='width: " + percentage + "%; background:" + colorCode + ";font-style:bold;'>\n\
                                                " + percentage + "%\n\
                                            </div>\n\
                                        </div>";
                                                ctx.append(html);


                                            }





//                                            var mybarChart = new Chart(ctx, {
//                                                type: 'horizontalBar',
//                                                data: data,
//                                                options: {
//                                                    scales: {
//                                                        
//                                                        yAxes: [{
//                                                                ticks: {
//                                                                    beginAtZero: true
//                                                                }
//
//                                                            }]
//                                                    }
//                                                }
//                                            });
                                            $('#request-modal').modal('hide');
                                        }

                                    });

                                    $.ajax({
                                        url: '${pageContext.request.contextPath}/rest/dashboard/otp/info?cmpo=${userSession.getUserCmpoId()}',
                                                type: 'GET',
                                                beforeSend: function (data) {
                                                    console.log("Saving Data...");
                                                },
                                                success: function (response) {

                                                    console.log();
                                                    var consent = response.consent_qty;
                                                    var cmpo = response.cmpo;
                                                    var unconsent = response.unconsent_qty;
                                                    var color = response.color_code;

                                                    console.log("consent " + consent);

                                                    //var ctx = $("#bar-chart");

                                                    var ctx = document.getElementById("bar").getContext("2d");

                                                    var data = {
                                                        labels: cmpo,
                                                        datasets: [
                                                            {
                                                                label: "Consent",
                                                                backgroundColor: '#455C73',
                                                                borderWidth: 1,
                                                                data: consent
                                                            },
                                                            {
                                                                label: "Unconsent",
                                                                backgroundColor: "orange",
                                                                borderWidth: 1,
                                                                data: unconsent
                                                            }

                                                        ]
                                                    };

//                                                    var myBarChart = new Chart(ctx).Bar(data, {barValueSpacing: 20
//                                                    });
//                                                    
                                                    var myBarChart = new Chart(ctx, {
                                                        type: 'bar',
                                                        data: data,
                                                        options: {
                                                            scales: {
                                                                yAxes: [{
                                                                        ticks: {
                                                                            min: 0,
                                                                        }
                                                                    }]


                                                            }
                                                        }
                                                    });

                                                    $('#request-modal').modal('hide');
                                                }

                                            });

                                            $.ajax({
                                                url: '${pageContext.request.contextPath}/rest/dashboard/ec/verify?cmpo=${userSession.getUserCmpoId()}',
                                                        type: 'GET',
                                                        beforeSend: function (data) {
                                                            console.log("Saving Data...");
                                                        },
                                                        success: function (response) {

                                                            console.log();

                                                            var cmpo = response.cmpo;
                                                            var totalNid = response.total_nid;
                                                            var thumb = response.thumb_not_found;
                                                            var nidOk = response.nid_ok;
                                                            var nidNotOk = response.nid_not_ok
                                                            var color = response.color_code;

                                                            //console.log("consent " + consent);

                                                            //var ctx = $("#bar-chart");

                                                            var ctx = document.getElementById("bar-ec").getContext("2d");

                                                            var data = {
                                                                labels: cmpo,
                                                                datasets: [
                                                                    {
                                                                        label: "Total NID",
                                                                        backgroundColor: '#455C73',
                                                                        borderWidth: 1,
                                                                        data: totalNid
                                                                    },
                                                                    {
                                                                        label: "Thumb Not Found",
                                                                        backgroundColor: '#9B59B6',
                                                                        borderWidth: 1,
                                                                        data: thumb
                                                                    },
                                                                    {
                                                                        label: "NID Valid",
                                                                        backgroundColor: "orange",
                                                                        borderWidth: 1,
                                                                        data: nidOk
                                                                    },
                                                                    {
                                                                        label: "NID Invalid",
                                                                        backgroundColor: '#3498DB',
                                                                        borderWidth: 1,
                                                                        data: nidNotOk
                                                                    }


                                                                ]
                                                            };

//                                                    var myBarChart = new Chart(ctx).Bar(data, {barValueSpacing: 20
//                                                    });
//                                                    
                                                            var myBarChart = new Chart(ctx, {
                                                                type: 'bar',
                                                                data: data,
                                                                options: {
                                                                    scales: {
                                                                        yAxes: [{
                                                                                ticks: {
                                                                                    min: 0,
                                                                                }
                                                                            }]


                                                                    }
                                                                }
                                                            });

                                                            $('#request-modal').modal('hide');
                                                        }

                                                    });


</script>