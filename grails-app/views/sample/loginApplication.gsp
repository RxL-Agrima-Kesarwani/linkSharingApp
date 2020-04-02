<html>
<head>
<title>Welcome to Grails</title>
<meta name="layout" content="main" />
<style type="text/css" media="screen">
#login{
background-color:darkred;
color:white;
text-align:right;
padding:5px 15px;
height:25px;
}
#login a{
color:white;
}
</style>
</head>
<body>
<div id="login">
<g:if test="${session.user}">
<g:if test="${flash.message}">Welcome back ${flash.message}</g:if>
<g:else>Logged in as <B>${session.user}</B></g:else>
 | <g:link action="logout">Logout</g:link>
</g:if>
<g:else>
<g:form controller="useraccount" action="login">
<label for="name">Username</label>
<input type="text" name="username"/>
<label for="password">Password</label>
<input type="password" name="password"/>
<input type="submit" value="Login"/>
</g:form>
</g:else>
</div>
</body>
</html>
