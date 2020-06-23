/*
 * Copyright (C) 2020 LE CLERRE Renaud
 *
 * This file is part of Re01JLib.
 *
 * Re01JLib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Re01JLib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Re01JLib. If not, see <http://www.gnu.org/licenses/>.
 */

package re01.design.view.swing;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.Action;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JMenuItem extends javax.swing.JMenuItem {

	private HashSet<ActionListener> actionsListeners = new HashSet<>();
	
	public JMenuItem( String text, int mnemonic ) {
		super( text, mnemonic );
		construct();
	}

	public JMenuItem( Action a ) {
		super( a );
		construct();
	}
	
	private void construct() {
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.SizeNormal))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.SizeNormal );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.PLAIN, fontSize.getSize() );
		}
		setFont( font );
		
		this.setMargin(new Insets(
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT(), 
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_WIDTH(), 
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT(), 
			re01.environment.Parameters.get_RECOMMENDED_COMPONENT_MARGIN_WIDTH()
		));
		
		this.setForeground( Parameters.getThemeSelected().getMenuItemForegroundColor().getRgbColor() );
		this.setBackground( Parameters.getThemeSelected().getMenuItemBackgroundColor().getRgbColor() );
	}
	
	@Override
	protected void finalize() throws Throwable {
		Iterator<ActionListener> actionsListenersIt = actionsListeners.iterator();
		while ( actionsListenersIt.hasNext() ) {
			ActionListener listener = actionsListenersIt.next();
			super.removeActionListener( listener );
		}
		actionsListeners.clear();
		
		super.finalize();
	}
	
	@Override
	public void addActionListener( ActionListener listener ) {
		if ( actionsListeners != null )
			actionsListeners.add( listener );
		super.addActionListener( listener );
	}
	
	@Override
	public void removeActionListener( ActionListener listener ) {
		if ( actionsListeners != null )
			actionsListeners.remove( listener );
		super.removeActionListener( listener );
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
