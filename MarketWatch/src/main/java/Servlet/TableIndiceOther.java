/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Servlet;

import Field.FieldBloomberg;
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
import java.util.Locale;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;

public class TableIndiceOther extends DataSourceServlet
{
	private final ArrayList <ColumnDescription> column = new ArrayList <> ();
	private String urlYahoo;
	private DecimalFormat formatter;
	
	@Override
	public void init ()
	{
		this.column.add(new ColumnDescription (FieldIndice.VIX.toString(), ValueType.TEXT, FieldIndice.VIX.toString()));
		this.column.add(new ColumnDescription (FieldIndice.BDI.toString(), ValueType.TEXT, FieldIndice.BDI.toString()));
		
		this.urlYahoo = FieldYahooFinance.URL.toString();
		
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.VIX.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		this.urlYahoo = this.urlYahoo + FieldYahooFinance.GOLD.toString() + FieldYahooFinance.STOCK_SEPARATOR.toString();
		
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
			
			dataTable.addColumn(new ColumnDescription (FieldIndice.GOLD.toString(), ValueType.TEXT, FieldIndice.GOLD.toString()));
			
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
				
				if (i == 1)
					row.addCell(this.getBalticDryIndex());
				
				row.addCell(lastPriceString + " (" + changePercentString + "%)");
			}
			
			dataTable.addRow(row);
		}
		catch (MalformedURLException e)
		{
			System.out.println("TableIndiceOther generateDataTable() MalformedURLException " + "URL : " + this.urlYahoo + " " + e);
		}
		catch (IOException e)
		{
			System.out.println("TableIndiceOther generateDataTable() IOException " + e);
		}
		
		return dataTable;
	}
	
	@Override
	protected boolean isRestrictedAccessMode ()
	{
		return false;
	}
	
	private String getBalticDryIndex ()
	{
		String balticDryIndex = "0";
		
		try (final Scanner balticDryIndexWebPage = new Scanner (new URL (FieldBloomberg.URL.toString()).openStream()))
		{
			boolean lastPriceHasFind = false;
			boolean changePercentHasFind = false;
			
			while (balticDryIndexWebPage.hasNext() && ((! lastPriceHasFind) || (! changePercentHasFind)))
			{
				String value = balticDryIndexWebPage.next();
				
				if (value.contains(FieldBloomberg.LAST_PRICE_PATTERN.toString()))
				{
					value = balticDryIndexWebPage.next();
					balticDryIndex = value.replaceFirst(FieldBloomberg.ITEM_FIND_PATTERN.toString(), "").replace("\"", "");
					balticDryIndex = balticDryIndex + " ";
					
					lastPriceHasFind = true;
				}
				else if (value.contains(FieldBloomberg.CHANGE_PERCENT_PATTERN.toString()))
				{
					value = balticDryIndexWebPage.next();
					balticDryIndex = balticDryIndex + "(" + value.replaceFirst(FieldBloomberg.ITEM_FIND_PATTERN.toString(), "").replace("\"", "") + "%)";
					
					changePercentHasFind = true;
				}
			}
		}
		catch (MalformedURLException e)
		{
			System.out.println("TableIndiceOther getBalticDryIndex() MalformedURLException " + "URL : " + FieldBloomberg.URL.toString() + " " + e);
		}
		catch (IOException e)
		{
			System.out.println("TableIndiceOther getBalticDryIndex() IOException " + e);
		}
		
		return balticDryIndex;
	}
}