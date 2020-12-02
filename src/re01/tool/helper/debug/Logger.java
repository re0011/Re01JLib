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

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import re01.io.system.Files;

/**
 *
 * @author renaud
 */
public class Logger {
	
	private String logsDirectory = null;
	private final String BASE_LOG_FILE_NAME = "re01jliblog_";
	private final String LOG_FILE_EXTENSION = ".log";
	private Integer maxFullFileBytesLength;
	private final Integer MAX_FILE_BYTES_LENGTH;
	private final Byte LOG_FILES_QTY = new Byte("11");
	
	private String logFileAbsolutePath = null;
	
	public Logger( String logsDirectory, int maxFullFileBytesLength ) {
		this.logsDirectory = logsDirectory;
		this.maxFullFileBytesLength = maxFullFileBytesLength;
		MAX_FILE_BYTES_LENGTH = maxFullFileBytesLength + 100000;// + 100 KB
	}
	
	public void write ( Exception e ) {
		String stackTrace = "";
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		for ( int i = 0; i < stackTraceElements.length; i++ ) {
			StackTraceElement stackTraceElement = stackTraceElements[i];
			stackTrace += stackTraceElement.toString() + "\n";
		}
		
		String message = ( stackTrace.isEmpty() == false ) ? e.toString() + "\n at: " + stackTrace : e.toString() ;
		write( message );
	}
	
	public void write( String message ) {
		Files files = new Files();
		
		try {
			if ( logFileAbsolutePath != null ) {
				File logFile = new File( logFileAbsolutePath );
				if ( logFile == null 
				|| logFile != null && files.isFileExists(logFile) == false 
				|| logFile != null && files.isFileExists(logFile) == true && logFile.length() > MAX_FILE_BYTES_LENGTH ) {
					logFileAbsolutePath = null;
					selectLogFilePath();
				}
			} else
				selectLogFilePath();

			if ( logFileAbsolutePath != null ) {
				File logFile = new File( logFileAbsolutePath );
				write( logFile, message );
			}
		} catch ( Exception e ) {
			SystemOutPrintHelper.println(true, "Re01JLibException in re01.io.system.Logger.write( String message )", e.toString());
		}
	}
	
	private void write( File logFile, String message ) {
		Files files = new Files();
		Date dateNow = new Date();
		
		String content = files.getContent( logFile, StandardCharsets.UTF_8 );
		content += "\n\n__________ " + dateNow.toString() + " __________\n\n" + message;
		
		files.writeContent( logFile.getAbsolutePath(), content, StandardCharsets.UTF_8 );
	}
	
	private void selectLogFilePath() {
		File logFileFound = null;
		Files files = new Files();
		
		Byte logFileId = new Byte("0");
		Path logFileNamePathAbsolute = null;
		do {
			String logFileName = BASE_LOG_FILE_NAME + logFileId + LOG_FILE_EXTENSION;
			logFileNamePathAbsolute = Paths.get( logsDirectory, logFileName );
			if ( logFileNamePathAbsolute != null ) {
				File file = logFileNamePathAbsolute.toFile();
				
				if ( file != null ) {
					if ( files.isFileExists( file ) == true ) {
						if ( file.length() <= MAX_FILE_BYTES_LENGTH ) {
							logFileFound = file;
						}
					} else {
						logFileFound = file;
						deleteLogFile( new Byte(String.valueOf(logFileId + 1)) );
					}
				}
			}
			logFileId++;
		} while ( logFileFound == null && logFileId < LOG_FILES_QTY );
		
		if ( logFileFound == null ) {
			logFileId = new Byte("0");
			boolean isFileDeleted = false;
			do {
				String logFileName = BASE_LOG_FILE_NAME + logFileId + LOG_FILE_EXTENSION;
				logFileNamePathAbsolute = Paths.get( logsDirectory, logFileName );
				if ( logFileNamePathAbsolute != null ) {
					File file = logFileNamePathAbsolute.toFile();
					if ( file != null ) {
						if ( file.length() >= maxFullFileBytesLength ) {
							isFileDeleted = files.deleteFile( file );
							deleteLogFile( new Byte(String.valueOf(logFileId + 1)) );
							break;
						}
					}
				}
				logFileId++;
			} while ( logFileId < LOG_FILES_QTY );
			
			if ( isFileDeleted == false ) {
				String logFileName = BASE_LOG_FILE_NAME + 0 + LOG_FILE_EXTENSION;
				logFileNamePathAbsolute = Paths.get( logsDirectory, logFileName );
				if ( logFileNamePathAbsolute != null ) {
					File file = logFileNamePathAbsolute.toFile();
					if ( file != null ) {
						isFileDeleted = files.deleteFile( file );
						deleteLogFile( new Byte(String.valueOf(logFileId + 1)) );
					}
				}
			}
			
			if ( isFileDeleted == true ) {
				if ( logFileNamePathAbsolute != null ) {
					File logFileNew = logFileNamePathAbsolute.toFile();
					if ( logFileNew != null && files.isFileExists(logFileNew) == false ) {
						logFileFound = logFileNew;
					}
				}
			}
		}
		
		if ( logFileFound != null ) {
			logFileAbsolutePath = logFileFound.getAbsolutePath();
		}
	}
	
	private void deleteLogFile( Byte logFileId ) {
		Files files = new Files();
		
		String logFileNameToDelete = BASE_LOG_FILE_NAME + logFileId + LOG_FILE_EXTENSION;
		Path logFileNamePathAbsoluteToDelete = Paths.get( logsDirectory, logFileNameToDelete );
		if ( logFileNamePathAbsoluteToDelete != null )
			files.delete( logFileNamePathAbsoluteToDelete.toString() );
	}
	
}
