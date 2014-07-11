/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldGoogleFinance
{
	URL ("https://www.google.com/finance?q="),
	DOW_JONES ("DJI"),
	
	LAST_PRICE_PATTERN ("id=\"ref_983582_l\">"),
	CHANGE_PERCENT_PATTERN ("id=\"ref_983582_cp\">");
	
	private String field = "";
	
	FieldGoogleFinance (String field)
	{
		this.field = field;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
}