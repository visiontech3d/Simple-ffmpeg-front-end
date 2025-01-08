/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my.FFMpegui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author me
 */
public class FFMpegui extends javax.swing.JFrame {

    /* ffmpeg location */
    public static String ffmpeg_loc = " ";
    /* ffmpeg command pre arguments */
    public static String ffmpeg_pre = "";
    /* ffmpeg command post arguments */
    public static String ffmpeg_post = "";
    /* How many times  button pressed*/
    public int PreButtonPressed = 0;
    public int PostButtonPressed = 0;
    public int TargetButtonPressed = 0;
    
    /* ffmpeg target folder */
    public static String ffmpeg_target = "";
    /* ffmpeg source folder */
    public static String ffmpeg_source = "";
    /* codec used the last time */
    public static String default_codec = "";
    
    /**
     * Creates new form FFMpegui
     */
    public FFMpegui() {
        initComponents();
        
        // Scan INI file for setup information
        try {
            Scanner infile = new Scanner(new File("SimpleFFMPEGConvert.ini"));
            infile.useDelimiter("\\|\\||\n");   /* This means || or new line as delimeter. Geniuses at Java couldn't even create easy parsing expressions */
            
            /* Read saved CJXL binary location */
            if (infile.next().equals("ffmpeg_loc")){
                ffmpeg_loc = infile.next().replace("\n", "").replace("\r", "");   /* Remove carriage returns from scan */
                System.out.println("ffmpeg_loc is " + ffmpeg_loc );
            }
            /* Read saved ffmpeg command pre run argument */
            if (infile.next().equals("ffmpeg_pre")){
                ffmpeg_pre = infile.next().replace("\n", "").replace("\r", "");   /* Remove carriage returns from scan */
                System.out.println("ffmpeg_pre is " + ffmpeg_pre );
            }
            /* Read saved ffmpeg command post run argument */
            if (infile.next().equals("ffmpeg_post")){
                ffmpeg_post = infile.next().replace("\n", "").replace("\r", "");  /* Remove carriage returns from scan */
                System.out.println("ffmpeg_post is " + ffmpeg_post );
            }
            /* Read saved codec used previously */
            if (infile.next().equals("default_codec")){
                default_codec = infile.next().replace("\n", "").replace("\r", "");  /* Remove carriage returns from scan */
                System.out.println("Default codec is " + default_codec );
            }
            /* Read saved file postfix used previously */
            if (infile.next().equals("postfix")){
                file_postfix.setText(infile.next().replace("\n", "").replace("\r", ""));   /* Remove carriage returns from scan */
                System.out.println("File postfix is " + file_postfix.getText() );
            }
            
            infile.close();
        } catch (IOException e) {
            System.out.println("An error occurred or SimpleCJXLConvert.ini does not exist");
            e.printStackTrace();
        }
        
        //Switch off panel until called on by button press
        jPanel3.setVisible(false);
        
        //Set table column width
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(500);
        
        //Clear combobox
        combobox_codec.removeAllItems();
        
        //Disable play button until a file is selected
        button_play.setVisible(false);
        combobox_codec.setEditable(true);
        
        //Populate codec list
        if (!ffmpeg_loc.isBlank()||default_codec.isBlank()){
            populate_codec_list();
            //combobox_codec.removeItemAt(0);
        }
        combobox_codec.setSelectedItem(default_codec);
    }

    private void populate_codec_list(){
        int startreading_flag = 0;
        String ffplay_command = "\"" + ffmpeg_loc + "\\ffmpeg.exe\"";
                
        ProcessBuilder build = new ProcessBuilder(ffplay_command, "-encoders");
        build.redirectErrorStream(true);
        try {
            //Array to hold list of codecs    
            ArrayList<String> codec_array = new ArrayList<String>();
            
            Process cmd = build.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
                
            //Get command output
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                //System.out.println(line);
                    
                //Read in decoders
                if (startreading_flag == 1){
                    //combobox_codec.addItem(line.substring(8, 20).trim());
                    codec_array.add(line.substring(8, 20).trim());
                    continue;
                }
                        
                if (line.equals(" ------")){
                    //Signale to start reading into combobox
                    startreading_flag = 1;
                    System.out.println("Read in codec begins");
                }
                    
            }
            Collections.sort(codec_array);          //Sort items in array
            for (String s : codec_array) {
                combobox_codec.addItem(s);
            }
            
        } catch (IOException e){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "ffmpeg.exe failed. Please make sure location \n of ffmpeg.exe is set and runs in cmd", "Alert", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser_ffmpeg = new javax.swing.JFileChooser();
        button_ffmpegloc = new javax.swing.JButton();
        button_ffmpegpre = new javax.swing.JButton();
        button_ffmpegpost = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        button_source = new javax.swing.JButton();
        button_target = new javax.swing.JButton();
        button_convert = new javax.swing.JButton();
        check_delete = new javax.swing.JCheckBox();
        check_restoredates = new javax.swing.JCheckBox();
        button_selectall = new javax.swing.JButton();
        button_unselect = new javax.swing.JButton();
        combobox_codec = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        button_play = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        file_postfix = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        button_ffmpegloc.setText("Set ffmpeg location");
        button_ffmpegloc.setToolTipText("Folder where ffmpeg.exe is located");
        button_ffmpegloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ffmpeglocActionPerformed(evt);
            }
        });

        button_ffmpegpre.setText("Set input arguments");
        button_ffmpegpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ffmpegpreActionPerformed(evt);
            }
        });

        button_ffmpegpost.setText("Set output arguments");
        button_ffmpegpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ffmpegpostActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Steps 1 - 2 only for one time setup", "", ""},
                {"1. Press Set ffmpeg Location and locate ffmpeg.exe", "", ""},
                {"2. Press Set Command arguments if any", "", ""},
                {"Regular Use", "", ""},
                {"1. Press Select Target Folder to place new files", "", ""},
                {"2. Press Select Source Folder to locate images to convert", "", ""},
                {"3. Select movies from table", "", ""},
                {"4. Select desired encoder from dropdown", "", ""},
                {"5. Press Convert to convert selected movies", "", ""}
            },
            new String [] {
                "File","Ext", "OK?"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        button_source.setText("Select source folder");
        button_source.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_sourceActionPerformed(evt);
            }
        });

        button_target.setText("Select target folder");
        button_target.setToolTipText("Select the folder where you want the target files to be stored.\nIf same as source folder then leave empty.");
        button_target.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_targetActionPerformed(evt);
            }
        });

        button_convert.setText("Convert!");
        button_convert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_convertActionPerformed(evt);
            }
        });

        check_delete.setText("Delete original files");
        check_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_deleteActionPerformed(evt);
            }
        });

        check_restoredates.setText("Restore file dates");
        check_restoredates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check_restoredatesActionPerformed(evt);
            }
        });

        button_selectall.setText("Select All");
        button_selectall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_selectallActionPerformed(evt);
            }
        });

        button_unselect.setText("DeSelect All");
        button_unselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_unselectActionPerformed(evt);
            }
        });

        combobox_codec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "none" }));
        combobox_codec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combobox_codecActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Codec");

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("jLabel4");

        jTextField3.setText("jTextField3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        button_play.setText("Play");
        button_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_playActionPerformed(evt);
            }
        });

        jLabel3.setText("Output file postfix");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_ffmpegloc)
                            .addComponent(button_ffmpegpost)
                            .addComponent(button_ffmpegpre))
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button_source)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_target)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combobox_codec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(file_postfix, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_convert)
                            .addComponent(check_delete)
                            .addComponent(check_restoredates))
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button_selectall)
                            .addComponent(button_unselect)
                            .addComponent(button_play))
                        .addGap(91, 91, 91))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_convert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(check_delete)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(check_restoredates))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_ffmpegloc)
                            .addComponent(button_source)
                            .addComponent(button_target)
                            .addComponent(combobox_codec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(button_ffmpegpre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(button_ffmpegpost))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(file_postfix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(button_selectall)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_unselect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_play))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_ffmpeglocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ffmpeglocActionPerformed
        FileChooser_ffmpeg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   // Set to select folder only
        int returnVal = FileChooser_ffmpeg.showOpenDialog(this); 
        if (returnVal == FileChooser_ffmpeg.APPROVE_OPTION) {
            File file = FileChooser_ffmpeg.getSelectedFile();
            // Assign CJXL executable location
            ffmpeg_loc = file.getAbsolutePath();
            System.out.println("Exe is at: " + ffmpeg_loc);
            
            populate_codec_list();      
            combobox_codec.setSelectedItem(default_codec);
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_button_ffmpeglocActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        File DelFile = new File("SimpleFFMPEGConvert.ini");
        DelFile.delete();
        
        try {
            FileWriter myWriter = new FileWriter("SimpleFFMPEGConvert.ini");
            myWriter.write("ffmpeg_loc||" + ffmpeg_loc);
            myWriter.write(System.getProperty( "line.separator" ) + "ffmpeg_pre||" + ffmpeg_pre);
            myWriter.write(System.getProperty( "line.separator" ) + "ffmpeg_post||" + ffmpeg_post);
            myWriter.write(System.getProperty( "line.separator" ) + "default_codec||" + combobox_codec.getSelectedItem().toString());
            myWriter.write(System.getProperty( "line.separator" ) + "postfix||" + file_postfix.getText());
            myWriter.write(System.getProperty( "line.separator" ));
            myWriter.close();
            System.out.println("Successfully wrote to INI file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    private void button_ffmpegpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ffmpegpreActionPerformed
        
        //Press the other button if pressed before
        if (PostButtonPressed>0){button_ffmpegpost.doClick();}   
        
        //Set dialog, text and others
        if (PreButtonPressed == 0){
            button_ffmpegpre.setText("Save input arguments");
            jLabel4.setText("Input argument");
            jTextField3.setText(ffmpeg_pre);
            jPanel3.setVisible(true);
            PreButtonPressed = PreButtonPressed + 1;
        } else {
            button_ffmpegpre.setText("Set input arguments");
            ffmpeg_pre = jTextField3.getText();
            jPanel3.setVisible(false);
            PreButtonPressed = 0;
        }
    }//GEN-LAST:event_button_ffmpegpreActionPerformed

    private void button_ffmpegpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ffmpegpostActionPerformed
        
        //Press the other button if pressed before
        if (PreButtonPressed>0){button_ffmpegpre.doClick();}   
        
        //Set dialog, text and others
        if (PostButtonPressed == 0){
            button_ffmpegpost.setText("Save output arguments");
            jLabel4.setText("Output argument");
            jTextField3.setText(ffmpeg_post);
            jPanel3.setVisible(true);
            PostButtonPressed = PostButtonPressed + 1;
        } else {
            button_ffmpegpost.setText("Set output arguments");
            ffmpeg_post = jTextField3.getText();
            jPanel3.setVisible(false);
            PostButtonPressed = 0;
        }
    }//GEN-LAST:event_button_ffmpegpostActionPerformed

    private void button_sourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_sourceActionPerformed
        // TODO add your handling code here:
        // Get model from jTable1
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        //Reset Table
        model.setRowCount(0); 
        
        FileChooser_ffmpeg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   // Set to select folder only
        int returnVal = FileChooser_ffmpeg.showOpenDialog(this); 
        if (returnVal == FileChooser_ffmpeg.APPROVE_OPTION) {
            File file = FileChooser_ffmpeg.getSelectedFile();
            ffmpeg_source = file.getAbsolutePath() + "\\";
            //jTable1.add(JCheckbox);
            System.out.println("Source is: " + ffmpeg_source);
        
            File folder = new File(ffmpeg_source);
            File[] listOfFiles = folder.listFiles();
            for (File file2 : listOfFiles) {
                if (file2.isFile()) {
                    //Get extension if any
                    int ext_index = file2.getName().lastIndexOf(".");
                    String ext = "";
                    if (ext_index > 0 && file2.getName().substring(ext_index, file2.getName().length()).length() < 5){
                        ext = file2.getName().substring(ext_index, file2.getName().length());
                    }
                    model.addRow(new Object[]{file2.getName(), ext, "", false});
                }
            }
            //Enable table sort 
            jTable1.setAutoCreateRowSorter(true);
            
        } else {
              System.out.println("File access cancelled by user.");
        }         
        
        //Add selection listener to jTable1
        jTable1.getSelectionModel().addListSelectionListener(new SharedListSelectionHandler());
    }//GEN-LAST:event_button_sourceActionPerformed

    //jTable1's listener, listens to selection and makes 'play' button visible
    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            
            //If there is selection and only 1 element is selected
            if (lsm.getMinSelectionIndex() != -1 && lsm.getMinSelectionIndex() == lsm.getMaxSelectionIndex()) {
                button_play.setVisible(true);
            } else {
                button_play.setVisible(false);
            }
        }
    }
    private void button_targetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_targetActionPerformed
        // TODO add your handling code here:
        TargetButtonPressed = TargetButtonPressed + 1;
        FileChooser_ffmpeg.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   // Set to select folder only
        int returnVal = FileChooser_ffmpeg.showOpenDialog(this); 
        if (returnVal == FileChooser_ffmpeg.APPROVE_OPTION) {
            File file = FileChooser_ffmpeg.getSelectedFile();
            // Assign CJXL executable location
            ffmpeg_target = file.getAbsolutePath() + "\\";
            System.out.println("Target is: " + ffmpeg_target);
            } else {
              System.out.println("File access cancelled by user.");
            }
    }//GEN-LAST:event_button_targetActionPerformed

    private void button_selectallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_selectallActionPerformed
        // TODO add your handling code here:
        jTable1.selectAll();
    }//GEN-LAST:event_button_selectallActionPerformed

    private void button_unselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_unselectActionPerformed
        // TODO add your handling code here:
        jTable1.clearSelection();
    }//GEN-LAST:event_button_unselectActionPerformed

    private void check_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_deleteActionPerformed
        // TODO add your handling code here:
        if(check_delete.isSelected()){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Original files will be deleted! No further warnings will be issued!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_check_deleteActionPerformed

    private void check_restoredatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check_restoredatesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check_restoredatesActionPerformed

    private void button_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_playActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        String ffplay_command = "\"" + ffmpeg_loc + "\\ffplay.exe\"";
        String ffplay_input = "\"" + ffmpeg_source + "\\" + model.getValueAt(jTable1.convertRowIndexToModel(jTable1.getSelectedRow()), NORMAL) + "\"";          //Convert from sorted table
        
        ProcessBuilder build = new ProcessBuilder(ffplay_command, ffplay_input);
        build.redirectErrorStream(true);
        try {
            System.out.println("Playing : " + ffplay_input);    
            
            Process cmd = build.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
                
            //Get command output
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                //System.out.println(line);
                }
            } catch (IOException e){
                System.out.println("Playback failed : \n" + e.toString());
                //e.printStackTrace();
            }
    }//GEN-LAST:event_button_playActionPerformed

    private void combobox_codecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combobox_codecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combobox_codecActionPerformed

    private void button_convertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_convertActionPerformed
        // Get table model from jTable1
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                
        //If no target folder specified use source folder as target folder
        if (TargetButtonPressed == 0) { ffmpeg_target = ffmpeg_source ; }
        
        //Start processing selected items
        //Process only if there is select items
        if (jTable1.getSelectedRowCount() > 0){
            //Loop all selected files in table
            process: for (int x : jTable1.getSelectedRows()){
                //Input file
                String infile = "" + model.getValueAt(jTable1.convertRowIndexToModel(x), NORMAL);    
                
                /* Get outfile name */
                //Get location of file extension 
                int extension = infile.lastIndexOf(".");
                System.out.println("Extension :" + extension);
                
                //Replace output file name with *.mp4
                String outfile ;
                if (extension > 0 && infile.substring(extension, infile.length()).length() < 5){       //In case source file has no extension, try to encompass cases like .jpeg , .jpg. Assume is not extension if length after final '.' is more than 5
                    outfile = infile.substring(0, extension) + file_postfix.getText() + ".mp4";
                    System.out.println("Outfile is :" + outfile);
                }    
                else {outfile = infile + file_postfix.getText() + ".mp4";}
                
                //Return code for delete operation
                int del_src_return = 1;
                //Return code for Convert command 
                int cmd_return = 1;
                
                //Check if target file already exist
                //Only if original file not intended to be deleted and target folder is the same as source folder or
                //target folder different from source folder
                if (!ffmpeg_target.equals(ffmpeg_source) || 
                    (!check_delete.isSelected() && ffmpeg_target.equals(ffmpeg_source) && infile.equals(outfile)) || 
                    (ffmpeg_target.equals(ffmpeg_source) && !infile.equals(outfile))){
                    //Get full path and file name
                    String full_path = ffmpeg_target + outfile ;
                    System.out.println("Full path: " + full_path);
                    
                    //Skip file if output already exists
                    Path temp_path = Paths.get(full_path);
                    if (Files.exists(temp_path)) {
                        System.out.println("Output file exists, skipping : " + full_path);
                        model.setValueAt("Skip", jTable1.convertRowIndexToModel(x), 2);
                        continue ;
                    }
                }
                
                //Run ffmpeg
                cmd_return = run_ffmpeg(infile, outfile);
                
                //Set date attributes
                int attr_exit = 1;  //Get powershell exit value
                attr_exit = run_attr_set(infile, outfile);
                
                //Delete original files
                //Only if ffmpeg.exe, date attribute set was successful and delete checkbox selected
                //Added check for ffmpeg file input because otherwise will delete original movie file
                if(cmd_return == 0 && attr_exit == 0 && check_delete.isSelected() &&
                  (!ffmpeg_target.equals(ffmpeg_source) || (ffmpeg_target.equals(ffmpeg_source) && !infile.equals(outfile)))      //if target file overwrites the source file, -y flag is set in ffmpeg to overwrite it in run_ffmpeg function
                   ){     
                    del_src_return = run_delete(infile);
                }else {
                    del_src_return = 0 ;
                }
                
                //Get return value and update table of success
                if (cmd_return == 0 && attr_exit == 0 && del_src_return == 0){
                    model.setValueAt("OK", jTable1.convertRowIndexToModel(x), 2);
                }else {
                    model.setValueAt("Fail", jTable1.convertRowIndexToModel(x), 2);
                }
                //Attempt to repaint table value
                model.fireTableCellUpdated(jTable1.convertRowIndexToModel(x), 2);
                                
            }
        }else {     //If no selected items stop and show dialog
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "No files selected from box", "Alert", JOptionPane.WARNING_MESSAGE);  
        }
    }//GEN-LAST:event_button_convertActionPerformed

    //Delete source file
    private int run_delete(String infile){
        Path source = Paths.get(ffmpeg_source + infile);
                                
        try {
            System.out.println("Deleting original file");
            Files.delete(source);
            return 0;
        } catch (IOException ex) {
            System.out.println("Cannot delete file : ");
            ex.printStackTrace();
            return 1;
        }
    }
    
    //Set date attributes to file
    private int run_attr_set(String infile, String outfile){
        if (check_restoredates.isSelected() == true){   //Run only if restoredate checkbox selected
            Path source = Paths.get(ffmpeg_source + infile);
            Path target = Paths.get(ffmpeg_target + outfile);
                        
            try {
                System.out.println("Restoring dates .. ");
                
                BasicFileAttributes src_attr = Files.readAttributes(source, BasicFileAttributes.class);
                Files.setAttribute(target, "creationTime", src_attr.creationTime());
                Files.setAttribute(target, "lastModifiedTime", src_attr.lastModifiedTime());
                return 0;
            } catch (IOException ex) {
                System.out.println("Cannot restore dates : ");
                ex.printStackTrace();
                return 1;
            }
        } else {
            return 0;
        }
    }
    
    //Run ffmpeg command
    private int run_ffmpeg(String infile, String outfile) {
        //Initialize command
        String command = "\"" + ffmpeg_loc + "\\ffmpeg.exe\"" ;
        
        /* Parse final command */
        /* Final command looks like "<FFMPEG Path>\ffmpeg.exe [input arguments] -i <Source path>\infile]..[output arguments] <Output path>\outfile */
        String command_input_arg;
        if (check_delete.isSelected() && ffmpeg_source.equals(ffmpeg_target) && infile.equals(outfile)){     //If delete original files selected then add -y to argument
            command_input_arg = "\"\"-y " + ffmpeg_pre.trim() + "\"\"";
        }else {
            command_input_arg = "\"\"" + ffmpeg_pre.trim() + "\"\"";
        }
        String command_output_arg = "\"\"" + ffmpeg_post.trim() + "\"\"";
        String command_input_fullname = "\"" + ffmpeg_source + infile + "\"";
        String command_output_fullname = "\"" + ffmpeg_target + outfile + "\"";
                
        //return code
        int cmd_return = 1;
        
        /* Run command */
        //Shamelessly copied from StackOverflow stackoverflow.com/users/48503/luke-woodward
        System.out.println("Final command: " + command.concat(" ") + command_input_arg.concat(" ") 
                + command_input_fullname.concat(" ")+ command_output_arg.concat(" ") + combobox_codec.getSelectedItem().toString().concat(" ")
                + command_output_fullname);
        
        ProcessBuilder build = new ProcessBuilder(command,
                                                  command_input_arg,
                                                  command_input_fullname,
                                                  command_output_arg,
                                                  combobox_codec.getSelectedItem().toString(),
                                                  command_output_fullname
                                                  );
        build.redirectErrorStream(true);    
        try {
            System.out.println("Running ffmpeg. All frame details will be suppressed");
            
            Process cmd = build.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(cmd.getInputStream()));
                
            //Get command output
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) { break; }
                if (line.length()> 5 && !line.substring(0, 5).equals("frame")){
                    //Print only important messages
                    System.out.println(line);
                }
                
            }
                
            try {
                cmd_return = cmd.waitFor();
                System.out.println("Cmd return: " + cmd_return);
            } catch (InterruptedException ex) {
                Logger.getLogger(FFMpegui.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return cmd_return;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FFMpegui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FFMpegui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FFMpegui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FFMpegui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FFMpegui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser_ffmpeg;
    private javax.swing.JButton button_convert;
    private javax.swing.JButton button_ffmpegloc;
    private javax.swing.JButton button_ffmpegpost;
    private javax.swing.JButton button_ffmpegpre;
    private javax.swing.JButton button_play;
    private javax.swing.JButton button_selectall;
    private javax.swing.JButton button_source;
    private javax.swing.JButton button_target;
    private javax.swing.JButton button_unselect;
    private javax.swing.JCheckBox check_delete;
    private javax.swing.JCheckBox check_restoredates;
    private javax.swing.JComboBox<String> combobox_codec;
    private javax.swing.JTextField file_postfix;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
