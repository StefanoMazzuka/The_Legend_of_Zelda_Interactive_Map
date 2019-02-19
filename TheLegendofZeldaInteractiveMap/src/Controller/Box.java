package Controller;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Box extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String img;
	private int state;
	
	public Box(int i, int j) {
		this.state = 0;
		this.img = String.valueOf(i);
		this.img = String.valueOf(this.img + j + ".png");
		setSize(258, 170);
		this.setBorder(null);
		this.setMargin(new Insets(0, 0, 0, 0));
		if (this.img.equals("77.png")) {
			this.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Img/" + this.img)));
			this.state = 1;
		}
		else {
			this.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Img/zelda.png")));
			this.state = 0;
		}
	}
	
	public String getImg() {
		return this.img;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
}