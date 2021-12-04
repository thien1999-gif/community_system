$(document).ready(function () {

    $("#password_error_message").hide();
    $("#confirm_password_error_message").hide(); 
    $("#date_error_message").hide();
    var error_password = false;
    var error_confirm_password = false;
    var error_image= false;
    var error_date = false;
  
	
   



    
  	
  
    
    $("#frmProfileAdmin").submit(function(){

        if( error_date == false && error_image == false && error_password == false && error_confirm_password == false ){
            return true;
        }
        else{

        	

        	if(error_password){
        		alert("PLease check valid password");
        	}
        	if(error_confirm_password){
        		alert("PLease check valid confirm password");
        	}
        	if(error_image){
        		alert("Please upload avatar with JPG");
        	}
        	if(error_date){
        		alert("Date not valid");
        	}

            
            return false;
        }

    });
    
    
	$(function() {
	    $('#profileImage').checkFileType({
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