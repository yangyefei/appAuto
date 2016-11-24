package test.selftest;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class TestSel {

  @BeforeClass
  public void beforeClass() {
  }
  
  @Test
  public void function() throws Exception {
	  
		String filePath =  String.valueOf(new File("./../../").getCanonicalPath()) +"auto.pdf";
		
		System.out.println(filePath);
	  

  }

  @AfterClass
  public void afterClass() {
  }

}
