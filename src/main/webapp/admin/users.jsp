<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="User Management" />
</jsp:include>
<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>User Management</h2>
                <p>You can manage existing user accounts here.</p>
                <hr class="my-4">
                <p>View accounts based on their roles to easily assign or revoke permissions.</p>
            </div>
        </div>
        
   		<c:if test="${message != null}">
			<div class="row">
				<div class="col text-center">
					<h4 class="message">${message}</h4>
				</div>
			</div>
		</c:if>


        <!-- Filter by Role -->
        <div class="row">
            <div class="col-md-4 offset-md-4 text-center">
                <form method="get" action="users">
				    <select name="roleFilter">
				        <option value="ALL">All Users</option>

				        <option <c:if test="${roleFilter eq 'admin'}"> selected </c:if> value="admin">Admin</option>
				        <option <c:if test="${roleFilter eq 'buyer'}"> selected </c:if> value="buyer">Buyer</option>
				        <option <c:if test="${roleFilter eq 'seller'}"> selected </c:if>value="seller">Seller</option>

				    </select>
				    <button type="submit" class="btn btn-primary mt-2">Filter</button>
				</form>
            </div>
        </div>


        <div class="row">&nbsp;</div>

        <!-- Display Message if any -->
        <c:if test="${message != null}">
            <div class="row">
                <div class="col text-center">
                    <h4 class="message">${message}</h4>
                </div>
            </div>
        </c:if>


        <div class="row">&nbsp;</div>

        <!-- User Table -->
        <div class="row">
            <div class="col">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>User ID</th>
                            <th>Email</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Phone</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listUsers}" var="listUser">
                            <tr>
                                <td>${listUser.userId}</td>
                                <td>${listUser.email}</td>
                                <td>${listUser.firstName}</td>
                                <td>${listUser.lastName}</td>
                                <td>${listUser.phone}</td>
                                <td>${listUser.role.name}</td>
                                <td>${listUser.enabled == 1 ? "Active" : "Banned"}</td>
                                <td>
                                    <a href="user_update?id=${listUser.userId}" class="btn btn-warning btn-sm">Update</a>
                                    
                                    <c:if test="${listUser.enabled == 1}">
                                        <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#confirmBanModal-${listUser.userId}">Ban</button>
                                        <div class="modal fade" id="confirmBanModal-${listUser.userId}" tabindex="-1" role="dialog" aria-labelledby="confirmBanModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="confirmDeleteModalLabel">Confirm Ban</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        Are you sure you want to permanently ban this user?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                        <a href="user_ban?id=${listUser.userId}" class="btn btn-danger">Ban</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Pagination Links -->
        <div class="row">
            <div class="col text-center">
                <c:if test="${currentPage > 1}">
                    <a href="?page=1&role=${param.role}">&laquo; First</a>
                    <a href="?page=${currentPage - 1}&role=${param.role}">&lt; Previous</a>
                </c:if>

                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <span>${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${i}&role=${param.role}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}&role=${param.role}">Next &gt;</a>
                    <a href="?page=${totalPages}&role=${param.role}">Last &raquo;</a>
                </c:if>
            </div>
        </div>

        <div class="row">&nbsp;</div>

        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
