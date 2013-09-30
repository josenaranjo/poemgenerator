package com.appia.poemgenerator.rule;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class provide an outline implementation of the {@link Rule}
 * interface to minimize the effort to implement it.
 * 
 * @author josel
 *
 */
public abstract class AbstractRule implements Rule {

	private List<AbstractRule> children = new ArrayList<>();
	
	private boolean or;

	public boolean isOr() {
	  return or;
  }

	public void setOr(boolean or) {
	  this.or = or;
  }

	public List<AbstractRule> getChildren() {
		List<AbstractRule> tempList = new ArrayList<>();
		if(or && children.size() > 0) {
			tempList.add(getRandomChildren());
		} else {
			tempList = children;
		}
		
	  return tempList;
  }

	private AbstractRule getRandomChildren() {
		return children.get((int)(Math.random() * ((children.size()))));
  }

	public void setChildren(List<AbstractRule> children) {
	  this.children = children;
  }
	
}
