package com.urkaz.sons_of_macabre.mixin;

import com.curseforge.macabre.procedures.CrackedCrystalizedBloodRightclickedProcedure;
import com.urkaz.sons_of_macabre.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrackedCrystalizedBloodRightclickedProcedure.class)
public class CrackedCrystalizedBloodRightclickedProcedureMixin {
    @Inject(
            method = "execute",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void sons_of_macabre$disableCrackedCrystalizedBloodTeleport(CallbackInfo ci) {
        if (Config.enableCustomPortal) {
            ci.cancel();
        }
    }
}
