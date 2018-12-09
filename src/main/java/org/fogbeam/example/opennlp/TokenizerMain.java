
package org.fogbeam.example.opennlp;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

/**

 * @brief Separación en tokens sobre textos, y guardandolos en un fichero de salida

 *

 */

public class TokenizerMain
{
	public static void main( String[] args ) throws Exception
	{

	
		// the model we trained
		InputStream modelIn = new FileInputStream( "models/en-token.model" );

		// Objeto que genera el fichero de salida
		PrintStream DDescritor = new PrintStream("Resultados.txt");
        
		String texto = null;

	        try {
	            FileReader entrada=new FileReader("docs/eval.txt");

	                int c=0;

	                while(c!=-1) {
	                    c=entrada.read();

	                    char letra=(char)c;

	                    texto+=letra;
	                }

	                entrada.close();

	                System.out.println(texto);

	        } catch (IOException e) {

	            System.out.println("No se ha encontrado el archivo");
	        }

		try
		{
			TokenizerModel model = new TokenizerModel( modelIn );
		
			Tokenizer tokenizer = new TokenizerME(model);
			
				/* note what happens with the "three depending on which model you use */
			String[] tokens = tokenizer.tokenize
					( texto  );
			
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
