package net.runelite.client.plugins.barehandimp;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.Color;

@ConfigGroup("barehandimp")
public interface BarehandImpConfig extends Config {

    @ConfigItem(
            keyName = "enableBodyHighlight",
            name = "Enable Body Highlight",
            description = "Toggle the body highlight of Imps.",
            position = 0
    )
    default boolean isEnableBodyHighlight() {
        return true;
    }

    @ConfigItem(
            keyName = "enableTileHighlight",
            name = "Enable Tile Highlight",
            description = "Toggle the tile highlight of Imps.",
            position = 1
    )
    default boolean isEnableTileHighlight() {
        return true;
    }

    @ConfigItem(
            keyName = "highlightBodyColor",
            name = "Body Highlight Color",
            description = "The color used to highlight the body of Imps.",
            position = 2
    )
    default Color highlightBodyColor() {
        return Color.CYAN;
    }

    @ConfigItem(
            keyName = "highlightTileColor",
            name = "Tile Highlight Color",
            description = "The color used to highlight tiles under Imps.",
            position = 3
    )
    default Color highlightTileColor() {
        return Color.GREEN;
    }

    @ConfigItem(
            keyName = "highlightTileBorderColor",
            name = "Tile Border Highlight Color",
            description = "The border color used to highlight tiles under Imps.",
            position = 4
    )
    default Color highlightTileBorderColor() {
        return Color.CYAN; 
    }

    @ConfigItem(
            keyName = "tileHighlightOpacity",
            name = "Tile Highlight Opacity",
            description = "Adjust the opacity of the tile highlight (0-255).",
            position = 5
    )
    default int tileHighlightOpacity() {
        return 128; 
    }
}
