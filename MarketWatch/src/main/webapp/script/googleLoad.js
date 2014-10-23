/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

google.load('visualization', '1', {packages: ['table', 'controls']});

google.setOnLoadCallback(indiceUSTable);
google.setOnLoadCallback(indiceEUTable);
google.setOnLoadCallback(indiceAsiaTable);
google.setOnLoadCallback(indiceOtherTable);
google.setOnLoadCallback(etfTable);
google.setOnLoadCallback(currencyTable);
google.setOnLoadCallback(stockTable);

function indiceUSTable ()
{
	var indiceUSData = new google.visualization.Query('TableIndiceUS', {sendMethod: 'scriptInjection'});
	
	indiceUSData.setRefreshInterval(5);
	indiceUSData.send(createUsTable);
}

function indiceEUTable ()
{
	var indiceEUData = new google.visualization.Query('TableIndiceEU', {sendMethod: 'scriptInjection'});
	
	indiceEUData.setRefreshInterval(10);
	indiceEUData.send(createEUTable);
}

function indiceAsiaTable ()
{
	var indiceAsiaData = new google.visualization.Query('TableIndiceAsia', {sendMethod: 'scriptInjection'});
	
	indiceAsiaData.setRefreshInterval(10);
	indiceAsiaData.send(createAsiaTable);
}

function indiceOtherTable ()
{
	var indiceOtherData = new google.visualization.Query('TableIndiceOther', {sendMethod: 'scriptInjection'});
	
	indiceOtherData.setRefreshInterval(5);
	indiceOtherData.send(createOtherTable);
}

function etfTable ()
{
	var etfData = new google.visualization.Query('TableETF', {sendMethod: 'scriptInjection'});
	
	etfData.setRefreshInterval(5);
	etfData.send(createETFTable);
}

function currencyTable ()
{
	var currencyData = new google.visualization.Query('TableCurrency', {sendMethod: 'scriptInjection'});
	
	currencyData.setRefreshInterval(10);
	currencyData.send(createCurrencyTable);
}

function stockTable ()
{
	var stockData = new google.visualization.Query('TableStock', {sendMethod: 'scriptInjection'});
	
	stockData.setRefreshInterval(20);
	stockData.send(createStockTable);
}