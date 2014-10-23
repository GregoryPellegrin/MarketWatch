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
	
	SYMBOL ("s"),//s s0(Real)
	LAST_PRICE ("l1"),
	CHANGE_PERCENT ("p2"),//p2 c0 k2(Real time)
	VOLUME ("v"),//v v0
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