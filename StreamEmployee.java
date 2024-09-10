package Streams;

import static java.util.stream.Collectors.counting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamEmployee {
    public static void main(String[] args) {

        List<Employee> empList = getEmpList();
        // Q How many male and female employees are there in the organization?
        Map<String, Long> genderFreq = empList.stream().collect(Collectors.groupingBy(Employee::getGender, counting()));
        System.out.println("Gender frequency=:" + genderFreq);
        System.out.println();

        // Print the name of all departments in the organization?
        List<String> deptList = empList.stream().map(x -> x.getDepartment()).distinct().toList();
        System.out.println("Department list is " + deptList);

        // What is the average age of male and female employees?
        Map<String, Double> averageAgeGenderWise = empList.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
        System.out.println("Average Age : " + averageAgeGenderWise);
        System.out.println();

        // Get the details of highest paid employee in the organization?
        Optional<Employee> employeeHighestAge = empList.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
        System.out.println("Hisghest salary employee " + employeeHighestAge);
        System.out.println();

        // Get the names of all employees who have joined after 2015?
        List<Employee> namesOfEMployeeJoinedAfter = empList.stream().filter(x -> x.getYearOfJoining() > 2015)
                .collect(Collectors.toList());
        System.out.println("Names of employee joined After 2015 =" + namesOfEMployeeJoinedAfter);
        System.out.println();

        // Count the number of employees in each department?
        Map<String, Long> numberByDept = empList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, counting()));
        System.out.println("Number of employees in each department = " + numberByDept);
        System.out.println();

        // What is the average salary of each department?
        Map<String, Double> avgSalByDept = empList.stream().collect(
                Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average salary by department is : " + avgSalByDept);
        System.out.println();

        // Get the details of youngest male employee in the product development
        // department?
        Optional<Employee> youngestEmp = empList.stream()
                .filter(x -> x.getGender().equals("Male") && x.getDepartment().equals("Product Development"))
                .collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));
        System.out.println("Youngest male employee is : " + youngestEmp);
        System.out.println();

        // Who has the most working experience in the organization?
        Optional<Employee> mostExp = empList.stream()
                .collect(Collectors.minBy(Comparator.comparing(Employee::getYearOfJoining)));
        System.out.println("Most experianced employee is " + mostExp);
        System.out.println();

        // How many male and female employees are there in the sales and marketing team?
        Map<String, Long> empFreqSalesMkt = empList.stream().filter(x-> x.getDepartment().equals("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender,counting()));
        System.out.println("Employee freq in sales and marketing :"+empFreqSalesMkt);
        System.out.println();

        //  What is the average salary of male and female employees?
        Map<String, Double> avgeAgeMF = empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Employee salary of male and female :"+avgeAgeMF);
        System.out.println();

        // List down the names of all employees in each department?
        Map<String,List<Employee>>empVsempList=empList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        Map<String,List<String>>empVsNames=new HashMap<>();
        empVsempList.entrySet().forEach(x->empVsNames.put(x.getKey(), x.getValue().stream().map(Employee::getName).toList()));
        System.out.println("Employee in each departyment "+empVsNames);
        System.out.println();
        // What is the average salary and total salary of the whole organization?
        Double avgSalary=empList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
         Optional<Employee> maxSalary = empList.stream().max(Comparator.comparing(Employee::getSalary));
        Double total=empList.stream().collect(Collectors.summingDouble(Employee::getSalary)); 

    }

    private static List<Employee> getEmpList() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
        return employeeList;
    }
}
