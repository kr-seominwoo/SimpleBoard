var hashTagCount = $("#hashtag-table >tbody tr").length;

function addHashTag() {
    let hashTag = $("#hashtag-input").val();
    if (hashTag =="" || hashTag == null) {
        return;
    }

    
    if (hashTagCount >= 5) {
        return;
    }

	let isDuplicate = false;
	$("#hashtag-table >tbody > tr").each(function() {
		let compare = $(this).children().children().val();
		if (hashTag == compare) {
			isDuplicate = true;
			return;
		}
	});
	
	if (isDuplicate == true) {
		return;
	}

    let newHashTag = '<tr><td><input type="text" name="hashTag" readonly value="' + hashTag +'"/><button type="button" onclick="removeHashTag(this)">X</button></td></tr>';
    
    $("#hashtag-table> tbody").append(newHashTag);
    ++hashTagCount;
}

function removeHashTag(event) {
    let target = event;
    event.parentNode.parentNode.remove();
    --hashTagCount;
}