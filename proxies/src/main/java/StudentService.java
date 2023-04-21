import java.util.List;

public class StudentService implements StudentServiceInt{
    @Cacheable("students")
    @Override
    public List<StudentDTO> findAllStudents() {
        System.out.println("Downloading students...");
        dummyWait();
        System.out.println("Students downloaded!");
        return List.of(
                new StudentDTO().setName("Adrian").setAge(22),
                new StudentDTO().setName("Howdy").setAge(69)
        );
    }


    private void dummyWait(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
