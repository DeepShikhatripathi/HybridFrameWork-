package testController;

import java.util.ArrayList;
import java.util.HashMap;

import keywordLibrary.Keyword;
import utilities.Excel;

public class Execution {

	public static void main(String[] args) throws Exception {

		HashMap<Integer, ArrayList<String>> testCaseData = Excel.getData("TestCases");
		System.out.println(testCaseData);

		for (Integer testCaseKey : testCaseData.keySet()) {
			ArrayList<String> testExecutionData = testCaseData.get(testCaseKey);
			if (testExecutionData.get(1).equals("run")) {
				HashMap<Integer, ArrayList<String>> data = Excel.getData(testExecutionData.get(0));
				System.out.println(data);
				for (Integer key : data.keySet()) {
					ArrayList<String> values = data.get(key);
					System.out.println(values);
					Keyword.invokeKeyword(values.get(0), values.get(1), values.get(2), values.get(3));
				}
				Excel.updateRow("TestCases", "Executed", testCaseKey);

			} else {
				System.out.println("Test case " + testExecutionData.get(0) + " was skipped!");
				Excel.updateRow("TestCases", "Skipped", testCaseKey);

			}

		}

	}

}
