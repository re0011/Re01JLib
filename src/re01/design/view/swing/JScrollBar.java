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

import re01.design.view.swing.jscrollbar.ScrollBarHorizontalUI;
import re01.design.view.swing.jscrollbar.ScrollBarVerticalUI;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JScrollBar extends javax.swing.JScrollBar {
	
	public JScrollBar( int orientation ) {
		super( orientation );
		construct( orientation );
	}
	
	public JScrollBar() {
		construct( 1 );
	}
	
	private void construct( int orientation ) {
		switch ( orientation ) {
			case ( 0 ) :
				this.setUI( new ScrollBarHorizontalUI() );
				this.setUnitIncrement( Parameters.get_RECOMMENDED_SCROLL_HORIZONTAL_UNIT_INCREMENT() );
				this.setBlockIncrement( Parameters.get_RECOMMENDED_SCROLL_HORIZONTAL_UNIT_INCREMENT() * 4 );
				break;
			case ( 1 ) :
				this.setUI( new ScrollBarVerticalUI() );
				this.setUnitIncrement( Parameters.get_RECOMMENDED_SCROLL_VERTICAL_UNIT_INCREMENT() );
				this.setBlockIncrement( Parameters.get_RECOMMENDED_SCROLL_VERTICAL_UNIT_INCREMENT() * 4 );
				break;
		}
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
