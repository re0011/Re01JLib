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

package re01.language;

/**
 *
 * @author renaud
 */
public class Language {
	
	protected final LanguageTypeEnum LANG_TYPE;
	private final Global LANG;
	
	public Language( LanguageTypeEnum langType ) {
		this.LANG_TYPE = langType;
		
		switch ( this.LANG_TYPE ) {
			case English:
				LANG = new re01.language.English();
				break;
			case French:
				LANG = new re01.language.French();
				break;
			default:
				LANG = new re01.language.English();
				break;
		}
	}

	public LanguageTypeEnum get_LANG_TYPE() {
		return LANG_TYPE;
	}

	public Global get_LANG() {
		return LANG;
	}
	
}
