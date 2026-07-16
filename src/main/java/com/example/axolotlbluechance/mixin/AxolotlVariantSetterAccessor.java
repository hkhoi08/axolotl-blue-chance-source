package com.example.axolotlbluechance.mixin;

import net.minecraft.world.entity.animal.axolotl.Axolotl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

/**
 * Ham setVariant() trong Axolotl la private, khong the goi thang tu ben ngoai.
 * Dung ky thuat @Invoker cua Mixin de "mo khoa" goi duoc ham private nay
 * ma khong can sua doi code goc cua game.
 */
@Mixin(Axolotl.class)
public interface AxolotlVariantSetterAccessor {

	@Invoker("setVariant")
	void axolotlBlueChance$setVariant(Axolotl.Variant variant);
}
