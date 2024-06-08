package iVoteSimulator;
import java.util.*;

/**
 *  Represents a quiz question wherein more than one answer may be selected.
 *  @author Anh Hoang
 */
public class MultipleSelectionQuestion extends Question {

	/** Default constructor. Creates an empty MultipleSelectionQuestion object with null/default values. */
	public MultipleSelectionQuestion() {
		super();
	}

	/**
	 *  Explicit constructor. Creates a Question object with provided values.
	 *  @param newPrompt      New prompt for the question
	 *  @param newOptions     Set of answer options for the question
	 *  @param newSolution	  Set of correct answers for the question
	 *  @param newPointValue  Value of question expressed in points
	 */
	public MultipleSelectionQuestion(String newPrompt, LinkedHashSet<String> newOptions,
									 HashSet<String> newSolution, double newPointValue) {
		super(newPrompt, newOptions, newSolution, newPointValue);
	}

	/**
	 *  Verify student answer to determine the amount of points they receive. The
	 *  number of points that each answer is worth scales based on the number of
	 *  correct answers.
	 *  @param Set of the student's answers to the question
	 *  @return Number of points the student earns on the question
	 */
	public double checkAnswer(HashSet<String> studentAnswer) {
		double totalPoints = this.getPointValue(); // Point weight of the question
		double pointsPerAnswer = totalPoints / this.getSolution().size(); // Value of each answer
		HashSet<String> studentAnswerCopy = new HashSet<>(studentAnswer); // Copy the student answer

		/* There are three cases to consider: (1.) Correct answer, (2.) Missing correct answer,
		 * and (3.) Incorrect answer. The following checks take care of all three by removing
		 * points for incorrect answers and only giving points for correct answers. */
		// Correct answers
		studentAnswerCopy.retainAll(this.getSolution()); // Intersection of student answers and the solution.
		int correctAnswers = studentAnswerCopy.size();
	
		// Incorect answers
		studentAnswerCopy = new HashSet<>(studentAnswer); // Copy studentAnswer again
		studentAnswerCopy.removeAll(this.getSolution());  // Difference between student answers and the solution
		int incorrectAnswers = studentAnswerCopy.size(); 
	
		// Calculate the net points earned
		double pointsEarned = (correctAnswers*pointsPerAnswer) - (incorrectAnswers*pointsPerAnswer);

		return Math.max(pointsEarned, 0.0); // Student can't earn negative points; min score is 0
	}
}