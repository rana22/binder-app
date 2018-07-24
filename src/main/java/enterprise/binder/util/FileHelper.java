package enterprise.binder.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipOutputStream;

@Component
public class FileHelper {

    public File createNewFile(String fileName) {
        return new File(fileName);
    }

    public Path createFiles(String filePath) throws IOException {
        return Files.createFile(Paths.get(filePath));
    }

    public Path returnPath(String sourceDirPath) {
        return Paths.get(sourceDirPath);
    }

    public ZipOutputStream getOutputStream(Path path) throws IOException {
        return new ZipOutputStream(Files.newOutputStream(path));
    }
}

