<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
$('#layout_east_datagrid').datagrid({    
    url:'',
    fit:true,
    title:'在线人数',
    columns:[[    
        {field:'nick',title:'用户名',width:30},    
        {field:'ip',title:'登录ip',width:50},    
        {field:'time',title:'登录时间',width:50}    
    ]]    
});  
</script>
<div data-options="region:'east',title:'今日信息'" style="width: 300px;">
        <div id="calendar" class="easyui-calendar" data-options="fit:true" ></div>
        <table id="layout_east_datagrid"></table>  
</div>
    