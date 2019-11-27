package com.mayab.calidad.travis;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testAdd() {
		Calculator calc = new Calculator();
		assertEquals(calc.add(1, 1), 2);
	}
	@Test
	public void testSub() {
		Calculator calc = new Calculator();
		assertEquals(calc.sub(1, 1), 0);
	}
	@Test
	public void testMul() {
		Calculator calc = new Calculator();
		assertEquals(calc.mul(3, 5), 15);
	}
	@Test
	public void testDiv() {
		Calculator calc = new Calculator();
		assertEquals(calc.div(8, 2), 4);
	}

}
