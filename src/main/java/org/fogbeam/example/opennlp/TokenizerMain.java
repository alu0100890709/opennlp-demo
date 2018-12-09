
package org.fogbeam.example.opennlp;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;


public class TokenizerMain
{
	public static void main( String[] args ) throws Exception
	{
		
		// the provided model
		// InputStream modelIn = new FileInputStream( "models/en-token.bin" );

		
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );
		
		////////////////////////////////////////////
		
		
		PrintStream DDescritor = new PrintStream("Resultados.txt");
        
		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
				/* note what happens with the "three depending on which model you use */
			String[] tokens = tokenizer.tokenize
					(  "A ranger journeying with Oglethorpe, founder of the Georgia Colony, " 
							+ " mentions \"three Mounts raised by the Indians over three of their Great Kings" 
							+ " who were killed in the Wars.\"" );
			
			for( String token : tokens )
			{
				System.out.println( token );
				DDescritor.println(token);
			}
			
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if( modelIn != null )
			{
				try
				{
					modelIn.close();
				}
				catch( IOException e )
				{
				}
			}
		}
		System.out.println( "\n-----\ndone" );
	}
}
