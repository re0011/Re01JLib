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
import java.util.ArrayList;
import java.util.Arrays;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JCheckBox extends javax.swing.JCheckBox {
	
	public JCheckBox() {
		super();
		construct();
	}
	
	public JCheckBox( String text ) {
		super( text );
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
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
