$(document).ready(function () {

  
	frmRequestForTrainer
    
    $("#frmRequestForTrainer").submit(function(){

        if($("#dayInWeek").val() !=null && $("#timeInDay").val() !=null){
        	
            return true;
        }
        else{

        	if($("#dayInWeek").val() ==null){
        		alert("PLease selected Day to study");
        	}
        	if($("#timeInDay").val() ==null){
        		alert("PLease selected Time to study");
        	}

      
            
            return false;
        }

    });
    

});