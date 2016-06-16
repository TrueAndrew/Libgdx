package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.JailBreak;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useCompass = false;					// Отключение компаса для сохранения заряда батареи телефона
		config.useAccelerometer = false;			// Отключение акселерометра для сохранения заряда батареи телефона
		config.useImmersiveMode = true;
		config.useWakelock = true;
		initialize(new JailBreak(), config);
	}
}
