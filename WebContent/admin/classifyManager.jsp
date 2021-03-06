<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$('#admin_classify_grid').datagrid({    
    url:'classifyAction!getList', 
    fit:true,
    pagination:true,
    fitColumns:true,
    rownumbers:true,
    checkOnSelect:false,
    selectOnCheck:false,
    toolbar: '#admin_classify_toolbar',
    sortName:'text',
    sortOrder:'asc',
    columns:[[    
        {field:'id',title:'ID',width:60,checkbox:true},    
        {field:'text',title:'影片分类',width:60,sortable:true},    
        {field:'createTime',title:'创建时间',width:60},    
        {field:'updateTime',title:'最后更新时间',width:60}
    ]]    
});

function searchClassify(){
    var text = $("#admin_classify_searchInput").val();
    if(text != ""){
        $('#admin_classify_grid').datagrid('load',{
            text: text
        });
    }else{
        $.messager.alert('提示信息','请输入内容后再点击查询','info');
    }
}

function showClassDia(){
    $('#admin_classify_addDialog').dialog('open');
}
function addClassify(){
    $('#admin_classify_regForm').form('submit',{
        
        url:'${pageContext.request.contextPath }/classifyAction!add',
        success:function(data){
            var obj = $.parseJSON(data);
            if (obj.success) {
                $('#admin_classify_addDialog').dialog('close');
                $.messager.show({
                    title : '提示',
                    msg : obj.msg,
                });
            }
        }
    });
}

function removeClassify(){
    var ids = [];
    var checks = $('#admin_classify_grid').datagrid('getChecked');
    for(var i=0;i<checks.length;i++){
        ids.push(checks[i].id);
    }
    console.info(ids.join(','));
    if(ids.length>0){
        $.messager.confirm('确认对话框', '您确定要删除该内容吗？', function(r){
            if (r){
                $.ajax({
                    url:'${pageContext.request.contextPath}/classifyAction!remove',
                    type:'POST',
                    data:{ids:ids.join(',')},
                    dataType:'json',
                    success:function(data){
                        if(data.success){
                            $('#admin_classify_grid').datagrid('load');
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

function clearClassify(){
    $("#admin_classify_grid").datagrid('load',{});
    $("#admin_classify_searchInput").val('');
}

function editClassify(){
    row = $('#admin_classify_grid').datagrid('getSelected');
    console.info(row);
    if(row!=null){
        $('#admin_classify_editDialog').dialog({
            href:'${pageContext.request.contextPath}/admin/edit/editClassify.jsp',
            title: '分类修改',    
            width: 300,    
            height: 250,
            modal:true,
            buttons:[{
                text:'修改',
                iconCls:'icon-ok',
                handler:function(){
                    $('#admin_classify_editForm').form('submit', {    
                        url:'${pageContext.request.contextPath}/classifyAction!edit',
                        dataType:'json',
                        success:function(data){ 
                              var obj = $.parseJSON(data);
                                if (obj.success) {
                                    $('#admin_classify_editDialog').dialog('close');
                                    $.messager.show({
                                        title : '提示',
                                        msg : obj.msg,
                                    });
                                    $('#admin_classify_grid').datagrid('reload');
                            }else{
                                $('#admin_classify_editDialog').dialog('close');
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
                    $('#admin_classify_editDialog').dialog('close');
                }
            }],
            onLoad:function(){
                console.info(row.username);
                $("#admin_classify_editForm").form('load',row);
            
            }
        })
    }else{
        $.messager.alert('提示信息','请选中一行再点击编辑','info');
    }
    
}


</script>


<div id="admin_classify_toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onClick="showClassDia()">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onClick="removeClassify()">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onClick="editClassify()">编辑</a>
    <input id="admin_classify_searchInput" name="text"/>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onClick="searchClassify()">查询</a>
        <a href="#"class="easyui-linkbutton"data-options="iconCls:'icon-undo',plain:true" onClick="clearClassify()">清空</a>
</div>

<table id="admin_classify_grid"></table>
<div id="admin_classify_editDialog"></div>
 
 <div id="admin_classify_addDialog" style="width:250px; height:150px;" class="easyui-dialog" data-options="title:'添加分类',modal:true,closed:true,buttons:[{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                   addClassify(); 
                }
            },{text:'取消',
                iconCls:'icon-remove',
                handler:function(){
                   $('#admin_classify_addDialog').dialog('close'); 
                }
            }]">
   <form id="admin_classify_regForm" method="post">
        <table>
            <tr>
            <tr>
                <td>分类名称</td>
                <td><input name="text"class="easyui-validatebox" data-options="required:true,missingMessage:'名称不能为空'"></td>
            </tr>
         
        </table>
    </form>
 </div>
    