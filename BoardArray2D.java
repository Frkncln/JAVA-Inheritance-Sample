import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


/**
*@author furkan celen
*/

 public class BoardArray2D extends AbstractBoard{
	private int  [][] mtrx2d;
	private int  [][] goalmtrx2d;

		/**
		*default constructor
			*/
	public BoardArray2D(){//	
		height = 9;
		width = 9;
		mtrx2d = new int  [height][width];
		goalmtrx2d = new int  [height][width];
		System.out.println("2d construcor boardnum= "+boardnum);
		}
	
		


	public BoardArray2D (int h,int w,char lmove,int nummove){
			height = h; width = w; lastmove=lmove; movenum =nummove;
			mtrx2d = new int  [height][width];
			goalmtrx2d = new int  [height][width];
		}

	public BoardArray2D(BoardArray2D obj2d)
			 {
				this(obj2d.height,obj2d.width ,obj2d.lastmove,obj2d. movenum);
			 	mtrx2d = new int  [height][width];

				for (int i = 0; i < height; i++){
					for (int j = 0; j < width; j++){
				 	mtrx2d[i][j] = obj2d.mtrx2d[i][j];
				 	}
				}
			}

		
	/*	tostring :this method send 2d matrix to string ,and returns it
		*@return matrix as a string
			**/
	public String toString(){ //printing matrix
	   	 int i,j,k=0;
		String s = "";	
		for (i = 0; i < height; i++){		
			for (j = 0; j < width; j++){
				if(mtrx2d[i][j]== 99){//if it is 99 this means bb
					s += "bb ";
					x=j;
					y=i;
					}
				else{
	     			      if((mtrx2d[i][j]/10)==0){
					s += "0"+mtrx2d[i][j];
					if(j!=width-1){s+= " ";}//we dont want to put space end of line	
					}
				      else {
					s += mtrx2d[i][j];
						if(j!=width-1){s+= " ";}
					}				
				}
			}
			s += "\n"; 
	   	}
 		return s;	
	} 
	/**
		*reading from file
		*@param fname is file name	
			*/
	public void readFromFile(String fname) throws FileNotFoundException{
		Scanner sc = new Scanner(new BufferedReader(new FileReader(fname)));
			int h=0,w=0,i,j;
		      
			String bb = "bb";
		      
			 for (i=0; sc.hasNextLine(); i++) {
			    	String [] line = sc.nextLine().trim().split(" ");
			   	w=0;
			 	for ( j=0; j<line.length; j++) {
					if(line[j].equals(bb))
						mtrx2d[i][j]=99;
					else
				       	mtrx2d[i][j] = Integer.parseInt(line[j]);
					w++;
			    }
			}
			width=w;	
		      	height=i;//height of matrix
			sc.close();
			System.out.println(toString());
	}
		/**
		*writing matrix to file
			*/
	public void writeToFile() {	
		Scanner input = new Scanner( System.in );			
		String fname;
		System.out.println("enter filename\n");
		fname=input.nextLine();
		try {
		  File file = new File(fname);
		  FileWriter writer = null ;
		  writer = new FileWriter(file);
		  BufferedWriter output = null;
		  output=  new BufferedWriter(writer);
		  output.write(toString());
		  output.close();

		} catch (FileNotFoundException ex) {System.out.println("Wrong file name");}
		  catch (IOException e) { System.out.println("Wrong file name");}	
		System.out.println("\nwrited matrix to "+fname+"file");
	}
		/**
		*resetting matrix
		*@param resetcheck we use that for know just for finding goalmatrix
		*/
	public void reset(int resetcheck){//finding the final board ascending 
		int i,j,k,l,m,n,b=0,a=0;
		int temp;
		//filling goal array with initial array,we dont change initial; 
		for (k = 0; k < height; k++){
			for (m = 0; m < width; m++){
				goalmtrx2d[k][m]=mtrx2d[k][m];
			}
		}
		
		for (k = 0; k < height; k++){//sorting array
			for (m = 0; m < width; m++){
			    n = m+1;
			    for ( i = k; i < height ; i++){
				for ( j = n; j < width; j++){
				    if(goalmtrx2d [k][m]!= 0 && goalmtrx2d [i][j]!= 0  ){//we dont change 00 's
					if (goalmtrx2d [k][m] > goalmtrx2d [i][j]){
					temp=goalmtrx2d [k][m]; 
					goalmtrx2d [k][m]=goalmtrx2d [i][j];
					goalmtrx2d [i][j]=temp;     
					}
				    }
				}
				n=0;
			    } 
			}
		}
	     if(resetcheck==1){//if we use this function just for finding goalmatrix we must know that
		for (k = 0; k < height; k++){//if we want reset mtrx then this resetting main matrix to goal
			for (m = 0; m < width; m++){
				mtrx2d[k][m]=goalmtrx2d[k][m];
			}
		}
	     }


	}
	/**
		*setting size of the matrix
		*/
	public void setSize(){//if user didnt use command line, we use this function
		int i,j,num=1;
	        Scanner input = new Scanner( System.in );            
		System.out.println("Enter height: ");
		height=input.nextInt();
		System.out.println("Enter width: ");
		width=input.nextInt();
		
		
		mtrx2d = new int  [height][width];

		for(i=0;i<height;i++){//filling array with solution (we dont use reset because of this)
			for(j=0;j<width;j++){
				if(i == height-1 && j == width-1){ 
					mtrx2d[i][j]= 99;//we declare 99 for bb 
					}		
				else	{mtrx2d[i][j] = num;}
					num=num+1;
			}
		}
		
		System.out.println(toString());

	}
	/**
		*move of matrix
		*@param printcheck we use that for know are we print matrix
		*/
	public void move(int printcheck) {
		    Scanner input = new Scanner( System.in );            
		
		int resetcheck=0,temp;
		char secim;
			System.out.println("Please enter the move direction : ");
			secim=input.next().charAt(0);
			reset(resetcheck);//for getting goalmatrix
			
				 
		if( secim == 'L'  || secim == 'l' ){

			if(x>0 && y< height && mtrx2d[y][x-1] != 0){
				temp=mtrx2d[y][x];
				mtrx2d[y][x]=mtrx2d[y][x-1];
				mtrx2d[y][x-1]=temp;				
				x=x-1;				
				movenum=movenum+1;
				lastmove = 'L';			
				if(isSolved()== true ){
					System.out.println("----PROBLEM SOLVED----\n"+"Total number of moves: "+movenum+"\n");
					}
			if(printcheck==1){			
				System.out.println(toString());}
				}
				
			else{ System.out.println("\n you CAN'T use this move,NOT VALID\n");   }
		}
		else if(secim=='R' || secim=='r' ){
			if(x<width-1 && y<height && mtrx2d[y][x+1] != 0){
				temp=mtrx2d[y][x];
				mtrx2d[y][x]=mtrx2d[y][x+1];
				mtrx2d[y][x+1]=temp;
				x=x+1;				
				movenum=movenum+1;
				lastmove = 'R';			
				if(isSolved()== true){
					System.out.println("----PROBLEM SOLVED----\n"+"Total number of moves: "+movenum+"\n");
				}				
				if(printcheck==1){			
				System.out.println(toString());}
				}

			 else{ System.out.println("\n you CAN'T use this move,NOT VALID\n");   }

		}
		else if(secim=='U' || secim=='u' ){

			if(x>=0 && y>0 && mtrx2d[y-1][x] != 0){
				temp=mtrx2d[y][x];
				mtrx2d[y][x]=mtrx2d[y-1][x];
				mtrx2d[y-1][x]=temp;
				y=y-1;				
				movenum=movenum+1;
				lastmove = 'U';				
				if(isSolved()== true){
					System.out.println("----PROBLEM SOLVED----\n"+"Total number of moves: "+movenum+"\n");
					}
				if(printcheck==1){			
				System.out.println(toString());}				
				}
			
			else{ System.out.println("\n you CAN'T use this move,NOT VALID\n");   }

		}

		else if(secim=='D' || secim=='d' ){
			if(x>=0 && y<height-1 && mtrx2d[y+1][x] != 0){
				temp=mtrx2d[y][x];
				mtrx2d[y][x]=mtrx2d[y+1][x];
				mtrx2d[y+1][x]=temp;
				y=y+1;				
				movenum=movenum+1;
				lastmove = 'D';			
			if(isSolved()== true){
				System.out.println("----PROBLEM SOLVED----\n"+"Total number of moves: "+movenum+"\n");
			}			
				if(printcheck==1){			
				System.out.println(toString());}			
			}
		else{ System.out.println("\n you CAN'T use this move,NOT VALID\n");   }

		}
	}
		/**
		*checking puzzle is solved
		*/
    	public boolean isSolved() {//comparing current matrix with goal
		int i,j,k,counter=0;
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if(goalmtrx2d[i][j]==mtrx2d[i][j]){
				counter++;		
				}				
			}
		}
		if(counter== height * width) return true;

		else 	return false;

	}
	/**
		*rgetting data with giving indexes
		*@param index1 and index2 is location of data
		*/
	public int  cell (int index1,int index2) { //we use this operator for get mtrx[index1][index2] 's value  
		
		int x,y,index1d;
			
		if (index1 >= height || index2 >= width ){
			System.out.println("wrong index for boardarray1d ,try it again later\n");
			return 0;
		}
			
		else {
			return mtrx2d[index1][index2];
		}
	}

}//Boardarray2d curl





