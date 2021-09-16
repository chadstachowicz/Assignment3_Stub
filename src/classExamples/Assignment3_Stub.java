package classExamples;

import java.util.Random;

public class Assignment3_Stub
{
	public static String generateRandomSequence(char[] alphabet, float[] weights, int length) throws Exception
	{
		//check to make sure the lengths match
		if(alphabet.length != weights.length)
		{
			throw new Exception("Exception: The length of the arrays must match.");
		}
		//check to make sure the lengths match
		if(alphabet.length == 0)
		{
			throw new Exception("Exception: The arrays are empty.");
		}
		//set starting total to 0
		Float total = 0.0f;
		//calculate the total weight of the array
		for(int i=0; i<alphabet.length; i++)
		{
			total = Float.sum(total,weights[i]);
		}
		//check that it's within the margin of error
		if(total < 0.99 || total > 1.01)
		{
			throw new Exception("Excepetion: Total weight: " + total + " - Not close enough to 1.");
		}
		//setup some vars
		Random random = new Random();
		String seq = "";
		//loop through the amount of length of string we want
		for (int i = 0; i < length; i++) 
		{
			//start position of each loop to check is 0f
			Float start = 0.0f;
			//scale in case the float generated is between the margin of error we accept below or above 1
			Float f = random.nextFloat() * total;
			//loop through all weights
			for(int k=0; k<weights.length; k++)
			{
				//calculate the end position to check 
				Float end = Float.sum(start,weights[k]);
				//check if the random number is between our barriers
				if (f >= start && f <= end) {
					//add the letter corresponding to the weight to the sequence
					seq = seq + alphabet[k];
				}
				//set out new start to our last end
				start = end;
			}
						
		}
		return seq;
	}
	
	
	public static void main(String[] args) throws Exception
	{
		float[] dnaWeights = { .3f, .3f, .2f, .2f };
		char[] dnaChars = { 'A', 'C', 'G', 'T'  };
		
		// a random DNA 30 mer
		System.out.println(generateRandomSequence(dnaChars, dnaWeights,30));
		
		// background rate of residues from https://www.science.org/doi/abs/10.1126/science.286.5438.295
		float proteinBackground[] =
			{0.072658f, 0.024692f, 0.050007f, 0.061087f,
		        0.041774f, 0.071589f, 0.023392f, 0.052691f, 0.063923f,
		        0.089093f, 0.023150f, 0.042931f, 0.052228f, 0.039871f,
		        0.052012f, 0.073087f, 0.055606f, 0.063321f, 0.012720f,
		        0.032955f}; 
			

		char[] proteinResidues = 
				new char[] { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
							 'V', 'W', 'Y' };
		
		// a random protein with 30 residues
		System.out.println(generateRandomSequence(proteinResidues, proteinBackground, 30));
		
	}
}