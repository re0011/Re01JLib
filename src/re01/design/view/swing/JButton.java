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

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.design.view.swing.jbutton.ui.GlobalUI;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JButton extends javax.swing.JButton {
	
	private HashSet<MouseListener> mousesListeners = new HashSet<>();
	private HashSet<ActionListener> actionsListeners = new HashSet<>();
	
	private ChangeListener changeListener = null;
	private FocusListener focusListener = null;
	private KeyListener keyListener = null;
	
	public JButton() {
		super();
		construct();
	}
	
	public JButton( String text ) {
		super( text );
		construct();
	}
	
	public JButton( Icon icon ) {
		super( icon );
		construct();
	}
	
	public JButton( String text, Icon icon ) {
		super( text, icon );
		construct();
	}
	
	private void construct() {
		final JButton THIS = this;

		setFocusPainted(false);
		setBorderPainted(true);
		setUI(new GlobalUI() );
		
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
		
		setForeground( Parameters.getThemeSelected().getButtonForegroundColor().getRgbColor() );
		setBackground( Parameters.getThemeSelected().getButtonBackgroundColor().getRgbColor() );

		changeListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JButton button = (JButton) e.getSource();
				ButtonModel buttonModel = button.getModel();
				if ( buttonModel.isRollover() ) {
					setForeground( Parameters.getThemeSelected().getButtonForegroundOverColor().getRgbColor() );
					setBackground( Parameters.getThemeSelected().getButtonBackgroundOverColor().getRgbColor() );
				} else {
					setForeground( Parameters.getThemeSelected().getButtonForegroundColor().getRgbColor() );
					setBackground( Parameters.getThemeSelected().getButtonBackgroundColor().getRgbColor() );
				}
			}
		};
		this.addChangeListener( changeListener );
		
		focusListener = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				setForeground( Parameters.getThemeSelected().getButtonForegroundOverColor().getRgbColor() );
				setBackground( Parameters.getThemeSelected().getButtonBackgroundOverColor().getRgbColor() );
			}

			@Override
			public void focusLost(FocusEvent e) {
				setForeground( Parameters.getThemeSelected().getButtonForegroundColor().getRgbColor() );
				setBackground( Parameters.getThemeSelected().getButtonBackgroundColor().getRgbColor() );
			}
		};
		this.addFocusListener( focusListener );
		
		keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if ( Objects.equals(e.getKeyCode(), KeyEvent.VK_ENTER) == true ) {
					THIS.doClick();
				}
			}
		};
		this.addKeyListener( keyListener );
	}
	
	@Override
	protected void finalize() throws Throwable {
		Iterator<MouseListener> mousesListenersIt = mousesListeners.iterator();
		while ( mousesListenersIt.hasNext() ) {
			MouseListener listener = mousesListenersIt.next();
			super.removeMouseListener( listener );
		}
		mousesListeners.clear();
		
		Iterator<ActionListener> actionsListenersIt = actionsListeners.iterator();
		while ( actionsListenersIt.hasNext() ) {
			ActionListener listener = actionsListenersIt.next();
			super.removeActionListener( listener );
		}
		actionsListeners.clear();
		
		try {
			removeChangeListener( changeListener );
		} catch (Exception e) { }
		try {
			removeFocusListener( focusListener );
		} catch (Exception e) { }
		try {
			removeKeyListener( keyListener );
		} catch (Exception e) { }
		
		super.finalize();
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
