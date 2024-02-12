package com.zhelearn.mods.noteconverter;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

@Mod("noteconverter")
public class NoteConverter
{
    public NoteConverter() {
        // ...
    }

    @Mod.EventBusSubscriber(modid = "noteconverter", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ServerEvents {
        @SubscribeEvent
        public static void onServerAboutToStart(FMLServerAboutToStartEvent event) {
            NoteConverterCommand.register(event.getServer().getCommands().getDispatcher());
        }
    }
}
