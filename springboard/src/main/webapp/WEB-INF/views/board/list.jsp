<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!--  header.jsp include -->
<%@include file="../includes/header.jsp" %>

<!-- 아래쪽이 header 제외한 영역  -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                        <button id="regBtn" type="button" class="btn btn-xs pull-right" >Register New Board</button>
                        </div>
                        
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>수정일</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="board">
                                	<tr>
                                		<td><c:out value="${board.bno }" /></td>
                                		<td><a href='/board/get?bno=<c:out value="${board.bno}"/>'>
                                		<c:out value="${board.title}" /></a></td>
                                		<td><c:out value="${board.writer}" /></td>
                                		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
                                		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></td>
                                	</tr>	
                                </c:forEach>    
                                </tbody>
                            </table>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
            
<!--  footer.jsp include -->
<%@include file="../includes/footer.jsp" %>

<script type="text/javascript">
	$(document).ready(
			function() {
				$("#regBtn").on("click", function(){
					self.location ="/board/register";
				});
			});
</script>
 