package Services;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AuditService {
    FileWriter out;

    public AuditService(String file) throws IOException {
        this.out = new FileWriter(file);
    }

    public void logAction(String action) throws IOException {
        out.append(action);
        out.append(", ");
        out.append(String.valueOf(LocalDate.now()));
        out.append(", ");
        out.append(String.valueOf(LocalTime.now()));
        out.append('\n');
        out.flush();

    }
}
