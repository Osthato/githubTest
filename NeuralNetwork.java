import java.util.List;

public class NeuralNetwork {

	private Matrix weightsIH;
    private Matrix weightsHH;
	private Matrix weightsHO;
	private Matrix biasH1;
    private Matrix biasH2;
	private Matrix biasO;
	// private double lRate;
	
	public NeuralNetwork(int i, int h1, int h2, int o) {
		weightsIH = new Matrix(h1, i);
        weightsHH = new Matrix(h2, h1);
		weightsHO = new Matrix(o, h2);
		
		biasH1 = new Matrix(h1, 1);
        biasH2 = new Matrix(h2, 1);
		biasO = new Matrix(o, 1);
		
		// lRate = 0.01;
		
	}
	
	public List<Double> predict(double[] X) {
		Matrix input = Matrix.fromArray(X);
		Matrix hidden1 = Matrix.multiply(weightsIH, input);
		hidden1.add(biasH1);
		hidden1.sigmoid();
		
        Matrix hidden2 = Matrix.multiply(weightsHH, hidden1);
		hidden2.add(biasH2);
		hidden2.sigmoid();

		Matrix output = Matrix.multiply(weightsHO, hidden2);
		output.add(biasO);
		output.sigmoid();
		
		return output.toArray();
	}
	
	// public void train(double[] X, double[] Y) {
	// 	Matrix input = Matrix.fromArray(X);
	// 	Matrix hidden = Matrix.multiply(weightsIH, input);
	// 	hidden.add(biasH);
	// 	hidden.sigmoid();
		
	// 	Matrix output = Matrix.multiply(weightsHO, hidden);
	// 	output.add(biasO);
	// 	output.sigmoid();
		
	// 	Matrix target = Matrix.fromArray(Y);
		
	// 	Matrix error = Matrix.subtract(target, output);
	// 	Matrix gradient = output.dsigmoid();
	// 	gradient.multiply(error);
	// 	gradient.multiply(lRate);
		
	// 	Matrix hiddenT = Matrix.transpose(hidden);
	// 	Matrix whoDelta = Matrix.multiply(gradient, hiddenT);
		
	// 	weightsHO.add(whoDelta);
	// 	biasO.add(gradient);
		
	// 	Matrix whoT = Matrix.transpose(weightsHO);
	// 	Matrix hiddenErrors = Matrix.multiply(whoT, error);
		
	// 	Matrix hGradient = hidden.dsigmoid();
	// 	hGradient.multiply(hiddenErrors);
	// 	hGradient.multiply(lRate);
		
	// 	Matrix iT = Matrix.transpose(input);
	// 	Matrix wihDelta = Matrix.multiply(hGradient, iT);
		
	// 	weightsIH.add(wihDelta);
	// 	biasH.add(hGradient);
			
	// }
	
	// public void fit(double[][] X, double[][] Y, int epochs) {
	// 	for (int i = 0; i < epochs; i++) {
	// 		int sampleN = (int)(Math.random()*X.length);
	// 		this.train(X[sampleN], Y[sampleN]);
	// 	}
	// }

    public void replaceWeights(NeuralNetwork winner) {
        this.weightsIH = winner.weightsIH;
        this.weightsHH = winner.weightsHH;
        this.weightsHO = winner.weightsHO;
        this.biasH1 = winner.biasH1;
        this.biasH2 = winner.biasH2;
        this.biasO = winner.biasO;
    }

    public int interpretResults(List<Double> Y) {
        int result = 0;
        for (int i = 1; i < Y.size(); i++) {
            if (Y.get(i) > Y.get(result)) {
                result = i;
            }
        }

        return result + 1;
    }
    
    public void randomize(double rate) {
        Matrix randomIH = new Matrix(weightsIH.getRows(), weightsIH.getCols());
        randomIH.multiply(rate);
        weightsIH.add(randomIH);

        Matrix randomHH = new Matrix(weightsHH.getRows(), weightsHH.getCols());
        randomHH.multiply(rate);
        weightsHH.add(randomHH);
        
        Matrix randomHO = new Matrix(weightsHO.getRows(), weightsHO.getCols());
        randomHO.multiply(rate);
        weightsHO.add(randomHO);

        Matrix randomH1 = new Matrix(biasH1.getRows(), biasH1.getCols());
        randomH1.multiply(rate);
        biasH1.add(randomH1);

        Matrix randomH2 = new Matrix(biasH2.getRows(), biasH2.getCols());
        randomH2.multiply(rate);
        biasH2.add(randomH2);

        Matrix randomO = new Matrix(biasO.getRows(), biasO.getCols());
        randomO.multiply(rate);
        biasO.add(randomO);
        }

        public String toString() {
            return "WeightsIH:\n" + weightsIH.toString() + "\nWeightsHH:\n" + weightsHH.toString() + "\nWeightsHO:\n" + weightsHO.toString() + "\nBiasH1:\n" + biasH1.toString() + "\nBiasH2:\n" + biasH2.toString() + "\nBiasO:\n" + biasO.toString();
        }


    }
