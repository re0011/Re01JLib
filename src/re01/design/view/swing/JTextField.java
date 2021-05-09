/*
 * Copyright (C) 2020-2021 LE CLERRE Renaud
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

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JTextField extends javax.swing.JTextField {
	
	private HashSet<KeyListener> keysListeners = new HashSet<>();
	private HashSet<MouseListener> mousesListeners = new HashSet<>();

	public JTextField() {
		super();
		construct();
	}

	public JTextField( String text ) {
		super(text);
		construct();
	}
	
	private void construct() {
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.SizeNormal))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.SizeNormal );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.PLAIN, fontSize.getSize() );
		}
		setFont( font );
	}
	
	@Override
	protected void finalize() throws Throwable {
		Iterator<KeyListener> keysListenersIt = keysListeners.iterator();
		while ( keysListenersIt.hasNext() ) {
			KeyListener listener = keysListenersIt.next();
			super.removeKeyListener( listener );
		}
		keysListeners.clear();
		
		Iterator<MouseListener> mousesListenersIt = mousesListeners.iterator();
		while ( mousesListenersIt.hasNext() ) {
			MouseListener listener = mousesListenersIt.next();
			super.removeMouseListener( listener );
		}
		mousesListeners.clear();
		
		super.finalize();
	}
	
	@Override
	public void addKeyListener( KeyListener listener ) {
		if ( keysListeners != null )
			keysListeners.add( listener );
		super.addKeyListener( listener );
	}
	
	@Override
	public void removeKeyListener( KeyListener listener ) {
		if ( keysListeners != null )
			keysListeners.remove( listener );
		super.removeKeyListener( listener );
	}
	
	@Override
	public void addMouseListener( MouseListener listener ) {
		if ( mousesListeners != null )
			mousesListeners.add( listener );
		super.addMouseListener( listener );
	}
	
	@Override
	public void removeMouseListener( MouseListener listener ) {
		if ( mousesListeners != null )
			mousesListeners.remove( listener );
		super.removeMouseListener( listener );
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
