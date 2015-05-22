package controller;

import static org.junit.Assert.*;
import model.Pixel;

import org.junit.Test;

public class ConversionRGBversCMYKTest {

// Les test ont ete pris en exemple sur le site	http://www.vogella.com/tutorials/JUnit/article.html#unitintegrationperformancetests
	
	
	
	
	
	
	@Test
	public void testGetC() {
		
		Pixel pixel = new Pixel (1,0,0,255);
		System.out.println("C: "+ ConversionRGBversCMYK.getC(pixel));
		//assertEquals(0, ConversionRGBversCMYK.getC(pixel));
		//fail("Not yet implemented");
	}

	@Test
	public void testGetM() {
		
		Pixel pixel = new Pixel (1,0,0,255);
		System.out.println("M: "+ConversionRGBversCMYK.getM(pixel));
		//assertEquals(0, ConversionRGBversCMYK.getM(pixel));
		//fail("Not yet implemented");
	}

	@Test
	public void testGetY() {
		
		Pixel pixel = new Pixel (1,0,0,255);
		System.out.println("Y: "+ConversionRGBversCMYK.getY(pixel));
		//assertEquals(0, ConversionRGBversCMYK.getY(pixel));
		//fail("Not yet implemented");
	}

	@Test
	public void testGetK() {
		
		Pixel pixel = new Pixel (1,0,0,255);
		System.out.println("K: "+ConversionRGBversCMYK.getK(pixel));
		//assertEquals(0, ConversionRGBversCMYK.getK(pixel));
		//fail("Not yet implemented");
	}

	@Test
	public void testGetR() {
		
		System.out.println("rouge:" + ConversionRGBversCMYK.getR(0, 0));
		//assertEquals(0, ConversionRGBversCMYK.getR(1,1));
		//fail("Not yet implemented");
	}

	@Test
	public void testGetG() {
		
		System.out.println("vert:" + ConversionRGBversCMYK.getG(1, 0));
		//assertEquals(0, ConversionRGBversCMYK.getR(1,1));
		//fail("Not yet implemented");
	}

	@Test
	public void testGetB() {
		
		System.out.println("bleu:" + ConversionRGBversCMYK.getB(1, 0));
		//assertEquals(0, ConversionRGBversCMYK.getR(1,1));
		//fail("Not yet implemented");
	}

}
