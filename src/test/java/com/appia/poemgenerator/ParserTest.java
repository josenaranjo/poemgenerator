package com.appia.poemgenerator;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	private Parser parser;
	private List<String> lines = new ArrayList<>();

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
		fail("Not yet implemented");
	}

	@Test
	public void testGetKeySuccessful() throws IllegalAccessException, IllegalArgumentException,
	    InvocationTargetException, NoSuchMethodException, SecurityException {
		Method method = Parser.class.getDeclaredMethod("getKey", String.class);
		method.setAccessible(true);
		Assert.assertEquals("POEM", method.invoke(parser, lines.get(0)));
		Assert.assertEquals("VERB", method.invoke(parser, lines.get(4)));
	}

}
