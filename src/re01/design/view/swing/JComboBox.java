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

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import re01.design.color.Color;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.design.view.swing.jcombobox.BulletColorItemRenderer;
import re01.design.view.swing.jcombobox.JComboBoxTypeEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JComboBox extends javax.swing.JComboBox {
	
	private HashSet<ActionListener> actionsListeners = new HashSet<>();
	
	private JComboBoxTypeEnum comboboxType;
	private DefaultComboBoxModel model;
	
	public JComboBox( JComboBoxTypeEnum comboboxType, String[] items, Integer maxWidth, Integer rowCount ) {
		super( items );
		construct( comboboxType, maxWidth, rowCount );
	}
	
	public JComboBox( JComboBoxTypeEnum comboboxType, Integer maxWidth, Integer rowCount ) {
		super();
		construct( comboboxType, maxWidth, rowCount );
	}
	
	public JComboBox( JComboBoxTypeEnum comboboxType, Integer maxWidth ) {
		super();
		construct( comboboxType, maxWidth, null );
	}
	
	private void construct( JComboBoxTypeEnum comboboxType, Integer maxWidth, Integer rowCount ) {
		this.comboboxType = comboboxType;
		switch ( this.comboboxType ) {
			case Colors:
			case ColorsOrImagesIcons:
			case ColorsWithLabel:
				model = new DefaultComboBoxModel();
				setModel(model);
				setRenderer(new BulletColorItemRenderer( this.comboboxType ) );
				break;
		}
		if ( rowCount != null )
			this.setMaximumRowCount( rowCount );
		if ( maxWidth != null )
			this.setMaximumSize( new Dimension(maxWidth, Integer.MAX_VALUE) );
		
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.SizeNormal))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.SizeNormal );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.PLAIN, fontSize.getSize() );
		}
		setFont( font );
	}
	
	@Override
	protected void finalize() throws Throwable {
		Iterator<ActionListener> actionsListenersIt = actionsListeners.iterator();
		while ( actionsListenersIt.hasNext() ) {
			ActionListener listener = actionsListenersIt.next();
			super.removeActionListener( listener );
		}
		actionsListeners.clear();
		
		super.finalize();
	}
	
	@Override
	public void addActionListener( ActionListener listener ) {
		if ( actionsListeners != null )
			actionsListeners.add( listener );
		super.addActionListener( listener );
	}
	
	@Override
	public void removeActionListener( ActionListener listener ) {
		if ( actionsListeners != null )
			actionsListeners.remove( listener );
		super.removeActionListener( listener );
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
	public void addItemsColors( ArrayList<Color> colors ) {
		Iterator<Color> colorsIt = colors.iterator();
		while ( colorsIt.hasNext() ) {
			Color color = colorsIt.next();
			Integer colorRgb = null;
			if ( color != null ) {
				colorRgb = color.getRgbColor().getRGB();
			}
			this.addItem( colorRgb );
		}
	}
	
	public void addItemsImagesIcons( ArrayList<ImageIcon> imagesIcons ) {
		Iterator<ImageIcon> imagesIconsIt = imagesIcons.iterator();
		while ( imagesIconsIt.hasNext() ) {
			ImageIcon imageIcon = imagesIconsIt.next();
			this.addItem( imageIcon );
		}
	}
	
}
