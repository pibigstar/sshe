<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
   function repair(){
	   $.ajax({
		   url:'${pageContext.request.contextPath}/repairAction!repair',
		   dataType:'json',
		   success:function(data){
			   var d = $.parseJSON(data);
			   if(d.success){
				   $.messager.show({
	                    title : '提示',
	                    msg : d.msg,
	              });
			   }
		   }
	   });
	   
	   
   }
</script>
<!-- 中间部分 -->

<div id="layout_center_layout"
	data-options="region:'center',border:false"
	style="padding: 2px; background: #eee;">
	
	<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false">
		<div title="首页" style="padding: 20px; display: none;">
		
		     <div id="index_about_panel" class="easyui-panel" title="关于" style="width:1370px;height:360px;padding:3px;background:#fafafa;"   
                data-options="iconCls:'icon-help',closable:true">   
            <p>SSHE</p>   
            <p>使用技术：</p>
            <ul>
                <li>struts2框架技术</li>
                <li>spring依赖注入</li>
                <li>hibernate框架</li>
                <li>前台easyui框架技术</li>
                <li>工厂模式使用</li>
                <li>泛型使用技术</li>
                <li>数据库外键应用</li>
            </ul>
            </div>
            <br>
            
            <div id="index_repair_panel" class="easyui-panel" title="修复"style="width:1370px;height:390px;padding:3px;background:#fafafa;"   
                data-options="iconCls:'icon-back',closable:true">   
            <p><a href="#" onClick="repair()">修复数据库</a></p>   
            </div>  
		</div>
	</div>

</div>