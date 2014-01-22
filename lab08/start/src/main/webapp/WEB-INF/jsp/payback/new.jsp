<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

Form:<br/>
<form:form method="POST"
           action="${pageContext.request.contextPath}/payback/confirm"
           commandName="purchaseForm">
    creditCardNumber: <form:input path="creditCardNumber"/> <br/>
    merchantNumber: <form:input path="merchantNumber"/> <br/>
    <input type="submit">
    </form:form>