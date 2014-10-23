/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldBloomberg
{
	URL ("http://www.bloomberg.com/quote/BDIY:IND"),
	
	LAST_PRICE_PATTERN ("itemprop=\"price\""),
	CHANGE_PERCENT_PATTERN ("itemprop=\"priceChangePercent\""),
	
	ITEM_FIND_PATTERN ("content=");
	
	private String field = "";
	
	FieldBloomberg (String field)
	{
		this.field = field;
		int test;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
}