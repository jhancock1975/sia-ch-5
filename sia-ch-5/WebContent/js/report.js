/**
 * constants, broker names
 * should find a better way to
 * define constants and put them
 * in a data structure for easier
 * referencing
 */
var schwab = 'Schwab';
var tdAmer = 'TdAmeriTrade';
var scott = 'ScottTrade';
var fidel = 'Fidelity';
/**
 * takes a big string of JSON report data
 * and converts it to a table,
 * then converts text of cells with broker
 * names in table to links
 */
$(document).ready(function() {
	var reports = jQuery.parseJSON(reportJson);
	jQuery.each(reports, function(i, report){
		report = cleanUpNumbers(report);
		var jsonHtmlTable = ConvertJsonToTable(report.reportList, 'jsonTable', 'reportTable', 'Download',  report.title);
		$('#msgid').append(jsonHtmlTable);
		var tdElements = $('#msgid, table, td');
		tdElements = linkIfyBrokers(tdElements);
	});
	/**
	 * converts a decimal number
	 * to a two digit decimal number
	 * for some reason toFixed() returns
	 * a string.
	 */
	function convertTwoDigitDec(x){
		return parseFloat(x.toFixed(2));
	}
	/**
	 * converts dividend yeild
	 * and percent off 52 week lows
	 * to two decimal place numbers.
	 */
	function cleanUpNumbers(report){
		jQuery.each(report.reportList, function (j, reportObj){
			reportObj.yield =  convertTwoDigitDec(reportObj.yield);
			reportObj.pctOff52WeekLow =  convertTwoDigitDec(reportObj.pctOff52WeekLow);
		});
		return report;
	}
	/**
	 * converts broker names to links
	 * @param report - a report produced from sia-ch-5 web app
	 */
	function linkIfyBrokers(tdElements){
		tdElements.each(function(){
			if (isABrokerName(this.innerHTML)){
				this.innerHTML = convertToLink(this.innerHTML);
				//this.innerHTML='<a href="http://swchab.com">Schwab</a>';
			}
		});
		return tdElements;
	}
	/**
	 * add broker name to this function to convert
	 * appearance of broker name in table to a link
	 * @param s - a string
	 */
	function isABrokerName(s){
		return s.valueOf() === schwab.valueOf() || 
			s.valueOf() === fidel.valueOf() || 
			s.valueOf() === scott.valueOf() ||
			s.valueOf() === tdAmer.valueOf();
	}
	/**
	 * @param s - text to convert to link
	 */
	function convertToLink(s){
		if (s.valueOf() === schwab.valueOf()){
			return linkify(s, 'schwab.com');
		} else if (s.valueOf() === tdAmer.valueOf()){
			return linkify(s, 'TdAmeriTrade.com');
		} else if (s.valueOf() === fidel.valueOf()){
			return linkify(s, 'fidelity.com');
		} else if (s.valueOf() === scott.valueOf()){
			return linkify(s, 'scottrade.com');
		} else {
			return s;
		}
	}
	/**
	 * returns string to be used as hyperlink
	 */
	function linkify(linkText, domainName){
		return '<a href=http://'+domainName+'>' + linkText + '</a>';
	}
});