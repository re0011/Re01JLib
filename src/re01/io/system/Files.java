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

package re01.io.system;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import re01.io.serialize.ParametersSerializer;
import re01.object.Tuple;

/**
 *
 * @author renaud
 */
public class Files {
	
	/*
	 * chmodCheck
	 * 
	 * Verify the read and write directory's rights.
	 */
	public Boolean chmodCheck( String directoryPath ) {
		Boolean isChmodOk = true;
		File file = null;
		String filePathNew = "";
		Integer d = 0;
		boolean isItOk = true;
		do {
			filePathNew = Paths.get( directoryPath, "chmod_test_" + d.toString() + ".txt" ).toString();
			file = new File( filePathNew );
			d++;
			isItOk = d < Integer.MAX_VALUE;
		}
		while ( file == null && isItOk == true 
		|| file != null && file.exists() == true && isItOk == true );
		
		Random rand = new Random();
		Integer randInt = rand.nextInt( 999999 );
		String stringEqualsTest = randInt.toString();
		
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream( file );
			bufferedOutputStream = new BufferedOutputStream( fileOutputStream );
			objectOutputStream = new ObjectOutputStream( bufferedOutputStream );
			objectOutputStream.writeObject( stringEqualsTest );
			objectOutputStream.flush();
		} catch ( FileNotFoundException e ) {
			isChmodOk = false;
		} catch ( IOException e ) {
			isChmodOk = false;
		} catch ( Exception e ) {
			isChmodOk = false;
		} finally {
			try {
				fileOutputStream.close();
			} catch ( Exception ex ) { }
			try {
				bufferedOutputStream.close();
			} catch ( Exception ex ) { }
			try {
				objectOutputStream.close();
			} catch ( Exception ex ) { }
		}
		
		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;
		ObjectInputStream objectInputStream = null;
		String stringEqualsTestFound;
		try {
			fileInputStream = new FileInputStream( new File( filePathNew ) );
			bufferedInputStream = new BufferedInputStream( fileInputStream );
			objectInputStream = new ObjectInputStream( bufferedInputStream );
			stringEqualsTestFound = ( String ) objectInputStream.readObject();
			if ( stringEqualsTest.equals( stringEqualsTestFound ) == false ) {
				isChmodOk = false;
			}
		} catch ( ClassNotFoundException e ) {
			isChmodOk = false;
		} catch ( FileNotFoundException e ) {
			isChmodOk = false;
		} catch ( IOException e ) {
			isChmodOk = false;
		} catch ( Exception e ) {
			isChmodOk = false;
		} finally {
			try {
				fileInputStream.close();
			} catch ( Exception ex ) { }
			try {
				bufferedInputStream.close();
			} catch ( Exception ex ) { }
			try {
				objectInputStream.close();
			} catch ( Exception ex ) { }
		}
		
		try {
			file.delete();
		} catch ( Exception e ) { }
		
		return isChmodOk;
	}
	
	//====================
	// region is
	//====================
	
	public Boolean isDirectoryExist( String path ) {
		Boolean isExists = false;
		try {
			File file = new File( path );
			if ( file.isDirectory() )
				isExists = true;
		} catch ( Exception e ) {
			
		}
		return isExists;
	}
	
	public Boolean isFileExists( String path ) {
		Boolean isExists = false;
		try {
			File file = new File( path );
			isExists = isFileExists( file );
		} catch ( Exception e ) {
			
		}
		return isExists;
	}
	
	public Boolean isFileExists( File file ) {
		boolean isExists = false;
		try {
			isExists = file.exists() && !file.isDirectory();
		} catch (Exception e) { }
		return isExists;
	}
	
	//====================
	// end region is
	//====================
	
	//====================
	// region create
	//====================
	
	public Boolean createDirectory( String path ) {
		Boolean isCreated = false;
		try {
			File file = new File( path );
			isCreated = file.mkdir();
		} catch ( Exception e ) {
			
		}
		return isCreated;
	}
	
	public Boolean writeContent( String path, String content, Charset charset ) {
		Boolean isSaved = false;
		PrintWriter printWriter = null;
		try {
			if ( charset != null )
				printWriter = new PrintWriter( path, charset );
			else
				printWriter = new PrintWriter( path );
			printWriter.print( content );
			isSaved = true;
		} catch ( Exception e ) {
			
		} finally {
			try {
				printWriter.close();
			} catch ( Exception ex ) { }
		}
		return isSaved;
	}
	
	public boolean rename( String path, String newPath ) {
		boolean isRenamed = false;
		File file = new File( path );
		File newFile = new File( newPath );
		
		if ( file.isDirectory() == true && newFile.isDirectory() == false 
		|| file.isDirectory() == false && file.exists() == true && newFile.isDirectory() == true 
		|| file.isDirectory() == false && file.exists() == true && newFile.isDirectory() == false && newFile.exists() == false ) {
			// API can not rename folder if a file with the new name exists in the same folder.
			isRenamed = file.renameTo( newFile );
		}
		
		return isRenamed;
	}
	
	//====================
	// end region create
	//====================
	
	//====================
	// region get
	//====================
	
	public String getContent( File file, Charset charset ) {
		String fileContent = "";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			if ( charset != null )
				fileReader = new FileReader( file, charset );
			else
				fileReader = new FileReader( file );
			
			bufferedReader = new BufferedReader( fileReader );
			
			String line;
			while ( (line = bufferedReader.readLine()) != null ) {
				fileContent += line + "\n";
			}
			
		} catch (Exception e) {
			
		} finally {
			try {
				fileReader.close();
			} catch ( Exception e ) {
				
			}
			try {
				bufferedReader.close();
			} catch ( Exception e ) {
				
			}
		}
		return fileContent;
	}
	
	public String getFileExtension( String name ) {
		String extension = null;
		String[] nameSplit = name.split("\\.");
		int nameSplitLength = nameSplit.length;
		if ( nameSplitLength > 1 ) {
			extension = nameSplit[nameSplitLength - 1];
		}
		return extension;
	}
	
	//====================
	// end region get
	//====================
	
	//====================
	// region delete
	//====================
	
	public HashSet<Tuple> delete( String path ) {
		HashSet<Tuple> result = new HashSet<Tuple>();
		
		File file = new File( path );
		if ( file.isDirectory() == true ) {
			
			File[] directoryFiles = file.listFiles();
			int directoryFilesLength = directoryFiles.length;
			for ( int i = 0; i < directoryFilesLength; i++ ) {
				File fileFound = directoryFiles[i];
				HashSet<Tuple> fileFoundDeleteResult = delete( fileFound.getPath() );
				Iterator<Tuple> fileFoundDeleteResultIt = fileFoundDeleteResult.iterator();
				while ( fileFoundDeleteResultIt.hasNext() ) {
					Tuple resultFound = fileFoundDeleteResultIt.next();
					result.add( resultFound );
				}
			}
			
			file.delete();
		} else if ( file.exists() ) {
			Tuple resultPartNew = new Tuple( getFileExtension(file.getName()), file.getPath(), file.delete() );
			result.add( resultPartNew );
		}
		
		return result;
	}
	
	public boolean deleteFile( String path ) {
		File file = new File( path );
		return deleteFile( file );
	}
	
	public boolean deleteFile( File file ) {
		boolean isDeleted = false;
		if ( file.exists() )
			isDeleted = file.delete();
		return isDeleted;
	}
	
	//====================
	// end region delete
	//====================
	
	//====================
	// region parameters
	//====================
	
	public void saveParams( String filePath, HashMap<String, HashMap<String, String>> params ) {
		ParametersSerializer serializer = new ParametersSerializer();
		serializer.write( filePath, params );
	}
	
	public HashMap<String, HashMap<String, String>> loadParams( String filePath ) {
		ParametersSerializer serializer = new ParametersSerializer();
		HashMap<String, HashMap<String, String>> params = serializer.read( filePath, true );
		return params;
	}
	
	//====================
	// end region parameters
	//====================
}
