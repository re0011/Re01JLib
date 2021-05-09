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

package re01.design.font;

import re01.design.theme.Theme;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class FontSize {
	
	private Theme theme;
	private FontStyleEnum fontStyle;
	private Integer charsBaseSize = null;
	
	public FontSize(Theme theme, FontStyleEnum fontStyle) {
		this.theme = theme;
		this.fontStyle = fontStyle;
	}
	
	public FontSize(Theme theme, FontStyleEnum fontStyle, int charsBaseSize) {
		this.theme = theme;
		this.fontStyle = fontStyle;
		this.charsBaseSize = charsBaseSize;
	}
	
	public Integer getSize() {
		int charsBaseSize = ( this.charsBaseSize == null ) ? Parameters.getCharsBaseSize() : this.charsBaseSize ;
		Integer size = charsBaseSize;
		
		switch ( theme.getThemeType() ) {
			case Undefined:
				switch ( fontStyle ) {
					case Title1:
						Double calcH1 = charsBaseSize * 3.2;
						size = calcH1.intValue();
						break;
					case Title2:
						Double calcH2 = charsBaseSize * 2.4;
						size = calcH2.intValue();
						break;
					case Title3:
						Double calcH3 = charsBaseSize * 1.6;
						size = calcH3.intValue();
						break;
					case SizeNormal:
						size = charsBaseSize;
						break;
				}
				break;
			case MetalSlate:
				switch ( fontStyle ) {
					case Title1:
						Double calcH1 = charsBaseSize * 3.2;
						size = calcH1.intValue();
						break;
					case Title2:
						Double calcH2 = charsBaseSize * 2.4;
						size = calcH2.intValue();
						break;
					case Title3:
						Double calcH3 = charsBaseSize * 1.6;
						size = calcH3.intValue();
						break;
					case SizeNormal:
						size = charsBaseSize;
						break;
				}
				break;
		}
		
		return size;
	}
	
}
