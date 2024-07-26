package dev.greenhouseteam.greenhouse_common.core.impl.neoforge;

import dev.greenhouseteam.greenhouse_common.core.api.GreenhouseConstants;
import dev.greenhouseteam.greenhouse_common.core.api.GreenhouseCommon;
import dev.greenhouseteam.greenhouse_common.core.impl.neoforge.platform.NeoforgePlatformHelper;
import net.neoforged.fml.common.Mod;

@Mod(GreenhouseConstants.MOD_ID)
public class GreenhouseCommonNeoForge {
    public GreenhouseCommonNeoForge() {
        GreenhouseCommon.init(new NeoforgePlatformHelper());
    }
}
