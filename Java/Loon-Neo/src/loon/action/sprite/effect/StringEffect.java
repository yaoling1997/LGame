package loon.action.sprite.effect;

import loon.LSystem;
import loon.action.map.Field2D;
import loon.action.sprite.Entity;
import loon.canvas.LColor;
import loon.font.IFont;
import loon.font.Text;
import loon.font.TextOptions;
import loon.geom.Vector2f;
import loon.opengl.GLEx;
import loon.utils.timer.LTimer;

/**
 * 一个字符淡出效果类(主要就是减血加血之类效果用的……)
 */
public class StringEffect extends Entity implements BaseEffect {

	public static float MOVE_VALUE = 1.5f;
	private LTimer _timer = new LTimer(0);
	private Vector2f _update;
	private Text _font;
	private boolean _completed;

	/**
	 * not Move
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect notMove(String mes, Vector2f pos, LColor color) {
		return notMove(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * not Move
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect notMove(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(0, 0), color);
	}

	/**
	 * ↙
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Ddown(String mes, Vector2f pos, LColor color) {
		return m45Ddown(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * ↙
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Ddown(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(-MOVE_VALUE, MOVE_VALUE), color);
	}

	/**
	 * ↗
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Dup(String mes, Vector2f pos, LColor color) {
		return m45Dup(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * ↗
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Dup(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(MOVE_VALUE, -MOVE_VALUE), color);
	}

	/**
	 * ↘
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Dright(String mes, Vector2f pos, LColor color) {
		return m45Dright(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * ↘
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Dright(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(MOVE_VALUE, MOVE_VALUE), color);
	}

	/**
	 * ↖
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Dleft(String mes, Vector2f pos, LColor color) {
		return m45Dleft(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * ↖
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect m45Dleft(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(-MOVE_VALUE, -MOVE_VALUE), color);
	}

	/**
	 * →
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect right(String mes, Vector2f pos, LColor color) {
		return right(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * →
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect right(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(MOVE_VALUE, 0), color);
	}

	/**
	 * ←
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect left(String mes, Vector2f pos, LColor color) {
		return left(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * ←
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect left(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(-MOVE_VALUE, 0), color);
	}

	/**
	 * ↑
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect up(String mes, Vector2f pos, LColor color) {
		return up(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * ↑
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect up(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(0, -MOVE_VALUE), color);
	}

	/**
	 * ↓
	 * 
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect down(String mes, Vector2f pos, LColor color) {
		return down(LSystem.getSystemGameFont(), mes, pos, color);
	}

	/**
	 * ↓
	 * 
	 * @param font
	 * @param mes
	 * @param pos
	 * @param color
	 * @return
	 */
	public final static StringEffect down(IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Vector2f.at(0, MOVE_VALUE), color);
	}

	public final static StringEffect move(int dir, IFont font, String mes, Vector2f pos, LColor color) {
		return new StringEffect(font, mes, pos, Field2D.getDirection(dir).cpy(), color);
	}

	public StringEffect(String mes, Vector2f pos, Vector2f update, LColor c) {
		this(TextOptions.LEFT(), LSystem.getSystemGameFont(), mes, pos, update, c);
	}

	public StringEffect(IFont font, String mes, Vector2f pos, Vector2f update, LColor c) {
		this(TextOptions.LEFT(), font, mes, pos, update, c);
	}

	public StringEffect(TextOptions opt, IFont font, String mes, Vector2f pos, Vector2f update, LColor color) {
		this._font = new Text(font, mes, opt);
		this._update = update;
		this._alpha = 1f;
		this.setLocation(pos);
		this.setColor(color);
		this.setSize(_font.getWidth(), _font.getHeight());
		this.setLocation(pos.x, pos.y);
		this.setRepaint(true);
	}

	public Text getText() {
		return _font;
	}

	@Override
	public void onUpdate(long elapsedTime) {
		if (_completed) {
			return;
		}
		if (_timer.action(elapsedTime)) {
			getLocation().addSelf(this._update);
			this._alpha -= 0.0125f;
			if (_alpha <= 0) {
				_completed = true;
				if (LSystem.getProcess() != null && LSystem.getProcess().getScreen() != null) {
					LSystem.getProcess().getScreen().remove(this);
				}
			}
		}
	}

	@Override
	public void repaint(GLEx g, float offsetX, float offsetY) {
		if (_completed) {
			return;
		}
		_font.paintString(g, drawX(offsetX), drawY(offsetY), _baseColor.mul(this._alpha));
	}

	public void setDelay(long d) {
		_timer.setDelay(d);
	}

	public long getDelay() {
		return _timer.getDelay();
	}

	public void setCompleted(boolean c) {
		this._completed = c;
	}

	@Override
	public boolean isCompleted() {
		return _completed;
	}

	@Override
	public void close() {
		super.close();
		_font.close();
	}

}
