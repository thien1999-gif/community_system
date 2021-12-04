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
    var error_date = false;
    var error_avatar = false;
    var error_cardFront = false;
    var error_cardBack = false;
    var error_CV = false;
    var error_province = false;
    var error_district = false;
    var error_ward = false;
    var error_village = false;
    var error_checkbox = false;
    if($("#avatar")[0].files.length == 0){
    	error_avatar = true; 
    }
    if($("#cardFront")[0].files.length == 0){
    	error_cardFront = true; 
    }    
    if($("#cardFront")[0].files.length == 0){
    	error_cardFront = true; 
    }   
    if($("#cardBack")[0].files.length == 0){
    	error_cardBack = true; 
    } 
    if($("#CV")[0].files.length == 0){
    	error_CV = true; 
    } 
    if($('#province').val() == null){
 	   error_province = true;
    }
    if($('#district').val() == null){
 	   error_district = true;
    }
    if($('#ward').val() == null){
 	   error_ward = true;
    }
    if($('#village').val() == null){
 	   error_village = true;
    } 
    if($('input[type="checkbox"]').prop("checked") != true){
    	error_checkbox = true;
    }
    
    $("#registration_form").submit(function(){

        if(error_checkbox == false &&  error_village == false && error_ward == false && error_district == false && error_province == false && error_CV == false && error_cardBack == false && error_cardFront == false && error_avatar == false && error_date == false && error_password == false && error_confirm_password ==false && error_fullname ==false && error_phone ==false && error_email ==false && error_salary == false){
        	$("#pageLoading").addClass("pageLoading");
        	$("#loading").addClass("lds-roller");
        	
            setInterval( function(){
          		$.ajax({
        			  
        			   url: "phanTramTeacher",
        			  
        			   success: function (data) {
        				   $("#phantram").html(data);
        		        	
        			   }
        			});
            	  
            	  
              } , 700 ); 
        }
        else{
        	if(error_email){
        		alert("PLease check valid email");
        	}
        	if(error_password){
        		alert("PLease check valid password");
        	}
        	if(error_confirm_password){
        		alert("PLease check valid confirm password");
        	}
        	if(error_fullname){
                alert("PLease check valid fullname");
        	}
        	if(error_date){
        		alert("PLease check valid date");
        	}
        	if(error_phone){
                alert("PLease check valid phone number");        		
        	}
        	if(error_salary){
                alert("PLease check valid salary");        		
        	}
        	if(error_avatar){
        		alert("PLease upload avatar");  
        	}
        	if(error_cardFront){
        		alert("PLease upload card id front side"); 
        	}
        	if(error_cardBack){
        		alert("PLease upload card id back side"); 
        	}       	
        	if(error_CV){
        		alert("PLease upload CV"); 
        	}
        	if(error_province){
        		alert("Please select province");
        	}
        	if(error_district){
        		alert("Please select district");
        	}
        	if(error_ward){
        		alert("Please select ward");
        	}
        	if(error_village){
        		alert("Please select village");
        	}
        	if(error_checkbox){
        		alert("Please choose subject in checkbox");
        	}
            return false;
        }

    });

    $('input[type="checkbox"]').click(function(){
        if($(this).prop("checked") == true){
        	error_checkbox = false;
        }
        else if($(this).prop("checked") == false){
        	error_checkbox = true;
        }
    });
    
    $('#province').change(function () {

        if($('#province').val() != null){
     	   error_province = false;
     	  
           if($('#district').val() != null){
        	   error_district = false;
           }
           else{
        	   error_district = true;
           }
     	   
        }
        else{
     	   error_province = true;
        }
       });
     $('#district').change(function () {

        if($('#district').val() != null){
     	   error_district = false;
     	   
     	   
            if($('#ward').val() != null){
            	   error_ward = false;
               }
               else{
            	   error_ward = true;
               }
        }
        else{
     	   error_district = true;
        }
       });
     
     $('#ward').change(function () {

        if($('#ward').val() != null){
     	   error_ward = false;
            if($('#village').val() != null){
         	   error_village = false;
            }
            else{
         	   error_village = true;
            }
     	   
        }
        else{
     	   error_ward = true;
        }
       });
     
     $('#village').change(function () {

        if($('#village').val() != null){
     	   error_village = false;
        }
        else{
     	   error_village = true;
        }
       });    
  
     
     
     
	$(function() {
	    $('#avatar').checkFileType({
	        allowedExtensions: ['jpg'],
	        success: function() {
	        	
	        	error_avatar = false;
	            
	        },
	        error: function() {
	        	alert("Please upload file JPG");
	        	error_avatar = true; 
	            
	        }
	    });

	});
 
	$(function() {
	    $('#cardFront').checkFileType({
	        allowedExtensions: ['jpg'],
	        success: function() {
	        	
	        	error_cardFront = false;
	            
	        },
	        error: function() {
	        	alert("Please upload file JPG");
	        	error_cardFront = true; 
	            
	        }
	    });

	});

	$(function() {
	    $('#cardBack').checkFileType({
	        allowedExtensions: ['jpg'],
	        success: function() {
	        	
	        	error_cardBack = false;
	            
	        },
	        error: function() {
	        	alert("Please upload file JPG");
	        	error_cardBack = true; 
	            
	        }
	    });

	});
	$(function() {
	    $('#CV').checkFileType({
	        allowedExtensions: ['pdf'],
	        success: function() {
	        	
	        	error_CV = false;
	            
	        },
	        error: function() {
	        	alert("Please upload file PDF");
	        	error_CV = true; 
	            
	        }
	    });

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
    

    $('input[type="date"]').change(function(){
        ageMS = Date.parse(Date()) - Date.parse(this.value);
        age = new Date();
        age.setTime(ageMS);
        ageYear = age.getFullYear() - 1970;
        if(ageYear < 17){
            $("#date_error_message").html("Age must be 17 or older");
            $("#date_error_message").show();
        	error_date = true;
        }
        else{
        	$("#date_error_message").hide();
        	error_date = false;
        }  
      
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
        var pattern = new RegExp(/[A-Za-z0-9_]{1,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/gm);
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
    
    (function($) {
	    $.fn.checkFileType = function(options) {
	        var defaults = {
	            allowedExtensions: [],
	            success: function() {},
	            error: function() {}
	        };
	        options = $.extend(defaults, options);

	        return this.each(function() {

	            $(this).on('change', function() {
	                var value = $(this).val(),
	                    file = value.toLowerCase(),
	                    extension = file.substring(file.lastIndexOf('.') + 1);
	                $(this).next('.custom-file-label').html(value);
	                if ($.inArray(extension, options.allowedExtensions) == -1) {
	                    options.error();
	                    $(this).focus();
	                } else {
	                    options.success();

	                }

	            });

	        });
	    };

	})(jQuery);    


});