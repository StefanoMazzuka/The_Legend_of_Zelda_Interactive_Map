package View;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Box;
import Controller.FileManagement;

import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int rows = 8;
	private int columns = 16;
	private Box[][] map;

	/**
	 * Create the frame.
	 */
	public View() {
		setTitle("The Legend of Zelda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1675, 916);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(View.class.getResource("/Img/icon.png")));
		map = new Box[rows][columns];

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getVerticalScrollBar().setValue(1);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Box box = new Box(i, j);
				GridBagConstraints gbc_box = new GridBagConstraints();
				gbc_box.gridx = j;
				gbc_box.gridy = i;

				box.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (box.getState() == 0) {
							box.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Img/" + box.getImg())));
							box.setState(1);
						} else {
							box.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Img/zelda.png")));
							box.setState(0);
						}
					}
				});

				map[i][j] = box;
				panel.add(box, gbc_box);
			}
		}

		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				save();
			}
		});

		mntmLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				load();
			}
		});
	}

	private String getData() {
		String data = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				data += map[i][j].getState() + ",";
			}
		}
		return data;
	}
	private void save() {
		JFileChooser fc = new JFileChooser();
		int result = fc.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			FileManagement fm = new FileManagement();	
			try {
				fm.save(getData(), new File(file.getPath() + ".txt"));
				JOptionPane.showMessageDialog(null, "Fichero generado con éxito.", 
						"Información", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void load() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			FileManagement fm = new FileManagement();
			String file = fm.fileToString(fileChooser.getSelectedFile());

			String[] data = file.split(",");
			int pos = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					map[i][j].setState(Integer.valueOf(data[pos]));
					if (map[i][j].getState() == 0) map[i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource("Img/zelda.png")));
					else map[i][j].setIcon(new ImageIcon(getClass().getClassLoader().getResource("Img/" + map[i][j].getImg())));
					pos++;
				}
			}
		}
	}
}