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

package re01.design.view.frame;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import re01.design.view.awt.BorderLayout;
import re01.design.view.component.Paragraph;
import re01.design.view.component.Title3;
import re01.design.view.swing.BoxLayout;
import re01.design.view.swing.JButton;
import re01.design.view.swing.JLabel;
import re01.design.view.swing.JPanel;
import re01.design.view.swing.JScrollPane;
import re01.exception.Re01JLibException;
import re01.environment.Parameters;
import re01.program.user.KeyEnterReleasedEvent;
import re01.program.user.KeyEnterReleasedEventListener;
import re01.program.user.Listener;
import re01.tool.helper.system.MethodHelper;

/**
 *
 * @author renaud
 */
public class Alert extends Global {
	
	private static final String ARG_KEY_ALERT_MESSAGE = "re01lib_view_alert_message";
	
	private WindowAdapter windowAdapter = null;
	
	private KeyEnterReleasedEventListener keyEnterReleasedEventListener = null;
	
	private JButton buttonOk = null;
	
	private final Alert THIS;
	
	public Alert( String viewTitle, Object[] callbacksArgs ) throws Re01JLibException {
		super(viewTitle, callbacksArgs);
		THIS = this;
		construct( viewTitle );
	}
	
	private void construct( String viewTitle ) throws Re01JLibException {
		windowAdapter = new WindowAdapter() {
			@Override
			public void windowClosing( WindowEvent windowEvent ) {
				THIS.delete();
				super.windowClosing( windowEvent );
			}
			
			@Override
			public void windowOpened( WindowEvent windowEvent ) {
				if ( Listener.getIsEnterPressed() == true ) {
					keyEnterReleasedEventListener = new KeyEnterReleasedEventListener() {
						public void eventOccurred(KeyEnterReleasedEvent evt) {
							SwingUtilities.invokeLater( new Runnable() {
								@Override
								public void run() {
									if ( buttonOk != null )
										buttonOk.requestFocus();
								}
							} );
						}
					};
					Listener.addKeyEnterReleasedEventListener( keyEnterReleasedEventListener );
				} else {
					SwingUtilities.invokeLater( new Runnable() {
						@Override
						public void run() {
							if ( buttonOk != null )
								buttonOk.requestFocus();
						}
					} );
				}
				
				super.windowOpened( windowEvent );
			}
		};
		this.addWindowListener( windowAdapter );
		
		this.getContentPane().setLayout( new BorderLayout() );
		this.setPreferredSize(new Dimension( Parameters.get_RECOMMENDED_POPUP_WINDOW_WIDTH(), Parameters.get_RECOMMENDED_POPUP_WINDOW_HEIGHT() ));
		isResizable = true;
		
		//====================
		// region north
		//====================
		
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout( new BoxLayout( panelNorth, BoxLayout.PAGE_AXIS ) );
		
		Title3 titleLabel = new Title3( viewTitle );
		panelNorth.add( titleLabel );
		
		this.add( panelNorth, "North" );
		
		//====================
		// end region north
		//====================
		
		//====================
		// region center
		//====================
		
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout( new BorderLayout() );
		
		JPanel subPanelCenter = new JPanel();
		subPanelCenter.setLayout( new BoxLayout( subPanelCenter, BoxLayout.PAGE_AXIS ) );
		
		LinkedHashMap<ImageIcon, String> messagesMap = (LinkedHashMap<ImageIcon, String>) MethodHelper.getArg(ARG_KEY_ALERT_MESSAGE, callbacksArgs );
		if ( messagesMap == null )
			throw new Re01JLibException( Parameters.getLanguageSelected().get_LANG().get_TEXT_CONFIRM_MESSAGE_IS_NULL() );
		
		Set<Entry<ImageIcon, String>> messagesMapSet = messagesMap.entrySet();
		Iterator<Entry<ImageIcon, String>> messagesMapSetIt = messagesMapSet.iterator();
		Integer i = 0;
		while ( messagesMapSetIt.hasNext() ) {
			Entry<ImageIcon, String> messagesMapSetEntry = messagesMapSetIt.next();
			ImageIcon messageIcon = messagesMapSetEntry.getKey();
			String message = messagesMapSetEntry.getValue();
			
			JPanel panelMessage = new JPanel();
			panelMessage.setLayout( new BoxLayout( panelMessage, BoxLayout.LINE_AXIS ) );
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.1;
			constraints.insets = new Insets(2, 2, 2, 2);
			
			JLabel labelIcon = new JLabel( messageIcon );
			panelMessage.add( labelIcon );
			Paragraph pMessage = new Paragraph( message );
			if ( Objects.equals((i % 2), 0) == true )
				pMessage.setBackground( Parameters.getThemeSelected().getBackgroundColorAlternate().getRgbColor() );
			
			constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weightx = 0.9;
			constraints.insets = new Insets(2, 2, 2, 2);
			
			panelMessage.add( pMessage );
			subPanelCenter.add( panelMessage );
			i++;
		}
		
		panelCenter.add( subPanelCenter, "North" );
		
		scrollCenter = new JScrollPane( panelCenter );
		this.add(scrollCenter, "Center" );
		
		//====================
		// end region center
		//====================
		
		//====================
		// region south
		//====================
		
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout( new BoxLayout( panelSouth, BoxLayout.LINE_AXIS ) );
		panelSouth.setComponentOrientation( ComponentOrientation.RIGHT_TO_LEFT );
		
		panelSouth.add( Box.createRigidArea(new Dimension(COMPONENT_SPACING_SIZE, 0)) );
		
		buttonOk = new JButton( Parameters.getLanguageSelected().get_LANG().get_CLOSE() );
		buttonOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				THIS.delete();
				try {
					MethodHelper.executeCallbacks(callbacksArgs );
				} catch (Exception ex) { }
			}
		} );
		panelSouth.add( buttonOk );
		
		panelSouth.add( Box.createRigidArea(new Dimension(COMPONENT_SPACING_SIZE, 0)) );
		this.add( panelSouth, "South" );
		
		//====================
		// end region south
		//====================
		
		display();
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		try {
			this.removeWindowListener( windowAdapter );
		} catch (Exception e) { }
		try {
			Listener.removeKeyEnterReleasedEventListener( keyEnterReleasedEventListener );
		} catch (Exception e) { }
		
		super.finalize();
	}

	public static final String get_ARG_KEY_ALERT_MESSAGE() {
		return ARG_KEY_ALERT_MESSAGE;
	}
	
}
