function checkPassword(form) {
	var password = form[form.id + ":passwordField"].value;
	var passwordConfirm = form[form.id + ":confirmPasswordField"].value;
	if (password == passwordConfirm) {
		return true;
	}
	else {
		var myError = $("#myError"); 
		myError.html("<p>Passwords do not match.</p>").addClass('text-danger');
		return false;
	}
}