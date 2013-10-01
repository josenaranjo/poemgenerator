package com.appia.poemgenerator.rule;

/**
 * This class represent a rule which contains 
 * a reference to other rules
 * @author josel
 *
 */
public class Reference extends AbstractRule {
	
	private final String name;
	
	public Reference(String name) {
	  this.name = name;
  }

	public String getName() {
	  return name;
  }
	
	@Override
	public String toString() {
		return name;
	}

}
