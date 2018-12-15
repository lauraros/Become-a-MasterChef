package become_a_masterchef;

// Import GUI related libraries and classes
import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Import Game class
import become_a_masterchef.game.Game;

/* 
 * Main Class for the game: it initialize a GUI 
 * for the initial settings of the game and start the Game.
*/
public class BecomeAMasterChef{

	
	public BecomeAMasterChef() {
		initGUI();
	}

	// Setup GUI for game entrance
	public static void initGUI() {
		
		// Settings for window frame
		JFrame frame = new JFrame();
		frame.setTitle("Become a Master Chef!");
		frame.setBounds(100, 100, 350, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		// Creating a main panel 
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new GridLayout(5, 2, 0, 0));
		mainPanel.setPreferredSize(new Dimension(350, 350));
		
		// Welcoming Label
		JLabel welcome = new JLabel("Welcome to Become a MasterChef!");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Tahoma", Font.BOLD, 14));
		mainPanel.add(welcome);
		
		// Creating a separate panel for main Game settings
		JPanel pSettings = new JPanel();
		mainPanel.add(pSettings);
		pSettings.setLayout(null);
		
		// Creating sevaral components for the main Game settings
		JLabel playerNameLabel = new JLabel("What's Your Name?");
		playerNameLabel.setBounds(0, 0, 170, 20);
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSettings.add(playerNameLabel);
		
		JTextField playerNameField = new JTextField();
		playerNameField.setBounds(200, 0, 120, 20);
		playerNameField.setHorizontalAlignment(SwingConstants.RIGHT);
		pSettings.add(playerNameField);
		playerNameField.setColumns(10);
		
		JLabel levelLabel = new JLabel("Choose a Level");
		levelLabel.setBounds(0, 20, 170, 20);
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pSettings.add(levelLabel);
		
		JComboBox<String> levelBox = new JComboBox<>();
		levelBox.setBounds(200, 20, 120, 20);
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
		
		JLabel skill1Label = new JLabel("Cooking Skills");
		skill1Label.setForeground(new Color(165, 42, 42));
		skill1Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		pSkills.add(skill1Label);
		
		SpinnerNumberModel skill1model = new SpinnerNumberModel( 5, 0, 10, 1 );
		SpinnerNumberModel skill2model = new SpinnerNumberModel( 5, 0, 10, 1 );
		
		JSpinner skill1V = new JSpinner( skill1model );
		JSpinner skill2V = new JSpinner( skill2model );
		pSkills.add(skill1V);
		pSkills.add(skill2V);
		
		JLabel skill2Label = new JLabel("Chopping");
		skill2Label.setForeground(new Color(0, 128, 128));
		skill2Label.setFont(new Font("Tahoma", Font.BOLD, 13));
		pSkills.add(skill2Label);
		
		// Add a Listener to both spinners so that their value is interdependent
		// Spinner 1 Listener
		skill1model.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent e){
			  SpinnerNumberModel model = (SpinnerNumberModel)e.getSource();
			  Integer value = (Integer)model.getValue();
			  skill2model.setMinimum(0 );
			  skill2model.setMaximum(10);
			  skill2model.setValue(10-value);
			}
		});
		
		// Spinner 1 Listener
		skill2model.addChangeListener(new ChangeListener(){
		public void stateChanged(ChangeEvent e){
			  SpinnerNumberModel model = (SpinnerNumberModel)e.getSource();
			  Integer value = (Integer)model.getValue();
			  skill1model.setMinimum(0 );
			  skill1model.setMaximum( 10 );
			  skill1model.setValue(10-value);
			}
		});	
		
		// Add a button to start the Game
		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		startButton.setForeground(new Color(0, 0, 205));
		mainPanel.add(startButton);
		startButton.setEnabled(true);
		frame.setLocationRelativeTo(null); 
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
            		new BecomeAMasterChef();
            	}catch (Exception e) {
            		e.printStackTrace();
            	}
            }
		});
	}
}
