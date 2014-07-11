/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldNasdaq
{
	URL_NYSE ("ftp://ftp.nasdaqtrader.com/symboldirectory/otherlisted.txt"),
	URL_NASDAQ ("ftp://ftp.nasdaqtrader.com/symboldirectory/nasdaqlisted.txt"),
	
	PATH_NYSE ("C:\\Users\\Gregory\\Downloads\\otherlisted.txt"),
	PATH_NASDAQ ("C:\\Users\\Gregory\\Downloads\\nasdaqlisted.txt"),
	
	URL_LIMIT (150),//200 max
	EOF_REGEX ("File Creation Time*.+"),
	
	DAY_IN_MILLISECOND (86400000);
	
	private String field = "";
	private int number = 0;
	
	FieldNasdaq (String field)
	{
		this.field = field;
	}
	
	FieldNasdaq (int number)
	{
		this.number = number;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
	
	public int toInt ()
	{
		return this.number;
	}
}