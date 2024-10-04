<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="page_head.jsp">
        <jsp:param name="pageTitle" value="My Profile" />
    </jsp:include>
    <style>

        .btn-custom {
            color: #fff;
            background-color: #18BC9C;
            border-color: #f8f9fa;
            padding: 1rem 1.75rem;
            font-size: 1.25rem;
            border-width: 2px;
            border-radius: 0.375rem;
            transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out;
        }

        .btn-custom:hover {
            background-color: #f8f9fa;
            color: #18BC9C;
            border-color: #f8f9fa;
        }

        .btn-custom:active {
            background-color: #f8f9fa;
            border-color: #f8f9fa;
        }

        .card {
            background: #fff;
            transition: .5s;
            border: 0;
            margin-bottom: 30px;
            border-radius: .55rem;
            position: relative;
            width: 100%;
            box-shadow: 0 1px 2px 0 rgb(0 0 0 / 10%);
            height: 100%; 
        }

        .card-body {
            flex: 1 1 auto;
            min-height: 1px;
            padding: 1rem;
        }

        .gutters-sm {
            margin-right: -8px;
            margin-left: -8px;
        }

        .gutters-sm > .col, .gutters-sm > [class*=col-] {
            padding-right: 8px;
            padding-left: 8px;
        }

        .rounded-circle {
            border-radius: 50% !important;
        }

        .mb-3, .my-3 {
            margin-bottom: 1rem!important;
        }

        .text-secondary {
            color: #6c757d !important;
        }

  
        .col-md-4, .col-md-8 {
            display: flex;
            flex-direction: column;
        }
    </style>
</head>

<body class="d-flex flex-column min-vh-100">
    <jsp:directive.include file="header_without_searchbar.jsp" />

    <div class="container flex-grow-1">
        <div class="main-body">
            <div class="row gutters-sm">
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <div class="card-body">
                            <div class="d-flex flex-column align-items-center text-center">
                                <img class="rounded-circle" src="https://cdn-icons-png.flaticon.com/512/4645/4645949.png" alt="User" width="150" />
                                <div class="mt-3">
                                    <h4>${user.firstName} ${user.lastName}</h4>
                                    <p class="text-secondary mb-1">${user.description != null ? user.description : "No Description"}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-8">
                    <div class="card mb-3 h-100">
                        <div class="card-body">
                            <!-- Email Address -->
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">E-mail Address</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.email}
                                </div>
                            </div>
                            <hr />

                            <!-- First Name -->
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">First Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.firstName}
                                </div>
                            </div>
                            <hr />

                            <!-- Last Name -->
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Last Name</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.lastName}
                                </div>
                            </div>
                            <hr />

                            <!-- Phone Number -->
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="mb-0">Phone Number</h6>
                                </div>
                                <div class="col-sm-9 text-secondary">
                                    ${user.phone}
                                </div>
                            </div>
                            <hr />

                            <!-- Description -->
                            <c:if test="${not empty user.description}">
                                <div class="row">
                                    <div class="col-sm-3">
                                        <h6 class="mb-0">Description</h6>
                                    </div>
                                    <div class="col-sm-9 text-secondary">
                                        ${user.description}
                                    </div>
                                </div>
                                <hr />
                            </c:if>

                            <!-- Edit Profile Link -->
                            <div class="row">
                                <div class="col-sm-12 text-center mt-3 mb-3">
                                    <a href="edit_profile" class="btn-custom">Edit Profile</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer Section -->
    <jsp:directive.include file="footer.jsp" />
</body>
</html>
