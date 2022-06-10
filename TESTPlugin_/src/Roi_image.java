import ij.IJ;
import ij.ImagePlus;
import ij.Prefs;
import ij.gui.Roi;
import ij.plugin.PlugIn;
import ij.plugin.frame.RoiManager;
 
public class Roi_image  implements PlugIn {
	public void run(String arg) {
		
		ImagePlus imagem = IJ.getImage();
		ImagePlus imagem_copia = imagem.duplicate();

		IJ.run("ROI Manager...", "");
		
		Prefs.blackBackground = false;
		IJ.run(imagem_copia, "Convert to Mask", "");
		IJ.run(imagem_copia, "Fill Holes", "");
		
		IJ.run("ROI Manager...", "");
		IJ.run(imagem_copia, "Analyze Particles...", "add");
		
		 			
		RoiManager roiManager = RoiManager.getRoiManager();
		Roi[] vetor_roi = roiManager.getRoisAsArray();
		
		int contador = 0;
		for(int x = 0; x < vetor_roi.length; ++x){
			contador++;
			imagem.setRoi(vetor_roi[x].getBounds());
			
			//cortar o sprite da imagem
			ImagePlus imagem_sprite = imagem.crop();
			imagem_sprite.setTitle("Sprite_crop" + contador);
			IJ.saveAs(imagem_sprite, "PNG", "C:/Users/mateus/Documents/GitHub/ProgramacaoOO/Imagens/Sprites/" + imagem_sprite.getTitle());
		}
		
	}
}