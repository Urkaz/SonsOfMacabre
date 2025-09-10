package com.urkaz.sons_of_macabre.config;

import com.google.gson.JsonObject;
import com.urkaz.sons_of_macabre.SonsOfMacabre;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraft.resources.ResourceLocation;

public class ConfigCondition implements ICondition {
    public static final ResourceLocation NAME = ResourceLocation.fromNamespaceAndPath(SonsOfMacabre.MODID, "config_enabled");
    private final String key;

    public ConfigCondition(String key) {
        this.key = key;
    }

    @Override
    public ResourceLocation getID() {
        return NAME;
    }

    @Override
    public boolean test(IContext context) {
        return Config.isEnabled(key);
    }

    public static class Serializer implements IConditionSerializer<ConfigCondition> {
        @Override
        public void write(JsonObject json, ConfigCondition value) {
            json.addProperty("key", value.key);
        }

        @Override
        public ConfigCondition read(JsonObject json) {
            return new ConfigCondition(json.get("key").getAsString());
        }

        @Override
        public ResourceLocation getID() {
            return NAME;
        }
    }
}