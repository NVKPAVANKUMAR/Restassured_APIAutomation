package Utils;

import com.github.javafaker.Faker;

public class RestUtils {
    private String name;
    private String job;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String emailID;
    private String salary;
    private String age;

    static Faker faker = new Faker();

    public static int getAge() {
        return faker.number().numberBetween(10,80);
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static String getSalary() {
        return faker.number().digits(5);
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static String getUserName() {
        return faker.name().username();
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static String getPassword() {
        return faker.lorem().characters(8,16,true,true);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static String getEmailID() {
        return faker.funnyName().name() + "@gmail.com";
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }


    public static String getName() {
        return faker.name().name();
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getJob() {
        return faker.job().title();
    }

    public void setJob(String job) {
        this.job = job;
    }
}
