/**
 * A driver program used to test the Applicant and HiringTable class
 * written as part of this assignment. The user can use the commands
 * below to perform operations on the HiringTable objects
 * and Applicant objects.
 *
 * @author
 * Nicole Niemiec
 * CSE 214 REC08
 * HOMEWORK #1
 *
 * @version 1
 */

import java.util.Scanner;

public class HiringSystem {

    private static Scanner input = new Scanner(System.in);
    private static HiringTable table = new HiringTable();
    private static HiringTable tableClone = new HiringTable();


    /**
     * Method where the program runs.
     * @param args
     *      Methods to run are found in args.
     */
    public static void main(String[] args){

        main_menu();

    }

    /**
     * Method to call other methods in regards to what the input user chose.
     */
    public static void main_menu(){

        String command;

        do {

            System.out.println("Main Menu");
            System.out.println("(A) \t Add Applicant");
            System.out.println("(R) \t Remove Applicant");
            System.out.println("(G) \t Get Applicant");
            System.out.println("(P) \t Print List");
            System.out.println("(RS) \t Refine Search");
            System.out.println("(S) \t Size");
            System.out.println("(D) \t Backup");
            System.out.println("(CB) \t Compare Backup");
            System.out.println("(RB) \t Revert Backup");
            System.out.println("(Q) \t Quit");


            //Scanner input = new Scanner(System.in);

            System.out.println("Please enter a command: ");
            command = input.nextLine().toUpperCase();

            switch (command){
                case "A" :
                    main_addApplicant();
                    break;
                case "R":
                    main_removeApplicant();
                    break;
                case "G" :
                    main_getApplicant();
                    break;
                case "P" :
                    main_printList();
                    break;
                case "RS":
                    main_refineSearch();
                    break;
                case "S" :
                    main_getSize();
                    break;
                case "D" :
                    main_backup();
                    break;
                case "CB" :
                    main_compareBackup();
                    break;
                case "RB" :
                    main_revertBackup();
                    break;
                case "Q" :
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    //main_menu();

            }

        }
        while(!command.equalsIgnoreCase("Q"));

        input.close();
    }

    /**
     * Method that calls the method to add an applicant to
     * the hiring table.
     */
    public static void main_addApplicant(){
        String applicantName, college;
        String[] skills = new String[3], companyNames = new String[3];
        double gpa;
        System.out.println("Enter Applicant Name: ");
        applicantName = input.nextLine();
        if(!Character.isLetter(applicantName.charAt(0))) {
            System.out.println("Invalid input.");
            main_menu();
        }
        System.out.println("Enter Applicant GPA: ");
        gpa = input.nextDouble();
        while(gpa <= 0 || gpa >= 4){
            System.out.println("Invalid input: GPA must be greater than 0.0 and less than 4.0");
            main_menu();
        }
        input.nextLine();
        System.out.println("Enter Applicant College: ");
        college = input.nextLine();
        if(!Character.isLetter(college.charAt(0))) {
            System.out.println("Invalid input.");
            main_menu();
        }

        int x = 0;

        while(x < 3){
            System.out.println("Enter up to " + (3 - x) + " Companies: ");
            String companyX = input.nextLine();
            if(!companyX.equals("")){
                companyNames[x] = companyX;
                x++;
            }
            else if(companyX.equals("") && x == 0){
                System.out.println("Error: Applicant must have at least one company.");
                x = 0;
            }
            else
                x = 3;
        }

        x = 0;

        while(x < 3){
            System.out.println("Enter up to " + (3 - x) + " Skills: ");
            String skillX = input.nextLine();
            if(!skillX.equals("")){
                skills[x] = skillX;
                x++;
            }
            else if(skillX.equals("") && x == 0) {
                System.out.println("Error: Applicant must have at least one skill.");
                x = 0;
            }
            else
                x = 3;
        }

        Applicant app = new Applicant(companyNames, applicantName,
                                        gpa, college, skills);

        try {
            table.addApplicant(app);
        }
        catch (FullTableException ex) {
           System.out.println("Full table.");
            main_menu();
        }




    }

    /**
     * Method that calls the method that gets the information
     * of searched applicant.
     */
    public static void main_getApplicant(){
        System.out.println("Enter Applicant Name: ");
        String name = input.nextLine();
        try {
           Applicant temp = table.getApplicant(name);
           System.out.println(temp.toString());
           System.out.println();
        } catch (ApplicantNotFoundException ex){
            System.out.println("Applicant not found.");
            main_menu();
        }
    }

    /**
     * Method that calls the method that removes the applicant
     * from the hiring database.
     */
    public static void main_removeApplicant(){
        System.out.println("Enter Applicant Name: ");
        String name = input.nextLine();
        try {
            table.removeApplicant(name);
            System.out.println();
        } catch (ApplicantNotFoundException ex){
            System.out.println("Applicant not found.");
            main_menu();
        }
    }

    /**
     * Method that calls the method that prints the list of Applicants
     * and their information.
     */
    public static void main_printList(){

        table.printApplicantTable();

    }

    /**
     * Method that calls the method that searches through the HiringTable and
     * only prints the applicants with the matching information.
     */
    public static void main_refineSearch(){
        System.out.println("Enter a company to filter for: ");
        String companyFilter = input.nextLine();
        System.out.println("Enter a skill to filter for: ");
        String skillFilter = input.nextLine();
        System.out.println("Enter a college to filter for: ");
        String collegeFilter = input.nextLine();
        System.out.println("Enter the minimum GPA to filter for: ");
        String gpaString = input.nextLine();
        double gpaFilter;
        if(gpaString.equals("")){
            gpaFilter = 0;
        }
        else {
            gpaFilter = Double.parseDouble(gpaString);
            if(gpaFilter > 4.0 || gpaFilter < 0.0) {
                System.out.println("Invalid GPA.");
                main_menu();
            }
        }

        try{
            table.refineSearch(table, companyFilter, skillFilter,
                    collegeFilter, gpaFilter);
        } catch (ApplicantNotFoundException ex){
            System.out.println(ex);
            main_menu();
        }

    }

    /**
     * Method that prints out the number of Applicants in
     * the hiring table.
     */
    public static void main_getSize(){

        System.out.println(table.size());

    }

    /**
     * Method that creates a clone of the current HiringTable.
     */
    public static void main_backup(){
        try {
            tableClone = (HiringTable) table.clone();
            System.out.println("Successfully created backup.");
        } catch (CloneNotSupportedException ex){
            System.out.println("Clone not successful.");
            main_menu();
        }
    }

    /**
     * Method that sets the current table to a clone of the backed up table.
     */
    public static void main_revertBackup(){
        try {
            table = (HiringTable) tableClone.clone();
            System.out.println("Successfully reverted to the backup copy.");
        } catch (CloneNotSupportedException ex){
            System.out.println("Clone not successful.");
            main_menu();
        }
    }

    /**
     * Method that compares the backup table and the current table to see if
     * they have the same contents and are equal.
     */
    public static void main_compareBackup(){
        //table.equals(tableClone);

        if(table.equals(tableClone))
            System.out.println("Current list is the same as the backup copy.");
        else
            System.out.println("Current list is not the same as the backup copy.");
    }


}
