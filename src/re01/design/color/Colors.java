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

package re01.design.color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author renaud
 */
public class Colors {
	
	public java.awt.Color getRgbColor( ColorTypeEnum color_type ) {
		java.awt.Color color;
		
		switch (color_type) {
			case Ash_gray:
				color = new java.awt.Color(178, 190, 181);
				break;
			case Black:
				color = new java.awt.Color(0, 0, 0);
				break;
			case BlackBean:
				color = new java.awt.Color(61, 12, 2);
				break;
			case BlackCoral:
				color = new java.awt.Color(84, 98, 111);
				break;
			case Blue:
				color = new java.awt.Color(0, 0, 255);
				break;
			case BlueBeau:
				color = new java.awt.Color(188, 212, 230);
				break;
			case BlueBDazzled:
				color = new java.awt.Color(46, 88, 148);
				break;
			case BlueBell:
				color = new java.awt.Color(162, 162, 208);
				break;
			case BlueGray:
				color = new java.awt.Color(102, 153, 204);
				break;
			case BlueSky:
				color = new java.awt.Color(135, 206, 235);
				break;
			case BlueVioletCrayola:
				color = new java.awt.Color(115, 102, 189);
				break;
			case BlueYonder:
				color = new java.awt.Color(80, 114, 167);
				break;
			case Bronze:
				color = new java.awt.Color(205, 127, 50);
				break;
			case DarkBlueGray:
				color = new java.awt.Color(102, 102, 153);
				break;
			case DarkSlateGray:
				color = new java.awt.Color(47, 79, 79);
				break;
			case Gainsboro:
				color = new java.awt.Color(220, 220, 220);
				break;
			case Gray:
				color = new java.awt.Color(190, 190, 190);
				break;
			case GrayDim:
				color = new java.awt.Color(105, 105, 105);
				break;
			case GrayLavender:
				color = new java.awt.Color(196, 195, 208);
				break;
			case GrayLight:
				color = new java.awt.Color(211, 211, 211);
				break;
			case GrayLightSlate:
				color = new java.awt.Color(119, 136, 153);
				break;
			case GrayMiddle:
				color = new java.awt.Color(139, 134, 128);
				break;
			case Green:
				color = new java.awt.Color(0, 255, 0);
				break;
			case GreenPigment:
				color = new java.awt.Color(0, 165, 80);
				break;
			case GreenYellow:
				color = new java.awt.Color(173, 255, 47);
				break;
			case MaroonWeb:
				color = new java.awt.Color(128, 0, 0);
				break;
			case MetallicSeaweed:
				color = new java.awt.Color(10, 126, 140);
				break;
			case Orange:
				color = new java.awt.Color(255, 127, 0);
				break;
			case Pink:
				color = new java.awt.Color(255, 192, 203);
				break;
			case Purple:
				color = new java.awt.Color(106, 13, 173);
				break;
			case Red:
				color = new java.awt.Color(255, 0, 0);
				break;
			case Teal:
				color = new java.awt.Color(0, 128, 128);
				break;
			case White:
				color = new java.awt.Color(255, 255, 255);
				break;
			case WhiteGhost:
				color = new java.awt.Color(248, 248, 255);
				break;
			case Yellow:
				color = new java.awt.Color(255, 255, 0);
				break;
			case YellowEarth:
				color = new java.awt.Color(225, 169, 95);
				break;
			case YellowMunsell:
				color = new java.awt.Color(239, 204, 0);
				break;
			case YellowNaples:
				color = new java.awt.Color(250, 218, 94);
				break;
			case YellowOrange:
				color = new java.awt.Color(255, 174, 66);
				break;
			case YellowTitanium:
				color = new java.awt.Color(238, 230, 0);
				break;
			default:
				color = null;
				break;
		}
		
		return color;
	}
	
	public Color getColorByRgbInt( Integer colorInt ) {
		Color color = null;
		
		ColorTypeEnum[] colorsTypes = ColorTypeEnum.values();
		int colorsTypesLength = colorsTypes.length;
		for ( int i = 0; i < colorsTypesLength; i++ ) {
			ColorTypeEnum colorTypeFound = colorsTypes[i];
			java.awt.Color colorFound = getRgbColor(colorTypeFound );
			if ( colorFound != null ) {
				Integer colorFoundByTypeInt = colorFound.getRGB();
				if ( Objects.equals(colorFoundByTypeInt, colorInt) == true ) {
					color = new Color( colorTypeFound, null );
					break;
				}
			}
		}
		
		return color;
	}
	
	public Color getColor( String colorTypeStr, ArrayList<Color> COLORS ) {
		Color colorFound = null;
		String colorTypeStrLower = colorTypeStr.toLowerCase();
		Iterator<Color> colorsAllowedIt = COLORS.iterator();
		while ( colorsAllowedIt.hasNext() ) {
			Color colorAllowed = colorsAllowedIt.next();
			if ( colorAllowed.getColorType().toString().toLowerCase().equals(colorTypeStrLower) == true ) {
				colorFound = colorAllowed;
				break;
			}
		}
		return colorFound;
	}
	
	public ColorTypeEnum getColorType( String colorName ) {
		ColorTypeEnum colorTypeFound = null;
		ColorTypeEnum[] colorsTypes = ColorTypeEnum.values();
		String colorNameLower = colorName.toLowerCase();
		for ( int i = 0; i < colorsTypes.length; i++ ) {
			ColorTypeEnum colorType = colorsTypes[i];
			if ( colorType.toString().toLowerCase().equals(colorNameLower) ) {
				colorTypeFound = colorType;
				break;
			}
		}
		return colorTypeFound;
	}
	
}
