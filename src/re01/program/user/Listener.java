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

package re01.program.user;

import javax.swing.event.EventListenerList;

/**
 *
 * @author renaud
 */
public class Listener {
	
	private static boolean isEnterPressed = false;

	public static boolean getIsEnterPressed() {
		return isEnterPressed;
	}

	public static void setIsEnterPressed( boolean isEnterPressed ) {
		Listener.isEnterPressed = isEnterPressed;
	}
	
	//====================
	// region listeners
	//====================
	
	protected static EventListenerList listenerList = new EventListenerList();

	public static void addKeyEnterReleasedEventListener(KeyEnterReleasedEventListener listener) {
		listenerList.add(KeyEnterReleasedEventListener.class, listener);
	}
	
	public static void removeKeyEnterReleasedEventListener(KeyEnterReleasedEventListener listener) {
		listenerList.remove(KeyEnterReleasedEventListener.class, listener);
	}
	
	public static void fireKeyEnterReleasedEvent(KeyEnterReleasedEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for ( int i = 0; i < listeners.length; i = i+2 ) {
			if ( listeners[i] == KeyEnterReleasedEventListener.class ) {
				((KeyEnterReleasedEventListener) listeners[i+1]).eventOccurred(evt);
			}
		}
	}
	
	//====================
	// end region listeners
	//====================
	
}
