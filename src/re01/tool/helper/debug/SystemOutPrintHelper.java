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

package re01.tool.helper.debug;

/**
 *
 * @author renaud
 */
public class SystemOutPrintHelper {
	
	public static void printFirst( boolean print, Boolean val ) {
		if ( print )
			print( "* " + val );
	}
	
	public static void printFirst( boolean print, String val ) {
		if ( print )
			print( "* " + val );
	}
	
	public static void printFirst( boolean print, int val ) {
		if ( print )
			print( "* " + val );
	}
	
	public static void printFirst( boolean print, String str, Boolean val ) {
		if ( print )
			print( "* " + str + " : " + val );
	}
	
	public static void printFirst( boolean print, String str, String val ) {
		if ( print )
			print( "* " + str + " : " + val );
	}
	
	public static void printFirst( boolean print, String str, int val ) {
		if ( print )
			print( "* " + str + " : " + val );
	}
	
	public static void print( boolean print, Boolean val ) {
		if ( print )
			print( " | " + val );
	}
	
	public static void print( boolean print, String val ) {
		if ( print )
			print( " | " + val );
	}
	
	public static void print( boolean print, int val ) {
		if ( print )
			print( " | " + val );
	}
	
	public static void print( boolean print, String str, Boolean val ) {
		if ( print )
			print( " | " + str + " : " + val );
	}
	
	public static void print( boolean print, String str, String val ) {
		if ( print )
			print( " | " + str + " : " + val );
	}
	
	public static void print( boolean print, String str, int val ) {
		if ( print )
			print( " | " + str + " : " + val );
	}
	
	public static void print_end( boolean print ) {
		if ( print )
			print( "\n" );
	}
	
	private static void print( String str ) {
		System.out.print( str );
	}
	
	public static void printlnFirst( boolean print, String className, String methodName ) {
		if ( print )
			println( "[" + className + "]" + "[" + methodName + "]" );
	}
	
	public static void printlnLast( boolean print, String className, String methodName ) {
		if ( print )
			println( "[" + className + "]" + "[" + methodName + "] End ---" );
	}
	
	public static void println( boolean print, Boolean val ) {
		if ( print )
			println( "* " + val );
	}
	
	public static void println( boolean print, String val ) {
		if ( print )
			println( "* " + val );
	}
	
	public static void println( boolean print, int val ) {
		if ( print )
			println( "* " + val );
	}
	
	public static void println( boolean print, String str, Boolean val ) {
		if ( print )
			println( "* " + str + " : " + val );
	}
	
	public static void println( boolean print, String str, String val ) {
		if ( print )
			println( "* " + str + " : " + val );
	}
	
	public static void println( boolean print, String str, int val ) {
		if ( print )
			println( "* " + str + " : " + val );
	}
	
	private static void println( String str ) {
		System.out.println( str );
	}
}
