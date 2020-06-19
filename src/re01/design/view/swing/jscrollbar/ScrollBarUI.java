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

package re01.design.view.swing.jscrollbar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.plaf.basic.BasicScrollBarUI;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class ScrollBarUI extends BasicScrollBarUI {
	
	@Override
	protected void paintTrack( Graphics g, JComponent compo, Rectangle rect ) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g2d.fillRect( rect.x, rect.y, rect.width, rect.height );
		g2d.drawRect( rect.x, rect.y, rect.width, rect.height );
		g2d.dispose();
	}
	
	@Override
	protected void paintThumb( Graphics g, JComponent compo, Rectangle rect ) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		java.awt.Color colorRgb;
		JScrollBar sb = (JScrollBar) compo;
		if (!sb.isEnabled())
			return;
		else if (isDragging)
			colorRgb = Parameters.getThemeSelected().getButtonBackgroundOverColor().getRgbColor();
		else if (isThumbRollover())
			colorRgb = Parameters.getThemeSelected().getButtonBackgroundOverColor().getRgbColor();
		else
			colorRgb = Parameters.getThemeSelected().getButtonBackgroundColor().getRgbColor();
		
		g2d.setPaint( colorRgb );
		g2d.fillRect( rect.x, rect.y, rect.width, rect.height );
		g2d.setPaint( Parameters.getThemeSelected().getButtonBackgroundColor().getRgbColor() );
		g2d.dispose();
	}
	
}
