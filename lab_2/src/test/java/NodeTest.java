import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class NodeTest {
   @Test
   void addition() {
      assertEquals(2, 1 + 1);
   }

   @Test
   void createTreeSpec() {
      Node node = new Node("root");
      assertEquals("root", node.getName());
   }

   @Test
   void toStringAllSpec() {
      Node root = new Node("root");
      Node firstChild = root.addChild("1");
      Node secondChild = root.addChild("2");
      Node node1 = firstChild.addChild("1.1");
      Node node2 = firstChild.addChild("1.2");
      Node node3 = secondChild.addChild("2.1");
      String s = root.toStringAll();
      String testString = "root\n\t1\n\t\t1.1\n\t\t1.2\n\t2\n\t\t2.1\n";
      assertEquals(testString, s);
   }

   @Test
   void addChildSpec() {
      Node root = new Node("root");
      Node node1 = root.addChild("node1");
      assertEquals(node1, root.getChild("node1"));
   }

   @Test
   void findChildSpec() {
      Node root = new Node("root");
      Node node1 = root.addChild("node1");
      Node node2 = root.addChild("node2");
      Node result1 = root.getChild("node1");
      Node result2 = root.getChild("node2");
      Node result3 = root.getChild("node3");
      assertNotNull(node1);
      assertNotNull(node2);
      assertEquals(node1, result1);
      assertEquals(node2, result2);
      assertNull(result3);
   }

   @Test
   void childCountSpec() {
      Node root = new Node("root");
      assertEquals(0, root.childCount());
      root.addChild("node1");
      assertEquals(1, root.childCount());
      root.addChild("node2");
      assertEquals(2, root.childCount());
   }

   @Test
   void deleteChildByNameSpec() {
      Node root = new Node("root");
      Node node1 = root.addChild("node1");
      Node node2 = root.addChild("node2");
      assertNotNull(node1);
      assertNotNull(node2);
      root.deleteChild("node1");
      assertNull(root.getChild("node1"));
      assertEquals(node2, root.getChild("node2"));
      assertEquals(1, root.childCount());
      root.deleteChild("node2");
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.childCount());
   }

   @Test
   void deleteChildByIDSpec() {
      Node root = new Node("root");
      Node node1 = root.addChild("node1");
      Node node2 = root.addChild("node2");
      assertNotNull(node1);
      assertNotNull(node2);
      root.deleteChild(node1.getID());
      assertNull(root.getChild("node1"));
      assertEquals(node2, root.getChild("node2"));
      assertEquals(1, root.childCount());
      root.deleteChild(node2.getID());
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.childCount());
   }

   @Test
   void deleteAllChildsSpec() {
      Node root = new Node("root");
      root.addChild("node1");
      root.addChild("node2");
      boolean result = root.deleteAllChilds();
      assertEquals(true, result);
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.childCount());
      result = root.deleteAllChilds();
      assertEquals(false, result);
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.childCount());
   }

}
