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

package re01.environment;

import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JComponent;
import re01.design.theme.Theme;
import re01.language.Language;
import re01.tool.helper.system.DesktopHelper;

/**
 *
 * @author renaud
 */
public class Parameters {
	
	private static final int DESKTOP_WIDTH = new DesktopHelper().get_DESKTOP_WIDTH();
	private static final int DESKTOP_HEIGHT = new DesktopHelper().get_DESKTOP_HEIGHT();
	
	private static final int DEFAULT_WINDOW_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(512) / new Float(1920)) ).intValue();
	private static final int PARAMETERS_WINDOW_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(1024) / new Float(1920)) ).intValue();
	private static final int POPUP_WINDOW_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(256) / new Float(1920)) ).intValue();
	private static final int MIN_WINDOW_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(256) / new Float(1920)) ).intValue();
	
	private static final int DEFAULT_WINDOW_HEIGHT = new Float( new Float(DESKTOP_HEIGHT) * (new Float(448) / new Float(1200)) ).intValue();
	private static final int PARAMETERS_WINDOW_HEIGHT = new Float( new Float(DESKTOP_HEIGHT) * (new Float(768) / new Float(1200)) ).intValue();
	private static final int POPUP_WINDOW_HEIGHT = new Float( new Float(DESKTOP_HEIGHT) * (new Float(256) / new Float(1200)) ).intValue();
	private static final int MIN_WINDOW_HEIGHT = new Float( new Float(DESKTOP_HEIGHT) * (new Float(256) / new Float(1200)) ).intValue();
	
	private static final int DESKTOP_WIDTH_MIN_FOR_STRICTS_VALUES = 1024;
	
	private static final int DEFAULT_CHARS_BASE_SIZE = new Float( new Float(DESKTOP_WIDTH) * (new Float(10) / new Float(1920))).intValue();
	private static final int MIN_CHARS_BASE_SIZE = new Float( new Float(DESKTOP_WIDTH) * (new Float(7) / new Float(1920)) ).intValue();
	private static final int MAX_CHARS_BASE_SIZE = new Float( new Float(DESKTOP_WIDTH) * (new Float(60) / new Float(1920)) ).intValue();
	private static final int STRICT_MIN_CHARS_BASE_SIZE = 7;
	
	private static final int DEFAULT_COMPONENT_SPACING_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(5) / new Float(1920)) ).intValue();
	private static final int MIN_COMPONENT_SPACING_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(3) / new Float(1920)) ).intValue();
	private static final int STRICT_MIN_COMPONENT_SPACING_WIDTH = 3;
	
	private static final int DEFAULT_COMPONENT_MARGIN_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(8) / new Float(1920)) ).intValue();
	private static final int MIN_COMPONENT_MARGIN_WIDTH = new Float( new Float(DESKTOP_WIDTH) * (new Float(3) / new Float(1920)) ).intValue();
	private static final int STRICT_MIN_COMPONENT_MARGIN_WIDTH = 2;
	
	private static final int DEFAULT_COMPONENT_MARGIN_HEIGHT = new Float( new Float(DESKTOP_WIDTH) * (new Float(2) / new Float(1920)) ).intValue();
	private static final int STRICT_MIN_COMPONENT_MARGIN_HEIGHT = 1;
	
	private static final int DEFAULT_ICONS_SIZE = new Float( new Float(DESKTOP_WIDTH) * (new Float(20) / new Float(1920))).intValue();
	private static final int MIN_ICONS_SIZE = new Float( new Float(DESKTOP_WIDTH) * (new Float(16) / new Float(1920))).intValue();
	private static final int MAX_ICONS_SIZE = new Float( new Float(DESKTOP_WIDTH) * (new Float(60) / new Float(1920))).intValue();
	private static final int STRICT_MIN_ICONS_SIZE = 16;
	
	private static final int DEFAULT_SCROLL_BAR_SIZE = get_RECOMMENDED_COMPONENT_MARGIN_WIDTH() * 2;
	private static final int DEFAULT_SCROLL_HORIZONTAL_UNIT_INCREMENT = new Float( new Float(DESKTOP_WIDTH) * (new Float(16) / new Float(1920)) ).intValue();
	private static final int DEFAULT_SCROLL_VERTICAL_UNIT_INCREMENT = new Float( new Float(DESKTOP_HEIGHT) * (new Float(16) / new Float(1200)) ).intValue();
	
	private static final int DEFAULT_DOCUMENT_HISTORY_LENGTH = 200;
	private static final int MIN_DOCUMENT_HISTORY_LENGTH = 1;
	private static final int MAX_DOCUMENT_HISTORY_LENGTH = 999;
	
	private static Theme themeSelected = null;
	private static Language languageSelected = null;
	private static Integer charsBaseSize = get_RECOMMENDED_CHARS_BASE_SIZE();
	private static Integer iconsSize = get_RECOMMENDED_ICONS_SIZE();
	private static String programIconPath = null;
	
	private static Integer documentHistoryLength = DEFAULT_DOCUMENT_HISTORY_LENGTH;
	
	//====================
	// region get
	//====================

	public static final int get_DEFAULT_WINDOW_WIDTH() {
		return DEFAULT_WINDOW_WIDTH;
	}

	public static final int get_PARAMETERS_WINDOW_WIDTH() {
		return PARAMETERS_WINDOW_WIDTH;
	}

	public static final int get_POPUP_WINDOW_WIDTH() {
		return POPUP_WINDOW_WIDTH;
	}
	
	public static final int get_MIN_WINDOW_WIDTH() {
		return MIN_WINDOW_WIDTH;
	}
	
	public static final int get_DEFAULT_WINDOW_HEIGHT() {
		return DEFAULT_WINDOW_HEIGHT;
	}
	
	public static final int get_PARAMETERS_WINDOW_HEIGHT() {
		return PARAMETERS_WINDOW_HEIGHT;
	}
	
	public static final int get_POPUP_WINDOW_HEIGHT() {
		return POPUP_WINDOW_HEIGHT;
	}

	public static final int get_MIN_WINDOW_HEIGHT() {
		return MIN_WINDOW_HEIGHT;
	}

	public static final int get_DEFAULT_CHARS_BASE_SIZE() {
		return DEFAULT_CHARS_BASE_SIZE;
	}
	
	public static final int get_MIN_CHARS_BASE_SIZE() {
		return MIN_CHARS_BASE_SIZE;
	}

	public static final int get_MAX_CHARS_BASE_SIZE() {
		return MAX_CHARS_BASE_SIZE;
	}

	public static final int get_DEFAULT_COMPONENT_SPACING_WIDTH() {
		return DEFAULT_COMPONENT_SPACING_WIDTH;
	}

	public static final int get_MIN_COMPONENT_SPACING_WIDTH() {
		return MIN_COMPONENT_SPACING_WIDTH;
	}

	public static final int get_DEFAULT_ICONS_SIZE() {
		return DEFAULT_ICONS_SIZE;
	}

	public static final int get_MIN_ICONS_SIZE() {
		return MIN_ICONS_SIZE;
	}

	public static final int get_MAX_ICONS_SIZE() {
		return MAX_ICONS_SIZE;
	}

	public static final int get_DEFAULT_SCROLL_BAR_SIZE() {
		return DEFAULT_SCROLL_BAR_SIZE;
	}

	public static final int get_DEFAULT_SCROLL_HORIZONTAL_UNIT_INCREMENT() {
		return DEFAULT_SCROLL_HORIZONTAL_UNIT_INCREMENT;
	}

	public static final int get_DEFAULT_SCROLL_VERTICAL_UNIT_INCREMENT() {
		return DEFAULT_SCROLL_VERTICAL_UNIT_INCREMENT;
	}

	public static final int get_DEFAULT_DOCUMENT_HISTORY_LENGTH() {
		return DEFAULT_DOCUMENT_HISTORY_LENGTH;
	}

	public static final int get_MIN_DOCUMENT_HISTORY_LENGTH() {
		return MIN_DOCUMENT_HISTORY_LENGTH;
	}

	public static final int get_MAX_DOCUMENT_HISTORY_LENGTH() {
		return MAX_DOCUMENT_HISTORY_LENGTH;
	}
	
	public static final int get_RECOMMENDED_WINDOW_WIDTH() {
		return DEFAULT_WINDOW_WIDTH;
	}
	
	public static final int get_RECOMMENDED_PARAMETERS_WINDOW_WIDTH() {
		return PARAMETERS_WINDOW_WIDTH;
	}

	public static final int get_RECOMMENDED_POPUP_WINDOW_WIDTH() {
		return POPUP_WINDOW_WIDTH;
	}
	
	public static final int get_RECOMMENDED_WINDOW_HEIGHT() {
		return DEFAULT_WINDOW_HEIGHT;
	}
	
	public static final int get_RECOMMENDED_PARAMETERS_WINDOW_HEIGHT() {
		return PARAMETERS_WINDOW_HEIGHT;
	}
	
	public static final int get_RECOMMENDED_POPUP_WINDOW_HEIGHT() {
		return POPUP_WINDOW_HEIGHT;
	}
	
	public static final int get_RECOMMENDED_CHARS_BASE_SIZE() {
		if ( DESKTOP_WIDTH > DESKTOP_WIDTH_MIN_FOR_STRICTS_VALUES ) {
			if ( DEFAULT_CHARS_BASE_SIZE > MIN_CHARS_BASE_SIZE )
				return DEFAULT_CHARS_BASE_SIZE;
			else
				return MIN_CHARS_BASE_SIZE;
		} else
			return STRICT_MIN_CHARS_BASE_SIZE;
	}
	
	public static final int get_RECOMMENDED_COMPONENT_SPACING_WIDTH() {
		if ( DESKTOP_WIDTH > DESKTOP_WIDTH_MIN_FOR_STRICTS_VALUES ) {
			if ( DEFAULT_COMPONENT_SPACING_WIDTH > MIN_COMPONENT_SPACING_WIDTH )
				return DEFAULT_COMPONENT_SPACING_WIDTH;
			else
				return MIN_COMPONENT_SPACING_WIDTH;
		} else
			return STRICT_MIN_COMPONENT_SPACING_WIDTH;
	}
	
	public static final int get_RECOMMENDED_COMPONENT_MARGIN_WIDTH() {
		if ( DESKTOP_WIDTH > DESKTOP_WIDTH_MIN_FOR_STRICTS_VALUES ) {
			if ( DEFAULT_COMPONENT_MARGIN_WIDTH > MIN_COMPONENT_MARGIN_WIDTH )
				return DEFAULT_COMPONENT_MARGIN_WIDTH;
			else
				return MIN_COMPONENT_MARGIN_WIDTH;
		} else
			return STRICT_MIN_COMPONENT_MARGIN_WIDTH;
	}
	
	public static final int get_RECOMMENDED_COMPONENT_MARGIN_HEIGHT() {
		if ( DEFAULT_COMPONENT_MARGIN_HEIGHT > STRICT_MIN_COMPONENT_MARGIN_HEIGHT )
			return DEFAULT_COMPONENT_MARGIN_HEIGHT;
		else
			return STRICT_MIN_COMPONENT_MARGIN_HEIGHT;
	}
	
	public static final int get_RECOMMENDED_ICONS_SIZE() {
		if ( DESKTOP_WIDTH > DESKTOP_WIDTH_MIN_FOR_STRICTS_VALUES ) {
			if ( DEFAULT_ICONS_SIZE > MIN_ICONS_SIZE )
				return DEFAULT_ICONS_SIZE;
			else
				return MIN_ICONS_SIZE;
		} else
			return STRICT_MIN_ICONS_SIZE;
	}
	
	public static final int get_RECOMMENDED_SCROLL_BAR_SIZE() {
		return DEFAULT_SCROLL_BAR_SIZE;
	}
	
	public static final int get_RECOMMENDED_SCROLL_HORIZONTAL_UNIT_INCREMENT() {
		return DEFAULT_SCROLL_HORIZONTAL_UNIT_INCREMENT;
	}

	public static final int get_RECOMMENDED_SCROLL_VERTICAL_UNIT_INCREMENT() {
		return DEFAULT_SCROLL_VERTICAL_UNIT_INCREMENT;
	}
	
	public static ArrayList<Integer> getCharsBaseSizeRange() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Integer incrementVal = new Float( new Float(MAX_CHARS_BASE_SIZE) / new Float(10) ).intValue();
		for ( Integer i = MIN_CHARS_BASE_SIZE; i <= MAX_CHARS_BASE_SIZE; i += incrementVal ) {
			result.add(i);
		}
		if ( result.contains(MAX_CHARS_BASE_SIZE) == false )
			result.add( MAX_CHARS_BASE_SIZE );
		
		return result;
	}
	
	public static int getCharsBaseSizeIncrementValue() {
		int incrementValMax = new Float( new Float(MAX_CHARS_BASE_SIZE) / new Float(10) ).intValue();
		if ( incrementValMax < 1 )
			incrementValMax = 1;
		int incrementVal = new Float( new Float(incrementValMax) / new Float(10) ).intValue();
		if ( incrementVal < 1 )
			incrementVal = 1;
		return incrementVal;
	}
	
	public static ArrayList<Integer> getIconsSizeRange() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Integer incrementVal = new Float( new Float(MAX_ICONS_SIZE) / new Float(10) ).intValue();
		for ( Integer i = MIN_ICONS_SIZE; i <= MAX_ICONS_SIZE; i += incrementVal ) {
			result.add(i);
		}
		if ( result.contains(MAX_ICONS_SIZE) == false )
			result.add( MAX_ICONS_SIZE );
		
		return result;
	}
	
	public static int getIconsSizeIncrementValue() {
		int incrementValMax = new Float( new Float(MAX_ICONS_SIZE) / new Float(10) ).intValue();
		if ( incrementValMax < 1 )
			incrementValMax = 1;
		int incrementVal = new Float( new Float(incrementValMax) / new Float(10) ).intValue();
		if ( incrementVal < 1 )
			incrementVal = 1;
		return incrementVal;
	}
	
	//====================
	// end region get
	//====================
	
	//====================
	// region G & S
	//====================
	
	public static Theme getThemeSelected() {
		return themeSelected;
	}

	public static void setThemeSelected(Theme themeSelected) {
		Parameters.themeSelected = themeSelected;
	}

	public static Language getLanguageSelected() {
		return languageSelected;
	}

	public static void setLanguageSelected(Language languageSelected) {
		Parameters.languageSelected = languageSelected;
		
		switch ( languageSelected.get_LANG_TYPE() ) {
			case English:
				JComponent.setDefaultLocale(Locale.ENGLISH);
				break;
			case French:
				JComponent.setDefaultLocale(Locale.FRENCH);
				break;
		}
	}
	
	public static Integer getCharsBaseSize() {
		return charsBaseSize;
	}

	public static void setCharsBaseSize(Integer charsBaseSize) {
		Parameters.charsBaseSize = charsBaseSize;
	}

	public static Integer getIconsSize() {
		return iconsSize;
	}

	public static void setIconsSize(Integer iconsSize) {
		Parameters.iconsSize = iconsSize;
	}

	public static String getProgramIconPath() {
		return programIconPath;
	}

	public static void setProgramIconPath(String programIconPath) {
		Parameters.programIconPath = programIconPath;
	}

	public static Integer getDocumentHistoryLength() {
		return documentHistoryLength;
	}

	public static void setDocumentHistoryLength(Integer documentHistoryLength) {
		Parameters.documentHistoryLength = documentHistoryLength;
	}
	
	//====================
	// end region G & S
	//====================
	
	//====================
	// region create
	//====================
	
	public static int createSpacingSize( float calc ) {
		return new Float( new Float(DESKTOP_WIDTH) * calc).intValue();
	}
	
	//====================
	// end region create
	//====================
	
	//====================
	// region is
	//====================
	
	public static boolean isWindowWidthValid( int size ) {
		return size >= MIN_WINDOW_WIDTH && size <= DESKTOP_WIDTH;
	}
	
	public static boolean isWindowHeightValid( int size ) {
		return size >= MIN_WINDOW_HEIGHT && size <= DESKTOP_HEIGHT;
	}
	
	public static boolean isCharsBaseSizeValid( int size ) {
		return size >= MIN_CHARS_BASE_SIZE && size <= MAX_CHARS_BASE_SIZE;
	}
	
	public static boolean isIconsSizeValid( int size ) {
		return size >= MIN_ICONS_SIZE && size <= MAX_ICONS_SIZE;
	}
	
	public static boolean isDocumentHistoryLengthValid( int val ) {
		return val >= MIN_DOCUMENT_HISTORY_LENGTH && val <= MAX_DOCUMENT_HISTORY_LENGTH;
	}
	
	//====================
	// end region is
	//====================
	
}
