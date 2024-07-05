package ru.summer2024.novikov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class NodeTest {

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
      firstChild.addChild("1.1");
      firstChild.addChild("1.2");
      secondChild.addChild("2.1");
      String s = root.toStringAll();
      String testString = "root\n\t1\n\t\t1.1\n\t\t1.2\n\t2\n\t\t2.1\n";
      assertEquals(testString, s);
   }

   @Test
   void toHTMLSpec() {
      Node root = new Node("root");
      Node firstChild = root.addChild("1");
      Node secondChild = root.addChild("2");
      firstChild.addChild("1.1");
      firstChild.addChild("1.2");
      secondChild.addChild("2.1");
      String s = root.toHTML();
      String testString = "root<ul><li>1<ul><li>1.1<ul></ul></li><li>1.2<ul></ul></li></ul></li><li>2<ul><li>2.1<ul></ul></li></ul></li></ul>";
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
      assertNotNull(node1);
      assertNotNull(node2);
      assertEquals(node1, root.getChild("node1"));
      assertEquals(node2, root.getChild("node2"));
      assertNull(root.getChild("node3"));
   }

   @Test
   void childCountSpec() {
      Node root = new Node("root");
      assertEquals(0, root.count());
      root.addChild("node1");
      assertEquals(1, root.count());
      root.addChild("node2");
      assertEquals(2, root.count());
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
      assertEquals(1, root.count());
      root.deleteChild("node2");
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.count());
   }

   @Test
   void deleteChildByIDSpec() {
      Node root = new Node("root");
      Node node1 = root.addChild("node1");
      Node node2 = root.addChild("node2");
      assertNotNull(node1);
      assertNotNull(node2);
      root.deleteChild(node1.getId());
      assertNull(root.getChild("node1"));
      assertEquals(node2, root.getChild("node2"));
      assertEquals(1, root.count());
      root.deleteChild(node2.getId());
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.count());
   }

   @Test
   void deleteAllChildsSpec() {
      Node root = new Node("root");
      root.addChild("node1");
      root.addChild("node2");
      boolean result = root.deleteAllChildren();
      assertEquals(true, result);
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.count());
      result = root.deleteAllChildren();
      assertEquals(false, result);
      assertNull(root.getChild("node1"));
      assertNull(root.getChild("node2"));
      assertEquals(0, root.count());
   }

}
