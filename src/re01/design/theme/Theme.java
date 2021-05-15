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

package re01.design.theme;

import java.util.ArrayList;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import re01.design.color.Color;
import re01.design.color.ColorAttributeTypeEnum;
import re01.design.color.ColorTypeEnum;
import re01.design.font.Font;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;

/**
 *
 * @author renaud
 */
public class Theme {
	
	private ThemeTypeEnum themeType;
	
	private Color foregroundColor;
	private Color backgroundColor;
	private Color backgroundColorAlternate;
	private Color linkColor;
	private Color selectedTextColor;
	private Color selectionColor;
	private Color osFolderColor;
	
	private Color panelBackgroundColor;
	
	private Color popupForegroundColor;
	private Color popupBackgroundColor;
	private Color popupMenuForegroundColor;
	private Color popupMenuBackgroundColor;
	
	private Color menuItemForegroundColor;
	private Color menuItemBackgroundColor;
	
	private Color buttonForegroundColor;
	private Color buttonForegroundActiveColor;
	private Color buttonForegroundOverColor;
	private Color buttonBackgroundColor;
	private Color buttonBackgroundActiveColor;
	private Color buttonBackgroundOverColor;
	
	public Theme( ThemeTypeEnum themeType ) {
		this.themeType = themeType;
		
		switch ( this.themeType ) {
			case MetalSlate:
				this.foregroundColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.backgroundColor = new Color( ColorTypeEnum.GrayLight, ColorAttributeTypeEnum.Background );
				this.backgroundColorAlternate = new Color( ColorTypeEnum.Gainsboro, ColorAttributeTypeEnum.Background );
				this.linkColor = new Color( ColorTypeEnum.Blue, ColorAttributeTypeEnum.Foreground );
				this.selectedTextColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.selectionColor = new Color( ColorTypeEnum.BlueBell, ColorAttributeTypeEnum.Background );
				this.osFolderColor = new Color( ColorTypeEnum.Bronze, ColorAttributeTypeEnum.Background );
				this.panelBackgroundColor = new Color( ColorTypeEnum.Gray, ColorAttributeTypeEnum.Background );
				this.popupForegroundColor = new Color( ColorTypeEnum.DarkBlueGray, ColorAttributeTypeEnum.Foreground );
				this.popupBackgroundColor = new Color( ColorTypeEnum.Gainsboro, ColorAttributeTypeEnum.Background );
				this.popupMenuForegroundColor = new Color( ColorTypeEnum.Gray, ColorAttributeTypeEnum.Foreground );
				this.popupMenuBackgroundColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.menuItemForegroundColor = new Color( ColorTypeEnum.DarkSlateGray, ColorAttributeTypeEnum.Foreground );
				this.menuItemBackgroundColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.buttonForegroundColor = new Color( ColorTypeEnum.GrayLight, ColorAttributeTypeEnum.Foreground );
				this.buttonForegroundActiveColor = new Color( ColorTypeEnum.Green, ColorAttributeTypeEnum.Foreground );
				this.buttonForegroundOverColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Foreground );
				this.buttonBackgroundColor = new Color( ColorTypeEnum.BlackCoral, ColorAttributeTypeEnum.Background );
				this.buttonBackgroundActiveColor = new Color( ColorTypeEnum.DarkSlateGray, ColorAttributeTypeEnum.Background );
				this.buttonBackgroundOverColor = new Color( ColorTypeEnum.GrayLightSlate, ColorAttributeTypeEnum.Background );
				break;
			default:
				this.foregroundColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.backgroundColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.backgroundColorAlternate = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.linkColor = new Color( ColorTypeEnum.Blue, ColorAttributeTypeEnum.Foreground );
				this.selectedTextColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.selectionColor = new Color( ColorTypeEnum.BlueBell, ColorAttributeTypeEnum.Background );
				this.osFolderColor = new Color( ColorTypeEnum.YellowNaples, ColorAttributeTypeEnum.Background );
				this.panelBackgroundColor = new Color( ColorTypeEnum.Gray, ColorAttributeTypeEnum.Background );
				this.popupForegroundColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.popupBackgroundColor = new Color( ColorTypeEnum.GrayLight, ColorAttributeTypeEnum.Background );
				this.popupMenuForegroundColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.popupMenuBackgroundColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.menuItemForegroundColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.menuItemBackgroundColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.buttonForegroundColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.buttonForegroundActiveColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.buttonForegroundOverColor = new Color( ColorTypeEnum.Black, ColorAttributeTypeEnum.Foreground );
				this.buttonBackgroundColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.buttonBackgroundActiveColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				this.buttonBackgroundOverColor = new Color( ColorTypeEnum.White, ColorAttributeTypeEnum.Background );
				break;
		}
	}
	
	public Font createFont( ArrayList<FontStyleEnum> fontsStyles, ColorTypeEnum colorTypeForeground, ColorTypeEnum colorTypeBackground ) {
		re01.design.font.Fonts fonts = new re01.design.font.Fonts();
		return new Font( fonts.getFontFamilyGlobal(), fontsStyles, colorTypeForeground, colorTypeBackground );
	}
	
	public Font createFont() {
		re01.design.font.Fonts fonts = new re01.design.font.Fonts();
		return new Font( fonts.getFontFamilyGlobal() );
	}
	
	public Font createFont( ArrayList<FontStyleEnum> fontsStyles ) {
		re01.design.font.Fonts fonts = new re01.design.font.Fonts();
		return new Font( fonts.getFontFamilyGlobal(), fontsStyles );
	}
	
	public ThemeTypeEnum getThemeType() {
		return themeType;
	}

	public Color getForegroundColor() {
		return foregroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Color getBackgroundColorAlternate() {
		return backgroundColorAlternate;
	}

	public Color getLinkColor() {
		return linkColor;
	}

	public Color getSelectedTextColor() {
		return selectedTextColor;
	}

	public Color getSelectionColor() {
		return selectionColor;
	}

	public Color getOsFolderColor() {
		return osFolderColor;
	}

	public Color getPanelBackgroundColor() {
		return panelBackgroundColor;
	}

	public Color getPopupForegroundColor() {
		return popupForegroundColor;
	}

	public Color getPopupBackgroundColor() {
		return popupBackgroundColor;
	}

	public Color getPopupMenuForegroundColor() {
		return popupMenuForegroundColor;
	}

	public Color getPopupMenuBackgroundColor() {
		return popupMenuBackgroundColor;
	}

	public Color getMenuItemForegroundColor() {
		return menuItemForegroundColor;
	}

	public Color getMenuItemBackgroundColor() {
		return menuItemBackgroundColor;
	}

	public Color getButtonForegroundColor() {
		return buttonForegroundColor;
	}

	public Color getButtonForegroundActiveColor() {
		return buttonForegroundActiveColor;
	}

	public Color getButtonForegroundOverColor() {
		return buttonForegroundOverColor;
	}

	public Color getButtonBackgroundColor() {
		return buttonBackgroundColor;
	}

	public Color getButtonBackgroundActiveColor() {
		return buttonBackgroundActiveColor;
	}

	public Color getButtonBackgroundOverColor() {
		return buttonBackgroundOverColor;
	}
	
	public SimpleAttributeSet defineTextAttributeStrong( SimpleAttributeSet simpleAttributeSet ) {
		switch ( themeType ) {
			case MetalSlate:
				StyleConstants.setBold( simpleAttributeSet, true );
				break;
		}
		return simpleAttributeSet;
	}
	
	public SimpleAttributeSet defineTextAttributeEm( SimpleAttributeSet simpleAttributeSet ) {
		switch ( themeType ) {
			case MetalSlate:
				StyleConstants.setItalic( simpleAttributeSet, true );
				break;
		}
		return simpleAttributeSet;
	}
	
	public SimpleAttributeSet defineTextAttributeUnderline( SimpleAttributeSet simpleAttributeSet ) {
		switch ( themeType ) {
			case MetalSlate:
				StyleConstants.setUnderline( simpleAttributeSet, true );
				break;
		}
		return simpleAttributeSet;
	}
	
	public SimpleAttributeSet defineTextAttributeTitle( SimpleAttributeSet simpleAttributeSet, FontStyleEnum fontStyle ) {
		FontSize fontSize = new FontSize( this, fontStyle );
		switch ( themeType ) {
			case MetalSlate:
				StyleConstants.setFontSize(simpleAttributeSet, fontSize.getSize());
				break;
		}
		return simpleAttributeSet;
	}
	
	public SimpleAttributeSet defineTextAttributeOsFolder( SimpleAttributeSet simpleAttributeSet ) {
		switch ( themeType ) {
			case MetalSlate:
				StyleConstants.setForeground(simpleAttributeSet, getOsFolderColor().getRgbColor() );
				break;
		}
		return simpleAttributeSet;
	}
	
	public SimpleAttributeSet defineTextAttributeLink( SimpleAttributeSet simpleAttributeSet ) {
		switch ( themeType ) {
			case MetalSlate:
				StyleConstants.setForeground( simpleAttributeSet, getLinkColor().getRgbColor() );
				StyleConstants.setUnderline( simpleAttributeSet, true );
				break;
		}
		return simpleAttributeSet;
	}
	
}
