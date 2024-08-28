package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="leventebanyoi.stepDefinitions", 
monochrome=true, tags = "@Regression" ,plugin= {"html:target/cucumber.html"})  //  tags = "@Regression" this will only run test marked with tag Regression
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	

}
