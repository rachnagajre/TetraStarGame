/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.design.pattern.tetrastar.model;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Rachna Gajre <rgajre@scu.edu>
 */
public abstract class TIterator implements Iterator<Object> {
	
	/**
	 * Tracks the current Position.
	 */
	int currentPosition;
	
	/**
	 * Begin traversal.
	 */
	public void begin() {
		currentPosition = 0;
	}
	
	/**
	 * End traversal.
	 */
	public void end() {
		int size = getSize();
		currentPosition = --size;
	}
	
	/**
	 * To Check whether next element exists or not.
	 */
	public boolean hasNext() {
		int size = getSize();
		if(currentPosition < size) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get the size of Aggregate.
	 */
	abstract int getSize();
	
	/**
	 * Get the next Aggregate Element.
	 */
        @Override
	public abstract Object next();
	
	/**
	 * Remove the next Aggregate Element.
	 */
        @Override
	public abstract void remove();
	
}

/**
 * StarMapComponentIterator provides traversal for StarMapComponent Aggregate.
 * This class acts as Concrete Iterator.
 */
class StarMapComponentIterator extends TIterator {
	
	/**
	 * Aggregate List.
	 */
	List<StarMapComponent> lsmc;
	
	StarMapComponentIterator(List<StarMapComponent> lsmc) {
		this.lsmc = lsmc;
		this.currentPosition = 0;
	}
	
	/**
	 * Get the size of Aggregate.
	 */
        @Override
	public int getSize() {
		return lsmc.size();
	}

	/**
	 * Get the next Aggregate Element.
	 */
        @Override
	public Object next() {
		return lsmc.get(currentPosition++);
	}
	
	/**
	 * Remove the next Aggregate Element.
	 */
        @Override
	public void remove() {
		try {
			lsmc.remove(currentPosition--); 
		}
		catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
}

/**
 * EncryptingHeroIterator provides traversal for EncryptingHero Aggregate.
 * This class acts as Concrete Iterator.
 */
class EncryptingHeroIterator extends TIterator {
	
	/**
	 * Aggregate List.
	 */
	List<EncryptingHero> leh;
	
	EncryptingHeroIterator(List<EncryptingHero> leh) {
		this.leh = leh;
		this.currentPosition = 0;
	}
	
	/**
	 * Get the size of Aggregate.
	 */
        @Override
	public int getSize() {
		return leh.size();
	}

	/**
	 * Get the next Aggregate Element.
	 */
        @Override
	public Object next() {
		return leh.get(currentPosition++);
	}
	
	/**
	 * Remove the next Aggregate Element.
	 */
        @Override
	public void remove() {
		try {
			leh.remove(currentPosition--); 
		}
		catch (UnsupportedOperationException e) {
			e.printStackTrace();
		}
	}
}