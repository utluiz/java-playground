import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class MigLayoutTest {

	public MigLayoutTest() {
	    JFrame frame = new JFrame();
	    frame.setLayout(new GridLayout(1,1));

		JPanel panel = new JPanel( new MigLayout() );
	    JPanel imagem = new JPanel();
	    imagem.setBackground(Color.red);


	    panel.add(new JLabel("Codigo"));
		panel.add( new JTextField() , "width 100%, span 4, wrap" );

	    panel.add(new JLabel("Nome"));
		panel.add( new JTextField() , "grow x, width :100:, span 3" );
		panel.add( imagem , "width :100:, height :100:, span 1 2, wrap" );

		panel.add( new JLabel( "Endreço" ) );
		panel.add( new JTextField() , "grow x, width :100: " );

		panel.add( new JLabel( "Numero" ) );
		panel.add( new JTextField() , "grow x, width :100:" );

	    frame.add(panel);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 300);
	    frame.setVisible(true);
	}

	public static void main(String args[]) {
	    new MigLayoutTest();
	}
}
