<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">

$(function() {
    $('#user_login_loginForm').form({
        url : '${pageContext.request.contextPath }/userAction!login',
        success : function(data) {
            var obj = $.parseJSON(data);
            if (obj.success) {
                $('#user_login_loginDialog').dialog('close');
                $.messager.show({
                    title : '提示',
                    msg : obj.msg,
                    timeout:3000,
                });
                window.location.reload();
            }
        }
    });
    //添加回车注册功能
    $('#user_login_loginForm input').bind('keyup',function(event){
        if(event.keyCode=='13'){
             $('#user_login_loginForm').submit();
        }
    });
    //登录文本框聚焦
    $('#user_login_loginForm input[name=username]').focus();
    
});


</script>
<div id="user_login_loginDialog" class="easyui-dialog"
	data-options="title:'登录',modal:true,closable:false,
            buttons:[{
                text:'登录',
                iconCls:'icon-ok',
                handler:function(){
                   $('#user_login_loginForm').submit();
             }
            },{
                text:'注册',
                iconCls:'icon-add',
                handler:function(){
                    $('#user_regist_regForm input').val('');
                    $('#user_regist_regDialog').dialog('open')
                }
            }]">
	<form id="user_login_loginForm" method="post">

		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" name="username"
					class="easyui-validatebox" data-options="required:true,missingMessage:'用户名不能为空'"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password"
					class="easyui-validatebox" data-options="required:true,missingMessage:'密码不能为空'"></td>
			</tr>
		</table>
	</form>
</div>