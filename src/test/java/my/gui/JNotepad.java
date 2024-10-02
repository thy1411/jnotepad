/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.gui;

/**
 *
 * @author tyty
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class JNotepad extends JFrame {

    private JMenuBar menuBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp, mZoom;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPrint, itemUndo, itemCut, itemDelete, itemSearchwithBing, itemFind, itemFindNext, itemReplace, itemGoTo, itemSelectAll, itemTimeDate, itemWordWrap;
    private JMenuItem itemPageSetup, itemExit, itemFont, itemStatusBar, itemZoomIn, itemZoomOut, itemViewHelp;
    private JMenuItem itemCopy, itemPaste;
    private JCheckBoxMenuItem itemWrap;
    private JTextArea txtEditor;
    private JToolBar toolBar;
    private JButton btNew, btOpen, btSave;
    private File currentFile;
    private Object textArea;

    public JNotepad(String title) {
        super(title);
        createMenu();
        createGui();
        createToolBar();
        setTitle("Untitled - JNotepad");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createMenu() {
        menuBar = new JMenuBar();

        menuBar.add(mFile = new JMenu("File"));
        menuBar.add(mEdit = new JMenu("Edit"));
        menuBar.add(mFormat = new JMenu("Format"));
        menuBar.add(mView = new JMenu("View"));
        menuBar.add(mHelp = new JMenu("Help"));

        mFile.add(itemNew = new JMenuItem("New"));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save As..."));
        mFile.addSeparator();
        mFile.add(itemPageSetup = new JMenuItem("Page Setup..."));
        mFile.add(itemPrint = new JMenuItem("Print..."));
        mFile.addSeparator();
        mFile.add(itemExit = new JMenuItem("Exit"));

        mEdit.add(itemUndo = new JMenuItem("Undo"));
        mEdit.addSeparator();
        mEdit.add(itemCut = new JMenuItem("Cut"));
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));
        mEdit.add(itemDelete = new JMenuItem("Delete"));
        mEdit.addSeparator();
        mEdit.add(itemSearchwithBing = new JMenuItem("Search with Bing..."));
        mEdit.add(itemFind = new JMenuItem("Find..."));
        mEdit.add(itemFindNext = new JMenuItem("Find Next"));
        mEdit.add(itemReplace = new JMenuItem("Replace..."));
        mEdit.add(itemGoTo = new JMenuItem("Go To..."));
        mEdit.addSeparator();
        mEdit.add(itemSelectAll = new JMenuItem("Select All"));
        mEdit.add(itemTimeDate = new JMenuItem("Time/Date"));

        itemWordWrap = new JCheckBoxMenuItem("Word Wrap", true);
        mFormat.add(itemWordWrap);
        itemWordWrap.addActionListener(e -> toggleWordWrap());
        mFormat.add(itemFont = new JMenuItem("Font..."));

        mView.add(mZoom = new JMenu("Zoom"));
        mView.add(itemStatusBar = new JCheckBoxMenuItem("Status Bar"));

        mZoom.add(itemZoomIn = new JMenuItem("Zoom In"));
        mZoom.add(itemZoomOut = new JMenuItem("Zoom Out"));

        mHelp.add(itemViewHelp = new JMenuItem("View Help"));

        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));

        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, KeyEvent.BUTTON1_MASK));
        itemSearchwithBing.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.BUTTON1_MASK));
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itemTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.BUTTON1_MASK));

        itemNew.addActionListener(e -> newFile());
        itemOpen.addActionListener(e -> openFile());
        itemSave.addActionListener(e -> saveFile());
        itemSaveAs.addActionListener(e -> saveFileAs());
        itemExit.addActionListener(e -> exitApplication());
        itemCopy.addActionListener(e -> copyText());
        itemPaste.addActionListener(e -> pasteText());
        
        setJMenuBar(menuBar);
    }

    private void toggleWordWrap() {
        txtEditor.setLineWrap(itemWrap.isSelected());
        txtEditor.setWrapStyleWord(itemWrap.isSelected()); // Ngắt dòng theo từ
    }

    private void createGui() {
        txtEditor = new JTextArea();
        txtEditor.setFont(new Font("Arial", Font.PLAIN, 16));
        txtEditor.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtEditor);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void createToolBar() {
        toolBar = new JToolBar();
        toolBar.add(btNew = new JButton("New"));
        toolBar.add(btOpen = new JButton("Open"));
        toolBar.add(btSave = new JButton("Save"));

        btNew.setIcon(new ImageIcon(this.getClass().getResource("/img/new-document.png")));
        btOpen.setIcon(new ImageIcon(this.getClass().getResource("/img/open.png")));
        btSave.setIcon(new ImageIcon(this.getClass().getResource("/img/bookmark.png")));

        btNew.addActionListener(e -> newFile());
        btOpen.addActionListener(e -> openFile());
        btSave.addActionListener(e -> saveFile());

        add(toolBar, BorderLayout.NORTH);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile())) {
                byte[] data = fis.readAllBytes();
                txtEditor.setText(new String(data));
                currentFile = fileChooser.getSelectedFile();
                setTitle(currentFile.getName() + " - JNotepad");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
            }
        }
    }

    private void saveFile() {
        if (currentFile != null) {
            try (FileOutputStream fos = new FileOutputStream(currentFile)) {
                fos.write(txtEditor.getText().getBytes());
                setTitle(currentFile.getName() + " - JNotepad");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + ex.getMessage());
            }
        } else {
            saveFileAs();
        }
    }

    private void saveFileAs() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            saveFile();
        }
    }

    private void exitApplication() {
        System.exit(0);
    }

    private void newFile() {
        txtEditor.setText("");
        currentFile = null;
        setTitle("Untitled - JNotepad");
    }

    private void copyText() {
        StringSelection stringSelection = new StringSelection(txtEditor.getSelectedText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private void pasteText() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                String text = (String) clipboard.getData(DataFlavor.stringFlavor);
                txtEditor.replaceSelection(text);
            }
        } catch (UnsupportedFlavorException | IOException ex) {
            JOptionPane.showMessageDialog(this, "Error pasting text: " + ex.getMessage());
        }
    }

    public JTextArea getEditorArea() {
        return txtEditor;
    }

}
