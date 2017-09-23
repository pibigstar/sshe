<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div data-options="region:'north'," style="height: 60px;">
        <h1>SSH电影后台管理系统</h1>
        <div id="sessionInfoDiv" style="position: absolute; right: 0px; bottom: 5px;" class="alert alert-info">
            <c:if test="${sessionInfo.id != null}">[<strong>${sessionInfo.name}</strong>]，欢迎你！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！</c:if>
        </div>
</div>

