/*
@author furkan celen


**/
//implementation of the abstract board class

import java.io.FileNotFoundException;

public abstract class AbstractBoard{
			
			protected int movenum,height,width;
			protected static int boardnum=0;//we use that for count the numberofboards
			protected char lastmove;
			protected int x,y;//using for bb coordinates
			protected String filename;//for reading from file

			public AbstractBoard () //empty constructor
			{	height =0 ;
				width =0; 
				lastmove='S';
				boardnum++;
				}		
			
			public AbstractBoard (int h,int w,char lastm,int moven) //argumental constructor
			{	
				height=h;
				width=w;
				lastmove=lastm;
				movenum=moven;
				boardnum++;
				}

			public int getmovenum (){
				return movenum;
			}

			public int getheight (){
				return height;
			}
			public int getwidth (){
				return width;
			}
			public String getfilename (){
				return filename;
			}			
			
			public int getx() {
				return x;
			}

			public int gety() {
				return y;
			}


			public void setmovenum (int nummove){
				movenum = nummove;
			}
			public void setheight (int newh){
				height = newh;
			}

			public void setwidth (int neww){
				width= neww;
			}

			public void setfilename (String fname){
				filename= fname;
			}

			public char lastMove(){
				if (movenum == 0)
					return 'S';
				else 
					return lastmove;			
			}

			public int NumberofBoards() {
				return boardnum;
			}
	
			public int numberofmoves() {
				return movenum;
			}
			 
			public boolean Equals (AbstractBoard b1 , AbstractBoard b2){
					
				if(b1.height == b2.height && b1.width == b2.width){
							
							if(b1.x == b2.x && b1.y == b2.y)
								return true;	
							else 
								return false;
						}	
				else 
						return false;
			}

		        public abstract int  cell ( int index1,int index2);

			public abstract String toString();

			public abstract void readFromFile(String fname)throws FileNotFoundException;

			public abstract void writeToFile();


			public abstract void reset(int resetcheck);

			public abstract void setSize();

			public abstract void move(int printcheck);

		    	
			public abstract boolean isSolved();

			
			
}//end of AbstractBoard class

/*
public static	boolean CheckMoves(AbstractBoard  &moves[] ){
	int i=0,j,x1,x2,y1,y2;
	boolean flag;

	while(moves[i] != NULL){//we compare the bb(blank) index,we use coordinates like  (y,x) ,
		x1= moves[i]->getx();	
		x2= moves[i-1]->getx();
		y1= moves[i]->gety();
		y2= moves[i-1]->gety();
		
			if(	(x1 -x2) <= 1 && (x1 -x2) >= -1  && y1 == y2)
					flag = true;
		
			else if((y1 -y2) <=1 && (y1 -y2) >= -1  && x1 == x2)
					flag = true;
					
			else 
				flag=false;

			i++;
	}

	return flag;
}

*/



