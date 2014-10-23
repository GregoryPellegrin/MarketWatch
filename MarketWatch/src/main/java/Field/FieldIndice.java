/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldIndice
{
	TIME ("Time"),
	
	DOW_JONES ("Dow Jones"),
	NASDAQ ("Nasdaq"),
	SP500 ("S&P 500"),
	
	TSX100 ("TSX 100"),
	
	FTSE100 ("FTSE 100"),
	CAC40 ("CAC 40"),
	DAX30 ("DAX 30"),
	
	NIKKEI225 ("Nikkei 225"),
	HKSE ("HKSE"),
	
	VIX ("VIX"),
	BDI ("BDI"),
	GOLD ("Gold");
	
	private String field = "";
	
	FieldIndice (String field)
	{
		this.field = field;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
}