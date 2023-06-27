package realtimeprogram;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrganizationDemo {

    public static void main(String[] args) {

        List<Employee> employees = getListOfEmployees();
       // employees.stream().forEach(System.out::println);

        //1.  How many male and female employees are there in the organization
        System.out.println("**Gender Wise Count**");
        genderWiseCount(employees);

        System.out.println("**Name of all departments**");
        distinctDepartments(employees);

        System.out.println("**Average of Male and Female Employees**");
        averageAgeOfMaleAndFemale(employees);

        System.out.println("**Highest paid employee**");
        detailsOfHighestPaidEmployee(employees);

        System.out.println("**Name of Employees who join after 2015**");
        nameOfEmployeesWhoJoinAfter2015(employees);

        System.out.println("**Count the no of employees in each department**");
        departmentWiseCount(employees);

        System.out.println("**Average salary of each department**");
        averageSalaryOfEachDepartment(employees);

        System.out.println("**Get the longest male employee from product development department**");
        youngestEmployeeGenderAndDepartment(employees,"male","Product Development");

        System.out.println("**Most experience employee in the organization**");
        mostExperienceEmployee(employees);

        System.out.println("**How many male and female employees in sales and marketing team**");
        genderCountForDepartmentSalesAndMarketing(employees);

        System.out.println("**Average salary of male and femal employees**");
        averageSalaryGenderWise(employees);

        System.out.println("**List down the names of all employees in each department**");
        departmentWiseEmployeesName(employees);

        System.out.println("**What is the average salary and total salary of the whole organization?**");
        averageAndTotalSalaryOfOrganization(employees);

        //summarizing double
        summarizingDoubles(employees);

        System.out.println("**Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.**");
        separateBelow25AndOlder(employees);

        System.out.println("**Oldest employee details**");
        oldestEmployeeDetails(employees);

        System.out.println("Hello World");
        System.out.println("I Love you Nita!");


    }

    private static void oldestEmployeeDetails(List<Employee> employees) {
        Optional<Employee> oldestEmployeeWrapper = employees.stream().max(Comparator.comparingInt(Employee::getAge));

        if(oldestEmployeeWrapper.isPresent()){
            Employee oldestEmployee = oldestEmployeeWrapper.get();

            System.out.println("Name : "+oldestEmployee.getName());

            System.out.println("Age : "+oldestEmployee.getAge());

            System.out.println("Department : "+oldestEmployee.getDepartment());

            String s = "string data to count each character";
            findCountOfChars(s);

        }
    }

    private static void findCountOfChars(String str) {
        LinkedHashMap<String, Long> collect = Arrays.stream(str.split(""))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(s -> s, LinkedHashMap::new, Collectors.counting()));
        System.out.println(collect);
    }

    private static void separateBelow25AndOlder(@NotNull List<Employee> employees) {
        Map<Boolean, List<Employee>> partitionEmployeesByAge = employees.stream()
                .collect(Collectors.partitioningBy(employee -> employee.getAge() > 25));
        //System.out.println(partitionEmployeesByAge);
        Set<Map.Entry<Boolean, List<Employee>>> entrySet = partitionEmployeesByAge.entrySet();

        for (Map.Entry<Boolean, List<Employee>> entry : entrySet)
        {
            System.out.println("----------------------------");

            if (entry.getKey())
            {
                System.out.println("Employees older than 25 years :");
            }
            else
            {
                System.out.println("Employees younger than or equal to 25 years :");
            }

            System.out.println("----------------------------");

            List<Employee> list = entry.getValue();

            for (Employee e : list)
            {
                System.out.println(e.getName());
            }
        }
    }

    private static void summarizingDoubles(List<Employee> employees) {

        DoubleSummaryStatistics collect = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(collect.getAverage());
    }

    private static void averageAndTotalSalaryOfOrganization(List<Employee> employees) {
        DoubleSummaryStatistics summaryStatistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println("Average salary of Organization is :"+summaryStatistics.getAverage());
        System.out.println("Total organization salary "+summaryStatistics.getSum());

    }

    private static void departmentWiseEmployeesName(List<Employee> employees) {
        Map<String, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        Set<Map.Entry<String, List<Employee>>> entries = collect.entrySet();
        for(Map.Entry<String, List<Employee>> entry :  entries ){
            System.out.println("Department name "+entry.getKey());
            System.out.println("-----------------");
            List<Employee> value = entry.getValue();

            for(Employee employee : value){
                System.out.println(employee.getName());
            }
            System.out.println("-----------------");
        }



    }

    private static void averageSalaryGenderWise(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().forEach(System.out::println);
    }

    private static void genderCountForDepartmentSalesAndMarketing(List<Employee> employees) {
        employees.stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase("Sales And Marketing"))
                .collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()))
                .entrySet().forEach(System.out::println);

    }

    private static void mostExperienceEmployee(List<Employee> employees) {
       /*employees.stream()
                .min(Comparator.comparingInt(Employee::getYearOfJoining))
                .ifPresent(System.out::println);*/

        Optional<Employee> first = employees.stream()
                .sorted(Comparator.comparingInt(Employee::getYearOfJoining))
                .findFirst();
        first.ifPresent(System.out::println);
    }

    private static void youngestEmployeeGenderAndDepartment(List<Employee> employees, String gender, String department) {


        employees.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender) && employee.getDepartment().equalsIgnoreCase(department))
                .min(Comparator.comparingInt(Employee::getAge))
                .ifPresent(System.out::println);

    }

    private static void averageSalaryOfEachDepartment(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().forEach(System.out::println);
    }

    private static void departmentWiseCount(List<Employee> employees) {
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()))
                .entrySet().forEach(System.out::println);

    }

    private static void nameOfEmployeesWhoJoinAfter2015(List<Employee> employees) {

        Function<Integer, Boolean> isAfter = year -> year > 2015;
        employees.stream()
                .filter(employee -> employee.getYearOfJoining() > 2015)
                .map(Employee::getName)
                .forEach(System.out::println);

    }


    private static void detailsOfHighestPaidEmployee(List<Employee> employees) {

        Optional<Employee> detailsOfHighestPaidEmp = employees.stream()
                .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));

        Optional<Employee> max = employees.stream().max(Comparator.comparingDouble(Employee::getSalary));
        max.ifPresent(System.out::println);


        //detailsOfHighestPaidEmp.ifPresent(System.out::println);

        /*Optional<Double> highestSalary = employees
                .stream()
                .map(Employee::getSalary)
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .findFirst();

        highestSalary.ifPresent(System.out::println);*/
    }

    private static void averageAgeOfMaleAndFemale(List<Employee> employees) {

        Map<String, Double> averageAgeGenderWise = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(averageAgeGenderWise);
    }

    private static void distinctDepartments(List<Employee> employees) {

        employees.stream()
                .map(Employee::getDepartment)
                .distinct().forEach(System.out::println);

    }

    private static void genderWiseCount(List<Employee> employees) {
        Map<String, Long> genderWiseCount = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

        //genderWiseCount.entrySet().stream().forEach(System.out::println);
        System.out.println(genderWiseCount);
    }

    private static List<Employee> getListOfEmployees() {
        List<Employee> employeeList = new ArrayList<>();

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
