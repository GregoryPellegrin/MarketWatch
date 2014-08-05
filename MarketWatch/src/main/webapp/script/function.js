/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

function changeDesignInLight ()
{
	$('#design').attr('href', './css/lightDesign.css');
}

function changeDesignInDark ()
{
	$('#design').attr('href', './css/darkDesign.css');
}

function getNumberOfRows ()
{
	var height = $(window).height();
	var indiceHeight = 86;
	var etfHeight = 86;
	var dataTableMarginTop = 10;
	var dataTableTh = 47;
	var dataTableTrHeight = 26;
	var footer = $('footer').outerHeight(true);
	
	height = height - indiceHeight
					- etfHeight
					- dataTableMarginTop
					- dataTableTh
					- footer;
	
	height = height / dataTableTrHeight;
	
	return Math.round(height);
}