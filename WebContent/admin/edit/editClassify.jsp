<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="admin_classify_editForm" method="post">
        <table>
             <tr>
                <td>ID</td>
                <td><input name="id"class="easyui-validatebox" readOnly="readOnly"></td>
            </tr>
            <tr>
                <td>分类名称</td>
                <td><input name="text"class="easyui-validatebox" data-options="required:true,missingMessage:'名称不能为空'"></td>
            </tr>
        </table>
</form>