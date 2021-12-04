$(document).ready(function () {
    $("#password_error_message").hide();
    $("#fullname_error_message").hide();
    $("confirm_password_error_message").hide(); 
    $("phone_error_message").hide();
    $("email_error_message").hide();
    $("salary_error_message").hide();
    var error_password = false;
    var error_confirm_password = false;
    var error_fullname = false;
    var error_phone = false;
    var error_email = false;
    var error_salary = false;

    $("#registration_form").submit(function(){

        if(error_password == false && error_confirm_password ==false && error_fullname ==false && error_phone ==false && error_email ==false && error_salary == false){
            return true;
        }
        else{
            alert("PLease check valid");
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

    $("#fullname").keyup(function (e) { 
        fullname();
    });
    $("#phone").keyup(function (e) { 
        phone();
    });
    $("#email").keyup(function (e) { 
        email();
    });
    $("#salary").keyup(function (e) { 
        salary(); 
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
    function fullname(){
        var fullname_length = $("#fullname").val().length;
        if(fullname_length <3 || fullname_length > 30){
            $("#fullname_error_message").html("Should be between 3 - 30 characters");
            $("#fullname_error_message").show();
            error_fullname = true;
        }
        else{
            error_fullname = false;
            $("#fullname_error_message") .hide();
        }
    };
    function phone(){
        var pattern = new RegExp(/(09|01|02[2|6|8|9])+([0-9]{8,9})\b/g);
        if(pattern.test($("#phone").val())){
            $("#phone_error_message").hide();
            error_phone=false;
        }
        else{
            $("#phone_error_message").html("Invalid phone");
            $("#phone_error_message").show();
            error_phone=true;
        }
    }
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
    function salary(){
        
        
        var pattern = new RegExp(/[0-9]{1,8}$/);
        if(pattern.test($("#salary").val())){
            $("#salary_error_message").hide();
            error_salary = false;
        }
        else{
            $("#salary_error_message").html("Invalid salary");
            $("#salary_error_message").show();
            error_salary = true;
        }
    }   


});