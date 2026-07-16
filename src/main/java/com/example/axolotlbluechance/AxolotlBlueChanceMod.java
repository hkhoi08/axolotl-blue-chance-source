package com.example.axolotlbluechance;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AxolotlBlueChanceMod implements ModInitializer {

	public static final String MOD_ID = "axolotl-blue-chance";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Ti le axolotl con sinh ra la mau xanh (blue) khi breeding.
	// 0.5F = 50%. Ban co the chinh so nay theo y muon (vi du 0.25F = 25%).
	public static final float BLUE_CHANCE = 0.5F;

	@Override
	public void onInitialize() {
		LOGGER.info("Axolotl Blue Chance mod da duoc tai. Ti le blue: {}%", BLUE_CHANCE * 100);
	}
}
