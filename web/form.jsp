<%--
  Created by IntelliJ IDEA.
  User: ASD
  Date: 2016/8/23
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form表单</title>
    <script type="text/javascript">
        var isCommitted = false;//表单是否已经提交标识，默认为false
        function dosubmit(){
            if(isCommitted==false){
                isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
                return true;//返回true让表单正常提交
            }else{
                return false;//返回false那么表单将不提交
            }
        }
    </script>
</head>

<body>
<form action="${pageContext.request.contextPath}/demo1" onsubmit="return dosubmit()" method="post">
    用户名：<input type="text" name="username">
    <input type="submit" value="提交" id="submit">
</form>
</body>
</html>
