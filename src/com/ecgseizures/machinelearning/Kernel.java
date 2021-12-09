package com.ecgseizures.machinelearning;

public class Kernel {
	
	private Matrix weights;
	
	public Kernel(int size) {
		weights = new Matrix(new float[size][size]);
	}

	public int size() {
		return weights.size()[0];
	}
	
	private float performSingleStep(Matrix matrix, int posX, int posY) {
		float val = 0;
		
		for (int y = 0; y < size(); y++) {
			for (int x = 0; x < size(); x++) {
				val += weights.getValue(x, y) * matrix.getValue(posX + x, posY + y);
			}
		}
		
		return val;
	}
	
	public Matrix perform(Matrix matrix) {
		int[] mSize = matrix.size();

		int newWidth = mSize[0] - (size() - 1);
		int newHeight = mSize[1] - (size() - 1);
		
		float[][] newValues = new float[newHeight][newWidth];
		
		for (int y = 0; y < newHeight; y++) {
			for (int x = 0; x < newWidth; x++) {
				newValues[y][x] = performSingleStep(matrix, x, y);
			}
		}
		
		return new Matrix(newValues);
	}
	
}
