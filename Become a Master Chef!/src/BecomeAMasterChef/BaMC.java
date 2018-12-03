package BecomeAMasterChef;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BaMC{

	
	public BaMC() {
		initGUI();
	}

	private void initGUI() {
		JFrame frame = new JFrame();
		frame.setTitle("Become a Master Chef!");
		frame.setBounds(100, 100, 360, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(5, 2, 0, 0));
		mainPanel.setPreferredSize(new Dimension(350, 250));
		
		JLabel welcome = new JLabel("Welcome to Become a Master Chef!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		mainPanel.add(welcome);
		
		JPanel pSettings = new JPanel();
		mainPanel.add(pSettings);
		pSettings.setLayout(null);
		
		JLabel playerNameLabel = new JLabel("What's Your Name?");
		playerNameLabel.setBounds(0, 0, 171, 19);
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSettings.add(playerNameLabel);
		
		JTextField playerNameField = new JTextField();
		playerNameField.setBounds(200, 0, 120, 19);
		playerNameField.setHorizontalAlignment(SwingConstants.RIGHT);
		pSettings.add(playerNameField);
		playerNameField.setColumns(10);
		
		JLabel levelLabel = new JLabel("Choose a Level");
		levelLabel.setBounds(0, 20, 171, 19);
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSettings.add(levelLabel);
		
		JComboBox levelBox = new JComboBox();
		levelBox.setBounds(200, 20, 120, 19);
		levelBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		levelBox.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Medium", "Hard", "Hardest"}));
		pSettings.add(levelBox);
		
		JLabel creditsLabel = new JLabel("<html><br>Choose Your Skill Balance <br>(Total 10 Points)</html>");
		creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		mainPanel.add(creditsLabel);
		
		JPanel pSkills = new JPanel();
		mainPanel.add(pSkills);
		FlowLayout fl_pSkills = new FlowLayout(FlowLayout.CENTER, 2, 2);
		pSkills.setLayout(fl_pSkills);
		
		JLabel Skill1Label = new JLabel("Cooking Skills");
		Skill1Label.setForeground(new Color(165, 42, 42));
		Skill1Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		pSkills.add(Skill1Label);
		
		JSpinner skill1V = new JSpinner();
		skill1V.setModel(new SpinnerNumberModel(5, 0, 10, 1));
		pSkills.add(skill1V);
		
		JLabel Skill2Label = new JLabel("Chopping");
		Skill2Label.setForeground(new Color(0, 128, 128));
		Skill2Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		pSkills.add(Skill2Label);
		
		JSpinner skill2V = new JSpinner();
		skill2V.setModel(new SpinnerNumberModel(5, 0, 10, 1));
		pSkills.add(skill2V);
		
		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		startButton.setForeground(new Color(0, 0, 205));
		mainPanel.add(startButton);

		frame.pack();
		frame.setVisible(true);
		
        //Process the button press
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String playerName = (String)playerNameField.getText();
                String level = (String)levelBox.getSelectedItem();
                int cookingSkills = (Integer)skill1V.getValue();
                
            	new JLabel(frame.getTitle());
               	new Game(playerName, level, cookingSkills);
            }
        });
        
        skill1V.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner s = (JSpinner) e.getSource();
                System.out.println((int)s.getValue());
                if ((int)skill1V.getValue() + (int)skill2V.getValue() != 10) {
                	startButton.setEnabled(false);
                }
                else startButton.setEnabled(true);
            }
        });
        
        skill2V.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner s = (JSpinner) e.getSource();
                System.out.println(s.getValue().toString());
                if ((int)skill1V.getValue() + (int)skill2V.getValue() != 10) {
                	startButton.setEnabled(false);
                }
                else startButton.setEnabled(true);
            }
        });
        
		
        }



	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	try {
            		new BaMC();
            	}catch (Exception e) {
            		e.printStackTrace();
            	}
            }
		});
	}
}
