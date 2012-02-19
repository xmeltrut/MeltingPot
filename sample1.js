function flagForm(element){
	$('#' + element).addClass("highlight");
	document.getElementById(element).onblur = new Function("flagFormRestore('" + element + "')");
	$('#' + element).focus();
}

function flagFormRestore(element){
	$('#' + element).removeClass("highlight");
}

function validateRequestForm()
{
	if ($('#yourname').val() == ""){
		alert('You did not enter your name.');
		flagForm('yourname');
		return false;
	} else if ($('#group').val() == ""){
		alert('You did not enter your group name.');
		flagForm('group');
		return false;
	} else if ($('#email').val() == ""){
		alert('You did not enter your email address.');
		flagForm('email');
		return false;
	} else if ($('#address').val() == ""){
		alert('You did not enter your postal address.');
		flagForm('address');
		return false;
	}
	
	return true;
}