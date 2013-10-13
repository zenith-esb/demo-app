package com.adhi.publisher.gui;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import com.adhi.publisher.MessagePublisher;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class MainWindow implements Observer{

	private JFrame frame;
	private JTextField textField;
	private String url;
	private String to = "All";
	private JProgressBar progressBar;
	private static MessagePublisher publisher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		publisher = new MessagePublisher();
		final MainWindow window = new MainWindow(publisher);
		publisher.addObserver(window);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow(Observable obs) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			initialize();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 322, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 89, 286, 140);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(10, 14, 266, 117);
		panel.add(editorPane);
		
		JLabel lblServer = new JLabel("Server :");
		lblServer.setBounds(20, 25, 46, 14);
		frame.getContentPane().add(lblServer);
		
		textField = new JTextField();
		textField.setBounds(66, 22, 219, 20);
		textField.setText(publisher.getPublishUrl());
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblTo = new JLabel("To        :");
		lblTo.setBounds(20, 50, 46, 14);
		frame.getContentPane().add(lblTo);
		
		String[] items = {"All", "Gampaha", "Fort"};		
		JComboBox comboBox = new JComboBox(items);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
		        to = (String)cb.getSelectedItem();
			}
		});
		comboBox.setBounds(66, 50, 219, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				progressBar.setValue(0);
				url = textField.getText() + "/" + to;  
			
				System.out.println(url +" - "+ editorPane.getText());
				publisher.setBody(editorPane.getText());
				publisher.setPublishUrl(url);
				Thread t = new Thread(publisher);
				t.start();
			}
		});
		btnSend.setBounds(207, 228, 89, 23);
		frame.getContentPane().add(btnSend);
		
		progressBar = new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setBounds(10, 231, 187, 20);
		frame.getContentPane().add(progressBar);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg0 != null){
			MessagePublisher pub = (MessagePublisher) arg0;
			//System.out.println(pub.getValue());
			progressBar.setValue(pub.getValue());
			
		}
				
	}
}
