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

package re01.design.view.swing.jprogressbar;

import java.awt.Dimension;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class GlobalProgressBarUI extends javax.swing.plaf.basic.BasicProgressBarUI {
	
	@Override
	protected Dimension getPreferredInnerHorizontal() {
		return new Dimension( Parameters.get_RECOMMENDED_WINDOW_WIDTH(), Parameters.get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT() * 8 );
	}
	
	@Override
	protected Dimension getPreferredInnerVertical() {
		return new Dimension( Parameters.get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT() * 3, Parameters.get_RECOMMENDED_WINDOW_HEIGHT() );
	}
	
}
