package utilities;

import java.util.ArrayList;

/**
 * This class represents a question that is asked by the classifier.
 * @author 756887
 *
 */
public class Question {
	
	private ArrayList<String> headers;
	private int column;
	private Double numericValue;
	private String stringValue;
	
	public Question(ArrayList<String> headers, int column, Double numericValue)
	{
		this.headers = headers;
		this.column = column;
		this.numericValue = numericValue;
	}
	
	public Question(ArrayList<String> headers, int column, String stringValue)
	{
		this.headers = headers;
		this.column = column;
		Double numVal = 0.0;
		boolean isNumeric = true;
		try {
			   numVal = Double.parseDouble(stringValue);
		} catch (NumberFormatException e)
		{
			   isNumeric = false;
		}
		if (isNumeric)
		{
			this.numericValue = numVal;
		} else
		{
			this.stringValue = stringValue;
		}
	}
	
	public Question() {}
	
	/**
	 * This method returns true or false if the input row of data matches the question.
	 * 
	 * @param example Row of data to test against the question
	 * @return True if the data matches the question and false if it does not
	 */
	public Boolean match(ArrayList<String> example)
	{
		String val = example.get(column);
		Double numVal = 0.0;
		boolean isNumeric = true;
		try {
			   numVal = Double.parseDouble(val);
		} catch (NumberFormatException e)
		{
			   isNumeric = false;
		}
		if (isNumeric)
		{
			return numVal >= numericValue;
		} else
		{
			return val == stringValue;
		}
	}
	
	public String toString()
	{
		if (numericValue != null)
		{
			return "Is " + headers.get(column) + " >= " + numericValue;
		} else if (stringValue != null && stringValue != "")
		{
			return "Is " + headers.get(column) + " == " + stringValue;
		} else
		{
			return null;
		}
	}

	/**
	 * @return the headers
	 */
	public ArrayList<String> getHeaders() {
		return headers;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return the numericValue
	 */
	public Double getNumericValue() {
		return numericValue;
	}

	/**
	 * @return the stringValue
	 */
	public String getStringValue() {
		return stringValue;
	}
	
	
}
