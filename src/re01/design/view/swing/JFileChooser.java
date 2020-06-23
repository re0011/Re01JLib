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

import java.util.ArrayList;
import java.util.Arrays;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JFileChooser extends javax.swing.JFileChooser {
	
	private static final String ARG_KEY_PATH_SELECTED = "re01_design_view_utils_directory_selection_path_selected";
	
	public JFileChooser() {
		super( new java.io.File(".") );
		construct();
	}
	
	public JFileChooser( String currentDirectoryPath ) {
		super( currentDirectoryPath );
		construct();
	}
	
	private void construct() {
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.SizeNormal))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.SizeNormal );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.PLAIN, fontSize.getSize() );
		}
		setFont( font );
	}

	public static final String get_ARG_KEY_PATH_SELECTED() {
		return ARG_KEY_PATH_SELECTED;
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
