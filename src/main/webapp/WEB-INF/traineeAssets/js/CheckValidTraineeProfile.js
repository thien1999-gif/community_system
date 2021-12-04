$(document).ready(function () {

  
    $("#fullname_error_message").hide();
   
    $("#phone_error_message").hide();

   $("#date_error_message").hide();

    var error_fullname = false;
    var error_phone = false;
    
    var error_date = false;
    var error_image = false;
    var error_province = false;
    var error_district = false;
    var error_ward = false;
    var error_village = false
    





   



	
    
  	
  
    
    $("#viewProfile").submit(function(){

        if(error_image == false && error_village == false && error_ward == false && error_district == false && error_province == false  && error_date ==false && error_fullname ==false && error_phone ==false){
            return true;
        }
        else{

        	

        	if(error_image){
        		alert("PLease upload avatar with JPG");
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
                  
    


    $("#fullname").keyup(function (e) { 
        fullname();
    });
    $("#phone").keyup(function (e) { 
        phone();
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



});