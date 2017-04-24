  import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution 
{

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter number of operations(add/find)");
        int n = in.nextInt();
        ContactBook c= new ContactBook();
        for(int a0 = 0; a0 < n; a0++)
        {
        	System.out.println("Enter operation(add/find):");
            String op = in.next();
            String contact = in.next();
            
            if(op.equals("add"))
                {
                c.addContact(contact);}
            else if(op.equals("find"))
                {
                 System.out.println("Number of Contacts starting with the entered prefix:");
                 System.out.println(c.countWords(contact));
                }
                
        }
        in.close();
    }
}

class ContactBook
{
    private Node root = new Node('*');
    
    
    public void addContact(String contact)
        {
        Node current = root;
         for(int i=0; i<contact.length() ; i++)
             {
                
              current=current.insert(contact.charAt(i));
              if(i == (contact.length()-1))
                    current.setCompleteWord();
                  
             }
        }
     public Node findPartial(String contact)
         { 
           Node current = root;
           for(int i=0 ; i<contact.length();i++)
              current=current.find(contact.charAt(i));
           return current;
         
         }
     public int countChildWords(String partial)
     {
		int count=0;
		Node partialRef = findPartial(partial);

		 
	     HashMap<Character,Node> children = partialRef.getChildren();
	     Iterator<Character> i = children.keySet().iterator();
	     while(i.hasNext())
	     {
	    	 char currChildKey = i.next();
	    	 Node currChildRef=partialRef.find(currChildKey);
	    	 if(currChildRef.isCompleteWord())
	    		 count++;
	    	 String partial_child = partial+currChildKey; 
	    	 count = count+ countChildWords(partial_child);
	     }
	     
    	 return count;
    	 
     }
     public int countWords(String partial)
     {
    	 int count = 0;
    	 Node partialRef = findPartial(partial);
    	 if(partialRef == null)
 			return 0;
    	 else
    	 {	 
    	 if(partialRef.isCompleteWord())
    		 count++;
    	 count = count+countChildWords(partial);
    	 return count;
    	 }
     }
     
    
}

class Node
{
    private HashMap<Character,Node> children = new HashMap<Character,Node>();
    private boolean isCompleteWord;
    private char data;
    
    public Node(char data)
     {
         this.data = data;
         
     }
    
    public Node insert(char value)
     {
    	
         
    		if(children.containsKey(value))
             {return children.get(value);}
           else
             {
              Node nd = new Node(value);
              this.children.put(value,nd);
              return nd;
             }
         
    	

              
     }
    public Node find(char value)
     {
        if(children.containsKey(value))
            {return children.get(value);}
        else
            return null;
       
        
     }
    public void setCompleteWord()
     {
       isCompleteWord = true;
     }
    
    public boolean isCompleteWord()
     {
       return isCompleteWord;
     }
    public HashMap<Character,Node> getChildren()
    {
    	return children;
    }
}

