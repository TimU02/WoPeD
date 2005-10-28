/*
 * 
 * Copyright (C) 2004-2005, see @author in JavaDoc for the author 
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * For contact information please visit http://woped.ba-karlsruhe.de
 *
 */
package org.woped.file;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;
import org.woped.core.config.ConfigurationManager;
import org.woped.core.model.ArcModel;
import org.woped.core.model.ModelElementContainer;
import org.woped.core.model.PetriNetModelProcessor;
import org.woped.core.model.petrinet.AbstractPetriNetModelElement;
import org.woped.core.model.petrinet.NameModel;
import org.woped.core.model.petrinet.OperatorTransitionModel;
import org.woped.core.model.petrinet.PetriNetModelElement;
import org.woped.core.model.petrinet.PlaceModel;
import org.woped.core.model.petrinet.ResourceClassModel;
import org.woped.core.model.petrinet.ResourceModel;
import org.woped.core.model.petrinet.TransitionModel;
import org.woped.core.model.petrinet.TransitionResourceModel;
import org.woped.core.model.petrinet.TriggerModel;
import org.woped.core.utilities.LoggerManager;
import org.woped.editor.controller.vc.EditorVC;
import org.woped.pnml.AnnotationGraphisType;
import org.woped.pnml.ArcNameType;
import org.woped.pnml.ArcToolspecificType;
import org.woped.pnml.ArcType;
import org.woped.pnml.DimensionType;
import org.woped.pnml.GraphicsArcType;
import org.woped.pnml.GraphicsNodeType;
import org.woped.pnml.GraphicsSimpleType;
import org.woped.pnml.NetToolspecificType;
import org.woped.pnml.NetType;
import org.woped.pnml.NodeNameType;
import org.woped.pnml.OperatorType;
import org.woped.pnml.OrganizationUnitType;
import org.woped.pnml.PlaceToolspecificType;
import org.woped.pnml.PlaceType;
import org.woped.pnml.PnmlDocument;
import org.woped.pnml.PnmlType;
import org.woped.pnml.PositionType;
import org.woped.pnml.ResourceMappingType;
import org.woped.pnml.ResourceType;
import org.woped.pnml.ResourcesType;
import org.woped.pnml.RoleType;
import org.woped.pnml.ToolspecificType;
import org.woped.pnml.TransitionResourceType;
import org.woped.pnml.TransitionToolspecificType;
import org.woped.pnml.TransitionType;
import org.woped.pnml.TriggerType;

/**
 * @author <a href="mailto:slandes@kybeidos.de">Simon Landes </a> <br>
 *         <br>
 * 
 * Created on: 13.01.2005 Last Change on: 13.01.2005
 */
public class PNMLExport {

	private PnmlDocument pnmlDoc = null;

	public static final String comment = "\nPLEASE DO NOT EDIT THIS FILE\nCreated with Workflow PetriNet Designer Version 1.0 (woped.org)\n";

	/**
	 * Method saveToFile. Saves a PetriNet Object to *.pnml File.
	 * 
	 * @param fileName
	 */
	public boolean saveToFile(EditorVC editor, String fileName) {
		LoggerManager.debug(Constants.FILE_LOGGER, "##### START PNML EXPORT #####");
		long begin = System.currentTimeMillis();
		try {
			createJavaBeansInstances(editor);
			XmlOptions opt = new XmlOptions();
			opt.setUseDefaultNamespace();
			opt.setSavePrettyPrint();
			opt.setSavePrettyPrintIndent(2);
			Map map = new HashMap();
			map.put("", "pnml.woped.org");
			opt.setSaveImplicitNamespaces(map);
			pnmlDoc.save(new File(fileName), opt);
			return true;
		} catch (IOException e) {
			LoggerManager.error(Constants.FILE_LOGGER, "Could not save file. " + e.getMessage());
			return false;
		} finally {
			LoggerManager.debug(Constants.FILE_LOGGER, "##### END PNML EXPORT ##### (" + (System.currentTimeMillis() - begin) + " ms)");
		}
	}

	private void createJavaBeansInstances(EditorVC editor) {
		ModelElementContainer elementContainer = editor.getModelProcessor().getElementContainer();
		PetriNetModelProcessor petrinetModel = (PetriNetModelProcessor) editor.getModelProcessor();
		PetriNetModelElement currentModel = null;
		pnmlDoc = PnmlDocument.Factory.newInstance();
		PnmlType iPnml = pnmlDoc.addNewPnml();
		// pnmlDoc.documentProperties();
		XmlCursor cursor = iPnml.newCursor();
		cursor.insertComment(comment);
		/* ##### NET ##### */
		NetType iNet = iPnml.addNewNet();
		// attr type
		iNet.setType(petrinetModel.getType());
		// attr id
		iNet.setId(petrinetModel.getId());
		// name
		if (petrinetModel.getName() != null) {
			iNet.addNewName().setText(petrinetModel.getName());
		}
		if (ConfigurationManager.getConfiguration().isExportToolspecific()) {
			NetToolspecificType iNetToolSpec = iNet.addNewToolspecific();

			iNetToolSpec.setTool("WoPeD");
			iNetToolSpec.setVersion("1.0");
			// graphics
			GraphicsSimpleType iGraphicsNet = iNetToolSpec.addNewBounds();
			if (editor.getSavedSize() != null) {
				DimensionType dim = iGraphicsNet.addNewDimension();
				dim.setX(new BigDecimal(editor.getSavedSize().getWidth()));
				dim.setY(new BigDecimal(editor.getSavedSize().getHeight()));
			}
			PositionType location = iGraphicsNet.addNewPosition();
			location.setX(new BigDecimal(editor.getLocation().getX()));
			location.setY(new BigDecimal(editor.getLocation().getY()));
			// resources
			ResourcesType iNetResources = iNetToolSpec.addNewResources();
			// Rescources
			ResourceType iResourceType;
			ResourceModel rModelTemp;
			for (Iterator iter = petrinetModel.getResources().iterator(); iter.hasNext();) {
				rModelTemp = (ResourceModel) iter.next();
				iResourceType = iNetResources.addNewResource();
				iResourceType.setName(rModelTemp.getName());
			}
			// Roles
			RoleType iRoleType;
			ResourceClassModel roleModelTemp;
			for (Iterator iter = petrinetModel.getRoles().iterator(); iter.hasNext();) {
				roleModelTemp = (ResourceClassModel) iter.next();
				iRoleType = iNetResources.addNewRole();
				iRoleType.setName(roleModelTemp.getName());
			}
			// Orga Units
			OrganizationUnitType iOrganizationUnitType;
			ResourceClassModel orgunitModelTemp;
			for (Iterator iter = petrinetModel.getOrganizationUnits().iterator(); iter.hasNext();) {
				orgunitModelTemp = (ResourceClassModel) iter.next();
				iOrganizationUnitType = iNetResources.addNewOrganizationUnit();
				iOrganizationUnitType.setName(orgunitModelTemp.getName());
			}
			// ResourceMap
			ResourceMappingType iNetResourceMap;
			Iterator iter = petrinetModel.getResourceMapping().keySet().iterator();
			while (iter.hasNext()) {
				String tempResourceClass = (String) iter.next();
				Vector values = (Vector) petrinetModel.getResourceMapping().get(tempResourceClass);
				// TODO check if mapping exists NullPointerExeption bei
				// speicherung gešnderter orgUnit die keine zugeordnete Resource
				// hat!
				for (Iterator iterator = values.iterator(); iterator.hasNext();) {
					iNetResourceMap = iNetResources.addNewResourceMapping();
					iNetResourceMap.setResourceClass(tempResourceClass);
					iNetResourceMap.setResourceID(iterator.next().toString());
				}

			}
			// toolspecific
			for (short i = 0; i < petrinetModel.getUnknownToolSpecs().size(); i++) {
				iNet.addNewToolspecific();
				if (petrinetModel.getUnknownToolSpecs().get(i) instanceof ToolspecificType) {
					iNet.setToolspecificArray(iNet.getToolspecificArray().length - 1, (NetToolspecificType) petrinetModel.getUnknownToolSpecs().get(i));
				}
			}
		}

		Iterator root2Iter = elementContainer.getRootElements().iterator();
		while (root2Iter.hasNext()) {
			currentModel = (PetriNetModelElement) root2Iter.next();
			/* ##### PLACES ##### */
			if (currentModel.getType() == PetriNetModelElement.PLACE_TYPE) {
				initPlace(iNet.addNewPlace(), (PlaceModel) currentModel);
			} else if (currentModel.getType() == PetriNetModelElement.TRANS_SIMPLE_TYPE)
			/* ##### TRANSITION ##### */
			{
				initTransition(iNet.addNewTransition(), (TransitionModel) currentModel, null);

			} else if (currentModel.getType() == PetriNetModelElement.SUBP_TYPE) {
				// entspricht Reference Transition mit Page
				initTransition(iNet.addNewTransition(), (TransitionModel) currentModel, null);
			} else if (currentModel.getType() == PetriNetModelElement.TRANS_OPERATOR_TYPE) {

				LoggerManager.debug(Constants.FILE_LOGGER, "   ... Setting InnerTtransitions for Operator (ID:" + currentModel.getId() + ")");
				OperatorTransitionModel operatorModel = (OperatorTransitionModel) currentModel;
				Iterator simpleTransIter = operatorModel.getSimpleTransContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_SIMPLE_TYPE).values().iterator();
				while (simpleTransIter.hasNext()) {
					PetriNetModelElement simpleTransModel = (PetriNetModelElement) simpleTransIter.next();
					if (simpleTransModel != null // Sometimes the iterator
							// returns null...
							&& operatorModel.getSimpleTransContainer().getElementById(simpleTransModel.getId()).getType() == PetriNetModelElement.TRANS_SIMPLE_TYPE) {
						initTransition(iNet.addNewTransition(), (TransitionModel) operatorModel.getSimpleTransContainer().getElementById(simpleTransModel.getId()), operatorModel);
					}

				}
				if (operatorModel.getCenterPlace() != null) {
					PlaceType iCenterPlace = initPlace(iNet.addNewPlace(), operatorModel.getCenterPlace());
					initToolspecific(iCenterPlace.addNewToolspecific(), operatorModel.getCenterPlace(), operatorModel.getId(), operatorModel.getOperatorType());
				}
				LoggerManager.debug(Constants.FILE_LOGGER, "   ... InnerTtransitions set.");
			}

		}
		/* ##### ARCS ##### */
		Iterator arcIter = elementContainer.getArcMap().keySet().iterator();
		ArcModel currentArc;
		ArcModel currentInnerArc;
		PetriNetModelElement currentTargetModel;
		PetriNetModelElement currentSourceModel;
		while (arcIter.hasNext()) {
			currentArc = elementContainer.getArcById(arcIter.next());
			currentTargetModel = (PetriNetModelElement) elementContainer.getElementById(currentArc.getTargetId());
			currentSourceModel = (PetriNetModelElement) elementContainer.getElementById(currentArc.getSourceId());
			if (currentTargetModel.getType() == PetriNetModelElement.TRANS_OPERATOR_TYPE) {
				Iterator innerArcIter = ((OperatorTransitionModel) currentTargetModel).getSimpleTransContainer().getArcMap().keySet().iterator();
				while (innerArcIter.hasNext()) {
					currentInnerArc = (ArcModel) ((OperatorTransitionModel) currentTargetModel).getSimpleTransContainer().getArcMap().get(innerArcIter.next());
					if (currentInnerArc.getSourceId().equals(currentSourceModel.getId())
							|| ((OperatorTransitionModel) currentTargetModel).getOperatorType() == OperatorTransitionModel.XOR_SPLITJOIN_TYPE) {
						initArc(iNet.addNewArc(), currentArc, currentInnerArc);
					}
				}
			} else if (currentSourceModel.getType() == PetriNetModelElement.TRANS_OPERATOR_TYPE) {
				Iterator innerArcIter = ((OperatorTransitionModel) currentSourceModel).getSimpleTransContainer().getArcMap().keySet().iterator();
				while (innerArcIter.hasNext()) {
					currentInnerArc = (ArcModel) ((OperatorTransitionModel) currentSourceModel).getSimpleTransContainer().getArcMap().get(innerArcIter.next());
					if (currentInnerArc.getTargetId().equals(currentTargetModel.getId())) {
						initArc(iNet.addNewArc(), currentArc, currentInnerArc);
					}
				}
			} else {
				initArc(iNet.addNewArc(), currentArc, null);
			}
		}
	}

	private PlaceType initPlace(PlaceType iPlace, PlaceModel currentModel) {
		// Name
		initNodeName(iPlace.addNewName(), currentModel.getNameModel());
		// initNodeName
		initElementGraphics(iPlace.addNewGraphics(), currentModel);
		// initalMarkings
		if (currentModel.getTokenCount() > 0) {
			iPlace.addNewInitialMarking().setText(String.valueOf(currentModel.getTokenCount()));
		}
		// toolspecific
		if (ConfigurationManager.getConfiguration().isExportToolspecific()) {
			for (short i = 0; i < currentModel.getUnknownToolSpecs().size(); i++) {
				iPlace.addNewToolspecific();
				if (currentModel.getUnknownToolSpecs().get(i) instanceof ToolspecificType) {
					iPlace.setToolspecificArray(iPlace.getToolspecificArray().length - 1, (PlaceToolspecificType) currentModel.getUnknownToolSpecs().get(i));
				}
			}
		}
		// attr. id
		iPlace.setId(currentModel.getId());
		LoggerManager.debug(Constants.FILE_LOGGER, "   ... Place (ID:" + currentModel.getId() + ") set");

		return iPlace;
	}

	private TransitionType initTransition(TransitionType iTransition, TransitionModel currentModel, OperatorTransitionModel operatorModel) {
		TransitionModel takenModel = operatorModel == null ? currentModel : operatorModel;
		// name
		initNodeName(iTransition.addNewName(), takenModel.getNameModel());
		// graphics
		initElementGraphics(iTransition.addNewGraphics(), takenModel);
		if (ConfigurationManager.getConfiguration().isExportToolspecific()) {
			// toolspecific
			for (short i = 0; i < takenModel.getUnknownToolSpecs().size(); i++) {
				iTransition.addNewToolspecific();
				if (takenModel.getUnknownToolSpecs().get(i) instanceof ToolspecificType) {
					iTransition.setToolspecificArray(iTransition.getToolspecificArray().length - 1, (TransitionToolspecificType) takenModel.getUnknownToolSpecs().get(i));
				}
			}
			initToolspecific(iTransition.addNewToolspecific(), takenModel);
		}
		// attr. id
		iTransition.setId(currentModel.getId());
		LoggerManager.debug(Constants.FILE_LOGGER, "   ... Transition (ID:" + currentModel.getId() + ") set");

		return iTransition;
	}

	private NodeNameType initNodeName(NodeNameType nodeName, NameModel element) {
		// name
		nodeName.setText(element.getNameValue());
		/*
		 * graphics
		 * 
		 * An annotation's graphics part requires an offset element describing
		 * the offset the lower left point of the surrounding text box has to
		 * the reference point of the net object on which the annotation occurs.
		 * TOD O:
		 */
		AnnotationGraphisType iGraphics = nodeName.addNewGraphics();
		PositionType pos = iGraphics.addNewOffset();
		pos.setX(BigDecimal.valueOf(element.getX()));
		pos.setY(BigDecimal.valueOf(element.getY()));

		return nodeName;
	}

	private GraphicsNodeType initElementGraphics(GraphicsNodeType iGraphics, AbstractPetriNetModelElement element) {
		DimensionType dim = iGraphics.addNewDimension();
		dim.setX(BigDecimal.valueOf(element.getWidth()));
		dim.setY(BigDecimal.valueOf(element.getHeight()));
		PositionType pos = iGraphics.addNewPosition();
		pos.setX(BigDecimal.valueOf(element.getX()));
		pos.setY(BigDecimal.valueOf(element.getY()));

		return iGraphics;
	}

	private PlaceToolspecificType initToolspecific(PlaceToolspecificType iToolspecific, PlaceModel currentModel, String operatorId, int operatorType) {
		iToolspecific.setTool("WoPeD");
		iToolspecific.setVersion("1.0");
		initOperator(iToolspecific.addNewOperator(), operatorId, operatorType);

		return iToolspecific;
	}

	private TransitionToolspecificType initToolspecific(TransitionToolspecificType iToolspecific, TransitionModel currentModel) {
		iToolspecific.setTool("WoPeD");
		iToolspecific.setVersion("1.0");
		if (currentModel.getToolSpecific().getOperatorId() != null) {
			initOperator(iToolspecific.addNewOperator(), currentModel.getToolSpecific().getOperatorId(), currentModel.getToolSpecific().getOperatorType());
			LoggerManager.debug(Constants.FILE_LOGGER, "   ... Operator for Transition (ID:" + currentModel.getId() + ") set");

		}
		if (currentModel.getToolSpecific().getTrigger() != null) {
			initTrigger(iToolspecific.addNewTrigger(), currentModel.getToolSpecific().getTrigger());
			LoggerManager.debug(Constants.FILE_LOGGER, "   ... Trigger for Transition (ID:" + currentModel.getId() + ") set");
		}
		if (currentModel.getToolSpecific().isSubprocess()) {
			iToolspecific.setSubprocess(true);
			LoggerManager.debug(Constants.FILE_LOGGER, "   ... Subprocess (ID:" + currentModel.getId() + ") set");
		}
		if (currentModel.getToolSpecific().getTransResource() != null) {
			initTransResource(iToolspecific.addNewTransitionResource(), currentModel.getToolSpecific().getTransResource());
			LoggerManager.debug(Constants.FILE_LOGGER, "   ... Resource for Transition (ID:" + currentModel.getId() + ") set");
		}
		return iToolspecific;
	}

	private OperatorType initOperator(OperatorType iOperator, String id, int type) {
		// attr. id
		iOperator.setId(id);
		// attr. type
		iOperator.setType(type);
		return iOperator;
	}

	private TriggerType initTrigger(TriggerType iTrigger, TriggerModel trigger) {
		// attr. id
		iTrigger.setId(trigger.getId());
		// attr. type
		iTrigger.setType(trigger.getTriggertype());
		// graphics
		GraphicsSimpleType iGraphics = iTrigger.addNewGraphics();
		DimensionType dim = iGraphics.addNewDimension();
		dim.setX(BigDecimal.valueOf(trigger.getWidth()));
		dim.setY(BigDecimal.valueOf(trigger.getHeight()));
		PositionType pos = iGraphics.addNewPosition();
		pos.setX(BigDecimal.valueOf(trigger.getX()));
		pos.setY(BigDecimal.valueOf(trigger.getY()));
		return iTrigger;
	}

	private TransitionResourceType initTransResource(TransitionResourceType iTransResource, TransitionResourceModel transResource) {
		// set Role & orgUnit
		iTransResource.setOrganizationalUnitName(transResource.getTransOrgUnitName());
		iTransResource.setRoleName(transResource.getTransRoleName());

		// graphics
		GraphicsSimpleType iGraphics = iTransResource.addNewGraphics();
		DimensionType dim = iGraphics.addNewDimension();
		dim.setX(BigDecimal.valueOf(transResource.getWidth()));
		dim.setY(BigDecimal.valueOf(transResource.getHeight()));
		PositionType pos = iGraphics.addNewPosition();
		pos.setX(BigDecimal.valueOf(transResource.getX()));
		pos.setY(BigDecimal.valueOf(transResource.getY()));
		return iTransResource;
	}

	private ArcType initArc(ArcType iArc, ArcModel outerArc, ArcModel innerArc) {
		ArcModel useArc = innerArc == null ? outerArc : innerArc;
		// inscription
		initNodeName(iArc.addNewInscription(), useArc);
		// graphics
		initArcGraphics(iArc.addNewGraphics(), outerArc);
		// attr. id
		iArc.setId(useArc.getId());
		// attr. source
		iArc.setSource(useArc.getSourceId());
		if (ConfigurationManager.getConfiguration().isExportToolspecific()) {
			if (outerArc.isRoute()) {
				ArcToolspecificType iArcTool = iArc.addNewToolspecific();
				iArcTool.setTool("WoPeD");
				iArcTool.setVersion("1.0");
				iArcTool.setRoute(true);
			}
			// toolspecific
			for (short i = 0; i < outerArc.getUnknownToolSpecs().size(); i++) {
				iArc.addNewToolspecific();
				if (outerArc.getUnknownToolSpecs().get(i) instanceof ToolspecificType) {
					iArc.setToolspecificArray(iArc.getToolspecificArray().length - 1, (ArcToolspecificType) outerArc.getUnknownToolSpecs().get(i));
				}
			}
		}
		// attr. target
		iArc.setTarget(useArc.getTargetId());
		LoggerManager.debug(Constants.FILE_LOGGER, "   ... Arc (ID:" + useArc.getId() + "( " + useArc.getSourceId() + " -> " + useArc.getTargetId() + ") set");

		return iArc;
	}

	private ArcNameType initNodeName(ArcNameType nodeName, ArcModel element) {
		// name
		nodeName.setText(element.getInscriptionValue());
		// graphics
		/*
		 * AnnotationGraphisType iGraphics = nodeName.addNewGraphics();
		 * PositionType pos = iGraphics.addNewOffset();
		 * pos.setX(BigDecimal.valueOf(element.getX()));
		 * pos.setY(BigDecimal.valueOf(element.getY())); pos =
		 * iGraphics.addNewOffset(); pos.setX(BigDecimal.valueOf(element.getX() +
		 * element.getWidth())); pos.setY(BigDecimal.valueOf(element.getY() +
		 * element.getHeight()));
		 */

		return nodeName;
	}

	private GraphicsArcType initArcGraphics(GraphicsArcType iGraphics, ArcModel arc) {
		// position
		if (arc.getPoints().length > 2) {
			PositionType pos;
			for (int i = 1; i < arc.getPoints().length - 1; i++) {
				pos = iGraphics.addNewPosition();
				pos.setX(BigDecimal.valueOf((int) arc.getPoints()[i].getX()));
				pos.setY(BigDecimal.valueOf((int) arc.getPoints()[i].getY()));
			}
		}
		// line
		// ...none

		return iGraphics;
	}
	//
	// private boolean isOperator(PetriNet net, String elementId)
	// {
	// if (elementId != null &&
	// net.getElementContainer().getElementById(elementId) != null
	// && net.getElementContainer().getElementById(elementId).getType() ==
	// PetriNetModelElement.TRANS_OPERATOR_TYPE)
	// {
	// return true;
	// } else
	// {
	// return false;
	// }
	// }
}