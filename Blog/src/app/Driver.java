package app;

import java.util.ArrayList;
import java.util.Arrays;

import tree.MyDecisionTreeClassifier;
import tree.MyDecisionTreeClassifier.Node;
import utilities.Question;
import utilities.QuestionGainPair;

public class Driver {

	public static void main(String[] args) {
		/**
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Color");
		headers.add("Texture");
		headers.add("Label");
		ArrayList<ArrayList<String>> trainingData = new ArrayList<ArrayList<String>>();
		trainingData.add(new ArrayList<String>(Arrays.asList("Orange", "Bumpy", "Orange")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Yellow", "Smooth", "Banana")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Red", "Smooth", "Apple")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Yellow", "Smooth", "Apple")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Green", "Smooth", "Apple")));
		**/
		
		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Movement");
		headers.add("Outsides");
		headers.add("Label");
		ArrayList<ArrayList<String>> trainingData = new ArrayList<ArrayList<String>>();
		trainingData.add(new ArrayList<String>(Arrays.asList("Walk", "Scales", "Iguana")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Walk", "Skin", "Human")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Fly", "Feather", "Eagle")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Fly", "Feather", "Crow")));
		trainingData.add(new ArrayList<String>(Arrays.asList("Swim", "Skin", "Dolphin")));
		
		MyDecisionTreeClassifier classifier = new MyDecisionTreeClassifier(headers);
		
		//This prints out the training data
		for (ArrayList<String> row: trainingData)
		{
			for (String value: row)
			{
				System.out.print(value + " ");
			}
			System.out.println();
		}
		
		//This gets the unique values and prints them out
		ArrayList<String> objects = classifier.uniqueValues(trainingData, 1);
		for (String value: objects)
		{
			System.out.println(value);
		}
		
		//This gets the unique labels as well as their count and prints them out
		ArrayList<String> labels = classifier.countClass(trainingData);
		for (String value: labels)
		{
			System.out.println(value);
		}
		
		//This creates a question and partitions the data based on that question
		Question q = new Question(headers, 1, "Smooth");
		System.out.println(q);
		ArrayList<ArrayList<ArrayList<String>>> trueFalseList = classifier.partition(trainingData, q);
		ArrayList<ArrayList<String>> trueList = trueFalseList.get(0);
		ArrayList<ArrayList<String>> falseList = trueFalseList.get(1);
		//This prints out the true results
		System.out.println("True:");
		for (ArrayList<String> row: trueList)
		{
			for (String value: row)
			{
				System.out.print(value + " ");
			}
			System.out.println();
		}
		System.out.println("False:");
		//This prints out the false results
		for (ArrayList<String> row: falseList)
		{
			for (String value: row)
			{
				System.out.print(value + " ");
			}
			System.out.println();
		}
		
		//This calculates the impurity and information gain and prints them out
		Double impurity = classifier.gini(trainingData);
		System.out.println("Impurity: " + impurity);
		Double infoGain = classifier.infoGain(trueList, falseList, impurity);
		System.out.println("Info Gain: " + infoGain);
		
		QuestionGainPair qgp = classifier.findBestSplit(trainingData);
		
		//This builds the decision tree and prints it out
		Node myTree = classifier.buildTree(trainingData);
		classifier.printTree(myTree, null);
		
		//This prints the prediction for the first row of data in the training data
		System.out.println(classifier.classify(trainingData.get(0), myTree));
		//This prints the predictions for each row of data in the training data
		int i = 0;
		for (ArrayList<String> row: trainingData)
		{
			System.out.println("Actual: " + row.get(row.size()-1) + " Predicted: " + classifier.classify(trainingData.get(i), myTree));
			i++;
		}
	}

}
