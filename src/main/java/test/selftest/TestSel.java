package test.selftest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestSel {

  @BeforeClass
  public void beforeClass() {
  }
  
  @Test
  public void function() {
	  
	  System.out.println("test");
	  
	  int a = 1;
	  int b =2;
	  String ab ="test";
	  String cdString ="test";
	  
	  
	  if (ab.equalsIgnoreCase(cdString)) {
		
	}
	  
	  
  }

  @AfterClass
  public void afterClass() {
  }

}
