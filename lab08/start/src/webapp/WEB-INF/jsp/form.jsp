<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

Form:
<form:form method="POST"
           action="${pageContext.request.contextPath}/paybook/new"
           commandName="purchase">
    amount: <form:input path="amount"/> <br/>
    creditCardNumber: <form:input path="creditCardNumber"/> <br/>
    merchantNumber: <form:input path="merchantNumber"/> <br/>
    date: <form:input path="date"/> <br/>
    </form:form>