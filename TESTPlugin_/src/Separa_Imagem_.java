import ij.IJ;
import ij.ImagePlus;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

public class Separa_Imagem_  implements PlugIn {
    public void run(String arg) {
        
        ImagePlus imagem = IJ.getImage();
        ImageProcessor processador = imagem.getProcessor();
        
        IJ.createImage("imagem1", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);
        IJ.createImage("imagem2", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);
        IJ.createImage("imagem3", "8-bit", imagem.getWidth(), imagem.getHeight(), 1);
        
        
        
        imagem.updateAndDraw();
    }
}