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
public class French extends Global {
	
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

	public French() {
		//=================
		// Files

		FOLDER = "Dossier";
		FOLDER_NOT_CREATED = "Dossier non créé";
		FOLDER_COPIED = "Dossier copié";
		FOLDERS_COPIED = "Dossiers copiés";
		FOLDER_MOVED = "Dossier déplacé";
		FOLDERS_MOVED = "Dossiers déplacés";
		FOLDER_RENAMED = "Dossier renommé";
		FOLDERS_RENAMED = "Dossiers renommés";
		FILE = "Fichier";
		FILE_USER_PARAMETERS = "Fichier des paramètres utilisateur";
		FILE_SAVED = "Fichier enregistré";
		FILE_NOT_SAVED = "Fichier non enregistré";
		FILE_NOT_CREATED = "Fichier non créé";
		FILES_SAVED = "Fichiers enregistrés";
		FILES_NOT_SAVED = "Fichiers non enregistrés";
		FILE_DELETED = "Fichier supprimé";
		FILE_NOT_DELETED = "Fichier non supprimé";
		FILES_DELETED = "Fichiers supprimés";
		FILES_NOT_DELETED = "Fichiers non supprimés";
		ITEM_COPIED = "Élément copié";
		ITEMS_COPIED = "Éléments copiés";
		ITEM_MOVED = "Élément déplacé";
		ITEMS_MOVED = "Éléments déplacés";
		ITEM_NOT_RENAMED = "Élément non renommé";
		CHMOD = "Droits d'accès";
		CHMOD_DIRECTORY_INSUFFICIENT = "Droits d'accès au répertoire insuffisants.";

		//==================
		// Global

		ABOUT = "À propos";
		AUTHOR = "Auteur";
		CHOOSE = "Choisir";
		CANCEL = "Annuler";
		CLOSE = "Fermer";
		CONFIRMATION = "Confirmation";
		COPY = "Copier";
		COPY_WITH_STYLE = "Copier avec le style";
		COPYRIGHT = "Copyright (C)";
		CUT = "Couper";
		DELETE = "Supprimer";
		DELETE_STYLE = "Supprimer le style";
		DIRECTORY = "Répertoire";
		DYNAMIC = "Dynamique";
		EDIT = "Éditer";
		ERROR = "Erreur";
		EXIT = "Quitter";
		HELP = "Aide";
		ICON = "Icône";
		KEYWORD = "Mot-clé";
		KEYBOARD_SHORTCUT = "Raccourci clavier";
		LEAVE = "Quitter";
		LIBRARY = "Bibliothèque";
		MESSAGES = "Messages";
		MINIMIZE = "Réduire";
		MOVE = "Déplacer";
		NAME = "Nom";
		NO = "Non";
		OF = "De";
		OK = "Valider";
		OPEN = "Ouvrir";
		ORDER = "Trier";
		PARAMETERS = "Paramètres";
		PASTE = "Coller";
		PROGRAM = "Programme";
		PROGRESS = "Progression";
		RENAME = "Renommer";
		REORDER = "Retrier";
		RESULT = "Résultat";
		SAVE = "Sauvegarder";
		SEARCH = "Rechercher";
		SELECT = "Sélectionner";
		SELECT_LANGUAGE = "Sélectionner langue";
		START = "Démarrer";
		VERSION = "Version";
		WEB_SITE = "Site web";
		WELCOME = "Bienvenue";
		WORK = "Travail";
		YES = "Oui";
		
		//=====================
		// Text editor
		
		BOLD = "Gras";
		ITALIC = "Italique";
		UNDERLINE = "Souligné";
		SIZE_NORMAL = "Taille normale";
		TITLE_0 = "Titre 0";
		TITLE_1 = "Titre 1";
		TITLE_2 = "Titre 2";
		TITLE_3 = "Titre 3";
		FONT_COLOR_BACKGROUND = "Couleur de fond";
		FONT_COLOR_BACKGROUND_SHORT = "CF";
		FONT_COLOR_FOREGROUND = "Couleur du texte";
		FONT_COLOR_FOREGROUND_SHORT = "CT";
		CHARS_BASE_SIZE = "Taille de base des polices de caractères";
		ICONS_SIZE = "Taille des icônes";
		DOCUMENT_HISTORY_LENGTH = "Tailles des historiques des documents";
		TEXT_DOCUMENT_HISTORY_LENGTH_INFO = "Nombre de retours à un état antérieur possibles dans un document (CTRL + Z). Plus ce nombre est élevé, plus le programme consomme de la mémoire vive.";
		
		//====================
		// Hardware

		CPU = "Processeur";
		CPU_CORE = "cœur";
		CPU_CORES = "cœurs";

		//=====================
		// KeyEvents

		ABOUT_KEYEVENT = KeyEvent.VK_P;
		CLOSE_KEYEVENT = KeyEvent.VK_F;
		COPY_KEYEVENT = KeyEvent.VK_C;
		EXIT_KEYEVENT = KeyEvent.VK_Q;
		HELP_KEYEVENT = KeyEvent.VK_A;
		MINIMIZE_KEYEVENT = KeyEvent.VK_R;
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
		
		ENGLISH = "Anglais (English)";
		FRENCH = "Français";
		LANGUAGE = "Langue";

		//======================
		// Librairies

		RE01JLIB = "Re01JLib";
		SIGAR = "Sigar";
		SIGAR_ADDRESS = "http://sigar.hyperic.com/";
		SIGAR_GITHUB_ADDRESS = "http://github.com/hyperic/sigar/";

		//====================
		// Licenses

		LICENSE = "Licence";
		LICENSE_APACHE_2 = "Apache 2";
		LICENSE_GNU = "GNU GENERAL PUBLIC LICENSE";
		LICENSE_GNU_SHORT = "GNU";

		//=============================
		// Operating systems

		GNU_LINUX = "GNU/Linux";

		//=======================
		// Global text
		
		TEXT_INFO_FILE_BUTTON_SELECT = "Cliquez sur choisir...";
		
		TEXT_KEYWORD_IS_EMPTY = "Le mot-clé ne contient pas de caractère.";
		TEXT_KEYWORD_IS_TO_SMALL = "Le mot-clé est trop court.";
		TEXT_KEYWORD_IS_TO_HIGH = "Le mot-clé est trop grand.";
		
		TEXT_NAME_IS_EMPTY = "Le nom ne contient pas de caractère.";
		TEXT_NAME_IS_TO_SMALL = "Le nom est trop court.";
		TEXT_NAME_IS_TO_HIGH = "Le nom est trop grand.";
		
		TEXT_NEED_PROGRAM_RESTART_TO_APPLY_CHANGES = "Le programme doit redémarrer pour appliquer les changements.";
		TEXT_YOU_NEED_TO_RESTART_PROGRAM_TO_APPLY_CHANGES = "Vous devez redémarrer le programme pour appliquer les changements.";
		
		PROGRAM_START = "Démarrage du programme";
		
		//===================
		// Windows
		
		CLOSE_WINDOW = "Fermer fenêtre";
		DO_NOT_DISPLAY_WINDOW_AGAIN = "Ne plus afficher cette fenêtre";
		
		//===================
		// Maths
		
		MATHS_SYMBOL_PLUS = "+";
		MATHS_SYMBOL_NEGATIVE = "-";
		
		//===================
		// Exceptions
	
		TEXT_CALLBACK_ARGS_AND_METHOD_NAME_NOT_FOUND = "Aucun Callback args ni aucun nom de méthode trouvé dans Method_helper.execute_callbacks( Object callbacks, Object[] args ).";
		TEXT_ALERT_MESSAGE_IS_NULL = "Un message d'alerte doit être spécifié.";
		TEXT_CONFIRM_MESSAGE_IS_NULL = "Un message de confirmation doit être spécifié.";
	}
}
