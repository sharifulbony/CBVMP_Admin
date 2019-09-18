<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="../layout/header.jsp" /> <br/>
<jsp:include page="../layout/sidebar.jsp" /> <br/>


<!-- page content -->
<div class="right_col" role="main">
    <div class="">
        <div class="page-title">
            <div class="title_left">
                <h3>User Settings</h3>
            </div>
        </div>

        <!--<div class="clearfix"></div>-->

        <div class="row">

            <div class="col-md-12 col-sm-12 col-xs-12">

                <div class="x_panel">
                    <div class="x_title">
                        <h2> <small>Reset Password </small></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div style="display: block;" class="x_content">

                        <form data-parsley-validate class="form-horizontal form-label-left" action="${pageContext.request.contextPath}/change/password" method="post" class="login-form">

                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="userName">Old Password <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input class="form-control col-md-7 col-xs-12" type="password" name="oldPassword" autocomplete="off" placeholder="Old Password" data-parsley-required="true">
                                </div>

                            </div>

                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">New Password <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input class="form-control col-md-7 col-xs-12" type="password" id="password" autocomplete="off" name="newPassword" placeholder="New password" data-parsley-required="true" onkeyup=" CheckPasswordStrength(this.value)"/>
                                    <b><span id="password_strength"></span></b>
                                </div>
                            </div>
                            <div class="item form-group bad">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="retypePassword">Retype New Password <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input class="form-control col-md-7 col-xs-12"  type="password" class="form-control" name="retypeNew" placeholder="Retype Password" data-parsley-required="true" onkeypress="setStrengthValue()" />
                                </div>
                            </div>
                            <div>
                                <input type="hidden" class="form-control" name="passwordStrength" id="passwordStrength" >
                            </div> 



                            <div class="ln_solid"></div>
                            <div class="form-group">
                                
                                <div class="col-md-6 col-md-offset-3">
                                    <h4 style="color: red"> ${message}</h4>
                                    <c:remove var="message" scope="session" /> 
                                    
                                    <button type="submit" class="btn btn-success">Change</button>
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

