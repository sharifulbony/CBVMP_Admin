<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<!-- page content -->
<div class="right_col" role="main" >


    <div class="row">

        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel">

                <div class="x_title">
                    <h2>Entered successfully&nbsp;</h2>
                    <!--     <span><a class="btn btn-primary" id="show_modal">Modal</a></span> -->

                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    Hello <b>${sessionScope.userSession.user} </b>, Logged in at: ${sessionScope.userSession.loginTime}<br/>
                </div>
                <div class="x_content">
                    <img style="width: 100%" src="back1.jpg">
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<jsp:include page="../layout/footer.jsp" /> <br/> 
<script type="text/javascript">

//         window.onbeforeunload = function () {
//                                    return "Do you really want to leave application?";
//                                    //if we return nothing here (just calling return;) then there will be no pop-up question at all
//                                    //return;
//                                };
</script>