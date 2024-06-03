package org.example.navalbattle.model;

import java.io.*;

public class NavalBattleSerialize {

    public static void serialize(NavalBattle navalBattle) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("navalBattle.nvl"))){
            out.writeObject(navalBattle);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return in.readObject();
        }
    }
}
