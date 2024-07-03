import java.util.ArrayList;
import java.util.UUID;

public class Node {
   String name;
   UUID ID;
   int pole;
   ArrayList<Node> childs = new ArrayList<Node>();

   public Node(String name) {
      this.name = name;
      this.ID = UUID.randomUUID();
   }

   public String toString() {
      String childList = name + "\t" + ID.toString() + "\n";
      for (Node node : childs) {
         childList += "\t\t" + node.name + "\t" + node.ID.toString() + "\n";
      }
      return childList;
   }

   public String toStringAll() {
      return name + "\n" + toStringAll(1);
   }

   private String toStringAll(int step) {
      String allInfo = "";
      for (Node node : childs) {
         for (int i = 0; i < step; i++)
            allInfo += '\t';
         allInfo += node.name + "\n" + node.toStringAll(step + 1);
      }
      return allInfo;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public UUID getID() {
      return ID;
   }

   public Node addChild(String name) {
      Node node = new Node(name);
      childs.add(node);
      return node;
   }

   public Node getChild(String name) {
      int i = 0;
      while (i < childs.size() && name != childs.get(i).name)
         i++;
      if (i < childs.size())
         return childs.get(i);
      return null;
   }

   public boolean deleteChild(String name) {
      int i = 0;
      while (i < childs.size() && name != childs.get(i).name)
         i++;
      if (i >= childs.size())
         return false;
      childs.remove(i);
      return true;
   }

   public boolean deleteChild(UUID ID) {
      int i = 0;
      while (i < childs.size() && ID != childs.get(i).ID)
         i++;
      if (i >= childs.size())
         return false;
      childs.remove(i);
      return true;
   }

   public boolean deleteAllChilds() {
      if (childs.size() <= 0)
         return false;
      childs.clear();
      return true;
   }

   public int childCount() {
      return childs.size();
   }
}
