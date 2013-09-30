package com.appia.poemgenerator.rule;

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
