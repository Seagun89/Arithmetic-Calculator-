/*RESOURCE:
 * https://www.geeksforgeeks.org/java-swing-jmenubar/
 * https://www.youtube.com/watch?v=BoOaFQLHNpQ
 * https://www.youtube.com/watch?v=7sLg-pyCtrw
 * https://www.youtube.com/watch?v=AK1TlaxBhbs
 * https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
 * https://www.codejava.net/java-se/swing/setting-shortcut-key-and-hotkey-for-menu-item-and-button-in-swing
 * https://www.youtube.com/watch?v=tBmBiJVfvUo
 * https://stackoverflow.com/questions/54982235/speak-text-as-type
 *
 * https://www.joe0.com/2014/03/24/how-to-fix-apache-tomcat-v7-0-not-showing-in-eclipse-server-runtime-environments-kepler-instructions/

 * http://www.java2s.com/Code/Jar/CatalogJar.htm
 * https://stackoverflow.com/questions/27808248/java-freetts-missing-voice
 * https://stackoverflow.com/questions/4213644/eclipse-how-do-i-add-the-javax-servlet-package-to-a-project/8898954
 * https://www.youtube.com/watch?v=7sLg-pyCtrw/
 * https://www.youtube.com/watch?v=4OdMztrDyOs
 * https://www.java-examples.com/disable-button-example
 * https://www.java-examples.com/jtextfield-disable-enable-example
 * http://www.learningaboutelectronics.com/Articles/How-to-check-if-a-text-field-is-empty-in-JavaFX.php
 * https://examples.javacodegeeks.com/desktop-java/swing/jtextfield/create-read-only-non-editable-jtextfield/
 * https://stackoverflow.com/questions/25355377/unable-to-make-text-areas-uneditable-in-java
 * 
 * https://www.javaer101.com/en/article/104018479.html
 * https://www.codota.com/code/java/methods/edu.cmu.sphinx.api.Configuration/setGrammarPath
 * 
 * https://stackoverflow.com/questions/61440935/java-swing-how-to-allow-user-enter-jtextfield-again-when-wrong-input
 */
package calc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Port;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;

public class MyCalcView {

	MyCalcModel calcmodel;
	
	private Logger logger = Logger.getLogger(getClass().getName()); // Logger
		
	private JFrame frame;
	private JSeparator separator;
	private String newString;
	private JTextField outputbox;
	final JTextField stringField;
	final JRadioButton speechButton;
	final JRadioButton textButton;
	final JLabel ModeSelection;
	final JButton clearBtn;
	final JButton calcBtn;
	final JButton rightPBtn;
	final JButton leftPBtn;
	final JButton powBtn;
	final JButton multBtn;
	final JButton subBtn;
	final JButton addBtn;
	final JButton nineBtn;
	final JButton eightBtn;
	final JButton sevenBtn;
	final JButton sixBtn;
	final JButton fiveBtn;
	final JButton fourBtn;
	final JButton threeBtn;
	final JButton twoBtn;
	final JButton oneBtn;
	final JButton zeroBtn;
	private JMenu x; // JMenu
	private JMenuItem m1, m2;  // Menu items
	private JLabel note;
    private String resultText;
	private JMenuBar mb;
	
/*Voice*/
	Voice voice;
	
/*LiveRecognizer*/	
	private LiveSpeechRecognizer recognizer; 
	
/*Threads*/
		Thread	speechThread;
		Thread	resourcesThread;

	/**
	 * Initialize the contents of the frame.
	 * @param theModel
	 */
//Intialize Method will have View and Control.	
//View: Creates the view for the calculator holds the buttons action listeners.
//Control: Interaction between the model and the view		
   public MyCalcView(MyCalcModel modelCon) {
		
/*Set up the logic*/
    	calcmodel = modelCon;
    	
/*Set up the frame*/	
		frame = new JFrame("Arithmetic Expressions Calculator");
		frame.setBounds(100, 100, 463, 697);
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 20));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);  
        
/*Create a panel*/        
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_1.setBounds(10, 33, 429, 42);
		frame.getContentPane().add(panel_1);
		
/*Create label name for the panel*/		
		JLabel lblNewLabel_1 = new JLabel("Calculator for Arithmetic Expressions ");
		lblNewLabel_1.setToolTipText("");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 429, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(87, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE))
		);//Close setHorizontalGroup
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 42, Short.MAX_VALUE)
				.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
		);//Close setVerticalGroup
		panel_1.setLayout(gl_panel_1);

/*textField*/
		stringField = new JTextField();
		stringField.setFont(new Font("Ink Free", Font.BOLD, 24));
		stringField.setBounds(39, 86, 184, 50);
		frame.getContentPane().add(stringField);
		stringField.setEditable(true); 
		
/*Mode Selection Label*/
		ModeSelection = new JLabel("Mode Selection");
		ModeSelection.setHorizontalAlignment(SwingConstants.CENTER);
		ModeSelection.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
		ModeSelection.setBounds(300, 154, 127, 23);
		frame.getContentPane().add(ModeSelection);

/*zeroBtn*/		
		zeroBtn = new JButton("0");
		zeroBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "0");
			}//Close actionPerformed 
		});//Close addActionListener
		zeroBtn.setBounds(39, 553, 70, 70);
		frame.getContentPane().add(zeroBtn);

/*oneBtn*/		
		oneBtn = new JButton("1");
		oneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "1");
			}//Close actionPerformed 
		});//Close addActionListener
		oneBtn.setBounds(39, 453, 70, 70);
		frame.getContentPane().add(oneBtn);
		
/*twoBtn*/
		twoBtn = new JButton("2");
		twoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "2");
			}//Close actionPerformed 
		});//Close addActionListener
		twoBtn.setBounds(129, 453, 70, 70);
		frame.getContentPane().add(twoBtn);
		
/*threeBtn*/
		threeBtn = new JButton("3");
		threeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "3");
			}//Close actionPerformed 
		});//Close addActionListener
		threeBtn.setBounds(219, 453, 70, 70);
		frame.getContentPane().add(threeBtn);
		
/*fourBtn*/
		fourBtn = new JButton("4");
		fourBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "4");
			}//Close actionPerformed 
		});//Close addActionListener
		fourBtn.setBounds(39, 353, 70, 70);
		frame.getContentPane().add(fourBtn);
		
/*fiveBtn*/
		fiveBtn = new JButton("5");
		fiveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "5");
			}//Close actionPerformed 
		});//Close addActionListener
		fiveBtn.setBounds(129, 353, 70, 70);
		frame.getContentPane().add(fiveBtn);
		
/*sixBtn*/
		sixBtn = new JButton("6");
		sixBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "6");
			}//Close actionPerformed 
		});//Close addActionListener
		sixBtn.setBounds(219, 353, 70, 70);
		frame.getContentPane().add(sixBtn);
		
/*sevenBtn*/
		sevenBtn = new JButton("7");
		sevenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "7");
			}//Close actionPerformed 
		});//Close addActionListener
		sevenBtn.setBounds(39, 253, 70, 70);
		frame.getContentPane().add(sevenBtn);
		
/*eightBtn*/
		eightBtn = new JButton("8");
		eightBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "8");
			}//Close actionPerformed 
		});//Close addActionListener
		eightBtn.setBounds(129, 253, 70, 70);
		frame.getContentPane().add(eightBtn);
		
/*nineBtn*/
		nineBtn = new JButton("9");
		nineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "9");
			}//Close actionPerformed 
		});//Close addActionListener
		nineBtn.setBounds(219, 253, 70, 70);
		frame.getContentPane().add(nineBtn);
		
/*addBtn*/
		addBtn = new JButton("+");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "+");
			}//Close actionPerformed 
		});//Close addActionListener
		addBtn.setBounds(309, 453, 70, 70);
		frame.getContentPane().add(addBtn);
		
/*subBtn*/
		subBtn = new JButton("-");
		subBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "-");
			}//Close actionPerformed 
		});//Close addActionListener
		subBtn.setBounds(309, 353, 70, 70);
		frame.getContentPane().add(subBtn);
		
/*multBtn*/
		multBtn = new JButton("*");
		multBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "*");
			}//Close actionPerformed 
		});//Close addActionListener
		multBtn.setBounds(309, 253, 70, 70);
		frame.getContentPane().add(multBtn);
		
/*powBtn*/
		powBtn = new JButton("<html>x<SUP>y</SUP></html>");
		powBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "^");
			}//Close actionPerformed 
		});//Close addActionListener
		powBtn.setBounds(219, 153, 70, 70);
		frame.getContentPane().add(powBtn);
		
/*leftPBtn*/
		leftPBtn = new JButton("(");
		leftPBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + "(");
			}//Close actionPerformed 
		});//Close addActionListener
		leftPBtn.setBounds(39, 153, 70, 70);
		frame.getContentPane().add(leftPBtn);
		
/*rightPBtn*/
		rightPBtn = new JButton(")");
		rightPBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText(stringField.getText() + ")");
			}//Close actionPerformed 
		});//Close addActionListener
		rightPBtn.setBounds(129, 153, 70, 70);
		frame.getContentPane().add(rightPBtn);
		
/*calcBtn*/ 
		calcBtn = new JButton("Calculate");
		calcBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				note.setText("NOTE: It's been submitted."); //Tell the user input was put in.
				calcBtnActionPerformed(e);//constructActionPerformed(evt);
			}//Close submitbutton actionPerformed
			
			private void calcBtnActionPerformed(ActionEvent evt) {
	        	  if (evt.getSource() == calcBtn) {	                	
	                 	String expr = stringField.getText();
	              try {   
	                 	outputbox.setText("= " + modelCon.stringToArithmetic(expr));
	                 	//outputbox.setText("= " +modelCon.stringToArithmetic(expr));
	           }catch (Exception f) {
	            		JOptionPane.showMessageDialog(null, "Wrong Expression. Please try again!", "Error Message", JOptionPane.ERROR_MESSAGE);
	                	System.out.println("Wrong Expression. Please try again!");
	                	throw new IllegalStateException("Error");    
	            	}//Close else 
				}//Close if
	        	}//Close calcBtn ActionPerformed
		});//CLose calcBtnActionPerformed addActionListener
		calcBtn.setBounds(219, 553, 160, 70);
		frame.getContentPane().add(calcBtn);
		
/*clearBtn*/
		clearBtn = new JButton("CLR");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringField.setText("");
				note.setText("");
			}//Close actionPerformed 
		});//Close addActionListener
		clearBtn.setBounds(129, 553, 70, 70);
		frame.getContentPane().add(clearBtn);
		
/*textButton - Text to Speech Button*/
		textButton = new JRadioButton("Text to Speech");
		textButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
	            voice = VoiceManager.getInstance().getVoice("kevin16");
	            if(outputbox.getText().isEmpty() && outputbox.getText().isBlank()) {
					textButton.setEnabled(false); //To disable JTextField use void setEnabled(boolean enabled)  method with false argument.
					JOptionPane.showMessageDialog(null, "You have not entered the Calculate Button with an expression.\nPlease reload the application and try again.", "Error Message", JOptionPane.ERROR_MESSAGE);
					System.out.println("Error Text Button.");
	            }else {
	            if (voice != null) {
	                voice.allocate();
	             if (e.getSource() == textButton) {                      
                            
	                try {
	                    voice.setRate(150);
	                    voice.setPitch(120);
	                    voice.setVolume(6);
	                    voice.speak(stringField.getText() + " " + " " + outputbox.getText()); // EMPTY QUOTE is to have the kevin to slowly speach, plause each worlds allowing user to understand 
	                }catch(Exception f) {
	                	f.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Wrong Expression. Please try again!", "Error Message", JOptionPane.ERROR_MESSAGE);
						System.out.println("Error in Text to Speech.");
						throw new IllegalStateException("Cannot find voice: kevin16");
						 
	                }//Close catch
	             }//Close if
	            }//Close else
				}//Close else
			}//Close actionPerformed
		});//Close addActionListener
		textButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textButton.setBounds(299, 190, 119, 23);
		frame.getContentPane().add(textButton);
		
/*speechButton - Speech to Text Button*/
		speechButton = new JRadioButton("Speech to Text");
		speechButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				note.setText("NOTE: Speech worked!");
				
				logger.log(Level.INFO, "Loading..\n"); // Loading Message
				
				 Configuration configuration = new Configuration(); // Configuration
				 
//Load model from the jar
				  configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
				  configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
				  
//Grammar
					configuration.setGrammarPath("resource:/grammar");
					configuration.setGrammarName("grammar");
					configuration.setUseGrammar(true);
//Start talking				
					logger.log(Level.INFO, "You can start to speak...\n");
				
	            try {
	                recognizer = new LiveSpeechRecognizer(configuration);
	                recognizer.startRecognition(true);
	                SpeechResult result = recognizer.getResult();
	                
	           if (result != null) {
	                resultText = result.getHypothesis();
	                
//Letting user know if microphone is on or not
	                if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
						System.out.println("Microphone is available.");
						logger.log(Level.INFO, "Microphone is available.\n");
					} else {
						logger.log(Level.INFO, "Microphone is not available.\n");
					}//Close else

//Output the result in console and the application	                
	                stringField.setText(wordsToNumbers(resultText)); //words user said
	                outputbox.setText("= " + modelCon.stringToArithmetic(wordsToNumbers(resultText))); //print the result
	                System.out.println("You said: "+wordsToNumbers(resultText));
	                recognizer.stopRecognition();  
//Handle errors 
	            }else
					logger.log(Level.INFO, "I can't understand what you said.\n");
			}catch (IOException e1) {
	                e1.printStackTrace(); 
	                JOptionPane.showMessageDialog(null, "ERROR", "Error Message", JOptionPane.ERROR_MESSAGE);
					System.out.println("Error in Speech to Text.");
					logger.log(Level.WARNING, null, e1);
					logger.log(Level.WARNING, null, e1);
					resourcesThread.interrupt();
	            }//CLose  catch 
	            logger.log(Level.INFO, "SpeechThread has exited...");
			}//Close actionPerformed
		});//Close addActionListener
//Create font, size, and frame for Speech to Text		
		speechButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		speechButton.setBounds(299, 219, 128, 23);
		frame.getContentPane().add(speechButton);
		
/*separator*/
		separator = new JSeparator();
		separator.setBounds(310, 177, 103, 2);
		frame.getContentPane().add(separator);
		
/*Create Menu Bar*/		
		mb = new JMenuBar();
		mb.setBounds(0, 0, 449, 22);
		frame.getContentPane().add(mb);
		x = new JMenu("Menu");
		
/*Create Menu Items*/
		//Help
        m1 = new JMenuItem("Help"); 
        m1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        m1.setBackground(SystemColor.inactiveCaption);
        m1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 String what = e.getActionCommand();
        		if(what.equals("Help")) {
        			
        			JOptionPane.showMessageDialog(frame,
        						  "How do I use this?\r\n" + "Press any number you wish to input and use operation and then clear to restart calculate\r\n\n"
    							+ "How do I exit out?\r\n" + "There are 3 ways user can exit out from the screen\r\n"
    							+ "1: Pess Ctrl and hold down and press Q to quit\r\n"
    							+ "2: Go to Menu bar and press Quit\r\n" 
    							+ "3: Press X on top right corner"
    							+ "\n\nHow to use Text to Speech Button? Seems lke it's not doing anything.\n"
    							+ "If you are pressing ''Text to Speech'' buttons first time when you opened the app, \nYou have not entered the Calculate Button with an expression. So, the Button will not work due to not \nhaving any numbers and operations in, please reload the application and try again with putting in numbers and operations.");
        			System.out.println("Help Center now exited.");
        		}else{
        			System.out.println("ERROR");
        		}//Close else 
        }//Close Help addActionListener
        });//Close Help addActionListener
        
       //Quit
        m2 = new JMenuItem("Quit");
        m2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        m2.setBackground(SystemColor.inactiveCaption);
        m2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame = new JFrame("Quit");
        		if(JOptionPane.showConfirmDialog(frame, "Confirm if you want to quit") == JOptionPane.YES_NO_OPTION) {
        			 System.out.println("Good Bye.");
        			System.exit(0);
        		}//Close if
        	}//CLose Quit actionPerformed
        });//Close Quit addActionListener       	   
        
/*Add menu items to menu*/
        x.add(m1);
        x.add(m2);

/*Add menu to menu bar*/
        mb.add(x);
        
/*Label for the NOTE, letting user know the input has successfully went through*/        
        note = new JLabel("");
        note.setBounds(27, 634, 409, 25);
        frame.getContentPane().add(note);
        
/*Outputbox*/        
        outputbox = new JTextField();
        outputbox.setFont(new Font("Ink Free", Font.BOLD, 24));
        outputbox.setEditable(false);
        outputbox.setBounds(219, 86, 184, 50);
        frame.getContentPane().add(outputbox);
    
	}//Close MyCalcView Initialize

/*Convert String to int*/  
   /**
	 * Logic for taking a textual number string and converting it into a number
	 * e.g. twenty five -> 25
	 * 
	 * This relies on there only being one textual number being processed. Steps
	 * prior to this deal with breaking a paragraph down into individual textual
	 * numbers, which could consist of a number of words.
	 * 
	 * @param words
	 * @return
	 */
   public String wordsToNumbers(String words) {
       newString = "";
       String[] splitWords = words.split(" ");
       for(String word : splitWords) {
           switch(word) {
               case "zero":
               newString += word.replace("zero", "0");
               break;
               case "hero":
               newString += word.replace("zero", "0");
               break;
               case "one":
               newString += word.replace("one", "1");
               break;
               case "two":
               newString += word.replace("two", "2");
               break;
               case "too":
               newString += word.replace("too", "2");
               break;
               case "three":
               newString += word.replace("three", "3");
               break;
               case "tree":
               newString += word.replace("tree", "3");
               break;
               case "free":
               newString += word.replace("free", "3");
               break;
               case "four":
               newString += word.replace("four", "4");
               break;
               case "for":
               newString += word.replace("for", "4");
               break;
               case "five":
               newString += word.replace("five", "5");
               break;
               case "six":
               newString += word.replace("six", "6");
               break;
               case "sex":
               newString += word.replace("sex", "6");
               break;
               case "seven":
               newString += word.replace("seven", "7");
               break;
               case "eight":
               newString += word.replace("eight", "8");
               break;
               case "ate":
               newString += word.replace("ate", "8");
               break;
               case "nine":
               newString += word.replace("nine", "9");
               break;   
               case "ten":
               newString += word.replace("ten", "10");
               break;
              case "plus":
               newString += word.replace("plus", "+");
               break;
               case "minus":
               newString += word.replace("minus", "-");
               break;
               case "times":
               newString += word.replace("times", "*");
               break;
               case "multiply":
               newString += word.replace("multiply", "*");
               break;  
               case "power":
               newString += word.replace("power", "^");
               break;
               case "powder":
               newString += word.replace("powder", "^");
               break;
               case "carrot":
               newString += word.replace("carrot", "^");
               break;
               case "left":
               newString += word.replace("left", "(");
               break;
               case "bracket":
               newString += word.replace("bracket", "(");
               break;
               case "right":
               newString += word.replace("right", ")");
               break;
               case "write":
               newString += word.replace("write", ")");
               break;
           }//Close switch   
       }//Close for loop
       return newString;
   }//Close wordsToNumbers

}//Close MyCalcView class
