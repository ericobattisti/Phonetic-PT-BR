package br.com.ebr.phonetizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FonetizacaoTest {

	private static PhonetizerBR phonetizerBR;

	@BeforeAll
	public static void init() {
		phonetizerBR = new PhonetizerBR();
	}

	@Test
	public void validPhonetizerTest() {
		var code1 = phonetizerBR.fonetizar("Rafaela Calleri");
		var code2 = phonetizerBR.fonetizar("Raphaela Kaleri");
		var code3 = phonetizerBR.fonetizar("Rafaella Kalery");

		assertEquals(code1, code2);
		assertEquals(code1, code3);
	}

	@Test
	public void validPhonetizerCodeTest() {
		var code1 = phonetizerBR.makePhoneticCode("Rafaela Calleri");
		var code2 = phonetizerBR.makePhoneticCode("Raphaela Kaleri");
		
		assertEquals(code1, code2);
	}

	@Test
	public void containsPhonetizerTest() {
		ArrayList<String> codes = phonetizerBR.makeAllPhoneticCodes("Rafaela Calleri França");

		String codeTest = phonetizerBR.makePhoneticCode("Raphaela Kaleri");

		assertTrue(codes.contains(codeTest));
	}
}
