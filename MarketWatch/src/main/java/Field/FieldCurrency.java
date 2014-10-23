/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Field;

public enum FieldCurrency
{
	USD_EUR ("USD/EUR"),
	USD_CAD ("USD/CAD"),
	USD_YEN ("USD/YEN"),
	USD_YUAN ("USD/YUAN");
	
	private String field = "";
	
	FieldCurrency (String field)
	{
		this.field = field;
	}
	
	@Override
	public String toString ()
	{
		return this.field;
	}
}