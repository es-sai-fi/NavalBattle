/**
 * @author Jose Martínez - Jhorman Gómez - Esteban Gómez
 * @version final
 */
package org.example.navalbattle.model;

import java.io.*;

/**
 * Utility class for serializing and deserializing the NavalBattle game state.
 * This class provides methods to save the game state to a file and to restore it from a file.
 */
public class NavalBattleSerialize {

    /**
     * Serializes the given NavalBattle object to a file named "navalBattle.nvl".
     *
     * @param navalBattle the NavalBattle object to serialize
     * @throws IOException if an I/O error occurs during serialization
     */
    public static void serialize(NavalBattle navalBattle) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("navalBattle.nvl"))){
            out.writeObject(navalBattle);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Deserializes a NavalBattle object from the specified file.
     *
     * @param fileName the name of the file from which to deserialize the NavalBattle object
     * @return the deserialized NavalBattle object
     * @throws IOException if an I/O error occurs during deserialization
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return in.readObject();
        }
    }
}
