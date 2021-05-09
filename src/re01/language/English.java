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

import java.awt.event.KeyEvent;

/**
 *
 * @author renaud
 */
public class English extends Global {
	
	//========================
	//========================
	//========================
	//========================
	//========================
	// Constructors
	//========================
	//========================
	//========================
	//========================
	//========================
	
	public English() {
		//=================
		// Files

		FOLDER = "Folder";
		FOLDER_NOT_CREATED = "Not created folder";
		FOLDER_COPIED = "Copied folder";
		FOLDERS_COPIED = "Copied folders";
		FOLDER_MOVED = "Moved folder";
		FOLDERS_MOVED = "Moved folders";
		FOLDER_RENAMED = "Renamed folder";
		FOLDERS_RENAMED = "Renamed folders";
		FILE = "File";
		FILE_USER_PARAMETERS = "User parameters file";
		FILE_SAVED = "Saved file";
		FILE_NOT_SAVED = "Not saved file";
		FILE_NOT_CREATED = "Not created file";
		FILES_SAVED = "Saved files";
		FILES_NOT_SAVED = "Not saved files";
		FILE_DELETED = "Deleted file";
		FILE_NOT_DELETED = "Not deleted file";
		FILES_DELETED = "Deleted files";
		FILES_NOT_DELETED = "Not deleted files";
		ITEM_COPIED = "Copied item";
		ITEMS_COPIED = "Copied items";
		ITEM_MOVED = "Moved item";
		ITEMS_MOVED = "Moved items";
		ITEM_NOT_RENAMED = "Not renamed item";
		CHMOD = "Access rights";
		CHMOD_DIRECTORY_INSUFFICIENT = "Insufficient access rights to directory.";

		//==================
		// Global

		ABOUT = "About";
		AUTHOR = "Author";
		CHOOSE = "Choose";
		CANCEL = "Cancel";
		CLOSE = "Close";
		CONFIRMATION = "Confirm";
		COPY = "Copy";
		COPY_WITH_STYLE = "Copy with style";
		COPYRIGHT = "Copyright (C)";
		CUT = "Cut";
		DELETE = "Delete";
		DELETE_STYLE = "Delete style";
		DIRECTORY = "Directory";
		DYNAMIC = "Dynamic";
		EDIT = "Edit";
		ERROR = "Error";
		EXIT = "Exit";
		HELP = "Help";
		ICON = "Icon";
		KEYWORD = "Keyword";
		KEYBOARD_SHORTCUT = "Keyboard shortcut";
		LEAVE = "Leave";
		LIBRARY = "Library";
		MESSAGES = "Messages";
		MINIMIZE = "Minimize";
		MOVE = "Move";
		NAME = "Name";
		NO = "No";
		OF = "Of";
		OK = "OK";
		OPEN = "Open";
		ORDER = "Order";
		PARAMETERS = "Parameters";
		PASTE = "Paste";
		PROGRAM = "Program";
		PROGRESS = "Progress";
		RENAME = "Rename";
		REORDER = "Reorder";
		RESULT = "Result";
		SAVE = "Save";
		SEARCH = "Search";
		SELECT = "Select";
		SELECT_LANGUAGE = "Select language";
		START = "Start";
		VERSION = "Version";
		WEB_SITE = "Web site";
		WELCOME = "Welcome";
		WORK = "Work";
		YES = "Yes";
		
		//=====================
		// Text editor
		
		BOLD = "Bold";
		ITALIC = "Italic";
		UNDERLINE = "Underline";
		SIZE_NORMAL = "Normal size";
		TITLE_0 = "Title 0";
		TITLE_1 = "Title 1";
		TITLE_2 = "Title 2";
		TITLE_3 = "Title 3";
		FONT_COLOR_BACKGROUND = "Background color";
		FONT_COLOR_BACKGROUND_SHORT = "BC";
		FONT_COLOR_FOREGROUND = "Text color";
		FONT_COLOR_FOREGROUND_SHORT = "TC";
		CHARS_BASE_SIZE = "Base fonts size";
		ICONS_SIZE = "Icons size";
		DOCUMENT_HISTORY_LENGTH = "Documents history size";
		TEXT_DOCUMENT_HISTORY_LENGTH_INFO = "Numbers of possibles back to anterior document state (CTRL + Z). More it is high, more the program consume the random access memory.";
		
		//====================
		// Hardware

		CPU = "Processor";
		CPU_CORE = "Core";
		CPU_CORES = "Cores";

		//=====================
		// KeyEvents

		ABOUT_KEYEVENT = KeyEvent.VK_A;
		CLOSE_KEYEVENT = KeyEvent.VK_C;
		COPY_KEYEVENT = KeyEvent.VK_C;
		EXIT_KEYEVENT = KeyEvent.VK_E;
		HELP_KEYEVENT = KeyEvent.VK_H;
		MINIMIZE_KEYEVENT = KeyEvent.VK_M;
		PARAMETERS_KEYEVENT = KeyEvent.VK_P;
		SAVE_KEYEVENT = KeyEvent.VK_S;
		SELECT_LANGUAGE_KEYEVENT = KeyEvent.VK_L;

		//======================
		// KeyStrokes

		COPY_KEYSTROKE = "control C";
		COPY_WITH_STYLE_KEYSTROKE = "control S";
		PASTE_KEYSTROKE = "control V";
		
		//=====================
		// Languages
		
		ENGLISH = "English";
		FRENCH = "French (Français)";
		LANGUAGE = "Langue";

		//======================
		// Librairies

		RE01JLIB = "Re01JLib";
		SIGAR = "Sigar";
		SIGAR_ADDRESS = "http://sigar.hyperic.com/";
		SIGAR_GITHUB_ADDRESS = "http://github.com/hyperic/sigar/";

		//====================
		// Licenses

		LICENSE = "License";
		LICENSE_APACHE_2 = "Apache 2";
		LICENSE_GNU = "GNU GENERAL PUBLIC LICENSE";
		LICENSE_GNU_SHORT = "GNU";

		//=============================
		// Operating systems

		GNU_LINUX = "GNU/Linux";

		//=======================
		// Global text
		
		TEXT_INFO_FILE_BUTTON_SELECT = "Clic to choose...";
		
		TEXT_KEYWORD_IS_EMPTY = "Keyword is empty.";
		TEXT_KEYWORD_IS_TO_SMALL = "Keyword is too short.";
		TEXT_KEYWORD_IS_TO_HIGH = "Keyword is too long.";
		
		TEXT_NAME_IS_EMPTY = "Name is empty.";
		TEXT_NAME_IS_TO_SMALL = "Name is too short.";
		TEXT_NAME_IS_TO_HIGH = "Name is too long.";
		
		TEXT_NEED_PROGRAM_RESTART_TO_APPLY_CHANGES = "The program need to restart to apply changes.";
		TEXT_YOU_NEED_TO_RESTART_PROGRAM_TO_APPLY_CHANGES = "You need to restart the program to apply changes.";
		
		PROGRAM_START = "Program starts";
		
		//===================
		// Windows
		
		CLOSE_WINDOW = "Close window";
		DO_NOT_DISPLAY_WINDOW_AGAIN = "Do not display this window again";
		
		//===================
		// Maths
		
		MATHS_SYMBOL_PLUS = "+";
		MATHS_SYMBOL_NEGATIVE = "-";
		
		//===================
		// Exceptions
	
		TEXT_CALLBACK_ARGS_AND_METHOD_NAME_NOT_FOUND = "Callback args and method name not found in Method_helper.execute_callbacks( Object callbacks, Object[] args ).";
		TEXT_ALERT_MESSAGE_IS_NULL = "Alert message must be specified.";
		TEXT_CONFIRM_MESSAGE_IS_NULL = "Confirm message must be specified.";
	}
	
}
