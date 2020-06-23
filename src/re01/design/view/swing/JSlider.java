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

import java.awt.Color;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Objects;
import javax.swing.JComponent;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class JSlider extends javax.swing.JSlider {
	
	public JSlider( int orientation, int min, int max, int value, ArrayList<Integer> rangeValues ){
		super( orientation, min, max, 1 );
		
		int defaultValueIndex = 0;
		ArrayList<String> rangeValuesStr = new ArrayList<>();
		Iterator<Integer> rangeValuesIt = rangeValues.iterator();
		while ( rangeValuesIt.hasNext() ) {
			Integer rangeValue = rangeValuesIt.next();
			if ( rangeValue < value )
				defaultValueIndex++;
			rangeValuesStr.add( rangeValue.toString() );
		}
		if ( Objects.equals(defaultValueIndex, 0) == true )
			defaultValueIndex = 1;
		
		setValue( defaultValueIndex );
		construct( rangeValuesStr );
	}
	
	private void construct( ArrayList<String> rangeValuesStr ) {
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		this.setCursor(cursor);
		this.setPaintLabels(true);
		this.setPaintTrack(true);
		this.setSnapToTicks(false);
		
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.SizeNormal))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.SizeNormal );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.PLAIN, fontSize.getSize() );
		}
		setFont( font );
		
		this.setBackground(Color.LIGHT_GRAY);
		
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		
		Iterator<String> rangeValuesStrIt = rangeValuesStr.iterator();
		Integer index = 1;
		while ( rangeValuesStrIt.hasNext() ) {
			String rangeValueStr = rangeValuesStrIt.next();
			JLabel label = new JLabel((rangeValueStr.length() < 4) ? rangeValueStr : rangeValueStr.substring(0, 3) + "...");
			label.setFullText(rangeValueStr);
			labels.put( index, label );
			index++;
		}
		this.setLabelTable( labels );
		
		Dictionary dictionary = this.getLabelTable();
		Enumeration enumeration = dictionary.elements();
		while ( enumeration.hasMoreElements() ) {
			Object obj = enumeration.nextElement();
			try {
				JComponent component = (JComponent) obj;
				component.setFont(font);
			} catch (Exception e) { }
		}
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
}
