
$("#all").click(function() { return false; });

$(document).ready(function(){
$("#all").click(function() { return false; });

    
    $('input#all').on('change', function() {
    $('input#pen').prop('checked', false); 
    $('input#notepad').prop('checked', false); 
    $('input#highlighter').prop('checked', false); 
});
$('input#pen').on('change', function() {
    $('input#all').prop('checked', false);  
});
$('input#notepad').on('change', function() {
    $('input#all').prop('checked', false);  
});
$('input#highlighter').on('change', function() {
    $('input#all').prop('checked', false);  
});
});