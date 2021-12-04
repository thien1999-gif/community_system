$(document).ready(function () {

    $("#fullname_error_message").hide();
    $("#phone_error_message").hide();
    $("#date_error_message").hide();
    $("#salary_error_message").hide();
   
    
    var error_fullname = false;
    var error_phone = false;
   
    var error_salary = false;
    var error_date = false;
    var error_avatar = false;

    
    var error_province = false;
    var error_district = false;
    var error_ward = false;
    var error_village = false;
  

    
    $("#viewTrainerProfile").submit(function(){

        if(  error_village == false && error_ward == false && error_district == false && error_province == false &&  error_avatar == false && error_date == false  && error_fullname ==false && error_phone ==false &&  error_salary == false){
            return true;
        }
        else{
        	

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
        		alert("PLease upload avatar with JPG");  
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
//        	if(error_checkbox){
//        		alert("Please choose subject in checkbox");
//        	}
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
 







    $("#fullname").keyup(function (e) { 
        fullname();
    });
    $("#phone").keyup(function (e) { 
        phone();
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