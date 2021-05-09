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

package re01.design.view.swing.jbutton;

import re01.design.view.swing.JButton;
import re01.design.view.swing.border.BorderRadius;

/**
 *
 * @author renaud
 */
public class BorderRadiusButton extends JButton {
	
	Integer radius;
	
	public BorderRadiusButton( String text ) {
		super( text );
		construct();
	}
	
	public BorderRadiusButton() {
		construct();
	}
	
	public void construct() {
		radius = 12;
		
		this.setBorder(new BorderRadius(radius, 5, 20, 20, 5));
		this.setBorderPainted(true);
		this.setFocusable(true);
		this.setFocusPainted(true);
		this.setContentAreaFilled(false);
	}
	
}
