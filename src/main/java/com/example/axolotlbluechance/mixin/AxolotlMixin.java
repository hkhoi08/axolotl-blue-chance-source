package com.example.axolotlbluechance.mixin;

import com.example.axolotlbluechance.AxolotlBlueChanceMod;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin nay can thiep vao Axolotl.getBreedOffspring(...) - phuong thuc vanilla
 * chiu trach nhiem tao ra con axolotl con khi 2 con axolotl bo me sinh san.
 *
 * Sau khi vanilla da tao xong con axolotl (voi mau duoc chon theo logic goc,
 * bao gom ca ti le 1/1200 ra mau xanh goc), mixin nay se "boc tham lai":
 * co BLUE_CHANCE (mac dinh 50%) de ep con vua sinh ra thanh mau xanh (BLUE),
 * bat ke logic goc da chon mau gi.
 *
 * *** LUU Y QUAN TRONG ***
 * Day la mod duoc viet dua tren cau truc class Axolotl da duoc xac nhan on dinh
 * qua nhieu nam trong Mojang mappings (package net.minecraft.world.entity.animal.axolotl,
 * class Axolotl extends Animal, co EntityDataAccessor DATA_VARIANT va enum Axolotl.Variant
 * voi cac gia tri LUCY, WILD, GOLD, CYAN, BLUE tuong ung Variant id 0-4).
 * Vi ban 26.2 la ban unobfuscated hoan toan moi (moi chuyen tu Yarn sang Mojang
 * mappings chinh thuc), TEN CHINH XAC cua method "getBreedOffspring" va setter
 * "setVariant" CAN DUOC BAN TU KIEM TRA LAI truoc khi build, bang cach:
 *   1. Chay lenh: ./gradlew genSources
 *   2. Mo file da decompile cua Axolotl.java (trong thu muc build cua Loom cache
 *      hoac qua IDE "Go to definition" tren class Axolotl)
 *   3. Doi chieu ten method/enum trong file nay khop voi source that
 * Neu ten khac di, chi can sua lai chuoi "method = ..." va ten enum BLUE ben duoi.
 */
@Mixin(Axolotl.class)
public abstract class AxolotlMixin {

	@Inject(method = "getBreedOffspring", at = @At("RETURN"))
	private void axolotlBlueChance$onBreedOffspring(
			ServerLevel level,
			AgeableMob otherParent,
			CallbackInfoReturnable<AgeableMob> cir
	) {
		AgeableMob result = cir.getReturnValue();
		if (result instanceof Axolotl baby) {
			// Random rieng cua mod, khong dung chung voi random noi bo cua entity
			if (Math.random() < AxolotlBlueChanceMod.BLUE_CHANCE) {
				// setVariant la private nen phai goi qua Invoker interface
				((AxolotlVariantSetterAccessor) baby).axolotlBlueChance$setVariant(Axolotl.Variant.BLUE);
				AxolotlBlueChanceMod.LOGGER.debug("Da ep axolotl con thanh mau xanh (blue)");
			}
		}
	}
}
