package iVoteSimulator;
import java.util.*;

/**
 * Represents a student participating in the voting.
 * @author Anh Hoang
 */
public class Student {
	private String UID;    // Unique ID
	private double points; // Total points earned

	/* Store answer submissions in key-value pair, where the key is the index of the
	 * question and the value is a set that contains the student's corresponding answer(s). */
	private Map<Integer, HashSet<String>> submissions; 
	/* The use of a HashSet for holding answers is due to uniqueness and access to set operations.
	 * Critically, the key-value mechanism means that the student's resubmission for a question will
	 * replace their old answer set, as intended. One question, one set of selected answers.*/
	
	/** Default constructor. Creates an empty Student object with null/default values. */
	public Student() {
		submissions = new HashMap<>();
		/* I use the UUID utility to generate UIDs here because it's known for uniqueness.
		 * While a collision is theoretically possible, they're highly, highly unlikely.
		 * The UIDs will be displayed in SimulationDriver (via VotingService's printTopTen()),
		 * though if this were an actual web application, it'd be preferrable to have a
		 * seperate field for human-readable student names.*/
		UID = UUID.randomUUID().toString();
		points = 0;
	}

	/**
	 * Retrieve a student's unique user ID.
	 * @return Student's UID
	 */
	public String getUID() {
		String studentUID = this.UID;
		return studentUID;
	}

	/**
	 * Get the points accrued by a given student.
	 * @return Student's point value.
	 */
	public double getPoints() {
		double studentPoints = this.points;
		return studentPoints;
	}

	/**
	 * Add a positive, nonzero amount of points to a student's total.
	 * @param pointChange The number of points to add
	 */
	public void addPoints(double pointChange) {
		// checkAnswer() ensures that a non-negative number of points is
		// returned. It's not necessary to repeat the same check here.
		this.points += pointChange;
	}

	/**
	 * Replace a student's entire submission (existing or not) for a
	 * specific question.
	 * @param index  Index of the question to be answered
	 * @param answer Set of the student's answer(s)
	 */
	public void setSubmission(int index, HashSet<String> answer) {
		submissions.put(index, answer); // Occurs whether or not the index key is in use
	}

	/**
	 * Add another String to the set containing the student's answers to a specific
	 * question.
	 * @param index  Index of the question to be answered
	 * @param answer Answer letter to be added
	 */
	public void addAnswer(int index, String answer) {
		submissions.get(index).add(answer);
	}

	/**
	 * Retrieve the set of a student's submissions for every question.
	 * @return All of a student's submissions
	 */
	public Map<Integer, HashSet<String>> getSubmissions() {
		Map<Integer, HashSet<String>> s = this.submissions;
		return s;
	}
	
	/**
	 * Retrieve the set of a student's submissions for a specific question.
	 * @param	Index of question
	 * @return  The student's latest submission for the question
	 */
	public HashSet<String> getSubmissions(int index) {
		HashSet<String> s = this.submissions.get((Integer)index);
		return s;
	}

}
