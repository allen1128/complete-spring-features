<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function onDeleteClick(event) {
		var doDelete = confirm("Are you aure you want to delete this offer?");
		if (doDelete == false) {
			event.preventDefault();
		}
	}

	function onReady() {
		$("#delete").click(onDeleteClick);
	}

	$(document).ready(onReady);	
</script>

<sf:form method="POST"
	action="${pageContext.request.contextPath}/docreate"
	commandName="offer">
	<sf:input name="id" path="id" type="hidden" />
	<table class="formtable">
		<tr>
			<td class="label">Your offer:</td>
			<td><sf:textarea class="control" path="text" rows="10" cols="10"></sf:textarea><br />
				<sf:errors path="text" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td></td>
			<td><input class="control" value="Save offer" type="submit" /></td>
		</tr>
		<tr>
			<td></td>
			<td>&nbsp;</td>
		</tr>
		<c:if test="${offer.id != 0}">
			<tr>
				<td></td>
				<td><input class="delete control" name="delete" id="delete"
					value="Delete this offer." type="submit" /></td>
			</tr>
		</c:if>
	</table>
</sf:form>