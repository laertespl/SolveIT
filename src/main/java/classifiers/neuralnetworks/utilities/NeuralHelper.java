package classifiers.neuralnetworks.utilities;

import java.util.Random;

import org.la4j.matrix.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;

public class NeuralHelper{

	
	public static Matrix createMatrixOfDoublesWithRandomValues(double randMin, double randMax,int rows, int columns)
	{
		Matrix matrix = new Basic2DMatrix(rows,columns); 
	    Random rand = new Random();
		
	    for(int i=0; i<matrix.rows(); i++)
	    {
	    	for(int j=0; j<matrix.columns(); j++)
	    	{
	    		double randomNum = rand.nextDouble() * ((randMax - randMin) + 1) + randMin;
	    		matrix.set(i, j, randomNum);
	    	}
	    }
	    return matrix ; 
	}
	
	
	public  static Matrix addBias(Matrix mat)
	{
		Matrix mat2 = new Basic2DMatrix(mat.rows(),mat.columns()+1);
		for(int i=0; i<mat.rows(); i++)
		{
			mat2.set(i, 0, 1);
			for(int j=1; j<mat.columns()+1; j++)
			{
				mat2.set(i, j, mat.get(i, j-1));
			}
		}
		return mat2 ;  
	} 
	
	public static void printMatrix(Matrix mat)
	{
		for(int i=0 ; i<mat.rows(); i++)
		{
			for(int j=0 ; j<mat.columns(); j++)
			{
				System.out.print(mat.get(i, j)+ " ");
			} 
			System.out.println();
		}
	}
	
	public static Matrix createOnesMatrix(int x,int y)
	{
		Matrix ones = new Basic2DMatrix(x, y) ;
		for(int i=0; i<ones.rows(); i++)
		{
			for(int j=0; j<ones.columns(); j++)
			{
				ones.set(i, j, 1);
			}
		}
		return ones ; 
	}
	
	public static Matrix returnAllRowsAndGivenCollumns(Matrix matrix,int columnToStart)
	{
		Matrix matrix2 = new Basic2DMatrix(matrix.rows(),matrix.columns()-1);
		for(int i=0; i<matrix.rows(); i++)
		{
			for(int j=columnToStart; j<matrix.columns(); j++)
			{
				matrix2.set(i, j-columnToStart, matrix.get(i, j));
			}
		}
		return matrix2 ; 
	}
	
	public static Matrix createMatrixOfZeros(int i,int j)
	{
		Matrix matrix = new Basic2DMatrix(i,j);
		
		for(int g=0; g<matrix.rows(); g++)
		{
			for(int p=0; p<matrix.columns(); p++)
			{
				matrix.set(g, p, 0);
			}
		}
		return matrix ; 
	}
	
	public static Matrix combineTwoMatrix(Matrix a ,Matrix b)
	{
		Matrix combine = new Basic2DMatrix(a.rows(),a.columns()+b.columns());
		
		for(int i=0; i<a.rows(); i++)
		{
			for(int j=0; j<a.columns(); j++)
			{
				combine.set(i, j, a.get(i, j));
			}
			for(int h=0; h<b.columns(); h++)
			{
				combine.set(i,h+a.columns(),b.get(i, h));
			}
		}
		return combine ; 
	}
	
	public static Matrix sigmoidGradient(Matrix matrix)
	{
		Matrix matrix2 = new Basic2DMatrix(matrix.rows(),matrix.columns()) ;
		Matrix temp = createOnesMatrix(matrix.rows(), matrix.columns()) ;
		matrix2=sigmoid(matrix).hadamardProduct(temp.subtract(sigmoid(matrix)));
		
		return matrix2 ; 
	}
	
	public static Matrix sigmoid(Matrix mat)
	{
		Matrix mat2 = new Basic2DMatrix(mat.rows(),mat.columns()) ;
		
		for(int i=0; i<mat.rows(); i++)
		{
			for(int j=0; j<mat.columns(); j++)
			{
				mat2.set(i, j, 1/(1+Math.exp(-mat.get(i, j))));
			}
		}
		return mat2 ; 
	}
	
}