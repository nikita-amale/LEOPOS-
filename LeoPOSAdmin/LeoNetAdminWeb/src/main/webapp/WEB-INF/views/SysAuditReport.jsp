<%@ page import="java.util.List"%>
<%@ page import="com.leonet.common.pojo.SysAuditReportDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/common/Header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/MenuBar.jsp"></jsp:include>

<div class="content-wrapper">
    <section class="content-header">
        <h1>Audit Report</h1>
    </section>
    <section class="content">
        <div class="card">
            <div class="card-header">
                <form id="sysAuditForm" action="${pageContext.request.contextPath}/sysAuditReport" method="get">
                <div class="card-body">
        <div class="form-group row">
    <div class="col-md-3">
        <label for="startDate">Start Date</label>
        <input type="date" class="form-control input-tip" name="startDate" id="startDate"
               value="${startDate != null ? startDate : ''}">
    </div>
    <div class="col-md-3">
        <label for="endDate">End Date</label>
        <input type="date" class="form-control input-tip" name="endDate" id="endDate"
               value="${endDate != null ? endDate : ''}">
    </div>
    <div class="col-md-3">
        <label for="action">Action</label>
        <select class="form-control select2bs4" name="action" id="action">
            <option value="" ${empty action ? 'selected' : ''}>Select</option>
            <c:forEach var="actionItem" items="${actions}">
                <option value="${actionItem}" ${actionItem == action ? 'selected' : ''}>${actionItem}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-3">
        <label for="userName">User Name</label>
        <select class="form-control select2bs4" name="userName" id="userName">
            <option value="" ${empty selectedUser ? 'selected' : ''}>Select</option>
            <c:forEach var="user" items="${userNames}">
                <option value="${user}" ${user == selectedUser ? 'selected' : ''}>${user}</option>
            </c:forEach>
        </select>
    </div>
</div>


							<button type="submit" class="btn btn-primary btn-font-size">Submit</button>
			<button type="button" class="btn btn-primary btn-font-size" onclick="goToFirstPage()">     Go to First Page</button>
   

    <button type="button" class="btn btn-primary btn-font-size" onclick="goToLastPage()">
        Go to Last Page
    </button>
			
                    </div>
                </form>
                
                
                
                
                
            </div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Created Date</th>
                            <th>Action</th>
                            <th>User Name</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="audit" items="${sysAuditList}">
                            <tr>
                                <td>${audit.id}</td>
                                <td>${audit.createdDate}</td>
                                <td>${audit.action}</td>
                                <td>${audit.userName}</td>
                                <td>${audit.desciption}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
          
            </div>
        </div>
            <c:choose>
    <c:when test="${noRecords}">
        <p>No records available</p>
    </c:when>
    <c:otherwise>
        <p>Showing ${currentRecords} to ${currentRecords + sysAuditList.size() - 1} of ${totalRecords} entries</p>
        <div class="bottom-right" style="position: absolute; right: 0;">
            <ul class="pagination justify-content-center">
                <li class="page-item ${currentPage == 0 ? 'disabled' : ''}">
                    <a class="page-link"
                       href="?startDate=${startDate}&endDate=${endDate}&action=${action}&userName=${selectedUser}&page=${currentPage - 1}&size=${size}"
                       aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:set var="startPage" value="${currentPage - 2 < 0 ? 0 : currentPage - 2}" />
                <c:set var="endPage" value="${currentPage + 2 >= totalPages ? totalPages - 1 : currentPage + 2}" />
                <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}" step="1">
                    <li class="page-item ${pageNumber == currentPage ? 'active' : ''}">
                        <a class="page-link"
                           href="?startDate=${startDate}&endDate=${endDate}&action=${action}&userName=${selectedUser}&page=${pageNumber}&size=${size}">${pageNumber + 1}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${currentPage == totalPages - 1 ? 'disabled' : ''}">
                    <a class="page-link"
                       href="?startDate=${startDate}&endDate=${endDate}&action=${action}&userName=${selectedUser}&page=${currentPage + 1}&size=${size}"
                       aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </div>
    </c:otherwise>
</c:choose>
    </section>
</div>

<jsp:include page="/WEB-INF/common/Footer.jsp"></jsp:include>
<script>

function goToFirstPage() {
    const startDate = '${startDate}';
    const endDate = '${endDate}';
    const action = '${action}';
    const selectedUser = '${selectedUser}';
    const size = '${size}';
    
    window.location.href = `?startDate=${startDate}&endDate=${endDate}&action=${action}&userName=${selectedUser}&page=0&size=${size}`;
}

function goToLastPage() {
    const totalPages = ${totalPages};
    const startDate = '${startDate}';
    const endDate = '${endDate}';
    const action = '${action}';
    const selectedUser = '${selectedUser}';
    const size = '${size}';
    
    window.location.href = `?startDate=${startDate}&endDate=${endDate}&action=${action}&userName=${selectedUser}&page=${totalPages - 1}&size=${size}`;
}
</script>
