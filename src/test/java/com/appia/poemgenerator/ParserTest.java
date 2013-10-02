package com.appia.poemgenerator;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.appia.poemgenerator.rule.AbstractRule;

public class ParserTest {

	private Parser parser;
	private List<String> lines = new ArrayList<>();
	private String testLine = "<NOUN>|<PREPOSITION>|<PRONOUN> $LINEBREAK";

	@Before
	public void setUp() throws Exception {
		lines.add("POEM: <LINE> <LINE> <LINE> <LINE> <LINE>");
		lines.add("LINE: <NOUN>|<PREPOSITION>|<PRONOUN> $LINEBREAK");
		lines.add("ADJECTIVE: black|white|dark|light|bright|murky|muddy|clear <NOUN>|<ADJECTIVE>|$END");
		lines.add("PRONOUN: my|your|his|her <NOUN>|<ADJECTIVE>");
		lines
		    .add("VERB : runs|walks|stands|climbs|crawls|flows|flies|transcends|ascends|descends|sinks <PREPOSITION>|<PRONOUN>|$END");

		parser = Parser.getInstance(lines);
	}

	@Test
	public void testGetRules() {
		Assert.assertEquals(parser.getRules().size(), 5);
	}

	@SuppressWarnings("unchecked")
  @Test
	public void testGetKeySuccessful() throws IllegalAccessException, IllegalArgumentException,
	    InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = Parser.class.getDeclaredMethod("getRuleList", String.class);
		method.setAccessible(true);
		Assert.assertEquals(2, ((List<AbstractRule>) method.invoke(parser, testLine)).size());
	}

}
