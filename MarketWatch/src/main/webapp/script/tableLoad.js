/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

function createUsTable (response)
{
	error(response);
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('indiceUSTable'));
	var cssClassNames =
	{
		headerRow: 'th',
		tableRow: 'td',
		headerCell: 'thSpan',
		tableCell: 'tdSpan'
	};
	
	if (data.getNumberOfRows() > 0)
	{
		data.removeRows(0, data.getNumberOfRows() - 1);

		data.setProperties(0, 0, {style: 'color: blue;'});

		for (var i = 1; i < data.getNumberOfColumns(); i++)
		{
			if (data.getValue(0, i).indexOf('-') > -1)
				data.setProperties(0, i, {style: 'color: red;'});
			else
				data.setProperties(0, i, {style: 'color: green;'});
		}
		
		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			sort: 'disable',
			cssClassNames: cssClassNames
		});
	}
}

function createEUTable (response)
{
	error(response);
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('indiceEUTable'));
	var cssClassNames =
	{
		headerRow: 'th',
		tableRow: 'td',
		headerCell: 'thSpan',
		tableCell: 'tdSpan'
	};
	
	if (data.getNumberOfRows() > 0)
	{
		data.removeRows(0, data.getNumberOfRows() - 1);

		for (var i = 0; i < data.getNumberOfColumns(); i++)
		{
			if (data.getValue(0, i).indexOf('-') > -1)
				data.setProperties(0, i, {style: 'color: red;'});
			else
				data.setProperties(0, i, {style: 'color: green;'});
		}
		
		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			sort: 'disable',
			cssClassNames: cssClassNames
		});
	}
}

function createAsiaTable (response)
{
	error(response);
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('indiceAsiaTable'));
	var cssClassNames =
	{
		headerRow: 'th',
		tableRow: 'td',
		headerCell: 'thSpan',
		tableCell: 'tdSpan'
	};
	
	if (data.getNumberOfRows() > 0)
	{
		data.removeRows(0, data.getNumberOfRows() - 1);

		for (var i = 0; i < data.getNumberOfColumns(); i++)
		{
			if (data.getValue(0, i).indexOf('-') > -1)
				data.setProperties(0, i, {style: 'color: red;'});
			else
				data.setProperties(0, i, {style: 'color: green;'});
		}
		
		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			sort: 'disable',
			cssClassNames: cssClassNames
		});
	}
}

function createOtherTable (response)
{
	error(response);
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('indiceOtherTable'));
	var cssClassNames =
	{
		headerRow: 'th',
		tableRow: 'td',
		headerCell: 'thSpan',
		tableCell: 'tdSpan'
	};
	
	if (data.getNumberOfRows() > 0)
	{
		data.removeRows(0, data.getNumberOfRows() - 1);

		for (var i = 0; i < data.getNumberOfColumns(); i++)
		{
			if (data.getValue(0, i).indexOf('-') > -1)
				data.setProperties(0, i, {style: 'color: red;'});
			else
				data.setProperties(0, i, {style: 'color: green;'});
		}
		
		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			sort: 'disable',
			cssClassNames: cssClassNames
		});
	}
}

function createETFTable (response)
{
	error(response);
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('etfTable'));
	var cssClassNames =
	{
		headerRow: 'th',
		tableRow: 'td',
		headerCell: 'thSpan',
		tableCell: 'tdSpan'
	};
	
	if (data.getNumberOfRows() > 0)
	{
		data.removeRows(0, data.getNumberOfRows() - 1);

		for (var i = 0; i < data.getNumberOfColumns(); i++)
		{
			if (data.getValue(0, i).indexOf('-') > -1)
				data.setProperties(0, i, {style: 'color: red;'});
			else
				data.setProperties(0, i, {style: 'color: green;'});
		}
		
		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			sort: 'disable',
			cssClassNames: cssClassNames
		});
	}
}

function createCurrencyTable (response)
{
	error(response);
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('currencyTable'));
	var cssClassNames =
	{
		headerRow: 'th',
		tableRow: 'td',
		headerCell: 'thSpan',
		tableCell: 'tdSpan'
	};
	
	if (data.getNumberOfRows() > 0)
	{
		data.removeRows(0, data.getNumberOfRows() - 1);

		for (var i = 0; i < data.getNumberOfColumns(); i++)
		{
			if (data.getValue(0, i).indexOf('-') > -1)
				data.setProperties(0, i, {style: 'color: red;'});
			else
				data.setProperties(0, i, {style: 'color: green;'});
		}
		
		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			sort: 'disable',
			cssClassNames: cssClassNames
		});
	}
}

function createStockTable (response)
{
	error(response);
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('stockTable'));
	var cssClassNames =
	{
		headerRow: 'th',
		tableRow: 'td',
		headerCell: 'thSpan',
		tableCell: 'tdSpan'
	};
	
	if (data.getNumberOfRows() > 0)
	{
		data.removeRows(0, data.getNumberOfRows() - 1);

		for (var i = 0; i < data.getNumberOfColumns(); i++)
		{
			if (data.getValue(0, i).indexOf('-') > -1)
				data.setProperties(0, i, {style: 'color: red;'});
			else
				data.setProperties(0, i, {style: 'color: green;'});
		}
		
		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			sort: 'disable',
			cssClassNames: cssClassNames
		});
	}
}