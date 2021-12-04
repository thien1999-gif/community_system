<%@page import="com.online.shop.dto.Village"%>
<%@page import="com.online.shop.dto.Ward"%>
<%@page import="com.online.shop.dto.District"%>
<%@page import="com.online.shop.dto.Province"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>

<head>
<c:url var="home" value="/" scope="request" />
<style type="text/css">
select {
	width: 100%;
}

</style>

	<script type="text/javascript">
	$(document).ready(function () {

		
// 					$.ajax({
				  
// 				   url: "province?provinceId=",
				  
// 				   success: function (data) {
// 					   console.log(data);
// 					  $("#district").html(data);
// 	            		$("#ward").html("<option disabled selected value  >- Ward -</option>");
// 	            		$("#village").html("<option disabled selected value  >- Village -</option>");
// 				   }
// 				});
		
		
		    var optionProvince = $("#province").find("option:selected");
            var valueProvince  = optionProvince.val();
            var textProvince   = optionProvince.text();
            if(valueProvince != null){
            	$('#citys').val(textProvince);
            }
            else{
            	$('#citys').val("null");
            }
            
            
		    var optionDistrict = $("#district").find("option:selected");
            var valueDistrict  = optionDistrict.val();
            var textDistrict   = optionDistrict.text();
            if(valueDistrict != null){
            	$('#districts').val(textDistrict);
            }
            else{
            	$('#districts').val("null");
            }
            
            
		    var optionWard = $("#ward").find("option:selected");
            var valueWard  = optionWard.val();
            var textWard   = optionWard.text();
            if(valueWard != null){
            	$('#wards').val(textWard);
            }
            else{
            	$('#wards').val("null");
            }
            
            
		    var optionVillage = $("#village").find("option:selected");
            var valueVillage  = optionVillage.val();
            var textVillage   = optionVillage.text();
            if(valueVillage != null){
            	$('#villages').val(textVillage);
            }
            else{
            	$('#villages').val("null");
            }
            
		
		
        $('#province').change(function () {
        	
            var optionSelected = $(this).find("option:selected");
            var valueSelected  = optionSelected.val();
            var textSelected   = optionSelected.text();
            $('#citys').val(textSelected);
			$.ajax({
				  
				   url: "${home}guest/province?provinceId="+valueSelected,
				  
				   success: function (data) {
					   console.log(data);
					  $("#district").html(data);
	            		$("#ward").html("<option disabled selected value  >- Ward -</option>");
	            		$("#village").html("<option disabled selected value  >- Village -</option>");
				   }
				});
          });
        
		
        $('#district').change(function () {
        	console.log("ok")
            var optionSelected = $(this).find("option:selected");
            var valueSelected  = optionSelected.val();
            var textSelected   = optionSelected.text();
            $('#districts').val(textSelected);
			$.ajax({
				  
				   url: "${home}guest/district?districtid="+valueSelected,
				  
				   success: function (data) {
					   console.log(data);
					  $("#ward").html(data);
					  $("#village").html("<option disabled selected value  >- Village -</option>"); 
				   }
				});
          });
        
        
        $('#ward').change(function () {
        	
            var optionSelected = $(this).find("option:selected");
            var valueSelected  = optionSelected.val();
            var textSelected   = optionSelected.text();
            $('#wards').val(textSelected);
			$.ajax({
				  
				   url: "${home}guest/ward?wardid="+valueSelected,
				  
				   success: function (data) {
					   console.log(data);
					  $("#village").html(data);
				   }
				});
          });
		
        $('#village').change(function () {
            var optionSelected = $(this).find("option:selected");
            var valueSelected  = optionSelected.val();
            var textSelected   = optionSelected.text();
            $('#villages').val(textSelected);
        
          


    }); 		
		
		
		

	});
	
	
	</script>

</head>



      <select name="seProvince" id="province">
              <option disabled selected value  >- Province -</option>
                    <c:forEach var ="province" items="${listProvince}">
                    <c:if test="${province.provinceid == codeProvince}">
                    <option value="${province.provinceid}" selected>${province.name}</option>
                    </c:if>
                     <c:if test="${province.provinceid != codeProvince}">
                    <option value="${province.provinceid}" >${province.name}</option>
                    </c:if>
                    
                    </c:forEach>                    
	
            </select>
            
                 
              <select name="seDistrict" id="district">
              <option disabled selected value  >- District -</option>
              	<c:forEach var="district" items="${listDistrict}" >
              		<c:if test="${district.districtid == codeDistrict}">
              		 <option value="${district.districtid}" selected>${district.name}</option>
              		</c:if>
               		<c:if test="${district.districtid != codeDistrict}">
              		 <option value="${district.districtid} ">${district.name}</option>
              		</c:if>             		
              	</c:forEach>
                </select>     
                
                 <select name="seWard" id="ward">
              <option disabled selected value  >- Ward -</option>
              	<c:forEach var="ward" items="${listWard}" >
              		<c:if test="${ward.wardid == codeWard}">
              		 <option value="${ward.wardid}" selected>${ward.name}</option>
              		</c:if>
              		<c:if test="${ward.wardid != codeWard}">
              		 <option value="${ward.wardid}" >${ward.name}</option>
              		</c:if>           		
              	</c:forEach>
              
                </select>    
                
                  <select name="seVillage" id="village">
              <option disabled selected value  >- Village -</option>
              	<c:forEach var="village" items="${listVillage}" >
              		<c:if test="${village.villageid == codeVillage}">
              		 <option value="${village.villageid}" selected>${village.name}</option>
              		</c:if>
              		<c:if test="${village.villageid != codeVillage}">
              		 <option value="${village.villageid}" >${village.name}</option>
              		</c:if>            		
              	</c:forEach>
              
                </select>  
                  
        

        
        
        
        
        
	
