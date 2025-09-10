package com.urkaz.sons_of_macabre.mixin;

import com.urkaz.sons_of_macabre.SonsOfMacabre;
import com.urkaz.sons_of_macabre.config.Config;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class BlockMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void newBlock(BlockBehaviour.Properties properties, CallbackInfo ci) {

        if (Config.fixMacabreBlockColors) {
            String s = this.getClass().getSimpleName();
            MapColor color = SonsOfMacabre.MACABRE_BLOCK_MAP_COLORS.get(s);
            if (color != null) {
                properties.mapColor(color);
            }
        }
    }
}
