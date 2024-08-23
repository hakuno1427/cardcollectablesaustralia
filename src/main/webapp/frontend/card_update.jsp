<!--
	@author Sera Jeong 12211242
	Created Date: 22/08/2024
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<jsp:include page="page_head.jsp">
    <jsp:param name="pageTitle" value="Update Card" />
</jsp:include>
<body>
    <div class="container">
        <jsp:directive.include file="header.jsp" />

        <div class="row">&nbsp;</div>
        <div class="row">
            <div class="col text-center">
                <h2>Update Card Information</h2>
            </div>
        </div>
        <div class="row">&nbsp;</div>

        <!-- Card Update Form -->
       <form action="card_update_save" method="post" style="max-width: 800px; margin: 0 auto;">
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Card Name :</label>
                <div class="col-sm-8">
                    <input type="text" name="cardName" class="form-control"
                           value="${card.cardName}" required minlength="2"/>
                </div>
            </div>
			<div class="form-group row">
			    <label class="col-sm-4 col-form-label">Game :</label>
			    <div class="col-sm-8">
			        <select id="game" name="game" class="form-control">
			            <c:forEach var="game" items="${games}">
			                <option value="${game}" ${card.game == game ? 'selected' : ''}>${game}</option>
			            </c:forEach>
			        </select>
			    </div>
			</div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Serial Number :</label>
                <div class="col-sm-8">
                    <input type="text" name="serialNumber" class="form-control"
                           value="${card.serialNumber}" required minlength="2" readonly/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Description :</label>
                <div class="col-sm-8">
                    <input type="text" name="description" class="form-control"
                           value="${card.description}" required minlength="2"/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Market Price :</label>
                <div class="col-sm-8">
                    <c:choose>
                        <c:when test="${card.marketprice == -1}">
                            <p>marketprice currently unavailable</p>
                        </c:when>
                        <c:otherwise>
                            <input type="number" name="marketprice" class="form-control" step="0.01" min="0"
                                   value="${card.marketprice}" required />
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-4 col-form-label">Image URL :</label>
                <div class="col-sm-8">
                    <input type="text" name="imageUrl" class="form-control"
                           value="${card.imageUrl}" required minlength="2"/>
                </div>
            </div>
            <div class="row">&nbsp;</div>
            <div class="row">
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary mr-3">Save</button>
                    <input type="button" value="Cancel" class="btn btn-secondary"
                           onclick="history.go(-1);" />
                </div>
            </div>
        </form>

        <jsp:directive.include file="footer.jsp" />
    </div>
</body>
<script type="text/javascript" src="js/customer-form.js"></script>
</html>
