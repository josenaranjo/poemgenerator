package com.appia.poemgenerator.rule;

/**
 * 
 * This class represent a word as a grammatical rule
 * 
 * @author josel
 *
 */
public class Word extends AbstractRule {
	
	private final String word;
	
	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
	  return word;
  }
	
	@Override
	public String toString() {
		return word;
	}

}
