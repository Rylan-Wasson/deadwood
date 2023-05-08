import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        XmlParser p = new XmlParser();
        ArrayList<Scene> scenes = p.parseCardsXML();
        
        GameBoard gb = new GameBoard(p.parseBoardXML());
        gb.distributeScenes(scenes);
    }
}
