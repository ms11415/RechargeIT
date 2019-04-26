/**
 * 
 */

let states = ["AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DC", "DE",
			"FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
			"LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT",
			"NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH",
			"OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT",
			"VT", "VA", "WA", "WV", "WI", "WY"]

$(document).ready(function(){
	// for loop adds all the states as option elements and values
	for (var i=0; i<states.length; i++) {
		$("select").append("<option value =" + states[i] + ">" + states[i] + "</option>");
	}
});