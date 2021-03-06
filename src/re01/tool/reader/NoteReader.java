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

package re01.tool.reader;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import re01.design.color.Color;
import re01.design.color.ColorAttributeTypeEnum;
import re01.design.color.ColorTypeEnum;
import re01.design.font.FontSize;
import re01.design.font.FontStyleEnum;
import re01.design.view.swing.JTextPane;
import re01.environment.Parameters;
import re01.exception.Re01JLibException;
import re01.tool.helper.system.StringHelper;

/**
 *
 * @author renaud
 */
public class NoteReader {
	
	private final String TAG_HEADER_START = "[header]";
	private final String TAG_HEADER_END = "[/header]";
	private final String TAG_BODY_START = "[body]";
	private final String TAG_BODY_END = "[/body]";
	
	public NoteReader() {
		
	}
	
	public String getHeaderContent( String content, Boolean withDelimiters ) {
		StringHelper stringHelper = new StringHelper();
		return stringHelper.getStrPart( content, TAG_HEADER_START, TAG_HEADER_END, withDelimiters );
	}
	
	public String createHeaderContent( HashMap<String, String> params ) {
		StringBuilder stringBuilderHeaderContent = new StringBuilder(TAG_HEADER_START);
		stringBuilderHeaderContent.append("\n");
		
		if ( params != null ) {
			Set<Entry<String, String>> paramsSet = params.entrySet();
			Iterator<Entry<String, String>> paramsSetIt = paramsSet.iterator();
			while ( paramsSetIt.hasNext() ) {
				Entry<String, String> paramsEntry = paramsSetIt.next();
				stringBuilderHeaderContent.append(paramsEntry.getKey());
				stringBuilderHeaderContent.append("=");
				stringBuilderHeaderContent.append(paramsEntry.getValue());
				stringBuilderHeaderContent.append("\n");
			}
		}
		
		stringBuilderHeaderContent.append(TAG_HEADER_END);
		stringBuilderHeaderContent.append("\n");
		return stringBuilderHeaderContent.toString();
	}
	
	public String getBodyContent( String content, Boolean withDelimiters ) {
		StringHelper stringHelper = new StringHelper();
		return stringHelper.getStrPart( content, "[body]", "[/body]", withDelimiters );
	}
	
	public String createBodyContent( String content ) {
		StringBuilder stringBuilderBodyContent = new StringBuilder(TAG_BODY_START);
		stringBuilderBodyContent.append("\n");
		StringReader stringReader = null;
		BufferedReader bufferedReader = null;
		
		if ( content != null ) {
			try {
				stringReader = new StringReader( content );
				bufferedReader = new BufferedReader( stringReader );

				String line;
				while ( ( line = bufferedReader.readLine() ) != null ) {
					try {
						stringBuilderBodyContent.append(line);
						stringBuilderBodyContent.append("\n");
					} catch ( Exception e ) {

					}
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
		}
		
		stringBuilderBodyContent.append(TAG_BODY_END);
		stringBuilderBodyContent.append("\n");
		return stringBuilderBodyContent.toString();
	}
	
	public String parseToString( JTextPane pane ) throws Re01JLibException {
		StringBuilder stringBuilderParsed = new StringBuilder();
		
		int posStart = 0;
		Integer posEnd = pane.getDocument().getLength();
		
		HashMap<Object, Object> attributesCurrent = new HashMap<>();
		HashSet<String> attributesNamesStrCurrent = new HashSet<>();
		
		AttributeSet attributeSet = null;
		
		int w = posStart;
		
		while ( w < posEnd ) {
			pane.setCaretPosition( w );
			attributeSet = pane.getCharacterAttributes();

			//====================
			// region create current attributes
			//====================

			attributesNamesStrCurrent.clear();

			Enumeration attrNames = attributeSet.getAttributeNames();
			while ( attrNames.hasMoreElements() ) {
				Object attrName = attrNames.nextElement();
				String attrNameStr = attrName.toString();

				attributesNamesStrCurrent.add( attrNameStr );

				Object attrNameFound = null;

				Set<Map.Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Map.Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Map.Entry<Object, Object> attributeCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributeCurrentEntry.getKey();
					String attributeCurrentStr = attributeCurrent.toString();
					if ( attributeCurrentStr.equals(attrNameStr) ) {
						attrNameFound = attributeCurrent;
						break;
					}
				}

				Object attrValue = attributeSet.getAttribute(attrName);
				switch ( attrNameStr ) {
					case ("bold"):
						Boolean boldBool = (Boolean) attrValue;
						if ( attrNameFound != null ) {
							Boolean boldBoolCurrent = (Boolean) attributesCurrent.get( attrNameFound );
							if ( boldBool != boldBoolCurrent ) {
								if ( boldBool == true )
									stringBuilderParsed.append("[b]");
								else
									stringBuilderParsed.append("[/b]");
								
								attributesCurrent.replace( attrNameFound, attrValue );
							}
						} else if ( boldBool == true ) {
							stringBuilderParsed.append("[b]");
							attributesCurrent.put( attrName, attrValue );
						}
						break;
					case ("underline"):
						Boolean underlineBool = (Boolean) attrValue;
						if ( attrNameFound != null ) {
							Boolean underlineBoolCurrent = (Boolean) attributesCurrent.get( attrNameFound );
							if ( underlineBool != underlineBoolCurrent ) {
								if ( underlineBool == true )
									stringBuilderParsed.append("[u]");
								else
									stringBuilderParsed.append("[/u]");
								attributesCurrent.replace( attrNameFound, attrValue );
							}
						} else if ( underlineBool == true ) {
							stringBuilderParsed.append("[u]");
							attributesCurrent.put( attrName, attrValue );
						}
						break;
					case ("italic"):
						Boolean italicBool = (Boolean) attrValue;
						if ( attrNameFound != null ) {
							Boolean italicBoolCurrent = (Boolean) attributesCurrent.get( attrNameFound );
							if ( italicBool != italicBoolCurrent ) {
								if ( italicBool == true )
									stringBuilderParsed.append("[i]");
								else
									stringBuilderParsed.append("[/i]");
								
								attributesCurrent.replace( attrNameFound, attrValue );
							}
						} else if ( italicBool == true ) {
							stringBuilderParsed.append("[i]");
							attributesCurrent.put( attrName, attrValue );
						}
						break;
					case ("size"):
						Integer sizeInt = (Integer) attrValue;

						FontSize fontSizeH1 = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.Title1 );
						FontSize fontSizeH2 = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.Title2 );
						FontSize fontSizeH3 = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.Title3 );
						
						if ( attrNameFound != null ) {
							Integer sizeIntCurrent = (Integer) attributesCurrent.get( attrNameFound );
							if ( Objects.equals(sizeInt, sizeIntCurrent) == false ) {
								if ( Objects.equals(sizeIntCurrent, fontSizeH1.getSize()) == true && Objects.equals(sizeInt, fontSizeH1.getSize()) == false )
									stringBuilderParsed.append("[/h1]");
								else if ( Objects.equals(sizeIntCurrent, fontSizeH2.getSize()) == true && Objects.equals(sizeInt, fontSizeH2.getSize()) == false )
									stringBuilderParsed.append("[/h2]");
								else if ( Objects.equals(sizeIntCurrent, fontSizeH3.getSize()) == true && Objects.equals(sizeInt, fontSizeH3.getSize()) == false )
									stringBuilderParsed.append("[/h3]");

								if ( Objects.equals(sizeIntCurrent, fontSizeH1.getSize()) == false && Objects.equals(sizeInt, fontSizeH1.getSize()) == true )
									stringBuilderParsed.append("[h1]");
								else if ( Objects.equals(sizeIntCurrent, fontSizeH2.getSize()) == false && Objects.equals(sizeInt, fontSizeH2.getSize()) == true )
									stringBuilderParsed.append("[h2]");
								else if ( Objects.equals(sizeIntCurrent, fontSizeH3.getSize()) == false && Objects.equals(sizeInt, fontSizeH3.getSize()) == true )
									stringBuilderParsed.append("[h3]");
								
								attributesCurrent.replace( attrNameFound, attrValue );
							}
						} else {
							if ( Objects.equals(sizeInt, fontSizeH1.getSize()) == true )
								stringBuilderParsed.append("[h1]");
							else if ( Objects.equals(sizeInt, fontSizeH2.getSize()) == true )
								stringBuilderParsed.append("[h2]");
							else if ( Objects.equals(sizeInt, fontSizeH3.getSize()) == true )
								stringBuilderParsed.append("[h3]");
							
							attributesCurrent.put( attrName, attrValue );
						}
						break;
					case ("foreground"):
						java.awt.Color colorForeground = (java.awt.Color) attrValue;
						if ( attrNameFound != null ) {
							String strColorStart = "";
							String strColorEnd = "";
							java.awt.Color colorForegroundCurrent = (java.awt.Color) attributesCurrent.get( attrNameFound );
							if ( colorForeground.toString().toLowerCase().equals(colorForegroundCurrent.toString().toLowerCase()) == false ) {
								ColorTypeEnum[] colorsTypes = ColorTypeEnum.values();
								int colorsTypesLength = colorsTypes.length;
								for ( int i = 0; i < colorsTypesLength; i++ ) {
									ColorTypeEnum colorType = colorsTypes[i];
									Color color = new Color( colorType, ColorAttributeTypeEnum.Foreground );
									if ( color.getRgbColor() != null ) {// it can be null if color type is Transparent.
										if ( color.getRgbColor().toString().toLowerCase().equals(colorForegroundCurrent.toString().toLowerCase()) == true )
											strColorEnd = "[/cf_" + color.getColorType().toString().toLowerCase() + "]";
										else if ( color.getRgbColor().toString().toLowerCase().equals(colorForeground.toString().toLowerCase()) == true ) {
											strColorStart = "[cf_" + color.getColorType().toString().toLowerCase() + "]";
											// color is allowed
											attributesCurrent.replace( attrNameFound, attrValue );
										}
									}
								}
								stringBuilderParsed.append(strColorEnd);
								stringBuilderParsed.append(strColorStart);
							}
						} else {
							ColorTypeEnum[] colorsTypes = ColorTypeEnum.values();
							int colorsTypesLength = colorsTypes.length;
							for ( int i = 0; i < colorsTypesLength; i++ ) {
								ColorTypeEnum colorType = colorsTypes[i];
								Color color = new Color( colorType, ColorAttributeTypeEnum.Foreground );
								if ( color.getRgbColor() != null ) {// it can be null if color type is Transparent.
									if ( color.getRgbColor().toString().toLowerCase().equals(colorForeground.toString().toLowerCase()) == true ) {
										stringBuilderParsed.append("[cf_");
										stringBuilderParsed.append(color.getColorType().toString().toLowerCase());
										stringBuilderParsed.append("]");
										// color is allowed
										attributesCurrent.replace( attrNameFound, attrValue );
										break;
									}
								}
							}
							attributesCurrent.put( attrName, attrValue );
						}
						break;
					case ("background"):
						java.awt.Color colorBackground = (java.awt.Color) attrValue;
						if ( attrNameFound != null ) {
							String strColorStart = "";
							String strColorEnd = "";
							java.awt.Color colorBackgroundCurrent = (java.awt.Color) attributesCurrent.get( attrNameFound );
							if ( colorBackground.toString().toLowerCase().equals(colorBackgroundCurrent.toString().toLowerCase()) == false ) {
								ColorTypeEnum[] colorsTypes = ColorTypeEnum.values();
								int colorsTypesLength = colorsTypes.length;
								for ( int i = 0; i < colorsTypesLength; i++ ) {
									ColorTypeEnum colorType = colorsTypes[i];
									Color color = new Color( colorType, ColorAttributeTypeEnum.Foreground );
									if ( color.getRgbColor() != null ) {// it can be null if color type is Transparent.
										if ( color.getRgbColor().toString().toLowerCase().equals(colorBackgroundCurrent.toString().toLowerCase()) == true )
											strColorEnd = "[/cb_" + color.getColorType().toString().toLowerCase() + "]";
										else if ( color.getRgbColor().toString().toLowerCase().equals(colorBackground.toString().toLowerCase()) == true ) {
											strColorStart = "[cb_" + color.getColorType().toString().toLowerCase() + "]";
											// color is allowed
											attributesCurrent.replace( attrNameFound, attrValue );
										}
									}
								}
								stringBuilderParsed.append(strColorEnd);
								stringBuilderParsed.append(strColorStart);
							}
						} else {
							ColorTypeEnum[] colorsTypes = ColorTypeEnum.values();
							int colorsTypesLength = colorsTypes.length;
							for ( int i = 0; i < colorsTypesLength; i++ ) {
								ColorTypeEnum colorType = colorsTypes[i];
								Color color = new Color( colorType, ColorAttributeTypeEnum.Foreground );
								if ( color.getRgbColor() != null ) {// it can be null if color type is Transparent.
									if ( color.getRgbColor().toString().toLowerCase().equals(colorBackground.toString().toLowerCase()) == true ) {
										stringBuilderParsed.append("[cb_");
										stringBuilderParsed.append(color.getColorType().toString().toLowerCase());
										stringBuilderParsed.append("]");
										// color is allowed
										attributesCurrent.replace( attrNameFound, attrValue );
										break;
									}
								}
							}
							attributesCurrent.put( attrName, attrValue );
						}
						break;
				}
			}
			
			//====================
			// end region create current attributes
			//====================
			
			//====================
			// region remove in current attributes
			//====================
			
			HashSet<Object> attributesCurrentKeysToRemove = new HashSet<>();

			Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
			Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
			while ( attributesCurrentSetIt.hasNext() ) {
				Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
				Object attributeCurrent = attributesCurrentEntry.getKey();
				Object attributeCurrentValue = attributesCurrentEntry.getValue();
				String attrNameStr = attributeCurrent.toString();
				boolean isAttrNameStrFound = false;
				
				Iterator<String> attributesNamesStrCurrentIt = attributesNamesStrCurrent.iterator();
				while ( attributesNamesStrCurrentIt.hasNext() ) {
					String attributeNameStrCurrent = attributesNamesStrCurrentIt.next();
					if ( attributeNameStrCurrent.equals(attrNameStr) ) {
						isAttrNameStrFound = true;
						break;
					}
				}
				
				if ( isAttrNameStrFound == false ) {
					switch ( attrNameStr ) {
						case ("bold"):
							stringBuilderParsed.append("[/b]");
							break;
						case ("italic"):
							stringBuilderParsed.append("[/i]");
							break;
						case ("underline"):
							stringBuilderParsed.append("[/u]");
							break;
						case ("size"):
							FontSize fontSizeH1 = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.Title1 );
							FontSize fontSizeH2 = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.Title2 );
							FontSize fontSizeH3 = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.Title3 );
							
							Integer sizeIntCurrent = (Integer) attributeCurrentValue;
							if ( Objects.equals(sizeIntCurrent, fontSizeH1.getSize()) == true )
								stringBuilderParsed.append("[/h1]");
							else if ( Objects.equals(sizeIntCurrent, fontSizeH2.getSize()) == true )
								stringBuilderParsed.append("[/h2]");
							else if ( Objects.equals(sizeIntCurrent, fontSizeH3.getSize()) == true )
								stringBuilderParsed.append("[/h3]");
							break;
						case ("foreground"):
							java.awt.Color colorForegroundCurrent = (java.awt.Color) attributeCurrentValue;
							ColorTypeEnum[] colorsTypesForeground = ColorTypeEnum.values();
							int colorsTypesForegroundLength = colorsTypesForeground.length;
							for ( int i = 0; i < colorsTypesForegroundLength; i++ ) {
								ColorTypeEnum colorType = colorsTypesForeground[i];
								Color color = new Color( colorType, ColorAttributeTypeEnum.Foreground );
								if ( color.getRgbColor() != null ) {// it can be null if color type is Transparent.
									if ( color.getRgbColor().toString().toLowerCase().equals(colorForegroundCurrent.toString().toLowerCase()) == true ) {
										stringBuilderParsed.append("[/cf_");
										stringBuilderParsed.append(color.getColorType().toString().toLowerCase());
										stringBuilderParsed.append("]");
										break;
									}
								}
							}
							break;
						case ("background"):
							java.awt.Color colorBackgroundCurrent = (java.awt.Color) attributeCurrentValue;
							ColorTypeEnum[] colorsTypesBackground = ColorTypeEnum.values();
							int colorsTypesBackgroundLength = colorsTypesBackground.length;
							for ( int i = 0; i < colorsTypesBackgroundLength; i++ ) {
								ColorTypeEnum colorType = colorsTypesBackground[i];
								Color color = new Color( colorType, ColorAttributeTypeEnum.Foreground );
								if ( color.getRgbColor() != null ) {// it can be null if color type is Transparent.
									if ( color.getRgbColor().toString().toLowerCase().equals(colorBackgroundCurrent.toString().toLowerCase()) == true ) {
										stringBuilderParsed.append("[/cb_");
										stringBuilderParsed.append(color.getColorType().toString().toLowerCase());
										stringBuilderParsed.append("]");
										break;
									}
								}
							}
							break;
					}
					
					attributesCurrentKeysToRemove.add( attributeCurrent );
				}
			}
			
			Iterator<Object> attributesCurrentKeysToRemoveIt = attributesCurrentKeysToRemove.iterator();
			while ( attributesCurrentKeysToRemoveIt.hasNext() ) {
				Object attributeCurrentKeyToRemove = attributesCurrentKeysToRemoveIt.next();
				attributesCurrent.remove( attributeCurrentKeyToRemove );
			}
			
			//====================
			// end region remove in current attributes
			//====================
			
			String strChar = "";
			try {
				// Need to "pane.getDocument().getText()" instead of "pane.getText()" overwise the length of the text will not be equals on differents operating systems
				strChar = pane.getDocument().getText(w, 1);
			} catch (BadLocationException ex) {
				Logger.getLogger(NoteReader.class.getName()).log(Level.SEVERE, null, ex);
			}
			stringBuilderParsed.append(strChar);
			w++;
		}
		return stringBuilderParsed.toString();
	}
	
	public JTextPane parseToPane( String strToParse, JTextPane pane ) throws Re01JLibException {
		if ( strToParse != null ) {
			String body = getBodyContent( strToParse, false );
			
			if ( body != null ) {
				StringHelper stringHelper = new StringHelper();
				re01.design.color.Colors colors = new re01.design.color.Colors();
				HashSet<String> tagsAllowed = new HashSet<>();
				HashSet<String> tagsColorsAllowed = new HashSet<>();
				HashSet<String> tagsCharsAllowed = new HashSet<>();

				tagsAllowed.addAll( Arrays.asList( new String[]{ "[b]", "[i]", "[u]", "[/b]", "[/i]", "[/u]", "[h1]", "[h2]", "[h3]", "[/h1]", "[/h2]", "[/h3]" } ) );

				Iterator<String> tagsAllowedIt = tagsAllowed.iterator();
				while ( tagsAllowedIt.hasNext() ) {
					String tagAllowed = tagsAllowedIt.next();
					try {
						tagsCharsAllowed = stringHelper.getCharsFromString( tagsCharsAllowed, tagAllowed );
					} catch ( Exception e ) { }
				}

				ColorTypeEnum[] colorsTypes = ColorTypeEnum.values();
				int colorsTypesLength = colorsTypes.length;
				for ( int i = 0; i < colorsTypesLength; i++ ) {
					String colorTypeStr = colorsTypes[i].toString().toLowerCase();
					String colorForegroundStartStr = "[cf_" + colorTypeStr + "]";
					String colorForegroundEndStr = "[/cf_" + colorTypeStr + "]";
					String colorBackgroundStartStr = "[cb_" + colorTypeStr + "]";
					String colorBackgroundEndStr = "[/cb_" + colorTypeStr + "]";

					try {
						tagsCharsAllowed = stringHelper.getCharsFromString( tagsCharsAllowed, colorForegroundStartStr );
					} catch ( Exception e ) { }
					try {
						tagsCharsAllowed = stringHelper.getCharsFromString( tagsCharsAllowed, colorBackgroundStartStr );
					} catch ( Exception e ) { }

					tagsColorsAllowed.add( colorForegroundStartStr );
					tagsColorsAllowed.add( colorForegroundEndStr );
					tagsColorsAllowed.add( colorBackgroundStartStr );
					tagsColorsAllowed.add( colorBackgroundEndStr );
				}
				tagsAllowed.addAll( tagsColorsAllowed );

				int index = 0;
				StringBuilder stringBuilderTag = new StringBuilder();
				String character;
				StringBuilder stringBuilder = new StringBuilder();
				String strToAdd;
				ArrayList<FontStyleEnum> fontsStyles = new ArrayList<>();
				ColorTypeEnum foregroundColor = null;
				ColorTypeEnum backgroundColor = null;

				do {
					character = null;
					try {
						character = body.substring( index, index + 1 );
						if ( character != null ) {
							stringBuilder.append(character);
							String characterLower = character.toLowerCase();

							if ( tagsCharsAllowed.contains(characterLower) )
								stringBuilderTag.append(characterLower);
							else
								stringBuilderTag = new StringBuilder();

							String tag = stringBuilderTag.toString();
							int tagLength = stringBuilderTag.length();
							for ( int i = 0; i < tagLength; i++ ) {
								String tagSub = tag.substring( i, tagLength );

								if ( tagsAllowed.contains(tagSub) ) {
									String str = stringBuilder.toString();
									strToAdd = str.substring( 0, str.length() - tagSub.length() );
									if ( !strToAdd.isEmpty() ) {
										pane.addText( strToAdd, Parameters.getThemeSelected().createFont(fontsStyles, foregroundColor, backgroundColor) );
									}
									stringBuilder = new StringBuilder();

									switch ( tagSub ) {
										case ( "[b]" ):
											if ( fontsStyles.contains(FontStyleEnum.Bold ) == false )
												fontsStyles.add(FontStyleEnum.Bold );
											break;
										case ( "[i]" ):
											if ( fontsStyles.contains(FontStyleEnum.Italic ) == false )
												fontsStyles.add(FontStyleEnum.Italic );
											break;
										case ( "[u]" ):
											if ( fontsStyles.contains(FontStyleEnum.Underline ) == false )
												fontsStyles.add(FontStyleEnum.Underline );
											break;
										case ( "[/b]" ):
											if ( fontsStyles.contains(FontStyleEnum.Bold ) == true )
												fontsStyles.remove(FontStyleEnum.Bold );
											break;
										case ( "[/i]" ):
											if ( fontsStyles.contains(FontStyleEnum.Italic ) == true )
												fontsStyles.remove(FontStyleEnum.Italic );
											break;
										case ( "[/u]" ):
											if ( fontsStyles.contains(FontStyleEnum.Underline ) == true )
												fontsStyles.remove(FontStyleEnum.Underline );
											break;
										case ( "[h1]" ):
											if ( fontsStyles.contains(FontStyleEnum.Title1 ) == false )
												fontsStyles.add(FontStyleEnum.Title1 );
											break;
										case ( "[h2]" ):
											if ( fontsStyles.contains(FontStyleEnum.Title2 ) == false )
												fontsStyles.add(FontStyleEnum.Title2 );
											break;
										case ( "[h3]" ):
											if ( fontsStyles.contains(FontStyleEnum.Title3 ) == false )
												fontsStyles.add(FontStyleEnum.Title3 );
											break;
										case ( "[/h1]" ):
											if ( fontsStyles.contains(FontStyleEnum.Title1 ) == true )
												fontsStyles.remove(FontStyleEnum.Title1 );
											break;
										case ( "[/h2]" ):
											if ( fontsStyles.contains(FontStyleEnum.Title2 ) == true )
												fontsStyles.remove(FontStyleEnum.Title2 );
											break;
										case ( "[/h3]" ):
											if ( fontsStyles.contains(FontStyleEnum.Title3 ) == true )
												fontsStyles.remove(FontStyleEnum.Title3 );
											break;
										default:
											if ( tagSub.startsWith("[cf_") ) {
												tagSub = tagSub.replace( "[cf_", "" );
												tagSub = tagSub.replace( "]", "" );
												foregroundColor = colors.getColorType( tagSub );
											} else if ( tagSub.startsWith("[/cf_") ) {
												tagSub = tagSub.replace( "[/cf_", "" );
												tagSub = tagSub.replace( "]", "" );
												ColorTypeEnum currentColor = colors.getColorType( tagSub );
												if ( foregroundColor != null && currentColor.toString().equals(foregroundColor.toString()) )
													foregroundColor = null;
											} else if ( tagSub.startsWith("[cb_") ) {
												tagSub = tagSub.replace( "[cb_", "" );
												tagSub = tagSub.replace( "]", "" );
												backgroundColor = colors.getColorType( tagSub );
											} else if ( tagSub.startsWith("[/cb_") ) {
												tagSub = tagSub.replace( "[/cb_", "" );
												tagSub = tagSub.replace( "]", "" );
												ColorTypeEnum currentColor = colors.getColorType( tagSub );
												if ( backgroundColor != null && currentColor.toString().equals(backgroundColor.toString()) )
													backgroundColor = null;
											}
											break;
									}
									stringBuilderTag = new StringBuilder();
									break;
								}
							}
						}

						index++;
					} catch ( StringIndexOutOfBoundsException e ) { }
				} while ( character != null );

				Integer stringBuilderLength = stringBuilder.length();
				if ( java.util.Objects.equals(stringBuilderLength, 0) == false ) {
					String str = stringBuilder.toString();
					strToAdd = str.substring( 0, stringBuilderLength );
					if ( !strToAdd.isEmpty() ) {
						pane.addText( strToAdd, Parameters.getThemeSelected().createFont(fontsStyles, foregroundColor, backgroundColor) );
					}
				}
			}
		}
		
		return pane;
	}
	
}
