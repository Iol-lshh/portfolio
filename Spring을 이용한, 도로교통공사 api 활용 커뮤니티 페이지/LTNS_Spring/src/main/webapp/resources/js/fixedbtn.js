
function top_arrow_fadeout() {
	if ($(this).scrollTop() > 200) {
		$('#top_arrow').fadeIn();
	} else {
		$('#top_arrow').fadeOut();
	}
}

function gototop() {
		$( 'html, body' ).animate( { scrollTop : 0 }, 400 );
		return false;
}