package core;

import java.util.ArrayList;
import java.util.List;

import org.testng.IClass;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import edu.emory.mathcs.backport.java.util.Arrays;

public class Listener extends TestListenerAdapter  {
	
	public static List<String> list = new ArrayList<String>();
	public static List<String> resultList = new ArrayList<String>();
	public static int count = 0;
	
	@Override
	public void onTestStart(ITestResult tr) {
		log("Test ["+tr.getName()+"] Started ~~~~");
	}

	/*@Override
	public void onTestSuccess(ITestResult tr) {
		StringBuffer sb = new StringBuffer();
        sb.append(tr.getMethod().getMethodName());
        sb.append(" - run for following parameters : ");
        for (Object eachObject : tr.getParameters()) {
            sb.append(eachObject).append(",");
        }
        //sb.append("]");
		log("Test [" + tr.getName() + "] PASSED");
		// This will print the class name in which the method is present
		log(tr.getTestClass());
		System.out.println("................................");
		String a[] = tr.getTestClass().toString().split(" ");
		String b = a[2].replace("]", "").replace("nobleMarketTest.", "");
		list.add("<br />");
		list.add("|-");
		list.add("<br />");
		list.add("|"+b);
		list.add("||"+sb);
		list.add("||"+"Pass");
		
	}*/
	
	public void onTestSuccess(ITestResult tr) {
		count++;
		StringBuffer sb = new StringBuffer();
		String arr[] = tr.getMethod().getMethodName().split("_TC_");
        sb.append("TC_").append(arr[1]);
        //sb.append(" - run for following parameters : ");
        /*for (Object eachObject : tr.getParameters()) {
            sb.append(eachObject).append(",");
        }*/
        //sb.append("]");
		log("Test [" + tr.getName() + "] PASSED");
		// This will print the class name in which the method is present
		log(tr.getTestClass());
		System.out.println("................................");
		String a[] = tr.getTestClass().toString().split(" ");
		String b = a[2].replace("]", "").replace("nobleMarketTest.", "");
		StringBuffer format1 = new StringBuffer();
		format1.append("\n").append("|-").append("\n").append("|").append(count).append("\n").append("|").append(b).append("\n").append("|").append(sb).append("\n").append("|").append("Pass").append("\n");
		System.out.println("************************"+format1.toString()+"****************************");
		list.add(format1.toString());
		resultList.add("PASSED");
		
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		count++;
		StringBuffer sb = new StringBuffer();
		String arr[] = tr.getMethod().getMethodName().split("_TC_");
        sb.append("TC_").append(arr[1]);
       /* for (Object eachObject : tr.getParameters()) {
            sb.append(eachObject).append(",");
        }*/
       // sb.append("]");

		log("Test [" + tr.getName() + "] FAILED !");
		System.out.println("................................");
		String a[] = tr.getTestClass().toString().split(" ");
		String b = a[2].replace("]", "").replace("nobleMarketTest.", "");
		StringBuffer format1 = new StringBuffer();
		format1.append("\n").append("|-").append("\n").append("|").append(count).append("\n").append("|").append(b).append("\n").append("|").append(sb).append("\n").append("|").append("Fail").append("\n");
		System.out.println("************************"+format1.toString()+"****************************");
		list.add(format1.toString());
		resultList.add("FAILED");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		count++;
		log("Test [" + tr.getName() + "] SKIPPED");
		System.out.println("................................");
		String a[] = tr.getTestClass().toString().split(" ");
		String b = a[2].replace("]", "").replace("nobleMarketTest.", "");
		list.add("\n");
		list.add("|-");
		list.add("\n");
		list.add("|"+b);
		list.add("||"+tr.getName());
		list.add("||"+"Skipped");
		resultList.add("SKIPPED");
	}

	private void log(Object[] methodName) {
		System.out.println(methodName);
	}
	
	private void log(String methodName) {
		System.out.println(methodName);
	}

	private void log(IClass testClass) {
		System.out.println(testClass);
	}
	
	public List<String> getResultList()
	{
		
		return list;
	}
	
	public List<String> getPassFailResult()
	{
		return resultList;
	}
}
