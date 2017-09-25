<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <form id="admin_news_editForm" method="post">
        <table>
            <tr>
                <td>ID</td>
                <td><input name="id"class="easyui-validatebox" readOnly="readOnly"></td>
            </tr>
            <tr>
                <td>标题</td>
                <td><input name="title"class="easyui-validatebox"data-options="required:true,missingMessage:'用户名不能为空'"></td>
            </tr>
            <tr>
                <td>简介</td>
                <td><input name="intro" class="easyui-validatebox"
                    data-options="required:true,missingMessage:'昵称不能为空'"></td>
            </tr>
            <tr>
                <td>内容</td>
                <td><input name="text" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:100px"></td>
            </tr>
        </table>
    </form>