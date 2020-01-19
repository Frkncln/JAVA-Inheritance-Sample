import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
*@author furkan celen
*/

public class Main{
	

	public static	void main(String args[])throws FileNotFoundException{
		Scanner input = new Scanner( System.in );
		int sec;
		System.out.println("1:Boardarray2d      2:Boardarrya1d  3:Checkmoves\n");
		System.out.println("Please select the number of the typename for a little testing programs \n");
		            
		System.out.println("Enter number: ");
		sec=input.nextInt();
		

		if(sec ==1){
			BoardArray2D twod = new BoardArray2D();
			String fname = "shapefile1.txt";
			String fname2;
			System.out.println("------------CURRENT CONFIGURATION-----------\n");
			try{
				twod.readFromFile(fname);
			}catch (FileNotFoundException ex)  
	    			{	System.out.println("Wrong file name");
					System.out.println("exiting,bye");
					System.exit(0);			
	    				}			
			int i,j,x,y,o=0,resetcheck=0,movetemp=0,printcheck=1;
			char secim;
			twod.reset(resetcheck);
			System.out.println("--------------------------------------------");
		
			
			while(true){
			      System.out.println("\nM move \n"
				     +"S setsize\n"
				     +"E Asks a file name and saves the current board configuration as a loadable shapefile\n"
				     +"O Asks a file name and loads the current board configuration from the shapefile\n"
				     +"N number of moves \n" +"B boards created so far\n" +"L lastmove board did\n"
				     +"Q Quits the game\n"
				     + "Please enter your CHOICE: ") ; 

				secim=input.next().charAt(0);
				//System.out.println("\n");
			if( secim == 'M'  || secim == 'm' ){
					twod.move(printcheck);}
			else if(secim=='E' || secim=='e' ){
					twod.writeToFile();
					}
			else if(secim=='N' || secim=='n' ){
					System.out.println("num of moves = "+twod.numberofmoves());
					}
			else if(secim=='B' || secim=='b' ){
					System.out.println("num of boards = "+twod.NumberofBoards());
					}
			else if(secim=='L' || secim=='l' ){
					System.out.println("last move we did = "+twod.lastMove());
					}
			else if(secim=='S' || secim=='s' ){
					twod.setSize();
				}

			else if(secim=='O' || secim=='o' ){
				Scanner in = new Scanner( System.in );
				System.out.println("enter fname");
				fname2=in.nextLine();
				try{
					twod.readFromFile(fname2);
				}catch (FileNotFoundException ex){	
						System.out.println("Wrong file name");
						System.out.println("exiting,bye");
						System.exit(0);
    				}		
				resetcheck=0;
				twod.reset(resetcheck);
			}

			else if(secim == 'Q' || secim == 'q'){
				System.out.println("exiting,bye");
				System.exit(0);
			}

			else{
				System.out.println("WRONG choice my friend,try AGAIN\n");
				}
			}//while curl



		}


		

		else if(sec ==2){
			BoardArray1D  oned =new BoardArray1D();	
			String fname = "shapefile1.txt";
			String fname3;
			char secim;
			int i,resetcheck=1,movetemp=0,printcheck=1;
			System.out.println("----CURRENT CONFIGURATION----\n");
			try{
					oned.readFromFile(fname);
				}catch (FileNotFoundException ex){	
						System.out.println("Wrong file name");
						System.out.println("exiting,bye");
						System.exit(0);			
    				}
				oned.reset(resetcheck);
				System.out.println("-----------------------------");
			
			while(true){
			      System.out.println("\nM move \n"
				     +"S setsize\n"
				     +"E Asks a file name and saves the current board configuration as a loadable shapefile\n"
				     +"O Asks a file name and loads the current board configuration from the shapefile\n"
				     +"N number of moves \n" +"B boards created so far\n" +"L lastmove board did\n"	  
				     +"Q Quits the game\n"
				     + "Please enter your CHOICE: " );
				secim=input.next().charAt(0);
				//System.out.println("\n");
				if( secim == 'M'  || secim == 'm' ){
					oned.move(printcheck);}

				else if(secim=='E' || secim=='e'){
					oned.writeToFile();
					}
				
				else if(secim=='S' || secim=='s' ){
					oned.setSize();
					}
				else if(secim=='N' || secim=='n' ){
					System.out.println("num of moves = "+oned.numberofmoves());
					}
				else if(secim=='B' || secim=='b' ){
					System.out.println("num of boards = "+oned.NumberofBoards());
					}
				else if(secim=='L' || secim=='l' ){
					System.out.println("last move we did = "+oned.lastMove());
					}
				else if(secim=='O' || secim=='o' ){
					Scanner in2 = new Scanner( System.in );
					System.out.println("enter filename");
					fname3=in2.nextLine();	

					try{
						oned.readFromFile(fname3);
					}catch (FileNotFoundException ex){	
						System.out.println("Wrong file name");
						System.out.println("exiting,bye");
						System.exit(0);			
    					}			
					resetcheck=1;
					oned.reset(resetcheck);
				}

				else if(secim == 'Q' || secim == 'q'){
					System.out.println("exiting,bye");
					System.exit(0);
				}

				else{
					System.out.println("WRONG choice my friend,try AGAIN\n");
				}
			     }//while curl
			}//elseifcurl
			else if(sec==3){
				int movenum;
				//Scanner input = new Scanner( System.in );
				BoardArray2D  twod =new BoardArray2D();
				try{
					twod.readFromFile("shapefile1.txt");
				}catch (FileNotFoundException ex){	
						System.out.println("Wrong file name");
						System.out.println("exiting,bye");
						System.exit(0);			
    				}
				int printcheck =1;
				System.out.println("Enter move number: ");
			        movenum = input.nextInt();
				AbstractBoard moves []= new AbstractBoard[movenum];
				//BoardArray2D  twod =new BoardArray2D();
				for(int i=0;i<movenum;i++){
				twod.move(printcheck);
				moves[i]=twod;		
				}
				boolean check =  CheckMoves(moves,movenum);
					System.out.println("check is: "+check);
				}
			else{	
					System.out.println("WRONG choice my friend,try AGAIN and choice 1 or 2!!!\n");
				}
			
		


	}//end of the main


	
	public static	boolean CheckMoves(AbstractBoard  moves[],int size){
		int i=1,j,x1,x2,y1,y2;
		boolean flag=true ;

		while(i<size){//we compare the bb(blank) index,we use coordinates like  (y,x) ,
			x1= moves[i].getx();	
			x2= moves[i-1].getx();
			y1= moves[i].gety();
			y2= moves[i-1].gety();
			
				if((x1 -x2) <= 1 && (x1 -x2) >= -1  && y1 == y2)
						flag = true;
			
				else if((y1 -y2) <=1 && (y1 -y2) >= -1  && x1 == x2)
						flag = true;
						
				else 
					flag=false;

				i++;
		}

		return flag;
	}	


}//end of the Main class
