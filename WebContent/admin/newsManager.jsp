<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$('#admin_news_grid').datagrid({    
    url:'newsAction!getList', 
    fit:true,
    border:false,
    pagination:true,
    fitColumns:true,
    rownumbers:true,
    checkOnSelect:false,
    selectOnCheck:false,
    toolbar: '#admin_newsManager_toolbar',
    sortName:'title',
    sortOrder:'asc',
    columns:[[    
        {field:'id',title:'新闻ID',width:60,checkbox:true},    
        {field:'title',title:'标题',width:60,sortable:true},    
        {field:'intro',title:'简介',width:60},
        {field:'text',title:'内容',width:60},
        {field:'createTime',title:'创建时间',width:60},    
        {field:'updateTime',title:'最后更新时间',width:60}
    ]]    
});

function searchNews(){
	var text = $("#admin_newsManager_searchInput").val();
    if(text != ""){
        $('#admin_news_grid').datagrid('load',{
            title: text
        });
    }else{
        $.messager.alert('提示信息','请输入内容后再点击查询','info');
    }
}

function showNewsDia(){
    $('#admin_newsManager_addDialog').dialog('open');
}
function addNews(){
    $('#admin_newsManager_regForm').form('submit',{
        
        url:'${pageContext.request.contextPath }/newsAction!add',
        success:function(data){
            var obj = $.parseJSON(data);
            if (obj.success) {
                $('#admin_newsManager_addDialog').dialog('close');
                $('#admin_news_grid').datagrid('load',{});
                $.messager.show({
                    title : '提示',
                    msg : obj.msg,
                });
            }
        }
    });
}

function removeNews(){
    var ids = [];
    var checks = $('#admin_news_grid').datagrid('getChecked');
    for(var i=0;i<checks.length;i++){
        ids.push(checks[i].id);
    }
    console.info(ids.join(','));
    if(ids.length>0){
        $.messager.confirm('确认对话框', '您确定要删除该内容吗？', function(r){
            if (r){
                $.ajax({
                    url:'${pageContext.request.contextPath}/newsAction!remove',
                    type:'POST',
                    data:{ids:ids.join(',')},
                    dataType:'json',
                    success:function(data){
                        if(data.success){
                            $('#admin_news_grid').datagrid('reload');
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
function clearNews(){
    $("#admin_news_grid").datagrid('load',{});
    $("#admin_newsManager_searchInput").val('');
}

function editNews(){
    row = $('#admin_news_grid').datagrid('getSelected');
    if(row!=null){
        $('#admin_news_editDialog').dialog({
            href:'${pageContext.request.contextPath}/admin/edit/editNews.jsp',
            title: '新闻修改',    
            width: 350,    
            height: 400,
            modal:true,
            buttons:[{
                text:'修改',
                iconCls:'icon-ok',
                handler:function(){
                    $('#admin_news_editForm').form('submit', {    
                        url:'${pageContext.request.contextPath}/newsAction!edit',
                        dataType:'json',
                        success:function(data){ 
                              var obj = $.parseJSON(data);
                                if (obj.success) {
                                    $('#admin_news_editDialog').dialog('close');
                                    $.messager.show({
                                        title : '提示',
                                        msg : obj.msg,
                                    });
                                    $('#admin_news_grid').datagrid('reload');
                            }else{
                                $('#admin_news_editDialog').dialog('close');
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
                    $('#admin_news_editDialog').dialog('close');
                }
            }],
            onLoad:function(){
                console.info(row.username);
                $("#admin_news_editForm").form('load',row);
            
            }
        })
    }else{
        $.messager.alert('提示信息','请选中一行再点击编辑','info');
    }
    
}
</script>


<div id="admin_newsManager_toolbar">
    <a href="#" class="easyui-linkbutton"data-options="iconCls:'icon-add',plain:true" onClick="showNewsDia()">添加</a>
    <a href="#" class="easyui-linkbutton"data-options="iconCls:'icon-remove',plain:true" onClick="removeNews()">删除</a>
    <a href="#" class="easyui-linkbutton"data-options="iconCls:'icon-edit',plain:true" onClick="editNews()">编辑</a> 
    <input id="admin_newsManager_searchInput" name="username" /> 
    <a href="#"class="easyui-linkbutton"data-options="iconCls:'icon-search',plain:true" onClick="searchNews()">查询</a>
    <a href="#"class="easyui-linkbutton"data-options="iconCls:'icon-undo',plain:true" onClick="clearNews()">清空</a>
</div>

<table id="admin_news_grid"></table>

<div id="admin_news_editDialog"></div>  

<div id="admin_newsManager_addDialog"
    style="width: 300px; height: 250px;" class="easyui-dialog"
    data-options="title:'添加用户',modal:true,closed:true,buttons:[{
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                   addNews(); 
                }
            },{text:'取消',
                iconCls:'icon-remove',
                handler:function(){
                   $('#admin_newsManager_addDialog').dialog('close'); 
                }
            }]">
    <form id="admin_newsManager_regForm" method="post">
        <table>
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
</div>