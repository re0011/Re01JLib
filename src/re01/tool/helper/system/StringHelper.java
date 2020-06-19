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

package re01.tool.helper.system;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author renaud
 */
public class StringHelper {
	
	//====================
	// region get
	//====================
	
	/*
	* Add the chars of the string to the collection, if not already added
	* 
	* @return	the chars collection
	*/
	public HashSet<String> getCharsFromString( HashSet<String> chars, String str ) {
		if ( chars == null )
			chars = new HashSet<String>();
		String character;
		int index = 0;
		int strLength = str.length();
		do {
			int indexPlus = index + 1;
			if ( indexPlus <= strLength ) {
				character = str.substring(index, indexPlus);
				if ( character != null && chars.contains(character) == false )
					chars.add( character );
				index++;
			} else
				character = null;
		} while ( character != null );
		
		return chars;
	}
	
	public String getFirstCharAsString( String str ) {
		String c = null;
		int strLength = str.length();
		if ( strLength > 0 ) {
			c = str.substring(0, 1);
		}
		return c;
	}
	
	public String getStrPart( String str, String startAfterLine, String endBeforeLine, Boolean withDelimiters ) {
		String strPart = null;
		
		StringReader stringReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			stringReader = new StringReader( str );
			bufferedReader = new BufferedReader( stringReader );
			
			Boolean isStarted = null;
			String line;
			while ( ( line = bufferedReader.readLine() ) != null ) {
				boolean isLastIt = false;
				try {
					String lineTrim = line.trim();
					if ( lineTrim.isEmpty() == false ) {
						if ( lineTrim.equals(startAfterLine) ) {
							isStarted = true;
							if ( withDelimiters == false )
								continue;
						} else if ( lineTrim.equals(endBeforeLine) ) {
							isStarted = false;
							if ( withDelimiters == true )
								isLastIt = true;
						}
						
						if ( isStarted != null && isStarted == true || isLastIt == true ) {
							if ( strPart == null )
								strPart = "";
							strPart += line + "\n";
						}
					} else if ( isStarted == true ) {
						if ( strPart == null )
							strPart = "";
						strPart += "\n";
					}
				} catch ( Exception e ) {
					
				}
				
				if ( isStarted != null && isStarted == false )
					break;
			}
			
		} catch ( Exception e ) {
			
		} finally {
			try {
				stringReader.close();
			} catch ( Exception ex ) { }
			try {
				bufferedReader.close();
			} catch ( Exception ex ) { }
		}
		
		return strPart;
	}
	
	//====================
	// end region get
	//====================
	
	//====================
	// region add
	//====================
	
	public HashSet<String> addToCollection( HashSet<String> collection, String[] strs ) {
		for ( int i = 0; i < strs.length; i++ )
			collection = addToCollection( collection, strs[i] );
		return collection;
	}
	
	public HashSet<String> addToCollection( HashSet<String> collection, HashSet<String> strs ) {
		Iterator<String> strsIt = strs.iterator();
		while ( strsIt.hasNext() ) {
			String str = strsIt.next();
			collection = addToCollection( collection, str );
		}
		return collection;
	}
	
	public HashSet<String> addToCollection( HashSet<String> collection, String str ) {
		if ( collection.contains(str) == false )
			collection.add( str );
		return collection;
	}
	
	//====================
	// end region add
	//====================
	
	//====================
	// region remove
	//====================
	
	public String removeCharsAt( String str, int start, int end ) {
		String strNew = "";
		int strLength = str.length();
		for ( int i = 0; i < strLength; i++ ) {
			Character c = str.charAt(i);
			if ( i < start || i > end )
				strNew += c;
		}
		return strNew;
	}
	
	public String removePartSplitWhile( String str, String removeWhileStr, String splitStr ) {
		String result = "";
		String[] strSplits = str.split(splitStr);
		boolean isStarted = false;
		for ( int i = 0; i < strSplits.length; i++ ) {
			String strSplit = strSplits[i];
			if ( strSplit.equals(removeWhileStr) )
				isStarted = true;
			if ( isStarted == true )
				result += strSplit;
		}
		return result;
	}
	
	//====================
	// end region remove
	//====================
	
}
