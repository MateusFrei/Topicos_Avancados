import ij.plugin.PlugIn;
import ij.gui.DialogListener;
import ij.gui.GenericDialog;
import java.awt.AWTEvent;
import ij.IJ;

public class Exemplo_Generic_Dialog implements PlugIn, DialogListener {
	public void run(String arg) {
		apresentarInterfaceGrafica();
	}
	
	public void apresentarInterfaceGrafica() {
		GenericDialog interfaceGrafica = new GenericDialog("Exemplo de uso do Generic Dialog");
		interfaceGrafica.addDialogListener(this);
		
		String[] estrategia = {"Estratégia 1", "Estratégia 2","Estratégia 3"}; 
		interfaceGrafica.addMessage("Mensagem de teste com alguma informação para a interface modal");
		interfaceGrafica.addStringField("Digitar algum texto de entrada:", "Texto Inicial");
		interfaceGrafica.addRadioButtonGroup("Botões para escolher uma dentre várias estratégias", estrategia, 1, 3, "Estratégia 2");
		interfaceGrafica.addNumericField("Entrar com um número inteiro:", 10, 0);
		interfaceGrafica.addCheckbox("Ativa ou desativa algum recurso", true);
		interfaceGrafica.addSlider("Mudar um valor dado um intervalo", 0, 255, 128, 1);
		interfaceGrafica.showDialog();
		
		if (interfaceGrafica.wasCanceled()) {
			IJ.showMessage("PlugIn cancelado!");
		}
		else {
			if (interfaceGrafica.wasOKed()) {
				
				IJ.log("_____________Últimas respostas obtidas_______________");
				IJ.log("Resposta da caixa de texto:" + interfaceGrafica.getNextString());
				IJ.log("Resposta do botão de rádio:" + interfaceGrafica.getNextRadioButton());
				IJ.log("Resposta do campo numérico:" + interfaceGrafica.getNextNumber());
		        IJ.log("Resposta do checkbox:" + interfaceGrafica.getNextBoolean());
		        IJ.log("Resposta do slider:" + interfaceGrafica.getNextNumber());
		        
		        IJ.showMessage("Plugin encerrado com sucesso!");
			}
		}
	}

	@Override
	public boolean dialogItemChanged(GenericDialog interfaceGrafica, AWTEvent e) {
		if (interfaceGrafica.wasCanceled()) return false;
		IJ.log("Resposta da caixa de texto:" + interfaceGrafica.getNextString());
		IJ.log("Resposta do botão de rádio:" + interfaceGrafica.getNextRadioButton());
		IJ.log("Resposta do campo numérico:" + interfaceGrafica.getNextNumber());
        IJ.log("Resposta do checkbox:" + interfaceGrafica.getNextBoolean());
        IJ.log("Resposta do slider:" + interfaceGrafica.getNextNumber());
        IJ.log("\n");
        return true;
    }
}