<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="admin_film_editForm" method="post">
        <table>
            <tr>
                <td>ID</td>
                <td><input name="id" class="easyui-validatebox" readOnly="readOnly" style="width:200px"/></td>
            </tr>
            
            <tr>
                <td>影片名称</td>
                <td><input name="name" style="width:200px" class="easyui-validatebox" data-options="required:true,missingMessage:'名称不能为空'"></td>
            </tr>
            <tr>
                <td>上映时间</td>
                <td><input name="startTime" id="dd" type="text" class="easyui-datebox" required="required" style="width:200px"/></td>
            </tr>
             <tr>
                <td>片图</td>
                <td><input class="easyui-filebox" data-options="accept:'image/*',multiple:true" style="width:300px"></td>
            </tr>
            <tr>
                <td>描述</td>
                <td><input name="filmDescribe" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:100px"></td>
            </tr>
        </table>
    </form>
 </div>