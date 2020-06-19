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

package re01.design.view.swing.jscrollbar;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JComponent;
import re01.design.view.swing.JButton;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class ScrollBarHorizontalUI extends ScrollBarUI {
	
	@Override
	protected void paintTrack( Graphics g, JComponent compo, Rectangle rect ) {
		super.paintTrack( g, compo, rect );
		
		compo.setPreferredSize( new Dimension(2, Parameters.get_RECOMMENDED_SCROLL_BAR_SIZE()) );
	}
	
	@Override
	protected JButton createDecreaseButton(int arg0) {
		JButton button  = new JButton("<");
		button.setFocusable( false );
		return button;
	}

	@Override
	protected JButton createIncreaseButton(int arg0) {
		JButton button  = new JButton(">");
		button.setFocusable( false );
		return button;
	}
	
}
