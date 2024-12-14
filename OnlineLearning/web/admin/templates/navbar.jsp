<!-- navbar.jsp -->

<nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
    <a href="../home" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
        <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>eLEARNING</h2>
    </a>
    <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav ms-auto p-4 p-lg-0">
            <a href="../home" class="nav-item nav-link ">Home</a>
            <a href="subject-management" class="nav-item nav-link ">Subject Management</a>
            
        </div>




        <% if (session.getAttribute("username") != null) { %>
        <a href="../profile" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block"><%= session.getAttribute("username") %></a>
        <% } else { %>
        <a href="login.jsp" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
            <% } %>







    </div>
</nav>
