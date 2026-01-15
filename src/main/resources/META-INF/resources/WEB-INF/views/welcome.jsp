<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>Welcome</title>
</head>
<body>
<h2>Welcome</h2>
<p>You are logged in.</p>
<form action="${pageContext.request.contextPath}/logout" method="post">
    <button type="submit">Logout</button>
</form>
</body></html>