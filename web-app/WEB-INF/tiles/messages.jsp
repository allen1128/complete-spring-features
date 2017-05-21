<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="messages"></div>


<script type="text/javascript">
	var timer;

	function showReply(i) {
		$("#form" + i).toggle();
		if ($( "form:visible" ).size() == 0){
			startTimer();
		} else {
			stopTimer();
		}
	}
	
	function success(data){
		$("#form" + data.target).toggle();
		$("#alert" + data.target).text("Message sent.");
		if ($( "form:visible" ).size() == 0){
			startTimer();
		} else {
			stopTimer();
		}
	}
	
	function error(data){
		alert("error");
	}
	
	function sendReply(i, name, email){
		$.ajax({
			type: 'POST',
			url: '<c:url value="sendreply"></c:url>',
			data: JSON.stringify({
				text: $("#textarea" + i).val(),
				target: i,
				name: name,
				email: email
			}),
			success: success,
			error: error,
			contentType: "application/json",
			dataType: "json"	
		});
	}
	function showMessages(data) {
		$("#messages").html("");

		for (var i = 0; i < data.messages.length; i++) {
			var message = data.messages[i];
			var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "message");

			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "subject");
			subjectSpan.appendChild(document.createTextNode(message.subject));

			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messageBody");
			contentSpan.appendChild(document.createTextNode(message.content));

			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "name");
			nameSpan.appendChild(document.createTextNode(message.name + " ("));
			var link = document.createElement("a");
			link.setAttribute("class", "replylink");
			link.setAttribute("href", "#");
			link.setAttribute("onclick", "showReply(" + i + ")");
			link.appendChild(document.createTextNode(message.email));
			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));
			
			var alertSpan = document.createElement("span");
			alertSpan.setAttribute("class", "alert");
			alertSpan.setAttribute("id", "alert" + i);

			var replyForm = document.createElement("form");
			replyForm.setAttribute("class", "replyform");
			replyForm.setAttribute("id", "form" + i);

			var textArea = document.createElement("textarea");
			textArea.setAttribute("class", "replyarea");
			textArea.setAttribute("id", "textarea" + i);

			var replyButton = document.createElement("input");
			replyButton.setAttribute("class", "replybutton");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("value", "Reply");
			replyButton.onclick = function(i, name, email){
				return function(){
					sendReply(i, name, email);
				}	
			}(i, message.name, message.email);

			replyForm.appendChild(textArea);
			replyForm.appendChild(replyButton);

			messageDiv.appendChild(subjectSpan);
			messageDiv.appendChild(contentSpan);
			messageDiv.appendChild(nameSpan);
			messageDiv.appendChild(alertSpan);
			messageDiv.appendChild(replyForm);			

			$("#messages").append(messageDiv);
		}

	}
	function onLoad() {
		updatePage();
		startTimer();
	}

	function startTimer() {
		timer = window.setInterval(updatePage, 3000);
	}

	function stopTimer() {
		window.clearInterval(timer);
	}

	function updatePage() {
		$.getJSON("<c:url value='/getmessages'/>", showMessages);
	}

	$(document).ready(onLoad);
</script>