package org.skooma;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@PluginDescriptor(
	name = "Quantify",
    description = "Track items"
)
public class QuantifyPlugin extends Plugin
{
	@Inject
	private Client client;

    @Inject
    private ClientToolbar clientToolbar;

	@Inject
	private QuantifyConfig config;

    @Inject
    private QuantifyPanel panel;

    @Inject
    private NavigationButton navButton;

    // List of itemIDs we want to lookup quantities for
    List<Integer> trackedIDs = new ArrayList<Integer>();

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Quantify started!");
        panel = injector.getInstance(QuantifyPanel.class);
        panel.init(client, config);

        BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/backpack.png");

        navButton = NavigationButton.builder()
                .tooltip("Quantify")
                .icon(icon)
                .priority(10)
                .panel(panel)
                .build();

        clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Quantify stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "[Quantify] Initialized", null);
        }
	}

	@Provides
    QuantifyConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(QuantifyConfig.class);
	}
}
