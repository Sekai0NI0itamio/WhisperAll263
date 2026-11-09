package itamio.whisperall;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("whisperall")
public class WhisperAllMod {
    public WhisperAllMod() {
        MinecraftForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("whisperall")
            .requires(s -> s.hasPermission(0))
            .then(Commands.argument("message", com.mojang.brigadier.arguments.StringArgumentType.greedyPhrase())
                .executes(ctx -> { String msg = ctx.getArgument("message", String.class); ctx.getSource().getServer().getPlayerList().broadcastSystemMessage(Component.literal("[WhisperAll] " + msg), false); return 1; })));
    }
}
