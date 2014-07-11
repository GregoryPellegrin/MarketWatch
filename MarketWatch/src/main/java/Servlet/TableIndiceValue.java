/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Servlet;

import Field.FieldGoogleFinance;
import Field.FieldIndice;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;

public class TableIndiceValue extends DataSourceServlet
{
	private final ArrayList <ColumnDescription> column = new ArrayList <> ();
	private String urlYahoo;
	private DecimalFormat formatter;
	
	@Override
	public void init ()
	{
		this.column.add(new ColumnDescription(FieldIndice.TIME.toString(), ValueType.TEXT, FieldIndice.TIME.toString()));
		this.column.add(new ColumnDescription(FieldIndice.DOW_JONES.toString(), ValueType.TEXT, FieldIndice.DOW_JONES.toString()));
		
		this.urlYahoo = FieldYahooFinance.URL.toString();
		
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.NASDAQ.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.SP500.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.FTSE100.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.TSX100.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.CAC40.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.DAX30.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.NIKKEI225.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.HKSE.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.ADD_COLUMN.toString();
		
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.LAST_PRICE.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.CHANGE_PERCENT.toString();
		
		DecimalFormatSymbols symbol = new DecimalFormatSymbols (Locale.ENGLISH);
		symbol.setDecimalSeparator('.');
		symbol.setGroupingSeparator(',');
		
		this.formatter = new DecimalFormat ("###,###.##");
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
			
			dataTable.addColumn(new ColumnDescription(FieldIndice.NASDAQ.toString(), ValueType.TEXT, FieldIndice.NASDAQ.toString()));
			dataTable.addColumn(new ColumnDescription(FieldIndice.SP500.toString(), ValueType.TEXT, FieldIndice.SP500.toString()));
			dataTable.addColumn(new ColumnDescription(FieldIndice.FTSE100.toString(), ValueType.TEXT, FieldIndice.FTSE100.toString()));
			dataTable.addColumn(new ColumnDescription(FieldIndice.TSX100.toString(), ValueType.TEXT, FieldIndice.TSX100.toString()));
			dataTable.addColumn(new ColumnDescription(FieldIndice.CAC40.toString(), ValueType.TEXT, FieldIndice.CAC40.toString()));
			dataTable.addColumn(new ColumnDescription(FieldIndice.DAX30.toString(), ValueType.TEXT, FieldIndice.DAX30.toString()));
			dataTable.addColumn(new ColumnDescription(FieldIndice.NIKKEI225.toString(), ValueType.TEXT, FieldIndice.NIKKEI225.toString()));
			dataTable.addColumn(new ColumnDescription(FieldIndice.HKSE.toString(), ValueType.TEXT, FieldIndice.HKSE.toString()));
			
			row.addCell(this.getTime());
			row.addCell(this.getDowJonesValue());
			
			for (int i = 0; i < dataTable.getNumberOfRows(); i++)
			{
				String lastPriceDataTable = dataTable.getCell(i, 0).toString();
				String changePercentDataTable = dataTable.getCell(i, 1).toString();
				
				if (! lastPriceDataTable.matches(".*\\d.*"))
					lastPriceDataTable = "0";
				if (! changePercentDataTable.matches(".*\\d.*"))
					changePercentDataTable = "0";
				
				String lastPriceString = this.formatter.format(Double.parseDouble(lastPriceDataTable));
				String changePercentString = this.formatter.format(Double.parseDouble(changePercentDataTable));
				
				row.addCell(lastPriceString + " (" + changePercentString + "%)");
			}
			
			dataTable.addRow(row);
		}
		catch (MalformedURLException e)
		{
			System.out.println("TableIndiceValue generateDataTable() MalformedURLException " + "URL : " + this.urlYahoo + " " + e);
		}
		catch (IOException e)
		{
			System.out.println("TableIndiceValue generateDataTable() IOException " + e);
		}
		
		return dataTable;
	}
	
	@Override
	protected boolean isRestrictedAccessMode ()
	{
		return false;
	}
	
	private String getTime ()
	{
		Calendar time = GregorianCalendar.getInstance();
		String amPm;
		
		if (time.get(Calendar.AM_PM) == Calendar.AM)
			amPm = "AM";
		else
			amPm = "PM";
		
		return time.get(Calendar.HOUR) + ":" + time.get(Calendar.MINUTE) + amPm;
	}
	
	private String getDowJonesValue ()
	{
		String dowJonesValue = "0";
		
		try (final Scanner dowJonesWebPage = new Scanner (new URL (FieldGoogleFinance.URL.toString()).openStream()))
		{
			boolean lastPriceHasFind = false;
			boolean changePercentHasFind = false;
			
			while (dowJonesWebPage.hasNext() && ((! lastPriceHasFind) || (! changePercentHasFind)))
			{
				String value = dowJonesWebPage.next();
				
				if (value.contains(FieldGoogleFinance.LAST_PRICE_PATTERN.toString()))
				{
					dowJonesValue = value.replaceFirst(FieldGoogleFinance.LAST_PRICE_PATTERN.toString(), "").replaceFirst("</span>", "");
					dowJonesValue = dowJonesValue + " ";
					
					lastPriceHasFind = true;
				}
				else if (value.contains(FieldGoogleFinance.CHANGE_PERCENT_PATTERN.toString()))
				{
					dowJonesValue = dowJonesValue + value.replaceFirst(FieldGoogleFinance.CHANGE_PERCENT_PATTERN.toString(), "").replaceFirst("</span>", "");
					
					changePercentHasFind = true;
				}
			}
		}
		catch (MalformedURLException e)
		{
			System.out.println("TableIndiceValue getDowJonesValue() MalformedURLException " + "URL : " + FieldGoogleFinance.URL.toString() + " " + e);
		}
		catch (IOException e)
		{
			System.out.println("TableIndiceValue getDowJonesValue() IOException " + e);
		}
		
		return dowJonesValue;
	}
}