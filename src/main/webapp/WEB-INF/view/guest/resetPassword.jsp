<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
/* Style all input fields */
input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
}

/* Style the submit button */
input[type=submit] {
  background-color: #4CAF50;
  color: white;
}

/* Style the container for inputs */
.container {
  background-color: #f1f1f1;
  padding: 20px;
}

/* The message box is shown when the user clicks on the password field */
#message {
  display:none;
  background: #f1f1f1;
  color: #000;
  position: relative;
  padding: 20px;
  margin-top: 10px;
}

#message p {
  padding: 10px 35px;
  font-size: 18px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
  color: green;
}

.valid:before {
  position: relative;
  left: -35px;
  content: "â";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
  color: red;
}

.invalid:before {
  position: relative;
  left: -35px;
  content: "â";
}


.form_table { text-align: center; }
  .full_width .segment_header { text-align: center !important; }
  .q { float:none;display: inline-block; }
  table.matrix, table.rating_ranking { margin-left:auto !important;margin-right:auto !important; }
</style>

	<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<body>


<div id="form-wrapper" style="max-width:500px;margin:auto;" >
<h3>Reset Password</h3>
	<div class="container">
  		<form id="frmResetPassword" action="${pageContext.request.contextPath}/guest/processReset" method="post">
    		<label for="usrname">Password</label>
    		<input type="password" id="password" name="password" value=""  required>
			<small style="color:red;" id="password_error_message" class="form-text text-muted"> </small>
			
			<br>
    		<label for="psw">Confirm Password</label>
    		<input type="password" id="confirm_password" name="confirmPassword" value=""  required>
    		<small style="color:red;" id="confirm_password_error_message" class="form-text text-muted"> </small>
    		
    		<input type="hidden" name="token" value="${resetToken}">
    		<font color="red">${errorMessage}</font>
   			<input type="submit" value="Submit">
  		</form>
	</div>
</div>



				
<script>
$(document).ready(function () {

    $("#password_error_message").hide();
    $("#confirm_password_error_message").hide(); 
    var error_password = false;
    var error_confirm_password = false;

  
	
   



    
  	
  
    
    $("#frmResetPassword").submit(function(){

        if( error_password == false && error_confirm_password == false ){
            return true;
        }
        else{

        	

        	if(error_password){
        		alert("PLease check valid password");
        	}
        	if(error_confirm_password){
        		alert("PLease check valid confirm password");
        	}


            
            return false;
        }

    });
    
    

    
    
    $("#password").keyup(function (e) { 
       password();
       confirm_password();
        });    
    $("#confirm_password").keyup(function (e) { 
         confirm_password();
    });

   
function password(){
        var password_length = $("#password").val().length;
        if(password_length <6 || password_length > 32){
            $("#password_error_message").html("Should be between 6 - 32 characters");
            $("#password_error_message").show();
            error_password = true;
        }
        else{
            error_password = false;
            $("#password_error_message") .hide();
        }
    };
function confirm_password(){
        if ( $("#confirm_password").val() != $("#password").val()){
            $("#confirm_password_error_message").html("Password not match");
            $("#confirm_password_error_message").show();
            error_confirm_password = true;
           }
           else{
            error_confirm_password = false;
            $("#confirm_password_error_message").hide();
           }
    };
  


});




</script>

</body>
</html>
