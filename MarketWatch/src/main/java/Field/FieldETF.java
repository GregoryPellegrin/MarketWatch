/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldETF
{
	SPY ("SPY"),
	UPRO ("UPRO"),
	SDS ("SDS"),
	
	TVIX ("TVIX"),
	
	GLD ("GLD"),
	SLV ("SLV");
	
	private String field = "";
	
	FieldETF (String field)
	{
		this.field = field;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
}