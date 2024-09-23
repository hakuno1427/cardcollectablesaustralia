<!--
	@author Sera Jeong 12211242 Created Date: 21/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Card Catalogue" />
</jsp:include>
<head>
    <script type="text/javascript">
        function confirmDelete() {
            return confirm("Are you sure you want to delete this card?");
        }
    </script>
</head>
<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>Card Catalogue</h2>
                <p>You can manage the card catalogue here.</p>
                <hr class="my-4">
                
                <p>Search by card name to quickly find specific cards in the catalogue.</p>
                <!-- Search Form -->
                <div class="d-flex justify-content-center">
	                <form action="catalogue" method="get" class="form-inline">
	                    <input type="search" name="keyword" class="form-control mr-sm-2 mt-1" placeholder="Enter keyword here..." />
	                    <input type="submit" value="Search" class="btn btn-outline-success mt-1" />
	                </form>
                </div>
                <hr class="my-4"> 
                               
                <div>
                	<p>To add a new card information to the catalogue: </p>
                    <a href="card_add" class="btn btn-primary">Add Card</a>
                </div>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        
        <!-- Existing Card Information Section -->
        <div class="row">
            <div class="col">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Image</th> <!-- Image as the first column -->
                            <th>Serial Number</th>
                            <th>Card Name</th>
                            <th>Description</th>
                            <th>Game</th>
                            <th>Marketprice($)</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listCards}" var="card">
                            <tr>
                                <td>
                                    <img src="${card.imageUrl}" alt="${card.cardName}" style="max-width: 100px; height: auto;">
                                </td>
                                <td>${card.serialNumber}</td>
                                <td>${card.cardName}</td>
                                <td>${card.description}</td>
                                <td>${card.game}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${card.marketprice == -1}">
                                            marketprice currently unavailable
                                        </c:when>
                                        <c:otherwise>
                                            ${card.marketprice}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <c:if test="${auth:hasPermission(role, 'MANAGE_CARD_CARTALOGUE')}">                
                                <td>
                                    <a href="card_update?id=${card.serialNumber}" class="btn btn-warning btn-sm">Update</a>
                                    <a href="card_delete?id=${card.serialNumber}" class="btn btn-danger btn-sm" onclick="return confirmDelete();">Delete</a>
                        </td>
                                </c:if>
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
                    <a href="?page=1">&laquo; First</a>
                    <a href="?page=${currentPage - 1}">&lt; Previous</a>
                </c:if>
                
                <c:forEach begin="${startPage}" end="${endPage}" var="i">
                    <c:choose>
                        <c:when test="${i == currentPage}">
                            <span>${i}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${i}">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <a href="?page=${currentPage + 1}">Next &gt;</a>
                    <a href="?page=${totalPages}">Last &raquo;</a>
                </c:if>
            </div>
        </div>

        <div class="row">&nbsp;</div>

        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
</html>
