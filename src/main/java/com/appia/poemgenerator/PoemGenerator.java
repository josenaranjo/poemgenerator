package com.appia.poemgenerator;

import java.util.List;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.appia.poemgenerator.rule.AbstractRule;
import com.appia.poemgenerator.rule.Reference;
import com.appia.poemgenerator.rule.RuleNode;
import com.appia.poemgenerator.util.FileUtil;

public class PoemGenerator {

	private static final Logger logger = Logger.getLogger(PoemGenerator.class);
	private final String grammaticalRulesPath;
	private List<String> lines;
	private Map<String, List<AbstractRule>> nodes;

	public PoemGenerator(String grammaticalRulesPath) {
		this.grammaticalRulesPath = grammaticalRulesPath;
		lines = FileUtil.readFileLines(grammaticalRulesPath);
		nodes = Parser.getInstance(lines).getRules();
	}

	public void generatePoem() {
		System.out.println(traverseRules(nodes.get("poem")));
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
		String path = "/Users/josel/Documents/workspace/poemgenerator/rules.txt";
		
		new PoemGenerator(path).generatePoem();
		
		logger.info("Exiting application.");
		System.exit(0);
	}

}
