/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Servlet;

import Data.Stock;
import Field.FieldNasdaq;
import Field.FieldStockValue;
import Field.FieldYahooFinance;
import Filter.FilterCsv;
import Filter.FilterNasdaq;
import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.ValueType;
import com.google.visualization.datasource.query.Query;
import com.google.visualization.datasource.util.CsvDataSourceHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.http.HttpServletRequest;

public class TableStock extends DataSourceServlet
{
	private final ArrayList <ColumnDescription> columnTemp = new ArrayList <> ();
	private final ArrayList <String> urlYahoo = new ArrayList <> ();
	private DecimalFormat formatter;
	private Timer timer;
	private String urlBefore;
	private String urlAfter;
	
	private class RefreshSymbolList extends TimerTask
	{
		public RefreshSymbolList () {}
		
		@Override
		public void run ()
		{
			refreshSymbolList();
		}
	}
	
	@Override
	public void init ()
	{
		this.columnTemp.add(new ColumnDescription (FieldStockValue.SYMBOL.toString(), ValueType.TEXT, FieldStockValue.SYMBOL.toString()));
		this.columnTemp.add(new ColumnDescription (FieldStockValue.LAST_PRICE.toString(), ValueType.NUMBER, FieldStockValue.LAST_PRICE.toString()));
		this.columnTemp.add(new ColumnDescription (FieldStockValue.CHANGE_PERCENT.toString(), ValueType.NUMBER, FieldStockValue.CHANGE_PERCENT.toString()));
		this.columnTemp.add(new ColumnDescription (FieldStockValue.VOLUME.toString(), ValueType.NUMBER, FieldStockValue.VOLUME.toString()));
		this.columnTemp.add(new ColumnDescription (FieldStockValue.AVERAGE_VOLUME.toString(), ValueType.NUMBER, FieldStockValue.AVERAGE_VOLUME.toString()));
		
		this.urlBefore = FieldYahooFinance.URL.toString();
		
		this.urlAfter = FieldYahooFinance.ADD_COLUMN.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.SYMBOL.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.LAST_PRICE.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.CHANGE_PERCENT.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.VOLUME.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.AVERAGE_VOLUME.toString();
		
		//this.timer = new Timer ();
		//this.timer.schedule(new RefreshSymbolList (), 10000000, FieldNasdaq.DAY_IN_MILLISECOND.toInt());// Dans 10000000 millisec puis tout les jours
		
		this.refreshSymbolList();
		
		DecimalFormatSymbols symbol = new DecimalFormatSymbols (Locale.ENGLISH);
		symbol.setDecimalSeparator('.');
		symbol.setGroupingSeparator(',');
		
		this.formatter = new DecimalFormat ("###,###.##");
		this.formatter.setDecimalFormatSymbols(symbol);
	}
	
	@Override
	public DataTable generateDataTable (Query query, HttpServletRequest request) throws DataSourceException
	{
		DataTable dataTable = new DataTable ();
		DataTable dataTableTemp = null;
		
		for (final String url : this.urlYahoo)
		{
			try (final Reader stockData = new FilterCsv (new BufferedReader (new InputStreamReader (new URL (url).openStream()))))
			{
				if (dataTableTemp != null)
					dataTableTemp.addRows(CsvDataSourceHelper.read(stockData, this.columnTemp, false).getRows());
				else
					dataTableTemp = CsvDataSourceHelper.read(stockData, this.columnTemp, false);
			}
			catch (MalformedURLException e)
			{
				System.out.println("TableStock generateDataTable() MalformedURLException " + "URL : " + url + " " + e);
			}
			catch (IOException e)
			{
				System.out.println("TableStock generateDataTable() IOException " + e);
			}
		}
		
		ArrayList <Stock> higherStock = new ArrayList <> ();
		
		for (int i = 0; i < 3; i++)
			higherStock.add(new Stock ("temp", "0.0", "0.0", "0.0", "0.0"));
		
		for (int i = 0; i < dataTableTemp.getNumberOfRows(); i++)
			if (! dataTableTemp.getCell(i, 2).toString().equals("null"))
				for (int j = 0; j < higherStock.size(); j++)
				{
					double changePercentDataTable = Double.parseDouble(dataTableTemp.getCell(i, 2).toString());
					double changePercentArrayList = Double.parseDouble(higherStock.get(j).getChangePercent());
					
					if (changePercentDataTable > changePercentArrayList)
					{
						higherStock.get(j).setSymbol(dataTableTemp.getCell(i, 0).toString());
						higherStock.get(j).setLastPrice(this.formatter.format(Double.parseDouble(dataTableTemp.getCell(i, 1).toString())));
						higherStock.get(j).setChangePercent(this.formatter.format(Double.parseDouble(dataTableTemp.getCell(i, 2).toString())));
						higherStock.get(j).setVolume(this.formatter.format(Double.parseDouble(dataTableTemp.getCell(i, 3).toString())));
						higherStock.get(j).setAverageVolume(this.formatter.format(Double.parseDouble(dataTableTemp.getCell(i, 4).toString())));
						
						if ((j == 0) || (j == 1))
							j = higherStock.size();
					}
				}
		
		ArrayList <ColumnDescription> column = new ArrayList <> ();
		TableRow row = new TableRow ();
		
		for (int i = 0; i < higherStock.size(); i++)
		{
			column.add(new ColumnDescription (higherStock.get(i).getSymbol(), ValueType.TEXT, higherStock.get(i).getSymbol()));
			row.addCell(higherStock.get(i).getLastPrice()+ " (" + higherStock.get(i).getChangePercent()+ "%) " + higherStock.get(i).getVolume()+ " (" + higherStock.get(i).getAverageVolume()+ ")");
		}
		
		dataTable.addColumns(column);
		dataTable.addRow(row);
		
		return dataTable;
	}
	
	@Override
	protected boolean isRestrictedAccessMode ()
	{
		return false;
	}
	
	private void refreshSymbolList ()
	{
		this.urlYahoo.clear();
		
		this.setUrlYahoo(FieldNasdaq.PATH_NYSE.toString());
		//this.setUrlYahoo(FieldNasdaq.URL_NYSE.toString());
		this.setUrlYahoo(FieldNasdaq.PATH_NASDAQ.toString());
		//this.setUrlYahoo(FieldNasdaq.URL_NASDAQ.toString());
		
		System.out.println("Stock (~" + (this.urlYahoo.size() * FieldNasdaq.URL_LIMIT.toInt()) + ")");
		System.out.println("Page (~" + ((this.urlYahoo.size() * FieldNasdaq.URL_LIMIT.toInt()) / 11) + ")");
	}
	
	private void setUrlYahoo (String path)
	{
		//try (final Reader symbolList = new FilterNasdaq (new BufferedReader (new InputStreamReader (new URL (url).openStream()))))
		try (Reader symbolList = new FilterNasdaq (new BufferedReader (new FileReader (new File (path)))))
		{
			String symbolLine = "";
			int urlLimit = 0;
			int characterSymbolList = symbolList.read();
			
			while (characterSymbolList != -1)
			{
				if (urlLimit < FieldNasdaq.URL_LIMIT.toInt())
				{
					symbolLine = symbolLine + String.valueOf((char) characterSymbolList);

					if (characterSymbolList == '+')
						urlLimit = urlLimit + 1;
					
					characterSymbolList = symbolList.read();
				}
				else
				{
					this.urlYahoo.add(this.urlBefore + symbolLine + this.urlAfter);
					
					urlLimit = 0;
					symbolLine = "";
				}
			}
			this.urlYahoo.set(this.urlYahoo.size() - 1, this.urlYahoo.get(this.urlYahoo.size() - 1).replaceFirst(FieldNasdaq.EOF_REGEX.toString(), ""));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("TableStock setUrlYahoo() FileNotFoundException " + "PATH : " + path + " " + e);
		}
		/*catch (MalformedURLException e)
		{
			System.out.println("TableStockValue setUrlYahoo() MalformedURLException " + "URL : " + url + " " + e);
		}*/
		catch (IOException e)
		{
			System.out.println("TableStock setUrlYahoo() IOException " + "PATH : " + path + " " + e);
		}
	}
}