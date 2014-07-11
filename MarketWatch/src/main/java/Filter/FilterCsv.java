/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Filter;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public final class FilterCsv extends FilterReader
{
	public FilterCsv (Reader input)
	{
		super (input);
	}
	
	@Override
	public int read (char [] cbuf, int off, int len) throws IOException
	{
		int read = super.read(cbuf, off, len);
		
		if (read == -1)
			return -1;
		
		int pos = off - 1;
		
		for (int readPos = off; readPos < off + read; readPos++)
		{
			if ((cbuf[readPos] != '+') && (cbuf[readPos] != '%'))
				pos++;
			else
				continue;
			
			if (pos < readPos)
				cbuf[pos] = cbuf[readPos];
		}
		
		return pos - off + 1;
	}
	
	@Override
	public int read () throws IOException
	{
		char [] buf = new char [1];
		int result = this.read(buf, 0, 1);
		
		if (result == -1)
			return -1;
		else
			return (int) buf[0];
	}
}