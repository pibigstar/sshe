<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$('#admin_film_grid').datagrid({    
    url:'filmAction!getList', 
    fit:true,
    border:false,
    pagination:true,
    fitColumns:true,
    rownumbers:true,
    checkOnSelect:false,
    selectOnCheck:false,
    toolbar: '#admin_film_toolbar',
    sortName:'name',
    sortOrder:'asc',
    columns:[[    
        {field:'id',title:'影片ID',width:60,checkbox:true},
        {field:'img',title:'片图',width:60,formatter:function(value,row){
            var str = "";
            if(value!="" || value!=null){
            str = "<img style=\"height: 80px;width: 150px;\" src=\""+value+"\"/>";
                  return str;
            }}},
        {field:'name',title:'影片名称',width:60,sortable:true},    
        {field:'filmDescribe',title:'描述',width:60},
        {field:'classifyText',title:'分类',width:60},
        {field:'startTime',title:'上映时间',width:60},
        {field:'createTime',title:'创建时间',width:60},    
        {field:'updateTime',title:'最后更新时间',width:60},
        {field:'url',title:'下载链接',width:60}
    ]]    
});


$('#admin_film_combobox').combobox({    
    url:'${pageContext.request.contextPath}/classifyAction!getAll',    
    valueField:'text',    
    textField:'text',
    
}); 



function searchFilm(){
	var text = $("#admin_film_searchInput").val();
    if(text != ""){
        $('#admin_film_grid').datagrid('load',{
            name: text
        });
    }else{
        $.messager.alert('提示信息','请输入内容后再点击查询','info');
    }
}

function showFilmDia(){
    $('#admin_film_addDialog').dialog('open');
}


function addFilm(){
    $('#admin_film_regForm').form('submit',{
        
        url:'${pageContext.request.contextPath }/fileAction',
        success:function(data){
            var obj = $.parseJSON(data);
            if (obj.success) {
                $('#admin_film_addDialog').dialog('close');
                $('#admin_film_grid').datagrid('load',{});
                $.messager.show({
                    title : '提示',
                    msg : obj.msg,
                });
            }
        }
    });
}

function removeFilm(){
    var ids = [];
    var checks = $('#admin_film_grid').datagrid('getChecked');
    for(var i=0;i<checks.length;i++){
        ids.push(checks[i].id);
    }
    console.info(ids.join(','));
    if(ids.length>0){
        $.messager.confirm('确认对话框', '您确定要删除该内容吗？', function(r){
            if (r){
                $.ajax({
                    url:'${pageContext.request.contextPath}/filmAction!remove',
                    type:'POST',
                    data:{ids:ids.join(',')},
                    dataType:'json',
                    success:function(d){
                        if(d.success){
                            $('#admin_film_grid').datagrid('reload');
                            $.messager.show({
                                title:'提示',
                                msg:d.msg
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

function clearFilm(){
    $("#admin_film_grid").datagrid('load',{});
    $("#admin_film_searchInput").val('');
}

function editFilm(){
    row = $('#admin_film_grid').datagrid('getSelected');
    if(row!=null){
        $('#admin_film_editDialog').dialog({
            href:'${pageContext.request.contextPath}/admin/edit/editFilm.jsp',
            title: '电影修改',    
            width: 400,    
            height: 300,
            modal:true,
            buttons:[{
                text:'修改',
                iconCls:'icon-ok',
                handler:function(){
                    $('#admin_film_editForm').form('submit', {    
                        url:'${pageContext.request.contextPath}/filmAction!edit',
                        dataType:'json',
                        success:function(data){ 
                              var obj = $.parseJSON(data);
                                if (obj.success) {
                                    $('#admin_film_editDialog').dialog('close');
                                    $.messager.show({
                                        title : '提示',
                                        msg : obj.msg,
                                    });
                                    $('#admin_film_grid').datagrid('reload');
                            }else{
                                $('#admin_film_editDialog').dialog('close');
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
                    $('#admin_film_editDialog').dialog('close');
                }
            }],
            onLoad:function(){
                $("#admin_film_editForm").form('load',row);
            }
        });
    }else{
        $.messager.alert('提示信息','请选中一行再点击编辑','info');
    }
    
}
</script>


<div id="admin_film_toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onClick="showFilmDia()">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onClick="removeFilm()">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onClick="editFilm()">编辑</a>
    <input id="admin_film_searchInput" name="name"/>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="searchFilm()">查询</a>
    <a href="#"class="easyui-linkbutton"data-options="iconCls:'icon-undo',plain:true" onClick="clearFilm()">清空</a>
</div>

<table id="admin_film_grid"></table>

<div id="admin_film_editDialog"></div>
 
 <div id="admin_film_addDialog" style="width:400px; height:300px;" class="easyui-dialog" data-options="title:'新增影片',modal:true,closed:true,buttons:[{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                   addFilm(); 
                }
            },{text:'取消',
                iconCls:'icon-remove',
                handler:function(){
                   $('#admin_film_addDialog').dialog('close'); 
                }
            }]">
   <form id="admin_film_regForm" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>影片名称</td>
                <td>
                    <input name="name" style="width:200px"class="easyui-validatebox" data-options="required:true,missingMessage:'名称不能为空'">
                </td>
            </tr>
            <tr>
                <td>分类</td>
                <td><input id="admin_film_combobox" name="classifyText" value="请选择分类" style="width:200px" /> </td>
            </tr>
            <tr>
                <td>上映时间</td>
                <td><input name="startTime" class="easyui-datebox" required="required" style="width:200px"/></td>
            </tr>
             <tr>
                <td>片图</td>
                <td><input name="upload"  class="easyui-filebox" data-options="accept:'image/*',multiple:true" style="width:300px"></td>
            </tr>
            <tr>
                <td>描述</td>
                <td><input name="filmDescribe" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:100px"></td>
            </tr>
        </table>
    </form>
 </div>
    