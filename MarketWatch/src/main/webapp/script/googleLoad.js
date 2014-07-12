/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

google.load('visualization', '1', {packages: ['table', 'controls']});

google.setOnLoadCallback(indiceTable);
google.setOnLoadCallback(etfValueTable);
google.setOnLoadCallback(stockValueTable);

function indiceTable ()
{
	var indiceData = new google.visualization.Query('TableIndiceValue', {sendMethod: 'scriptInjection'});
	
	//indiceData.setRefreshInterval(5);
	indiceData.send(indiceResponse);
}

function etfValueTable ()
{
	var etfValueData = new google.visualization.Query('TableETFValue', {sendMethod: 'scriptInjection'});
	
	//etfValueData.setRefreshInterval(5);
	etfValueData.send(etfValueResponse);
}

function stockValueTable ()
{
	var stockValueData = new google.visualization.Query('TableStockValue', {sendMethod: 'scriptInjection'});
	
	//stockValueData.setRefreshInterval(10);
	stockValueData.send(stockValueResponse);
}