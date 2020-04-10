var download = function(){
$.ajax({
url:"sample/download",
type: "POST"
data :("name" : "ajax", "visibility":"PUBLIC"),
success : function(){

},
error: function() {
alert ("Unable to download");
}
});
$(document).ready(function(){
$('#downloadInDashboard').click(function(){
download();
});
$('select#loggedInUserPage').change(function(){
alert("Page");});
});
}


