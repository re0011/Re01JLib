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

package re01.language;

/**
 *
 * @author renaud
 */
public abstract class Global {
	
	//=================
	// Files
	
	protected String FOLDER;
	protected String FOLDER_NOT_CREATED;
	protected String FOLDER_COPIED;
	protected String FOLDERS_COPIED;
	protected String FOLDER_MOVED;
	protected String FOLDERS_MOVED;
	protected String FOLDER_RENAMED;
	protected String FOLDERS_RENAMED;
	protected String FILE;
	protected String FILE_USER_PARAMETERS;
	protected String FILE_SAVED;
	protected String FILE_NOT_SAVED;
	protected String FILE_NOT_CREATED;
	protected String FILES_SAVED;
	protected String FILES_NOT_SAVED;
	protected String FILE_DELETED;
	protected String FILE_NOT_DELETED;
	protected String FILES_DELETED;
	protected String FILES_NOT_DELETED;
	protected String ITEM_COPIED;
	protected String ITEMS_COPIED;
	protected String ITEM_MOVED;
	protected String ITEMS_MOVED;
	protected String ITEM_NOT_RENAMED;
	protected String CHMOD;
	protected String CHMOD_DIRECTORY_INSUFFICIENT;
	
	//==================
	// Global
	
	protected String ABOUT;
	protected String AUTHOR;
	protected String CHOOSE;
	protected String CANCEL;
	protected String CLOSE;
	protected String CONFIRMATION;
	protected String COPY;
	protected String COPY_WITH_STYLE;
	protected String COPYRIGHT;
	protected String CUT;
	protected String DELETE;
	protected String DELETE_STYLE;
	protected String DIRECTORY;
	protected String DYNAMIC;
	protected String EDIT;
	protected String ERROR;
	protected String EXIT;
	protected String HELP;
	protected String ICON;
	protected String KEYWORD;
	protected String KEYBOARD_SHORTCUT;
	protected String LEAVE;
	protected String LIBRARY;
	protected String MESSAGES;
	protected String MINIMIZE;
	protected String MOVE;
	protected String NAME;
	protected String NO;
	protected String OF;
	protected String OK;
	protected String OPEN;
	protected String ORDER;
	protected String PARAMETERS;
	protected String PASTE;
	protected String PROGRAM;
	protected String PROGRESS;
	protected String RENAME;
	protected String REORDER;
	protected String RESULT;
	protected String SAVE;
	protected String SEARCH;
	protected String SELECT;
	protected String SELECT_LANGUAGE;
	protected String START;
	protected String VERSION;
	protected String WEB_SITE;
	protected String WELCOME;
	protected String WORK;
	protected String YES;
	
	//=====================
	// Text editor
	
	protected String BOLD;
	protected String ITALIC;
	protected String UNDERLINE;
	protected String SIZE_NORMAL;
	protected String TITLE_0;
	protected String TITLE_1;
	protected String TITLE_2;
	protected String TITLE_3;
	protected String FONT_COLOR_BACKGROUND;
	protected String FONT_COLOR_BACKGROUND_SHORT;
	protected String FONT_COLOR_FOREGROUND;
	protected String FONT_COLOR_FOREGROUND_SHORT;
	protected String CHARS_BASE_SIZE;
	protected String ICONS_SIZE;
	protected String DOCUMENT_HISTORY_LENGTH;
	protected String TEXT_DOCUMENT_HISTORY_LENGTH_INFO;
	
	//====================
	// Hardware
	
	protected String CPU;
	protected String CPU_CORE;
	protected String CPU_CORES;
	
	//=====================
	// KeyEvents
	
	protected int ABOUT_KEYEVENT;
	protected int CLOSE_KEYEVENT;
	protected int COPY_KEYEVENT;
	protected int EXIT_KEYEVENT;
	protected int HELP_KEYEVENT;
	protected int MINIMIZE_KEYEVENT;
	protected int PARAMETERS_KEYEVENT;
	protected int SAVE_KEYEVENT;
	protected int SELECT_LANGUAGE_KEYEVENT;
	
	//======================
	// KeyStrokes
	
	protected String COPY_KEYSTROKE;
	protected String COPY_WITH_STYLE_KEYSTROKE;
	protected String PASTE_KEYSTROKE;
	
	//=====================
	// Languages
	
	protected String ENGLISH;
	protected String FRENCH;
	protected String LANGUAGE;
	
	//======================
	// Librairies
	
	protected String RE01JLIB;
	protected String SIGAR;
	protected String SIGAR_ADDRESS;
	protected String SIGAR_GITHUB_ADDRESS;
	
	//====================
	// Licenses
	
	protected String LICENSE;
	protected String LICENSE_APACHE_2;
	protected String LICENSE_GNU;
	protected String LICENSE_GNU_SHORT;
	
	//=============================
	// Operating systems
	
	protected String GNU_LINUX;
	
	//=======================
	// Global text
	
	protected String TEXT_INFO_FILE_BUTTON_SELECT;
	
	protected String TEXT_KEYWORD_IS_EMPTY;
	protected String TEXT_KEYWORD_IS_TO_SMALL;
	protected String TEXT_KEYWORD_IS_TO_HIGH;
	
	protected String TEXT_NAME_IS_EMPTY;
	protected String TEXT_NAME_IS_TO_SMALL;
	protected String TEXT_NAME_IS_TO_HIGH;
	
	protected String TEXT_NEED_PROGRAM_RESTART_TO_APPLY_CHANGES;
	protected String TEXT_YOU_NEED_TO_RESTART_PROGRAM_TO_APPLY_CHANGES;
	
	protected String PROGRAM_START;
	
	//===================
	// Windows
	
	protected String CLOSE_WINDOW;
	protected String DO_NOT_DISPLAY_WINDOW_AGAIN;
	
	//===================
	// Maths
	
	protected String MATHS_SYMBOL_PLUS;
	protected String MATHS_SYMBOL_NEGATIVE;
	
	//===================
	// Exceptions
	
	protected String TEXT_CALLBACK_ARGS_AND_METHOD_NAME_NOT_FOUND;
	protected String TEXT_ALERT_MESSAGE_IS_NULL;
	protected String TEXT_CONFIRM_MESSAGE_IS_NULL;
	
	//=============================
	//=============================
	//=============================
	//=============================
	//=============================
	// Getters
	//=============================
	//=============================
	//=============================
	//=============================
	//=============================
	
	//=================
	// Files
	
	public String get_FOLDER() {
		return FOLDER;
	}

	public String get_FOLDER_NOT_CREATED() {
		return FOLDER_NOT_CREATED;
	}

	public String get_FOLDER_COPIED() {
		return FOLDER_COPIED;
	}

	public String get_FOLDERS_COPIED() {
		return FOLDERS_COPIED;
	}

	public String get_FOLDER_MOVED() {
		return FOLDER_MOVED;
	}

	public String get_FOLDERS_MOVED() {
		return FOLDERS_MOVED;
	}

	public String get_FOLDER_RENAMED() {
		return FOLDER_RENAMED;
	}
	
	public String get_FOLDERS_RENAMED() {
		return FOLDERS_RENAMED;
	}
	
	public String get_FILE() {
		return FILE;
	}

	public String get_FILE_USER_PARAMETERS() {
		return FILE_USER_PARAMETERS;
	}

	public String get_FILE_SAVED() {
		return FILE_SAVED;
	}
	
	public String get_FILE_NOT_SAVED() {
		return FILE_NOT_SAVED;
	}

	public String get_FILE_NOT_CREATED() {
		return FILE_NOT_CREATED;
	}

	public String get_FILES_SAVED() {
		return FILES_SAVED;
	}
	
	public String get_FILES_NOT_SAVED() {
		return FILES_NOT_SAVED;
	}

	public String get_FILE_DELETED() {
		return FILE_DELETED;
	}

	public String get_FILE_NOT_DELETED() {
		return FILE_NOT_DELETED;
	}

	public String get_FILES_DELETED() {
		return FILES_DELETED;
	}

	public String get_FILES_NOT_DELETED() {
		return FILES_NOT_DELETED;
	}

	public String get_ITEM_COPIED() {
		return ITEM_COPIED;
	}

	public String get_ITEMS_COPIED() {
		return ITEMS_COPIED;
	}

	public String get_ITEM_MOVED() {
		return ITEM_MOVED;
	}
	
	public String get_ITEMS_MOVED() {
		return ITEMS_MOVED;
	}

	public String get_ITEM_NOT_RENAMED() {
		return ITEM_NOT_RENAMED;
	}

	public String get_CHMOD() {
		return CHMOD;
	}

	public String get_CHMOD_DIRECTORY_INSUFFICIENT() {
		return CHMOD_DIRECTORY_INSUFFICIENT;
	}
	
	//==================
	// Global

	public String get_ABOUT() {
		return ABOUT;
	}

	public String get_AUTHOR() {
		return AUTHOR;
	}

	public String get_CHOOSE() {
		return CHOOSE;
	}

	public String get_CANCEL() {
		return CANCEL;
	}

	public String get_CLOSE() {
		return CLOSE;
	}

	public String get_CONFIRMATION() {
		return CONFIRMATION;
	}
	
	public String get_COPY() {
		return COPY;
	}

	public String get_COPY_WITH_STYLE() {
		return COPY_WITH_STYLE;
	}

	public String get_COPYRIGHT() {
		return COPYRIGHT;
	}

	public String get_CUT() {
		return CUT;
	}

	public String get_DELETE() {
		return DELETE;
	}

	public String get_DELETE_STYLE() {
		return DELETE_STYLE;
	}

	public String get_DIRECTORY() {
		return DIRECTORY;
	}

	public String get_DYNAMIC() {
		return DYNAMIC;
	}

	public String get_EDIT() {
		return EDIT;
	}

	public String get_ERROR() {
		return ERROR;
	}

	public String get_EXIT() {
		return EXIT;
	}

	public String get_HELP() {
		return HELP;
	}

	public String get_ICON() {
		return ICON;
	}

	public String get_KEYWORD() {
		return KEYWORD;
	}

	public String get_KEYBOARD_SHORTCUT() {
		return KEYBOARD_SHORTCUT;
	}

	public String get_LEAVE() {
		return LEAVE;
	}

	public String get_LIBRARY() {
		return LIBRARY;
	}

	public String get_MESSAGES() {
		return MESSAGES;
	}

	public String get_MINIMIZE() {
		return MINIMIZE;
	}

	public String get_MOVE() {
		return MOVE;
	}

	public String get_NAME() {
		return NAME;
	}

	public String get_NO() {
		return NO;
	}

	public String get_OF() {
		return OF;
	}

	public String get_OK() {
		return OK;
	}

	public String get_OPEN() {
		return OPEN;
	}

	public String get_ORDER() {
		return ORDER;
	}

	public String get_PARAMETERS() {
		return PARAMETERS;
	}

	public String get_PASTE() {
		return PASTE;
	}

	public String get_PROGRAM() {
		return PROGRAM;
	}

	public String get_PROGRESS() {
		return PROGRESS;
	}

	public String get_RENAME() {
		return RENAME;
	}

	public String get_REORDER() {
		return REORDER;
	}

	public String get_RESULT() {
		return RESULT;
	}

	public String get_SAVE() {
		return SAVE;
	}

	public String get_SEARCH() {
		return SEARCH;
	}

	public String get_SELECT() {
		return SELECT;
	}
	
	public String get_SELECT_LANGUAGE() {
		return SELECT_LANGUAGE;
	}

	public String get_START() {
		return START;
	}

	public String get_VERSION() {
		return VERSION;
	}

	public String get_WEB_SITE() {
		return WEB_SITE;
	}
	
	public String get_WELCOME() {
		return WELCOME;
	}
	
	public String get_WORK() {
		return WORK;
	}

	public String get_YES() {
		return YES;
	}
	
	//=====================
	// Text editor
	
	public String get_BOLD() {
		return BOLD;
	}

	public String get_ITALIC() {
		return ITALIC;
	}

	public String get_UNDERLINE() {
		return UNDERLINE;
	}
	
	public String get_SIZE_NORMAL() {
		return SIZE_NORMAL;
	}

	public String get_TITLE_0() {
		return TITLE_0;
	}

	public String get_TITLE_1() {
		return TITLE_1;
	}

	public String get_TITLE_2() {
		return TITLE_2;
	}

	public String get_TITLE_3() {
		return TITLE_3;
	}

	public String get_FONT_COLOR_BACKGROUND() {
		return FONT_COLOR_BACKGROUND;
	}
	
	public String get_FONT_COLOR_BACKGROUND_SHORT() {
		return FONT_COLOR_BACKGROUND_SHORT;
	}

	public String get_FONT_COLOR_FOREGROUND() {
		return FONT_COLOR_FOREGROUND;
	}
	
	public String get_FONT_COLOR_FOREGROUND_SHORT() {
		return FONT_COLOR_FOREGROUND_SHORT;
	}

	public String get_CHARS_BASE_SIZE() {
		return CHARS_BASE_SIZE;
	}

	public String get_ICONS_SIZE() {
		return ICONS_SIZE;
	}

	public String get_DOCUMENT_HISTORY_LENGTH() {
		return DOCUMENT_HISTORY_LENGTH;
	}

	public String get_TEXT_DOCUMENT_HISTORY_LENGTH_INFO() {
		return TEXT_DOCUMENT_HISTORY_LENGTH_INFO;
	}
	
	//====================
	// Hardware

	public String get_CPU() {
		return CPU;
	}

	public String get_CPU_CORE() {
		return CPU_CORE;
	}

	public String get_CPU_CORES() {
		return CPU_CORES;
	}
	
	//=====================
	// KeyEvents

	public int get_ABOUT_KEYEVENT() {
		return ABOUT_KEYEVENT;
	}

	public int get_CLOSE_KEYEVENT() {
		return CLOSE_KEYEVENT;
	}
	
	public int get_COPY_KEYEVENT() {
		return COPY_KEYEVENT;
	}

	public int get_EXIT_KEYEVENT() {
		return EXIT_KEYEVENT;
	}

	public int get_HELP_KEYEVENT() {
		return HELP_KEYEVENT;
	}

	public int get_MINIMIZE_KEYEVENT() {
		return MINIMIZE_KEYEVENT;
	}

	public int get_PARAMETERS_KEYEVENT() {
		return PARAMETERS_KEYEVENT;
	}

	public int get_SAVE_KEYEVENT() {
		return SAVE_KEYEVENT;
	}

	public int get_SELECT_LANGUAGE_KEYEVENT() {
		return SELECT_LANGUAGE_KEYEVENT;
	}
	
	//======================
	// KeyStrokes

	public String get_COPY_KEYSTROKE() {
		return COPY_KEYSTROKE;
	}

	public String get_COPY_WITH_STYLE_KEYSTROKE() {
		return COPY_WITH_STYLE_KEYSTROKE;
	}

	public String get_PASTE_KEYSTROKE() {
		return PASTE_KEYSTROKE;
	}
	
	//=====================
	// Languages

	public String get_ENGLISH() {
		return ENGLISH;
	}
	
	public String get_FRENCH() {
		return FRENCH;
	}

	public String get_LANGUAGE() {
		return LANGUAGE;
	}
	
	//======================
	// Librairies

	public String get_RE01JLIB() {
		return RE01JLIB;
	}
	
	public String get_SIGAR() {
		return SIGAR;
	}

	public String get_SIGAR_ADDRESS() {
		return SIGAR_ADDRESS;
	}

	public String get_SIGAR_GITHUB_ADDRESS() {
		return SIGAR_GITHUB_ADDRESS;
	}
	
	//====================
	// Licenses

	public String get_LICENSE() {
		return LICENSE;
	}
	
	public String get_LICENSE_APACHE_2() {
		return LICENSE_APACHE_2;
	}
	
	public String get_LICENSE_GNU() {
		return LICENSE_GNU;
	}

	public String get_LICENSE_GNU_SHORT() {
		return LICENSE_GNU_SHORT;
	}
	
	//=============================
	// Operating systems
	
	public String get_GNU_LINUX() {
		return GNU_LINUX;
	}
	
	//=======================
	// Global text

	public String get_TEXT_INFO_FILE_BUTTON_SELECT() {
		return TEXT_INFO_FILE_BUTTON_SELECT;
	}

	public String get_TEXT_KEYWORD_IS_EMPTY() {
		return TEXT_KEYWORD_IS_EMPTY;
	}

	public String get_TEXT_KEYWORD_IS_TO_SMALL() {
		return TEXT_KEYWORD_IS_TO_SMALL;
	}

	public String get_TEXT_KEYWORD_IS_TO_HIGH() {
		return TEXT_KEYWORD_IS_TO_HIGH;
	}

	public String get_TEXT_NAME_IS_EMPTY() {
		return TEXT_NAME_IS_EMPTY;
	}

	public String get_TEXT_NAME_IS_TO_SMALL() {
		return TEXT_NAME_IS_TO_SMALL;
	}

	public String get_TEXT_NAME_IS_TO_HIGH() {
		return TEXT_NAME_IS_TO_HIGH;
	}

	public String get_TEXT_NEED_PROGRAM_RESTART_TO_APPLY_CHANGES() {
		return TEXT_NEED_PROGRAM_RESTART_TO_APPLY_CHANGES;
	}

	public String get_TEXT_YOU_NEED_TO_RESTART_PROGRAM_TO_APPLY_CHANGES() {
		return TEXT_YOU_NEED_TO_RESTART_PROGRAM_TO_APPLY_CHANGES;
	}

	public String get_PROGRAM_START() {
		return PROGRAM_START;
	}
	
	//===================
	// Windows

	public String get_CLOSE_WINDOW() {
		return CLOSE_WINDOW;
	}

	public String get_DO_NOT_DISPLAY_WINDOW_AGAIN() {
		return DO_NOT_DISPLAY_WINDOW_AGAIN;
	}
	
	//===================
	// Maths

	public String get_MATHS_SYMBOL_PLUS() {
		return MATHS_SYMBOL_PLUS;
	}

	public String get_MATHS_SYMBOL_NEGATIVE() {
		return MATHS_SYMBOL_NEGATIVE;
	}
	
	//===================
	// Exceptions

	public String get_TEXT_CALLBACK_ARGS_AND_METHOD_NAME_NOT_FOUND() {
		return TEXT_CALLBACK_ARGS_AND_METHOD_NAME_NOT_FOUND;
	}

	public String get_TEXT_ALERT_MESSAGE_IS_NULL() {
		return TEXT_ALERT_MESSAGE_IS_NULL;
	}

	public String get_TEXT_CONFIRM_MESSAGE_IS_NULL() {
		return TEXT_CONFIRM_MESSAGE_IS_NULL;
	}
	
}
