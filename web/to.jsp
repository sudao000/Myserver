<%@ page language="java" pageEncoding="UTF-8"%>
<!-- 使用taglib指令引用gacl标签库，标签库的前缀(prefix)可以随便设置，如这里设置成 prefix="xdp" -->
<%@taglib uri="/gacl"  prefix="xdp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>输出客户端的IP</title>
</head>

<body>
你的IP地址是(使用java代码获取输出)：
<%
    //在jsp页面中使用java代码获取客户端IP地址
    String ip = request.getRemoteAddr();
    out.write(ip);
%>
<hr/>
你的IP地址是(使用自定义标签获取输出)：
<%--使用自定义标签viewIP --%>
<xdp:viewIP/>
</body>
</html>
