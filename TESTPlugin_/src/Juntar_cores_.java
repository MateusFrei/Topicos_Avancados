import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
 
public class Juntar_cores_  implements PlugIn {
	public void run(String arg) {
		
		
		ImagePlus imagem_red = WindowManager.getImage("ImagemR");
		ImageProcessor processador_red = imagem_red.getProcessor();
		
		ImagePlus imagem_green = WindowManager.getImage("ImagemG");
		ImageProcessor processador_green = imagem_green.getProcessor();
		
		ImagePlus imagem_blue = WindowManager.getImage("ImagemB");
		ImageProcessor processador_blue = imagem_blue.getProcessor();

		ImagePlus imagem_colorida = IJ.createImage("Imagem_junta_RGB", "RGB", imagem_red.getWidth() , imagem_red.getHeight(), 1);
		
		ImageProcessor processadror_rgb = imagem_colorida.getProcessor();
		int vetor[] = new int[3];
		
        for(int x = 0; x < processadror_rgb.getWidth(); x++) {
        	for(int y = 0; y < processadror_rgb.getHeight(); y++) {
        		
        		vetor[0] = processador_red.getPixel(x, y);
        		vetor[1] = processador_green.getPixel(x, y);
        		vetor[2] = processador_blue.getPixel(x, y);
        		
        		processadror_rgb.putPixel(x, y, vetor);
        		
        	}
        }
        
        imagem_colorida.show();
        imagem_colorida.updateAndDraw();
        imagem_red.hide();
        imagem_green.hide();
        imagem_blue.hide();
	}
}