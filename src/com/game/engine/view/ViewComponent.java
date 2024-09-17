package com.game.engine.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


import com.game.engine.model.WindowSpec;

public class ViewComponent extends AView{
	public ViewComponent(WindowSpec spec) {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Color cl = Color.black;
		g2d.setColor(cl);
		g2d.fillRect(0, 0, 640, 480);
		super.paintComponent(g2d);
	}
}
