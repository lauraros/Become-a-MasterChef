package BecomeAMasterChef;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.CompoundBorder;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EtchedBorder;

import BecomeAMasterChef.Player.Player;

import javax.swing.JButton;

import javax.swing.SwingUtilities;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BaMC{

	
	public BaMC() {
		//Player player= new Player();
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
		mainPanel.setPreferredSize(new Dimension(350, 200));
		
		JLabel welcome = new JLabel("Welcome to Become a Master Chef!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		mainPanel.add(welcome);
		
		JPanel pSettings = new JPanel();
		mainPanel.add(pSettings);
		pSettings.setLayout(null);
		
		JLabel playerNameLabel = new JLabel("What's your name?");
		playerNameLabel.setBounds(0, 0, 171, 19);
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSettings.add(playerNameLabel);
		
		JTextField playerNameField = new JTextField();
		playerNameField.setBounds(200, 0, 120, 19);
		playerNameField.setHorizontalAlignment(SwingConstants.RIGHT);
		pSettings.add(playerNameField);
		playerNameField.setColumns(10);
		
		JLabel levelLabel = new JLabel("Which level you want to play?");
		levelLabel.setBounds(0, 20, 171, 19);
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSettings.add(levelLabel);
		
		JComboBox levelBox = new JComboBox();
		levelBox.setBounds(200, 20, 120, 19);
		levelBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		levelBox.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Medium", "Hard", "Hardest"}));
		pSettings.add(levelBox);
		
		JLabel creditsLabel = new JLabel("<html>People have talents... set up yours! <br>(You have 10 points in total.)</html>");
		creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		mainPanel.add(creditsLabel);
		
		JPanel pSkills = new JPanel();
		mainPanel.add(pSkills);
		FlowLayout fl_pSkills = new FlowLayout(FlowLayout.CENTER, 2, 2);
		pSkills.setLayout(fl_pSkills);
		
		JLabel Skill1Label = new JLabel("Chopping");
		Skill1Label.setForeground(new Color(165, 42, 42));
		Skill1Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		pSkills.add(Skill1Label);
		
		JSpinner skill1V = new JSpinner();
		skill1V.setModel(new SpinnerNumberModel(5, 0, 10, 1));
		pSkills.add(skill1V);
		
		JLabel Skill2Label = new JLabel("Boiling");
		Skill2Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		Skill2Label.setForeground(new Color(0, 128, 128));
		pSkills.add(Skill2Label);
		
		JTextField skill2V = new JTextField();
		int skill1 = (int) skill1V.getValue();
		int skill2 = 10 - skill1;
		skill2V.setText(Integer.toString(skill2));
		skill2V.setHorizontalAlignment(SwingConstants.CENTER);
		skill2V.setEditable(false);
		pSkills.add(skill2V);
		skill2V.setColumns(10);
	
		
		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		okButton.setForeground(new Color(0, 0, 205));
		mainPanel.add(okButton);

		frame.pack();
		frame.setVisible(true);
		
        //Process the button press
        okButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String playerName = (String)playerNameField.getText();
                //Get the vertical gap value
                String gameLevel = (String)levelBox.getSelectedItem();
                //System.out.println(playerName);
                System.out.println("hello" );
                //private Player= new Player(name, chopping, cooking)
                //		level
                //		Game(Player, level)
            }
        });
        
		
        }


/*
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
            public void run() {
            	try {
            		BaMC play = new BaMC();
            	}catch (Exception e) {
            		e.printStackTrace();
            	}
            }
			
			//play.setVisible(true);
		});
	}
*/
}
