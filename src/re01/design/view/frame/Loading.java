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

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import re01.design.view.awt.BorderLayout;
import re01.design.view.component.Title1;
import re01.design.view.component.Title3;
import re01.design.view.swing.BoxLayout;
import re01.design.view.swing.JPanel;
import re01.design.view.swing.JProgressBar;
import re01.design.view.swing.JScrollPane;
import re01.exception.Re01JLibException;
import re01.environment.Parameters;
import re01.tool.helper.system.MethodHelper;

/**
 *
 * @author renaud
 */
public class Loading extends Global {
	
	private static final String ARG_KEY_STEP_ID = "re01lib_view_loading_step_id";
	private static final String ARG_KEY_STEPS_LENGTH = "re01lib_view_loading_steps_length";
	
	private WindowAdapter windowAdapter = null;
	
	private final Loading THIS;
	
	private JProgressBar progressBar;
	
	public Loading( String viewTitle, int progressBarLength, Object[] callbacksArgs ) throws Re01JLibException {
		super(viewTitle, callbacksArgs);
		THIS = this;
		construct( viewTitle, progressBarLength );
	}
	
	public Loading( String viewTitle, Object[] callbacksArgs ) throws Re01JLibException {
		super(viewTitle, callbacksArgs);
		THIS = this;
		construct( viewTitle, null );
	}
	
	private void construct( String viewTitle, Integer progressBarLength ) throws Re01JLibException {
		windowAdapter = new WindowAdapter() {
			@Override
			public void windowClosing( java.awt.event.WindowEvent windowEvent ) {
				THIS.delete();
				
				super.windowClosing( windowEvent );
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
		
		Title1 titleLabel = new Title1( viewTitle );
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
		
		String labelStr = Parameters.getLanguageSelected().get_LANG().get_PROGRESS();
		
		Integer stepId = MethodHelper.getArgInteger( ARG_KEY_STEP_ID, callbacksArgs );
		Integer stepsLength = MethodHelper.getArgInteger( ARG_KEY_STEPS_LENGTH, callbacksArgs );
		if ( stepId != null && stepsLength != null ) {
			labelStr += " (" + stepId + "/" + stepsLength + ")";
		}
		
		Title3 label = new Title3( labelStr );
		subPanelCenter.add( label );
		
		if ( progressBarLength != null )
			progressBar = new JProgressBar( 0, progressBarLength );
		else {
			progressBar = new JProgressBar();
			progressBar.setIndeterminate( true );
		}
		subPanelCenter.add( progressBar );
		
		panelCenter.add( subPanelCenter, "North" );
		
		scrollCenter = new JScrollPane( panelCenter );
		
		this.add(scrollCenter, "Center" );
		
		//====================
		// end region center
		//====================
		
		display();
	}

	public static final String get_ARG_KEY_STEP_ID() {
		return ARG_KEY_STEP_ID;
	}

	public static final String get_ARG_KEY_STEPS_LENGTH() {
		return ARG_KEY_STEPS_LENGTH;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar( JProgressBar progressBar ) {
		this.progressBar = progressBar;
	}
	
}
