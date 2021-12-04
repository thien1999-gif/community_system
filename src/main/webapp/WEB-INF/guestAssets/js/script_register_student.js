$(document).ready(function () {


	
    $("#password_error_message").hide();
    $("#fullname_error_message").hide();
    $("#confirm_password_error_message").hide(); 
    $("#phone_error_message").hide();
    $("#email_error_message").hide();
   $("#date_error_message").hide();
    var error_password = false;
    var error_confirm_password = false;
    var error_fullname = false;
    var error_phone = false;
    var error_email = false;
    var error_date = false;
    var error_image = false;
    var error_province = false;
    var error_district = false;
    var error_ward = false;
    var error_village = false
    
   if( $("#fullname").val() ==""){
	   error_fullname = true;
   }
   if( $("#password").val() ==""){
	   error_password = true;
   }
   if( $("#confirm_password").val() ==""){
	   error_confirm_password = true;
   }
   if( $("#phone").val() ==""){
	   error_phone = true;
   }
   if( $("#email").val() ==""){
	   error_email = true;
   }
   if(!Date.parse($("#dateOfBirth").val())){
	  
   	error_date = true;
   	}
   if($("#image")[0].files.length == 0){
	   error_image = true; 
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
	
   


	$(function() {
	    $('#image').checkFileType({
	        allowedExtensions: ['jpg'],
	        success: function() {
	        	
	        	error_image = false;
	            
	        },
	        error: function() {
	        	alert("Please upload file JPG");
	        	 error_image = true; 
	            
	        }
	    });

	});
	
    
  	
  
    
    $("#registration_form").submit(function(){

        if(error_village == false && error_ward == false && error_district == false && error_province == false && error_image == false && error_date ==false && error_password == false && error_confirm_password ==false && error_fullname ==false && error_phone ==false && error_email ==false){
            
        	$("#pageLoading").addClass("pageLoading");
        	$("#loading").addClass("lds-roller");
        	
            setInterval( function(){
          		$.ajax({
        			  
        			   url: "phantram",
        			  
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
        	if(error_image){
        		alert("Please upload file JPG");
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
            
            return false;
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
    

    $('input[type="date"]').change(function(){
        ageMS = Date.parse(Date()) - Date.parse(this.value);
        age = new Date();
        age.setTime(ageMS);
        ageYear = age.getFullYear() - 1970;
        if(ageYear < 6){
            $("#date_error_message").html("Age must be 6 or older");
            $("#date_error_message").show();
        	error_date = true;
        }
        else{
        	$("#date_error_message").hide();
        	error_date = false;
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


});