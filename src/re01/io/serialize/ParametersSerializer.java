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

package re01.io.serialize;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author renaud
 */
public class ParametersSerializer {
	
	public void write( String filePath, HashMap<String, HashMap<String, String>> params ) {
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			fileOutputStream = new FileOutputStream( filePath );
			outputStreamWriter = new OutputStreamWriter( fileOutputStream, "UTF-8" );
			bufferedWriter = new BufferedWriter( outputStreamWriter );
			Iterator<Map.Entry<String, HashMap<String, String>>> paramsIteratorEntry = params.entrySet().iterator();
			while ( paramsIteratorEntry.hasNext() ) {
				Map.Entry<String, HashMap<String, String>> param = paramsIteratorEntry.next();
				bufferedWriter.write( "[" + param.getKey() + "]" );
				bufferedWriter.newLine();
				
				SortedSet<String> sortedKeys = new TreeSet<String>( param.getValue().keySet() );
				Iterator<String> sortedKeysIt = sortedKeys.iterator();
				while ( sortedKeysIt.hasNext() ) {
					String key = sortedKeysIt.next();
					bufferedWriter.write( key + "=" + param.getValue().get(key) );
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch ( Exception e ) {
			
		} finally {
			try {
				fileOutputStream.close();
			} catch ( Exception ex ) { }
			try {
				outputStreamWriter.close();
			} catch ( Exception ex ) { }
			try {
				bufferedWriter.close();
			} catch ( Exception ex ) { }
		}
	}
	
	public HashMap<String, HashMap<String, String>> read( String str, boolean isFilePath ) {
		HashMap<String, HashMap<String, String>> params = new HashMap<>();
		
		FileReader fileReader = null;
		StringReader stringReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			if ( isFilePath == true ) {
				fileReader = new FileReader( str );
				bufferedReader = new BufferedReader( fileReader );
			} else {
				stringReader = new StringReader( str );
				bufferedReader = new BufferedReader( stringReader );
			}
			
			String line;
			String keyLast = null, key = null;
			params.put( key, new HashMap<String, String>() );
			while ( ( line = bufferedReader.readLine() ) != null ) {
				try {
					HashMap<String, String> paramsSection = params.get( key );
					
					String lineTrim = line.trim();
					if ( lineTrim.isEmpty() == false ) {
						if ( lineTrim.startsWith("[") == false ) {
							String[] lineSplit = lineTrim.split( "=" );
							paramsSection.put( lineSplit[0].trim(), lineSplit[1].trim() );
						} else if ( lineTrim.startsWith("[/") == false ) {
							key = lineTrim.replace( "[", "").replace("]", "" );
							if ( keyLast == null || keyLast != null && keyLast.equals(key) == false ) {
								params.put( key, new HashMap<String, String>() );
							}
							
							keyLast = key;
						}
					}
				} catch ( Exception e ) { }
			}
			
		} catch ( Exception e ) {
			
		} finally {
			try {
				if ( isFilePath == true )
					fileReader.close();
				else
					stringReader.close();
			} catch ( Exception ex ) { }
			try {
				bufferedReader.close();
			} catch ( Exception ex ) { }
		}
		
		params.remove(null);
		
		return params;
	}
	
}
