package mnemonics;

import java.util.*;
import java.util.logging.*;

import mnemonics.model.Solution;
import mnemonics.service.MnemonicsService;

public class Main {

	static Logger logger = Logger.getLogger("MyLog");

	public static void main(String[] args) {

		logger.setLevel(Level.FINE);
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n");

		String[] args2 = { "A,a,p,f,l,B,b,i,r,n,C,c,l,m,n,t,i,n", "Apfel,Birne,Clementine" };

		List<Solution> solutions = MnemonicsService.solve(args2);
		logSolutions(solutions);

		logger.log(Level.INFO, "---");

		List<Solution> solutions2 = MnemonicsService.solve(args2, 2);
		logSolutions(solutions2);
	}

	private static void logSolutions(List<Solution> solutions) {

		HashSet<Solution> solutionSet = new HashSet<>(solutions);
		if (solutionSet.isEmpty()) {
			logger.log(Level.INFO, "no solutions");
		} else {
			for (Solution solution : solutionSet) {
				logger.log(Level.INFO, "{0}", new Object[] { solution });
			}
		}
	}

}
