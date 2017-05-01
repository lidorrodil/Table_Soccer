package userInterface;

import javafx.scene.control.Label;

public class MyLabel extends Label {
	public MyLabel(String text) {
		super(text);
		this.setMaxWidth(Double.MAX_VALUE);
		this.setMaxHeight(Double.MAX_VALUE);
	}
}