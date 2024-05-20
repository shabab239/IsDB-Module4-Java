package generic;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Shabab-1281539
 */
public class MyFileWriter<E> {
    
    private E toWrite;
    private String filePath;
    
    public void write() throws Exception {
        FileWriter writer = new FileWriter(new File(filePath), true);
        writer.write(String.valueOf(toWrite));
        writer.close();
    }
    
    public E getToWrite() {
        return toWrite;
    }
    
    public void setToWrite(E toWrite) {
        this.toWrite = toWrite;
    }
    
    public String getFilePath() {
        return filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
}
