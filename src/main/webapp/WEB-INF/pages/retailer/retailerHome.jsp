<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
  <head>
    <title>零售商管理</title>
    <style>
        *{margin:0; padding:0;} #menuContent a{text-decoration:none;color:#ffffff}
        .c{
          border-style: solid;width: 200px;height: 130px;
          margin: 4 23 0 23;border-radius:5;display:block;
          background:#fff;
          margin:10% auto;
        }
        .mask,.addMask{
        	width:100%;
        	height:100%;
        	position: absolute;
        	background:rgba(0,0,0,.3);
        	display: none;
        }
    </style>
        
    <script type="text/javascript" 
  		src="/js/jquery-1.4.4.min.js"></script>
	  <script type="text/javascript">
		  function showAddMask(flag) {
		      if(flag=="true"){
		          $(".addMask").css("display","block");
			  }else{
                  $(".addMask").css("display","none");
			  }
          }
          function checkAddRetailer() {
			  if($("addName").val()==null||$("addName").val()==""){alert("用户名不能为空");
			  return false;
			  }
              if($("addTelephone").val()==null||$("addTelephone").val()==""){alert("手机号不能为空");
                  return false;
              }
              var myreg=/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
              if(!myreg.test($("#addTelphone").val()))
			  {
                  alert("请输入有效的电话号码");
                  return false;
			  }
              if($("addAddress").val()==null||$("addAddress").val()==""){alert("地址不能为空");
                  return false;
              }
          }
	  </script>
  </head>
  <body>
      <%@ include file="../menu.jsp" %><br/>
   <div class="addMask">
	   <div class="c">
	     <div style="background-color:#173e65;height:20px;color:#fff;font-size:12px;padding-left:7px;">
	     	添加信息<font style="float:right;padding-right: 10px;" onclick="showAddMask('false')">x</font>
	     </div>
	     <form id="addForm" action="add.action" method="post" onsubmit="checkAddRetailer()">
		        姓名：<input type="text" id="addName" name="name" style="width:120px"/> <br/>
		        手机：<input type="text" id="addTelphone" name="telphone" style="width:120px"/><br/>
		        地址：<input type="text" id="addAddress" name="address" style="width:120px"/><br/>
		     <input type="hidden" name="status" value="1"/>
		     <input type="submit" value="添加" style="background-color:#173e65;color:#ffffff;width:70px;"/>
	     </form>
	    </div>
   </div>
   <div class="mask">
	   <div class="c">
	     <div style="background-color:#173e65;height:20px;color:#fff;font-size:12px;padding-left:7px;">
	     	修改信息<font style="float:right;padding-right: 10px;" onclick="cancelEdit()">x</font>
	     </div>
	     <form id="editForm" action="edit.action" method="post">
		        姓名：<input type="text" id="editName" name="name" style="width:120px"/> <br/>
		        手机：<input type="text" id="editTelphone" name="telphone" style="width:120px"/><br/>
		        地址：<input type="text" id="editAddress" name="address" style="width:120px"/><br/>
		        状态：<select id="eStatus" onchange="changeEditStatus()">
		        <option value="1">启用</option>
		        <option value="0">停用</option>
		     </select><br/>
		     <input type="hidden" name="retailerId" id="retailerId"/>
		     <input type="hidden" name="status" id="editStatus"/>
		     <input type="hidden" name="startPage" id="eStartPage"/>
			 <input type="hidden" name="currentPage" id="eCurrentPage"/>
			 <input type="hidden" name="pageSize" id="ePageSize"/>
		     <input type="submit" value="提交" style="background-color:#173e65;color:#ffffff;width:70px;"/>
	     </form>
	    </div>
  </div>
  <form id="listForm" action="list.action" method="post">
        姓名：<input type="text" name="name" style="width:120px"/> 
        手机：<input type="text" name="telphone" style="width:120px"/>
        地址：<input type="text" name="address" style="width:120px"/><br/><br/>
        状态：<select id="indexStatus" onchange="changeStatus()">
        <option value="-1" selected="selected">全部</option>
        <option value="1">启用</option>
        <option value="0">停用</option>
     </select>
     <input type="hidden" name="status" id="status" value="-1">
        创建日期：<input type="text" name="createTime"/>
     <input type="submit" value="搜索" style="background-color:#173e65;color:#ffffff;width:70px;"/> <br/>
     <!-- 显示错误信息 -->  
	 <c:if test="${errorMsg}">   
	     <font color="red">${errorMsg}</font><br/>
	 </c:if> 
	 <input type="hidden" name="startPage" id="startPage" value="${startPage}"/>
	 <input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
	 <input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
	 <input type="hidden" name="sumPageNumber" id="sumPageNumber" value="${sumPageNumber}"/>
	 <input type="hidden" name="countNumber" id="countNumber" value="${countNumber}"/>
  </form>
  <hr style="margin-top: 10px;"/> 
  <button onclick="showAddMask('true')" style="background-color:#173e65;color:#ffffff;width:70px;">添加</button>
  <c:if test="${list!=null}">
	  <table style="margin-top: 10px;width:700px;text-align:center;" border=1>  
	    <tr>  
	      <td>序号</td><td>姓名</td><td>手机号</td><td>地址</td>
	      <td>状态</td><td>创建日期</td><td>操作</td>
	   </tr>  
      <c:forEach items="${list}" var="item" varStatus="status">  
	     <tr>  
	       <td>${status.index+1}</td><td>${item.name }</td>
	       <td>${item.telphone}</td><td>${item.address }</td>  
	       <td>
	           <c:if test="${item.status==1}">
	               <font color="blue">启用</font>
	           </c:if>
	           <c:if test="${item.status==0}">
	               <font color="red">停用</font>
	           </c:if>
	       </td>
	       <td>${item.createTime}</td>
	       <td>
	       		<a>编辑</a>|
	       		<a>删除</a>
	       		<form id="deleteForm" action="delete.action" method="post">
	       		   <input type="hidden" name="retailerId" id="dRetailerId"/>
	       		   <input type="hidden" name="startPage" id="dStartPage"/>
			       <input type="hidden" name="currentPage" id="dCurrentPage"/>
			       <input type="hidden" name="pageSize" id="dPageSize"/>
	       		</form>
	       </td>
	     </tr>  
	    </c:forEach>  
	    </table> 
   </c:if>
   <c:if test="${list==null}">
       <b>搜索结果为空！</b>
   </c:if>
   <div style="margin-top: 10px;">
       <a onclick="toPrePage()">上一页</a><a onclick="toNextPage()">下一页</a>
       <input type="text" id="pageNumber" style="width:50px">
       <button onclick="toLocationPage()">go</button>
       <div id="pageInfo"></div>
   </div>
  </body>
</html>
