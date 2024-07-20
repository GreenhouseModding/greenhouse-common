package dev.greenhouseteam.greenhouse_common.mixin.client;

import dev.greenhouseteam.greenhouse_common.GreenhouseCommon;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class Mixin_Minecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        GreenhouseCommon.LOG.info("This line is printed by an example mod common mixin!");
        GreenhouseCommon.LOG.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}