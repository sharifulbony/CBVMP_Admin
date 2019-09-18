<%-- 
    Document   : welcome
    Created on : May 31, 2016, 2:06:59 PM
    Author     : rahat
--%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CBVMP | Admin Panel </title>

        <!-- CSS
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        -->

        <link rel="stylesheet" href="${pageContext.request.contextPath}/build/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/build/assets/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/build/assets/css/form-elements.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/build/assets/css/style.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- Favicon and touch icons -->
        <!--        <link rel="shortcut icon" href="assets/ico/favicon.png">
                <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
                <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
                <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
                <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">-->

    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">

            <div class="inner-bg">
                <div class="container">


                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 text">
                            <h1 ><strong style="font-family: Tahoma">CBVMP Admin Panel</strong></h1>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-sm-6 col-sm-offset-3 form-box">

                            <div class="form-bottom">
                                
                                <form id="modified" role="form" action="login" method="post" class="login-form" autocomplete="off" >
                                    <!--<h1>Admin Panel</h1>-->
                                    <h3 style="color:#e66022; text-align: center; font-family: Verdana">${message}</h3>
                                    <c:remove var="message" scope="session" />
                                    <div class="clearfix"> </div>
                                    </br>
                                    <div class="form-group">

                                        <input type="text" class="form-username form-control" autocomplete="on"   name="username" placeholder="Username" required="required" />
                                    </div>
                                    <div class="form-group">

                                        <input  type="password" class="form-username form-control" name="password" autocomplete="off"  placeholder="Password" required="required" />
                                    </div>
                                    <div>
                                        <button type="submit" class="btn">Log in</button>
                                        <!--<input type="submit" class="btn btn-default" value="Log in"/>-->
                                        <!--<a class="btn btn-default submit" href="index.html">Log in</a>-->


                                    </div>



                                </form>

                            </div>

                        </div>


                    </div>

                </div>

            </div>

        </div>


        <!-- Javascript -->
        <script src="${pageContext.request.contextPath}/build/assets/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/build/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/build/assets/js/jquery.backstretch.min.js"></script>
        <script src="${pageContext.request.contextPath}/build/assets/js/scripts.js"></script>

        <!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>