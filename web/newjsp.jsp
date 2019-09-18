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

        <script src="${pageContext.request.contextPath}/build/vendor/jquery/dist/jquery.min.js"></script>
        
    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">

            <div class="inner-bg">
                <div class="container">


                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 text">
                            <h1 style="font-family: Tahoma">Reset Password</h1>
                        </div>
                    </div>

                    <div class="row">

                        <div class="col-sm-6 col-sm-offset-3 form-box">

                            <div class="form-bottom">
                                <form id="modified" role="form" action="login" method="post" class="login-form" autocomplete="on" >
                                    <!--<h1>Admin Panel</h1>-->
                                    <h3 style="color:#e66022; text-align: center; font-family: Verdana">${message}</h3>
                                    <c:remove var="message" scope="session" />
                                    <div class="clearfix"> </div>
                                    </br>
                                    <div class="form-group">
                                        <input type="password" class="form-username form-control"  name="oldPassword" autocomplete="off" placeholder="Old Password" data-parsley-required="true" />

                                        <!--<input type="text" class="form-username form-control" autocomplete="on"   name="username" placeholder="Username" required="required" />-->
                                    </div>
                                    <div class="form-group">
                                        <input type="password" id="password" class="form-username form-control" autocomplete="off" name="newPassword" placeholder="New password" data-parsley-required="true" onkeyup=" CheckPasswordStrength(this.value)"/>
                                <b><span id="password_strength"></span></b>

                                        <!--<input  type="password" class="form-username form-control" name="password" autocomplete="off"  placeholder="Password" required="required" />-->
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-username form-control" name="retypeNew" placeholder="Retype Password" data-parsley-required="true" onkeypress="setStrengthValue()" />

                                        <!--<input  type="password" class="form-username form-control" name="password" autocomplete="off"  placeholder="Password" required="required" />-->
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" class="form-username form-control" name="passwordStrength" id="passwordStrength" />

                                        <!--<input  type="password" class="form-username form-control" name="password" autocomplete="off"  placeholder="Password" required="required" />-->
                                    </div>
                                    <div>
                                        <button type="submit" class="btn">Change</button>
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
<script type="text/javascript">
    var strength = "";
    function setStrengthValue()
    {
        $('#passwordStrength').val(strength);
    }

    function CheckPasswordStrength(password) {
        var password_strength = document.getElementById("password_strength");
        var nonEngChr = new RegExp("[^\x00-\x7F]+");
        if (password.length == 0) {
            password_strength.innerHTML = "";
            return;
        }
        if (nonEngChr.test(password))
        {
            password_strength.innerHTML = "Unicode not supported";
            password_strength.style.color = "red";
            return;
        } else if (password.length < 8)
        {
            password_strength.innerHTML = "Give minimum 8 characters";
            password_strength.style.color = "red";
            return;
        } else if (password.length > 19)
        {
            password_strength.innerHTML = "Give maximum 20 characters";
            password_strength.style.color = "red";
            return;
        }

        //Regular Expressions.
        var regex = new Array();
        regex.push("[A-Z]"); //Uppercase Alphabet.
        regex.push("[a-z]"); //Lowercase Alphabet.
        regex.push("[0-9]"); //Digit.
        regex.push("[$@$!%*#?&]"); //Special Character.
        //regex.push("[^\x00-\x7F]+"); //Non English component
        //regex.push("^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$");//Minimum 8 characters at least 1 Alphabet and 1 Number
        var passed = 0;
        // var length = 0;
        //Validate for each Regular Expression.
        for (var i = 0; i < regex.length; i++) {
            if (new RegExp(regex[i]).test(password)) {
                passed++;
            }
        }
        //Validate for length of Password.
        if (passed > 2 && password.length > 7) {
            passed++;
            //length = 1;
        }
        //Display status.
        var color = "";
        //var strength = "";
        switch (passed) {
            case 0:
            case 1:
                strength = "Weak";
                color = "red";
                break;
            case 2:
                strength = "Good";
                color = "darkorange";
                break;
            case 3:
            case 4:
                strength = "Strong";
                color = "green";
                break;
            case 5:
                strength = "Strong";
                color = "darkgreen";
                break;
        }
        password_strength.innerHTML = strength;
        password_strength.style.color = color;
    }
</script>