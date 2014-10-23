/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldYahooFinance
{
	URL ("http://finance.yahoo.com/d/quotes.csv?s="),
	STOCK_SEPARATOR ("+"),
	ADD_COLUMN ("&f="),
	
	DOW_JONES ("^DJI"),
	NASDAQ ("^IXIC"),
	SP500 ("^GSPC"),
	TSX100 ("^GSPTSE"),
	FTSE100 ("^FTSE"),
	CAC40 ("^FCHI"),
	DAX30 ("^GDAXI"),
	NIKKEI225 ("^N225"),
	HKSE ("^HSI"),
	
	VIX ("^VIX"),
	BDI ("^GDAXI"),
	GOLD ("GCM14.CMX"),
	
	SPY ("SPY"),
	UPRO ("UPRO"),
	SDS ("SDS"),
	TVIX ("TVIX"),
	GLD ("GLD"),
	SLV ("SLV"),
	
	USD_EUR ("USDEUR=X"),
	USD_CAD ("USDCAD=X"),
	USD_YEN ("USDJPY=X"),
	USD_YUAN ("USDCNH=X"),
	
	SYMBOL ("s"),
	LAST_PRICE ("l1"),
	CHANGE_DOLLAR ("c1"),
	CHANGE_PERCENT ("p2"),
	VOLUME ("v"),
	AVERAGE_VOLUME ("a2");
	
	private String field = "";
	
	FieldYahooFinance (String field)
	{
		this.field = field;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
}