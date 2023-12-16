package com.silvermoon.boxplusplus.util;

import static com.silvermoon.boxplusplus.util.Util.i18n;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumChatFormatting;

import gregtech.api.recipe.check.CheckRecipeResult;
import gregtech.api.recipe.check.CheckRecipeResultRegistry;

public class ResultModuleRequirement implements CheckRecipeResult {

    private int required;
    private boolean isUpdated;

    public ResultModuleRequirement(int required, boolean isUpdated) {
        this.required = required;
        this.isUpdated = isUpdated;
    }

    @Override
    public String getID() {
        return "module_requirement";
    }

    @Override
    public boolean wasSuccessful() {
        return false;
    }

    @Override
    public String getDisplayString() {
        return EnumChatFormatting.AQUA + i18n("tile.boxplusplus.boxUI.37")
            + i18n("tile.boxplusplus_" + (isUpdated ? "boxmoduleplus." : "boxmodule." + required + ".name"));
    }

    @Override
    public CheckRecipeResult newInstance() {
        return new ResultModuleRequirement(0, false);
    }

    @Override
    public void encode(PacketBuffer buffer) {
        buffer.writeVarIntToBuffer(required);
        buffer.writeBoolean(isUpdated);
    }

    @Override
    public void decode(PacketBuffer buffer) {
        required = buffer.readVarIntFromBuffer();
        isUpdated = buffer.readBoolean();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultModuleRequirement that = (ResultModuleRequirement) o;
        return required == that.required && (isUpdated == that.isUpdated);
    }

    static {
        CheckRecipeResultRegistry.register(new ResultModuleRequirement(0, false));
    }
}
