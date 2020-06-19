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

package re01.design.font;

import java.awt.GraphicsEnvironment;

/**
 *
 * @author renaud
 */
public class Fonts {
	
	public String getFontFamilyGlobal(){
		String fontName = null;
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = graphicsEnvironment.getAvailableFontFamilyNames();
		String fontNameFound;
		String liberationMonoFound = null;
		String ubuntuLightFound = null;
		String ubuntuMonoFound = null;
		String dejaVuSansFound = null;
		String verdanaFound = null;
		int fontsLength = fonts.length;
		for ( int f = 0; f < fontsLength; f++ ){
			fontNameFound = fonts[f];
			switch ( fontNameFound ) {
			case ( "Liberation Mono" ) :
				liberationMonoFound = fontNameFound;
				break;
			case ( "Ubuntu Light" ) :
				ubuntuLightFound = fontNameFound;
				break;
			case ( "Ubuntu Mono" ) :
				ubuntuMonoFound = fontNameFound;
				break;
			case ( "DejaVu Sans" ) :
				dejaVuSansFound = fontNameFound;
				break;
			case ( "Verdana" ) :
				verdanaFound = fontNameFound;
				break;
			}
			if ( liberationMonoFound != null && ubuntuLightFound != null && ubuntuMonoFound != null && dejaVuSansFound != null && verdanaFound != null )
				break;
		}
		if ( liberationMonoFound != null )
			fontName = liberationMonoFound;
		else if ( ubuntuLightFound != null )
			fontName = ubuntuLightFound;
		else if ( ubuntuMonoFound != null )
			fontName = ubuntuMonoFound;
		else if ( dejaVuSansFound != null )
			fontName = dejaVuSansFound;
		else if ( verdanaFound != null )
			fontName = verdanaFound;
		return fontName;
	}
}
