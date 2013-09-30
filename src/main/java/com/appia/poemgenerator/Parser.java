package com.appia.poemgenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.appia.poemgenerator.rule.AbstractRule;
import com.appia.poemgenerator.rule.RuleFactory;
import com.appia.poemgenerator.rule.RuleNode;

/**
 * This class will parse the file which contains 
 * the grammatical rules.
 * 
 * @author josel
 *
 */
public class Parser {
	
	public static final String REGEX_PIPELINE = "[|]";
	public static final String REGEX_SPACE = "\\s";

	private static final Logger logger = Logger.getLogger(Parser.class);
	
	private final List<String> lines;
	
	private Parser(List<String> lines) {
		this.lines = lines;
  }
	
	public static Parser getInstance(List<String> lines) {
		if(lines == null) {
			throw new IllegalArgumentException("The line can't be null");
		}
		return new Parser(lines);
	}
	
	/**
	 * This method will return a Map containing list of possible
	 * rules defined in the parsed file
	 * @return a map representation of each rule
	 */
	public Map<String, List<AbstractRule>> getRules() {
		Map<String, List<AbstractRule>> nodes = new HashMap<>();
		for(String line : lines) {
			String[] pieces = splitLine(line, ":");
			if(pieces.length > 1) {
				nodes.put(pieces[0].trim().toLowerCase(), getRuleList(pieces[1].trim()));
			}
		}
		
		return nodes;
	}

	private List<AbstractRule> getRuleList(String line) {
		List<AbstractRule> ruleAndChildren = new ArrayList<>();
		for(String nodes : line.split(REGEX_SPACE)) {
			AbstractRule node = null;
			if(nodes.split(REGEX_PIPELINE).length > 1) {
				List<AbstractRule> ruleOrChildren = new ArrayList<>();
				node = new RuleNode(nodes);
				node.setOr(true);
				for(String part : nodes.split(REGEX_PIPELINE)) {
					ruleOrChildren.add(RuleFactory.getRuleInstance(part));
				}
				node.setChildren(ruleOrChildren);
			} else {
				node = RuleFactory.getRuleInstance(nodes);
			}
			ruleAndChildren.add(node);
		}
		
	  return ruleAndChildren;
  }

	public List<String> getLines() {
	  return lines;
  }

	private String[] splitLine(String line, String regex) {
		String[] pieces = line.split(regex);
		if(pieces.length < 1) {
			logger.debug("The line didn't have the expected format " + line);
		}
		return pieces;
	}
	

}
