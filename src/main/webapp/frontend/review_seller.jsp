<!--
    @author Sera Jeong 12211242 Created Date: 01/09/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Seller Reviews" />
</jsp:include>
<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>Seller Reviews</h2>
                <p>You can view the reviews and ratings left by the buyers from your card sales here.</p>
                <hr class="my-4">
                <div>
                    <p>Your Seller ID number is: ${sellerId}</p>
                </div>
            </div>
        </div>
        <div class="row">&nbsp;</div>
        
        <!-- Existing Reviews Section -->
        <div class="row">
            <div class="col">
                <table class="table table-striped">
                    <thead>
                        <tr>
<!--                         
                            <th>Review ID</th>
                            <th>Buyer ID</th>
                             -->
                            <th>Comment</th>
                            <th>Rating</th>
                            <th>Review Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${reviews}" var="review">
                            <tr>
<%--                             
                                <td>${review.reviewId}</td>
                                <td>${review.buyerId}</td>
                                 --%>
                                <td>${review.comment}</td>
                                <td>${review.rating}</td>
                                <td>${review.reviewDate}</td>
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
