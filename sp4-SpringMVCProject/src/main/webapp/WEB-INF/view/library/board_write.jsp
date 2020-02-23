<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script language="javascript">
	function addboard(){
		libraryform.submit();
	}
	</script>
</head>
<body>
<form:form action="libraryWritePro" method="post" name="libraryform" commandName="libraryCommand">
<form:hidden path="boardNum" value=""/>
<table cellpadding="0" cellspacing="0">
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">글쓴이</div>
		</td>
		<td>
			<form:input path="boardName"  size="10" maxlength="10" 
				value=""/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<form:password path="boardPass" size="10" maxlength="10" 
				value=""/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">제 목</div>
		</td>
		<td>
			<form:input path="boardSubject"  size="50" maxlength="100" 
				value=""/>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">내 용</div>
		</td>
		<td>
			<form:textarea path="boardContent" cols="67" rows="15"></form:textarea>
		</td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td style="font-family:돋음; font-size:12"> -->
<!-- 			<div align="center">파일 첨부</div> -->
<!-- 		</td> -->
<!-- 		<td> -->
<!-- 			<input name="boardFile" type="file"  /> -->
<!-- 		</td> -->
<!-- 	</tr> -->
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">
			<a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;		
			<a href="javascript:history.go(-1)">[뒤로]</a>
		</td>
	</tr>
</table>
</form:form>
</body>
</html>