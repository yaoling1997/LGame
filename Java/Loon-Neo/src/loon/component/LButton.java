/**
 * 
 * Copyright 2008 - 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @project loon
 * @author cping
 * @email：javachenpeng@yahoo.com
 * @version 0.1
 */
package loon.component;

import loon.LSystem;
import loon.LTexture;
import loon.LTextures;
import loon.canvas.LColor;
import loon.event.CallFunction;
import loon.font.FontSet;
import loon.font.IFont;
import loon.opengl.GLEx;
import loon.opengl.TextureUtils;

public class LButton extends LComponent implements FontSet<LButton> {

	private String text = null;

	private boolean over, pressed, exception, selected;

	private int pressedTime, offsetLeft, offsetTop, type;

	private IFont font;

	private LColor fontColor = LColor.white;

	private CallFunction _function;

	public LButton(String fileName) {
		this(fileName, null, 0, 0);
	}

	public LButton(String fileName, String text, int row, int col) {
		this(LTextures.loadTexture(fileName), text, row, col, 0, 0);
	}

	public LButton(String fileName, int row, int col) {
		this(fileName, null, row, col, 0, 0);
	}

	public LButton(String fileName, String text, int row, int col, int x, int y) {
		this(LTextures.loadTexture(fileName), text, row, col, x, y);
	}

	public LButton(LTexture img, String text, int row, int col, int x, int y) {
		this(TextureUtils.getSplitTextures(img, row, col), text, row, col, x, y);
	}

	public LButton(LTexture[] img, String text, int row, int col, int x, int y) {
		this(LSystem.getSystemGameFont(), img, text, row, col, x, y);
	}

	public LButton(IFont font, LTexture[] img, String text, int row, int col,
			int x, int y) {
		super(x, y, row, col);
		this.font = font;
		this.text = text;
		if (img != null) {
			this.setImages(img);
		}
	}

	public LButton(String text, int x, int y, int w, int h) {
		this(LSystem.getSystemGameFont(), text, x, y, w, h);
	}

	public LButton(IFont font, String text, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.font = font;
		this.text = text;
	}

	public void setImages(LTexture... images) {
		LTexture[] buttons = new LTexture[4];
		if (images != null) {
			int size = images.length;
			this.type = size;
			switch (size) {
			case 1:
				buttons[0] = images[0];
				buttons[1] = images[0];
				buttons[2] = images[0];
				buttons[3] = images[0];
				break;
			case 2:
				buttons[0] = images[0];
				buttons[1] = images[1];
				buttons[2] = images[0];
				buttons[3] = images[0];
				break;
			case 3:
				buttons[0] = images[0];
				buttons[1] = images[1];
				buttons[2] = images[2];
				buttons[3] = images[0];
				break;
			case 4:
				buttons = images;
				break;
			default:
				exception = true;
				break;
			}
		}
		if (!exception) {
			this.setImageUI(buttons, true);
		}

	}

	@Override
	public void createUI(GLEx g, int x, int y, LComponent component,
			LTexture[] buttonImage) {
		LButton button = (LButton) component;
		if (buttonImage != null) {
			if (!button.isEnabled()) {
				g.draw(buttonImage[3], x, y, baseColor);
			} else if (button.isTouchPressed()) {
				g.draw(buttonImage[2], x, y, baseColor);
			} else if (button.isTouchOver()) {
				g.draw(buttonImage[1], x, y, baseColor);
			} else {
				if (type == 1) {
					g.draw(buttonImage[0],
							x,
							y,
							baseColor == null ? LColor.gray : baseColor
									.mul(LColor.gray));
				} else {
					g.draw(buttonImage[0], x, y, baseColor);
				}
			}
		}
		if (text != null) {
			int tmp = g.color();
			g.setColor(fontColor);
			font.drawString(
					g,
					text,
					x + button.getOffsetLeft()
							+ (button.getWidth() - font.stringWidth(text)) / 2,
					y
							+ button.getOffsetTop()
							+ (button.getHeight() - font.getHeight() - font
									.getAscent()) / 2);
			g.setColor(tmp);
		}
	}

	@Override
	public void update(long elapsedTime) {
		if (!visible) {
			return;
		}
		super.update(elapsedTime);
		if (selected) {
			this.pressed = true;
			return;
		}
		if (this.pressedTime > 0 && --this.pressedTime <= 0) {
			this.pressed = false;
		}
	}

	public boolean isTouchOver() {
		return this.over;
	}

	public boolean isTouchPressed() {
		return this.pressed;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String st) {
		this.text = st;
	}

	public void checked() {
		this.pressed = true;
		this.selected = true;
	}

	public void unchecked() {
		this.pressed = false;
		this.selected = false;
	}

	@Override
	protected void processTouchDragged() {
		this.over = this.pressed = this.intersects(this.input.getTouchX(),
				this.input.getTouchY());
		super.processTouchDragged();
	}

	@Override
	protected void processTouchClicked() {
		this.doClick();
	}

	@Override
	protected void processTouchPressed() {
		this.downClick();
		this.pressed = true;
	}

	@Override
	protected void processTouchReleased() {
		this.upClick();
		if (_function != null) {
			_function.call(this);
		}
		this.pressed = false;
	}

	@Override
	protected void processTouchEntered() {
		this.over = true;
	}

	@Override
	protected void processTouchExited() {
		this.over = this.pressed = false;
	}

	@Override
	protected void processKeyPressed() {
		if (this.isSelected()) {
			this.pressedTime = 5;
			this.pressed = true;
			this.doClick();
		}
	}

	@Override
	protected void processKeyReleased() {
		if (this.isSelected()) {
			this.pressed = false;
		}
	}

	public boolean isException() {
		return exception;
	}

	@Override
	public String getUIName() {
		return "Button";
	}

	@Override
	public IFont getFont() {
		return font;
	}

	@Override
	public LButton setFont(IFont font) {
		this.font = font;
		return this;
	}

	public CallFunction getFunction() {
		return _function;
	}

	public void setFunction(CallFunction function) {
		this._function = function;
	}

	public LColor getFontColor() {
		return fontColor;
	}

	public void setFontColor(LColor fontColor) {
		this.fontColor = fontColor;
	}

	public int getOffsetLeft() {
		return offsetLeft;
	}

	public void setOffsetLeft(int offsetLeft) {
		this.offsetLeft = offsetLeft;
	}

	public int getOffsetTop() {
		return offsetTop;
	}

	public void setOffsetTop(int offsetTop) {
		this.offsetTop = offsetTop;
	}

}
