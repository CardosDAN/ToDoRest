package backend.ToDoApp.excepton;

public class CategoryNotFoundException extends Throwable {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}