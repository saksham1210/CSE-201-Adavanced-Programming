import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Student
{
    static class StudentSortingComparator implements Comparator<Student> {
        @Override
        public int compare(Student stud1, Student stud2) {
            // for comparison
            int Test_scoreCompare=1000000000;
            if (stud1.getTest_score()==stud2.getTest_score())
            {
                Test_scoreCompare =0;
            }
            if (stud1.getTest_score()<stud2.getTest_score())
            {
                Test_scoreCompare =1;
            }
            if (stud1.getTest_score()>stud2.getTest_score())
            {
                Test_scoreCompare =-1;
            }
            int CGPACompare=1000000000;
            if (stud1.getCGPA()==stud2.getCGPA()) { CGPACompare =0; }
            if (stud1.getCGPA()<stud2.getCGPA()) { CGPACompare =1; }
            if (stud1.getCGPA()>stud2.getCGPA()) { CGPACompare =-1; }
            // 2-level comparison using if-else block
            if (Test_scoreCompare == 0) { return ((CGPACompare == 0) ? Test_scoreCompare : CGPACompare); }
            else { return Test_scoreCompare; }
        }
    }
    public static boolean isPlaced(ArrayList<Student> list)
    {
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).getPlacement_Status().compareTo("Not Placed")==0)
            {
                return false;
            }
        }
        return true;
    }
    private int Roll_number;    //it should be unique
    private float CGPA;
    private String Course;     // Branch
    private String Placement_Status;
    private int Test_score;
    private String CompanyPlaced;
    public int getRoll_number() { return Roll_number; }
    public float getCGPA() { return CGPA; }
    public int getTest_score() { return Test_score; }
    public String getCourse() { return Course; }
    public String getPlacement_Status() { return Placement_Status; }
    public void setPlacement_Status(Student stud) { stud.Placement_Status="Placed"; }
    public void setTest_score(Student stud,int test_score) { stud.Test_score=test_score; }
    public void setCompanyPlaced(Student stud,String CompanyPlaced) { stud.CompanyPlaced=CompanyPlaced; }
    public String getCompanyPlaced()
    {
        if (getPlacement_Status().compareTo("Not Placed")==0)
        {
            return " ";
        }
        else{
            return CompanyPlaced;
        }
    }
    public Student(String Course, float CGPA,int roll_number)
    {
        this.CGPA=CGPA;
        this.Course=Course;
        this.Roll_number=roll_number;
        Test_score=0;
        Placement_Status="Not Placed";
    }
    public void display_details()
    {
        System.out.println(getRoll_number()+" "+getCourse()+" "+getPlacement_Status()+" "+getCompanyPlaced());
    }
}
