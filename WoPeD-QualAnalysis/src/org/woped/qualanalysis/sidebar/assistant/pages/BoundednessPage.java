package org.woped.qualanalysis.sidebar.assistant.pages;

import org.woped.qualanalysis.sidebar.SideBar;
import org.woped.qualanalysis.sidebar.assistant.components.BeginnerPanel;
import org.woped.translations.Messages;


/**
 * shows boundedness details
 * 
 * @author Lennart Oess, Arthur Vetter, Jens Tessen, Heiko Herzog
 * 
 */
@SuppressWarnings("serial")
public class BoundednessPage extends BeginnerPanel {

	private int numUnboundedPlaces;

	public BoundednessPage(BeginnerPanel previous, SideBar sideBar) {
		super(previous, sideBar, Messages
				.getString(PREFIX + "BoundednessAnalysis"));

		numUnboundedPlaces = qualanalysisService.getNumUnboundedPlaces();

		if (numUnboundedPlaces != 0) {
			status = false;
		}
	}

	@Override
	public void addComponents() {
		createEntry(PREFIX + "NumUnboundedPlaces", qualanalysisService
				.getUnboundedPlacesIterator(), numUnboundedPlaces,
				PREFIX_HELP + "UnboundedPlaces",
				PREFIX_EXAMPLE + "UnboundedPlaces");
		createEmptyEntry();
	}

}