function checkURLs(reAuthURLs, requestURL){

	if (reAuthURLs.includes(requestURL)){
		$("#reAuthModal").modal({backdrop: "static",keyboard: false });
	}

}

$(document).ready(function() {
	
	$("#loginForm").submit(function(e) {
		
		e.preventDefault();

		let password = document.getElementById("loginPassword").value;
		let email = document.getElementById("loginEmail").value;
		
		document.getElementById("submitBtn").innerText = "working ...";
		
		let url = "/rest/login?email=" + email + "&password=" + password;

		$.ajax({
			url: url,
			contentType: "application/x-www-form-urlencoded",
			type: "POST",
			data: $("#loginForm").serialize(),
			success: function(data) {
				$("#reAuthModal").modal("hide");
			},
			error: function(err) {
				document.getElementById("submitBtn").innerText = " Login ";
				document.getElementById("feedBackArea").innerText = " Wrong email or password";
			}
		});
	});
});

