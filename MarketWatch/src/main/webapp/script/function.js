/**
 * Gregory Pellegrin
 * pellegrin.gregory.work@gmail.com
 */

function error (response)
{
	if (response.isError())
	{
		alert(response.getMessage() + ' ' + response.getDetailedMessage());
		
		return;
	}
}