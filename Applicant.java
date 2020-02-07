

/**
 * An object class meant to serve as a representation of Applicants
 * applying to Bookface, containing different hiring data (skills, companies, college, gpa, name, etc.)
 * and methods to manipulate said hiring data.
 *
 * @author
 * Nicole Niemiec
 * CSE 214 REC08
 * HOMEWORK #1
 *
 * @version 1
 */


import java.util.Arrays;
import java.util.Objects;
@SuppressWarnings("all")

public class Applicant implements Cloneable{

    /**
     * An array of companies the Applicant has worked for.
     */
    private String[] companyNames;

    /**
     * The name of the applicant.
     */
    private String applicantName;

    /**
     * The applicant's university GPA that is set as a double.
     */
    private double gpa;

    /**
     * The college that the applicant attended.
     */
    private String college;

    /**
     * An array of skills that the applicant has acquired.
     */
    private String[] skills;

    /**
     * Number of skills within the skill array that
     * are not null.
     */
    private int skillsNum;

    /**
     * Number of companies within the company array
     * that are not null
     */
    private int companyNum;

    /**
     * Skills written out in a String.
     */
    private String skillString = "";

    /**
     * Companies written out in a String.
     */
    private String companyString = "";

    /**
     * Mutator method for setting the number of skills
     * that an Applicant has.
     */
    public void skillsNum() {

        skillsNum = 0;

        for(int x = 0; x < HiringTable.MAX_SKILLS; x++)
        {
            if(skills[x] != null)
                skillsNum++;
        }


    }

    /**
     * Mutator method for setting the number of companies
     * that an Applicant has worked for.
     */
    public void companyNum() {

        companyNum = 0;

        for(int x = 0; x < HiringTable.MAX_COMPANIES; x++)
        {
            if(companyNames[x] != null)
                companyNum++;
        }

    }

    /**
     * Mutator method for setting the contents of skillString.
     */
    public void setSkillString() {

        skillString = "";

        for(int x = 0; x < skillsNum; x++)
        {
            skillString += this.skills[x] + "";

            if(x + 1 == skillsNum)
                skillString += "";
            else
                skillString += ", ";
        }

    }

    /**
     * Accessor method that returns the String value of skillStirng.
     * @return
     *      Returns the String value of skillString.
     */
    public String getSkillString() {

        return skillString;
    }

    /**
     * Mutator method for setting companyString.
     */
    public void setCompanyString() {

        companyString = "";

        for(int x = 0; x < companyNum; x++)
        {
            companyString += this.companyNames[x] + "";

            if(x + 1 == companyNum)
                companyString += "";
            else
                companyString += ", ";
        }
    }

    /**
     * Accessor method that returns the String value of companyString.
     * @return
     *      Returns companyString as a String.
     */
    public String getCompanyString() {

        return companyString;
    }


    /**
     * Constructs an instance of the Applicant
     * class that is empty or null.
     */
    public Applicant(){
        this.applicantName = "";
        this.companyNames = new String[3];
        this.skills = new String[3];
        this.college = "";
        this.gpa = 0;
        skillsNum();
        companyNum();
        setCompanyString();
        setSkillString();
    }

    /**
     * Constructs an instance of the Applicant class that
     * has been instantiated by the parameters.
     * @param companyNames
     *      The applicant's company experience.
     * @param applicantName
     *      The applicant's name.
     * @param gpa
     *      The applicant's college GPA.
     * @param college
     *      The applicant's college experience.
     * @param skills
     *      The applicant's skills.
     */
    public Applicant(String[] companyNames, String applicantName,
                     double gpa, String college, String[] skills) {

        this.companyNames = companyNames;
        this.applicantName = applicantName;
        this.gpa = gpa;
        this.college = college;
        this.skills = skills;
        skillsNum();
        companyNum();
        setCompanyString();
        setSkillString();

    }


    /**
     * Accessor method for getting all the companies that the
     * applicant has worked for.
     * @return
     *      The return value is the String array of
     *      all the companies that the applicant
     *      has worked for.
     */
    public String[] getCompanyNames() {

        return companyNames;

    }

    /**
     * Mutator method for setting the companies that
     * the Applicant has worked for.
     * @param companyNames
     *      An array of companies the applicant has
     *      worked for.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if the first company is empty.
     */
    public void setCompanyNames(String[] companyNames)
            throws IllegalArgumentException{

        if(companyNames[0].equals(""))
            throw new IllegalArgumentException("First company cannot be empty.");
        else
            this.companyNames = companyNames;

    }

    /**
     * Accessor method for getting the applicant's name.
     * @return
     *      Returns the applicant's name.
     */
    public String getApplicantName() {

        return applicantName;

    }

    /**
     * Mutator method for setting the applicant's name.
     * @param applicantName
     *      The name of the applicant.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if the String is empty.
     */
    public void setApplicantName(String applicantName)
            throws IllegalArgumentException{

        if(applicantName.equals(""))
            throw new IllegalArgumentException("Name cannot be empty.");
        else
            this.applicantName = applicantName;

    }

    /**
     * Accessor method for getting the applicant's GPA
     * @return
     *      Returns the GPA as a double.
     */
    public double getGpa(){

        return gpa;
    }

    /**
     * Mutator method for setting the applicant's GPA.
     * @param gpa
     *      The GPA to be set.
     * @throws IllegalArgumentException
     *      Throws the IllegalArgumentException if the parameter is
     *      less than 0 or greater than 4.
     */
    public void setGpa(double gpa) throws IllegalArgumentException {
        if(gpa >= 0 || gpa <= 4.0)
            this.gpa = gpa;
        else
            throw new IllegalArgumentException("GPA cannot be negative.");
    }

    /**
     * Accessor method for getting the applicant's college.
     * @return
     *      Returns the applicant's college.
     */
    public String getCollege() {

        return college;

    }

    /**
     * Mutator methond for setting the applicant's college.
     * @param college
     *      The name of the college.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException if the String is empty.
     */
    public void setCollege(String college) throws IllegalArgumentException{
        if(college.equals(""))
            throw new IllegalArgumentException("String cannot be empty.");
        else
            this.college = college;

    }

    /**
     * Accessor method for getting the applicant's skills.
     * @return
     *      Returns the applicant's skills.
     */
    public String[] getSkills() {

        return skills;

    }

    /**
     * Mutator method for setting the applicant's skills.
     * @param skills
     *      The skills of the applicant.
     * @throws IllegalArgumentException
     *      Throws an IllegalArgumentException when first index is empty.
     */
    public void setSkills(String[] skills) throws IllegalArgumentException{

        if(skills[0].equals(""))
            throw new IllegalArgumentException("First skill cannot be empty.");
        else
            this.skills = skills;

    }

    /**
     * A method that returns a String representation of the given Applicant
     * object.
     * @return
     *      Returns a String representation of the Applicant object.
     */
    @Override
    public String toString() {

        return "Applicant Name: " + applicantName + "\n" +
                "Applicant Applying From: " + companyString + "\n" +
                "Applicant GPA: " + gpa + "\n" +
                "Applicant College: " + college + "\n" +
                "Applicant Skills: " + skillString;

    }

    /**
     * A boolean method for comparing two objects of the Applicant
     * class. To determine if the objects are equal, the method
     * compares the attributes of the Applicant objects. The second
     * object must be typecasted to the Applicant class.
     * @param other
     *      Second object being compared.
     * @return
     *      Returns true if all of the attributes are equal.
     *      Returns false if one or more of the attributes
     *      are not equal.
     */
    @Override
    public boolean equals(Object other) {

        if(!(other instanceof Applicant))
            return false;
        else {
            Applicant newO = (Applicant) other;
            return (((Applicant) other).getApplicantName().equals(this.applicantName))
                    && (((Applicant) other).getGpa() == this.gpa)
                    && (((Applicant) other).getCollege().equals(this.college))
                    && (((Applicant) other).getCompanyString().equals(this.companyString))
                    && (((Applicant) other).getSkillString().equals(this.skillString));
        }

    }

    /**
     * A method for cloning Applicant objects without affecting
     * the original Applicant object being cloned.
     * @return
     *      A copy of the Applicant object.
     */
    @Override
    public Object clone(){

        Applicant appClone = new Applicant(this.getCompanyNames().clone(),
                this.getApplicantName(), this.getGpa(), this.getCollege(),
                this.getSkills().clone());

        return appClone;

    }



}
