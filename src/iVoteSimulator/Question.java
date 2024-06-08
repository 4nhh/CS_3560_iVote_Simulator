package iVoteSimulator;
import java.util.*;

/**
 *  Abstract class representing a question in the voting simulator.
 *  Contains methods to create, modify and access question objects.
 *  @author Anh Hoang
 */
public abstract class Question {
	/* The choice String for the question prompt is relatively self-explanatory.
	 * I used HashSets for the options and the solution in order to guarantee uniqueness
	 * and efficient access to set operations (intersection, difference).
	 * For the options, I used a LinkedHashSet to easily display them by insertion order.
	 * As for the choice of using double for the point value, it is possible for questions and
	 * answer weights to yield point tallies that aren't perfect integers. You will see this
	 * in the simulation.
	 */
	protected String prompt; 				  // Prompt for the question
	protected LinkedHashSet<String> options;  // Holds answer choices for the question.
	protected HashSet<String> solution; 	  // Holds the correct answer(s) for the question
	protected double pointValue; 			  // Weight of the question in points

	/** Default constructor. Creates an empty Question object with null values. */
	public Question() {
		this.prompt = "";
		this.options = new LinkedHashSet<String>();
		this.solution = new HashSet<String>();
		this.pointValue = 0.0;
	}

	/**
	 *  Explicit constructor. Creates a Question object with provided values.
	 *  @param newPrompt      New prompt for the question
	 *  @param newOptions     Set of answer options for the question
	 *  @param newSolution	  Set of correct answers for the question
	 *  @param newPointValue  Value of question expressed in points
	 */
	public Question(String newPrompt, LinkedHashSet<String> newOptions,
					HashSet<String> newSolution, double newPointValue) {
		this.prompt = newPrompt;
		this.options = newOptions;
		this.solution = newSolution;
		this.pointValue = newPointValue;
	}

	/**
	 * Getter method for prompt variable.
	 * @return  Question prompt string value
	 */
	public String getPrompt() {
		return this.prompt;
	}

	/**
	 * Setter method for prompt variable.
	 * @param p  New value to set as prompt
	 */
	public void setPrompt(String p) {
		this.prompt = p;
	}

	/**
	 * Getter method for options variable.
	 * @return  Set of selectable options
	 */
	public LinkedHashSet<String> getOptions() {
		return this.options;
	}

	/**
	 * Setter method for options variable.
	 * @param o  New options set for the question
	 */
	public void setOptions(LinkedHashSet<String> o) {
		this.options = o;
	}

	/**
	 * Setter method for solution variable.
	 * @param s  New solution set for the question
	 */
	public void setSolution(HashSet<String> s) {
		this.solution = s;
	}

	/**
	 * Getter method for solution variable.
	 * @return Solution set
	 */
	public HashSet<String> getSolution() {
		return this.solution;
	}

	/**
	 * Getter method for pointValue variable.
	 * @return Point value of question
	 */
	public double getPointValue() {
		return this.pointValue;
	}

	/**
	 * Setter method for pointValue variable.
	 * @param p  New point value for the question
	 */
	public void setPointValue(double p) {
		this.pointValue = p;
	}

	/**
	 * Abstract method to check the solution and determine score
	 * based on provided student's answer. To be implemented in subclasses.
	 * @param studentAnswer  Answers provided by student
	 * @return 				 Calculated score
	 */
	abstract double checkAnswer(HashSet<String> studentAnswer);
}
