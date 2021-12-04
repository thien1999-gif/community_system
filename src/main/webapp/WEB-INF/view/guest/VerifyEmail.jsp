<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%-- <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Veryfi Code</title>
</head>
<body>
<form action="verifyCode" method="post">
<input type="hidden" name="userEmail" value="${userEmail}"/>
Code: <input type="text" name="verifyCodeEmail" value=""/>
<input type="submit" value="Submit"/>
</form>
</body>
</html> --%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Veirfy Code</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Verify Form</h2>
  <p>Please check your email for verify code to activate the account</p>
  <form action="verifyCode" method="post">
    <div class="form-group">
      <label for="email">Verify Code:</label>
      <input type="hidden" name="userEmail" value="${userEmail}"/>
      <input type="text" class="form-control" id="email" placeholder="Verify Code" name="verifyCodeEmail" value="">
    </div>
    <button type="submit" class="btn btn-default">Submit</button>
  </form>
  <font color="red">
  ${errorCode}
  </font>
</div>

</body>
</html>
