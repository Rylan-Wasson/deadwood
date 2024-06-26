import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlParser {
    final String board_pathname = "./board.xml";
    final String cards_pathname = "./cards.xml";

    public ArrayList<Location> parseBoardXML(){
        ArrayList<Location> locations = new ArrayList<Location>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(board_pathname));
            
            NodeList set_nodes = document.getElementsByTagName("set");
            /* parse through sets */
            for(int i = 0; i < set_nodes.getLength(); i++){
                ArrayList<String> adjacent_locations = new ArrayList<String>();
                ArrayList<Role> extra_roles = new ArrayList<Role>();
                ArrayList<ShotCounter> shot_counters = new ArrayList<ShotCounter>();
                int max_shot_counters = 0;
                int sx = 0;
                int sy = 0;
                int sw = 0;
                int sh = 0; // area variables for set
                Node cur_set_node = set_nodes.item(i);
                String name = cur_set_node.getAttributes().getNamedItem("name").getNodeValue(); //grab set name

                /* parse through all set children */
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
                            Element set_area = (Element) sub;
                            sx = Integer.parseInt(set_area.getAttribute("x")); 
                            sy = Integer.parseInt(set_area.getAttribute("y")); 
                            sw = Integer.parseInt(set_area.getAttribute("w")); 
                            sh = Integer.parseInt(set_area.getAttribute("h")); 
                            break;
                        case "takes":
                            NodeList take_nodes = sub.getChildNodes();
                            for(int v = 0; v < take_nodes.getLength(); v++){ //iterate through takes
                                Node take = take_nodes.item(v);
                                if(take.getNodeType() == Node.ELEMENT_NODE){
                                    max_shot_counters++;
                                    Element takeElement = (Element) take;
                                    int num = Integer.parseInt(takeElement.getAttribute("number"));
                                    Element area_element = (Element) take.getChildNodes().item(0);
                                    int x = Integer.parseInt(area_element.getAttribute("x")); 
                                    int y = Integer.parseInt(area_element.getAttribute("y")); 
                                    int w = Integer.parseInt(area_element.getAttribute("w")); 
                                    int h = Integer.parseInt(area_element.getAttribute("h")); 
                                    ShotCounter counter = new ShotCounter(num, x, y, w, h);
                                    shot_counters.add(counter);
                                }
                            }
                            
                            break;
                        case "parts":
                            NodeList parts = sub.getChildNodes();
                            for(int f = 0; f < parts.getLength(); f++){ //iterate through parts
                                Node part = parts.item(f);
                                if(part.getNodeType() == Node.ELEMENT_NODE){
                                    Element part_element = (Element) part;
                                    String p_name = part_element.getAttribute("name");
                                    int level = Integer.parseInt(part_element.getAttribute("level"));
                                    String line = "";
                                    int x = 0;
                                    int y = 0;
                                    int w = 0;
                                    int h = 0;
                                    
                                    NodeList area_nodes = part_element.getElementsByTagName("area"); //grab area
                                    if(area_nodes.getLength() > 0){
                                        Element area_element = (Element) area_nodes.item(0);
                                        x = Integer.parseInt(area_element.getAttribute("x")); 
                                        y = Integer.parseInt(area_element.getAttribute("y")); 
                                        w = Integer.parseInt(area_element.getAttribute("w")); 
                                        h = Integer.parseInt(area_element.getAttribute("h")); 
                                    }

                                    NodeList line_nodes = part_element.getElementsByTagName("line"); //grab line 
                                    if(line_nodes.getLength() > 0){
                                        Element line_element = (Element) line_nodes.item(0);
                                        line = line_element.getTextContent();
                                    }
                                    Role role = new Role(p_name, line, level, false, x, y , w, h);
                                    extra_roles.add(role);
                                }
                                
                            }
                            break;
                        default:
                            break;
                    }
                } // set children 
                Collections.sort(shot_counters, Comparator.comparing(ShotCounter::getNum));
                Set set = new Set(name, adjacent_locations, shot_counters, max_shot_counters, extra_roles, sx, sy, sw, sh);
                locations.add(set);
            } // sets

            NodeList trailer_nodes = document.getElementsByTagName("trailer");
            /* Iterate through trailers */
            for(int i = 0; i < trailer_nodes.getLength(); i++){
                ArrayList<String> adjacent_locations = new ArrayList<String>();
                Node cur_trailer_node = trailer_nodes.item(i);
                NodeList trailer_children = cur_trailer_node.getChildNodes();
                String name = "Trailer";
                int x = 0;
                int y = 0;
                int w = 0;
                int h = 0;
                /* Iterate through trailer children */
                for(int j = 0; j < trailer_children.getLength(); j++){ 
                    Node sub = trailer_children.item(j);
                    if(sub.getNodeName().equals("area")){
                        Element area_element = (Element) sub;
                        x = Integer.parseInt(area_element.getAttribute("x")); 
                        y = Integer.parseInt(area_element.getAttribute("y")); 
                        w = Integer.parseInt(area_element.getAttribute("w")); 
                        h = Integer.parseInt(area_element.getAttribute("h")); 
                    }

                    if(sub.getNodeName().equals("neighbors")){
                        NodeList neighbors = sub.getChildNodes();
                        /* Iterate through neighbors */
                        for(int f = 0; f < neighbors.getLength(); f++){
                            Node neighbor = neighbors.item(f);
                            if(neighbor.getNodeName().equals("neighbor")){
                                String n_name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                                adjacent_locations.add(n_name);
                            }
                        }
                    }
                } // trailer children
                Location trailer = new Location(name, adjacent_locations, x, y, w, h);
                locations.add(trailer);
            } // trailers

            /* Office */
            NodeList offices = document.getElementsByTagName("office");
            if(offices.getLength()>0){
                int x = 0;
                int y = 0;
                int w = 0;
                int h = 0;
                Node office = offices.item(0);
                String name = "Office";
                ArrayList<String> adjacent_locations = new ArrayList<String>();
                ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
                NodeList office_children = office.getChildNodes();
                /* Iterate through office children */
                for(int i = 0; i < office_children.getLength(); i++){
                    Node sub = office_children.item(i);

                    if(sub.getNodeName().equals("area")){
                        Element area_element = (Element) sub;
                        x = Integer.parseInt(area_element.getAttribute("x")); 
                        y = Integer.parseInt(area_element.getAttribute("y")); 
                        w = Integer.parseInt(area_element.getAttribute("w")); 
                        h = Integer.parseInt(area_element.getAttribute("h")); 
                    }
                    if(sub.getNodeName().equals("neighbors")){
                        NodeList neighbors = sub.getChildNodes();
                        /* Iterate through office neighbors */
                        for(int j = 0; j < neighbors.getLength(); j++){
                            Node neighbor = neighbors.item(j);
                            if(neighbor.getNodeName().equals("neighbor")){
                                String n_name = neighbor.getAttributes().getNamedItem("name").getNodeValue();
                                adjacent_locations.add(n_name);
                            }
                        } // office neighbors
                    } else if(sub.getNodeName().equals("upgrades")){
                        NodeList upgrade_nodes = sub.getChildNodes();
                        /* Iterate through upgrades */
                        for(int j = 0; j < upgrade_nodes.getLength(); j++){
                            int ux = 0;
                            int uy = 0;
                            int uw = 0;
                            int uh = 0;
                            Node upgrade_node = upgrade_nodes.item(j);
                            if(upgrade_node.getNodeName().equals("upgrade")){
                                Element upgrade_element = (Element) upgrade_node;
                                Element area_element = (Element) upgrade_element.getElementsByTagName("area").item(0);
                                int level = Integer.parseInt(upgrade_element.getAttribute("level"));
                                int ammount = Integer.parseInt(upgrade_element.getAttribute("amt"));
                                String currency = upgrade_element.getAttribute("currency");

                                ux = Integer.parseInt(area_element.getAttribute("x")); 
                                uy = Integer.parseInt(area_element.getAttribute("y")); 
                                uw = Integer.parseInt(area_element.getAttribute("w")); 
                                uh = Integer.parseInt(area_element.getAttribute("h")); 
                                Upgrade upgrade = new Upgrade(level, ammount, currency, ux, uy, uw, uh);
                                upgrades.add(upgrade);
                                
                            }
                        } // upgrades   
                    }
                } // office children
                CastingOffice casting_office = new CastingOffice(name, adjacent_locations, upgrades, x, y, w, h);
                locations.add(casting_office);
            } // office
            return locations;

        } catch (Exception e) {
            System.err.print("Error parsing file");
        }
        
        return null;
    }

    public ArrayList<Scene> parseCardsXML(){
        ArrayList<Scene> scenes = new ArrayList<Scene>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File(cards_pathname));
            NodeList card_nodes = document.getElementsByTagName("card");
            /* Iterate through cards */
            for(int i = 0; i < card_nodes.getLength(); i++){
                Scene scene = null;
                Node card = card_nodes.item(i);
                String img = card.getAttributes().getNamedItem("img").getNodeValue();
                String name = card.getAttributes().getNamedItem("name").getNodeValue();
                int budget = Integer.parseInt(card.getAttributes().getNamedItem("budget").getNodeValue());
                ArrayList<Role> roles = new ArrayList<Role>();
                
                NodeList card_children = card.getChildNodes();
                /* Iterate through card children */
                for(int j = 0; j < card_children.getLength(); j++){
                    
                    Node sub = card_children.item(j);
                    switch (sub.getNodeName()) {
                        case "scene":
                            Element scene_element = (Element) sub;
                            int s_num = Integer.parseInt(scene_element.getAttribute("number"));
                            String s_line = scene_element.getTextContent();
                            scene = new Scene(name, s_line, budget, s_num, img);
                            break;
                        case "part":
                            Element part_element = (Element) sub;
                            String p_name = part_element.getAttribute("name");
                            int p_level = Integer.parseInt(part_element.getAttribute("level"));
                            String p_line = part_element.getElementsByTagName("line").item(0).getTextContent();

                            NodeList areaNodes = part_element.getElementsByTagName("area");
                            Element area_element = (Element) areaNodes.item(0);
                            int x = Integer.parseInt(area_element.getAttribute("x")); 
                            int y = Integer.parseInt(area_element.getAttribute("y")); 
                            int w = Integer.parseInt(area_element.getAttribute("w")); 
                            int h = Integer.parseInt(area_element.getAttribute("h")); 
                            Role main_role = new Role(p_name, p_line, p_level, true, x, y, w, h);
                            roles.add(main_role);
                            break;
                        default:
                            break;
                    }
                }
                if(scene != null){
                    scene.setRoles(roles);
                    scenes.add(scene);
                }
                
            } // cards
            return scenes;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print("Error parsing file");
        }
        return null;
    }   
}