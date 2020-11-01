package br.newtonpaiva.lab.tcc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TccApplicationTests extends AbstractResourceTest{

	@Test
	public void testContextLoads() {
		assertTrue(true);
	}

}
