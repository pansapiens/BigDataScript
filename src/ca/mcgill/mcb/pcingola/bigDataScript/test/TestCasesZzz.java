package ca.mcgill.mcb.pcingola.bigDataScript.test;

import junit.framework.Assert;

import org.junit.Test;

import ca.mcgill.mcb.pcingola.bigDataScript.util.Gpr;

/**
 * Quick test cases when creating a new feature...
 *
 * @author pcingola
 *
 */
public class TestCasesZzz extends TestCasesBase {

	//	@Test
	//	public void test125_automatic_help() {
	//		Gpr.debug("Test");
	//		String output = "Command line options 'run_125.bds' :\n" //
	//				+ "\t-mean <int>                                  : Help for argument 'mean' should be printed here\n" //
	//				+ "\t-min <int>                                   : Help for argument 'min' should be printed here\n" //
	//				+ "\t-num <int>                                   : Number of times 'hi' should be printed\n" //
	//				+ "\t-salutation <string>                         : Salutation to use\n" //
	//				+ "\t-someVeryLongCommandLineArgumentName <bool>  : This command line argument has a really long name\n" //
	//				+ "\t-useTab <bool>                               : Use tab before printing line\n" //
	//				+ "\n" //
	//		;
	//
	//		// Run and capture stdout
	//		String args[] = { "test/run_125.bds", "-h" };
	//		String stdout = runAndReturnStdout(args);
	//		if (verbose) System.err.println("STDOUT: " + stdout);
	//
	//		// Check that sys and task outputs are not there
	//		Assert.assertEquals(output, stdout);
	//	}
	//
	//	@Test
	//	public void test125b_automatic_help_unsorted() {
	//		Gpr.debug("Test");
	//		String output = "Command line options 'run_125b.bds' :\n" //
	//				+ "\t-useTab <bool>                               : Use tab before printing line\n" //
	//				+ "\t-someVeryLongCommandLineArgumentName <bool>  : This command line argument has a really long name\n" //
	//				+ "\t-salutation <string>                         : Salutation to use\n" //
	//				+ "\t-num <int>                                   : Number of times 'hi' should be printed\n" //
	//				+ "\t-min <int>                                   : Help for argument 'min' should be printed here\n" //
	//				+ "\t-mean <int>                                  : Help for argument 'mean' should be printed here\n" //
	//				+ "\n" //
	//		;
	//
	//		// Run and capture stdout
	//		String args[] = { "test/run_125b.bds", "-h" };
	//		String stdout = runAndReturnStdout(args);
	//		if (verbose) System.err.println("STDOUT: " + stdout);
	//
	//		// Check that sys and task outputs are not there
	//		Assert.assertEquals(output, stdout);
	//	}

	/**
	 * Show help when there are no arguments
	 */
	@Test
	public void test125c_automatic_help() {
		Gpr.debug("Test");
		String output = "Command line options 'run_125c.bds' :\n" //
				+ "\t-mean <int>                                  : Help for argument 'mean' should be printed here\n" //
				+ "\t-min <int>                                   : Help for argument 'min' should be printed here\n" //
				+ "\t-num <int>                                   : Number of times 'hi' should be printed\n" //
				+ "\t-salutation <string>                         : Salutation to use\n" //
				+ "\t-someVeryLongCommandLineArgumentName <bool>  : This command line argument has a really long name\n" //
				+ "\t-useTab <bool>                               : Use tab before printing line\n" //
				+ "\n" //
		;

		// Run and capture stdout
		String stdout = runAndReturnStdout("test/run_125c.bds");
		if (verbose) System.err.println("STDOUT: " + stdout);

		// Check that sys and task outputs are not there
		Assert.assertEquals(output, stdout);
	}

}
