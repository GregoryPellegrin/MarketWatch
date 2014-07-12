/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

function indiceResponse (response)
{
	if (response.isError())
	{
		alert(response.getMessage() + ' ' + response.getDetailedMessage());
		
		return;
	}
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('indiceValueTable'));
	var cssClassNames =
	{
		headerRow: 'indiceTh',
		tableRow: 'indiceTd',
		oddTableRow: '',
		hoverTableRow: 'indiceTdHover',
		selectedTableRow: 'indiceTdSelected',
		headerCell: 'indiceThSpan',
		tableCell: 'indiceTdSpan',
		rowNumberCell: ''
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

function etfValueResponse (response)
{
	if (response.isError())
	{
		alert(response.getMessage() + ' ' + response.getDetailedMessage());
		
		return;
	}
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('etfValueTable'));
	var cssClassNames =
	{
		headerRow: 'indiceTh',
		tableRow: 'indiceTd',
		oddTableRow: '',
		hoverTableRow: 'indiceTdHover',
		selectedTableRow: 'indiceTdSelected',
		headerCell: 'indiceThSpan',
		tableCell: 'indiceTdSpan',
		rowNumberCell: ''
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

function stockValueResponse (response)
{
	if (response.isError())
	{
		alert(response.getMessage() + ' ' + response.getDetailedMessage());
		
		return;
	}
	
	var data = response.getDataTable();
	var table = new google.visualization.Table(document.getElementById('stockValueTable'));
	var cssClassNames =
	{
		headerRow: 'dataTableTh',
		tableRow: 'dataTableTdEven',
		oddTableRow: 'dataTableTdOdd',
		hoverTableRow: 'dataTableTdHover',
		selectedTableRow: 'dataTableTdSelected',
		headerCell: 'dataTableThSpan',
		tableCell: 'dataTableTdSpan',
		rowNumberCell: ''
	};
	var netChangeColorFormatter = new google.visualization.ColorFormat();
	var doubleNumberFormatter = new google.visualization.NumberFormat({decimalSymbol: '.', groupingSymbol: ',', fractionDigits: 2});
	var integerNumberFormatter = new google.visualization.NumberFormat({decimalSymbol: '.', groupingSymbol: ',', fractionDigits: 0});
	
	netChangeColorFormatter.addRange(-9999999, 0, 'red', '00FFFFFF');
	netChangeColorFormatter.addRange(0, 9999999, 'green', '00FFFFFF');
	
	if (data.getNumberOfRows() > 0)
	{
		netChangeColorFormatter.format(data, 2);
		netChangeColorFormatter.format(data, 3);

		doubleNumberFormatter.format(data, 1);
		doubleNumberFormatter.format(data, 2);
		doubleNumberFormatter.format(data, 3);
		doubleNumberFormatter.format(data, 4);
		doubleNumberFormatter.format(data, 5);
		doubleNumberFormatter.format(data, 6);
		doubleNumberFormatter.format(data, 7);
		doubleNumberFormatter.format(data, 8);
		doubleNumberFormatter.format(data, 9);
		doubleNumberFormatter.format(data, 10);
		doubleNumberFormatter.format(data, 11);

		integerNumberFormatter.format(data, 12);
		integerNumberFormatter.format(data, 13);

		table.draw(data,
		{
			showRowNumber: false,
			allowHtml: true,
			alternatingRowStyle: true,
			page: 'enable',
			pageSize: Math.round(($(window).height()-(90*2+10+45+35)-10)/26),
			sortColumn: 2,
			sortAscending: false,
			cssClassNames: cssClassNames
		});
	}
}