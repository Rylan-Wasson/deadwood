import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import javax.sound.sampled.Line;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser {
    final String board_pathname = "./board.xml";
    final String cards_pathname = "./card.xml";

    public ArrayList<Location> parseBoardXML(){
        ArrayList<Location> locations = new ArrayList<Location>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(board_pathname));
            NodeList set_nodes = document.getElementsByTagName("set");
            
            //parse sets
            for(int i = 0; i < set_nodes.getLength(); i++){
                ArrayList<String> adjacent_locations = new ArrayList<String>();
                ArrayList<Role> extra_roles = new ArrayList<Role>();
                int max_shot_counters = 0;

                Node cur_set_node = set_nodes.item(i);
                String name = cur_set_node.getAttributes().getNamedItem("name").getNodeValue(); //grab set name


                //grab children values
                NodeList children = cur_set_node.getChildNodes();
                for(int j = 0; j < children.getLength(); j++){
                    
                    Node sub = children.item(j);
                    switch (sub.getNodeName()) {
                        case "neighbors":
                            NodeList neighbors = sub.getChildNodes();
                            for(int x = 0; x < neighbors.getLength(); x++){ //iterate through neighbors
                                Node neighbor = neighbors.item(x);
                                if(neighbor.getNodeName().equals("neighbor")){
                                    String n_name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                                    adjacent_locations.add(n_name);
                                }
                            }
                            break;
                        case "area":
                            //TODO implement me for gui
                            break;
                        case "takes":
                            NodeList take_nodes = sub.getChildNodes();
                            for(int v = 0; v < take_nodes.getLength(); v++){ //iterate through takes
                                Node take = take_nodes.item(v);
                                if(take.getNodeType() == Node.ELEMENT_NODE){
                                    Element takeElement = (Element) take;
                                    int num = Integer.parseInt(takeElement.getAttribute("number"));
                                    if(max_shot_counters < num){
                                        max_shot_counters = num;
                                    }
                                }
                            }
                            
                            break;
                        case "parts":
                            NodeList parts = sub.getChildNodes();
                            for(int y = 0; y < parts.getLength(); y++){ //iterate through parts
                                Node part = parts.item(y);
                                if(part.getNodeType() == Node.ELEMENT_NODE){
                                    Element part_element = (Element) part;
                                    String p_name = part_element.getAttribute("name");
                                    int level = Integer.parseInt(part_element.getAttribute("level"));
                                    String line = "";
                                    
                                    NodeList line_nodes = part_element.getElementsByTagName("line"); //grab line 
                                    if(line_nodes.getLength() > 0){
                                        Element line_element = (Element) line_nodes.item(0);
                                        line = line_element.getTextContent();
                                    }
                                    Role role = new ExtraRole(p_name, line, level);
                                    extra_roles.add(role);
                                }
                                
                            }
                            break;
                        default:
                            break;
                    }
                }
                System.out.println(name+" "+adjacent_locations+" "+max_shot_counters);
                Set set = new Set(name, adjacent_locations, max_shot_counters, max_shot_counters);
                locations.add(set);
            }

            
            return locations;

        } catch (Exception e) {
            // TODO: handle exception
        }
        
        return null;
    }

    
}