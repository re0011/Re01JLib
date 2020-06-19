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

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import re01.environment.Parameters;
import re01.exception.Re01JLibException;

/**
 *
 * @author renaud
 */
public class MethodHelper {
	
	/**
	 * Main methods of program
	 */
	private static Method[] mainMethods = null;
	
	private static final String ARG_KEY_CALLBACKS_TO_EXECUTE = "re01_methods_helper_objects_callbacks_to_execute";

	public static Method[] getMainMethods() {
		return mainMethods;
	}

	public static void setMainMethods( Method[] methods ) {
		MethodHelper.mainMethods = methods;
	}

	public static final String get_ARG_KEY_CALLBACKS_TO_EXECUTE() {
		return ARG_KEY_CALLBACKS_TO_EXECUTE;
	}
	
	//====================
	// region execute callbacks
	//====================
	
	public static void executeCallbacks( Object[] args ) throws Re01JLibException {
		Object callbacks = getArgObject(MethodHelper.ARG_KEY_CALLBACKS_TO_EXECUTE, args );
		if ( callbacks != null ) {
			executeCallbacks( callbacks, args );
		}
	}
	
	public static void executeCallbacks( Object callbacks, Object[] args ) throws Re01JLibException {
		HashMap<Object, HashMap<Class, String[]>> callbacksObjMap = null;
		HashMap<String, String[]> callbacksMap = null;
		String[] callbacksNamesTable = null;
		try {
			callbacksObjMap = ( HashMap<Object, HashMap<Class, String[]>> ) callbacks;
		} catch ( Exception e ) { }
		if ( callbacksObjMap != null ) {
			Set<Entry<Object, HashMap<Class, String[]>>> callbacksObjMapSet = callbacksObjMap.entrySet();
			Iterator<Entry<Object, HashMap<Class, String[]>>> callbacksObjMapSetIt = callbacksObjMapSet.iterator();
			while ( callbacksObjMapSetIt.hasNext() ) {
				Entry<Object, HashMap<Class, String[]>> callbacksObjMapEntry = callbacksObjMapSetIt.next();
				Object obj = callbacksObjMapEntry.getKey();
				
				HashMap<Class, String[]> classInfo = callbacksObjMapEntry.getValue();
				Set<Entry<Class, String[]>> classInfoSet = classInfo.entrySet();
				Iterator<Entry<Class, String[]>> classInfoSetIt = classInfoSet.iterator();
				while ( classInfoSetIt.hasNext() ) {
					Entry<Class, String[]> classInfoEntry = classInfoSetIt.next();
					Class c = classInfoEntry.getKey();
					String[] callbacksNames = classInfoEntry.getValue();
					
					int callbacksNamesLength = callbacksNames.length;
					for ( int i = 0; i < callbacksNamesLength; i++ ) {
						String callbackName = callbacksNames[i];
						Method method = null;
						try {
							method = c.getDeclaredMethod( callbackName, Object[].class );
						} catch ( Exception e ) { }
						
						if ( method == null ) {
							try {
								method = c.getDeclaredMethod( callbackName );
							} catch ( Exception e ) { }
						}
						
						if ( method != null ) {
							try {
								method.invoke( obj, new Object[]{args} );
							} catch ( IllegalArgumentException e ) {
								try {
									method.invoke( obj );
								} catch ( Exception ex ) { }
							} catch ( Exception e ) { }
						}
					}
				}
			}
		} else {
			try {
				callbacksMap = ( HashMap<String, String[]> ) callbacks;
			} catch ( Exception e ) { }

			if ( callbacksMap != null ) {
				Set<Entry<String, String[]>> setEntry = callbacksMap.entrySet();
				Iterator<Entry<String, String[]>> setEntryIt = setEntry.iterator();
				while ( setEntryIt.hasNext() ) {
					Entry<String, String[]> entry = setEntryIt.next();
					String classNameWithMethodKey = entry.getKey();
					String[] callbacksNames = entry.getValue();
					
					Method[] classMethods = getArgMethods( classNameWithMethodKey, args );
					executeCallbacks( callbacksNames, classMethods, args );
				}
			} else {
				try {
					callbacksNamesTable = ( String[] ) callbacks;
				} catch ( Exception e ) { }
				
				if ( callbacksNamesTable != null ) {
					executeCallbacks( callbacksNamesTable, mainMethods, args );
				}
			}
			if ( callbacksMap == null && callbacksNamesTable == null )
				throw new Re01JLibException( Parameters.getLanguageSelected().get_LANG().get_TEXT_CALLBACK_ARGS_AND_METHOD_NAME_NOT_FOUND() );
		}
	}
	
	private static void executeCallbacks( String[] callbacksNames, Method[] methods, Object[] args ) throws Re01JLibException {
		for( int i = 0; i < callbacksNames.length; i++ ) {
			String callbackName = callbacksNames[i];
			for ( int f = 0; f < methods.length; f++ ) {
				Method method = methods[f];
				if ( method.getName().equals(callbackName) ) {
					try {
						method.invoke( null, new Object[]{args} );
					} catch (Exception ex) {
						throw new Re01JLibException(ex);
					}
					break;
				}
			}
		}
	}
	
	//====================
	// end region execute callbacks
	//====================
	
	//====================
	// region get args
	//====================
	
	public static Object getArg( String key, Object[] args ) throws Re01JLibException {
		Object objectFound = null;
		
		if ( args != null ) {
			for ( int argI = 0; argI < args.length; argI++ ) {
				HashMap<String, Object> argsFound = ( HashMap<String, Object> ) args[argI];
				Set<Map.Entry<String, Object>> argsFoundSet = argsFound.entrySet();
				Iterator<Map.Entry<String, Object>> callbacksSetIt = argsFoundSet.iterator();
				while ( callbacksSetIt.hasNext() ) {
					Map.Entry<String, Object> entry = callbacksSetIt.next();
					if ( entry.getKey().equals(key) ) {
						objectFound = entry.getValue();
						break;
					}
				}
			}
		}
		
		return objectFound;
	}
	
	public static Object getArgObject( String key, Object[] args ) throws Re01JLibException {
		Object resultFound = null;
		Object objArg = getArg( key, args );
		if ( objArg != null ) {
			resultFound = ( Object ) objArg;
		}
		return resultFound;
	}
	
	public static Method[] getArgMethods( String key, Object[] args ) throws Re01JLibException {
		Method[] resultFound = null;
		Object objArg = getArg( key, args );
		if ( objArg != null ) {
			resultFound = ( Method[] ) objArg;
		}
		return resultFound;
	}
	
	public static Boolean getArgBoolean( String key, Object[] args ) throws Re01JLibException {
		Boolean resultFound = null;
		Object objArg = getArg( key, args );
		if ( objArg != null ) {
			resultFound = ( Boolean ) objArg;
		}
		return resultFound;
	}
	
	public static Integer getArgInteger( String key, Object[] args ) throws Re01JLibException {
		Integer resultFound = null;
		Object objArg = getArg( key, args );
		if ( objArg != null ) {
			resultFound = ( Integer ) objArg;
		}
		return resultFound;
	}
	
	public static String getArgString( String key, Object[] args ) throws Re01JLibException {
		String resultFound = null;
		Object objArg = getArg( key, args );
		if ( objArg != null ) {
			resultFound = ( String ) objArg;
		}
		return resultFound;
	}
	
	public static File getArgFile( String key, Object[] args ) throws Re01JLibException {
		File resultFound = null;
		Object objArg = getArg( key, args );
		if ( objArg != null ) {
			resultFound = ( File ) objArg;
		}
		return resultFound;
	}
	
	//====================
	// end region get args
	//====================
	
	public static void addOrReplaceCallbackArg( String key, Object argNew, Object[] args ) throws Re01JLibException {
		if ( args != null ) {
			boolean isObjectExist = false;
			for ( int argI = 0; argI < args.length; argI++ ) {
				HashMap<String, Object> subArgsFound = ( HashMap<String, Object> ) args[argI];
				Set<Map.Entry<String, Object>> subArgsFoundSet = subArgsFound.entrySet();
				Iterator<Map.Entry<String, Object>> callbacksSetIt = subArgsFoundSet.iterator();
				while ( callbacksSetIt.hasNext() ) {
					Map.Entry<String, Object> entry = callbacksSetIt.next();
					if ( entry.getKey().equals( key ) ) {
						subArgsFound.replace( key, argNew );
						isObjectExist = true;
						break;
					}
				}
			}
			if ( isObjectExist == false ) {
				HashMap<String, Object> subArgsNew = ( HashMap<String, Object> ) args[0];
				subArgsNew.put(key, argNew);
				args[0] = subArgsNew;
			}
		}
	}
	
	public static HashMap<String, Object> createDefaultArgs() {
		return new HashMap<String, Object>();
	}
	
}
