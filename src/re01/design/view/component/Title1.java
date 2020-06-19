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

package re01.design.view.component;

import java.util.ArrayList;
import java.util.Arrays;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.design.view.swing.JLabel;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class Title1 extends JLabel {

	public Title1( String text ) {
		super( text );
		setAlignmentX( CENTER_ALIGNMENT );
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.Title1, FontStyleEnum.Bold))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.Title1 );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.BOLD, fontSize.getSize() );
		}
		setFont( font );
	}
	
}
