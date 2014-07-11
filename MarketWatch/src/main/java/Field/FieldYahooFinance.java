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
	FTSE100 ("^FTSE"),
	TSX100 ("^GSPTSE"),
	CAC40 ("^FCHI"),
	DAX30 ("^GDAXI"),
	NIKKEI225 ("^N225"),
	HKSE ("^HSI"),
	
	SPY ("SPY"),
	SSO ("SSO"),
	SDS ("SDS"),
	VIX ("VIX"),
	TVIX ("TVIX"),
	GOLD ("GCM14.CMX"),
	GLD ("GLD"),
	SLV ("SLV"),
	
	SYMBOL ("s"),//s s0(Real)
	LAST_PRICE ("l1"),
	CHANGE_PERCENT ("p2"),//p2 c0 k2(Real time)
	CHANGE_DOLLAR ("c1"),//c6 (Real time)
	BID ("b3"),//b0 b3(Real time)
	ASK ("b2"),//a0 b2(Real time)
	OPEN ("o"),//o o0
	CLOSE ("p"),//p p0
	HIGH ("h"),//h h0
	LOW ("g"),//g g0
	WEEK_52_HIGH ("k"),//k k0
	WEEK_52_LOW ("j"),//j j0
	VOLUME ("v"),//v v0
	AVERAGE_VOLUME ("a2"),
	MARKET_CAP ("j1");//j1 j3(Real time)
	
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