<%-- 
    Document   : footer
    Created on : Jun 27, 2016, 12:27:22 PM
    Author     : tanbir
--%>


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Please Login to continue</h4>

                <p id="demo"></p>
            </div>
            <div class="modal-body">
                <section class="login_content">
                    <form role="form"class="login-form">
                        <h1>Login Form</h1>
                        <div>
                            <input type="text" class="form-control" autocomplete="on" name="modalusername" placeholder="${sessionScope.userSession.user}" disabled readonly/>
                        </div>
                        <div>
                            <input type="password" class="form-control" id="modalpass" name="modalpassword" placeholder="Password" required="required" />
                            <p style="color: red "id="wrongpass"></p> 
                        </div>

                        <div class="clearfix"> </div>
                        <div class="modal-footer">
                            <!-- <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> -->
                            <a class="btn btn-default" onclick="myLogin();">Log In</a>
                        </div>


                    </form>
                </section>
            </div>

        </div>
    </div>
</div>
<!-- jQuery -->
<!--<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>-->
<script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/build/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath}/build/vendor/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="${pageContext.request.contextPath}/build/vendor/nprogress/nprogress.js"></script>
<!-- sites custome js -->
<script src="${pageContext.request.contextPath}/build/js/custom.min.js"></script>
<script type="text/javascript">

                                var inactivetime = 0;
                                var idleTime = 0;

                                $(document).ready(function () {
//                                    wireUpEvents();
                                    checkLock();
//                                    $('a').on('mousedown', stopNavigate);
//
//                                    $('a').on('mouseleave', function () {
//                                        $(window).on('beforeunload', function () {
//                                            return 'Are you sure you want to leave?';
//                                        });
//                                    });

//                                    console.log("start");
                                });
                               
//                                function stopNavigate() {
//                                    $(window).off('beforeunload');
//                                }
//
//                                $(window).on('beforeunload', function () {
//                                    return 'Are you sure you want to leave?';
//                                });
//
//                                $(window).on('unload', function () {
//
//                                    logout();
//
//                                }







                                window.onblur = function () {
//                                    console.log("blur");


                                    $.ajax({
                                        url: '${pageContext.request.contextPath}/rest/setlock/get',
                                        type: 'POST',
                                        success: function (data) {
                                            if (data == 0)
                                            {
                                                window.blurred = true;
                                                var lastActivetime = new Date().getTime();
                                                $.ajax({
                                                    url: '${pageContext.request.contextPath}/rest/time/get',
                                                    type: 'GET',
                                                    data: {lasttime: lastActivetime},
                                                    success: function (data) {
                                                    }
                                                });
                                            }
                                        }
                                    });

                                };

                                window.onfocus = function () {
//                                    console.log("focus");
                                    //checkLock();
                                    window.blurred = false;
                                    var focusTime = new Date().getTime();
                                    setTimeout(onFocusFunc(), 2000)
                                    function onFocusFunc()
                                    {
                                        $.ajax({
                                            url: '${pageContext.request.contextPath}/rest/time/post',
                                            type: 'POST',
                                            success: function (data) {
                                                if (data == 0)
                                                {
                                                    return;
                                                } else
                                                {
                                                    inactivetime = (focusTime - data) / 1000;
//                                               console.log("inactivetime="+inactivetime);
                                                    if (inactivetime >${sessionScope.userSession.maxSessionTimeout})
                                                    {
                                                        logout();
                                                    } else if (inactivetime >${sessionScope.userSession.maxInactiveTime})
                                                    {
                                                        openModal();
                                                    }
                                                }

                                            }
                                        });
                                    }
                                };

                                var idleInterval = setInterval(function () {
                                    if (window.blurred) {
                                        return;
                                    }
                                    timerIncrement();
                                }, 1000);

                                function timerIncrement()
                                {
                                    var inactiveTime =${sessionScope.userSession.maxInactiveTime};
                                    var sessionTimeout =${sessionScope.userSession.maxSessionTimeout};
//                                    console.log("OOmaxInactiveTime=" + inactiveTime + "/" + "maxSessionTimeout=" + sessionTimeout);
                                    idleTime++;
//                                    console.log("OOidealtime=" + idleTime);
                                    if (idleTime > sessionTimeout)
                                    {
                                        logout();
                                    } else if (idleTime > inactiveTime)
                                    {
                                        openModal();
                                    }
                                }

                                //Zero the idle timer on mouse movement.
                                $(this).mousemove(function (e) {

                                    idleTime = 0;
                                });
                                $(this).keypress(function (e) {
                                    idleTime = 0;
                                });
                                $(this).click(function (e) {
                                    idleTime = 0;
                                });

                                function checkLock() {
                                    var lockVal = ${sessionScope.userSession.lock};

                                    if (lockVal == 1)
                                    {
                                        openModal();
                                    } else
                                    {
                                        hideModal();
                                    }
                                }

                                function openModal() {
                                    $.ajax({
                                        url: '${pageContext.request.contextPath}/rest/setlock/set',
                                        type: 'GET',
                                        success: function (data) {
                                            $('#myModal').modal({
                                                keyboard: false,
                                                backdrop: false
                                            });
                                        }
                                    });
                                }
                                function hideModal() {
                                    $('#myModal').hide();
                                    $('.modal-backdrop').hide();
                                    var lastActivetime_modal = new Date().getTime();
                                    $.ajax({
                                        url: '${pageContext.request.contextPath}/rest/time/get',
                                        type: 'GET',
                                        data: {lasttime: lastActivetime_modal},
                                        success: function (data) {
                                        }
                                    });
                                }
                                function myLogin() {
                                    var modPass = $("#modalpass").val();
                                    var modUserName = $("[name='modalusername']").attr('placeholder');
                                    $.ajax({
                                        url: '${pageContext.request.contextPath}/rest/login/get',
                                        type: 'GET',
                                        data: {username: modUserName, password: modPass},
                                        success: function (data) {

                                            if (data == 1)
                                            {
                                                openModal();
                                                $('#wrongpass').text("Wrong Password!");
                                            } else
                                            {
                                                hideModal();
                                                var lastActivetime_login = new Date().getTime();
                                                $.ajax({
                                                    url: '${pageContext.request.contextPath}/rest/time/get',
                                                    type: 'GET',
                                                    data: {lasttime: lastActivetime_login},
                                                    success: function (data) {
                                                    }
                                                });
                                                location.reload();
                                            }
                                        }
                                    });

                                }

                                function logout() {
                                    $.ajax({
                                        url: '${pageContext.request.contextPath}/rest/logout/get',
                                        type: 'GET',
                                        success: function (data) {
                                            //var url = '${pageContext.request.contextPath}';
                                            //window.location.replace(url);
                                            location.reload();

                                        }
                                    });
                                }
//                                window.onbeforeunload = function () {
//                                    DetectBrowserExit();
//                                }
//                                function DetectBrowserExit()
//                                {
//                                    alert('Execute task which do you want before exit');
//                                }

//                                var validNavigation = false;
//
//                                function endSession() {
//                                    // Browser or broswer tab is closed
//                                    // Do sth here ...
//                                    alert("bye");
//                                }
//
//                                function wireUpEvents() {
//                                    /*
//                                     * For a list of events that triggers onbeforeunload on IE
//                                     * check http://msdn.microsoft.com/en-us/library/ms536907(VS.85).aspx
//                                     */
//                                    console.log("cross clicked");
//                                    window.onbeforeunload = function () {
//                                        if (!validNavigation) {
//                                            endSession();
//                                        }
//                                    }
//
//                                    // Attach the event keypress to exclude the F5 refresh
////                                    $(document).bind('keypress', function (e) {
////                                        if (e.keyCode == 116) {
////                                            validNavigation = true;
////                                        }
////                                    });
////
////                                    // Attach the event click for all links in the page
////                                    $("a").bind("click", function () {
////                                        validNavigation = true;
////                                    });
////
////                                    // Attach the event submit for all forms in the page
////                                    $("form").bind("submit", function () {
////                                        validNavigation = true;
////                                    });
////
////                                    // Attach the event click for all inputs in the page
////                                    $("input[type=submit]").bind("click", function () {
////                                        validNavigation = true;
////                                    });
//
//                                }
//
//// Wire up the events as soon as the DOM tree is ready
////                                $(document).ready(function () {
////                                    
////                                });
////




</script>
</body>
</html>

