package byog.PracticeSaveObjects;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


/* this class is to demonstrate how to save an object in a file, and reload it in program */
/* the following steps are required in order to save object in a file:
    1. create a class that implements the "Serializable" interface
    2. Open or create a new file using FileOutputStream
    3. Create an objectOutputStream giving the above FileoutputStream as an argument to the constructor
    4. Use objectOutputStream.writeObject method to write the object you want to the file
 */
public class SaveObject {

    private static final String filepath = "F:\\1_ML\\Java\\CS61b\\Repo\\CS61b\\proj2\\byog\\gamesave\\file.jar";

    public static void main (String[] args) {
        SaveObject objectIO = new SaveObject(); // instantiate SaveObject class
        Student student = new Student("John", "Frost", 22); // instantiate a student class
        objectIO.WriteObjectToFile(student); // call WriteObjectToFile method
    }

    public void WriteObjectToFile(Object serObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object was sucessfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
