$(document).ready(function () {
    $("#password_error_message").hide();
    
    $("email_error_message").hide();
   
    var error_password = false;
    
    
    var error_email = false;
   

    $("#registration_form").submit(function(){

        if(error_password == false   && error_email ==false ){
            return true;
        }
        else{
            alert("PLease check valid");
            return false;
        }

    });


    $("#password").keyup(function (e) { 
       password();
       
        });    
    $("#email").keyup(function (e) { 
            email();
        });    


    





    function password(){
        var password_length = $("#password").val().length;
        if(password_length <8 || password_length > 32){
            $("#password_error_message").html("Should be between 8 - 32 characters");
            $("#password_error_message").show();
            error_password = true;
        }
        else{
            error_password = false;
            $("#password_error_message") .hide();
        }
    };
    
    
   
    function email(){
        var pattern = new RegExp(/[A-Za-z0-9_]{5,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/gm);
        if(pattern.test($("#email").val())){
            $("#email_error_message").hide();
            error_email = false;
        }
        else{
            $("#email_error_message").html("Invalid email");
            $("#email_error_message").show();
            error_email = true;
        }
    }  
     


});