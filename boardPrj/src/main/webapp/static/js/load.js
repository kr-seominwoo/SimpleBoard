$("#likeBtn").click(function() {
	const curURL = $(window.location)[0].href;
	const slashLastIndex = curURL.lastIndexOf("/"); 
	let url = curURL.substring(0, slashLastIndex + 1) + "like";
	const POST_NUMBER = $("#like").attr("postNumber");
	$("#like").load(url, {"postNumber" : POST_NUMBER});
});

$("#unlikeBtn").click(function() {
	const curURL = $(window.location)[0].href;
	const slashLastIndex = curURL.lastIndexOf("/"); 
	let url = curURL.substring(0, slashLastIndex + 1) + "unlike";
	const POST_NUMBER = $("#like").attr("postNumber");
	$.post(url, {"postNumber" : POST_NUMBER}, function(response) {
		if (response == "success") {
			alert("싫어요");	
		} else {
			alert("싫어요 실패");
		}
	});
});