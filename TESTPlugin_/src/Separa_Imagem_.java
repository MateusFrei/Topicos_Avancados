import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

public class Separa_Imagem_  implements PlugIn {
    public void run(String arg) {
        
        ImagePlus imagem = IJ.getImage();
        
        ImagePlus ImagemR = IJ.createImage("ImagemR", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);       
        ImagePlus ImagemG = IJ.createImage("ImagemG", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);        
        ImagePlus ImagemB = IJ.createImage("ImagemB", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);
        
        
        ImageProcessor processador = imagem.getProcessor();
        
        ImageProcessor processadorR = ImagemR.getProcessor(); 
        for(int x = 0; x < processadorR.getWidth(); x++) {
        	for(int y = 0; y < processadorR.getHeight(); y++) {
        		processadorR.putPixel(x, y, processador.getPixel(x, y, null) [0]);
        	}
        }
        IJ.run(ImagemR, "Red", "");
        ImagemR.updateAndDraw();
        ImagemR.show();

        
        ImageProcessor processadorG = ImagemG.getProcessor();
        for(int x = 0; x < processadorG.getWidth(); x++) {
        	for(int y = 0; y < processadorG.getHeight(); y++) {
        		processadorG.putPixel(x, y, processador.getPixel(x, y, null) [1]);
        	}
        }
        IJ.run(ImagemG, "Green", "");
        ImagemG.updateAndDraw();
        ImagemG.show();

        
        ImageProcessor processadorB = ImagemB.getProcessor();        
        for(int x = 0; x < processadorB.getWidth(); x++) {
        	for(int y = 0; y < processadorB.getHeight(); y++) {
        		processadorB.putPixel(x, y, processador.getPixel(x, y, null) [2]);
        	}
        }
        IJ.run(ImagemB, "Blue", "");
        ImagemB.updateAndDraw();
        ImagemB.show();
        imagem.hide();
    }
}