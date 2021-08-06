package dropwizard.model;

public class Student {

	public Student() {
		super();
	}
	private String name;
	private String universityId;
  private	String subjectSpecilization;
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @return the universityId
 */
public String getUniversityId() {
	return universityId;
}
/**
 * @return the subjectSpecilization
 */
public String getSubjectSpecilization() {
	return subjectSpecilization;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @param universityId the universityId to set
 */
public void setUniversityId(String universityId) {
	this.universityId = universityId;
}
/**
 * @param subjectSpecilization the subjectSpecilization to set
 */
public void setSubjectSpecilization(String subjectSpecilization) {
	this.subjectSpecilization = subjectSpecilization;
}
public Student(String name, String universityId, String subjectSpecilization) {
	super();
	this.name = name;
	this.universityId = universityId;
	this.subjectSpecilization = subjectSpecilization;
}
@Override
public String toString() {
	return "Student [name=" + name + ", universityId=" + universityId + ", subjectSpecilization=" + subjectSpecilization
			+ "]";
} 


}
