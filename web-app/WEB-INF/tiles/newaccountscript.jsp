<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkPasswordMatch);
		$("#confirmpass").keyup(checkPasswordMatch);
		$("#detail").submit(canSubmit);
	}

	function canSubmit() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		return password === confirmpass;
	}

	function checkPasswordMatch() {
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if (password.length < 3) {
			return;
		}

		if (password === confirmpass) {
			$("#matchpass").text(
					"<fmt:message key='MatchedPasswords.user.password' />");
			$("#matchpass").removeClass("error");
			$("#matchpass").addClass("valid");
		} else {
			$("#matchpass").text(
					"<fmt:message key='UnmatchedPasswords.user.password' />")
			$("#matchpass").addClass("error");
			$("#matchpass").removeClass("valid");
		}
	}

	$(document).ready(onLoad);
</script>