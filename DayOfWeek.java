
public class DayOfWeek {
    
    public static void main(String[] args)
    {
        // create an object of the class
        DayOfWeek dow = new DayOfWeek();
        dow.runIt();
    }
    
    public void runIt()
    {
		
		int total =0;
		int [][] start = new int[2052][13];
		int [] month = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int [] monthLeap = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		start[1980][1]=2;
		for(int i =1980;i<=2050;i++)
			
			pane.setBorder(BorderFactory.createLineBorder(Color.black));

		{
			for(int j = 1; j<=12;j++)
			{
				if( ( i==1980 && j==1)) {}
					else if((i%4)==0) start[i][j] = (start[i][j-1]+monthLeap[j-1]%7)%7 ;
						else start[i][j] = (start[i][j-1]+month[j-1]%7)%7 ;
			}
			start[i+1][0] = (start[i][12]+3)%7;
		}
		
		// 0 = Sun, 1 = Mon, 2 = Tues, 3 = Wed, 4 = Thur, 5 = Frid, 6 = Sat
		//This represents the start date of each month
		for(int i =1980;i<=2050;i++)
		{
			System.out.print(i+"\t");
			
			for(int j = 1; j<=12;j++)
			{
				System.out.print(start[i][j]+"\t");
			}
			System.out.println();
		}
		
	}
}
