package term_project;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class Review implements Serializable{
	private int star;
	private String review;
	
	public Review(){
		star = 0;
		review = "";
	}
	
	public Review(int theStar, String theReview){
		this.star = theStar;
		this.review = theReview;
	}
	
	public String toString(){
		return star + "\n" + review;
	}
	
	public int getStar(){return star;}
	public String getreview(){return review;}
	
	public void setStar(int theStar){
		this.star = theStar;
	}
	public void setreview(String thereview){
		this.review = thereview;
	}
	
	public static void main(String[] args){
		Review[] a = new Review[2];
		a[0] = new Review(1, "맛없다.");
		a[1] = new Review(5, "맛있다.");
		
		String currentProjPath = "";
		try{
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		String path = currentProjPath+"/src/store/";
		
		try{
			for(int i = 2; i <= 60; i++){
				ObjectOutputStream outputStream = 
					new ObjectOutputStream(new FileOutputStream(path + i + "/Review"));
				outputStream.writeObject(a);
				outputStream.close();
			}
		}catch(IOException e){
			System.err.println("Error writing");
			System.exit(0);
		}
		
	}
}
