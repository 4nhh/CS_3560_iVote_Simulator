package iVoteSimulator;
import java.util.*;

/**
 * Executes an instance of the voting simulator.
 * @author Anh Hoang
 */
public class SimulationDriver {

	/**
	 * Uses the explicit constructor of the question class to generate a hardcoded
	 * set of questions for testing.
	 * @return LinkedHashSet of four pre-determined questions
	 */
	public static LinkedHashSet<Question> createQuestions() {
		Question q1 = new MultipleSelectionQuestion("Select all valid dog breeds.",
				new LinkedHashSet<String>(
						Arrays.asList("A. Shih Tzu", "B. Blue Heeler", "C. Abyssinian", "D. Maltese")),
				new HashSet<String>(Arrays.asList("A", "B", "D")), 4.0);
		Question q2 = new MultipleSelectionQuestion("Select the facts about dogs that are true.",
				new LinkedHashSet<String>(Arrays.asList("A. Dogs regulate their temperature by panting.",
						"B. One dog year is equivalent to 7 human years.", "C. Each dog's nose has a unique print.")),
				new HashSet<String>(Arrays.asList("A", "C")), 5.0);
		Question q3 = new SingleSelectionQuestion("It is safe for dogs to consume grapes.",
				new LinkedHashSet<String>(Arrays.asList("A. True", "B. False")),
				new HashSet<String>(Arrays.asList("B")), 2.0);
		Question q4 = new SingleSelectionQuestion("How many dog breeds are officially recognized?",
				new LinkedHashSet<String>(Arrays.asList("A. 300", "B. 330", "C. 360", "D. 390")),
				new HashSet<String>(Arrays.asList("C")), 2.0);
		return new LinkedHashSet<Question>(Arrays.asList(q1, q2, q3, q4));
	}

	/**
	 * Generates a random number of students (between 15 and 50) for use in testing
	 * the voting simulator.
	 * @return LinkedHashSet of 6 to 20 Student objects
	 */
	public static HashSet<Student> generateStudents() {
		Random rand = new Random();
		HashSet<Student> studentList = new HashSet<Student>();
		int numStudents = (rand.nextInt(36) + 15);
		for (int i = 0; i < numStudents; i++) {
			Student newStudent = new Student();
			studentList.add(newStudent);
		}
		return studentList;
	}

	/**
	 * Randomly populates Student submissions for each question in the set. Each
	 * choice has equal probability of selection.
	 * @param questions LinkedHashSet of Questions to simulate.
	 * @param students  LinkedHashSet of participating Students.
	 * @return LinkedHashSet of Students with valid submissions.
	 */
	public static void populateStudentAnswers(LinkedHashSet<Question> questions,
				  							  HashSet<Student> students) {
		HashSet<Student> studentsWithAns = students;
		int i = 0;
		for (Question q : questions) {
			int options = q.getOptions().size();
			Random rand = new Random();
			for (Student s : students) {
				s.setSubmission((Integer) i, new HashSet<String>());
				if (q instanceof MultipleSelectionQuestion) {
					while (s.getSubmissions(i).isEmpty()) {
						for (int k = 0; k < options; k++) {
							if (rand.nextDouble() < (1.0 / options)) {
								char ans = (char) ('A' + rand.nextInt(options));
								s.addAnswer(i, Character.toString(ans));
							}
						}
					}
				} else {
					char ans = (char) ('A' + rand.nextInt(options));
					s.addAnswer(i, Character.toString(ans));
				}
			}
			i++;
		}
		students = studentsWithAns;
	}

	public static void main(String[] args) {
		LinkedHashSet<Question> questions = createQuestions();
		HashSet<Student> students = generateStudents();
		populateStudentAnswers(questions, students);
		VotingService voteSim = new VotingService(questions, students);
		voteSim.processQuestions();
		voteSim.printTopTen();
	}
}