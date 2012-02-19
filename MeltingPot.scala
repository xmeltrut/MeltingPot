/**
 * MeltingPot combines and compresses JavaScript files
 *
 * @author Chris Worfolk <xmeltrut@gmail.com>
 *
 * @todo Specify output file
 * @todo Specify input directories
 * @todo Option not to combine
 * @todo Option not to compress
 * @todo Handle source file not found
 * @todo Handle potential file overwrites
 * @todo Handle literal strings in compression
 * @todo Improve compression
 * @todo Improve handling of new line stripping
 */

import scala.collection.mutable.Map
import java.io.FileWriter

object MeltingPot {

	/**
	 * Hold a map of filenames, and their data
	 */
	private val files = Map[String, String]()
	
	/**
	 * Main method
	 *
	 * @param args Command line arguments
	 */
	def main(args: Array[String]) {
	
		// read in each files
		if (args.length > 0) {
			args.foreach(readFile)
		} else {
			Console.err.println("Usage: MeltingPot <filename> [<other filenames>]")
		}
		
		// compress each file
		this.files.foreach {
			case(key, value) => this.files(key) = this.compress(value)
		}
		
		// combine files
		this.write()
	
	}
	
	/**
	 * Compress a block of code
	 *
	 * @param code Code to compress
	 */
	def compress (code: String): String = {
	
		var output = code
		output = this.stripBlockComments(output)
		output = this.stripInlineComments(output)
		output = this.stripNewLines(output)
		output = this.stripTabs(output)
		output = this.stripWhiteSpace(output)
		return output
	
	}
	
	/**
	 * Read in a file
	 *
	 * @param path Path to file
	 */
	def readFile (path: String) {
	
		val source = scala.io.Source.fromFile(path)
		val lines = source.mkString
		source.close()
		
		this.files += (path -> lines)
	
	}
	
	/**
	 * Strip out block comments
	 *
	 * @param code Code
	 */
	def stripBlockComments (code: String): String = {
		val replaced = code.replaceAll("/\\*([^/]*)\\*/", "")
		return replaced
	}
	
	/**
	 * Strip out inline comments
	 *
	 * @param code Code
	 */
	def stripInlineComments (code: String): String = {
		val replaced = code.replaceAll("//([^\\n]*)\\n", "")
		return replaced
	}
	
	/**
	 * Strip out new lines
	 *
	 * @param code Code
	 */
	def stripNewLines (code: String): String = {
		val replaced = code.replaceAll(";\n", ";")
		return replaced
	}
	
	/**
	 * Strip tabs out
	 *
	 * @param code Code
	 */
	def stripTabs (code: String): String = {
		val replaced = code.replaceAll("\t", "")
		return replaced
	}
	
	/**
	 * Strip white space out
	 *
	 * @param code Code
	 */
	def stripWhiteSpace (code: String): String = {
		var replaced = code
		replaced = replaced.replaceAll(" + ", "+")
		replaced = replaced.replaceAll("\\) \\{", "){")
		return replaced
	}
	
	
	
	/**
	 * Write the output
	 */
	def write () {
	
		val fw = new FileWriter("output.js")
		this.files.foreach { case (key, value) => fw.write(value) }
		fw.close()
	
	}

}
