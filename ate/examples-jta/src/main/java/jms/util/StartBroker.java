package jms.util;
import java.io.File;

import org.apache.activemq.broker.Main;

public class StartBroker
{
	public static void main ( String[] args ) 
	throws Exception
	{
		if ( args.length != 1 ) {
			System.err.println ( "Arg required: broker port" );
			System.exit ( 1 );
		}
		//create temp lock file on URL
		String fileName = "../" + args[0] + ".lck";
		File lockFile = new File ( fileName );
		if ( lockFile.createNewFile() ) {
			System.out.println ( "Starting broker on " + args[0] );
			lockFile.deleteOnExit();
			String[]  brokerArgs = new String[1];
			brokerArgs[0] = "tcp://localhost:" + args[0];
			//XXX : PLQ package change for activeMQ 4.1.2
			Main.main ( brokerArgs );
		}	
		else {
			System.out.println ( "Broker already running." );
		}
	
	}
}
