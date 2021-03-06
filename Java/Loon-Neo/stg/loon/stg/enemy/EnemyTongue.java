package loon.stg.enemy;

import loon.stg.STGObject;
import loon.stg.STGScreen;

/**
 * Copyright 2008 - 2011
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
 * @project loonframework
 * @author chenpeng
 * @email：ceponline@yahoo.com.cn
 * @version 0.1
 */
public abstract class EnemyTongue extends STGObject {

	String explosion;

	public EnemyTongue(STGScreen stg, int no, float x, float y, int tpno) {
		super(stg, no, x, y, tpno);
		super.attribute = STGScreen.ENEMY;
		super.hitPoint = 10000;
		super.countUpdate = 20;
	}

	public void beDestroyed() {
		this.scrollMove();
		if (this.count == 0) {
			if (explosion != null) {
				addClass(explosion, getX(), getY(), super.plnNo);
			} else {
				onExplosion();
			}
		} else if (this.count > countUpdate || getY() > getScreenHeight()) {
			delete();
		}
		++this.count;
		if (this.count % 2 == 0) {
			setPlaneView(false);
		} else {
			setPlaneView(true);
		}
	}

	@Override
	public void update() {
		beDestroyed();
	}

	public abstract void onExplosion();

}
