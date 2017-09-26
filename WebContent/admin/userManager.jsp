<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$('#admin_user_grid').datagrid({    
    url:'userAction!getList', 
    fit:true,
    border:false,
    pagination:true,
    fitColumns:true,
    rownumbers:true,
    checkOnSelect:false,
    selectOnCheck:false,
    toolbar: '#admin_userManager_toolbar',
    sortName:'username',
    sortOrder:'asc',
    columns:[[    
        {field:'id',title:'用户ID',width:60,checkbox:true},    
        {field:'username',title:'用户名',width:60,sortable:true},    
        {field:'password',title:'密码',width:60},
        {field:'nick',title:'昵称',width:60},
        {field:'createTime',title:'创建时间',width:60},    
        {field:'updateTime',title:'最后更新时间',width:60}
    ]]    
});

function searchUser(){
	var text = $("#admin_userManager_searchInput").val();
	if(text != ""){
		$('#admin_user_grid').datagrid('load',{
	        username: text
	    });
	}else{
		$.messager.alert('提示信息','请输入内容后再点击查询','info');
	}
	
}

function showUserDia(){
	$('#admin_userManager_addDialog').dialog('open');
}
function addUser(){
    $('#admin_userManager_regForm').form('submit',{
    	url:'${pageContext.request.contextPath }/userAction!add',
    	success:function(data){
    		var obj = $.parseJSON(data);
            if (obj.success) {
                $('#admin_userManager_addDialog').dialog('close');
                $.messager.show({
                    title : '提示',
                    msg : obj.msg,
                });
            }
    	}
    });
}

function removeUser(){
	var ids = [];
	var checks = $('#admin_user_grid').datagrid('getChecked');
	for(var i=0;i<checks.length;i++){
		ids.push(checks[i].id);
	}
	if(checks.length>0){
		$.messager.confirm('确认对话框', '您确定要删除该内容吗？', function(r){
		    if (r){
		    	$.ajax({
		            url:'${pageContext.request.contextPath}/userAction!remove',
		            type:'POST',
		            data:{ids:ids.join(',')},
		            dataType:'json',
		            success:function(data){
		                if(data.success){
		                    $('#admin_user_grid').datagrid('load');
		                    $.messager.show({
		                        title:'提示',
		                        msg:data.msg
		                    })
		                }else{
		                	$.messager.show({
		                        title:'提示',
		                        msg:data.msg
		                    })
		                }
		            }
		        });
		    }
		});
		
    }else{
    	$.messager.alert('提示信息','请至少勾选一项','info');
    }
}

function clearUser(){
	$("#admin_user_grid").datagrid('load',{});
	$("#admin_userManager_searchInput").val('');
}

function editUser(){
	row = $('#admin_user_grid').datagrid('getSelected');
	if(row!=null){
		$('#admin_user_editDialog').dialog({
			href:'${pageContext.request.contextPath}/admin/edit/editUser.jsp',
			title: '用户修改',    
		    width: 400,    
		    height: 230,
		    modal:true,
		    buttons:[{
                text:'修改',
                iconCls:'icon-ok',
                handler:function(){
                	$('#admin_user_editForm').form('submit', {    
                	    url:'${pageContext.request.contextPath}/userAction!edit',
                	    dataType:'json',
                	    success:function(data){ 
                	    	  var obj = $.parseJSON(data);
                	            if (obj.success) {
                	                $('#admin_user_editDialog').dialog('close');
                	                $.messager.show({
                	                    title : '提示',
                	                    msg : obj.msg,
                	                });
                	               $('#admin_user_grid').datagrid('reload');
                	        }else{
                	        	$('admin_user_editDialog').dialog('close');
                	        	 $.messager.show({
                	                 title : '提示',
                	                 msg : obj.msg,
                	           });
                	        }  
                	    }    
                	});  
                }
            },{
                text:'取消',
                iconCls:'icon-remove',
                handler:function(){
                	$('admin_user_editDialog').dialog('close');
                }
            }],
            onLoad:function(){
            	console.info(row.username);
            	$("#admin_user_editForm").form('load',row);
            
            },
            onClose:function(){
            	$('#admin_user_editDialog').dialog('destroy');
            }

		})
	}else{
        $.messager.alert('提示信息','请选中一行再点击编辑','info');
    }
	
}
</script>


<div id="admin_userManager_toolbar">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onClick="showUserDia()">添加</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onClick="removeUser()">删除</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onClick="editUser()">编辑</a> 
	<input id="admin_userManager_searchInput" name="username" /> 
	<a href="#"class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="searchUser()">查询</a>
	<a href="#"class="easyui-linkbutton"data-options="iconCls:'icon-undo',plain:true" onClick="clearUser()">清空</a>
</div>


<table id="admin_user_grid"></table>

<div id="admin_user_editDialog"></div>  


<div id="admin_userManager_addDialog"
	style="width: 300px; height: 250px;" class="easyui-dialog"
	data-options="title:'添加用户',modal:true,closed:true,buttons:[{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                   addUser(); 
                }
            },{text:'取消',
                iconCls:'icon-remove',
                handler:function(){
                   $('#admin_userManager_addDialog').dialog('close'); 
                }
            }]">
	<form id="admin_userManager_regForm" method="post">
		<table>
			<tr>
				<td>用户名</td>
				<td><input id="username" name="username"
					class="easyui-validatebox"
					data-options="required:true,missingMessage:'用户名不能为空'"></td>
			</tr>
			<tr>
				<td>昵称</td>
				<td><input id="nick" name="nick" class="easyui-validatebox"
					data-options="required:true,missingMessage:'昵称不能为空'"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input id="admin_user_pwd" name="password" type="password"
					class="easyui-validatebox"
					data-options="required:true,missingMessage:'密码不能为空'"></td>
			</tr>
			<tr>
				<td>重复密码</td>
				<td><input type="password" class="easyui-validatebox" required="required" validType="equals['#admin_user_pwd']"></td>
			</tr>
		</table>
	</form>
</div>

