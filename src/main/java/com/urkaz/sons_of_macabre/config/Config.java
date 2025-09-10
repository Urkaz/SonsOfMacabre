package com.urkaz.sons_of_macabre.config;

import com.urkaz.sons_of_macabre.SonsOfMacabre;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = SonsOfMacabre.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue ONLY_MACABRE_ASHES = BUILDER
            .comment("Only Drop Ether Ashes in Macabre dimension")
            .define("onlyMacabreAshes", true);

    private static final ForgeConfigSpec.BooleanValue ENABLE_CUSTOM_PORTAL = BUILDER
            .comment("Enable custom portal made of flesh blocks")
            .worldRestart()
            .define("enableCustomPortal", true);

    private static final ForgeConfigSpec.BooleanValue USE_OSSEOUS_TAG_AS_BLACKLIST = BUILDER
            .comment("Use sons_of_sins:is_osseous tag as a blacklist")
            .define("isOsseousAsBlacklist", false);

    private static final ForgeConfigSpec.BooleanValue ALWAYS_DROP_ETHER = BUILDER
            .comment("Completely ignores sons_of_sins:is_osseous tag when killing. Also ignores isOsseousAsBlacklist.")
            .define("alwaysDropEther", true);

    private static final ForgeConfigSpec.BooleanValue USE_SIMPLE_RECIPE_FOR_BLOCK_OF_FLESH = BUILDER
            .comment("Replaces the recipe of Flesh Block with a simpler one")
            .worldRestart()
            .define("simpleRecipeBlockOfFlesh", true);

    private static final ForgeConfigSpec.BooleanValue FIX_MACABRE_BLOCK_COLORS = BUILDER
            .comment("Fixes Macabre blocks being invisible on maps")
            .worldRestart()
            .define("fixMacabreBlockColors", true);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean onlyMacabreAshes;
    public static boolean enableCustomPortal;
    public static boolean isOsseousAsBlacklist;
    public static boolean alwaysDropEther;
    public static boolean simpleRecipeBlockOfFlesh;
    public static boolean fixMacabreBlockColors;


    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        onlyMacabreAshes = ONLY_MACABRE_ASHES.get();
        enableCustomPortal = ENABLE_CUSTOM_PORTAL.get();
        isOsseousAsBlacklist = USE_OSSEOUS_TAG_AS_BLACKLIST.get();
        alwaysDropEther = ALWAYS_DROP_ETHER.get();
        simpleRecipeBlockOfFlesh = USE_SIMPLE_RECIPE_FOR_BLOCK_OF_FLESH.get();
        fixMacabreBlockColors = FIX_MACABRE_BLOCK_COLORS.get();
    }

    public static boolean isEnabled(String key) {
            return switch (key) {
                case "simple_flesh" -> simpleRecipeBlockOfFlesh;
                case "complex_flesh" -> !simpleRecipeBlockOfFlesh;
                default -> false;
            };
    }
}
