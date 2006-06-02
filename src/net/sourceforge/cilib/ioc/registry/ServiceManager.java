/*
 * ServiceManager.java
 * 
 * Created on Jun 2, 2006
 *
 * Copyright (C) 2003, 2004 - CIRG@UP 
 * Computational Intelligence Research Group (CIRG@UP)
 * Department of Computer Science 
 * University of Pretoria
 * South Africa
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package net.sourceforge.cilib.ioc.registry;

import java.util.Hashtable;

public class ServiceManager {
	
	private Hashtable<String, Object> objectSet;
	private static ServiceManager instance;
	

	private ServiceManager() {
		objectSet = new Hashtable<String, Object>();
	}
	
	public static ServiceManager getInstance() {
		if (instance == null)
			instance = new ServiceManager();
		
		return instance;
	}
	
	public Object getObject(String name) {
		return this.objectSet.get(name);
	}
	
	public void addObject(String name, Object object) {
		this.objectSet.put(name, object);
	}

}
