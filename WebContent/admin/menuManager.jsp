<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

$('#admin_menu_treegrid').treegrid({    
    url:'${pageContext.request.contextPath}/menuAction!getMenu',    
    idField:'id',    
    treeField:'name',    
    columns:[[    
        {title:'id',field:'ID',width:180},    
        {field:'text',title:'名称',width:60,align:'right'},    
        {field:'iconCls',title:'图标',width:80},    
        {field:'url',title:'目标地址',width:80}    
    ]]    
});  


</script>  
    
<table id="admin_menu_treegrid" style="width:600px;height:400px"></table>  

    