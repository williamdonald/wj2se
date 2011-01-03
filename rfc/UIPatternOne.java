package rfc;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UIPatternOne extends JFrame implements Observer{
	//interfaces=========================================================================================//
	private String getEnd(){
		//RFC
//		return _endField.getText();
		return _subject.getEnd();
	}
	
	private void setEnd(final String end){
		//RFC
//		_endField.setText(end);
		_subject.setEnd(end);
	}
	
	private void setLength(final String length) {
//		_lengthField.setText(length);
		_subject.setLength(length);
	}

	private String getLength() {
//		return _lengthField.getText();
		return _subject.getLength();
	}

	private String getStart() {
//		return _startField.getText();
		return _subject.getStart();
	}
	
	private void setStart(final String start){
//		_startField.setText(start);
		_subject.setStart(start);
	}
	
	//UI-Control Parts=========================================================================================//
	JTextField _startField = new JTextField();
	JTextField _endField = new JTextField();
	JTextField _lengthField = new JTextField();
	
	SysFocus listener = new SysFocus();
	
	class SysFocus extends java.awt.event.FocusAdapter{
		public void focusLost(FocusEvent event){
			Object object = event.getSource();
			if (object == _startField)
				startField_FocusLost(event);
			else if (object == _endField)
				endField_FocusLost(event);
			else if (object == _lengthField)
				lengthField_FocusLost(event);
			
		}
	}
	
	private void addListeners(){
		_startField.addFocusListener(listener);
		_endField.addFocusListener(listener);
		_lengthField.addFocusListener(listener);
	}
	
	private void createContent(final Container panel){
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(3,2));
		jp.add(new JLabel("Start"));
		jp.add(_startField);
		jp.add(new JLabel("End"));
		jp.add(_endField);
		jp.add(new JLabel("Length"));
		jp.add(_lengthField);
		
		JPanel booster = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(jp, BorderLayout.NORTH);
		panel.add(booster, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(300,200));
	}
	private static void createAndShowGUI() {
        //Create and set up the window.
    	UIPatternOne frame = new UIPatternOne();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.createContent(frame.getContentPane());
        
        frame.addListeners();
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	//From UI TO Domain
	public void endField_FocusLost(FocusEvent event) {
		//RFC
		setEnd(_endField.getText());
		if (isNotInteger(getEnd()))
//            _endField.setText("0");
			setEnd("0");
		_subject.calculateLength();
		
		
	}
	
	public void startField_FocusLost(FocusEvent event) {
		setStart(_startField.getText());
		if (isNotInteger(getStart()))
//            _startField.setText("0");
			setStart("0");
		_subject.calculateLength();
		
		
		
	}
	public void lengthField_FocusLost(FocusEvent event) {
		setLength(_lengthField.getText());
		if (isNotInteger(getLength()))
			setLength("0");
		_subject.calculateEnd();
		
	}
	
	
	//From Domain to UI
	@Override
	public void update(Observable o, Object arg) {
		_endField.setText(_subject.getEnd());
		_startField.setText(_subject.getStart());
		_lengthField.setText(_subject.getLength());
	}
	
	//domain class =========================================================================================//
	
	/**
	 * separate from GUI code.
	 */
	class Interval extends Observable{
		private String _end = "0";
		private String _start = "0";
		private String _length = "0";
		public String getStart() {
			return _start;
		}

		public void setStart(String start) {
			_start = start;
			setChanged();
			notifyObservers();
		}

		public String getLength() {
			return _length;
		}

		public void setLength(String length) {
			_length = length;
			setChanged();
			notifyObservers();
		}

		public String getEnd() {
			return _end;
		}

		public void setEnd(String end) {
			//RFC
			_end = end;
			setChanged();
			notifyObservers();
		}

		void calculateEnd() {
		    try {
		    	//RFC
		        int start = Integer.parseInt(getStart());
		        //RFC
		        int length = Integer.parseInt(getLength());
		        int end = start + length;
		        //RFC
		//	        _endField.setText(String.valueOf(end));
		        setEnd(String.valueOf(end));
		      } catch (NumberFormatException e) {
		        throw new RuntimeException ("Unexpected Number Format Error");
		      }
		}

		void calculateLength() {
		      try {
		          int start = Integer.parseInt(getStart());
		          int end = Integer.parseInt(getEnd());
		          int length = end - start;
		//	          _lengthField.setText(String.valueOf(length));
		          setLength(String.valueOf(length));
		        } catch (NumberFormatException e) {
		          throw new RuntimeException ("Unexpected Number Format Error");
		        }
		
			
		}
	}
	//link to domain from UI
	private Interval _subject;
	
	UIPatternOne(){
		_subject = new Interval();
		_subject.addObserver(this);
		update(_subject,null);
	}

	

	private boolean isNotInteger(String text) {
		try {
			Integer.valueOf(text);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

	

	
	
	
	//launch helper===============================================================================================
	public static void main(String[] args) {
	       /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

	}


}
