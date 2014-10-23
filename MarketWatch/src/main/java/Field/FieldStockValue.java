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
	
	VOLUME ("Volume"),
	AVERAGE_VOLUME ("Average Volume");
	
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