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
import javax.swing.KeyStroke;
import javax.swing.text.DefaultEditorKit;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JTextArea extends javax.swing.JTextArea {
	
	public JTextArea() {
		super();
		construct();
	}
	
	public JTextArea( String text ) {
		super( text );
		construct();
	}
	
	private void construct() {
		this.setBackground( Parameters.getThemeSelected().getBackgroundColor().getRgbColor() );
		this.setSelectedTextColor( Parameters.getThemeSelected().getSelectedTextColor().getRgbColor() );//Foreground
		this.setSelectionColor( Parameters.getThemeSelected().getSelectionColor().getRgbColor() );//Background
		
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.SizeNormal))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.SizeNormal );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.PLAIN, fontSize.getSize() );
		}
		setFont( font );

		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setForeground( Parameters.getThemeSelected().getPopupMenuForegroundColor().getRgbColor() );
		popupMenu.setBackground( Parameters.getThemeSelected().getPopupMenuBackgroundColor().getRgbColor() );

		DefaultEditorKit.CopyAction copy = new DefaultEditorKit.CopyAction();
		copy.putValue( DefaultEditorKit.CopyAction.NAME, Parameters.getLanguageSelected().get_LANG().get_COPY() );
		copy.putValue( DefaultEditorKit.CopyAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke( Parameters.getLanguageSelected().get_LANG().get_COPY_KEYSTROKE() ) );

		JMenuItem menuItem = new JMenuItem( copy );
		menuItem.setForeground( Parameters.getThemeSelected().getMenuItemForegroundColor().getRgbColor() );
		menuItem.setBackground( Parameters.getThemeSelected().getMenuItemBackgroundColor().getRgbColor() );
		popupMenu.add( menuItem );
		this.setComponentPopupMenu( popupMenu );
		
		this.setEditable( false );
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
