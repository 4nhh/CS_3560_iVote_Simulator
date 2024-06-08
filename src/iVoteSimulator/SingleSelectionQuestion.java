package iVoteSimulator;
import java.util.*;

/**
 *  Represents a quiz question wherein only one answer may be selected.
 *  @author Anh Hoang
 */
public class SingleSelectionQuestion extends Question {

	/** Default constructor. Creates an empty SingleSelectionQuestion object with null/default values. */
	public SingleSelectionQuestion() {
		super();
	}

	/**
	 *  Explicit constructor. Creates a Question object with provided values.
	 *  @param newPrompt      New prompt for the question
	 *  @param newOptions     Set of answer options for the question
	 *  @param newSolution	  Set of correct answers for the question
	 *  @param newPointValue  Value of question expressed in points
	 */
	public SingleSelectionQuestion(String newPrompt, LinkedHashSet<String> newOptions,
			HashSet<String> newSolution, double newPointValue) {
		super(newPrompt, newOptions, newSolution, newPointValue);
	}

	/**
	 *  Verify student answer to determine whether they receive points. As only
	 *  one answer is possible, they either earn full points or no points.
	 *  @param Set of the student's answers to the question
	 *  @return Number of points the student earns on the question
	 */
	public double checkAnswer(HashSet<String> studentAnswer) {
		// Since only one solution is possible, we only need to check for equality.
		if (this.getSolution().equals(studentAnswer)) { // If correct,
			return this.getPointValue();				// full points!  :)
		} else {										// If incorrect,
			return 0.0;									// nothing.
		}
	}
}