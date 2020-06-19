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

package re01.design.view.swing.jtextpane;

import java.util.HashMap;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author renaud
 */
public class DocumentHistory {
	
	private String text;
	private HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> style;
	private Integer caretPosition;
	
	public DocumentHistory( String text, HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> style, Integer caretPosition ) {
		this.text = text;
		this.style = style;
		this.caretPosition = caretPosition;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> getStyle() {
		return style;
	}

	public void setStyle(HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> style) {
		this.style = style;
	}

	public Integer getCaretPosition() {
		return caretPosition;
	}

	public void setCaretPosition(Integer caretPosition) {
		this.caretPosition = caretPosition;
	}
	
}
