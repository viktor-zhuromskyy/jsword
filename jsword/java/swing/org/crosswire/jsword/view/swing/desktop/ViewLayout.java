
package org.crosswire.jsword.view.swing.desktop;

import java.awt.Component;

import org.crosswire.jsword.view.swing.book.BibleViewPane;

/**
 * Abstract manager of how we layout views.
 * 
 * <p><table border='1' cellPadding='3' cellSpacing='0'>
 * <tr><td bgColor='white' class='TableRowColor'><font size='-7'>
 *
 * Distribution Licence:<br />
 * JSword is free software; you can redistribute it
 * and/or modify it under the terms of the GNU General Public License,
 * version 2 as published by the Free Software Foundation.<br />
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br />
 * The License is available on the internet
 * <a href='http://www.gnu.org/copyleft/gpl.html'>here</a>, or by writing to:
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 * MA 02111-1307, USA<br />
 * The copyright to this program is held by it's authors.
 * </font></td></tr></table>
 * @see docs.Licence
 * @author Joe Walker [joe at eireneh dot com]
 * @version $Id$
 */
public abstract class ViewLayout
{
    /**
     * Simple ctor that puts us in contact with a Desktop to manage
     */
    public ViewLayout(Desktop tools)
    {
        this.tools = tools;
    }

    /**
     * What should the desktop add to the parent?
     */
    public abstract Component getRootComponent();

    /**
     * Add a view to the set while visible
     */
    public abstract boolean add(BibleViewPane view);

    /**
     * Remove a view from the set while visible
     */
    public abstract boolean remove(BibleViewPane view);

    /**
     * Update a view from the set while visible
     */
    public abstract void updateTitle(BibleViewPane view);

    /**
     * While visible, which is the current pane
     */
    public abstract BibleViewPane getSelected();

    /**
     * Accessor for the Desktop that we are managing
     */
    public Desktop getDesktop()
    {
        return tools;
    }

    private Desktop tools;
}
