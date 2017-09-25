<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="admin_user_editForm" method="post">
        <table>
            <tr>
                <td>ID</td>
                <td><input id="editId" name="id" class="easyui-validatebox" readOnly="readOnly"></td>
            </tr>
            <tr>
                <td>用户名</td>
                <td><input id="editUsername" name="username"class="easyui-validatebox"data-options="required:true,missingMessage:'用户名不能为空'"></td>
            </tr>
            <tr>
                <td>昵称</td>
                <td><input id="editNick" name="nick" class="easyui-validatebox"data-options="required:true,missingMessage:'昵称不能为空'"></td>
            </tr>
            <tr>
                <td>密码</td>
                <td><input id="editPassword" name="password"class="easyui-validatebox"data-options="required:true,missingMessage:'密码不能为空'"></td>
            </tr>
             <tr>
                <td>创建时间</td>
                <td><input id="editCreateTime" name="createTime"class="easyui-datetimebox"data-options="required:true"></td>
            </tr>
        </table>
    </form>
</div>