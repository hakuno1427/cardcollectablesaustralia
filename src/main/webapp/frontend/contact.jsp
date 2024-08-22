<!--
	@author Sera Jeong 12211242
	Created Date: 21/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="Contact Us" />
</jsp:include>
<body>
	<div class="container">
		<jsp:include page="header.jsp" />

		<div class="row">&nbsp;</div>
		<div class="row">
			<div class="col text-left">
				<h2 style="text-align: center;">Contact Us</h2>
				<p>If you have an issue or need assistance, please contact us via email.</p>
                <p>You can email us directly at <a href="mailto:coit13230.2024.team2@gmail.com">coit13230.2024.team2@gmail.com</a></p>
                <p>Please include your <strong>full name</strong> and <strong>message</strong> in your email. A clear description of your issue or inquiry will enable us to better understand your situation and provide more effective assistance.</p>
                <p>Currently, we only offer support via email. Our team is striving to respond to all inquiries within 2-3 business days. Furthermore, we are working on expanding our support options.</p>
                <p>Thank you for your patience, and we look forward to hearing from you.</p>
            </div>
		</div>
		<div class="row">&nbsp;</div>

		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>
