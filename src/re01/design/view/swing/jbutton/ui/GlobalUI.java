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

package re01.design.view.swing.jbutton.ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalButtonUI;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class GlobalUI extends MetalButtonUI {
	
	public GlobalUI() {
	
	}
	
	@Override
	public void paint(Graphics g, JComponent c) {
		final Color color1 = new Color(230, 255, 255, 0);
		final Color color2 = new Color(255, 230, 255, 64);
		final Color alphaColor = new Color(200, 200, 230, 64);
		final Color color3 = new Color( alphaColor.getRed(), alphaColor.getGreen(), alphaColor.getBlue(), 0 );
		final Color color4 = new Color( alphaColor.getRed(), alphaColor.getGreen(), alphaColor.getBlue(), 64 );
		super.paint(g, c);
		
		Graphics2D g2D = (Graphics2D) g;
		GradientPaint gradient1 = new GradientPaint( 0.0F, (float) c.getHeight() / (float) 2, color1, 0.0F, 0.0F, color2 );
		Rectangle rect1 = new Rectangle(0, 0, c.getWidth(), c.getHeight() / 2);
		g2D.setPaint(gradient1);
		g2D.fill(rect1);
		GradientPaint gradient2 = new GradientPaint( 0.0F, (float) c.getHeight() / (float) 2, color3, 0.0F, c.getHeight(), color4 );
		Rectangle rect2 = new Rectangle(0, c.getHeight() / 2, c.getWidth(), c.getHeight());
		g2D.setPaint(gradient2);
		g2D.fill(rect2);
    }

	@Override
	public void paintButtonPressed(Graphics g, AbstractButton b) {
		paintText(g, b, b.getBounds(), b.getText());
		g.setColor( Parameters.getThemeSelected().getButtonBackgroundActiveColor().getRgbColor().brighter() );
		g.fillRect(0, 0, b.getSize().width, b.getSize().height);
	}

	public void paintBorder(Graphics g) {
		
	}

	@Override
	protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
		
	}
}
