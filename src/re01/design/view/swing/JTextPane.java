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

package re01.design.view.swing;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import javax.swing.KeyStroke;
import javax.swing.text.AttributeSet;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import re01.design.color.Color;
import re01.design.color.ColorTypeEnum;
import re01.design.font.FontSize;
import re01.design.font.Font;
import re01.design.font.FontStyleEnum;
import re01.design.theme.Theme;
import re01.design.theme.ThemeTypeEnum;
import re01.design.view.swing.jtextpane.DocumentHistory;
import re01.environment.Parameters;
import re01.exception.Re01JLibException;
import re01.tool.helper.system.KeyboardHelper;
import re01.tool.helper.system.MethodHelper;
import re01.tool.timer.TimerTaskDocumentHistory;

/**
 *
 * @author renaud
 */
public class JTextPane extends javax.swing.JTextPane {
	
	private HashSet<KeyListener> keysListeners = new HashSet<>();
	private HashSet<MouseListener> mousesListeners = new HashSet<>();
	
	private static final String ARG_KEY_COPY_STYLE_VALUE = "re01lib_view_jtextpane_copy_style_value";
	
	protected Object[] callbacksArgs;
	
	protected MouseAdapter scrollMouseAdapter = null;
	protected ArrayList<DocumentHistory> documentHistory = new ArrayList<>();
	protected int documentHistoryPosition = 0;
	protected TimerTaskDocumentHistory timerTaskDocumentHistory = null;
	protected Boolean isFirstDocumentHistoryInserted = false;
	protected Boolean isDocumentHistoryNeedPurge = false;
	protected Boolean isCharInsertedFromLastShift = false;
	
	protected Integer selectionStartIndex = 0;
	protected Integer scrollPosition;
	protected ArrayList<Integer> searchFoundPositions = new ArrayList<>();
	protected Integer searchFoundQty = null;
	protected Integer searchFoundQtyPosition = 0;
	protected String searchTextLast = "";
	protected String textLast = "";
	
	public JTextPane( String text, boolean enableCopyActionWithStyle, boolean enablePasteAction, Object[] callbacksArgs ) throws Re01JLibException {
		super();
		construct( enableCopyActionWithStyle, enablePasteAction, callbacksArgs );
		addText( text, null );
	}
	
	public JTextPane( String text, Font font, boolean enableCopyActionWithStyle, boolean enablePasteAction, Object[] callbacksArgs ) throws Re01JLibException {
		super();
		construct( enableCopyActionWithStyle, enablePasteAction, callbacksArgs );
		addText( text, font );
	}
	
	private void construct( boolean enableCopyActionWithStyle, boolean enablePasteAction, Object[] callbacksArgs ) {
		this.callbacksArgs = callbacksArgs;
		JTextPane pane = this;
		KeyboardHelper keyboardHelper = new KeyboardHelper();
		
		this.addKeyListener( new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				Integer keyCode = e.getKeyCode();
				
				insertFirstDocumentHistory();
				
				if ( Objects.equals(keyCode, KeyEvent.VK_S) && e.isControlDown() == true ) {
					if ( enableCopyActionWithStyle == true ) {
						try {
							HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> copiedStyle = pane.copyStyle(false);
							MethodHelper.addOrReplaceCallbackArg( ARG_KEY_COPY_STYLE_VALUE, pane.copyStyle(false), pane.getCallbacksArgs() );
							MethodHelper.executeCallbacks( pane.getCallbacksArgs() );
						} catch (Exception ex) { }
						pane.copy();
					}
				} else if ( Objects.equals(keyCode, KeyEvent.VK_Z) && e.isControlDown() == true && e.isShiftDown() == true
				|| Objects.equals(keyCode, KeyEvent.VK_Y) && e.isControlDown() == true ) {
					
					insertDocumentHistoryPending();
					
					Integer documentHistoryLength = pane.getDocumentHistory().size();
					Iterator<DocumentHistory> documentHistoryIt = pane.getDocumentHistory().iterator();
					DocumentHistory documentHistoryLast = null;
					Integer documentHistoryPositionLast = documentHistoryPosition;
					if ( Objects.equals(documentHistoryPositionLast, documentHistoryLength) == false ) {
						int i = 0;
						while ( documentHistoryIt.hasNext() ) {
							DocumentHistory documentHistoryFound = documentHistoryIt.next();
							documentHistoryLast = documentHistoryFound;
							if ( Objects.equals(documentHistoryPositionLast, i) == true )
								break;
							i++;
						}
						if ( documentHistoryLast != null ) {
							applyDocumentHistory( documentHistoryLast );
						}

						if ( documentHistoryPosition < documentHistoryLength )
							documentHistoryPosition++;
					}
					
				} else if ( Objects.equals(keyCode, KeyEvent.VK_Z) && e.isControlDown() == true ) {
					
					insertDocumentHistoryPending();
					
					Iterator<DocumentHistory> documentHistoryIt = pane.getDocumentHistory().iterator();
					DocumentHistory documentHistoryLast = null;
					Integer documentHistoryPositionLast = documentHistoryPosition - 2;
					if ( Objects.equals(documentHistoryPositionLast, -1) == false ) {
						int i = 0;
						while ( documentHistoryIt.hasNext() ) {
							DocumentHistory documentHistoryFound = documentHistoryIt.next();
							documentHistoryLast = documentHistoryFound;
							if ( Objects.equals(documentHistoryPositionLast, i) == true )
								break;
							i++;
						}
						if ( documentHistoryLast != null ) {
							applyDocumentHistory( documentHistoryLast );
						}

						if ( documentHistoryPosition > 0 )
							documentHistoryPosition--;
					}
					
					isDocumentHistoryNeedPurge = true;
				} else if ( Objects.equals(keyCode, KeyEvent.VK_SHIFT) == false 
				&& keyboardHelper.isKeyCodeNonCharKey(keyCode) == false )
					isCharInsertedFromLastShift = true;
			}

			@Override
			public void keyReleased( KeyEvent evt ) {
				Integer keyCode = evt.getKeyCode();
				if ( evt.isControlDown() == false 
				&& Objects.equals(keyCode, KeyEvent.VK_CONTROL) == false 
				&& ( Objects.equals(keyCode, KeyEvent.VK_SHIFT) == false || Objects.equals(keyCode, KeyEvent.VK_SHIFT) == true && isCharInsertedFromLastShift == true ) 
				&& keyboardHelper.isKeyCodeNonCharKey(keyCode) == false ) {
					final Timer TIMER = new Timer();
					
					if ( timerTaskDocumentHistory != null ) {
						timerTaskDocumentHistory.cancel();
					}
					
					timerTaskDocumentHistory = new TimerTaskDocumentHistory( TIMER ) {
						@Override
						public void run() {
							insertDocumentHistory();

							this.cancel();
							
							super.run();
						}
					};
					
					TIMER.schedule( timerTaskDocumentHistory, 750 );
				}
			}
		} );
		
		scrollMouseAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				final JTextPane PANE = (JTextPane) e.getSource();
				if (searchFoundQty != null)
					scrollPosition = PANE.getCaretPosition();
				
				super.mousePressed( e );
			}
		};
		this.addMouseListener( scrollMouseAdapter );
		
		this.setBackground( Parameters.getThemeSelected().getBackgroundColor().getRgbColor() );
		this.setSelectedTextColor( Parameters.getThemeSelected().getSelectedTextColor().getRgbColor() );//Foreground
		this.setSelectionColor( Parameters.getThemeSelected().getSelectionColor().getRgbColor() );//Background
		
		java.awt.Font font = Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(Arrays.asList(FontStyleEnum.SizeNormal))).getFont();
		if ( font == null ) {
			FontSize fontSize = new FontSize( Parameters.getThemeSelected(), FontStyleEnum.SizeNormal );
			font = new java.awt.Font( getFont().getFamily(), java.awt.Font.PLAIN, fontSize.getSize() );
		}
		setFont( font );

		JPopupMenu popupMenu = new JPopupMenu();
		
		DefaultEditorKit.CopyAction copyAction = new DefaultEditorKit.CopyAction();
		copyAction.putValue( DefaultEditorKit.CopyAction.NAME, Parameters.getLanguageSelected().get_LANG().get_COPY() );
		copyAction.putValue( DefaultEditorKit.CopyAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke( Parameters.getLanguageSelected().get_LANG().get_COPY_KEYSTROKE() ) );
		
		JMenuItem menuItemCopyAction = new JMenuItem( copyAction );
		menuItemCopyAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MethodHelper.addOrReplaceCallbackArg( ARG_KEY_COPY_STYLE_VALUE, null, pane.getCallbacksArgs() );
					MethodHelper.executeCallbacks( pane.getCallbacksArgs() );
				} catch (Exception ex) { }
			}
		} );
		popupMenu.add( menuItemCopyAction );
		
		if ( enableCopyActionWithStyle == true ) {
			DefaultEditorKit.CopyAction copyActionWithStyle = new DefaultEditorKit.CopyAction();
			copyActionWithStyle.putValue( DefaultEditorKit.CopyAction.NAME, Parameters.getLanguageSelected().get_LANG().get_COPY_WITH_STYLE());
			copyActionWithStyle.putValue( DefaultEditorKit.CopyAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke( Parameters.getLanguageSelected().get_LANG().get_COPY_WITH_STYLE_KEYSTROKE() ) );
			
			JMenuItem menuItemCopyActionWithStyle = new JMenuItem( copyActionWithStyle );
			menuItemCopyActionWithStyle.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> copiedStyle = pane.copyStyle(false);
						MethodHelper.addOrReplaceCallbackArg( ARG_KEY_COPY_STYLE_VALUE, pane.copyStyle(false), pane.getCallbacksArgs() );
						MethodHelper.executeCallbacks( pane.getCallbacksArgs() );
					} catch (Exception ex) { }
				}
			} );
			popupMenu.add( menuItemCopyActionWithStyle );
		}
		
		if ( enablePasteAction == true ) {
			DefaultEditorKit.PasteAction pasteAction = new DefaultEditorKit.PasteAction();
			pasteAction.putValue( DefaultEditorKit.PasteAction.NAME, Parameters.getLanguageSelected().get_LANG().get_PASTE() );
			pasteAction.putValue( DefaultEditorKit.PasteAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke( Parameters.getLanguageSelected().get_LANG().get_PASTE_KEYSTROKE() ) );

			JMenuItem menuItemPasteAction = new JMenuItem( pasteAction );
			popupMenu.add( menuItemPasteAction );
		}
		this.setComponentPopupMenu( popupMenu );
	}
	
	@Override
	protected void finalize() throws Throwable {
		Iterator<KeyListener> keysListenersIt = keysListeners.iterator();
		while ( keysListenersIt.hasNext() ) {
			KeyListener listener = keysListenersIt.next();
			super.removeKeyListener( listener );
		}
		keysListeners.clear();
		
		Iterator<MouseListener> mousesListenersIt = mousesListeners.iterator();
		while ( mousesListenersIt.hasNext() ) {
			MouseListener listener = mousesListenersIt.next();
			super.removeMouseListener( listener );
		}
		mousesListeners.clear();
		
		try {
			this.removeMouseListener( scrollMouseAdapter );
		} catch (Exception e) { }
		
		if ( timerTaskDocumentHistory != null ) {
			timerTaskDocumentHistory.cancel();
		}
		
		super.finalize();
	}
	
	@Override
	public void addKeyListener( KeyListener listener ) {
		if ( keysListeners != null )
			keysListeners.add( listener );
		super.addKeyListener( listener );
	}
	
	@Override
	public void removeKeyListener( KeyListener listener ) {
		if ( keysListeners != null )
			keysListeners.remove( listener );
		super.removeKeyListener( listener );
	}
	
	@Override
	public void addMouseListener( MouseListener listener ) {
		if ( mousesListeners != null )
			mousesListeners.add( listener );
		super.addMouseListener( listener );
	}
	
	@Override
	public void removeMouseListener( MouseListener listener ) {
		if ( mousesListeners != null )
			mousesListeners.remove( listener );
		super.removeMouseListener( listener );
	}
	
	@Override
	public void cut() {
		super.cut();
		insertDocumentHistoryPending();
		insertDocumentHistory();
	}
	
	@Override
	public void paste() {
		super.paste();
		insertDocumentHistoryPending();
		insertDocumentHistory();
	}
	
	@Override
	public JToolTip createToolTip() {
		return new JToolTip();
	}
	
	//====================
	// region G & S
	//====================

	public static final String get_ARG_KEY_COPY_STYLE_VALUE() {
		return ARG_KEY_COPY_STYLE_VALUE;
	}

	public Object[] getCallbacksArgs() {
		return callbacksArgs;
	}

	public ArrayList<DocumentHistory> getDocumentHistory() {
		return documentHistory;
	}

	public void setDocumentHistory(ArrayList<DocumentHistory> documentHistory) {
		this.documentHistory = documentHistory;
	}

	public int getSelectionStartIndex() {
		return selectionStartIndex;
	}

	public void setSelectionStartIndex(int selectionStartIndex) {
		this.selectionStartIndex = selectionStartIndex;
	}

	public Integer getSearchFoundQty() {
		return searchFoundQty;
	}

	public Integer getSearchFoundQtyPosition() {
		return searchFoundQtyPosition;
	}
	
	//====================
	// end region G & S
	//====================
	
	//====================
	// region DocumentHistory
	//====================
	
	private void purgeDocumentHistory() {
		int documentHistoryLength = documentHistory.size();
		for ( int i = documentHistoryPosition; i < documentHistoryLength ; i++ ) {
			try {
				documentHistory.remove( documentHistoryPosition );
			} catch (IndexOutOfBoundsException e) { }
		}
	}
	
	private void applyDocumentHistory( DocumentHistory documentHistory ) {
		this.setText( documentHistory.getText() );
		try {
			this.setCharacterAttributes( documentHistory.getStyle(), false, null, null, null );
		} catch (Re01JLibException ex) {
			
		}
		try {
			this.setCaretPosition( documentHistory.getCaretPosition() + 1 );
		} catch ( IllegalArgumentException ex ) {
			this.setCaretPosition( documentHistory.getCaretPosition() );
		}
	}
	
	private void insertDocumentHistory() {
		if ( isDocumentHistoryNeedPurge == true ) {
			isDocumentHistoryNeedPurge = false;
			purgeDocumentHistory();
		}
		
		int selectionStart = this.getSelectionStart();
		int selectionEnd = this.getSelectionEnd();
		
		int caretPosition = this.getCaretPosition();
		DocumentHistory documentHistory = null;
		try {
			documentHistory = new DocumentHistory( this.getText(), this.copyStyle(true), caretPosition );
		} catch (Re01JLibException ex) {
			
		}

		if ( documentHistory != null )
			this.getDocumentHistory().add( documentHistory );

		int documentHistoryLength = this.getDocumentHistory().size();
		documentHistoryPosition = documentHistoryLength;

		if ( documentHistoryLength > Parameters.getDocumentHistoryLength() ) {
			this.getDocumentHistory().remove(0);
		}
		
		isCharInsertedFromLastShift = false;
	}
	
	private void insertFirstDocumentHistory() {
		if ( isFirstDocumentHistoryInserted == false ) {
			insertDocumentHistory();
			isFirstDocumentHistoryInserted = true;
		}
	}
	
	private void insertDocumentHistoryPending() {
		if ( timerTaskDocumentHistory != null && timerTaskDocumentHistory.getIsRunDone() == false ) {
			timerTaskDocumentHistory.run();
			timerTaskDocumentHistory = null;
		}
	}
	
	//====================
	// end region DocumentHistory
	//====================
	
	//====================
	// region text & style
	//====================
	
	public HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> copyStyle( Boolean isCopyAll ) throws Re01JLibException {
		JTextPane paneToCopy;
		if ( isCopyAll == false )
			paneToCopy = this;
		else {
			paneToCopy = new JTextPane( 
				this.getText(), 
				Parameters.getThemeSelected().createFont(new ArrayList<FontStyleEnum>(), ColorTypeEnum.Black, ColorTypeEnum.White), 
				true, 
				false, 
				null
			);
			paneToCopy.setStyledDocument( this.getStyledDocument() );
		}
		
		Integer posStart;
		Integer posEnd;
		if ( isCopyAll == false ) {
			posStart = paneToCopy.getSelectionStart();
			posEnd = paneToCopy.getSelectionEnd();
		} else {
			posStart = 0;
			posEnd = paneToCopy.getDocument().getLength();
		}
		
		HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> simplesAttributesSet = new HashMap<>();
		Integer simplesAttributesSetIndex = 0;
		HashMap<Object, Object> attributesLast = null;
		HashMap<Object, Object> attributesCurrent = new HashMap<>();
		
		AttributeSet attributeSet = null;
		
		Integer posStartNew = 0;
		Integer wNew = posStartNew;
		Integer wNewLast = wNew;
		
		Integer w = posStart;
		while ( w <= posEnd ) {
			paneToCopy.setCaretPosition( w );
			attributeSet = paneToCopy.getCharacterAttributes();

			//====================
			// region create current attributes
			//====================

			attributesCurrent.clear();

			Enumeration attrNames = attributeSet.getAttributeNames();
			while ( attrNames.hasMoreElements() ) {
				Object attrName = attrNames.nextElement();
				String attrNameStr = attrName.toString();

				Boolean attrNameStrFound = false;
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributeCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributeCurrentEntry.getKey();
					String attributeCurrentStr = attributeCurrent.toString();
					if ( attributeCurrentStr.equals(attrNameStr) ) {
						attrNameStrFound = true;
						break;
					}
				}
				if ( attrNameStrFound == false )
					attributesCurrent.put( attrName, attributeSet.getAttribute(attrName) );
			}

			if ( attributesLast == null ) {
				attributesLast = new HashMap<Object, Object>();
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributesCurrentEntry.getKey();
					Object attributeCurrentValue = attributesCurrentEntry.getValue();

					attributesLast.put( attributeCurrent, attributeCurrentValue );
				}
			}

			//====================
			// end region create current attributes
			//====================

			//====================
			// region compare attributes
			//====================

			Boolean isAttributesLastSameAsCurrent = true;

			Integer attributesCurrentLength = attributesCurrent.size();
			Integer attributesLastLength = attributesLast.size();
			Integer compareAttributesLength = attributesCurrentLength.compareTo(attributesLastLength);
			if ( compareAttributesLength < 0 || compareAttributesLength > 0 ) {
				isAttributesLastSameAsCurrent = false;
			} else {
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributesCurrentEntry.getKey();
					String attributeCurrentStr = attributeCurrent.toString();

					Boolean attrNameStrFound = false;
					Set<Entry<Object, Object>> attributesLastSet = attributesLast.entrySet();
					Iterator<Entry<Object, Object>> attributesLastSetIt = attributesLastSet.iterator();
					while ( attributesLastSetIt.hasNext() ) {
						Entry<Object, Object> attributesLastEntry = attributesLastSetIt.next();
						Object attributeLast = attributesLastEntry.getKey();
						String attributeLastStr = attributeLast.toString();

						if ( attributeLastStr.equals(attributeCurrentStr) ) {

							switch ( attributeLastStr ) {
								case ("bold"):
								case ("underline"):
								case ("italic"):
									Boolean boolCurrent = (Boolean) attributesCurrentEntry.getValue();
									Boolean boolLast = (Boolean) attributesLastEntry.getValue();
									if ( boolCurrent == boolLast ) {
										attrNameStrFound = true;
									}
									break;
								case ("size"):
									Integer intCurrent = (Integer) attributesCurrentEntry.getValue();
									Integer intLast = (Integer) attributesLastEntry.getValue();
									if ( Objects.equals(intCurrent, intLast) == true ) {
										attrNameStrFound = true;
									}
									break;
								case ("foreground"):
								case ("background"):
									java.awt.Color colorCurrent = (java.awt.Color) attributesCurrentEntry.getValue();
									java.awt.Color colorLast = (java.awt.Color) attributesLastEntry.getValue();
									if ( colorCurrent.equals(colorLast) == true ) {
										attrNameStrFound = true;
									}
									break;
							}
						}
						if ( attrNameStrFound == true )
							break;
					}
					if ( attrNameStrFound == false ) {
						isAttributesLastSameAsCurrent = false;
						break;
					}
				}
				if ( isAttributesLastSameAsCurrent == true ) {
					Set<Entry<Object, Object>> attributesLastSet = attributesLast.entrySet();
					Iterator<Entry<Object, Object>> attributesLastSetIt = attributesLastSet.iterator();
					while ( attributesLastSetIt.hasNext() ) {
						Entry<Object, Object> attributesLastEntry = attributesLastSetIt.next();
						Object attributeLast = attributesLastEntry.getKey();
						String attributeLastStr = attributeLast.toString();

						Boolean attrNameStrFound = false;
						attributesCurrentSetIt = attributesCurrentSet.iterator();
						while ( attributesCurrentSetIt.hasNext() ) {
							Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
							Object attributeCurrent = attributesCurrentEntry.getKey();
							String attributesCurrentStr = attributeCurrent.toString();
							if ( attributesCurrentStr.equals(attributeLastStr) == true ) {

								switch ( attributeLastStr ) {
									case ("bold"):
									case ("underline"):
									case ("italic"):
										Boolean boolCurrent = (Boolean) attributesCurrentEntry.getValue();
										Boolean boolLast = (Boolean) attributesLastEntry.getValue();
										if ( boolCurrent == boolLast ) {
											attrNameStrFound = true;
										}
										break;
									case ("size"):
										Integer intCurrent = (Integer) attributesCurrentEntry.getValue();
										Integer intLast = (Integer) attributesLastEntry.getValue();
										if ( Objects.equals(intCurrent, intLast) == true ) {
											attrNameStrFound = true;
										}
										break;
									case ("foreground"):
									case ("background"):
										java.awt.Color colorCurrent = (java.awt.Color) attributesCurrentEntry.getValue();
										java.awt.Color colorLast = (java.awt.Color) attributesLastEntry.getValue();
										if ( colorCurrent.equals(colorLast) == true ) {
											attrNameStrFound = true;
										}
										break;
								}
							}
							if ( attrNameStrFound == true )
								break;
						}
						if ( attrNameStrFound == false ) {
							isAttributesLastSameAsCurrent = false;
							break;
						}
					}
				}
			}

			//====================
			// end region compare attributes
			//====================

			if ( isAttributesLastSameAsCurrent == false || Objects.equals(w, posEnd) == true ) {
				//====================
				// region add attribute
				//====================

				SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
				Set<Entry<Object, Object>> attributesLastSet = attributesLast.entrySet();
				Iterator<Entry<Object, Object>> attributesLastSetIt = attributesLastSet.iterator();
				while ( attributesLastSetIt.hasNext() ) {
					Entry<Object, Object> attributesLastEntry = attributesLastSetIt.next();
					Object attributeLast = attributesLastEntry.getKey();
					Object attributeLastVal = attributesLastEntry.getValue();
					String attributeLastStr = attributeLast.toString();
					switch ( attributeLastStr ) {
						case ("bold"):
							simpleAttributeSet.addAttribute( StyleConstants.FontConstants.Bold, attributeLastVal );
							break;
						case ("italic"):
							simpleAttributeSet.addAttribute( StyleConstants.CharacterConstants.Italic, attributeLastVal );
							break;
						case ("underline"):
							simpleAttributeSet.addAttribute( StyleConstants.CharacterConstants.Underline, attributeLastVal );
							break;
						case ("size"):
							simpleAttributeSet.addAttribute( StyleConstants.FontConstants.Size, attributeLastVal );
							break;
						case ("background"):
							simpleAttributeSet.addAttribute( StyleConstants.ColorConstants.Background, attributeLastVal );
							break;
						case ("foreground"):
							simpleAttributeSet.addAttribute( StyleConstants.ColorConstants.Foreground, attributeLastVal );
							break;
					}
				}

				HashMap<SimpleAttributeSet, Integer[]> simpleAttributeSetMap = new HashMap<>();
				simpleAttributeSetMap.put( simpleAttributeSet, new Integer[]{ wNewLast, wNew } );
				simplesAttributesSet.put( simplesAttributesSetIndex, simpleAttributeSetMap );
				simplesAttributesSetIndex++;

				//====================
				// end region add attribute
				//====================

				wNewLast = wNew;

				attributesLast = new HashMap<Object, Object>();
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributesCurrentEntry.getKey();
					Object attributeCurrentValue = attributesCurrentEntry.getValue();
					attributesLast.put( attributeCurrent, attributeCurrentValue );
				}
			}
			
			w++;
			wNew++;
		}
		
		if ( isCopyAll == false )
			selectText( posStart, posEnd );
		
		return simplesAttributesSet;
	}
	
	public void addStyle( FontStyleEnum fontStyleNew, Color colorNew ) throws Re01JLibException {
		insertFirstDocumentHistory();
		
		Integer posStart = this.getSelectionStart();
		Integer posEnd = this.getSelectionEnd();
		
		HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> simplesAttributesSet = new HashMap<>();
		Integer simplesAttributesSetIndex = 0;
		HashMap<Object, Object> attributesLast = null;
		HashMap<Object, Object> attributesCurrent = new HashMap<>();
		
		AttributeSet attributeSet = null;
		Boolean isAttrExistOnEveryPosition = true;
		
		Integer w = posStart;
		Integer wLast = w;
		while ( w <= posEnd ) {
			this.setCaretPosition( w );
			attributeSet = this.getCharacterAttributes();
			
			//====================
			// region create current attributes
			//====================
			
			attributesCurrent.clear();
			
			Enumeration attrNames = attributeSet.getAttributeNames();
			while ( attrNames.hasMoreElements() ) {
				Object attrName = attrNames.nextElement();
				String attrNameStr = attrName.toString();
				
				Boolean attrNameStrFound = false;
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributeCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributeCurrentEntry.getKey();
					String attributeCurrentStr = attributeCurrent.toString();
					if ( attributeCurrentStr.equals(attrNameStr) == true ) {
						attrNameStrFound = true;
						break;
					}
				}
				if ( attrNameStrFound == false )
					attributesCurrent.put( attrName, attributeSet.getAttribute(attrName) );
			}
			
			if ( attributesLast == null ) {
				attributesLast = new HashMap<Object, Object>();
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributesCurrentEntry.getKey();
					Object attributeCurrentValue = attributesCurrentEntry.getValue();
					
					attributesLast.put( attributeCurrent, attributeCurrentValue );
				}
			}
			
			//====================
			// end region create current attributes
			//====================
			
			//====================
			// region compare attributes
			//====================
			
			Boolean isAttributesLastSameAsCurrent = true;
			
			Integer attributesCurrentLength = attributesCurrent.size();
			Integer attributesLastLength = attributesLast.size();
			Integer compareAttributesLength = attributesCurrentLength.compareTo(attributesLastLength);
			if ( compareAttributesLength < 0 || compareAttributesLength > 0 ) {
				isAttributesLastSameAsCurrent = false;
			} else {
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributesCurrentEntry.getKey();
					String attributeCurrentStr = attributeCurrent.toString();
					
					Boolean attrNameStrFound = false;
					Set<Entry<Object, Object>> attributesLastSet = attributesLast.entrySet();
					Iterator<Entry<Object, Object>> attributesLastSetIt = attributesLastSet.iterator();
					while ( attributesLastSetIt.hasNext() ) {
						Entry<Object, Object> attributesLastEntry = attributesLastSetIt.next();
						Object attributeLast = attributesLastEntry.getKey();
						String attributeLastStr = attributeLast.toString();
						
						if ( attributeLastStr.equals(attributeCurrentStr) ) {
							
							switch ( attributeLastStr ) {
								case ("bold"):
								case ("underline"):
								case ("italic"):
									Boolean boolCurrent = (Boolean) attributesCurrentEntry.getValue();
									Boolean boolLast = (Boolean) attributesLastEntry.getValue();
									if ( Objects.equals(boolCurrent, boolLast) == true )
										attrNameStrFound = true;
									break;
								case ("size"):
									Integer intCurrent = (Integer) attributesCurrentEntry.getValue();
									Integer intLast = (Integer) attributesLastEntry.getValue();
									if ( Objects.equals(intCurrent, intLast) == true )
										attrNameStrFound = true;
									break;
								case ("foreground"):
								case ("background"):
									java.awt.Color colorCurrent = (java.awt.Color) attributesCurrentEntry.getValue();
									java.awt.Color colorLast = (java.awt.Color) attributesLastEntry.getValue();
									if ( colorCurrent.equals(colorLast) == true )
										attrNameStrFound = true;
									break;
							}
						}
						if ( attrNameStrFound == true )
							break;
					}
					if ( attrNameStrFound == false ) {
						isAttributesLastSameAsCurrent = false;
						break;
					}
				}
				if ( isAttributesLastSameAsCurrent == true ) {
					Set<Entry<Object, Object>> attributesLastSet = attributesLast.entrySet();
					Iterator<Entry<Object, Object>> attributesLastSetIt = attributesLastSet.iterator();
					while ( attributesLastSetIt.hasNext() ) {
						Entry<Object, Object> attributesLastEntry = attributesLastSetIt.next();
						Object attributeLast = attributesLastEntry.getKey();
						String attributeLastStr = attributeLast.toString();
						
						Boolean attrNameStrFound = false;
						attributesCurrentSetIt = attributesCurrentSet.iterator();
						while ( attributesCurrentSetIt.hasNext() ) {
							Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
							Object attributeCurrent = attributesCurrentEntry.getKey();
							String attributesCurrentStr = attributeCurrent.toString();
							if ( attributesCurrentStr.equals(attributeLastStr) ) {
								
								switch ( attributeLastStr ) {
									case ("bold"):
									case ("underline"):
									case ("italic"):
										Boolean boolCurrent = (Boolean) attributesCurrentEntry.getValue();
										Boolean boolLast = (Boolean) attributesLastEntry.getValue();
										if ( boolCurrent == boolLast )
											attrNameStrFound = true;
										break;
									case ("size"):
										Integer intCurrent = (Integer) attributesCurrentEntry.getValue();
										Integer intLast = (Integer) attributesLastEntry.getValue();
										if ( Objects.equals(intCurrent, intLast) == true )
											attrNameStrFound = true;
										break;
									case ("foreground"):
									case ("background"):
										java.awt.Color colorCurrent = (java.awt.Color) attributesCurrentEntry.getValue();
										java.awt.Color colorLast = (java.awt.Color) attributesLastEntry.getValue();
										if ( colorCurrent.equals(colorLast) == true )
											attrNameStrFound = true;
										break;
								}
							}
							if ( attrNameStrFound == true )
								break;
						}
						if ( attrNameStrFound == false ) {
							isAttributesLastSameAsCurrent = false;
							break;
						}
					}
				}
			}
			
			//====================
			// end region compare attributes
			//====================
			
			if ( isAttributesLastSameAsCurrent == false || Objects.equals(w, posEnd) == true ) {
				//====================
				// region add attribute
				//====================
				
				SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
				Boolean isAttrNameFound = false;
				Set<Entry<Object, Object>> attributesLastSet = attributesLast.entrySet();
				Iterator<Entry<Object, Object>> attributesLastSetIt = attributesLastSet.iterator();
				while ( attributesLastSetIt.hasNext() ) {
					Entry<Object, Object> attributesLastEntry = attributesLastSetIt.next();
					Object attributeLast = attributesLastEntry.getKey();
					Object attributeLastVal = attributesLastEntry.getValue();
					String attributeLastStr = attributeLast.toString();
					switch ( attributeLastStr ) {
						case ("bold"):
							if ( fontStyleNew == null || fontStyleNew != null && fontStyleNew != FontStyleEnum.Bold )
								simpleAttributeSet.addAttribute( StyleConstants.FontConstants.Bold, attributeLastVal );
							else
								isAttrNameFound = (Boolean) attributeLastVal;
							break;
						case ("italic"):
							if ( fontStyleNew == null || fontStyleNew != null && fontStyleNew != FontStyleEnum.Italic )
								simpleAttributeSet.addAttribute( StyleConstants.CharacterConstants.Italic, attributeLastVal );
							else
								isAttrNameFound = (Boolean) attributeLastVal;
							break;
						case ("underline"):
							if ( fontStyleNew == null || fontStyleNew != null && fontStyleNew != FontStyleEnum.Underline )
								simpleAttributeSet.addAttribute( StyleConstants.CharacterConstants.Underline, attributeLastVal );
							else
								isAttrNameFound = (Boolean) attributeLastVal;
							break;
						case ("size"):
							simpleAttributeSet.addAttribute( StyleConstants.FontConstants.Size, attributeLastVal );
							break;
						case ("background"):
							simpleAttributeSet.addAttribute( StyleConstants.ColorConstants.Background, attributeLastVal );
							break;
						case ("foreground"):
							simpleAttributeSet.addAttribute( StyleConstants.ColorConstants.Foreground, attributeLastVal );
							break;
					}
				}
				
				if ( isAttrNameFound == false )
					isAttrExistOnEveryPosition = false;

				HashMap<SimpleAttributeSet, Integer[]> simpleAttributeSetMap = new HashMap<>();
				simpleAttributeSetMap.put( simpleAttributeSet, new Integer[]{ wLast, w } );
				simplesAttributesSet.put( simplesAttributesSetIndex, simpleAttributeSetMap );
				simplesAttributesSetIndex++;
				
				//====================
				// end region add attribute
				//====================
				
				wLast = w;
				
				attributesLast = new HashMap<Object, Object>();
				Set<Entry<Object, Object>> attributesCurrentSet = attributesCurrent.entrySet();
				Iterator<Entry<Object, Object>> attributesCurrentSetIt = attributesCurrentSet.iterator();
				while ( attributesCurrentSetIt.hasNext() ) {
					Entry<Object, Object> attributesCurrentEntry = attributesCurrentSetIt.next();
					Object attributeCurrent = attributesCurrentEntry.getKey();
					Object attributeCurrentValue = attributesCurrentEntry.getValue();
					attributesLast.put( attributeCurrent, attributeCurrentValue );
				}
			}
			
			w++;
		}
		
		//====================
		// region set character attributes
		//====================
		
		setCharacterAttributes( simplesAttributesSet, isAttrExistOnEveryPosition, null, fontStyleNew, colorNew );
		
		//====================
		// end region set character attributes
		//====================
		
		insertDocumentHistoryPending();
		insertDocumentHistory();
		
		selectText( posStart, posEnd );
	}
	
	public void setCharacterAttributes( HashMap<Integer, HashMap<SimpleAttributeSet, Integer[]>> simplesAttributesSet, Boolean isAttrExistOnEveryPosition, Integer positionPlus, FontStyleEnum fontStyleNew, Color colorNew ) throws Re01JLibException {
		Set<Entry<Integer, HashMap<SimpleAttributeSet, Integer[]>>> simplesAttributesSetEntry = simplesAttributesSet.entrySet();
		Iterator<Entry<Integer, HashMap<SimpleAttributeSet, Integer[]>>> simplesAttributesSetEntryIt = simplesAttributesSetEntry.iterator();
		
		while ( simplesAttributesSetEntryIt.hasNext() ) {
			Entry<Integer, HashMap<SimpleAttributeSet, Integer[]>> simpleAttributeSetSubEntry = simplesAttributesSetEntryIt.next();
			HashMap<SimpleAttributeSet, Integer[]> simpleAttrSetSub = simpleAttributeSetSubEntry.getValue();
			Set<Entry<SimpleAttributeSet, Integer[]>> simplesAttributesSetSubEntry = simpleAttrSetSub.entrySet();
			Iterator<Entry<SimpleAttributeSet, Integer[]>> simplesAttributesSetSubEntryIt = simplesAttributesSetSubEntry.iterator();
			
			while ( simplesAttributesSetSubEntryIt.hasNext() ) {
				Entry<SimpleAttributeSet, Integer[]> simpleAttributeSetEntry = simplesAttributesSetSubEntryIt.next();
				SimpleAttributeSet simpleAttrSet = simpleAttributeSetEntry.getKey();
				Integer[] positions = simpleAttributeSetEntry.getValue();
				Integer posStartToSet = ( positionPlus != null ) ? positions[0] + positionPlus : positions[0];
				Integer posEndToSet = ( positionPlus != null ) ? positions[1] + positionPlus : positions[1];
				
				SimpleAttributeSet simpleAttrSetNew = new SimpleAttributeSet();
				Enumeration attrNames = simpleAttrSet.getAttributeNames();
				while ( attrNames.hasMoreElements() ) {
					Object attrName = attrNames.nextElement();
					simpleAttrSetNew.addAttribute( attrName, simpleAttrSet.getAttribute(attrName) );
				}

				if ( fontStyleNew != null ) {
					switch ( fontStyleNew ) {
						case Bold :
							Boolean isSetBold = isAttrExistOnEveryPosition == false;
							StyleConstants.setBold( simpleAttrSetNew, isSetBold );
							break;
						case Italic :
							Boolean isSetItalic = isAttrExistOnEveryPosition == false;
							StyleConstants.setItalic( simpleAttrSetNew, isSetItalic );
							break;
						case Underline :
							Boolean isSetUnderline = isAttrExistOnEveryPosition == false;
							StyleConstants.setUnderline( simpleAttrSetNew, isSetUnderline );
							break;
						case SizeNormal :
						case Title1 :
						case Title2 :
						case Title3 :
							FontSize fontSizeTitle = new FontSize( new Theme( ThemeTypeEnum.Undefined ), fontStyleNew );
							StyleConstants.setFontSize( simpleAttrSetNew, fontSizeTitle.getSize() );
							break;
					}
				}
				if ( colorNew != null ) {
					switch ( colorNew.getColorAttributeType() ) {
						case Foreground:
							if ( colorNew.getColorType() != ColorTypeEnum.Transparent )
								StyleConstants.setForeground( simpleAttrSetNew, colorNew.getRgbColor() );
							break;
						case Background:
							if ( colorNew.getColorType() != ColorTypeEnum.Transparent )
								StyleConstants.setBackground( simpleAttrSetNew, colorNew.getRgbColor() );
							break;
					}
				}
				
				StyledDocument doc = this.getStyledDocument();
				try {
					doc.setCharacterAttributes( posStartToSet, posEndToSet - posStartToSet, simpleAttrSetNew, true );

				} catch ( Exception e ) {
					throw new Re01JLibException(e);
				}
			}
		}
	}
	
	public void deleteStyle() throws Re01JLibException {
		insertFirstDocumentHistory();
		
		Integer posStart = this.getSelectionStart();
		Integer posEnd = this.getSelectionEnd();
		
		SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
		StyledDocument doc = this.getStyledDocument();
		try {
			doc.setCharacterAttributes( posStart, posEnd - posStart, simpleAttributeSet, true );

		} catch ( Exception e ) {
			throw new Re01JLibException(e);
		}
		
		insertDocumentHistoryPending();
		insertDocumentHistory();
	}
	
	public void addText( String text, Font font ) throws Re01JLibException {
		Integer documentLength = this.getDocument().getLength();
		StyledDocument doc = this.getStyledDocument();
		
		try {
			doc.insertString( 
				documentLength, 
				text, 
				( font != null ) ? font.getSimpleAttributeSet() : new SimpleAttributeSet()
			);
		} catch ( Exception e ) {
			throw new Re01JLibException( e );
		}
	}
	
	public void selectText( Integer posStart, Integer posEnd ) {
		this.setSelectionStart( posStart );
		this.setSelectionEnd( posEnd );
	}
	
	//====================
	// end region text & style
	//====================
	
	//====================
	// region search
	//====================
	
	public void search( String searchKeyWord ) {
		String searchKeyWordLower = searchKeyWord.toLowerCase();
		if ( searchTextLast.equals(searchKeyWordLower) == false ) {
			searchReset();
			searchTextLast = searchKeyWordLower;
		}
		
		// Focus the text area, otherwise the highlighting won't show up
		this.requestFocusInWindow();
		if (searchKeyWordLower != null && searchKeyWordLower.length() > 0) {
			Document document = this.getDocument();
			String documentTextAll = "";
			try {
				documentTextAll = document.getText(0, document.getLength() - 1);
			} catch (Exception ex) { }
			
			if (textLast.equals(documentTextAll) == false) {
				searchReset();
				textLast = documentTextAll;
			}
			
			Integer searchKeyWordLowerLength = searchKeyWordLower.length();
			try {
				Boolean found = false;
				Integer foundQty = 0;
				searchFoundQtyPosition = 0;
				if (scrollPosition + searchKeyWordLowerLength > document.getLength()) {
					scrollPosition = 0;
				}
				Integer searchFoundPositionsLength = searchFoundPositions.size();
				while (scrollPosition + searchKeyWordLowerLength <= document.getLength()) {
					String match = document.getText(scrollPosition, searchKeyWordLowerLength).toLowerCase();
					if (match.equals(searchKeyWordLower) == true) {
						found = true;
						foundQty++;
						if (searchFoundQty != null) {
							Iterator<Integer> searchFoundPositionsIt = searchFoundPositions.iterator();
							Integer inputBookmarkDescriptionSearchFoundPositionFound;
							while (searchFoundPositionsIt.hasNext()) {
								inputBookmarkDescriptionSearchFoundPositionFound = searchFoundPositionsIt.next();
								searchFoundQtyPosition++;
								if ( Objects.equals(inputBookmarkDescriptionSearchFoundPositionFound, scrollPosition) == true )
									break;
							}
							break;
						} else
							searchFoundPositions.add(scrollPosition);
					}
					scrollPosition++;
				}

				Boolean isFoundPositionEqualsZero = Objects.equals(searchFoundQtyPosition, 0);
				if (searchFoundQty == null) {
					searchFoundQty = foundQty;
				} else {
					if (isFoundPositionEqualsZero == true)
						searchFoundQtyPosition = searchFoundPositionsLength;
				}

				Integer inputBookmarkDescriptionSearchFoundPositionLast = 0;
				if (Objects.equals(searchFoundPositionsLength, 0) == false)
					inputBookmarkDescriptionSearchFoundPositionLast = searchFoundPositions.get(searchFoundPositionsLength - 1);
				Boolean movePositionToEnd = false;
				if (Objects.equals(inputBookmarkDescriptionSearchFoundPositionLast, scrollPosition) == true)
					movePositionToEnd = true;

				if (found == true) {
					// Get the rectangle of the where the text would be visible...
					Rectangle viewRect = this.modelToView(scrollPosition);
					// Scroll to make the rectangle visible
					this.scrollRectToVisible(viewRect);
					// Highlight the text
					this.setCaretPosition(scrollPosition + searchKeyWordLowerLength);
					this.moveCaretPosition(scrollPosition);
					// Move the search position beyond the current match
					scrollPosition += searchKeyWordLowerLength;
				}

				if (movePositionToEnd == true)
					scrollPosition = document.getLength();
			} catch (Exception ex) { }

		}
	}
	
	private void searchReset() {
		scrollPosition = 0;
		searchFoundPositions.clear();
		searchFoundQty = null;
	}
	
	//====================
	// end region search
	//====================
	
}
