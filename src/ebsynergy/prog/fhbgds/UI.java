package ebsynergy.prog.fhbgds;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

public class UI extends JFrame {

	private static final long serialVersionUID = 1L;

	public UI() {
        initComponents();
    }

    private void initComponents() {

        printButton = new JButton();
        saveButton = new JButton();
        loadButton = new JButton();
        scrollPane = new JScrollPane();
        outputArea = new JTextArea();
        exitButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        
        printButton.setText("Print All");
        
        printButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event){
        		Actions.printGrades();
        	}
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event){
        		Actions.save();
        	}
        });

        loadButton.setText("Load");
        loadButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event){
        		Actions.load();
        	}
        });

        outputArea.setColumns(20);
        outputArea.setRows(5);
        scrollPane.setViewportView(outputArea);

        exitButton.setText("Exit");
        exitButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent event){
        		Actions.exit();
        	}
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                            .addComponent(printButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadButton, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(printButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addGap(18, 18, 18)
                        .addComponent(loadButton))
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton))
                .addContainerGap())
        );

        pack();
    }
    
    protected static void disableAllButtons(){
    	printButton.setEnabled(false);
    	exitButton.setEnabled(false);
    	loadButton.setEnabled(false);
    	saveButton.setEnabled(false);
    }

    private static JButton printButton;
    private static JButton exitButton;
    private static JButton saveButton;
    private static JButton loadButton;
    private JScrollPane scrollPane;
    public static JTextArea outputArea;
}
