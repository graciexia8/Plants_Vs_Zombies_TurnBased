
//Gracie Xia
//13/01/2015
//A Beginner's Version Of Plants Vs. Zombies (turn-based)

/*
=====================RESOURCES==========================
IMP ZOMBIE: http://plantsvszombies.wikia.com/wiki/Imp/Gallery
SUNFLOWER: http://img3.wikia.nocookie.net/__cb20090521221033/plantsvszombies/images/e/e2/Sunflower1.png
PEASHOOTER: http://pvzcc.wikia.com/wiki/File:Animated_peashooter.gif
WALL-NUT: http://plantsvszombies.wikia.com/wiki/Wall-nut/Gallery
POTATO-MINE: http://plantsvszombies.wikia.com/wiki/Potato_Mine/Gallery
BIG ZOMBIE: https://8bitotaku.files.wordpress.com/2010/10/4g3mab_jpg.gif
FRONT PAGE: http://3.bp.blogspot.com/-8uQbVwj6B0k/USr-OIzmJXI/AAAAAAAAEog/0ts9qQJ3hBQ/s1600/Plants+Vs.+Zombies+HD+for+IOS+Free+App+of+the+Week+IMG_1403.png
INSTRUCTION: http://venuspatrol.com/2009/05/sftw-plants-vs-zombies/
MUSIC1: https://www.youtube.com/watch?v=4w3VqzwJ1j4
MUSIC2: https://www.youtube.com/watch?v=HD3qtfKCWsE&index=15&list=PL8462408ECD4BCE41
MUSIC3: https://www.youtube.com/watch?v=42z5KdK3usU
MUSIC4: https://www.youtube.com/watch?v=9ulmdvUVAJ8
MUSIC5: https://www.youtube.com/watch?v=WlwRxM-IHp4

Plant Buttons and shovel Icon, sun were all screen shots from the game which you can visit at:
http://www.popcap.com/plants-vs-zombies

=========================================================
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import sun.audio.*;
import java.io.*;
import java.io.FileInputStream.*;

public class game extends Applet implements ActionListener
{

    JButton grids[] = new JButton [45]; //grid for game
    int tracker[] [] = {{10, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 0, 0, 0, 0, 0, 0, 0}};                                                                                                                       //tracker grid for game
    JButton plants[] = new JButton [4]; //array for plant buttons
    JLabel words; //instructions during game
    JLabel suncount;

    int sunCount = 0; //counts how many suns player has
    int sunflowerCount = 0;
    String words1 = ""; //track for plant button clicky thing
    String words2 = "";
    int tracker1 = 0;

    int row = 5;
    int col = 9;

    boolean inplace = false;
    boolean lose = false;
    //lawnmowerCounts allow player to see zombie get run over by lawnmowers
    int lawnmowerCount = 0;
    int lawnmowerCount2 = 0;
    int lawnmowerCount3 = 0;
    int lawnmowerCount4 = 0;
    int lawnmowerCount5 = 0;
    int zombiecount = 0;

    int nextcount2 = 1;
    char press = 'n';

    //GLOBAL VARIABLES FOR ZOMBIES
    int zombiewalk = 0;
    int zombiewalk1 = 0;
    int zombiewalk2 = 0;
    int zombiewalk3 = 0;
    int zombiewalk4 = 0;
    //Life that each zombie has
    int zombielife1 = 1000;
    int zombielife2 = 1000;
    int zombielife3 = 1000;
    int zombielife4 = 1000;
    int zombielife5 = 1000;
    //the lifespan of each walnut
    int walnutcount = 0;
    int walnutcount1 = 0;
    int walnutcount2 = 0;
    int walnutcount3 = 0;
    int walnutcount4 = 0;

    JButton shovel;
    JLabel sun, instruct;
    int instructionCounter = 0;
    JProgressBar pi;

    Panel p_card; // to hold all of the different screens
    Panel card1, card2, card3, card4, card5, card6, card7;
    CardLayout cdLayout = new CardLayout ();
    //variables for JAudio stuff
    static AudioPlayer MGP = AudioPlayer.player;
    static AudioStream BGM;
    static AudioData MD;
    static ContinuousAudioDataStream loop = null;

    public void init ()  //to initialize all screens
    {

	p_card = new Panel ();
	p_card.setLayout (cdLayout);
	introduction ();
	instructions ();
	objective ();
	plantsvszombies ();
	objectiveDone ();
	losing ();
	endMenu ();

	setLayout (new BorderLayout ());
	add ("Center", p_card);
    } //end of init


    //**************************************************************INTRODUCTION**********************************************************************
    public void introduction ()  //first screen is set up
    {
	playMusic ("mainMenu");
	card1 = new Panel ();
	JButton next = new JButton (createImageIcon ("titles.png"));
	next.setOpaque (false);
	next.setContentAreaFilled (false);
	next.setBorderPainted (false);
	next.addActionListener (this);
	next.setActionCommand ("i1");

	card1.add (next);
	p_card.add ("i1", card1);
    } //end of introduction


    //**************************************************************LOSING**********************************************************************

    public void losing ()  //losing screen is set up
    {
	card6 = new Panel ();
	JButton losing = new JButton (createImageIcon ("brain.jpg"));
	losing.setOpaque (false);
	losing.setContentAreaFilled (false);
	losing.setBorderPainted (false);
	losing.addActionListener (this);
	losing.setActionCommand ("i6");

	card6.add (losing);
	p_card.add ("i6", card6);
    } //end of losing


    //************************************************************END MENU***********************************************************************************

    public void endMenu ()  //last screen where player decides if she/he wants to play again is set up
    {
	card7 = new Panel ();
	Panel g = new Panel (new GridLayout (2, 1));
	Panel h = new Panel (new GridLayout (2, 1));
	JLabel giant = new JLabel (createImageIcon ("giant.gif"));
	JLabel title = new JLabel ("Would You Like to: ");
	title.setFont (new Font ("Arial", Font.BOLD, 30));
	JButton yes = new JButton ("PLAY AGAIN");
	yes.setBorderPainted (false);
	yes.setFont (new Font ("Arial", Font.PLAIN, 25));
	yes.setBackground (Color.white);
	yes.addActionListener (this);
	yes.setActionCommand ("i7");

	JButton no = new JButton ("EXIT GAME");
	no.setBorderPainted (false);
	no.setFont (new Font ("Arial", Font.PLAIN, 25));
	no.setBackground (Color.white);
	no.addActionListener (this);
	no.setActionCommand ("no");

	g.add (yes);
	g.add (no);
	h.add (title);
	h.add (g);
	card7.add (h);
	card7.add (giant);
	p_card.add ("i7", card7);
    } //end of endMenu


    //**********************************************************INSTRUCTIONS*******************************************************************************
    public void instructions ()  //page where there are instructions

    { //tell player how to play the game

	card2 = new Panel ();

	Panel instructAndProceed = new Panel (new GridLayout (2, 1));
	instruct = new JLabel (createImageIcon ("pvz-help0.jpg"));

	JButton next1 = new JButton ("FURTHER INSTRUCTIONS");
	next1.setBorderPainted (false);
	next1.setBackground (new Color (136, 11, 17));
	next1.setForeground (Color.white);
	next1.addActionListener (this);
	next1.setActionCommand ("instructions");

	JButton proceed = new JButton ("PROCEED TO GAME");
	proceed.setBorderPainted (false);
	proceed.setBackground (new Color (136, 11, 17));
	proceed.setForeground (Color.white);
	proceed.addActionListener (this);
	proceed.setActionCommand ("i2");
	instructAndProceed.add (next1);
	instructAndProceed.add (proceed);

	card2.add (instruct);
	card2.add (instructAndProceed, BorderLayout.PAGE_END);
	p_card.add ("i2", card2);
    }


    //********************************************************OBJECTIVE************************************************************************************
    public void objective ()  //shows objective of game
    {
	resize (1024, 680);
	card3 = new Panel (new FlowLayout (FlowLayout.CENTER, 0, 0));
	JLabel ob1 = new JLabel (createImageIcon ("ob1.jpg"));
	JButton ob2 = new JButton (createImageIcon ("ob2.jpg"));
	ob2.setBackground (new Color (28, 13, 6));
	ob2.setOpaque (false);
	ob2.setContentAreaFilled (false);
	ob2.setBorderPainted (false);
	ob2.addActionListener (this);
	ob2.setActionCommand ("i3");
	JLabel ob3 = new JLabel (createImageIcon ("ob3.jpg"));
	card3.add (ob1);
	card3.add (ob2);
	card3.add (ob3);

	p_card.add ("i3", card3);

    }


    //***************************************************OBJECTIVEDONE*****************************************************************************
    public void objectiveDone ()  //appears when player defeats all 20 zombies
    {
	resize (1024, 680);
	card5 = new Panel (new FlowLayout (FlowLayout.CENTER, 0, 0));
	JLabel ob1 = new JLabel (createImageIcon ("ob4.jpg"));
	JButton ob2 = new JButton (createImageIcon ("ob2.jpg"));
	ob2.setBackground (new Color (28, 13, 6));
	ob2.setOpaque (false);
	ob2.setContentAreaFilled (false);
	ob2.setBorderPainted (false);
	ob2.addActionListener (this);
	ob2.setActionCommand ("i5");
	JLabel ob3 = new JLabel (createImageIcon ("ob3.jpg"));
	card5.add (ob1);
	card5.add (ob2);
	card5.add (ob3);

	p_card.add ("i5", card5);
    }


    //**************************************************************PUBLIC VOID GAME***********************************************************************
    public void plantsvszombies ()  //method where game grid and other layouts are set up
    {
	card4 = new Panel ();
	//SHOVEL BUTTON
	shovel = new JButton (createImageIcon ("shovel.jpg")); //setting up Button for shovel
	shovel.setPreferredSize (new Dimension (60, 60));
	shovel.setOpaque (false);
	shovel.setContentAreaFilled (false);
	shovel.setBorderPainted (false);
	shovel.addActionListener (this);
	shovel.setActionCommand ("shovel");

	//SUN COUNT TRACKER BUTTON
	Panel sungroup = new Panel ();

	sun = new JLabel (createImageIcon ("SunFA.png"));
	suncount = new JLabel (": " + sunCount + "       ");
	suncount.setFont (new Font ("Arial", Font.BOLD, 14));
	sungroup.add (sun);
	sungroup.add (suncount);


	//RESTART AND INSTRUCTIONS BUTTON
	Panel group_ri = new Panel (new GridLayout (2, 1));

	JButton restart = new JButton ("RESTART");
	JButton instructions = new JButton ("INSTRUCTIONS");

	restart.setPreferredSize (new Dimension (25, 25));
	restart.setBorderPainted (false);

	restart.addActionListener (this);
	restart.setActionCommand ("RESTART");
	instructions.addActionListener (this);
	instructions.setActionCommand ("back to instructions");
	instructions.setBorderPainted (false);
	restart.setBackground (new Color (136, 11, 17));
	restart.setForeground (Color.white);
	instructions.setBackground (new Color (136, 11, 17));
	instructions.setForeground (Color.white);
	group_ri.add (restart);
	group_ri.add (instructions); //add two buttons to a group

	//TITLE JLABEL + ADDING ALL WIDGETS TO TOP
	JLabel title = new JLabel (createImageIcon ("title.png"));

	Panel top = new Panel ();

	top.add (shovel);
	top.add (sungroup);
	top.add (title);
	top.add (group_ri);

	//CREATING GRID
	Panel p = new Panel (new GridLayout (5, 9));

	for (int i = 0 ; i < grids.length ; i++)
	{
	    grids [i] = new JButton ("");
	    grids [i].setPreferredSize (new Dimension (85, 85));
	    grids [i].setBackground (new Color (0, 158, 6));
	    if (i % 2 == 1)
		grids [i].setBackground (new Color (0, 120, 14));


	    if (i == 0 || i == 9 || i == 18 || i == 27 || i == 36) //excludes lawnmowers
	    {
		grids [i].setIcon (createImageIcon ("lawn_mower.png"));
		grids [i].setBackground (new Color (98, 59, 1));
	    }
	    p.add (grids [i]);

	    grids [i].setBorderPainted (false);
	    grids [i].addActionListener (this);
	    grids [i].setActionCommand ("" + i);

	} //end of for; sets up grid and lawnmowers

	//CREATING NEXT AND INSTRUCTION JLABEL
	Panel l = new Panel (new GridLayout (2, 1));
	JButton nextMove = new JButton (" NEXT>> ");
	nextMove.setBorderPainted (false);
	nextMove.setBackground (new Color (136, 11, 17));
	nextMove.setForeground (Color.white);
	nextMove.addActionListener (this);
	nextMove.setActionCommand ("NEXT");

	words = new JLabel ("PRESS THE NEXT BUTTON                                                       ");
	words.setFont (new Font ("Arial", Font.BOLD, 25));
	l.add (nextMove);
	//CREATING PLANT BUTTONS
	Panel g = new Panel ();

	for (int i = 0 ; i < plants.length ; i++)
	{
	    plants [i] = new JButton (createImageIcon ("sunflower" + i + ".jpg"));
	    plants [i].setOpaque (false);
	    plants [i].setContentAreaFilled (false);
	    plants [i].setBorderPainted (false);
	    plants [i].addActionListener (this);
	    plants [i].setActionCommand ("plant" + i);

	    g.add (plants [i]);
	} //end of for; sets up button plant thing
	
	JFrame progress= new JFrame();
	pi = new JProgressBar (0, 0, 100);
	pi.setValue (0);
	pi.setBackground(Color.white);
	pi.setForeground(new Color (136, 11, 17));
	pi.setStringPainted (true); 
	progress.getContentPane().add(pi);
	progress.resize(200,75);
	progress.setVisible(true);
       


	card4.add (top);
	card4.add (p, BorderLayout.CENTER);
	card4.add (g, BorderLayout.SOUTH);
	card4.add (l);
	card4.add (words, BorderLayout.SOUTH);
	p_card.add ("i4", card4);
	//fill up tracker
    } //end of init


    //****************************************************************ACTION PERFORMED********************************************************************************
    public void actionPerformed (ActionEvent e)
    {
	if (e.getActionCommand ().equals ("i1")) //screen one
	{
	    resize (920, 360);
	    cdLayout.show (p_card, "i2");
	}
	else if (e.getActionCommand ().equals ("i2")) //screen two
	{
	    cdLayout.show (p_card, "i3");
	    resize (1024, 680);
	}
	else if (e.getActionCommand ().equals ("i3")) //screen three
	{
	    stopMusic ();
	    playMusic ("day");
	    cdLayout.show (p_card, "i4");
	}

	else if (e.getActionCommand ().equals ("i5")) //screen 5, objective done
	{
	    resize (920, 360);
	    cdLayout.show (p_card, "i7");
	    stopMusic ();
	    playMusic ("Moongrains");
	}
	else if (e.getActionCommand ().equals ("i6")) //screen 6, losing screen
	{
	    resize (920, 360);
	    cdLayout.show (p_card, "i7");
	    stopMusic ();
	    playMusic ("Moongrains");
	}

	else if (e.getActionCommand ().equals ("i7")) //last screen
	{
	    stopMusic ();
	    playMusic ("day");
	    resize (1024, 680);
	    cdLayout.show (p_card, "i4");
	    restart ();
	}
	else if (e.getActionCommand ().equals ("no")) //exit option
	{
	    int select = JOptionPane.showConfirmDialog (null, "Are you sure?", "QUIT GAME", JOptionPane.YES_NO_OPTION);
	    if (select == JOptionPane.YES_OPTION)
		System.exit (0);
	}

	if (e.getActionCommand ().equals ("instructions")) //to see next set of instructions
	{
	    instructionCounter++;
	    if (instructionCounter > 7)
		instructionCounter -= instructionCounter;

	    instruct.setIcon (createImageIcon ("pvz-help" + instructionCounter + ".jpg"));
	} //end of instructions

	if (e.getActionCommand ().equals ("RESTART")) //to restart gameboard
	    restart ();
	if (e.getActionCommand ().equals ("back to instructions")) //allow player to move from game to instruction if needed
	    cdLayout.show (p_card, "i2");

	if (e.getActionCommand ().equals ("NEXT")) //next button
	{
	    press = 'p';
	    if (zombiecount == 21) //if 21 zombies are defeated, screen will automatically change
	    {
		stopMusic ();
		cdLayout.show (p_card, "i5");

	    }
	    else if (lose == true) //if zombie enters house, screen will automatically change
	    {
		stopMusic ();
		soundEffect ("GameOver");
		cdLayout.show (p_card, "i6");
		lose = false;
	    } //end of if



	    else if (nextcount2 == 1)
	    {
		words.setText ("Click on the Sun. Then Click Next");

		grids [30].setIcon (createImageIcon ("sunFAred.png"));
		tracker [30 / col] [30 % col] = 5;
		plants [0].setEnabled (false);
		plants [1].setEnabled (false);
		plants [2].setEnabled (false);
		plants [3].setEnabled (false);
	    } //FIRST STEP OF TUTORIAL

	    else if (nextcount2 == 2)
	    {
		words.setText ("You Gained 50 Sun. Plant a Sunflower, Then Click Next.");
		sun.setIcon (createImageIcon ("sunFApoint.png"));
		plants [0].setEnabled (true);

	    } //SECOND STEP OF TUTORIAL
	    else if (nextcount2 == 3)
	    {
		words.setText ("Sunflowers Also Give you Sun. Click the Sun.");

		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
			if (tracker [i] [j] == 1)
			{
			    grids [(i * 9) + j].setIcon (createImageIcon ("latest.png"));
			    tracker [i] [j] = 6;

			} //end of if

		    }
		} //end of 2d array search
	    } //THIRD STEP OF TUTORIAL
	    else if (nextcount2 == 4)
	    {
		sun.setIcon (createImageIcon ("sunFA.png"));
		shovel.setIcon (createImageIcon ("shovel1.jpg"));
		plants [0].setEnabled (false);
		words.setText ("If you Want to Remove Sunflower, Click on Shovel.");


	    } //FOURTH STEP OF TUTORIAL
	    else if (nextcount2 == 5)
	    {
		shovel.setIcon (createImageIcon ("shovel.jpg"));
		grids [20].setIcon (createImageIcon ("r.png"));
		grids [21].setIcon (createImageIcon ("e.png"));
		grids [22].setIcon (createImageIcon ("a.png"));
		grids [23].setIcon (createImageIcon ("d.png"));
		grids [24].setIcon (createImageIcon ("y.png"));
		nextcount2++;
		words.setText ("That's all for now.");


	    } //FIFTH STEP TO TUTORIAL
	    else if (nextcount2 == 6)
	    {
		grids [20].setIcon (createImageIcon (""));
		grids [21].setIcon (createImageIcon ("s.png"));
		grids [22].setIcon (createImageIcon ("e.png"));
		grids [23].setIcon (createImageIcon ("t.png"));
		grids [24].setIcon (createImageIcon (""));
		nextcount2++;
	    } //SIXTH STEP TO TUTORIAL

	    else if (nextcount2 == 7)
	    {
		grids [20].setIcon (createImageIcon ("p.png"));
		grids [21].setIcon (createImageIcon ("l.png"));
		grids [22].setIcon (createImageIcon ("a.png"));
		grids [23].setIcon (createImageIcon ("n.png"));
		grids [24].setIcon (createImageIcon ("t.png"));
		nextcount2++;
	    } //SEVENTH STEP TO TUTORIAL
	    else if (nextcount2 == 8)
	    {
		grids [20].setIcon (createImageIcon (""));
		grids [21].setIcon (createImageIcon (""));
		grids [22].setIcon (createImageIcon (""));
		grids [23].setIcon (createImageIcon (""));
		grids [24].setIcon (createImageIcon (""));
		nextcount2++;
	    } //EIGHTH STEP TO TUTORIAL
	    else
	    {

		plants [0].setEnabled (true);
		plants [1].setEnabled (true);
		plants [2].setEnabled (true);
		plants [3].setEnabled (true);

		//CREATING RANDOM SUNS TO DROP

		int random = (int) (Math.random () * 25) + 1;
		int randomtwo = (int) (Math.random () * 3) + 0;

		while (tracker [random / col] [random % col] != 0 && tracker [random / col] [random % col] != 5)
		    random = (int) (Math.random () * 25) + 1;

		int stepstoSun = 0;

		if (randomtwo == 1 && random != 0 && random != 9 && random != 18 && random != 27 && random != 36)
		{
		    grids [random].setIcon (createImageIcon ("sunFA.png"));
		    tracker [random / col] [random % col] = 5;
		} //end of if

		//CREATING SUN FROM SUNFLOWER TO POP UP
		int randomthree = (int) (Math.random () * 5) + 1;
		if (randomthree == 2 && checkingSun (sunflowerCount) != 0)
		{


		    for (int i = 0 ; i < row ; i++)
		    {
			for (int j = 0 ; j < col ; j++)
			{
			    if (tracker [i] [j] == 1)
			    {
				grids [(i * 9) + j].setIcon (createImageIcon ("latest.png"));
				tracker [i] [j] = 6;

			    } //end of if

			} //enf of first for
		    } //end of 2d array search
		} //end of else if
	    } //CREATING ZOMBIES TO COME USING NEXTCOUNT2

	    if (nextcount2 > 15)
	    {
		zombie (grids);                
		pi.setValue (zombiecount*5);
	    } //END OF IF NEXTCOUNT2 = 15

	} //end of next button

	if (e.getActionCommand ().equals ("plant0") && sunCount > 49)
	{
	    words1 = "sunflower.gif";
	    tracker1 = 1;
	    sunCount -= 50;
	    words.setText ("Sunflower Selected.");
	    nextcount2++;
	    select (grids);

	} //if sunflower button is pressed


	else if (e.getActionCommand ().equals ("plant1") && sunCount > 99)
	{
	    words1 = "Peashooter.gif";
	    tracker1 = 2;
	    sunCount -= 100;
	    words.setText ("Peashooter Selected.");
	    nextcount2 += 3;
	    select (grids);

	} //if peashooter button is pressed


	else if (e.getActionCommand ().equals ("plant2") && sunCount > 49)
	{
	    words1 = "wallnut.gif";
	    tracker1 = 3;
	    sunCount -= 50;
	    words.setText ("Wallnut Selected.");
	    nextcount2++;
	    select (grids);

	} //if wallnut button is pressed


	else if (e.getActionCommand ().equals ("plant3") && sunCount > 24)
	{
	    words1 = "potato-mine.gif";
	    tracker1 = 4;
	    sunCount -= 25;
	    words.setText ("Potato Mine Selected.");
	    nextcount2++;
	    select (grids);

	} //if potato mine button is pressed


	else if (e.getActionCommand ().equals ("shovel"))
	{
	    words1 = "";
	    inplace = true;
	    tracker1 = 0;

	} //if shovel button is pressed


	else if ((e.getActionCommand ().equals ("plant3") || e.getActionCommand ().equals ("plant2") || e.getActionCommand ().equals ("plant1") || e.getActionCommand ().equals ("plant0")) && sunCount < 25)
	    words.setText ("You Don't have enough Sun.");

	//uses words1 to track which plant button is clicked
	else
	{

	    for (int i = 0 ; i < grids.length ; i++)
	    {
		if ((e.getActionCommand ().equals ("" + i) && i != 0 && i != 9 && i != 18 && i != 27 && i != 36) && tracker [i / col] [i % col] == 0 && inplace == false && press == 'p')
		{
		    grids [i].setIcon (createImageIcon (words1));
		    tracker [i / col] [i % col] = tracker1;
		    words1 = "";
		    tracker1 = 0;
		    words.setText ("");
		    suncount.setText (": " + sunCount);
		    deselect (grids);
		    words.setIcon (createImageIcon (""));

		} // for planting plants

		else if ((e.getActionCommand ().equals ("" + i) && i != 0 && i != 9 && i != 18 && i != 27 && i != 36) && tracker [i / col] [i % col] != 0 && inplace == true && press == 'p')
		{
		    grids [i].setIcon (createImageIcon (words1));
		    tracker [i / col] [i % col] = tracker1;
		    words1 = "";
		    tracker1 = 0;
		    inplace = false;
		    words.setText (words2 + " removed.");
		    suncount.setText (": " + sunCount);
		    deselect (grids);
		    words.setIcon (createImageIcon (""));
		    nextcount2++;

		} //for removing plants
		else if (e.getActionCommand ().equals ("" + i) && tracker [i / col] [i % col] == 5 && inplace == false)
		{
		    words1 = "";
		    tracker1 = 0;
		    tracker [i / col] [i % col] = tracker1;
		    grids [i].setIcon (createImageIcon (words1));
		    sunCount += 50;
		    words.setText ("");
		    suncount.setText (": " + sunCount);
		    nextcount2++;
		    deselect (grids);
		    words.setIcon (createImageIcon (""));


		} // for collecting fallen suns
		else if (e.getActionCommand ().equals ("" + i) && tracker [i / col] [i % col] == 6 && inplace == false)
		{
		    words1 = "sunflower.gif";
		    tracker1 = 1; //DO NOT CHANGE THIS!
		    tracker [i / col] [i % col] = tracker1;
		    grids [i].setIcon (createImageIcon (words1));
		    sunCount += 25;
		    words.setText ("");
		    suncount.setText (": " + sunCount);
		    nextcount2++;
		    deselect (grids);
		    words.setIcon (createImageIcon (""));

		} //for harvesting suns from sunflwoers
	    } //end of for; allows planting in grid
	} //end of else
    } //END OF ACTION PERFORMED


    //************************************************************************CreateImageIcon method*******************************************************************
    protected static ImageIcon createImageIcon (String path)
    {
	java.net.URL imgURL = game.class.getResource (path);
	if (imgURL != null)
	    return new ImageIcon (imgURL);
	else
	{
	    System.err.println ("Couldn't find file: " + path);
	    return null;
	}
    } //end of createImageIcon method


    //****************************************************************************CHECKINGSUN******************************************************************
    public int checkingSun (int sunflowerCount)  //to check how many suns there are in the whole grid in order to allow them to harvest
    {
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (tracker [i] [j] == 1)
		{
		    sunflowerCount++;
		}
	    }
	} //end of 2d array search
	return sunflowerCount;
    } //End of CheckingSun method


    //*****************************************************************ZOMBIE METHOD********************************************************************************
    public void zombie (JButton grids[])  //method that controls interations and conditions of zombie, randomizes number to allow a zombie from one row to move at a time
    {
	int randomzombie = (int) (Math.random () * 5) + 1; //the randomized number

	if (randomzombie == 1 || randomzombie == 34) // for if the number is one
	{
	    if (zombiewalk == 9)
	    {
		grids [8 - zombiewalk + 1].setIcon (createImageIcon (""));
		tracker [(8 - zombiewalk + 1) / col] [(8 - zombiewalk + 1) % col] = 0;
		zombiewalk = 0;
		lose = true;
	    } //LOSING CONDITION

	    else if (zombielife1 <= 0)
	    {
		zombiecount++;
		words.setIcon (createImageIcon ("waskilled.png"));
		grids [8 - (zombiewalk - 1)].setIcon (createImageIcon (""));
		tracker [(8 - (zombiewalk - 1)) / col] [(8 - (zombiewalk - 1)) % col] = 0;
		zombielife1 = 1000;
		zombiewalk = 0;
	    } //WHEN A ZOMBIE DIES

	    else if (tracker [(8 - zombiewalk) / col] [(8 - zombiewalk) % col] == 4)
	    {
		grids [(8 - zombiewalk)].setIcon (createImageIcon (""));
		tracker [(8 - zombiewalk) / col] [(8 - zombiewalk) % col] = 0;

		if (zombiewalk != 0)
		{
		    grids [(8 - zombiewalk) + 1].setIcon (createImageIcon (""));
		    tracker [((8 - zombiewalk) + 1) / col] [((8 - zombiewalk) + 1) % col] = 0;
		}
		zombiewalk = 0;
		words.setIcon (createImageIcon ("k.png"));
		zombiecount++;
		zombielife1 = 1000;
	    } //GETTING POTATO MINE TO WORK

	    else if (zombiecount == 20)
	    {
		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
			if (tracker [i] [j] == 7)
			    grids [(i * 9) + j].setIcon (createImageIcon (""));
		    }
		}

		plants [0].setEnabled (false);
		plants [1].setEnabled (false);
		plants [2].setEnabled (false);
		plants [3].setEnabled (false);
		stopMusic ();
		soundEffect ("win");
		zombiecount = 21;
	    } //FIX WINNING CONDITION LATER

	    else if (tracker [(8 - zombiewalk) / col] [(8 - zombiewalk) % col] == 3)
	    {
		if (walnutcount1 < 5)
		{
		    grids [(8 - zombiewalk) + 1].setIcon (createImageIcon ("imps.gif"));
		    tracker [((8 - zombiewalk) + 1) / col] [((8 - zombiewalk) + 1) % col] = 7;
		    zombielife1 -= (100 * Peashooter (1));
		    walnutcount1++;
		} //end of if

		else
		{
		    grids [8 - (zombiewalk)].setIcon (createImageIcon ("imps.gif"));
		    tracker [(8 - zombiewalk) / col] [(8 - zombiewalk) % col] = 7;
		    grids [(8 - zombiewalk) + 1].setIcon (createImageIcon (""));
		    tracker [((8 - zombiewalk) + 1) / col] [((8 - zombiewalk) + 1) % col] = 0;
		    walnutcount1 -= 5;
		}
	    } //GETTING WALNUT TO WORK

	    else if (tracker [(8 - zombiewalk) / col] [(8 - zombiewalk) % col] == 10)
	    {
		lawnmower ();
		zombiewalk = 0;
	    } //GETTING LAWNMOWER TO WORK

	    else
	    {
		zombielife1 -= (100 * Peashooter (1));

		grids [8 - zombiewalk].setIcon (createImageIcon ("imps.gif"));
		tracker [(8 - zombiewalk) / col] [(8 - zombiewalk) % col] = 7;
		words.setText ("");
		words.setIcon (createImageIcon (""));
		if (zombiewalk != 0)
		{
		    grids [(8 - zombiewalk) + 1].setIcon (createImageIcon (""));
		    tracker [((8 - zombiewalk) + 1) / col] [((8 - zombiewalk) + 1) % col] = 0;
		}
		zombiewalk++;
	    } //GETTING ZOMBIE TO MOVE FORWARD

	}


	else if (randomzombie == 2 || randomzombie == 34) //for if the number is two
	{

	    if (zombiewalk1 == 9)
	    {
		grids [17 - zombiewalk1 + 1].setIcon (createImageIcon (""));
		tracker [(17 - zombiewalk1 + 1) / col] [(17 - zombiewalk1 + 1) % col] = 0;
		zombiewalk1 = 0;
		lose = true;
	    } //LOSING CONDITION

	    else if (zombielife2 <= 0)
	    {
		zombiecount++;
		words.setIcon (createImageIcon ("waskilled.png"));
		grids [17 - (zombiewalk1 - 1)].setIcon (createImageIcon (""));
		tracker [(17 - (zombiewalk1 - 1)) / col] [(17 - (zombiewalk1 - 1)) % col] = 0;
		zombielife2 = 1000;
		zombiewalk1 = 0;
	    }
	    else if (zombiecount == 20)
	    {
		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
			if (tracker [i] [j] == 7)
			    grids [(i * 9) + j].setIcon (createImageIcon (""));
		    }
		}
		plants [0].setEnabled (false);
		plants [1].setEnabled (false);
		plants [2].setEnabled (false);
		plants [3].setEnabled (false);
		stopMusic ();
		soundEffect ("win");
		zombiecount = 21;
	    } //FIX WINNING CONDITION LATER

	    else if (tracker [(17 - zombiewalk1) / col] [(17 - zombiewalk1) % col] == 4)
	    {
		grids [(17 - zombiewalk1)].setIcon (createImageIcon (""));
		tracker [(17 - zombiewalk1) / col] [(17 - zombiewalk1) % col] = 0;

		if (zombiewalk1 != 0)
		{
		    grids [(17 - zombiewalk1) + 1].setIcon (createImageIcon (""));
		    tracker [((17 - zombiewalk1) + 1) / col] [((17 - zombiewalk1) + 1) % col] = 0;
		}
		zombiewalk1 = 0;
		words.setIcon (createImageIcon ("k.png"));
		zombiecount++;
		zombielife2 = 1000;
	    } //GETTING POTATO MINE TO WORK

	    else if (tracker [(17 - zombiewalk1) / col] [(17 - zombiewalk1) % col] == 3)
	    {
		if (walnutcount < 5)
		{
		    grids [(17 - zombiewalk1) + 1].setIcon (createImageIcon ("imps.gif"));
		    tracker [((17 - zombiewalk1) + 1) / col] [((17 - zombiewalk1) + 1) % col] = 7;
		    zombielife2 -= (100 * Peashooter (2));
		    walnutcount++;
		} //end of if

		else
		{
		    grids [17 - (zombiewalk1)].setIcon (createImageIcon ("imps.gif"));
		    tracker [(17 - zombiewalk1) / col] [(17 - zombiewalk1) % col] = 7;
		    grids [(17 - zombiewalk1) + 1].setIcon (createImageIcon (""));
		    tracker [((17 - zombiewalk1) + 1) / col] [((17 - zombiewalk1) + 1) % col] = 0;
		    walnutcount -= 5;
		}
	    } //GETTING WALNUT TO WORK

	    else if (tracker [(17 - zombiewalk1) / col] [(17 - zombiewalk1) % col] == 10)
	    {
		lawnmower2 ();
		zombiewalk1 = 0;
	    } //TO GET LAWNMOWER TO WORK

	    else
	    {
		zombielife2 -= (100 * Peashooter (2));

		grids [17 - zombiewalk1].setIcon (createImageIcon ("imps.gif"));
		tracker [(17 - zombiewalk1) / col] [(17 - zombiewalk1) % col] = 7;
		words.setIcon (createImageIcon (""));
		words.setText ("");

		if (zombiewalk1 != 0)
		{
		    grids [(17 - zombiewalk1) + 1].setIcon (createImageIcon (""));
		    tracker [((17 - zombiewalk1) + 1) / col] [((17 - zombiewalk1) + 1) % col] = 0;
		}
		zombiewalk1++;
	    } //TO GET ZOMBIE TO WALK FORWARD

	} //end of number 2


	else if (randomzombie == 3 || randomzombie == 34)
	{
	    if (zombiewalk2 == 9)
	    {
		grids [26 - zombiewalk2 + 1].setIcon (createImageIcon (""));
		tracker [(26 - zombiewalk2 + 1) / col] [(26 - zombiewalk2 + 1) % col] = 0;
		zombiewalk2 = 0;
		lose = true;
		//INSERT LOSING STUFF HERE
	    } //LOSING CONDITION

	    else if (zombielife3 <= 0)
	    {
		zombiecount++;
		words.setIcon (createImageIcon ("waskilled.png"));
		grids [26 - (zombiewalk2 - 1)].setIcon (createImageIcon (""));
		tracker [(26 - (zombiewalk2 - 1)) / col] [(26 - (zombiewalk2 - 1)) % col] = 0;
		zombielife3 = 1000;
		zombiewalk2 = 0;
	    } //For when a zombie dies
	    else if (zombiecount == 20)
	    {
		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
			if (tracker [i] [j] == 7)
			    grids [(i * 9) + j].setIcon (createImageIcon (""));
		    }
		}
		plants [0].setEnabled (false);
		plants [1].setEnabled (false);
		plants [2].setEnabled (false);
		plants [3].setEnabled (false);
		stopMusic ();
		soundEffect ("win");
		zombiecount = 21;
	    } //FIX WINNING CONDITION LATER

	    else if (tracker [(26 - zombiewalk2) / col] [(26 - zombiewalk2) % col] == 4)
	    {
		grids [(26 - zombiewalk2)].setIcon (createImageIcon (""));
		tracker [(26 - zombiewalk2) / col] [(26 - zombiewalk2) % col] = 0;

		if (zombiewalk2 != 0)
		{
		    grids [(26 - zombiewalk2) + 1].setIcon (createImageIcon (""));
		    tracker [((26 - zombiewalk2) + 1) / col] [((26 - zombiewalk2) + 1) % col] = 0;
		}
		zombiewalk2 = 0;
		words.setIcon (createImageIcon ("k.png"));
		zombiecount++;
		zombielife3 = 1000;
	    } //GETTING POTATO MINE TO WORK

	    else if (tracker [(26 - zombiewalk2) / col] [(26 - zombiewalk2) % col] == 3)
	    {
		if (walnutcount2 < 5)
		{
		    grids [(26 - zombiewalk2) + 1].setIcon (createImageIcon ("imps.gif"));
		    tracker [((26 - zombiewalk2) + 1) / col] [((26 - zombiewalk2) + 1) % col] = 7;
		    zombielife3 -= (100 * Peashooter (3));
		    walnutcount2++;
		} //end of if

		else
		{
		    grids [26 - (zombiewalk2)].setIcon (createImageIcon ("imps.gif"));
		    tracker [(26 - zombiewalk2) / col] [(26 - zombiewalk2) % col] = 7;
		    grids [(26 - zombiewalk2) + 1].setIcon (createImageIcon (""));
		    tracker [((26 - zombiewalk2) + 1) / col] [((26 - zombiewalk2) + 1) % col] = 0;
		    walnutcount2 -= 5;
		}
	    } //GETTING WALNUT TO WORK

	    else if (tracker [(26 - zombiewalk2) / col] [(26 - zombiewalk2) % col] == 10)
	    {
		lawnmower3 ();
		zombiewalk2 = 0;
	    } //for getting lawnmower to work

	    else
	    {
		zombielife3 -= (100 * Peashooter (3));
		grids [26 - zombiewalk2].setIcon (createImageIcon ("imps.gif"));
		words.setText ("");
		tracker [(26 - zombiewalk2) / col] [(26 - zombiewalk2) % col] = 7;
		words.setIcon (createImageIcon (""));
		if (zombiewalk2 != 0)
		{
		    grids [(26 - zombiewalk2) + 1].setIcon (createImageIcon (""));
		    tracker [((26 - zombiewalk2) + 1) / col] [((26 - zombiewalk2) + 1) % col] = 0;
		}
		zombiewalk2++;
	    } //get zombie three to move forward
	} //end of zombie three


	else if (randomzombie == 4 || randomzombie == 34)
	{
	    if (zombiewalk3 == 9)
	    {
		grids [35 - zombiewalk3 + 1].setIcon (createImageIcon (""));
		tracker [(35 - zombiewalk3 + 1) / col] [(35 - zombiewalk3 + 1) % col] = 0;
		zombiewalk3 = 0;
		lose = true;
	    } //LOSING CONDITION

	    else if (zombielife4 <= 0)
	    {
		zombiecount++;
		words.setIcon (createImageIcon ("waskilled.png"));
		grids [35 - (zombiewalk3 - 1)].setIcon (createImageIcon (""));
		tracker [(35 - (zombiewalk3 - 1)) / col] [(35 - (zombiewalk3 - 1)) % col] = 0;
		zombielife4 = 1000;
		zombiewalk3 = 0;
	    } //if the zombie dies
	    else if (zombiecount == 20)
	    {
		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
			if (tracker [i] [j] == 7)
			    grids [(i * 9) + j].setIcon (createImageIcon (""));
		    }
		}
		plants [0].setEnabled (false);
		plants [1].setEnabled (false);
		plants [2].setEnabled (false);
		plants [3].setEnabled (false);
		stopMusic ();
		soundEffect ("win");
		zombiecount = 21;
	    } //FIX WINNING CONDITION LATER

	    else if (tracker [(35 - zombiewalk3) / col] [(35 - zombiewalk3) % col] == 4)
	    {
		grids [(35 - zombiewalk3)].setIcon (createImageIcon (""));
		tracker [(35 - zombiewalk3) / col] [(35 - zombiewalk3) % col] = 0;

		if (zombiewalk3 != 0)
		{
		    grids [(35 - zombiewalk3) + 1].setIcon (createImageIcon (""));
		    tracker [((35 - zombiewalk3) + 1) / col] [((35 - zombiewalk3) + 1) % col] = 0;
		}
		zombiewalk3 = 0;
		words.setIcon (createImageIcon ("k.png"));
		zombiecount++;
		zombielife4 = 1000;
	    } //GETTING POTATO MINE TO WORK

	    else if (tracker [(35 - zombiewalk3) / col] [(35 - zombiewalk3) % col] == 3)
	    {
		if (walnutcount3 < 5)
		{
		    grids [(35 - zombiewalk3) + 1].setIcon (createImageIcon ("imps.gif"));
		    tracker [((35 - zombiewalk3) + 1) / col] [((35 - zombiewalk3) + 1) % col] = 7;
		    zombielife4 -= (100 * Peashooter (4));
		    walnutcount3++;
		} //end of if

		else
		{
		    grids [35 - (zombiewalk3)].setIcon (createImageIcon ("imps.gif"));
		    tracker [(35 - zombiewalk3) / col] [(35 - zombiewalk3) % col] = 7;
		    grids [(35 - zombiewalk3) + 1].setIcon (createImageIcon (""));
		    tracker [((35 - zombiewalk3) + 1) / col] [((35 - zombiewalk3) + 1) % col] = 0;
		    walnutcount3 -= 5;
		}
	    } //GETTING WALNUT TO WORK

	    else if (tracker [(35 - zombiewalk3) / col] [(35 - zombiewalk3) % col] == 10)
	    {
		lawnmower4 ();
		zombiewalk3 = 0;
	    } //for lawnmower to work

	    else
	    {
		zombielife4 -= (100 * Peashooter (4));
		grids [35 - zombiewalk3].setIcon (createImageIcon ("imps.gif"));
		tracker [(35 - zombiewalk3) / col] [(35 - zombiewalk3) % col] = 7;
		words.setText ("");
		words.setIcon (createImageIcon (""));
		if (zombiewalk3 != 0)
		{
		    grids [(35 - zombiewalk3) + 1].setIcon (createImageIcon (""));
		    tracker [((35 - zombiewalk3) + 1) / col] [((35 - zombiewalk3) + 1) % col] = 0;
		}
		zombiewalk3++;
	    } // to get zombie to walk forward

	}

	else if (randomzombie == 5 || randomzombie == 34)
	{
	    if (zombiewalk4 == 9)
	    {
		grids [44 - zombiewalk4 + 1].setIcon (createImageIcon (""));
		tracker [(44 - zombiewalk4 + 1) / col] [(44 - zombiewalk4 + 1) % col] = 0;
		zombiewalk1 = 0;
		lose = true;
		//INSERT LOSING STUFF HERE
	    } //LOSING CONDITION

	    else if (zombielife5 <= 0)
	    {
		zombiecount++;
		words.setIcon (createImageIcon ("waskilled.png"));
		grids [44 - (zombiewalk4 - 1)].setIcon (createImageIcon (""));
		tracker [(44 - (zombiewalk4 - 1)) / col] [(44 - (zombiewalk4 - 1)) % col] = 0;
		zombielife5 = 1000;
		zombiewalk4 = 0;
	    } // for when a zombie dies
	    else if (zombiecount == 20)
	    {
		for (int i = 0 ; i < row ; i++)
		{
		    for (int j = 0 ; j < col ; j++)
		    {
			if (tracker [i] [j] == 7)
			    grids [(i * 9) + j].setIcon (createImageIcon (""));
		    }
		}
		plants [0].setEnabled (false);
		plants [1].setEnabled (false);
		plants [2].setEnabled (false);
		plants [3].setEnabled (false);
		stopMusic ();
		soundEffect ("win");
		zombiecount = 21;
	    } //FIX WINNING CONDITION LATER

	    else if (tracker [(44 - zombiewalk4) / col] [(44 - zombiewalk4) % col] == 4)
	    {
		grids [(44 - zombiewalk4)].setIcon (createImageIcon (""));
		tracker [(44 - zombiewalk4) / col] [(44 - zombiewalk4) % col] = 0;

		if (zombiewalk4 != 0)
		{
		    grids [(44 - zombiewalk4) + 1].setIcon (createImageIcon (""));
		    tracker [((44 - zombiewalk4) + 1) / col] [((44 - zombiewalk4) + 1) % col] = 0;
		}
		zombiewalk4 = 0;
		words.setIcon (createImageIcon ("k.png"));
		zombiecount++;
		zombielife5 = 1000;
	    } //GETTING POTATO MINE TO WORK

	    else if (tracker [(44 - zombiewalk4) / col] [(44 - zombiewalk4) % col] == 3)
	    {
		if (walnutcount4 < 5)
		{
		    grids [(44 - zombiewalk4) + 1].setIcon (createImageIcon ("imps.gif"));
		    tracker [((44 - zombiewalk4) + 1) / col] [((44 - zombiewalk4) + 1) % col] = 7;
		    zombielife5 -= (100 * Peashooter (5));
		    walnutcount4++;
		} //end of if

		else
		{
		    grids [44 - (zombiewalk4)].setIcon (createImageIcon ("imps.gif"));
		    tracker [(44 - zombiewalk4) / col] [(44 - zombiewalk4) % col] = 7;
		    grids [(44 - zombiewalk4) + 1].setIcon (createImageIcon (""));
		    tracker [((44 - zombiewalk4) + 1) / col] [((44 - zombiewalk4) + 1) % col] = 0;
		    walnutcount4 -= 5;
		}
	    } //GETTING WALNUT TO WORK

	    else if (tracker [(44 - zombiewalk4) / col] [(44 - zombiewalk4) % col] == 10)
	    {
		lawnmower5 ();
		zombiewalk4 = 0;
	    } // to get lanwmower to work

	    else
	    {
		zombielife5 -= (100 * Peashooter (5));
		grids [44 - zombiewalk4].setIcon (createImageIcon ("imps.gif"));
		words.setIcon (createImageIcon (""));
		words.setText ("");
		tracker [(44 - zombiewalk4) / col] [(44 - zombiewalk4) % col] = 7;
		if (zombiewalk4 != 0)
		{
		    grids [(44 - zombiewalk4) + 1].setIcon (createImageIcon (""));
		    tracker [((44 - zombiewalk4) + 1) / col] [((44 - zombiewalk4) + 1) % col] = 0;
		}
		zombiewalk4++;
	    } // to get zombie to move forward

	}
    } //END OF zombie CLASS


    //**********************************************************************LAWNMOWER METHOD******************************************************************************
    public void lawnmower ()  // for the first lawnmower in the row
    {
	if (lawnmowerCount == 0) // shows lawnmower killing the zombie
	{
	    grids [0].setIcon (createImageIcon (""));
	    grids [8].setIcon (createImageIcon ("lawn_mower.png"));
	    tracker [8 / col] [8 % col] = 10;
	    tracker [0 / col] [0 % col] = 10;
	    words.setIcon (createImageIcon ("lawn1.png"));

	    for (int i = (0) ; i < 1 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    if (tracker [i] [j] == 7)
		    {
			tracker [i] [j] = 0;
			grids [(i * 9) + j].setIcon (createImageIcon (""));
			grids [(i * 9) + j].setBackground (new Color (128, 0, 0));
		    }

		    grids [(i * 9) + j].setBorderPainted (true);
		}
	    } //END OF FOR
	    lawnmowerCount = 1;

	} //end of if

	else //resets everything that the lawnmower destroyed to its orginal form, minus the zombie
	{
	    grids [0 + 1].setBackground (new Color (0, 120, 14));
	    grids [8].setIcon (createImageIcon (""));
	    words.setIcon (createImageIcon ("lawn2.png"));
	    tracker [8 / col] [8 % col] = 0;
	    tracker [0 / col] [0 % col] = 0;

	    for (int i = 0 ; i < 1 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    grids [(i * 9) + j].setBorderPainted (false);
		}
	    }
	    lawnmowerCount = 0;
	    zombiecount++;
	    zombielife1 = 1000;
	}
    } //END OF LAWNMOWER CLASS


    //**************************************************************LAWNMOWER2***************************************************************************************
    public void lawnmower2 ()  //second lawnmower in row
    {
	if (lawnmowerCount2 == 0)
	{
	    grids [9].setIcon (createImageIcon (""));
	    grids [17].setIcon (createImageIcon ("lawn_mower.png"));
	    tracker [17 / col] [17 % col] = 10;
	    tracker [9 / col] [9 % col] = 10;
	    words.setIcon (createImageIcon ("lawn1.png"));

	    for (int i = 1 ; i < 2 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    if (tracker [i] [j] == 7)
		    {
			tracker [i] [j] = 0;
			grids [(i * 9) + j].setIcon (createImageIcon (""));
			grids [(i * 9) + j].setBackground (new Color (128, 0, 0));
		    }

		    grids [(i * 9) + j].setBorderPainted (true);
		}
	    } //END OF FOR
	    lawnmowerCount2 = 1;
	}

	else
	{
	    grids [9 + 1].setBackground (new Color (0, 158, 6));
	    grids [17].setIcon (createImageIcon (""));
	    words.setIcon (createImageIcon ("lawn2.png"));
	    tracker [17 / col] [17 % col] = 0;
	    tracker [9 / col] [9 % col] = 0;

	    for (int i = 1 ; i < 2 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    grids [(i * 9) + j].setBorderPainted (false);
		}
	    }
	    lawnmowerCount2 = 0;
	    zombiecount++;
	    zombielife2 = 1000;
	}
    } //END OF LAWNMOWER CLASS


    //**************************************************************LAWNMOWER3****************************************************************************************
    public void lawnmower3 ()  //third lawnmower in row
    {
	if (lawnmowerCount3 == 0)
	{
	    grids [18].setIcon (createImageIcon (""));
	    grids [26].setIcon (createImageIcon ("lawn_mower.png"));
	    tracker [26 / col] [26 % col] = 10;
	    tracker [18 / col] [18 % col] = 10;
	    words.setIcon (createImageIcon ("lawn1.png"));

	    for (int i = 2 ; i < 3 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    if (tracker [i] [j] == 7)
		    {
			tracker [i] [j] = 0;
			grids [(i * 9) + j].setIcon (createImageIcon (""));
			grids [(i * 9) + j].setBackground (new Color (128, 0, 0));
		    }

		    grids [(i * 9) + j].setBorderPainted (true);
		}
	    } //END OF FOR
	    lawnmowerCount3 = 1;
	}
	else
	{
	    grids [18 + 1].setBackground (new Color (0, 120, 14));
	    grids [26].setIcon (createImageIcon (""));
	    words.setIcon (createImageIcon ("lawn2.png"));
	    tracker [26 / col] [26 % col] = 0;
	    tracker [18 / col] [18 % col] = 0;

	    for (int i = 2 ; i < 3 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    grids [(i * 9) + j].setBorderPainted (false);
		}
	    }
	    lawnmowerCount3 = 0;
	    zombiecount++;
	    zombielife3 = 1000;
	}
    } //END OF LAWNMOWER CLASS


    //**************************************************************LAWNMOWER4***************************************************************************************
    public void lawnmower4 ()  //fourth lawnmower in row
    {
	if (lawnmowerCount4 == 0)
	{
	    grids [27].setIcon (createImageIcon (""));
	    grids [35].setIcon (createImageIcon ("lawn_mower.png"));
	    tracker [35 / col] [35 % col] = 10;
	    tracker [27 / col] [27 % col] = 10;
	    words.setIcon (createImageIcon ("lawn1.png"));

	    for (int i = 3 ; i < 4 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    if (tracker [i] [j] == 7)
		    {
			tracker [i] [j] = 0;
			grids [(i * 9) + j].setIcon (createImageIcon (""));
			grids [(i * 9) + j].setBackground (new Color (128, 0, 0));
		    }

		    grids [(i * 9) + j].setBorderPainted (true);
		}
	    } //END OF FOR
	    lawnmowerCount4 = 1;
	}

	else
	{
	    grids [27 + 1].setBackground (new Color (0, 158, 6));
	    grids [35].setIcon (createImageIcon (""));
	    words.setIcon (createImageIcon ("lawn2.png"));
	    tracker [35 / col] [35 % col] = 0;
	    tracker [27 / col] [27 % col] = 0;

	    for (int i = 3 ; i < 4 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    grids [(i * 9) + j].setBorderPainted (false);
		}
	    }
	    lawnmowerCount4 = 0;
	    zombiecount++;
	    zombielife4 = 1000;
	}
    } //END OF LAWNMOWER CLASS


    //**************************************************************LAWNMOWER5*************************************************************************************
    public void lawnmower5 ()  //last lawnmower in row
    {
	if (lawnmowerCount5 == 0)
	{
	    grids [36].setIcon (createImageIcon (""));
	    grids [44].setIcon (createImageIcon ("lawn_mower.png"));
	    tracker [44 / col] [44 % col] = 10;
	    tracker [36 / col] [36 % col] = 10;
	    words.setIcon (createImageIcon ("lawn1.png"));

	    for (int i = 4 ; i < 5 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    if (tracker [i] [j] == 7)
		    {
			tracker [i] [j] = 0;
			grids [(i * 9) + j].setIcon (createImageIcon (""));
			grids [(i * 9) + j].setBackground (new Color (128, 0, 0));
		    }

		    grids [(i * 9) + j].setBorderPainted (true);
		}
	    } //END OF FOR
	    lawnmowerCount5 = 1;
	}
	else
	{
	    grids [36 + 1].setBackground (new Color (0, 120, 14));
	    grids [44].setIcon (createImageIcon (""));
	    words.setIcon (createImageIcon ("lawn2.png"));
	    tracker [44 / col] [44 % col] = 0;
	    tracker [36 / col] [36 % col] = 0;

	    for (int i = 4 ; i < 5 ; i++)
	    {
		for (int j = 0 ; j < col ; j++)
		{
		    grids [(i * 9) + j].setBorderPainted (false);
		}
	    }
	    lawnmowerCount5 = 0;
	    zombiecount++;
	    zombielife5 = 1000;
	}
    } //END OF LAWNMOWER CLASS


    //**************************************************************PEASHOOTER CHECKER********************************************************************************
    public int Peashooter (int rowNum)  // method to count the number of peashooters in a row, given the row
    {
	int shooterNum = 0;

	for (int i = (rowNum - 1) ; i < rowNum ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (tracker [i] [j] == 2)
		    shooterNum++;
	    }
	}
	return shooterNum;
    } //end of Peashooter Method


    //*************************************************************SELECTING OPEN POSITIONS***************************************************************************
    public void select (JButton grids[])  // method for selecting buttons
    {
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		if (tracker [i] [j] == 0)
		    grids [(i * 9) + j].setBorderPainted (true);
		else if (tracker [18 / col] [18 % col] == 10 || tracker [18 / col] [18 % col] == 0)
		{
		    grids [9].setBorderPainted (false);
		    grids [18].setBorderPainted (false);
		    grids [27].setBorderPainted (false);
		    grids [36].setBorderPainted (false);
		}
	    } //end of j for
	} //end of for
    }


    //**************************************************DESELECTING POSITIONS*******************************************************************************************
    public void deselect (JButton grids[])  // method for deselcting buttons
    {
	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
	    {
		grids [(i * 9) + j].setBorderPainted (false);

	    } //end of j for
	} //end of for
    } // en dof deelect method


    //*******************************************************RESTART*******************************************************************************************

    public void restart ()  //method for restarting everything
    {
	sunCount = 0;
	sunflowerCount = 0;
	words1 = ""; //track for plant button clicky thing
	words2 = "";
	tracker1 = 0;

	inplace = false;
	lawnmowerCount = 0;
	int lawnmowerCount2 = 0;
	int lawnmowerCount3 = 0;
	int lawnmowerCount4 = 0;
	int lawnmowerCount5 = 0;
	zombiecount = 0;

	nextcount2 = 1;
	press = 'n';

	zombiewalk = 0;
	zombiewalk1 = 0;
	zombiewalk2 = 0;
	zombiewalk3 = 0;
	zombiewalk4 = 0;

	zombielife1 = 1000;
	zombielife2 = 1000;
	zombielife3 = 1000;
	zombielife4 = 1000;
	zombielife5 = 1000;

	walnutcount = 0;
	walnutcount1 = 0;
	walnutcount2 = 0;
	walnutcount3 = 0;
	walnutcount4 = 0;

	for (int i = 0 ; i < grids.length ; i++)
	    grids [i].setIcon (createImageIcon (""));

	for (int i = 0 ; i < row ; i++)
	{
	    for (int j = 0 ; j < col ; j++)
		tracker [i] [j] = 0;
	}


	tracker [0] [0] = 10;
	tracker [1] [0] = 10;
	tracker [2] [0] = 10;
	tracker [3] [0] = 10;
	tracker [4] [0] = 10;
	grids [0].setIcon (createImageIcon ("lawn_mower.png"));
	grids [9].setIcon (createImageIcon ("lawn_mower.png"));
	grids [18].setIcon (createImageIcon ("lawn_mower.png"));
	grids [27].setIcon (createImageIcon ("lawn_mower.png"));
	grids [36].setIcon (createImageIcon ("lawn_mower.png"));
	suncount.setText (": " + sunCount + "     ");

    } //end of restart


    //********************************************************************Music**************************************************************************************
    public static void playMusic (String filepath)  // Method to allow music of 1MB size or smaller
    {

	try
	{
	    BGM = new AudioStream (new FileInputStream (filepath + ".wav")); //set song
	    MD = BGM.getData (); //get data fom song
	    loop = new ContinuousAudioDataStream (MD); //set as loop
	}


	catch (IOException error)  //error
	{
	    System.out.println ("Audio - File not found.");
	}


	MGP.start (loop); //start running loop
    } //end method playMusic


    //*********************************************************************STOP MUSIC****************************************************************
    public static void stopMusic ()
    {
	MGP.stop (loop);
    } //end method stopMusic


    //******************************************************************SOUND EFFECT****************************************************************
    public static void soundEffect (String filepath)
    {
	//initialize objects
	AudioPlayer SEP = AudioPlayer.player; //declare sound effect player
	AudioStream SE; //declare sound effect
	AudioData MA; //declare audio data
	AudioDataStream play = null; //set as single run (NOT LOOP)

	try
	{
	    SE = new AudioStream (new FileInputStream (filepath + ".wav")); //set file
	    MA = SE.getData (); //get data from file
	    play = new AudioDataStream (MA); //set data to play once (NOT
	}
	catch (IOException error)  //error
	{
	    System.out.println ("Audio - File not found.");
	}
	SEP.start (play);
    } //end method soundEffect
} //end of class
