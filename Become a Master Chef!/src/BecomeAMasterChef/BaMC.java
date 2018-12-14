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

import BecomeAMasterChef.SoundEffects.SoundEffect;

import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BaMC{

	
	public BaMC() {
		initGUI();
	}

	// Setup GUI for game entrance
	public static void initGUI() {
		// Settings for window frame
		JFrame frame = new JFrame();
		frame.setTitle("Become a Master Chef!");
		frame.setBounds(100, 100, 360, 240);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		// Creating a panel for main Game settings
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
		
		JComboBox<String> levelBox = new JComboBox<>();
		levelBox.setBounds(200, 20, 120, 19);
		levelBox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		levelBox.setModel(new DefaultComboBoxModel<>(new String[] {"Easy", "Medium", "Hard", "Hardest"}));
		pSettings.add(levelBox);
		
		JLabel creditsLabel = new JLabel("<html><br>Choose Your Skills Balance <br>(10 Total Credits)</html>");
		creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creditsLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		mainPanel.add(creditsLabel);
		
		// Creating a separate Panel for skills settings for the game
		JPanel pSkills = new JPanel();
		mainPanel.add(pSkills);
		FlowLayout fl_pSkills = new FlowLayout(FlowLayout.CENTER, 2, 2);
		pSkills.setLayout(fl_pSkills);
		
		JLabel Skill1Label = new JLabel("Cooking Skills");
		Skill1Label.setForeground(new Color(165, 42, 42));
		Skill1Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		pSkills.add(Skill1Label);
		
		SpinnerNumberModel skill1model = new SpinnerNumberModel( 5, 0, 10, 1 );
		SpinnerNumberModel skill2model = new SpinnerNumberModel( 5, 0, 10, 1 );
		
		JSpinner skill1V = new JSpinner( skill1model );
		JSpinner skill2V = new JSpinner( skill2model );
		pSkills.add(skill1V);
		pSkills.add(skill2V);
		
		JLabel Skill2Label = new JLabel("Chopping");
		Skill2Label.setForeground(new Color(0, 128, 128));
		Skill2Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		pSkills.add(Skill2Label);
		
		// Add a Listener to both spinners so that their value is interdependent
		skill1model.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent e){
			  SpinnerNumberModel model = (SpinnerNumberModel)e.getSource();
			  Integer value = (Integer)model.getValue();
			  skill2model.setMinimum(0 );
			  skill2model.setMaximum(10);
			  skill2model.setValue(10-value);
			}
		});
		skill2model.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent e){
			  SpinnerNumberModel model = (SpinnerNumberModel)e.getSource();
			  Integer value = (Integer)model.getValue();
			  skill1model.setMinimum(0 );
			  skill1model.setMaximum( 10 );
			  skill1model.setValue(10-value);
			}
		});	
		
		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		startButton.setForeground(new Color(0, 0, 205));
		mainPanel.add(startButton);
		startButton.setEnabled(true);
		
		frame.pack();
		frame.setVisible(true);
		
        // Adds a Listener to the 'start' button to start the game
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){


               // Get 'player name', 'game level', and 'skill set' from input
            	String playerName = (String)playerNameField.getText();
            	if (playerName.equals("")) {
            		playerName = "Gordon Ramsay";
            	}
                String level = (String)levelBox.getSelectedItem();
                int cookingSkills = (Integer)skill1V.getValue();
                
                // Creating a new game with input values as settings
            	new JLabel(frame.getTitle());
               	new Game(playerName, level, cookingSkills);
               	
               	frame.dispose();
            }
        });
        }


	//Game entrance
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
