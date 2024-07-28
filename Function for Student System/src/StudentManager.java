import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private ArrayList<Student> students;
    public static final String FILE_NAME = "student.csv";
    public static final String COMMA = ",";

    public StudentManager(){
        ArrayList<Student> studentList = new ArrayList<>();
        this.students = studentList;
    }
    public boolean isIDInProductList (int id){
        if (!students.isEmpty()){
            for (Student student: students) {
                if (student.getId() == id)
                    return true;
            }
        }
        return false;
    }
    public Student inputStudentData() {
        int id;
        int age;
        String name;
        String sex;
        String address;
        double gpa;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Student name: ");
        name = scanner.nextLine();
        boolean isId = true;
        do {
            System.out.println("Student ID: ");
            id = scanner.nextInt();
            if (isIDInProductList (id)) {
                System.out.println("ID da ton tai");
            } else {
                isId = false;
            }
        } while (isId);
        System.out.println("Student Age: ");
        age = scanner.nextInt();
        System.out.println("Student Sex: ");
        sex = scanner.nextLine();
        System.out.println("Student Address: ");
        address = scanner.nextLine();
        System.out.println("Student GPA: ");
        gpa = scanner.nextDouble();

        Student student = new Student(id, name, age, sex, address, gpa);
        return student;
    }
    public ArrayList<Student> getStudents () { return students; }
    public void addStudent (Student student) {
        String line = null;
        String sex = null;
        line = student.getId()+ COMMA+ student.getName()+ COMMA+ student.getAge()+ COMMA+ student.getSex(sex)+ COMMA+ student.getAddress()+ COMMA+ student.getGpa();
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public Student findStudentFromFile (int id){
        List<Student> studentList = readStudentsFromFile();
        if(!studentList.isEmpty()){
            for (Student student: studentList) {
                if (student.getId() == id){
                    return student;
                }
            }
        }
        return null;
    }
    private void updateStudentById (int id){
        List<Student> studentList = readStudentsFromFile();
        for (int i=0; i< studentList.size(); i++){
            if (studentList.get(i).getId()== id){
                String name;
                int age;
                String sex;
                String address;
                double gpa;
                Scanner scanner = new Scanner(System.in);
                System.out.println("Student Mame: ");
                name = scanner.nextLine();
                System.out.println("Student Age: ");
                age = scanner.nextInt();
                System.out.println("Student Sex: ");
                sex = scanner.nextLine();
                System.out.println("Student Address: ");
                address = scanner.nextLine();
                System.out.println("Student GPA: ");
                gpa = scanner.nextDouble();
                studentList.get(i).setName(name);
                studentList.get(i).setAge(age);
                studentList.get(i).getSex(sex);
                studentList.get(i).setAddress(address);
                studentList.get(i).setGpa(gpa);
            }}

        for (int i = 0; i < studentList.size(); i++){
            String line = null;
            String sex = null;
            line = studentList.get(i).getId()+ COMMA+ studentList.get(i).getName()+ COMMA+ studentList.get(i).getAge()+ COMMA+ studentList.get(i).getSex(sex)+ COMMA+ studentList.get(i).getAddress()+ COMMA+ studentList.get(i).getGpa();
            try {
                FileWriter fileWriter;
                if (i==0) {
                    fileWriter = new FileWriter(FILE_NAME, false);
                }else {
                    fileWriter = new FileWriter(FILE_NAME,true);
                }

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }}


    private void showStudent(Student student){
        String sex = null;
        System.out.println("Student ID:"+student.getId() +"Student Name: "+ student.getName()+ "Student Age: "+ student.getAge()+ "Student Sex: "+ student.getSex(sex)+ "Student Address: "+ student.getAddress()+ "Student GPA: "+ student.getGpa());
    }
    private void showStudents(List<Student> studentList){
        for (Student student: studentList){
            String sex = null;
            System.out.println("Student ID:"+student.getId() +"Student Name: "+ student.getName()+ "Student Age: "+ student.getAge()+ "Student Sex: "+ student.getSex(sex)+ "Student Address: "+ student.getAddress()+ "Student GPA: "+ student.getGpa());
        }
    }

    private int inputId() {
        System.out.println("Student ID: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return id;
    }

    private List<Student> readStudentsFromFile(){
        List<String> listLine = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                listLine.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line: listLine){
            String[] lineSplit = line.split(COMMA);
            System.out.println(lineSplit);
            Student student = new Student(Integer.parseInt(lineSplit[0]), lineSplit[1], Integer.parseInt(lineSplit[2]), lineSplit[3], lineSplit[4], Double.parseDouble(lineSplit[5]));
            studentList.add(student);
        }
        return studentList;}

    public void menu(){
        char choice = '?';
        while (choice!='0'){
            System.out.println("--Menu--");
            System.out.println("1. Xem danh sach sinh vien: ");
            System.out.println("2. Them moi: ");
            System.out.println("3. Cap nhat: ");
            System.out.println("4. Xoa:  ");
            System.out.println("5. Thoat ");
            System.out.println("Nhap vao lua chon: ");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case '1':{
                    List<Student> studentList = readStudentsFromFile();
                    showStudents(studentList);
                    break;}
                case '2': {
                    Student student = inputStudentData();
                    addStudent(student);
                }
                case'3':{
                    int id = inputId();
                    updateStudentById(id);
                    break; }

                case '4': {
                    System.out.println("Nhap ID xoa");
                    int id = inputId();
                    Student student = findStudentFromFile(id);
                    if (student != null) {
                        showStudent(student);
                    }else {
                        System.out.println("khong tim thay");
                    }
                    break;
                }


            }
        }
    }}







