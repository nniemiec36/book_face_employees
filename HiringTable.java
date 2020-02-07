import sun.tools.jconsole.MaximizableInternalFrame;

/**
 *
 *
 * @author
 * Nicole Niemiec
 * CSE 214 REC08
 * HOMEWORK #1
 *
 * @version 1
 */

public class HiringTable implements Cloneable{

    private Applicant[] applicantsArr = new Applicant[MAX_APPLICANTS];
    public static final int MAX_SKILLS = 3;
    public static final int MAX_COMPANIES = 3;
    public static final int MAX_APPLICANTS = 50;
    private int size = 0;


    /**
     * Constructs an instance of the HiringTable with no Applicant objects in it.
     * <b>Postcondition:</b>
     *      The HiringTable has been initialized to an empty list of Applicants.
     */
    public HiringTable(){
        applicantsArr = new Applicant[MAX_APPLICANTS];
    }

    /**
     * A method for determining how many Applicant objects
     * are in the HiringTable list.
     * <b>Preconditions:</b>
     *      The HiringTable has been instantiated.
     * @return
     *      Returns an int number of Applicant objects
     *      currently in the list.
     */
    public int size() {
        return size;
    }

    /**
     * A method for adding new Applicant objects into the HiringTable list.
     * <b>Preconditions:</b>
     *      This Applicant object has been instantiated and the number
     *      of Applicants in the HiringTable is less than MAX_APPLICANTS.
     * <b>Postconditions:</b>
     *      The new Applicant is now inserted at the end of the list.
     * @param newApplicant
     *      The Applicant object being added into the HiringTable list.
     * @throws FullTableException
     *      Throws the FullTableException if there is already the max
     *      number of Applicants in the HiringTable.
     */
    public void addApplicant(Applicant newApplicant) throws FullTableException{

        int count = 0;
        for(int x = 0; x < MAX_APPLICANTS; x++) {
            if (applicantsArr[x] != null && count < MAX_APPLICANTS)
                count++;
        }

        if (count == 49)
            throw new FullTableException("50 applicants already");
        else {
            applicantsArr[count] = newApplicant;

            System.out.println("Applicant " + newApplicant.getApplicantName()
                    + " has been successfully added to the hiring system.");
            size++;
        }
    }


    /**
     * Method for removing an applicant with the same name as the parameter.
     * The method searches through the array table for a matching applicant. It is then
     * removed and the following Applicants (if any) are moved up indexes.
     * <b>Precondition:</b>
     *      The HiringTable has been instantiated.
     * <b>Postconditions:</b>
     *      The Applicant with the name given has been removed from the list.
     *      Any Applicant that was in a spot after the removed Applicant are shifted upwards
     *      one spot.
     * @param name
     *      The name of the Applicant that is going to be removed.
     * @throws ApplicantNotFoundException
     *      Throws the ApplicantNotFoundException if there is no Applicant with the
     *      given name in the list.
     */
    public void removeApplicant(String name) throws ApplicantNotFoundException{
        int count = 0;
        while(!name.equals(applicantsArr[count].getApplicantName()))
        {
            count++;
        }

        if(name.equals(applicantsArr[count].getApplicantName())){
            if(size == 1)
                applicantsArr[count] = null;
            else
                applicantsArr[count] = applicantsArr[count + 1];
        }

        int move = 0;
        int index = count + 1;

        while(move < count){
            applicantsArr[index] = applicantsArr[index - 1];
            move++;
            index++;
        }

        if(move == count){
            applicantsArr[move + count] = null;
        }

        System.out.println("Applicant " + name
                + " has been successfully removed from the Hiring Table.");
        size--;

    }

    /**
     * Accessor method for retrieving and printing an applicant whose name
     * matches the key name.
     * <b>Precondition:</b>
     *      The HiringTable object has been instantiated.
     * @param name
     *      The name of the Applicant to retrieve.
     * @return
     *      Returns the Applicant with the corresponding name.
     * @throws ApplicantNotFoundException
     *      Throws the ApplicantNotFoundException exception if there is no Applicant
     *      with the given name in the list.
     */
    public Applicant getApplicant(String name) throws ApplicantNotFoundException{

        int count = 0;

        while(!applicantsArr[count].getApplicantName().equals(name) && count < size()) {
                count++;
        }

        if (applicantsArr[count].getApplicantName().equals(name))
            return applicantsArr[count];
        else
            throw new ApplicantNotFoundException("not found");

    }

    /**
     * Method for refining the Applicant search process and only printing
     * the applicants that match the specifications that are
     * being searched for. The method prints all of the applicants that
     * match these specifications.
     * <b>Preconditions:</b>
     *      The HiringTable object has been instantiated.
     * <b>Postconditions:</b>
     *      Displays a neatly formatted table of each Applicant
     *      filtered from the HiringTable.
     * @param table
     *      The table of Applicant tables that are being searched through.
     * @param company
     *      The company that the Hiring System is searching for
     *      in their Applicants.
     * @param skill
     *      The skill that the Hiring System is searching for
     *      in their Applicants.
     * @param college
     *      The college that the Hiring System is searching for
     *      in their Applicants.
     * @param gpa
     *      The minimum GPA that the Hiring System is searching for in their Applicants.
     * @throws ApplicantNotFoundException
     *      Throws ApplicantNotFoundException if the Applicant is not found.
     */
    public static void refineSearch(HiringTable table, String company,
                                    String skill, String college, double gpa)
    throws ApplicantNotFoundException {

        HiringTable table1 = new HiringTable();
        //Applicant[] app1 = new Applicant[50];

        int count = 0;

        if(gpa != 0.0){
            for(int x = 0; x < table.size(); x++) {
                if (table.applicantsArr[x].getGpa() >= gpa) {
                    Applicant temp = (Applicant) table.applicantsArr[x].clone();
                    table1.applicantsArr[count] = temp;
                    count++;
                }
            }
        }
        else {
            for (int x = 0; x < table.size(); x++) {
                try {
                    Applicant temp = (Applicant) table.applicantsArr[x].clone();
                    table1.addApplicant(temp);
                    count++;
                } catch (FullTableException ex){
                    System.out.println("Full table.");
                }
            }
        }

        int num = table1.size();

        if(!college.equals("")){

            try{
                for(int x = 0; x < num; x++){
                    if(!table1.applicantsArr[x].getCollege().equals(college)){
                        table1.removeApplicant(table1.applicantsArr[x].getApplicantName());
                    }
                }
            } catch (ApplicantNotFoundException ex){
                System.out.println(ex);
            }

        }

        num = table1.size();

        if(!skill.equals("")){
            try{
                for(int x = 0; x < num; x++){
                    if(!table1.applicantsArr[x].getSkillString().contains(skill))
                        table1.removeApplicant(table1.applicantsArr[x].getApplicantName());
                    //count--;
                }
            } catch (ApplicantNotFoundException ex){
                System.out.println(ex);
            }
        }

        num = table1.size();

        if(!company.equals("")){
            try{
                for(int x = 0; x < num; x++){
                    if(!table1.applicantsArr[x].getCompanyString().contains(company))
                        table1.removeApplicant(table1.applicantsArr[x].getApplicantName());
                }
            } catch (ApplicantNotFoundException ex){
                System.out.println(ex);
            }
        }

        table1.printApplicantTable();

    }

    /**
     * A method that clones a HiringTable object in order to make an
     * identical table which can help backups and restoration.
     * <b>Preconditions:</b>
     *      This HiringTable has been instantiated.
     * @return
     *      a clone of the HiringTable object.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {

        HiringTable tableClone = new HiringTable();
        Applicant tempApp = new Applicant();

        for(int x = 0; x < this.size(); x++){
                tempApp = (Applicant) this.applicantsArr[x].clone();
            try {
                tableClone.addApplicant(tempApp);
            } catch (FullTableException ex){
                System.out.println("Table is full");
            }
        }

        return tableClone;

    }

    /**
     * Mathod for comparing two HiringTable objects to see if the objects
     * are equal to each other.
     * <b>Precondition:</b>
     *      The HiringTable has been instantiated.
     * @param other
     *      The HiringTable object being compared to.
     * @return
     *      Returns true if the two tables are equal, false if they aren't.
     */
    @Override
    public boolean equals(Object other){


        if(!(other instanceof HiringTable))
            return false;

        if(this.size() != ((HiringTable)other).size())
            return false;

        for(int x = 0; x < this.size(); x++){
            if(!this.applicantsArr[x].equals(((HiringTable) other).applicantsArr[x]))
                return false;
        }

        return true;
    }

    /**
     * Method for printing the list of Applicants with their
     * corresponding hiring information.
     * <b>Precondition:</b>
     *      The HiringTable has been instantiated.
     * <b>Postcondition:</b>
     *      Displays a neatly formatted table of each Applicant from the
     *      HiringTable.
     */
    public void printApplicantTable() {

        System.out.printf("%-30s%-18s%-10s%-20s%-15s", "Company Name","Applicant", "GPA", "College", "Skills");
        System.out.println();

            System.out.println("-------------------------------------------" +
                    "-----------------------------------------------------------");
            for (int x = 0; x < size(); x++) {

                System.out.printf("%-30s%-18s%-10.2f%-20s%-15s", applicantsArr[x].getCompanyString(),
                        applicantsArr[x].getApplicantName(), applicantsArr[x].getGpa(), applicantsArr[x].getCollege(),
                        applicantsArr[x].getSkillString());

                System.out.println();
            }
            System.out.println();
        }

}
