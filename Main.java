import java.util.*;
/**
 * Asks the user for parameters and which Rule (Hebb or Delta) they wish to use. The program will calculate for the solution vector until it reaches the
 * number of iterations reuested, or it finds the solution.
 * 
 * @author Dana Ottaviani 
 * @version 12-4-2016
 */
public class Main
{
    /**
     * Calculates for a solution vector with the Hebb or Delta Rule
     */
    public static void main(String[] args)
    {
       //learning rate
       final double e;
       //allowed maximum difference in result of solution finding a vector's output
       final double d;
       //number of iterations
       final double iterations;
       //origin vector
       double[] w0 = {0.0, 0.0, 0.0};
       
       //for user input
       Scanner scan = new Scanner(System.in);
       
       //user's origin
       System.out.println("Origin vector, separate the 3 decimal values by a space: ");
       String[] origin = {};
       String input = "";
       //splits the string numbers by the space and puts them into origin
       if(scan.hasNextLine()){
           input = scan.nextLine();
           origin = input.split(" ");     
       } 
       //puts the string values of origin into w0 as double values
       for(int i = 0; i < origin.length; i++){   
           w0[i] = Double.valueOf(origin[i]);   
       }
       
       //user's input vectors
       System.out.println("3 input vectors, separate each decimal value by a space and each vector by a semicolon (example: 1 2 3 9;3 4 5 8;2 3 1 2;): ");
       
       //to hold the 3 input vectors
       double[][] vectors = {{0, 0, 0, 0},//v1
                            {0, 0, 0, 0},//v2        
                            {0, 0, 0, 0}};//v3
       //placeholder
       double[] vector = {0, 0, 0, 0};
       
       String part = "";
       //row index in vectors array
       int k = 0;
     
       if(scan.hasNextLine()){
           //contains whole string of the input vectors
           input = scan.nextLine();
           //continues until there is no more input vectors (no semicolon)
           while(input.indexOf(";") != -1){
               //part contains the next input vector
               part = input.substring(0, input.indexOf(";"));
               //input is replaced by the substring starting after the vector that is contained in part
               input = input.substring(input.indexOf(";") + 1);  
               //split part's vector by its spaces
               origin = part.split(" ");               
               for(int i = 0; i < origin.length; i++){
                   //add each value in the vector as a double
                   vector[i] = Double.valueOf(origin[i]);
                   //place the double value ito the double array to have an input array inside each index of vectors
                   vectors[k][i] = vector[i];
               }
               System.out.println(Arrays.toString(vectors[k]));
               //increase row index in vectors
               k++;
           }
       } 
       System.out.println();
       
       //user's learning rate
       System.out.print("Learning rate (as a decimal): ");
       e = scan.nextDouble();
       
       //user's allowed maximum difference
       System.out.print("Allowed maximum difference in result of solution (as a decimal): ");
       d = scan.nextDouble();
       
       //user's iteration value
       System.out.print("Number of iterations: ");
       iterations = scan.nextInt();
       
       //user's choice in which rule to use
       System.out.print("Would you like to use the Hebb Rule of the Delta Rule? ");
       input = scan.next();
       System.out.println();
             
       //new vector
       double[] w = {0.0, 0.0, 0.0}; 
       
       //keeping track of what vector is created                     
       int index = 1; 
       //the index in the vectors array is set to 0 for the next part
       int i = 0;
       
       boolean solutionFound = false;
       //for the delta's calculation
       double actual;
       
       //continues until a solution has been found or it reaches the number of iterations requested
       while(!solutionFound && index <= iterations){
           System.out.println("w" + index + " and the new origin:"); 
           //calculates the actual value if needed
           actual = w0[0]*vectors[i][0] + w0[1]*vectors[i][1] + w0[2]*vectors[i][2];
           for (int j = 0; j < vectors[i].length - 1; j++){
                //if user chose hebb or delta, the appropriate end of the equation will be used
                w[j] = e * vectors[i][j] * (input.equalsIgnoreCase("Hebb")? vectors[i][vectors[i].length - 1]:(vectors[i][vectors[i].length - 1] - actual));
                //adds the current origin to the new vector before making itself the new origin
                w0[j] += w[j];           
           } 
           System.out.println(Arrays.toString(w0));
           
           //reaches the end of the input vectors list
           if(i == vectors.length - 1){
                i = 0;
           }
           else{//or continues until the end         
                i++;
           }
           index++;
           System.out.println();
           
           solutionFound = checkVector(vectors, w0, d);
           System.out.println("Found solution? " + solutionFound);
           System.out.println();
       }      
    }

    /**
     * Calculates the output of each vector with the possible solution (the origin)
     * If one of the outputs of the vectors is not within the user's parameter, then the method returns false
     * 
     * @param  double[][] vectors   the list of vectors
     * @param  double[] origin      the possible solution vector which is currently the new origin
     * @param  double d             the user's allowed maximum difference between the actual output and the target output of each vector
     * 
     * @return solnVector           Returns true if all calculations are within the allowed maximum difference, false otherwise
     */
    public static boolean checkVector(double[][] vectors, double[] origin, double d)
    {
       boolean solnVector = true;
       double result = 0.0;
       int i = 0;
       while(solnVector && i < vectors.length){
         for (int j = 0; j < vectors[i].length - 1; j++){
            result += origin[j] * vectors[i][j]; 
         }
         if (result > (vectors[i][vectors[i].length - 1] + d) || result < (vectors[i][vectors[i].length - 1] - d)){
            solnVector = false;
         } 
         i++;       
         result = 0.0;
       }
       return solnVector;
    }
}