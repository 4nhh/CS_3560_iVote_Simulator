package iVoteSimulator;
import java.util.*;

/**
 * Demonstrate the main processes of the vote simulator.
 * @author Anh Hoang
 */
public class VotingService {

	private LinkedHashSet<Question> questionList;
	private LinkedHashSet<Student> studentList;

	/** Explicit constructor.
	 * @param questions	 LinkedHashSet of questions to be answered
	 * @param students	 LinkedHashSet of participating students
	 */
	public VotingService(LinkedHashSet<Question> questions, LinkedHashSet<Student> students) {
		this.setQuestions(questions);
		this.setStudentList(students);
	}
	public void setQuestions(LinkedHashSet<Question> q) {
		questionList = q;
	}
	public LinkedHashSet<Question> getQuestions() {
		LinkedHashSet<Question> q = this.questionList;
		return q;
	}
	public void setStudentList(LinkedHashSet<Student> students) {
		this.studentList = students;
	}
	public LinkedHashSet<Student> getStudentList() {
		LinkedHashSet<Student> students = this.studentList;
		return students;
	}

	// Processes all questions in the question list and displays the output.
	public void processQuestions() {
		int i = 0;
		for (Question question : questionList) {
			Map<String, Integer> answerCount = new LinkedHashMap<String, Integer>();

			System.out.println("\u001b[1m" + question.prompt + "\u001b[0m"); // Print each prompt
			int k = 0;
			for (String option : question.options) {
				String ansLetter = Character.toString((char)('A' +  k));
				System.out.println("\t" + option); 	 // Print each option
				answerCount.put(ansLetter, 0);
				k++;
			}

			for (Student s : studentList) { // For each student
				// Compare answers to corresponding question in list
				double pointsEarned = question.checkAnswer(s.getSubmissions(i));
				s.addPoints(pointsEarned); // Update student point count
				for (String key : answerCount.keySet()) {
					if (s.getSubmissions(i).contains(key)) { // Increment answer frequency
						answerCount.put(key, answerCount.get(key) + 1);
					}
				}
			}
			for (Map.Entry<String, Integer> e : answerCount.entrySet()) {
				if (question.getSolution().contains(e.getKey())) { // Correct answers
					System.out.print(" \u001b[32m" + e.getKey() + ": " + e.getValue() + "\u001b[0m\t "); // Print in green
				}
				else { // Incorrect answers
					System.out.print(" \u001b[31m" + e.getKey() + ": " + e.getValue() + "\u001b[0m\t "); // Print in red
				}
			}
			System.out.println("\n");
			++i;
		}
	}
	Comparator<Student> compareByPoints = new Comparator<Student>() {
	    @Override
	    public int compare(Student s1, Student s2) {
	        return Double.compare(s2.getPoints(), s1.getPoints());
	    }
	};
	
	public void printTopTen() {
		ArrayList<Student> studentArrayList = new ArrayList<Student>(studentList);
		double maxPoints = 0;
		
		Collections.sort(studentArrayList, compareByPoints);
		for (Question q : questionList) {
			maxPoints += q.getPointValue();
		}
		
		System.out.println("\u001b[1m" + studentList.size() + 
						   " students participated in this poll.\n" +
						   "Congratulations to the top 10 scorers!\u001b[0m");

		int studentRank = 1;
		int i = 0;
		for (Student s : studentArrayList) {
			System.out.println(String.format("%-4s", studentRank + ".") + s.getUID()
							   + "  (" + String.format("%.2f", s.getPoints()) + "/" + maxPoints + ")");
			++studentRank;
			++i;
			if (i == 10) {
				break;
			}
		}
	}
}