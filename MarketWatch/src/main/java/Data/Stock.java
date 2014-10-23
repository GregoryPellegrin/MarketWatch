/*
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

package Data;

public class Stock
{
	private String symbol;
	private String lastPrice;
	private String changePercent;
	private String volume;
	private String averageVolume;
	
	public Stock (String symbol, String lastPrice, String changePercent, String volume, String averageVolume)
	{
		this.symbol = symbol;
		this.lastPrice = lastPrice;
		this.changePercent = changePercent;
		this.volume = volume;
		this.averageVolume = averageVolume;
	}

	public String getSymbol ()
	{
		return this.symbol;
	}

	public String getLastPrice ()
	{
		return this.lastPrice;
	}

	public String getChangePercent ()
	{
		return this.changePercent;
	}

	public String getVolume ()
	{
		return this.volume;
	}

	public String getAverageVolume ()
	{
		return this.averageVolume;
	}

	public void setSymbol (String symbol)
	{
		this.symbol = symbol;
	}

	public void setLastPrice (String lastPrice)
	{
		this.lastPrice = lastPrice;
	}

	public void setChangePercent (String changePercent)
	{
		this.changePercent = changePercent;
	}

	public void setVolume (String volume)
	{
		this.volume = volume;
	}

	public void setAverageVolume (String averageVolume)
	{
		this.averageVolume = averageVolume;
	}
}