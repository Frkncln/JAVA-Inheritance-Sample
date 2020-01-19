import java.io.File;
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

	
 public class BoardArray1D extends AbstractBoard{
	
	private int  [] mtrx1d;
	private int  [] goalmtrx1d;
	private int c,size;
		/**
		*default constructor
			*/
	public BoardArray1D(){	
		height = 9;
		width = 9;
		size =	height*width; 
		mtrx1d = new int [size];
		goalmtrx1d=new int [size];
		System.out.println("1d default construcor boardnum= "+boardnum);
	}


		
	public BoardArray1D(int h,int w,char lmove,int nummove){
		super(h,w,lmove,nummove);	
		size =	h*w; 
		mtrx1d = new int [size];
		goalmtrx1d=new int [size];
		System.out.println("1d construcor boardnum= "+boardnum);
			}
				

	public int getsize(){
			return size;		
		}
			
	public void setsize(int height,int width){
			
			size = height * width;

		}
	/*	tostring :this method send 1d matrix to string ,and returns it
		* c is coordinate of the bb 
		*@return matrix as a string	
			**/
	public String toString(){ //printing matrix
	   	 int i,j,k=0,bbcount=0;
		String s="";	   
		for (i = 0; i < height; i++){		
			for (j = 0; j < width; j++){
				
				if(mtrx1d[k]== 99){//if it is 99 this means bb
					s+="bb ";
					c=k; //c is coordinate of the bb in the matrix
					}
				else{
	     				if((mtrx1d[k]/10)==0)
						s+="0"+mtrx1d[k]+" ";
					else 
						s+=mtrx1d[k] + " ";
				}
				
			   k++;			
			} 
		s+="\n"; 
	    	} 
		
		return s;
	} 
		/**
		*reading from file
		*@param fname is file name	
			*/
	public void readFromFile(String fname)throws FileNotFoundException{
			Scanner sc = new Scanner(new BufferedReader(new FileReader(fname)));
			int h=0,w=0,i,j,k=0;//k for 1d matrix converting
		      
			String bb = "bb";
		      
			 for (i=0; sc.hasNextLine(); i++) {
			    	String [] line = sc.nextLine().trim().split(" ");
			   	w=0;
			 	for ( j=0; j<line.length; j++) {
					if(line[j].equals(bb))
						mtrx1d[k] = 99;
					else
				       	mtrx1d[k] = Integer.parseInt(line[j]);
					w++;
					k++;
			    	}
			 }
			width=w;	
		      	height=i;//height of matrix
			size=height*width;
			sc.close();
			System.out.println(toString());
	}
		/**
		*writing matrix to file with input filename
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
			catch (IOException e) {System.out.println("Wrong file name");
						}
		System.out.println("\nwrited matrix to "+fname+"file");
	}
		/**
		*resetting matrix
		*@param resetcheck we use that for know just for finding goalmatrix
		*/
	public void reset(int resetcheck){//finding the final board ascending 
		int i,j,k,m,a=0;
		int temp;
		
		//filling goal array with initial array,we dont change initial;
		for (k = 0; k < size; k++){
			
				goalmtrx1d[k]=mtrx1d[k];
			
		}
		for ( i = 1; i < size; i++) {
			    for ( j = i; j > 0; j--) {
				if(goalmtrx1d [j]!= 0 && goalmtrx1d[j-1]!= 0){
				     if (goalmtrx1d[j] < goalmtrx1d [j - 1]) {
				      	temp = goalmtrx1d[j];
				      	goalmtrx1d[j] = goalmtrx1d[j - 1];
				      	goalmtrx1d[j - 1] = temp;
				     	}
				}
			    }
   		}
	     if(resetcheck==1){//if we use this function just for finding goalmatrix we dont use this section
		a=0;			
		for (k = 0; k < height; k++){//if we want reset mtrx1d then this resetting main matrix to goal
			for (m = 0; m < width; m++){
				mtrx1d[a]=goalmtrx1d[a];
				a++;	
			}
		}
	     }
		
			System.out.println("reset sonu matrix bu gardas reset check = \n"+resetcheck+toString());
	}
		/**
		*setting size of the matrix
		*/
	public void setSize() {//if user didnt use comman line, we use this function
		int i,j,k=0,num=1;

		Scanner input = new Scanner( System.in );            
		System.out.println("Enter height: ");
		height=input.nextInt();
		System.out.println("Enter width: ");
		width=input.nextInt();
		
		size =	height*width;
		mtrx1d = new int[size];
		goalmtrx1d = new int[size];
		for(i=0;i<height;i++){
			for(j=0;j<width;j++){
				if(i == height-1 && j == width-1 ){ mtrx1d[k]= 99;//we declare 99 for bb 
					  }			
				else	{mtrx1d[k]=num;}
					num=num+1;		
					k=k+1;			
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
			System.out.println( "Please enter the move direction : ");
			secim=input.next().charAt(0);
			reset(resetcheck);//for getting goalmatrix
		if( secim == 'L'  || secim == 'l' ){
				if( (c+1) % width != 1 && mtrx1d[c-1] != 0){
					temp=mtrx1d[c];
					mtrx1d[c]=mtrx1d[c-1];
					mtrx1d[c-1]=temp;			
					c=c-1;
					movenum=movenum+1;
					lastmove = 'L';		
					if(isSolved()== true ){
						System.out.println("Problem solved\n"+"Total number of moves: "+movenum+"\n");
						}
					if(printcheck==1){			
					System.out.println(toString());}
					}
					
				else{ System.out.println("\n you CAN'T use this move,NOT VALID\n");  }
			}
			else if(secim=='R' || secim=='r' ){
				if((c+1) % width !=0 && mtrx1d[c+1] != 0){
					temp=mtrx1d[c];
					mtrx1d[c]=mtrx1d[c+1];
					mtrx1d[c+1]=temp;				
					c=c+1;				
					movenum=movenum+1;
					lastmove = 'R';			
					if(isSolved()== true){
						System.out.println("Problem solved\n"+"Total number of moves: "+movenum+"\n");	
						}				
					if(printcheck==1){			
					System.out.println(toString());}
					}

				 else{ System.out.println("\n you CAN'T use this move,NOT VALID\n");   }

			}
			else if(secim=='U' || secim=='u' ){

				if( c > width -1&& mtrx1d[c-width] != 0){
					temp=mtrx1d[c];
					mtrx1d[c]=mtrx1d[c-width];
					mtrx1d[c-width]=temp;
					c=c-width;				
					movenum=movenum+1;
					lastmove = 'U';				
					if(isSolved()== true){
						System.out.println("Problem solved\n"+"Total number of moves: "+movenum+"\n");
						}
					if(printcheck==1){			
					System.out.println(toString());}				
					}
				
				else{ System.out.println("\n you CAN'T use this move,NOT VALID\n");  }

			}

			else if(secim=='D' || secim=='d' ){
				if(c <size - width &&  mtrx1d[c+width] != 0){
					temp=mtrx1d[c];
					mtrx1d[c]=mtrx1d[c+width];
					mtrx1d[c+width]=temp;
					c=c+width;				
					movenum=movenum+1;
					lastmove = 'D';			
				if(isSolved()== true){
					System.out.println("Problem solved\n"+"Total number of moves: "+movenum+"\n");
				}			
					if(printcheck==1){			
					System.out.println(toString());}			
				}
				else{System.out.println("\n you CAN'T use this move,NOT VALID\n");   }
			}
	}
		/**
		*checking puzzle is solved
		*@return true or false
		*/
    	public boolean isSolved() {//comparing current matrix with goal
		int i,j,k,counter=0;
		for(i=0;i<size;i++){
			if( goalmtrx1d[i] == mtrx1d[i]){
					counter++;		
				}		
			
		}
		if(counter== height * width) return true;

		else 	return false;

	}
		/**
		*rgetting data with giving indexes
		*@param index1 and index2 is location of data
		*@return data in that index
		*/
	public int  cell ( int index1, int index2) { //we use this operator for get mtrx[index1][index2] 's value  
		
		int x,y,index1d;
			
		if (index1 >= height || index2 >= width ){
			System.out.println("wrong index for boardarray1d ,try it again later\n");
			return 0;		
		}
			
		else {
			index1d = width*index1 + index2;			
			return 	mtrx1d [index1d];		
		}
	

	}

}//Boardarray1d  curl
