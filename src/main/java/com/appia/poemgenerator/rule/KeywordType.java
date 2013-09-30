package com.appia.poemgenerator.rule;

/**
 * This enum will represent all the possible keywords
 * in the grammatical rules.
 * @author josel
 *
 */
public enum KeywordType {
	END(""), LINEBREAK("\\\\n");
	
	private String keyword;
	
	private KeywordType(String keyword) {
	  this.keyword = keyword;
  }
	
	public static KeywordType parse(String keyword) {
		switch (keyword.toUpperCase()) {
		case "$END":
			return KeywordType.END;
		case "$LINEBREAK":
			return KeywordType.LINEBREAK;
		default:
			return null;
		}
	}

	public String getKeyword() {
	  return keyword;
  }

	public void setKeyword(String keyword) {
	  this.keyword = keyword;
  }

}
