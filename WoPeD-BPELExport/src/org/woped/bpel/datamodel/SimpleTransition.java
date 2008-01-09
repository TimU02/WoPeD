package org.woped.bpel.datamodel;

import org.oasisOpen.docs.wsbpel.x20.process.executable.TActivity;
import org.woped.core.model.petrinet.TransitionModel;

public class SimpleTransition extends Transition<TransitionModel>
{

	public SimpleTransition(TransitionModel data)
	{
		super(data);
	}

	@Override
	public boolean equals(AbstractElement e)
	{
		if (!SimpleTransition.class.isInstance(e))
			return false;
		if (this.getData().getId() != ((SimpleTransition) e).getData().getId())
			return false;
		return true;
	}

	@Override
	public TActivity getBpelCode()
	{
		return null;
	}
	
	public String toString()
	{
		return SimpleTransition.class.getSimpleName() + " Stored element " + this.getData().getId();
	}

}