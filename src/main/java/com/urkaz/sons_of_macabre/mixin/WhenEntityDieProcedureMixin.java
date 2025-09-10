package com.urkaz.sons_of_macabre.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.urkaz.sons_of_macabre.config.Config;
import net.mcreator.sonsofsins.procedures.WhenEntityDieProcedure;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WhenEntityDieProcedure.class)
public class WhenEntityDieProcedureMixin {

    @Inject(
            method = "execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/registries/RegistryObject;get()Ljava/lang/Object;",
                    ordinal = 0,
                    remap = true
            ),
            remap = false,
            cancellable = true
    )
    private static void sons_of_macabre$onlyMacabreAshes(CallbackInfo ci,
                                                         @Local(argsOnly = true, ordinal = 1) Entity sourceentity) {
        Level world = sourceentity.level();
        if (Config.onlyMacabreAshes && world.dimension() != ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath("macabre", "the_pit"))) {
            ci.cancel();
        }
    }

    @ModifyExpressionValue(
            method = "execute(Lnet/minecraftforge/eventbus/api/Event;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/tags/TagKey;)Z",
                    remap = true
            ),
            remap = false
    )
    private static boolean sons_of_macabre$overrideOsseousList(boolean original) {
        if (Config.alwaysDropEther)
            return true;

        if (Config.isOsseousAsBlacklist)
            return !original;

        return original;
    }
}
