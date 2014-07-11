/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldStockValue
{
	SYMBOL ("Symbol"),
	LAST_PRICE ("Last"),
	
	CHANGE_PERCENT ("Net Change (%)"),
	CHANGE_DOLLAR ("Net Change ($)"),
	
	BID ("Bid"),
	ASK ("Ask"),
	
	OPEN ("Open"),
	CLOSE ("Prev Close"),
	
	HIGH ("High"),
	LOW ("Low"),
	WEEK_52_HIGH ("52 Week High"),
	WEEK_52_LOW ("52 Week Low"),
	
	VOLUME ("Volume"),
	AVERAGE_VOLUME ("Average Volume"),
	
	MARKET_CAP ("Market Cap");
	
	private String field = "";
	
	FieldStockValue (String field)
	{
		this.field = field;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
}