<%@ page pageEncoding="UTF-8"%>
<%
	String sessionName 	= (String)session.getAttribute("session_name");
	String sessionLevel = (String)session.getAttribute("session_level");
	String sessionId 	= (String)session.getAttribute("session_id");
	if(sessionName == null) sessionName="";
	if(sessionLevel == null) sessionLevel="";
	if(sessionId == null) sessionId="";
%>
