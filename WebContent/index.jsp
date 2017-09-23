<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script src="js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script src="js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
<link id="easyuiTheme" rel="stylesheet" href="js/jquery-easyui-1.5.2/themes/<c:out value='${cookie.easyuiThemeName.value }' default='sunny'/>/easyui.css">
<link rel="stylesheet" href="js/jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" href="css/index.css">
<script type="text/javascript" src="js/my/extends.js"></script>
<script type="text/javascript" src="js/my/jquery.cookie.js"></script>
<link rel="shortcut icon" href="favicon.ico" />
<title>主页</title>
<script type="text/javascript">

</script>
</head>
<body class="easyui-layout">
	<!-- 顶部部分 -->
	<jsp:include page="layout/head.jsp"></jsp:include>
	<!-- 左边部分 -->
	<jsp:include page="layout/west.jsp"></jsp:include>
	<!-- 右边部分 -->
	<jsp:include page="layout/east.jsp"></jsp:include>
	<!-- 中间部分 -->
	<jsp:include page="layout/center.jsp"></jsp:include>
	<!-- 底部部分 -->
	<jsp:include page="layout/tail.jsp"></jsp:include>
    <c:if test="${sessionInfo.id==null }">
        <jsp:include page="back/login.jsp"></jsp:include>
    </c:if>
	<!-- 登录注册框 -->
	<jsp:include page="back/regist.jsp"></jsp:include>
	
</body>
</html>