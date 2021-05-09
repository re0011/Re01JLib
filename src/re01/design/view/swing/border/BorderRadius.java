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

package re01.design.view.swing.border;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import re01.design.view.swing.Border;

/**
 *
 * @author renaud
 */
public class BorderRadius extends Border {
	
	private int radius;
	private int paddingTop;
	private int paddingLeft;
	private int paddingRight;
	private int paddingBottom;

	public BorderRadius( int radius, int paddingTop, int paddingLeft, int paddingRight, int paddingBottom ) {
		this.radius = radius;
		this.paddingTop = paddingTop;
		this.paddingLeft = paddingLeft;
		this.paddingRight = paddingRight;
		this.paddingBottom = paddingBottom;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(this.paddingTop + 1, this.paddingLeft + 1, this.paddingBottom + 2, this.paddingRight);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}
	
}
