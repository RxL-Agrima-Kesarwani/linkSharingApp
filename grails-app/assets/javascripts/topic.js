var addTopic = function(){
$.ajax({
url:"sample/addTopic",
type: "POST"
data :("name" : "ajax", "visibility":"PUBLIC"),
success : function(){
alert("Topic added successfully");
},
error: function() {
alert ("Unable to add topic");
}
});
$(document).ready(function(){
$('#addTopicButton').click(function(){
addTopic();
});
});
}


