import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
import java.util.Arrays;
import ij.IJ;
import ij.ImagePlus;

public class Filtro_Naolinear implements PlugIn {

	ImagePlus imagem_RGB = IJ.getImage();
	ImageProcessor processador_RGB = imagem_RGB.getProcessor();	

	ImagePlus imagem_naolinear = IJ.createImage("Imagem Resultante", "8-bit", processador_RGB.getWidth(), processador_RGB.getHeight(), 1);
	ImageProcessor processador_naolinear = imagem_naolinear.getProcessor();

	public void run(String arg) {
		showInterface();
	}

	private void showInterface() {

		int[] vetor = new int[9];
		double mediana;
		int tamanho = vetor.length;

		for (int x = 1; x < processador_RGB.getWidth()-1; x++) {
			for (int y = 1; y < processador_RGB.getHeight()-1; y++) {
				vetor[0] = processador_RGB.getPixel(x-1,y+1);
				vetor[1] = processador_RGB.getPixel(x+1,y+1);
				vetor[2] = processador_RGB.getPixel(x, y+1);
				vetor[3] = processador_RGB.getPixel(x-1, y-1);
				vetor[4] = processador_RGB.getPixel(x+1,y-1);
				vetor[5] = processador_RGB.getPixel(x-1, y);
				vetor[6] = processador_RGB.getPixel(x+1, y);
				vetor[7] = processador_RGB.getPixel(x, y-1);
				vetor[8] = processador_RGB.getPixel(x, y);
				
				Arrays.sort(vetor);
				
				if (tamanho % 2 == 0) {
					mediana = ((double)vetor[tamanho/2] + (double)vetor[tamanho/2 - 1])/2;
				} else {
					mediana = (double) vetor[tamanho/2];
				}

				processador_naolinear.putPixel(x, y, (int) mediana);

			}
		}
		imagem_naolinear.show();
	}

	}


