/**
 * 
 */

$(document).ready(function(){	
	$("div.dog").hover(
		function(){$(this).addClass("highlight");}, 
		function(){$(this).removeClass("highlight");});
	});