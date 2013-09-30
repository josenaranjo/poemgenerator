package com.appia.poemgenerator.rule;

/**
 * This class will represent the rule keyword
 * extracted from the grammatical rule file.
 * @author josel
 *
 */
public class Keyword extends AbstractRule {
	
	private KeywordType type;
	
	public Keyword(String keyword) {
	  type = KeywordType.parse(keyword);
  }

	public KeywordType getType() {
	  return type;
  }

	public void setType(KeywordType type) {
	  this.type = type;
  }
	
	@Override
	public String toString() {
		return type.getKeyword();
	}

}
