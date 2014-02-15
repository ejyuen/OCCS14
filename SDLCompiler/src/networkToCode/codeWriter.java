package networkToCode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import sdlNetwork.*;


public class codeWriter {

	public static void writeCase(String outputFileName, SDLNetwork network) {
		StringBuffer output = new StringBuffer();
		output.append("import java.util.ArrayList\nimport java.util.LinkedList\n import java.util.Queue;\n import java.util.ConcurrentLinkedQueue;\n"
				+ "public class "  + outputFileName + " implements Runnable {\n"
				+ "private void stateAction(Signal signal){\nif(signal == null){\nreturn;\n}\nswitch(state){\n");
		for (State state : network.getStates()) {
			output.append("case " + state + ":\nswitch(signal){\n");
			for (Connection connection : state.getConnections()) {
				output.append("case " + connection.getSignal() + ":\nstate = State."
						+ connection.getEndState() + "\nbreak;");
			}
			output.append("default:\nbreak;\n}\nbreak;\n}\n}\n}\n");
		}

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName + ".java"));
			out.write(output.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
