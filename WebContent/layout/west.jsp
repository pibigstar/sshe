<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
        //增加选项卡
        function addTab(opts){
            var t = $('#layout_center_tabs');
            console.info(opts.href);
            if(opts.href!=undefined){
                if(t.tabs('exists',opts.title)){
                    t.tabs('select',opts.title)
                 }else{
                     t.tabs('add',opts)
                 }
            }
        }
        //更换皮肤
        function changeThemeFun(themeName) {
            var easyuiTheme = $('#easyuiTheme');
            var url = easyuiTheme.attr('href');
            var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
            easyuiTheme.attr('href', href);
            console.info(href);
            $.cookie('easyuiThemeName', themeName, {
                expires : 7
            });
        };
        
        //注销登录
        function userQuit(){
        	$.ajax({
        		url:'${pageContext.request.contextPath}/userAction!quit',
        		success:function(){
        			window.location.reload();
        		}
        	})
        }
</script>
<div id="index_tree" data-options="region:'west',title:'功能导航'"style="width: 200px;">

    <div id="layout_west_accordion" class="easyui-accordion" data-options="fit:true,border:false" style="width:300px;height:200px;">   
        <div title="功能菜单" data-options="iconCls:'icon-save'">
            <ul id="layout_west_tree" class="easyui-tree" data-options="url:'${pageContext.request.contextPath }/menuAction!getMenu',
            onClick: function(node){addTab({title:node.text,closable : true,href:node.url})},lines : true">
            </ul>  
        </div>
        
        
        <div title="其他菜单" data-options="iconCls:'icon-reload'">
            <ul id="tt" class="easyui-tree">   
                <li>   
                    <span>更换皮肤</span>   
                    <ul>   
                        <li><a onclick="changeThemeFun('default');">default</a></li>
                        <li><a onclick="changeThemeFun('gray');">gray</a></li>
                        <li><a onclick="changeThemeFun('metro');">metro</a></li>
                        <li><a onclick="changeThemeFun('bootstrap');">bootstrap</a></li>
                        <li><a onclick="changeThemeFun('black');">black</a></li>
                        <li><a onclick="changeThemeFun('cupertino');">cupertino</a></li>
                        <li><a onclick="changeThemeFun('dark-hive');">dark-hive</a></li>
                        <li><a onclick="changeThemeFun('sunny');" >sunny</a></li>
                        <li><a onclick="changeThemeFun('pepper-grinder');">pepper-grinder</a></li>
                    </ul> 
               </li> 
               <li>
                    <span>用户注销</span>
                    <ul>
                        <li><a onClick="userQuit()">注销</a></li>
                    </ul>
               
               </li>     
            </ul> 
        </div> 
    </div>  
</div>