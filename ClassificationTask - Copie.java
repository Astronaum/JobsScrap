import java.awt.Frame;
import java.util.Random;

import javax.swing.JFrame;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
public class ClassificationTask {

	public static void main(String[] args) throws Exception {

		/*
		 * Load the data
		 */
		
		
		DataSource source = new DataSource("C:\\Users\\Mkh\\Desktop\\train.arff");
		Instances data = source.getDataSet();
		System.out.println(data.numInstances() + " instances loaded.");

		
		/*
		 * Feature selection
		 */
		
		
		AttributeSelection attSelect = new AttributeSelection();
		InfoGainAttributeEval eval = new InfoGainAttributeEval();
		Ranker search = new Ranker();
		attSelect.setEvaluator(eval);
		attSelect.setSearch(search);
		attSelect.SelectAttributes(data);
		int[] indices = attSelect.selectedAttributes();
		System.out.println("Selected attributes: "+Utils.arrayToString(indices));

		
		/*
		 * Build a decision tree
		 */
		
		
		String[] options = new String[1];
		options[0] = "-U";
		J48 tree = new J48();
		tree.setOptions(options);
		tree.buildClassifier(data);
		System.out.println(tree);

		
		/*
		 * Classify new instance.
		 */
		
		
		double[] vals = new double[data.numAttributes()];
		vals[0] = 3.0; // experience
		vals[1] = 2.0; // ville
		vals[2] = 1; // nbr de postes
		vals[3] = 1.0; // niveau etudes
		Instance test = new DenseInstance(1.0, vals);
		
		//Assosiate your instance with Instance object in this case dataRaw
		
		test.setDataset(data); 
		double label = tree.classifyInstance(test);
		System.out.println(data.classAttribute().value((int) label)+" est la prediction du type de contrat");

		
		/*
		 * Visualize decision tree
		 */
		
		
		TreeVisualizer tv = new TreeVisualizer(null, tree.graph(),
				new PlaceNode2());
		JFrame frame = new javax.swing.JFrame("Tree Visualizer");
		frame.setSize(6000, 3000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(tv);
		frame.setVisible(true);
		frame.setState(Frame.NORMAL);
		tv.fitToScreen();

		
		/*
		 * Evaluation
		 */

		
		data.setClassIndex(data.numAttributes()-1);
		
		//loading test data
		
		DataSource source1 = new DataSource("C:\\Users\\Mkh\\Desktop\\test.arff");
		Instances testdata = source1.getDataSet();
		System.out.println(testdata.numInstances() + " instances loaded.");
		testdata.setClassIndex(testdata.numAttributes()-1);
		
		//evaluation model
		
		Evaluation eval_roc = new Evaluation(data);
		eval_roc.evaluateModel(tree, testdata) ;
		System.out.println(eval_roc.toSummaryString());
		
		// Confusion matrix
		
		double[][] confusionMatrix = eval_roc.confusionMatrix();
		System.out.println(eval_roc.toMatrixString());


		/*
		 * Bonus: Plot ROC curve
		 */

		
		ThresholdCurve tc = new ThresholdCurve();
		int classIndex = 0;
		Instances result = tc.getCurve(eval_roc.predictions(), classIndex);
		// plot curve
		ThresholdVisualizePanel vmc = new ThresholdVisualizePanel();
		vmc.setROCString("(Area under ROC = " + tc.getROCArea(result) + ")");
		vmc.setName(result.relationName());
		PlotData2D tempd = new PlotData2D(result);
		tempd.setPlotName(result.relationName());
		tempd.addInstanceNumberAttribute();
		// specify which points are connected
		boolean[] cp = new boolean[result.numInstances()];
		for (int n = 1; n < cp.length; n++)
			cp[n] = true;
		tempd.setConnectPoints(cp);

		// add plot
		vmc.addPlot(tempd);
		// display curve
		JFrame frameRoc = new javax.swing.JFrame("ROC Curve");
		frameRoc.setSize(800, 500);
		frameRoc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameRoc.getContentPane().add(vmc);
		frameRoc.setVisible(true);

	}
}
