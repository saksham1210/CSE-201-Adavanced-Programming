import java.util.*;
public class main {
    static ArrayList<Company> company_list = new ArrayList<>(); //arraylist to store all company objects
    static ArrayList<Student> students_list = new ArrayList<>(); //arraylist which stores objects of class student
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        for (int i = 0; i < N; i++)
        {
            float cg = input.nextFloat();
            String branch = input.next();
            int roll_number = (i + 1);
            Student stud = new Student(branch, cg, roll_number); //object named "stud" initialized
            students_list.add(stud);  //added student object to the arraylist
        }
        while (Student.isPlaced(students_list) == false)
        {
            int query = input.nextInt();
            if (query == 1)
            {
                String company_name = input.next();
                System.out.print("Number Of Eligible Courses = ");
                int numberOF_req = input.nextInt();
                ArrayList<String> req_course = new ArrayList<>();
                for (int i = 0; i < numberOF_req; i++) {
                    String name_req = input.next();
                    req_course.add(name_req);
                }
                System.out.print("Number Of Required Students = ");
                int numberOFstudents = input.nextInt();
                Company comp = new Company(company_name, req_course, numberOFstudents);//made a new object of company
                company_list.add(comp);
                comp.display_Company_details();
                System.out.println("Enter scores for technical round");
                Iterator<Student> itr_eli=students_list.iterator();
                while(itr_eli.hasNext())
                {
                    Student next_eli=itr_eli.next();
                    if(comp.getCourse_req().contains(next_eli.getCourse()) && next_eli.getPlacement_Status().compareTo("Not Placed")==0 )
                    {

                        System.out.println("Enter marks for roll no. "+next_eli.getRoll_number());
                        int test_marks=input.nextInt();
                        next_eli.setTest_score(next_eli,test_marks);
                    }
                }
            }

            if (query == 2)
            {
                Iterator<Student> Iterator_s = students_list.iterator();
                System.out.println("Accounts removed for: ");
                while (Iterator_s.hasNext())
                {
                    Student next_s=Iterator_s.next();
                    String p_status = next_s.getPlacement_Status();
                    if (p_status.compareTo("Placed") == 0)
                    {
                        System.out.println(next_s.getRoll_number());
                        Iterator_s.remove();
                    }
                }
            }

            if (query == 3)
            {
                Iterator<Company> Iterator_c = company_list.iterator();
                System.out.print("Accounts removed for: ");
                while (Iterator_c.hasNext())
                {
                    Company next_c=Iterator_c.next();
                    String p_status = next_c.getApplicationstatus();
                    if (p_status.compareTo("Closed") == 0)
                    {
                        System.out.println(next_c.getName());
                        Iterator_c.remove();
                    }
                }
            }

            if (query == 4)
            {
                Iterator<Student> itr_u = students_list.iterator();
                int count=0;
                while (itr_u.hasNext())
                {
                    Student next_u=itr_u.next();
                    String p_status = next_u.getPlacement_Status();
                    if (p_status.compareTo("Not Placed") == 0)
                    {
                        count++;
                    }
                }
                System.out.println("No. of students left : "+count);
            }

            if (query == 5)
            {
                Iterator<Company> itr_o = company_list.iterator();
                while (itr_o.hasNext())
                {
                    Company next_o=itr_o.next();
                    if (next_o.getApplicationstatus().compareTo("Open")==0)
                    {
                        System.out.println(next_o.getName());
                    }
                }
            }

            if (query == 6) {
                String c_name = input.next();
                Iterator<Student> itr_e = students_list.iterator();
                while (itr_e.hasNext())
                {
                    int k=-1;
                    for (int i=0;i<company_list.size();i++)
                    {
                        k+=1;
                        if (company_list.get(i).getName().compareTo(c_name) == 0)
                        {
                            break;
                        }
                    }
                    Student next_e = itr_e.next();
                    if (next_e.getPlacement_Status().compareTo("Not Placed") == 0 && company_list.get(k).getCourse_req().contains(next_e.getCourse()))
                    {
                        Company.eligible_students.add(next_e);
                    }
                    Collections.sort(Company.eligible_students, new Student.StudentSortingComparator());
                    while (company_list.get(k).getStudents_accepted() != company_list.get(k).getStudents_req() && next_e.getPlacement_Status().compareTo("Not Placed") == 0)
                    {
                        next_e.setPlacement_Status(next_e);
                        next_e.setCompanyPlaced(next_e, c_name);
                        System.out.println("Roll no. of Students Selected");
                        System.out.println(next_e.getRoll_number());
                        company_list.get(k).addStudents_accepted();
                    }
                }
            }
            if (query == 7)
            {
                String comp_name=input.next();
                for(int i=0;i<company_list.size();i++)
                {
                    if (company_list.get(i).getName().compareTo(comp_name)==0)
                    {
                        System.out.println(company_list.get(i).getName());
                        System.out.println("Course Criteria");
                        for (int m=0;m<company_list.get(i).getCourse_req().size();m++)
                        {
                            System.out.println(company_list.get(i).getCourse_req().get(m));
                        }
                        System.out.println("Number Of Required Students = "+company_list.get(i).getStudents_req());
                        System.out.println("Application Status : "+company_list.get(i).getApplicationstatus());
                        break;
                    }
                }
            }

            if (query == 8)
            {
                int stu_roll=input.nextInt();
                for(int i=0;i<students_list.size();i++)
                {
                    if (students_list.get(i).getRoll_number()==stu_roll)
                    {
                        System.out.println("Roll no. :"+students_list.get(i).getRoll_number());
                        System.out.println("CGPA : "+students_list.get(i).getCGPA());
                        System.out.println("Course : "+students_list.get(i).getCourse());
                        System.out.println("Placement Status : "+students_list.get(i).getPlacement_Status());
                        if (students_list.get(i).getPlacement_Status().compareTo("Placed")==0)
                        {
                            System.out.println(students_list.get(i).getCompanyPlaced());
                        }
                        break;
                    }
                }
            }

            if (query == 9)
            {
                int roll =input.nextInt();
                boolean flag=false;
                for (int j=0;j<students_list.size();j++)
                {
                    if (students_list.get(j).getRoll_number()==roll)
                    {
                        flag=true;
                    }
                }
                System.out.println(flag);
                if (flag==true)
                {
                    for(int i=0;i<company_list.size();i++)
                    {
                        for (int m=0;m<company_list.get(i).getEligible_students().size();m++)
                        {
                            System.out.print(company_list.get(i).getEligible_students().get(m).getRoll_number()+" ");
                            System.out.println(company_list.get(i).getEligible_students().get(m).getTest_score());
                        }
                    }
                }
                else
                {
                    System.out.println("Kuch to hai error");
                }
            }
        }
    }
}
