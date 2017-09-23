<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(function() {
		$('#user_regist_regForm').form({
			url : '${pageContext.request.contextPath }/userAction!reg',
			success : function(data) {
				var obj = $.parseJSON(data);
				if (obj.success) {
					$('#user_regist_regDialog').dialog('close');
					$.messager.show({
						title : '提示',
						msg : obj.msg,
					});
				}
			}
		});
		//添加回车注册功能
		$('#user_regist_regForm input').bind('keyup',function(event){
			if(event.keyCode=='13'){
				 $('#user_regist_regForm').submit();
			}
		});
		
	});
</script>
<div id="user_regist_regDialog" class="easyui-dialog"
	data-options="title:'注册',modal:true,closed:true,
            buttons:[{
                text:'注册',
                iconCls:'icon-save',
                handler:function(){
                $('#user_regist_regForm').submit();
             }
            }]">

	<form id="user_regist_regForm" method="post">
		<table>
			<tr>
				<td>用户名</td>
				<td><input id="username" name="username"
					class="easyui-validatebox"
					data-options="required:true,missingMessage:'用户名不能为空'"></td>
			</tr>
			<tr>
				<td>昵称</td>
				<td><input id="nick" name="nick"
					class="easyui-validatebox"
					data-options="required:true,missingMessage:'昵称不能为空'"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input id="pwd" name="password" type="password"
					class="easyui-validatebox"
					data-options="required:true,missingMessage:'密码不能为空'"></td>
			</tr>
			<tr>
				<td>重复密码</td>
				<td><input id="rpwd" type="password"
					class="easyui-validatebox" required="required" validType="equals['#pwd']"></td>
			</tr>
		</table>
	</form>
</div>