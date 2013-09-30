package com.appia.poemgenerator.rule;

/**
 * 
 * This factory will return the appropriate instance
 * of the parsed rule
 * 
 * @author josel
 *
 */
public class RuleFactory {
	
	private RuleFactory() {
	}
	
	public static AbstractRule getRuleInstance(String node) {
		if(node.matches("^<.*>$")) {
			return new Reference(node.replaceAll("(<|>)", "").toLowerCase());
		} else if (node.startsWith("$")) {
			return new Keyword(node);
		} else {
			return new Word(node);
		}
	}

}
