package graphics_game_witcher;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener, KeyListener {

	/*------------------------------------ Game World Objects ---------------------------------*/
	Hero hero;
	Health health;
	LevelEndFlag levelEndFlag;
	World world = new World();
	MyCanvas canvas = new MyCanvas();
	HeroJump heroJump = new HeroJump();
	MainMenu mainMenu = new MainMenu();
	GameOver gameOver = new GameOver();
	PauseMenu pauseMenu = new PauseMenu();
	
	
	/*-------------------------------------- Game Object Maps ----------------------------------*/
	SpellMap spellMap = new SpellMap();
	SoundMap audioPlayer = new SoundMap();
	MonsterMap monsterMap = new MonsterMap();
	PlatformMap platformMap = new PlatformMap();
	MonsterAttackMap monsterAttackMap = new MonsterAttackMap();
	
	
	/*---------------------------------- Game World Array Details --------------------------------*/
	int worldArr[][] = world.getWorldArr();
	int numRows =  world.getRows();
	int numCols = world.getColumns();
	int j = 0;	// Last Column loaded from World Array
	
	
	/*------------------------ Used in loadGameWorld() function -----------------------------*/
	int prevCol = 0;	// Previously scanned column
	int objectX = 0;	// X Coordinate to load Object in World
	
	
	/*----------------------------- Used in keyReleased() function --------------------------------*/
	boolean space = false;
    boolean right = false;
    boolean left = false;
    
	
    /*--------------------- Data Structures used to store game objects ----------------------------*/
    ArrayList<Spell> spells = new ArrayList<>();
    ArrayList<Monster> monsters = new ArrayList<>();
    ArrayList<Platform> platforms = new ArrayList<>();
    Map<String, Sound> sounds = new HashMap<String, Sound>();
	ArrayList<MonsterAttack> monsterAttacks = new ArrayList<>();
	
	
	/*---------------------------------- Screen Dimension Details ---------------------------------*/
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	int screenWidth = (int)d.getWidth();
	int screenHeight = (int)d.getHeight();
	
	
	boolean heroFallState = false;	// To check hero is falling  or not
    int timerMonsterAttack = 0;		// Used in function monsterAttack()
    int timerJumpFlag = 0;			// Used in function monsterAttack()
	Timer timer = null;				// Game World timer for refresh
	Sound stream = null;			// Holds the Current Background Audio
	

	/*------------------------------- Initializer Functions ------------------------------*/
	/*----------------------------------- Constructor ------------------------------------*/
	public MyFrame() {
		// Set window settings
		setTitle("The Witcher");	
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(this);	// Listen to changes
		
		// Add main menu to JFrame and listen to buttons
		add(mainMenu);
		mainMenu.getNewGameBtn().addActionListener(this);
		mainMenu.getVolumeBtn().addActionListener(this);
		mainMenu.getExitBtn().addActionListener(this);
		stream = mainMenu.getStream();
		
		// Listen to Pause Menu Buttons
		pauseMenu.getResumeBtn().addActionListener(this);
		pauseMenu.getVolumeBtn().addActionListener(this);
		pauseMenu.getExitBtn().addActionListener(this);
	}
	
	/*-------------------- Function to initialize the Game Over Window -------------------*/
	public void initGameOverComponents() {
		// Remove game world and stop combat music
		remove(canvas);
		stopBackgroundAudio();
		
		timer.stop();	// Stop game timer
		
		// Add game over menu and listen to exit button
		add(gameOver);
		gameOver.getExitBtn().addActionListener(this);
		
		// Repaint canvas and focus keyListener to this window
		revalidate();
		repaint();
		requestFocusInWindow();
	}
	
	/*----------------------- Function to initialize the Game World ----------------------*/
 	public void initGameWorldComponents() {
		
		System.out.println(screenWidth + " " + screenHeight);
		
		// Load the initial Game World
		loadGameWorld(30);
		
		/*-------------- Add hero and health to the world -----------*/
		// Hero coordinates X: 200, Y: 425
		hero = new Hero(new Point(200, 425)); 
		canvas.objects.add(hero);
		// Health bar coordinates X: 125, Y: 80
		health = new Health(new Point(125, 80), new Point(200, 30));
		canvas.objects.add(health);
		
		// Control the game through single timer
		timerControl();
	}
	
 	
 	/*------------------------------- Audio Clip Controllers -----------------------------*/
 	/*---------------------- Function to Play the Background Theme -----------------------*/
	public void playBackgroundAudio() {
		if (stream != null) {
			stream.play(true);
		}
	}
	
	/*---------------------- Function to Stop the Background Theme  ----------------------*/
	public void stopBackgroundAudio() {
		if (stream != null) {
			stream.stop();
		}
	}
	
	
	/*------------------------------- Game World System ----------------------------------*/
	/*----------------------- Function to load the game world  ---------------------------*/
	public void loadGameWorld(int numOfCols) {
		Platform platform = null;
		Monster monster = null;
		
		int i = 0;
		int numCols = j + numOfCols;	// Total no. of Columns to load
		
		// Scan column wise
		while (j < numCols) {
			i = 0;	// Initialize the rows
			
			while (i < 17) {
				// Y Coordinate of new object to be added
				int objectY = i * 50;
				
				// Case when Platform encountered
				if (worldArr[i][j] == 1) {		
					platform = new Platform(new Point(objectX , objectY), platformMap.getPlatform("platform1"));
					canvas.objects.add(platform);
					platforms.add(platform);
				}
				// Case when Platform encountered
				if (worldArr[i][j] == 121) {
					platform = new Platform(new Point(objectX , objectY), platformMap.getPlatform("platform2"));
					canvas.objects.add(platform);
					platforms.add(platform);
				}
				// Case when Platform encountered
				if (worldArr[i][j] == 131) {
					platform = new Platform(new Point(objectX , objectY), platformMap.getPlatform("platform3"));
					canvas.objects.add(platform);
					platforms.add(platform);
				}
				// Case when Monster encountered
				if (worldArr[i][j] == 2) {
					monster = new Monster(new Point(objectX , objectY), monsterMap.getMonster("fiend"), 2);
					canvas.objects.add(monster);
					monsters.add(monster);
				}
				// Case when Monster encountered
				if (worldArr[i][j] == 232) {
					monster = new Monster(new Point(objectX , objectY), monsterMap.getMonster("leshy"), 232);
					canvas.objects.add(monster);
					monsters.add(monster);
				}
				// Case when Monster encountered
				if (worldArr[i][j] == 242) {
					monster = new Monster(new Point(objectX , objectY), monsterMap.getMonster("witch"), 242);
					canvas.objects.add(monster);
					monsters.add(monster);
				}
				// Case when Flag encountered
				if (worldArr[i][j] == 3) {					
					levelEndFlag = new LevelEndFlag(new Point(objectX , objectY));
					canvas.objects.add(levelEndFlag);
				}
				++i;
			}// End of inner 'while' loop
			// Set current column as previous column & increase column count
			prevCol = j;
			++j;
			
			// X Coordinate of next object to be added
			objectX += 50;
		}// End of outer 'while' loop
	}
	
	/*---------------- Function to control game through single timer ---------------------*/
	/*-------------------- Timer function to control game refresh  -----------------------*/
	public void timerControl() {
		
		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Hero Animations
				hero.standingAnimation();
				if (hero.stateIgni == true) {
					hero.igniAnimation();
				}				
				if (heroJump.stateJump == true) {
					heroJump.jump();
				}
				
				// Monster Animations
				for (int i = 0; i < monsters.size(); i++) {
					monsters.get(i).standingAnimation();
				}
				
				// Spell Animations
				for (int i = 0; i < spells.size(); i++) {
					spells.get(i).animation();
				}
				
				// Monster Attack Animations
				for (int i = 0; i < monsterAttacks.size(); i++) {
					monsterAttacks.get(i).attackAnimation();
				}
				
				// Flag Animations
				if (levelEndFlag != null) {
					levelEndFlag.flagAnimation();
				}
				
				// Check hero collision with platform
				if (heroJump.stateJump == false) {
					checkCollisionPlatform();
				}
				
				// Check hero spell collision with monsters
				checkCollisionSpellAttack();

				// Monster attack generator
				monsterAttack();
				
				// Monster Attack collision with Hero
				checkCollisionMonsterAttack();
				
				// Remove Objects
				removeMonster();
				removeMonsterAttack();
				removeSpell();
				
				// Load world array column when shifted
				getNextWorldColumn();
				
				// Re-initialize the Jump flags after 1sec
				if (timerJumpFlag == 100) {
					space = false;
					right = false;
					left = false;
					
					timerJumpFlag = 0;	// Reset timer
				}
				timerJumpFlag += 10;
				
				// Repaint the canvas to refresh screen
				canvas.repaint();
			}
			
		});
		timer.start();
	}	

	
	/*----------------------------------- Collisions -------------------------------------*/
	/*---------------------- Check Hero collision with Platform --------------------------*/
	public void checkCollisionPlatform() {
		int feetStartX = hero.getFeetStartX();
		int feetEndX = hero.getFeetEndX();
		int feetEndY = hero.getFeetEndY();
		heroFallState = true;
		
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			int platformX = platform.location.x;
			int platformY = platform.location.y;
			
			// If Hero feet collides with Platform
			if (((feetStartX >= platformX && feetStartX <= platformX+50) || (feetEndX >= platformX && feetEndX <= platformX+50)) && feetEndY == platformY) {
				heroFallState = false; // Hero reached platform, fall state = false
			}
		}
		// If hero is falling, decrease height
		if (heroFallState == true) {
			if (hero.location.y < screenHeight) {
				hero.location.y += 10;
			} else {
				initGameOverComponents();	// Hero out of window, game over
			}
		} 
	}	
	
	/*-------------------- Check Monster collision with Hero Attack ----------------------*/
	public void checkCollisionSpellAttack() {
		for (int i = 0; i < spells.size(); i++) {
			Spell spell = spells.get(i);
			int spellStartX = spell.getRangeStartX();
			int spellStartY = spell.getRangeStartY();
			int spellEndY = spell.getRangeEndY();
			
			for (int j = 0; j < monsters.size(); j++) {
				Monster monster = monsters.get(j);
				int monsterX1 = monster.location.x;
				int monsterY1 = monster.location.y;
				int monsterX2 = monster.getMonsterEndX();
				int monsterY2 = monster.getMonsterEndY();
				
				if (spellStartX > monsterX1 && spellStartX < monsterX2) {
					if ((spellStartY > monsterY1 && spellStartY < monsterY2) || (spellEndY > monsterY1 && spellEndY < monsterY2)) {
						canvas.objects.remove(monster);
						monsters.remove(j);
						canvas.objects.remove(spell);
						spells.remove(i);
						return;
					}
				}
			}
		}
	}
	
	/*-------------------- Check Monster Attack collision with Hero ----------------------*/
	public void checkCollisionMonsterAttack() {
		for (int i = 0; i < monsterAttacks.size(); i++) {
			MonsterAttack monsterAttack = monsterAttacks.get(i);
			int monsterAttackHeadX =  monsterAttack.location.x;
			int monsterAttackStartY = monsterAttack.getRangeStartY();
			int monsterAttackEndY = monsterAttack.getRangeEndY();
			
			int heroX1 = hero.location.x;
			int heroY1 = hero.location.y;
			int heroX2 = hero.location.x + hero.getWidth();
			int heroY2 = hero.location.y + hero.getHeight();
			
			if (monsterAttackHeadX > heroX1 && monsterAttackHeadX < heroX2) {
				if ((monsterAttackStartY > heroY1 && monsterAttackStartY < heroY2) || (monsterAttackEndY > heroY1 && monsterAttackEndY < heroY2)) {
					canvas.objects.remove(monsterAttack);
					monsterAttacks.remove(i);
					health.setHealth();
					return;
				}
			}
		}
		if (health.getHealth() <= 0) {
			initGameOverComponents();
		}
	}
	
	
	/*------------------------------------ Attacks ---------------------------------------*/
	/*-------------------- Monster Attacks Hero within 1000 pixels -----------------------*/
	public void monsterAttack() {
		// Generate attack every 4sec
		if (timerMonsterAttack == 2000) {
			for (int i = 0; i < monsters.size(); i++) {
				Monster monster = monsters.get(i);
				int distance = monster.location.x - hero.location.x;
				
				// Generate leshy attack
				if (monster.monsterCode == 232 && distance < 1000 && distance > 0) {
					MonsterAttack monsterAttack = new MonsterAttack(new Point(monster.location.x, monster.location.y), monsterAttackMap.getMonsterAttack("leshyAttack"));
					canvas.objects.add(monsterAttack);
					monsterAttacks.add(monsterAttack);
					
					// Play audio clip of attack
					if (!sounds.containsKey("fireball")) {
		        		Sound stream = new Sound(audioPlayer.getSound("fireball"));
		        		sounds.put("fireball", stream);
		        	}
		        	sounds.get("fireball").play(false);
				}
				
				// Generate witch attack
				if (monster.monsterCode == 242 && distance < 1000 && distance > 0) {
					MonsterAttack monsterAttack = new MonsterAttack(new Point(monster.location.x, monster.location.y), monsterAttackMap.getMonsterAttack("witchAttack"));
					canvas.objects.add(monsterAttack);
					monsterAttacks.add(monsterAttack);
					
					// Play audio clip of attack
					if (!sounds.containsKey("spell")) {
		        		Sound stream = new Sound(audioPlayer.getSound("spell"));
		        		sounds.put("spell", stream);
		        	}
		        	sounds.get("spell").play(false);
				}
			}	// End of 'for' loop
			timerMonsterAttack = 0;	// Reset timer
		}
		timerMonsterAttack += 10;
	}

	/*---------------------------- Hero attack on key event ------------------------------*/
	public void HeroAttack(String spellName) {
    	Spell spell = new Spell(hero.location, hero.getWidth(), spellMap.getSpell(spellName));
		canvas.objects.add(spell);
		spells.add(spell);
		
		// Play audio clip of attack
    	if (!sounds.containsKey(spellName)) {
    		Sound stream = new Sound(audioPlayer.getSound(spellName));
    		sounds.put(spellName, stream);
    	}
    	sounds.get(spellName).play(false);
	}
	
	
	/*-------------------------------- Remove Objects ------------------------------------*/
	/*---------------------- Remove Monsters when crossed from screen --------------------*/
	public void removeMonster() {
		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			if (monster.location.x+150 <= 0) {
				canvas.objects.remove(monster);
				monsters.remove(i);
			}
		}
	}
	
	/*------------------ Remove Monsters Attacks when crossed from screen ----------------*/
	public void removeMonsterAttack() {
		for (int i = 0; i < monsterAttacks.size(); i++) {
			MonsterAttack monsterAttack = monsterAttacks.get(i);
			if (monsterAttack.location.x + monsterAttack.getWidth() <= 0) {
				canvas.objects.remove(monsterAttack);
				monsterAttacks.remove(i);
			}
		}
	}
	
	/*----------------- Remove Hero Spell Attacks when crossed from screen ---------------*/
	public void removeSpell() {
		for (int i = 0; i < spells.size(); i++) {
			Spell spell = spells.get(i);
			if (spell.state == true) {
				canvas.objects.remove(spell);
				spells.remove(i);
			}
		}
	}


	/*---------------------------------- Shift World -------------------------------------*/
	/*----------------------- Shift World when Hero Moves right --------------------------*/
	public void shiftWorld(int speed) {
		
		// Move all Platforms with the Speed value
		for (int i = 0; i < platforms.size(); i++) {
			Platform platform = platforms.get(i);
			platform.location.x -= speed;
			
			// Remove Platform if not in screen
			if (platform.location.x + 50 <= 0) { 
				canvas.objects.remove(platform);
				platforms.remove(i);
			}
		}
		
		// Move all Monsters with the Speed value
		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			monster.location.x -= speed;
		}
		
		// Move all Monster Attack with the Speed value
		for (int i = 0; i < monsterAttacks.size(); i++) {
			MonsterAttack monsterAttack = monsterAttacks.get(i);
			monsterAttack.location.x -= speed;
		}
		
		// Move Level End Flag with the Speed value
		if (levelEndFlag != null) {
			levelEndFlag.location.x -= speed;
		}
		
		// Move Last Coordinate to add Objects with the speed value
		objectX -= speed;
	}
	
	/*----------------------- Load Next Column from World Array --------------------------*/
	public void getNextWorldColumn() {
		
		// Get next World Objects only if not already scanned
		if (prevCol != j && j < 90) {
			
			// Load objects in World if last World is Shifted 
			if ((screenWidth - objectX) > 1) {				
				loadGameWorld(1);
			}
		}// End of outer 'if'
    	
		// Load World again if all columns already loaded
    	if (j == 90) {
    		// Load World again if last object away from window width of 250 pixel
    		if ((screenWidth - objectX) > 250) {
    			j = 0;
    			objectX += 250;
    			loadGameWorld(1);
    		}
    	}// End of outer 'if'
	}
	
	
	/*----------------------------------- Key Events -------------------------------------*/
	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		/*-------------------------- Event Jump ----------------------------*/
		// Set keys for Jump
		if (keyCode == KeyEvent.VK_SPACE) {
            space = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            left = true;
        }

		// If Jump Right
        if (space && right && heroFallState == false) {
        	heroFallState = true;
    		heroJump.setJumpState();
    		heroJump.setHeroObj(hero);
    		heroJump.setJumpRight();
    		
        	space = false;
        	right = false;
        }
        
        // If Jump Left
        if (space && left && heroFallState == false) {
        	heroFallState = true;
    		heroJump.setJumpState();
    		heroJump.setHeroObj(hero);
    		heroJump.setJumpLeft();
    		
        	space = false;
        	left = false;
        }
		
        /*-------------------------- Event Hero Attack ----------------------------*/
        // If igni attack
        if (keyCode == KeyEvent.VK_1) {        	
        	HeroAttack("igni");
        	
        	hero.stateIgni = true;	// Set state of attack to true
		}
        
        /*-------------------------- Event Pause Game ----------------------------*/
        if (keyCode == KeyEvent.VK_ESCAPE) {
        	// Add Pause Menu and listen to Buttons
        	if (pauseMenu.pauseMenuState == false) {
        		timer.stop();	// Pause Game Timer  
        		setGlassPane(pauseMenu);  	// Set the Pause Menu
                pauseMenu.setVisible(true);  // Show the Pause Menu
        		
        		pauseMenu.pauseMenuState = true;
        	} else {
        		timer.restart();				// Restart Game Timer    		
        		pauseMenu.setVisible(false);	// Hide Pause Menu  
        		
        		pauseMenu.pauseMenuState = false;
        	}
    		// Focus keyListener to this window
    		requestFocusInWindow();
        }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// Move hero to left
        if (keyCode == KeyEvent.VK_LEFT && hero.location.x > 0) {
            hero.location.x -= 10;
        }
        
        // Move hero to right by shifting world to left
        if (keyCode == KeyEvent.VK_RIGHT && (hero.location.x + hero.getWidth()) < screenWidth) {
        	shiftWorld(10);
        } 	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		// If "Start New Game" button clicked
		if(cmd.equalsIgnoreCase("start new game")) {
			// Remove main menu and stop main menu theme
			remove(mainMenu);
			stopBackgroundAudio();
			stream = canvas.stream;	// Play Combat Music
			playBackgroundAudio();
			
			// Add Game World to canvas
			initGameWorldComponents();
			add(canvas);
			
			// Repaint canvas and focus keyListener to this window
			revalidate();
			repaint();
			requestFocusInWindow();
		}
		
		// If "Volume" button clicked
		if(cmd.equalsIgnoreCase("volume")) {
			String temp = JOptionPane.showInputDialog("Current Volume : " + stream.getVolume() + "\nEnter Value (0 to 100): ");
			try {
				int volume = Integer.parseInt(temp);
				if (volume >= 0 && volume <= 100) {
					stream.setVolume(volume);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			// Focus keyListener to this window
    		requestFocusInWindow();
		}
		
		// If "Resume" button clicked
		if(cmd.equalsIgnoreCase("resume")) {	
			timer.restart();	// Restart Game Timer    		
    		pauseMenu.setVisible(false);  // Hide Pause Menu    		
    		pauseMenu.pauseMenuState = false;
    		
    		// Focus keyListener to this window
    		requestFocusInWindow();
		}
		
		// If "Quit Game" button clicked
		if(cmd.equalsIgnoreCase("quit game")) {	
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}