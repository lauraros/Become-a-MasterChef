package BecomeAMasterChef;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.BorderLayout;




public class BaMC{
	

	public BaMC() {

		initUI();
	}

	private void initUI() {
		final String gapList[] = {"0", "10", "15", "20"};
	    final int maxGap = 20;
	    String playerName = null;
	    String gameLevel = null;

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Become a MasterChef!");
		frame.setSize(400, 150);

		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		JLabel welcome = new JLabel("Welcome to... Become a MasterChef!");
		welcome.setFont(new Font("", Font.PLAIN, 15));
		mainPanel.add(welcome, BorderLayout.NORTH);
		JPanel settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(2,2));
		mainPanel.add(settingsPanel, BorderLayout.CENTER);
		//JPanel panel = new JPanel();
		//panel.setBackground(Color.white);
		//settingsPanel.setResizable(false);
		JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
		settingsPanel.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));
		
        //GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel nameLabel = new JLabel("What's your name? ");
        JTextArea nameField = new JTextArea ("");
        //JLabel level = new JLabel("Level");
        JLabel levelLabel = new JLabel("Which level do you want to play? ");
        SpinnerNumberModel levelModel = new SpinnerNumberModel(1,1,5,1);
        String[] levels = new String[] {"Easy", "Normal", "Hard", "Hardest"};
        JComboBox<String> level = new JComboBox<String>(levels);
        

        settingsPanel.add(nameLabel);
        settingsPanel.add(nameField);
        settingsPanel.add(levelLabel);
        settingsPanel.add(level);
        frame.add(mainPanel);

        JButton okButton = new JButton("OK");
        mainPanel.add(okButton, BorderLayout.SOUTH);
		//frame.getContentPane().add(panel);

		//frame.pack();
		frame.setVisible(true);
		
        //Process the Apply gaps button press
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String playerName = (String)nameField.getText();
                //Get the vertical gap value
                String gameLevel = (String)level.getSelectedItem();
                System.out.println(playerName);
                System.out.println(gameLevel );
            }
        });
        
		
        }



	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	BaMC play = new BaMC();
            }
			
			//play.setVisible(true);
		});
	}
}
