import java.util.Scanner;
public class PlotWord {
  public static void main(String[]args)
  {
    PlotWord p1 = new PlotWord();
    p1.runner();
  }
  public void runner()
  {
    Scanner s1 = new Scanner(System.in);
    System.out.print("\n\n\n");
    System.out.println("Please enter a coordinate: ");
    int xc = s1.nextInt();
    int yc = s1.nextInt();
    System.out.print("What is the word you would like to plot? ");
    s1.nextLine();
    String str = s1.nextLine();
    plotPoints(xc, yc,str);
    System.out.print("\n\n\n");
  }
  public void plotPoints(int x1, int y1, String str)
  {
    int newY = (y1*-1)+10;
    int newX = 0;
    if(x1 <=0) { 
      newX = (x1*2)+20;
    } else { 
      newX = (x1*2)+18;
    }
    for(int y=0;y<21;y++)
    {
      for(int x=0;x<41;x++)
      {
        if(x == newX && y == newY)
        {
          System.out.print(str +" ");
          x = x + str.length();
        } else if (y == 10) { 
          System.out.print("-");
        } else if (x == 20) { 
          System.out.print("|");
        } else  { 
          System.out.print(" ");
        }
      }
      System.out.println();
   
    }
  } 
}