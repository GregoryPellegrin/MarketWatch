/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Servlet;

import Field.FieldCurrency;
import Field.FieldYahooFinance;
import Filter.FilterCsv;
import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;
import com.google.visualization.datasource.util.CsvDataSourceHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

public class TableCurrency extends DataSourceServlet
{
	private final ArrayList <ColumnDescription> column = new ArrayList <> ();
	private String urlYahoo;
	private DecimalFormat formatter;
	
	@Override
	public void init ()
	{
		this.column.add(new ColumnDescription (FieldCurrency.USD_EUR.toString(), ValueType.TEXT, FieldCurrency.USD_EUR.toString()));
		
		this.urlYahoo = FieldYahooFinance.URL.toString();
		
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.USD_EUR.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.USD_CAD.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.USD_YEN.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.USD_YUAN.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.ADD_COLUMN.toString();
		
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.LAST_PRICE.toString();
		
		DecimalFormatSymbols symbol = new DecimalFormatSymbols (Locale.ENGLISH);
		symbol.setDecimalSeparator('.');
		symbol.setGroupingSeparator(',');
		
		this.formatter = new DecimalFormat ("###,###.####");
		this.formatter.setDecimalFormatSymbols(symbol);
	}
	
	@Override
	public DataTable generateDataTable (Query query, HttpServletRequest request) throws DataSourceException
	{
		DataTable dataTable = null;
		
		try (final Reader reader = new FilterCsv (new BufferedReader (new InputStreamReader (new URL (this.urlYahoo).openStream()))))
		{
			TableRow row = new TableRow ();
			
			dataTable = CsvDataSourceHelper.read(reader, this.column, false);
			
			dataTable.addColumn(new ColumnDescription (FieldCurrency.USD_CAD.toString(), ValueType.TEXT, FieldCurrency.USD_CAD.toString()));
			dataTable.addColumn(new ColumnDescription (FieldCurrency.USD_YEN.toString(), ValueType.TEXT, FieldCurrency.USD_YEN.toString()));
			dataTable.addColumn(new ColumnDescription (FieldCurrency.USD_YUAN.toString(), ValueType.TEXT, FieldCurrency.USD_YUAN.toString()));
			
			for (int i = 0; i < dataTable.getNumberOfRows(); i++)
			{
				String lastPriceDataTable = dataTable.getCell(i, 0).toString();
				
				if (! lastPriceDataTable.matches(".*\\d.*"))
					lastPriceDataTable = "0";
				
				String lastPriceString = this.formatter.format(Double.parseDouble(lastPriceDataTable));
				
				row.addCell(lastPriceString);
			}
			
			dataTable.addRow(row);
		}
		catch (MalformedURLException e)
		{
			System.out.println("TableCurrency generateDataTable() MalformedURLException " + "URL : " + this.urlYahoo + " " + e);
		}
		catch (IOException e)
		{
			System.out.println("TableCurrency generateDataTable() IOException " + e);
		}
		
		return dataTable;
	}
	
	@Override
	protected boolean isRestrictedAccessMode ()
	{
		return false;
	}
}