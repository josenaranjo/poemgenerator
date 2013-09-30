package com.appia.poemgenerator.rule;

/**
 * This class represents a node which contains
 * a set of children 
 * @author josel
 *
 */
public class RuleNode extends AbstractRule {
	
	private String parsedString;
	
	public RuleNode(String parsedString) {
		this.parsedString = parsedString;
  }

	public String getParsedString() {
	  return parsedString;
  }

	public void setParsedString(String parsedString) {
	  this.parsedString = parsedString;
  }

}
