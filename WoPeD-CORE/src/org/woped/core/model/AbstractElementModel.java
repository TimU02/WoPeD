package org.woped.core.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgraph.graph.AttributeMap;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.woped.core.Constants;
import org.woped.core.model.petrinet.NameModel;
import org.woped.core.utilities.LoggerManager;

public abstract class AbstractElementModel extends DefaultGraphCell implements Serializable
{

	//! The following methods and members are used for
	//! structural analysis and not needed for anything else
	int m_marking = -1;
	public int getMarking() { return m_marking; }
	public void setMarking(int marking) { m_marking = marking; }
	
    private int            modelProcessorType = -1;
    private CreationMap    creationMap        = null;
    private String         id                 = null;
    private ElementContext elementContext     = null;
    private NameModel      nameModel          = null;
    private boolean 	   readOnly           = false;
    //! In order to be able to navigate between the different levels
    //! of element container (e.g. simple transition container of an operator)
    //! we need to store a reference to each owning container
    //! (a model element can be owned by more than one ModelElementContainer)
    //! This reference will be maintained by the ModelElementContainer methods
    //! addElement() and removeOnlyElement() which will call addOwningContainer() and
    //! removeOwningContainer() to do so.
    private Set owningContainers = new HashSet();
    public void addOwningContainer(ModelElementContainer owningContainer)
    {
    	owningContainers.add(owningContainer);    	
    }
    public void removeOwningContainer(ModelElementContainer owningContainer)
    {
    	owningContainers.remove(owningContainer);
    }
    public Iterator getOwningContainers()
    {
    	return owningContainers.iterator();
    }
    
    public AbstractElementModel(CreationMap creationMap, Object userObject, int modelProcessorType)
    {
        super(null);
        this.elementContext = new ElementContext();
        nameModel = new NameModel(creationMap);
        AttributeMap map = getAttributes();
        GraphConstants.setOpaque(map, false);
        if (creationMap.containsKey(CreationMap.READ_ONLY)
				&& (Boolean) creationMap.get(CreationMap.READ_ONLY)) {
			GraphConstants.setBorderColor(map, new Color(125, 125, 125));
		} else {
			GraphConstants.setBorderColor(map, Color.BLACK);
		}
        GraphConstants.setEditable(map, true);
        if (creationMap.getSize() == null)
        {
            GraphConstants.setSize(map, new Dimension(getDefaultWidth(), getDefaultHeight()));
        } else
        {
            GraphConstants.setSize(map, new Dimension(creationMap.getSize().getX1(), creationMap.getSize().getX2()));
        }
        GraphConstants.setMoveable(map, true);
        setAttributes(map);

        if (creationMap.getId() != null)
        {
            setId(creationMap.getId());
            if (creationMap.getPosition() != null)
            {
                setPosition(new Point(creationMap.getPosition().getX1(), creationMap.getPosition().getX2()));
            }

            if (creationMap.getNamePosition() != null)
            {
                nameModel.setPosition(new Point(creationMap.getNamePosition().getX1(), creationMap.getNamePosition().getX2()));
            } else if (getPosition() != null)
            {
                nameModel.setPosition(getPosition().x + ((this.getWidth() - getNameModel().getWidth()) / 2), getPosition().y + getHeight() + 5);
            }
        } else
        {
            LoggerManager.error(Constants.CORE_LOGGER, "It's not allowed to create a Element without id. Please use ModelElementFactory instead.");
        }

    }

    public CreationMap getCreationMap()
    {
        if (creationMap == null)
        {
            creationMap = CreationMap.createMap();
        }
        creationMap.setPosition(new IntPair(getPosition()));
        creationMap.setId(getId());
        creationMap.setName(getNameValue());
        creationMap.setSize(new IntPair(GraphConstants.getSize(getAttributes())));
        creationMap.setNamePosition(new IntPair(getNameModel().getPosition()));
        creationMap.setType(getType());
        return creationMap;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public abstract int getType();

    public DefaultPort getPort()
    {
        if (!isLeaf()) return (DefaultPort) getChildAt(0);
        else return null;
    }

    public int getX()
    {
        return (int) GraphConstants.getBounds(getAttributes()).getX();
    }

    public int getY()
    {
        return (int) GraphConstants.getBounds(getAttributes()).getY();
    }

    public abstract void setSize(Dimension dim);

    public abstract void setSize(int width, int height);

    public abstract int getHeight();

    public abstract int getWidth();

    public void setPosition(Point2D p)
    {
        setPosition((int) p.getX(), (int) p.getY());
    }

    public Point getPosition()
    {
        Rectangle2D rect = GraphConstants.getBounds(getAttributes());
        if (rect != null)
        {
            return new Point((int) rect.getX(), (int) rect.getY());
        }
        return null;
    }

    public void setPosition(int x, int y)
    {
        AttributeMap map = getAttributes();
        GraphConstants.setBounds(map, new Rectangle(x, y, getWidth(), getHeight()));
        getAttributes().applyMap(map);
    }

    public abstract String getToolTipText();

    public abstract int getDefaultWidth();

    public abstract int getDefaultHeight();

    public int getModelProcessorType()
    {
        return modelProcessorType;
    }

    public ElementContext getElementContext()
    {
        return elementContext;
    }

    public void setElementContext(ElementContext elementContext)
    {
        this.elementContext = elementContext;
    }

    public String getNameValue()
    {
        return (String) getNameModel().getUserObject();
    }

    public void setNameValue(String name)
    {

        getNameModel().setUserObject(name);
    }

    /**
     * Returns the nameModel.
     * 
     * @return NameModel
     */
    public NameModel getNameModel()
    {
        return nameModel;
    }

    /**
     * Sets the nameModel.
     * 
     * @param nameModel
     *            The nameModel to set
     */
    public void setNameModel(NameModel nameModel)
    {
        this.nameModel = nameModel;
    }
	public boolean isReadOnly() {
		return readOnly;
	}
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}
}
