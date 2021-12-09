package com.ecgseizures.machinelearning;

import com.ecgseizures.util.Function;

public class Matrix {

	private float[][] values;

	public Matrix(float[][] values) {
		this.values = values;
	}

	public float getValue(int x, int y) {
		return this.values[y][x];
	}
	
	public Matrix add(Matrix otherMatrix) {
		int[] size = this.size();
		
		float[][] newValues = new float[size[0]][size[1]];

		for (int y = 0; y < size[1]; y++) {
			for (int x = 0; x < size[0]; x++) {
				newValues[y][x] = getValue(x, y) + otherMatrix.getValue(x, y);
			}
		}

		return new Matrix(newValues);
	}

	public Matrix multiply(float scalar) {
		Function mult = new Function() {
			@Override
			public float apply(float x) {
				return x * scalar;
			}
		};
		
		return applyFunction(mult);
	}

	public Matrix applyFunction(Function function) {
		int[] size = this.size();

		float[][] newValues = new float[size[0]][size[1]];

		for (int y = 0; y < size[1]; y++) {
			for (int x = 0; x < size[0]; x++) {
				newValues[y][x] = function.apply(getValue(x, y));
			}
		}

		return new Matrix(newValues);
	}
	
	public Matrix dot(Matrix otherMatrix) {
		int[] thisSize = this.size();
		int[] otherSize = otherMatrix.size();

		float[][] newValues = new float[thisSize[1]][otherSize[0]];

		for (int i = 0; i < thisSize[1]; i++) {
			for (int n = 0; n < otherSize[0]; n++) {
				for (int k = 0; k < thisSize[0]; k++) {
					newValues[i][n] += getValue(k, i) * otherMatrix.getValue(n, k);
				}
			}
		}

		return new Matrix(newValues);
	}

	public Matrix T() {
		int[] size = this.size();

		float[][] newValues = new float[size[0]][size[1]];

		for (int y = 0; y < size[1]; y++) {
			for (int x = 0; x < size[0]; x++) {
				newValues[x][y] = getValue(x, y);
			}
		}

		return new Matrix(newValues);
	}

	public int[] size() {
		return new int[] { this.values[0].length, this.values.length };
	}

}
