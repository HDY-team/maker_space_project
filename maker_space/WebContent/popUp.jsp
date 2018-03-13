<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>MakerSpace</title>
<!-- Bootstrap core CSS-->
<link href="./Resource/mms/vendor/bootstrap/css/bootstrap2.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template-->
<link href="./Resource/mms/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- Custom styles for this template-->
<link href="./Resource/mms/css/sb-admin.css" rel="stylesheet">
<!-- Include jQuery -->
<script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>


<!-- Include jQuery Popup Overlay -->
<link href="./Resource/popup/jquery.popupoverlay.js" rel="stylesheet">

</head>
<body>

	<!-- Add an optional button to open the popup -->
	<button class="my_popup_open">Open popup</button>

	<!-- Add content to the popup -->
	<div id="my_popup">


		<!-- Add an optional button to close the popup -->
		<button class="my_popup_close">Close</button>

	</div>

	<!-- Include jQuery -->
	<script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>


	<script>
    $(document).ready(function() {

      // Initialize the plugin
      $('#my_popup').popup();

    });
  </script>

</body>
</html>