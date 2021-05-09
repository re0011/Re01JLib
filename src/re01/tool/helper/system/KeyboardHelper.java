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

package re01.tool.helper.system;

import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 *
 * @author renaud
 */
public class KeyboardHelper {
	
	public Boolean isKeyCodeNonCharKey(Integer keyCode) {
		return Objects.equals(keyCode, KeyEvent.VK_ALT) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_ALT_GRAPH) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_CONTEXT_MENU) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_CAPS_LOCK) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_LEFT) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_RIGHT) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_UP) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_DOWN) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_KP_LEFT) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_KP_RIGHT) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_KP_UP) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_KP_DOWN) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_PAGE_UP) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_PAGE_DOWN) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_HOME) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_END) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_ESCAPE) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_INSERT) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_SCROLL_LOCK) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_PAUSE) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_PRINTSCREEN) == true 
		|| Objects.equals(keyCode, KeyEvent.VK_WINDOWS) == true;
	}
	
}
