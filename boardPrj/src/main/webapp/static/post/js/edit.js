var hashTagCount = $("#hashtag-table> tbody").length;
var hashTagNumber = 1;

function addHashTag() {
    let hashTag = $("#hashtag-input").val();
    if (hashTag =="" || hashTag == null) {
        return;
    }

    
    if (hashTagCount >= 5) {
        return;
    }


    let newHashTag = '<tr><td><input id="' + hashTagNumber++ + '" type="text" name="hashTag" readonly value="' + hashTag +'"/><button type="button" onclick="removeHashTag(this)">X</button></td></tr>';
    
    $("#hashtag-table> tbody").append(newHashTag);
    ++hashTagCount;
}

function removeHashTag(event) {
    let target = event;
    event.parentNode.parentNode.remove();    
}