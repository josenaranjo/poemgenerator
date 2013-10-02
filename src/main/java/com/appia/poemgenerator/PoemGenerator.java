package com.appia.poemgenerator;

import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.appia.poemgenerator.rule.AbstractRule;
import com.appia.poemgenerator.rule.Reference;
import com.appia.poemgenerator.rule.RuleNode;
import com.appia.poemgenerator.util.FileUtil;

/**
 * This is the principal class, will obtain
 * the input from the user and generate the poem.
 * @author josel
 *
 */
public class PoemGenerator {

	private static final Logger logger = Logger.getLogger(PoemGenerator.class);

	private static final String ATTRIBUTE_POEM = "poem";
	
	private final String grammaticalRulesPath;
	private List<String> lines;
	private Map<String, List<AbstractRule>> nodes;

	public PoemGenerator(String grammaticalRulesPath) {
		this.grammaticalRulesPath = grammaticalRulesPath;
		lines = FileUtil.readFileLines(grammaticalRulesPath);
		nodes = Parser.getInstance(lines).getRules();
	}

	public void generatePoem() {
		System.out.println(traverseRules(nodes.get(ATTRIBUTE_POEM)));
	}

	private String traverseRules(List<AbstractRule> list) {
		StringBuilder builder = new StringBuilder();
		for (AbstractRule rule : list) {
			if (!rule.isOr() && rule.getChildren().size() > 0) {
				builder.append(traverseRules(rule.getChildren()));
				builder.append(" ");
			} else {
				if (rule instanceof Reference) {
					builder.append(traverseRules(nodes.get(((Reference) rule).getName())));
					builder.append(" ");
				} else if (rule instanceof RuleNode) {
					builder.append(traverseRules(rule.getChildren()));
					builder.append(" ");
				} else {
					builder.append(rule.toString());
					builder.append(" ");
				}
			}
		}
		return builder.toString();
	}

	public String getGrammaticalRulesPath() {
		return grammaticalRulesPath;
	}

	public static void main(String[] args) {
		BasicConfigurator.configure();
		logger.info("Entering application.");
		
		if(args.length > 0 && args[0] != null && !"".equals(args[0])) {
			new PoemGenerator(args[0]).generatePoem();
		} else {
			System.out.println("Please include the path to the rules file.");
		}
		
		logger.info("Exiting application.");
		System.exit(0);
	}

}
