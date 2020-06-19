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

package re01.object;

/**
 *
 * @author renaud
 */
public class Tuple {
	
	private Object one = null;
	private Object two = null;
	private Object three = null;
	
	public Tuple( Object one, Object two, Object three ) {
		this.one = one;
		this.two = two;
		this.three = three;
	}
	
	public Tuple() {
		
	}

	public Object getOne() {
		return one;
	}

	public void setOne(Object one) {
		this.one = one;
	}

	public Object getTwo() {
		return two;
	}

	public void setTwo(Object two) {
		this.two = two;
	}

	public Object getThree() {
		return three;
	}

	public void setThree(Object three) {
		this.three = three;
	}
	
}
