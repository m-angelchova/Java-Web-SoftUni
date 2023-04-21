import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        StudentServiceInt studentService = createStudentService();
        studentService.findAllStudents().forEach(System.out::println);
        System.out.println("---");
        studentService.findAllStudents().forEach(System.out::println);
    }

    private static  StudentServiceInt createStudentService(){
        return (StudentServiceInt) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                new Class[]{StudentServiceInt.class},
                new CacheableInvocationHandler(new StudentService())
        );
    }
}
