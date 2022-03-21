import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;
 
public class Juntar_cores_  implements PlugIn {
	public void run(String arg) {
		ImagePlus imagem_colorida = IJ.getImage();
		
		ImagePlus imagem_red = WindowManager.getImage("ImagemR");
		ImagePlus imagem_green = WindowManager.getImage("ImagemG");
		ImagePlus imagem_blue = WindowManager.getImage("ImagemB");
		
		ImageProcessor processadror_rgb = imagem_colorida.getProcessor();
        for(int x = 0; x < processadror_rgb.getWidth(); x++) {
        	for(int y = 0; y < processadror_rgb.getHeight(); y++) {
        		processadror_rgb.putPixel(x, y, imagem_red.getPixel(x, y));
        		processadror_rgb.putPixel(x, y, imagem_green.getPixel(x, y));
        		processadror_rgb.putPixel(x, y, imagem_blue.getPixel(x, y));
        	}
        }
        imagem_colorida.updateAndDraw();
        
	}
}