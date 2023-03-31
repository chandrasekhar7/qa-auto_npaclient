package com.pages.testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/"},
        plugin = {"pretty","json:target/json-report/cucumber.json",
        		"com.epam.reportportal.cucumber.ScenarioReporter",
        		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        		tags = "@regression",
        //Enable dryRun if you want to do a dryrun and not open the browser
        //dryRun=true,
        glue={"stepDefinitions"},
        
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests{
	   @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
}

