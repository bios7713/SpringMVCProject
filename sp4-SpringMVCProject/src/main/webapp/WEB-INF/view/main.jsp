<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"  uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- login 안됬을때 -->

<form:form action="login" name="frm" id="frm" method="post" commandName="loginCommand">  
<c:if test="${empty authInfo }">
	<table border =1>	
		<tr><td colspan="3">
						자동 로그인 <form:checkbox path="autoLogin"/>
				</td>
			</tr>
		<tr><td>아이디 입력</td>		
		<td>
		<form:input  id = "id1" path="id1" />
		<form:errors path="id1" /> 
		</td>
		<td> <form:checkbox path="idStore" /> 아이디 저장
		</td>	
		</tr>
		<tr><td>비밀번호</td>
		<td>
		<form:password id = "pw" path="pw"/>
		<form:errors path="pw" />
		</td>
		<td ><input type="submit" id = "btn" value='<spring:message code="login.title" />'>
		</td>
	</tr>
	<tr> <td colspan="3"> 
				<a href="#" >아이디 찾기</a> | <a href="#">비밀번호 찾기</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="register/agree">회원 가입</a>
	</td> </tr>	
	</table>
	</c:if>
</form:form>

<c:if test="${!empty authInfo }">
 <!-- 로그인 되었을때 -->
 <a href="memberDetail">내 정보</a>
 <a href="logout">로그아웃</a>
 <a href="member/memberList">회원리스트</a>
 <a href="board/boardList">공지사항 게시판</a>
 <a href="library/libraryList">일반 자료실</a>
 <a href="answer/answerList">답변형 게시판</a>
 <a href="commentBoard">댓글 게시판</a>
 <a href="goodsList">상품목록</a>
 <a href="mailForm">메일전송</a>
</c:if>
 </body>
</html>