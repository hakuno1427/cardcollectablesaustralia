<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
	<jsp:param name="pageTitle" value="${pageTitle}" />
</jsp:include>
<body>
	<link rel="stylesheet" href="../css/my_messages.css">
    <script src="../js/messages.js"></script>
    
    <div class="container">
    <jsp:directive.include file="header.jsp" />
    <div class="message-row row g-0">
        <!-- User List -->
        <div class="message-col col-md-2">
            <div class="card">
                <div class="card-header">
                    <h5>Users</h5>
                </div>
                <div class="card-body scrollable-list">
                    <ul class="list-group">
                        <c:forEach var="user" items="${userList}">
                            <li class="list-group-item user-item" data-userid="${user.userId}">
                                ${user.firstName} ${user.lastName}
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Subject List -->
        <div class="message-col col-md-2">
            <div class="card">
                <div class="card-header">
                    <h5>Subject</h5>
                </div>
                <div class="card-body scrollable-list">
                    <ul class="list-group" id="subjectList">
                        <!-- Initially empty, will be populated via Ajax -->
                    </ul>
                </div>
            </div>
        </div>

        <!-- Message Area -->
        <div class="message-col col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5>Messages</h5>
                </div>
                <div class="card-body message-box" id="messageBox">
                    <!-- Messages for the selected subject will appear here -->
                </div>
                <div id="messageForm" class="card-footer" style="display:none;">
                    <form id="sendMessageForm">
                        <input type="hidden" name="userId" id="userId">
                        <input type="hidden" name="subject" id="subject">
                        <div class="mb-3">
                            <textarea class="form-control" name="messageContent" id="messageContent" rows="3" placeholder="Enter your message here..."></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary">Send Message</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <jsp:directive.include file="footer.jsp" />
</div>

</body>
</html>
