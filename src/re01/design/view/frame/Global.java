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

package re01.design.view.frame;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import re01.design.view.swing.JScrollPane;
import re01.exception.Re01JLibException;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class Global extends JFrame {
	
	protected final int COMPONENT_MARGIN_WIDTH = Parameters.get_RECOMMENDED_COMPONENT_MARGIN_WIDTH();
	protected final int COMPONENT_MARGIN_HEIGHT = Parameters.get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT();
	protected final int COMPONENT_SPACING_SIZE = Parameters.get_RECOMMENDED_COMPONENT_SPACING_WIDTH();
	
	private WindowAdapter windowAdapter;
	
	protected Object[] callbacksArgs;
	protected Boolean isResizable = true;
	
	protected JScrollPane scrollCenter = null;
	
	public Global( String viewTitle, Object[] callbacksArgs ) throws Re01JLibException {
		super();
		this.callbacksArgs = callbacksArgs;
		
		String programIconPath = Parameters.getProgramIconPath();
		if ( programIconPath != null ) {
			try {
				URL urlImage = ClassLoader.getSystemResource( programIconPath );
				Image image = new ImageIcon( urlImage ).getImage();
				setIconImage( image );
			} catch( Exception e ) { }
		}
		
		setTitle( viewTitle );
		
		windowAdapter = new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if ( scrollCenter != null ) {
					scrollCenter.getViewport().setViewPosition(new Point(0, 0));
					scrollCenter.revalidate();
				}
				
				super.windowOpened( e );
			}
		};
		this.addWindowListener( windowAdapter );
	}
	
	@Override
	protected void finalize() throws Throwable {
		try {
			this.removeWindowListener( windowAdapter );
		} catch (Exception e) { }
		
		super.finalize();
	}
	
	public void display() {
		this.pack();
		this.setLocationRelativeTo( null );
	}
	
	public void resume(){
		this.setVisible( true );
		this.setEnabled( true );
		this.setAlwaysOnTop( false );
		this.setResizable(isResizable );
	}
	
	public void delete() {
		this.setVisible( false );
		
		// Important : after that, the GC can work.
		this.dispose();
	}
	
}
