package byog.Core;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ObjectIO {
    private static final String filepath = "F:\\1_ML\\Java\\CS61b\\Repo\\CS61b\\proj2\\byog\\gamesave\\file.txt";

    /* Serialize an object and save to filepath */
    public void writeObjectToFile(Object serObj) {
        try {
            FileOutputStream fileOut = new FileOutputStream (filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream (fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            System.out.println("The Object was successfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /* De-serialize an file to Object */
    public Object readObjectFromFile() {
        Object obj = new Object();
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            obj = objectIn.readObject();
            System.out.println("The Object was sucessfully retrieved");

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return obj;
    }
}
