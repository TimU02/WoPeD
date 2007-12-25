package org.woped.bpel.datamodel;

public class IfTransition extends TerminalElement
{

	public IfTransition(AbstractElement begin)
	{
		super("test");
	}

	@Override
	public boolean equals(AbstractElement e)
	{
		if(!IfTransition.class.isInstance(e)) return false;
		if(this.getID() != e.getID()) return false;
		return true;
	}

	@Override
	public String getBpelCode()
	{
		return null;
	}

}
