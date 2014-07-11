/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Filter;

import Field.FieldYahooFinance;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public final class FilterNasdaq extends FilterReader
{
	private boolean first;
	
	public FilterNasdaq (Reader input)
	{
		super (input);
		
		this.first = true;
	}
	
	@Override
	public int read () throws IOException
	{
		int character = super.read();
		
		if (this.first)
		{
			while (character != '\n')
				character = super.read();
			
			character = super.read();
			
			this.first = false;
		}
		
		if (character == -1)
			return -1;
		
		if (character == '.')
			return '-';
		
		if (character == '|')
		{
			character = super.read();
			
			while (character != '\n')
				character = super.read();
			
			return FieldYahooFinance.STOCK_SEPARATOR.toString().charAt(0);
		}
		
		return character;
	}
}