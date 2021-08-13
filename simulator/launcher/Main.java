package simulator.launcher;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

/*
 * Examples of command-line parameters:
 * 
 *  -h
 *  -i resources/examples/ex4.4body.txt -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl ftcg
 *  -i resources/examples/ex4.4body.txt -o resources/examples/ex4.4body.out -s 100 -gl nlug
 *
 */
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONObject;

import simulator.factories.BasicBodyBuilder;
import simulator.factories.Factory;
import simulator.factories.MassLosingBodyBuilder;
import simulator.model.*;
import simulator.view.MainWindow;
import simulator.control.Controller;
import simulator.factories.*;


public class Main {

	// default values for some parameters
	private final static Integer STEPS = 150;
	private final static Double _dtimeDefaultValue = 2500.0;

	// some attributes to stores values corresponding to command-line parameters
	//
	private static Double _dtime = null;
	private static String _inFile = null;
	private static String _outFile = null;
	private static JSONObject _gravityLawsInfo = null;
	private static Integer _STEPS = null;
	private static  String mode = null;

	// factories
	private static Factory<Body> _bodyFactory;
	private static Factory<GravityLaws> _gravityLawsFactory;

	private static void init() {
		// initialize the bodies factory
		// ...

		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		bodyBuilders.add(new BasicBodyBuilder());
		bodyBuilders.add(new MassLosingBodyBuilder());
		ArrayList<Builder<GravityLaws>> lawBuilders = new ArrayList<>();
		lawBuilders.add(new NewtonUniversalGravitationBuilder());
		lawBuilders.add(new FallingToCenterGravityBuilder());
		lawBuilders.add(new NoGravityBuilder());
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilders);
		_gravityLawsFactory = new BuilderBasedFactory<GravityLaws>(lawBuilders);


		// initialize the gravity laws factory
		// ...

		ArrayList<Builder<GravityLaws>> gravityLawsBuilders = new ArrayList<>();
		gravityLawsBuilders.add(new NewtonUniversalGravitationBuilder());
		gravityLawsBuilders.add(new FallingToCenterGravityBuilder());
		gravityLawsBuilders.add(new NoGravityBuilder());
		_gravityLawsFactory = new BuilderBasedFactory<GravityLaws>(gravityLawsBuilders);
	}

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseModeOption(line);
			parseInFileOption(line);
			parseOutFileOption(line);
			parseStepsOption(line);
			parseDeltaTimeOption(line);
			parseGravityLawsOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static void parseStepsOption(CommandLine line) throws ParseException{
		String steps = line.getOptionValue("s", STEPS.toString());
		try
		{
			_STEPS = Integer.parseInt(steps);
			assert(_STEPS > 0);
		}
		catch(Exception e)
		{
			throw new ParseException("Value of steps is not valid:" + steps);
		}
	}

	private static void parseOutFileOption(CommandLine line)  throws ParseException {
		_outFile = line.getOptionValue("o");
		if (_outFile == null && mode.equals("batch")) {
			throw new ParseException("An output file of bodies is required");
		}
	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();



		// help
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message.").build());

		// input file
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Bodies JSON input file.").build());

		//mode
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc(" Execution Mode. Possible values: ’batch’\r\n" + 
				"(Batch mode), ’gui’ (Graphical User Interface mode). Default value: ’batch’.").build());

		//output file
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Bodies JSON output file.").build());

		//steps
		cmdLineOptions.addOption(Option.builder("s").longOpt("steps").hasArg().desc("Sequency count of steps. default value:" + STEPS ).build());

		// delta-time
		cmdLineOptions.addOption(Option.builder("dt").longOpt("delta-time").hasArg()
				.desc("A double representing actual time, in seconds, per simulation step. Default value: "
						+ _dtimeDefaultValue + ".")
				.build());

		// gravity laws -- there is a workaround to make it work even when
		// _gravityLawsFactory is null. 
		//
		String gravityLawsValues = "N/A";
		String defaultGravityLawsValue = "N/A";
		if (_gravityLawsFactory != null) {
			gravityLawsValues = "";
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gravityLawsValues.length() > 0) {
					gravityLawsValues = gravityLawsValues + ", ";
				}
				gravityLawsValues = gravityLawsValues + "'" + fe.getString("type") + "' (" + fe.getString("desc") + ")";
			}
			defaultGravityLawsValue = _gravityLawsFactory.getInfo().get(0).getString("type");
		}
		cmdLineOptions.addOption(Option.builder("gl").longOpt("gravity-laws").hasArg()
				.desc("Gravity laws to be used in the simulator. Possible values: " + gravityLawsValues
						+ ". Default value: '" + defaultGravityLawsValue + "'.")
				.build());

		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null && mode.equals("batch")){
			throw new ParseException("An input file of bodies is required");
		}
	}

	private static void parseDeltaTimeOption(CommandLine line) throws ParseException {
		String dt = line.getOptionValue("dt", _dtimeDefaultValue.toString());
		try {
			_dtime = Double.parseDouble(dt);
			assert (_dtime > 0);
		} catch (Exception e) {
			throw new ParseException("Invalid delta-time value: " + dt);
		}
	}

	private static void parseModeOption(CommandLine line) throws ParseException {
		mode = line.getOptionValue("m");
		if (mode != null && (!mode.equals("batch") && (!mode.equals("gui"))) || mode == null) {
			throw new ParseException("A valid mode is required");
		}

	}

	private static void parseGravityLawsOption(CommandLine line) throws ParseException {

		// this line is just a work around to make it work even when _gravityLawsFactory
		// is null, you can remove it when've defined _gravityLawsFactory

		String gl = line.getOptionValue("gl");
		if (gl != null) {
			for (JSONObject fe : _gravityLawsFactory.getInfo()) {
				if (gl.equals(fe.getString("type"))) {
					_gravityLawsInfo = fe;
					break;
				}
			}
			if (_gravityLawsInfo == null) {
				throw new ParseException("Invalid gravity laws: " + gl);
			}
		} else {
			_gravityLawsInfo = _gravityLawsFactory.getInfo().get(0);
		}
	}

	private static void startBatchMode() throws Exception {
		// create and connect components, then start the simulator
		GravityLaws gravityLaw =  _gravityLawsFactory.createInstance(_gravityLawsInfo);
		PhysicsSimulator sim = new PhysicsSimulator(_dtime, gravityLaw);
		InputStream is = new FileInputStream(_inFile);
		OutputStream os = (_outFile == null)? System.out: new PrintStream(_outFile);
		Controller ctr1 = new Controller(sim, _bodyFactory);
		ctr1.loadBodies(is);
		ctr1.run(_STEPS, os);
	}

	private static void startGUIMode() throws Exception {
		GravityLaws gravityLaw =  _gravityLawsFactory.createInstance(_gravityLawsInfo);
		PhysicsSimulator sim = new PhysicsSimulator(_dtime, gravityLaw);
		Controller ctrl = new Controller(sim, _bodyFactory, _gravityLawsFactory);
		
		if(_inFile != null)
		{
			InputStream is = new FileInputStream(_inFile);
			ctrl.loadBodies(is);
		}
	
	
	
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			
			public void run() {
			new MainWindow(ctrl);
			}
			});

	}


	private static void start(String[] args) throws Exception {
		parseArgs(args);
		if(mode.equals("batch") || mode == null)
		{
			startBatchMode();
		}
		else if(mode.equals("gui"))
		{
			startGUIMode();
		}
	
	}

	public static void main(String[] args) {
		try {
			init();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}
