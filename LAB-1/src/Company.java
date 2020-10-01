import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Company
{
    private String Name;
    private int Students_req;
    private String Applicationstatus;
    private int Students_accepted;
    private ArrayList<String> Course_req;
    static ArrayList<Student> eligible_students=new ArrayList<>();  //made for eligible students
    public ArrayList<String> getCourse_req(){return Course_req;}
    public int getStudents_req(){return Students_req;}
    public String getName(){return Name;}
    public int getStudents_accepted(){return Students_accepted;}
    public void addStudents_accepted(){Students_accepted+=1;}
    public String getApplicationstatus()
    {
        if (Students_req==Students_accepted)
        {
            Applicationstatus="Closed";
        }
        else {
            Applicationstatus="Open";
        }
        return Applicationstatus;
    }
    public  ArrayList<Student> getEligible_students()
    {
        return eligible_students;
    }
    public void addEligible(Student stud)
    {
        eligible_students.add(stud);
    }
    public Company(String Name,ArrayList<String> Course_req,int Students_req)
    {
       this.Name=Name;
       this.Course_req=Course_req;
       this.Students_req=Students_req;
    }
    public void display_Company_details()
    {
        System.out.println(getName());
        System.out.println("Course Criteria");
        Iterator<String> Iterator = getCourse_req().iterator();
        while (Iterator.hasNext())
        {
            String name_co = Iterator.next();
            System.out.println(name_co);
        }
        System.out.println("Number Of Required Students = "+getStudents_req());

        System.out.println(getApplicationstatus());
    }
    public void display_Company_details(Company comp_d)
    {
        System.out.println(comp_d.getName());
        System.out.println("Course Criteria");
        System.out.println("Number Of Required Students = "+getStudents_req());
        System.out.println("Application Status "+comp_d.getApplicationstatus());

    }
}



