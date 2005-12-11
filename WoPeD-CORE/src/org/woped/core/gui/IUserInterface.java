package org.woped.core.gui;

import java.awt.Component;
import java.awt.Rectangle;
import java.util.List;

import org.woped.core.controller.IEditor;

public interface IUserInterface
{
    public void addEditor(IEditor editor);

    public void removeEditor(IEditor editor);

    public void requestEditorFocus(IEditor editor);

    public void hideEditor(IEditor editor);

    public IEditor getEditorFocus();

    public List getAllEditors();

    public void quit();

    public Rectangle getBounds();

    public Component getComponent();

    public Component getPropertyChangeSupportBean();
    
    public void cascadeFrames();
    
    public void arrangeFrames();
}
