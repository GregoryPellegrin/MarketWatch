/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldETF
{
	SPY ("SPY"),
	SSO ("SSO"),
	SDS ("SDS"),
	
	VIX ("VIX"),
	TVIX ("TVIX"),
	
	GOLD ("Gold"),
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