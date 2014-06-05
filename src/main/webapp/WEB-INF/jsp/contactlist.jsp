<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Address Book</title>
	<link  rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>">
  <script src="<c:url value="/resources/js/jquery-1.9.0.min.js"/>"></script>
  <script src="<c:url value="/resources/js/jquery-ui.min.js"/>"></script>
	<style type="text/css">
		body {
			font-family: sans-serif;
		}
		.data, .data td {
			border-collapse: collapse;
			border: 1px solid #aaa;
			margin: 2px;
			padding: 2px;
		}
		.data th {
			font-weight: bold;
			background-color: #5C82FF;
			color: white;
		}
	</style>
	<script>
	$(function() {
			$( "#searchName" ).autocomplete({
		      source: function( request, response ) {
					$.ajax({
						url: "${pageContext.request.contextPath}/search",
						success: function( data ) {
							response( $.map( data.searchContacts, function( item ) {
								return {
									value: item.name, data: item.id,
									label: item.name
								}
							}));
						}						
					});
				},
				select: function(event, ui) { 
				    $("#searchName").val(ui.item.value);
				    $("#searchContactId").val(ui.item.data); 
				    ///alert(ui.item.data+document.getElementById("searchContactId").value);
				   },
				minLength: 1,
				open: function() {
					$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
				},
				close: function() {
					$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
				}
		    });
	});
	</script>
</head>
<body>

<h2>Address Book</h2>

<form:form method="post" action="add.html" commandName="contact">
	<form:input type="hidden" path="id" />
	<table>
	<tr>
		<td><label for="name">Name</label></td>
		<td><form:input path="name" /></td> 
	</tr>
	<tr>
		<td><label for="address">Address</label></td>
		<td><form:input path="address" /></td>
	</tr>
	<tr>
		<td><label for="phone">Phone</label></td>
		<td><form:input path="phonenumber" /></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="Save Contact"/>
		</td>
	</tr>
</table>	
</form:form>
<h3>Search Contact</h3>

<div class="ui-widget">
  <label for="searchName">Name: </label>
  <input id="searchName">
  
</div>
<form:form method="post" action="edit.html">
	<input type="hidden" name="searchContactId" id="searchContactId" />
  <input type="submit" value="Edit Contact"/>
</form:form>
	
<h3>Contacts</h3>
<c:if  test="${!empty contactList}">
<table class="data">
<tr>
	<th>Name</th>
	<th>Address</th>
	<th>Telephone</th>
	<th>&nbsp;</th>
</tr>
<c:forEach items="${contactList}" var="contact">
	<tr>
		<td>${contact.name} </td>
		<td>${contact.address}</td>
		<td>${contact.phonenumber}</td>
		<td><a href="delete/${contact.id}">delete</a></td>
	</tr>
</c:forEach>
</table>
</c:if>


</body>
</html>
