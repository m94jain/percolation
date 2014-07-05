import java.lang.Math.*;
public class PercolationStats {
    private double size;
    private int times;
    private Percolation base;
    private double[] arr;
    
   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   {
       System.out.println("constructing");
       size = N*N;
       times=T;
       arr=new double[T];
       double count;
       int i,j;
       double frac=0.0;
       for(int k=0;k<T;k++)
       {         
           count=0;
           base = new Percolation(N);
           while(base.percolates()==false)
           {
                // System.out.println("here");
               do
               {
                  //   System.out.println("here2");
                   i=1 + (int)(Math.random() * ((N - 1) + 1));
                   
                   j=1 + (int)(Math.random() * ((N - 1) + 1));
                    // System.out.println(i+" "+j);
               }
               while(base.isOpen(i,j)==true);
               base.open(i,j);
               count++;
           }
           frac=count/size;
           System.out.println(count);
           arr[k]=frac;
       }
         System.out.println("done");
   }
           
                   
   public double mean()                     // sample mean of percolation threshold
   {
       double m=0;
       double mean;
       for(int i=0;i<times;i++)
       {
           m=m+arr[i];
       }
       mean=m/times;
       return mean;
   }
           
   public double stddev()                   // sample standard deviation of percolation threshold
   {
       double mean=this.mean();
       double square=0;
       double root;
       for(int i=0;i<times;i++)
       {
           square=(arr[i]-mean)*(arr[i]-mean);
       }
       square=square/(times-1);
       root=Math.sqrt(square);
       return root;
   }
       
   public double confidenceLo()             // returns lower bound of the 95% confidence interval
   {
       double mean=this.mean();
       double dev=this.stddev();
       double CL=mean-((1.96*dev)/Math.sqrt(times));
       return CL;
   }
   public double confidenceHi()             // returns upper bound of the 95% confidence interval
   {
       double mean=this.mean();
       double dev=this.stddev();
       double CH=mean+((1.96*dev)/Math.sqrt(times));
       return CH;
   }
   public static void main(String[] args)   // test client, described below*/
   {
       PercolationStats n=new PercolationStats(200,100);
       System.out.println("working");
       System.out.println("mean= "+n.mean());
       System.out.println("deviation= "+n.stddev());
       System.out.println("Confidence Low= "+n.confidenceLo());
       System.out.println("Confidence High= "+n.confidenceHi());
       System.out.println("ended");
   }
}
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   