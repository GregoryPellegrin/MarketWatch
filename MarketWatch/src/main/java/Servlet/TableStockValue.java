/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Servlet;

import Field.FieldNasdaq;
import Field.FieldStockValue;
import Field.FieldYahooFinance;
import Filter.FilterCsv;
import Filter.FilterNasdaq;
import com.google.visualization.datasource.DataSourceServlet;
import com.google.visualization.datasource.base.DataSourceException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
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
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.http.HttpServletRequest;

public class TableStockValue extends DataSourceServlet
{
	private final ArrayList <ColumnDescription> column = new ArrayList <> ();
	private final ArrayList <String> urlYahoo = new ArrayList <> ();
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
		this.column.add(new ColumnDescription(FieldStockValue.SYMBOL.toString(), ValueType.TEXT, FieldStockValue.SYMBOL.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.LAST_PRICE.toString(), ValueType.NUMBER, FieldStockValue.LAST_PRICE.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.CHANGE_PERCENT.toString(), ValueType.NUMBER, FieldStockValue.CHANGE_PERCENT.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.CHANGE_DOLLAR.toString(), ValueType.NUMBER, FieldStockValue.CHANGE_DOLLAR.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.BID.toString(), ValueType.NUMBER, FieldStockValue.BID.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.ASK.toString(), ValueType.NUMBER, FieldStockValue.ASK.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.OPEN.toString(), ValueType.NUMBER, FieldStockValue.OPEN.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.CLOSE.toString(), ValueType.NUMBER, FieldStockValue.CLOSE.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.HIGH.toString(), ValueType.NUMBER, FieldStockValue.HIGH.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.LOW.toString(), ValueType.NUMBER, FieldStockValue.LOW.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.WEEK_52_HIGH.toString(), ValueType.NUMBER, FieldStockValue.WEEK_52_HIGH.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.WEEK_52_LOW.toString(), ValueType.NUMBER, FieldStockValue.WEEK_52_LOW.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.VOLUME.toString(), ValueType.NUMBER, FieldStockValue.VOLUME.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.AVERAGE_VOLUME.toString(), ValueType.NUMBER, FieldStockValue.AVERAGE_VOLUME.toString()));
		this.column.add(new ColumnDescription(FieldStockValue.MARKET_CAP.toString(), ValueType.TEXT, FieldStockValue.MARKET_CAP.toString()));
		
		this.urlBefore = FieldYahooFinance.URL.toString();
		
		this.urlAfter = FieldYahooFinance.ADD_COLUMN.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.SYMBOL.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.LAST_PRICE.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.CHANGE_PERCENT.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.CHANGE_DOLLAR.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.BID.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.ASK.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.OPEN.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.CLOSE.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.HIGH.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.LOW.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.WEEK_52_HIGH.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.WEEK_52_LOW.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.VOLUME.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.AVERAGE_VOLUME.toString();
		this.urlAfter = this.urlAfter + FieldYahooFinance.MARKET_CAP.toString();
		
		//this.timer = new Timer ();
		//this.timer.schedule(new RefreshSymbolList (), 10000000, FieldNasdaq.DAY_IN_MILLISECOND.toInt());// Dans 10000000 millisec puis tout les jours
		
		this.refreshSymbolList();
	}
	
	@Override
	public DataTable generateDataTable (Query query, HttpServletRequest request) throws DataSourceException
	{
		DataTable dataTable = null;
		
		for (final String url : this.urlYahoo)
		{
			try (final Reader stockData = new FilterCsv (new BufferedReader (new InputStreamReader (new URL (url).openStream()))))
			{
				if (dataTable != null)
					dataTable.addRows(CsvDataSourceHelper.read(stockData, this.column, false).getRows());
				else
					dataTable = CsvDataSourceHelper.read(stockData, this.column, false);
			}
			catch (MalformedURLException e)
			{
				System.out.println("TableStockValue generateDataTable() MalformedURLException " + "URL : " + url + " " + e);
			}
			catch (IOException e)
			{
				System.out.println("TableStockValue generateDataTable() IOException " + e);
			}
		}
		
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
			System.out.println("TableStockValue setUrlYahoo() FileNotFoundException " + "PATH : " + path + " " + e);
		}
		/*catch (MalformedURLException e)
		{
			System.out.println("TableStockValue setUrlYahoo() MalformedURLException " + "URL : " + url + " " + e);
		}*/
		catch (IOException e)
		{
			System.out.println("TableStockValue setUrlYahoo() IOException " + "PATH : " + path + " " + e);
		}
	}
}