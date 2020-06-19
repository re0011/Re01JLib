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

package re01.tool.reader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author renaud
 */
public class ParameterReader {
	
	private final String PARAMETER_HEADER = "Header";
	private final String PARAMETER_HEADER_ICON_TYPE = "icon_type";
	private final String PARAMETER_HEADER_ICON_VALUE = "icon_value";
	
	private HashMap<String, HashMap<String, String>> parameters = null;
	
	public ParameterReader( HashMap<String, HashMap<String, String>> parameters ) {
		this.parameters = parameters;
	}
	
	public ParameterReader() {
		
	}

	public String get_PARAMETER_HEADER() {
		return PARAMETER_HEADER;
	}

	public String get_PARAMETER_HEADER_ICON_TYPE() {
		return PARAMETER_HEADER_ICON_TYPE;
	}

	public String get_PARAMETER_HEADER_ICON_VALUE() {
		return PARAMETER_HEADER_ICON_VALUE;
	}

	public HashMap<String, HashMap<String, String>> getParameters() {
		return parameters;
	}
	
	public HashMap<String, String> getSection( String sectionToGet ) {
		HashMap<String, String> sectionFound = null;
		
		String sectionToGetLower = sectionToGet.toLowerCase();
		
		if ( parameters != null ) {
			Set<Entry<String, HashMap<String, String>>> parametersSet = parameters.entrySet();
			Iterator<Entry<String, HashMap<String, String>>> parametersSetIt = parametersSet.iterator();
			while ( parametersSetIt.hasNext() ) {
				Entry<String, HashMap<String, String>> parametersEntry = parametersSetIt.next();
				String section = parametersEntry.getKey();
				if ( section.toLowerCase().equals(sectionToGetLower) == true ) {
					sectionFound = parametersEntry.getValue();
					break;
				}
			}
		}
		
		return sectionFound;
	}
	
	public String getValue( String sectionToGet, String keyToGet ) {
		String valueFound = null;
		String sectionToGetLower = sectionToGet.toLowerCase();
		String keyToGetLower = keyToGet.toLowerCase();
		
		if ( parameters != null ) {
			Set<Entry<String, HashMap<String, String>>> parametersSet = parameters.entrySet();
			Iterator<Entry<String, HashMap<String, String>>> parametersSetIt = parametersSet.iterator();
			while ( parametersSetIt.hasNext() ) {
				Entry<String, HashMap<String, String>> parametersEntry = parametersSetIt.next();
				String section = parametersEntry.getKey();
				if ( section.toLowerCase().equals(sectionToGetLower) == true ) {
					HashMap<String, String> paramsSection = parametersEntry.getValue();
					Set<Entry<String, String>> paramsSectionSet = paramsSection.entrySet();
					Iterator<Entry<String, String>> paramsSectionSetIt = paramsSectionSet.iterator();
					while ( paramsSectionSetIt.hasNext() ) {
						Entry<String, String> paramsSectionEntry = paramsSectionSetIt.next();
						String key = paramsSectionEntry.getKey();
						if ( key.toLowerCase().equals(keyToGetLower) == true ) {
							valueFound = paramsSectionEntry.getValue();
							break;
						}
					}
				}
				if ( valueFound != null )
					break;
			}
		}
		
		return valueFound;
	}
	
	public void addValue( String section, String keyToAdd, String valueToAdd ) {
		String sectionLower = section.toLowerCase();
		String keyToAddLower = keyToAdd.toLowerCase();
		
		if ( parameters != null ) {
			Set<Entry<String, HashMap<String, String>>> parametersSet = parameters.entrySet();
			Iterator<Entry<String, HashMap<String, String>>> parametersSetIt = parametersSet.iterator();
			while ( parametersSetIt.hasNext() ) {
				Entry<String, HashMap<String, String>> parametersEntry = parametersSetIt.next();
				String sectionFound = parametersEntry.getKey();
				if ( sectionFound.toLowerCase().equals(sectionLower) == true ) {
					boolean isKeyExist = false;
					String key = null;
					
					HashMap<String, String> paramsSection = parametersEntry.getValue();
					Set<Entry<String, String>> paramsSectionSet = paramsSection.entrySet();
					Iterator<Entry<String, String>> paramsSectionSetIt = paramsSectionSet.iterator();
					
					while ( paramsSectionSetIt.hasNext() ) {
						Entry<String, String> paramsSectionEntry = paramsSectionSetIt.next();
						key = paramsSectionEntry.getKey();
						if ( key.toLowerCase().equals(keyToAddLower) == true ) {
							isKeyExist = true;
							break;
						}
					}
					
					if ( isKeyExist == true )
						paramsSection.replace( key, valueToAdd );
					else
						paramsSection.put( keyToAddLower, valueToAdd );
					
					break;
				}
			}
		}
	}
	
}
