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

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import re01.design.color.Color;
import re01.design.color.ColorAttributeTypeEnum;
import re01.design.color.ColorTypeEnum;
import re01.environment.Parameters;

/**
 *
 * @author renaud
 */
public class Font {
	
	private String fontFamilyName;
	private java.awt.Font font;
	private FontSize fontSize;
	private ArrayList<FontStyleEnum> fontsStyles;
	private ColorTypeEnum colorTypeForeground;
	private ColorTypeEnum colorTypeBackground;
	private SimpleAttributeSet simpleAttributeSet;
	
	public Font( String fontFamilyName, ArrayList<FontStyleEnum> fontsStyles, ColorTypeEnum colorTypeForeground, ColorTypeEnum colorTypeBackground ) {
		this.colorTypeForeground = colorTypeForeground;
		this.colorTypeBackground = colorTypeBackground;
		construct( fontFamilyName, fontsStyles );
	}
	
	public Font( String fontFamilyName, ArrayList<FontStyleEnum> fontsStyles ) {
		construct( fontFamilyName, fontsStyles );
	}
	
	public Font( String fontFamilyName ) {
		construct( fontFamilyName, null );
	}
	
	private void construct( String fontFamilyName, ArrayList<FontStyleEnum> fontsStyles ) {
		this.fontFamilyName = fontFamilyName;
		this.fontsStyles = fontsStyles;
		
		FontStyleEnum fontStyle = FontStyleEnum.SizeNormal;
		int fontStyleAwt = java.awt.Font.PLAIN;

		if ( this.fontsStyles != null ) {
			Iterator<FontStyleEnum> fontsStylesIt = this.fontsStyles.iterator();
			while ( fontsStylesIt.hasNext() ) {
				FontStyleEnum fontStyleFound = fontsStylesIt.next();
				switch ( fontStyleFound ) {
					case Title1:
					case Title2:
					case Title3:
						fontStyle = fontStyleFound;
						break;
					case Italic:
						fontStyleAwt = java.awt.Font.ITALIC;
					case Bold:
						fontStyleAwt = java.awt.Font.BOLD;
						break;
				}
			}
		}
		
		if ( this.fontFamilyName != null ) {
			this.font = new java.awt.Font( this.fontFamilyName, fontStyleAwt, new FontSize(Parameters.getThemeSelected(), fontStyle).getSize() );
		} else {
			this.font = null;
		}
		this.fontSize = new FontSize( Parameters.getThemeSelected(), fontStyle );
		
		this.simpleAttributeSet = createSimpleAttributeSet( this.fontsStyles, this.colorTypeForeground, this.colorTypeBackground );
	}
	
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	// G & S
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================

	public String getFontFamilyName() {
		return fontFamilyName;
	}

	public void setFontFamilyName(String fontFamilyName) {
		this.fontFamilyName = fontFamilyName;
	}

	public java.awt.Font getFont() {
		return font;
	}

	public void setFont( java.awt.Font font ) {
		this.font = font;
	}

	public FontSize getFontSize() {
		return fontSize;
	}

	public void setFontSize(FontSize fontSize) {
		this.fontSize = fontSize;
	}

	public ArrayList<FontStyleEnum> getFontsStyles() {
		return fontsStyles;
	}

	public void setFontsStyles(ArrayList<FontStyleEnum> fontsStyles) {
		this.fontsStyles = fontsStyles;
	}

	public SimpleAttributeSet getSimpleAttributeSet() {
		return simpleAttributeSet;
	}

	public void setSimpleAttributeSet(SimpleAttributeSet simpleAttributeSet) {
		this.simpleAttributeSet = simpleAttributeSet;
	}
	
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	// Create
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	//====================
	
	public SimpleAttributeSet createSimpleAttributeSet( ArrayList<FontStyleEnum> fontsStyles, ColorTypeEnum colorTypeForeground, ColorTypeEnum colorTypeBackground ) {
		SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
		if ( fontsStyles != null ) {
			Iterator<FontStyleEnum> fontsStylesIt = fontsStyles.iterator();
			while ( fontsStylesIt.hasNext() ) {
				FontStyleEnum fontStyle = fontsStylesIt.next();

				if ( fontStyle != null ) {
					switch ( fontStyle ) {
						case Bold:
							simpleAttributeSet = Parameters.getThemeSelected().defineTextAttributeStrong( simpleAttributeSet );
							break;
						case Italic:
							simpleAttributeSet = Parameters.getThemeSelected().defineTextAttributeEm( simpleAttributeSet );
							break;
						case Underline:
							simpleAttributeSet = Parameters.getThemeSelected().defineTextAttributeUnderline( simpleAttributeSet );
							break;
						case Title1:
						case Title2:
						case Title3:
							simpleAttributeSet = Parameters.getThemeSelected().defineTextAttributeTitle( simpleAttributeSet, fontStyle );
							break;
						case OsFolder:
							simpleAttributeSet = Parameters.getThemeSelected().defineTextAttributeOsFolder( simpleAttributeSet );
							break; 
						case Link:
							simpleAttributeSet = Parameters.getThemeSelected().defineTextAttributeLink( simpleAttributeSet );
							break;
					}
				}
			}
		}

		if ( colorTypeForeground != null ) {
			StyleConstants.setForeground(simpleAttributeSet, new Color( colorTypeForeground, ColorAttributeTypeEnum.Foreground ).getRgbColor() );
		}
		if ( colorTypeBackground != null ) {
			StyleConstants.setBackground(simpleAttributeSet, new Color( colorTypeBackground, ColorAttributeTypeEnum.Background ).getRgbColor() );
		}

		return simpleAttributeSet;
	}
	
}
