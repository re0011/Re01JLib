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

package re01.program.user;

import java.util.EventObject;

/**
 *
 * @author renaud
 */
public class KeyEnterReleasedEvent extends EventObject {
	
	private boolean isKeyReleased = false;
	
	public KeyEnterReleasedEvent(Object source) {
		super(source);
		isKeyReleased = true;
	}

	public boolean getIsKeyReleased() {
		return isKeyReleased;
	}
	
}
