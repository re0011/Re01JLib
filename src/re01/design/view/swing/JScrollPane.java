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

import java.awt.Component;

/**
 *
 * @author renaud
 */
public class JScrollPane extends javax.swing.JScrollPane {
	
	public JScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super( view, vsbPolicy, hsbPolicy );
		construct();
	}
	
	public JScrollPane( Component view ) {
		super( view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		construct();
	}
	
	public JScrollPane() {
		super( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		construct();
	}
	
	private void construct() {
		JScrollBar scrollBarVertical = new JScrollBar(1);
		this.setVerticalScrollBar( scrollBarVertical );

		JScrollBar scrollBarHorizontal = new JScrollBar(0);
		this.setHorizontalScrollBar( scrollBarHorizontal );
		
		this.setBorder( null );
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
